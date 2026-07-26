#!/usr/bin/env bash
#
# Gera as apresentações do curso.
#
#   bash recursos/slides/gerar.sh              # gera tudo o que estiver desatualizado
#   bash recursos/slides/gerar.sh 01-conceitos # gera só um módulo
#   bash recursos/slides/gerar.sh --forcar     # regera tudo, ignorando cache
#   bash recursos/slides/gerar.sh --html       # gera .html além do .pdf
#
# Pipeline por módulo:
#   NN-modulo/img/*.mmd                → NN-modulo/img/*.svg      (mermaid-cli)
#   NN-modulo/apresentacao-NN-*.md     → NN-modulo/apresentacao-NN-*.pdf  (marp-cli)
#
# Requisitos: node + npx (as ferramentas são baixadas no cache do npx) e
# Google Chrome instalado, usado pelo Marp para exportar o PDF.

set -euo pipefail

RAIZ="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
TEMA="$RAIZ/recursos/slides/trilha.css"
CONFIG="$RAIZ/recursos/slides/marp.config.mjs"

MERMAID="@mermaid-js/mermaid-cli@11"
MARP="@marp-team/marp-cli@4"

FORCAR=0
HTML=0
FILTRO=""

for arg in "$@"; do
  case "$arg" in
    --forcar|-f) FORCAR=1 ;;
    --html)      HTML=1 ;;
    -h|--help)   sed -n '2,20p' "${BASH_SOURCE[0]}" | sed 's/^# \{0,1\}//'; exit 0 ;;
    *)           FILTRO="$arg" ;;
  esac
done

cd "$RAIZ"

# Chrome: o Marp encontra sozinho na maioria dos casos, mas no macOS
# apontar explicitamente evita o erro "Could not find Chrome".
if [[ -z "${CHROME_PATH:-}" ]]; then
  for c in "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome" \
           "/Applications/Microsoft Edge.app/Contents/MacOS/Microsoft Edge" \
           "/Applications/Chromium.app/Contents/MacOS/Chromium"; do
    [[ -x "$c" ]] && export CHROME_PATH="$c" && break
  done
fi

if [[ -z "${CHROME_PATH:-}" ]]; then
  echo "❌ Nenhum navegador baseado em Chromium encontrado."
  echo "   Instale o Google Chrome ou defina CHROME_PATH manualmente."
  exit 1
fi

# `desatualizado ORIGEM DESTINO` → 0 quando precisa regerar
desatualizado() {
  [[ $FORCAR -eq 1 ]] && return 0
  [[ ! -f "$2" ]] && return 0
  [[ "$1" -nt "$2" ]] && return 0
  return 1
}

echo "📁 Raiz .......... $RAIZ"
echo "🎨 Tema .......... ${TEMA#$RAIZ/}"
echo "🌐 Chrome ........ $CHROME_PATH"
[[ -n "$FILTRO" ]] && echo "🔎 Filtro ........ $FILTRO"
echo

# ---------------------------------------------------------------- diagramas
svg_gerados=0
svg_pulados=0

while IFS= read -r mmd; do
  [[ -n "$FILTRO" && "$mmd" != *"$FILTRO"* ]] && continue
  svg="${mmd%.mmd}.svg"
  if desatualizado "$mmd" "$svg"; then
    echo "🖼️  ${mmd#./}  →  ${svg##*/}"
    # `< /dev/null`: ver o comentário no laço dos decks, abaixo
    npx -y "$MERMAID" -i "$mmd" -o "$svg" -b transparent --quiet </dev/null >/dev/null
    svg_gerados=$((svg_gerados + 1))
  else
    svg_pulados=$((svg_pulados + 1))
  fi
done < <(find . -name '*.mmd' -not -path './.git/*' | sort)

# ------------------------------------------------------------------- decks
decks_gerados=0
decks_pulados=0

while IFS= read -r deck; do
  [[ -n "$FILTRO" && "$deck" != *"$FILTRO"* ]] && continue
  pdf="${deck%.md}.pdf"

  # o deck também é regerado quando o tema muda
  if desatualizado "$deck" "$pdf" || desatualizado "$TEMA" "$pdf"; then
    echo "📊 ${deck#./}  →  ${pdf##*/}"
    #
    # --allow-local-files permite embutir os SVG dos diagramas;
    # o resto (tags HTML, emoji nativo) vem do marp.config.mjs.
    #
    # ⚠️ `< /dev/null` é OBRIGATÓRIO aqui. O marp-cli lê o stdin como mais
    # um documento quando ele não é um TTY — e, dentro deste `while read`,
    # o stdin herdado é o pipe do `find` com o caminho do PRÓXIMO deck
    # ainda na fila. Sem isto o marp conta dois documentos e aborta com
    # "Output path cannot specify with processing multiple files".
    # O bug fica invisível enquanto existe um único deck no repositório.
    #
    npx -y "$MARP" "$deck" --pdf --allow-local-files \
      --config "$CONFIG" --theme "$TEMA" -o "$pdf" </dev/null
    [[ $HTML -eq 1 ]] && npx -y "$MARP" "$deck" --allow-local-files \
      --config "$CONFIG" --theme "$TEMA" -o "${deck%.md}.html" </dev/null
    decks_gerados=$((decks_gerados + 1))
  else
    decks_pulados=$((decks_pulados + 1))
  fi
done < <(find . -name 'apresentacao-*.md' -not -path './.git/*' | sort)

echo
echo "✅ Diagramas: $svg_gerados gerados, $svg_pulados em dia"
echo "✅ Decks:     $decks_gerados gerados, $decks_pulados em dia"
[[ $((svg_gerados + decks_gerados)) -eq 0 ]] && echo "   (nada mudou — use --forcar para regerar assim mesmo)"
exit 0
