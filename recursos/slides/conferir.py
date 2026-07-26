#!/usr/bin/env python3
"""
Estima a altura de cada slide e avisa quando o conteúdo passa da área útil.

    python3 recursos/slides/conferir.py                 # todos os decks
    python3 recursos/slides/conferir.py 06-fluxo        # só um

Por que existe: o Marp NÃO avisa quando o conteúdo estoura o slide — ele
simplesmente deixa o texto passar por cima do rodapé, e o defeito só aparece
olhando o PDF. Com 61 decks previstos, conferir a olho um a um não escala.

É uma ESTIMATIVA, calibrada nas métricas do trilha.css. Serve para apontar
onde olhar, não para substituir a conferida visual dos slides marcados.
"""

import re
import sys
from pathlib import Path

# --- métricas do trilha.css (px, sobre a tela de 1280x720) ---
ALTURA = 720
PAD_TOPO, PAD_BASE = 60, 78
UTIL = ALTURA - PAD_TOPO - PAD_BASE          # 582
LARGURA_UTIL = 1280 - 70 * 2                 # 1140

H2 = 48 + 28 + 14 + 3                        # linha + margem + padding + borda
LINHA_P = 39                                 # 26px * 1.5
MARGEM_P = 18
LINHA_LI = 39
MARGEM_LI = 14
LINHA_PRE = 34                               # 22px * 1.55
CAIXA_PRE = 44 + 20                          # padding vertical + margem
LINHA_CITA = 38                              # 25px * 1.5
CAIXA_CITA = 36 + 20
# Medidos nos PNGs: a `tabela-densa` (fonte 22, padding 9) rende linhas bem
# mais baixas que a tabela comum (fonte 25, padding 13). Usar um valor só
# subestimava toda tabela normal.
CAB_TABELA, LINHA_TABELA = 61, 64             # tabela comum
CAB_DENSA, LINHA_DENSA = 55, 52               # com a classe tabela-densa
MARGEM_TABELA = 20

CHARS_P = int(LARGURA_UTIL / 13.2)           # ~86 caracteres por linha a 26px


def chars_por_celula(n_colunas: int, densa: bool) -> int:
    """
    Quantos caracteres cabem numa célula antes de quebrar.

    Depende do número de colunas — uma tabela de 2 colunas tem células quase
    o dobro das de uma de 3. Sem isto o estimador acusa quebra onde não há,
    e uma tabela de 2 colunas larga aparece como 136% do slide.
    """
    largura_col = LARGURA_UTIL / max(1, n_colunas) - 36   # menos o padding
    # ~0,45em de largura média por caractere, medido nos PNGs renderizados
    largura_char = 10.0 if densa else 11.4                # 22px vs 25px
    return max(8, int(largura_col / largura_char))


# A estimativa é ~4% pessimista (conferido contra slides que cabem por pouco),
# então o erro só dispara acima disso; entre o aviso e o erro, olhe o PNG.
LIMITE_AVISO = 0.92
LIMITE_ERRO = 1.02


def limpa(txt: str) -> str:
    """Tira marcação que não ocupa espaço, para contar caracteres visíveis."""
    txt = re.sub(r"`([^`]*)`", r"\1", txt)
    txt = re.sub(r"\*\*|\*|~~", "", txt)
    txt = re.sub(r"\[([^\]]*)\]\([^)]*\)", r"\1", txt)
    txt = re.sub(r"<[^>]+>", "", txt)
    return txt.strip()


def linhas_de(texto: str, chars_por_linha: int = CHARS_P) -> int:
    return max(1, -(-len(limpa(texto)) // chars_por_linha))


def altura_do_slide(fonte: str) -> tuple[int, list[str]]:
    """Devolve (altura estimada, lista de blocos) de um slide."""
    alt, blocos = 0, []
    densa = "tabela-densa" in fonte
    linhas = fonte.split("\n")
    i = 0
    while i < len(linhas):
        ln = linhas[i]

        if ln.startswith("```"):                       # bloco de código
            j = i + 1
            while j < len(linhas) and not linhas[j].startswith("```"):
                j += 1
            n = j - i - 1
            alt += n * LINHA_PRE + CAIXA_PRE
            blocos.append(f"código de {n} linhas")
            i = j + 1
            continue

        if ln.startswith("|"):                          # tabela
            j = i
            while j < len(linhas) and linhas[j].startswith("|"):
                j += 1
            n = j - i - 2                               # tira cabeçalho e separador
            n_col = len(ln.split("|")) - 2
            largura = chars_por_celula(n_col, densa)
            # uma célula que quebra empurra a linha inteira para baixo
            extra = sum(
                max(0, linhas_de(c, largura) - 1)
                for l in linhas[i:j]
                for c in l.split("|")[1:-1]
            )
            cab = CAB_DENSA if densa else CAB_TABELA
            linha = LINHA_DENSA if densa else LINHA_TABELA
            alt += cab + max(0, n) * linha + extra * 30 + MARGEM_TABELA
            blocos.append(f"tabela de {max(0, n)} linhas x {n_col} col")
            i = j
            continue

        if ln.startswith("## "):
            alt += H2
            blocos.append("h2")
        elif ln.startswith("> "):
            alt += linhas_de(ln[2:]) * LINHA_CITA + CAIXA_CITA
            blocos.append("callout")
        elif re.match(r"^\s*[-*]\s+|^\s*\d+\.\s+", ln):
            alt += linhas_de(ln) * LINHA_LI + MARGEM_LI
            blocos.append("item")
        elif ln.startswith("!["):
            m = re.search(r"w:(\d+)", ln)
            alt += int(int(m.group(1)) * 0.28) if m else 260   # proporção típica
            blocos.append("imagem")
        elif ln.strip() and not ln.startswith("<!--") and not ln.startswith("<"):
            alt += linhas_de(ln) * LINHA_P + MARGEM_P
            blocos.append("parágrafo")
        i += 1

    return alt, blocos


def main() -> int:
    raiz = Path(__file__).resolve().parents[2]
    filtro = sys.argv[1] if len(sys.argv) > 1 else ""
    problemas = 0

    # rglob, não glob("*/…"): em curso-vscode os decks ficam na RAIZ do
    # repositório, porque lá o material é um .md por módulo, sem subpasta.
    decks = (d for d in raiz.rglob("apresentacao-*.md") if ".git" not in d.parts)
    for deck in sorted(decks):
        if filtro and filtro not in str(deck):
            continue
        corpo = deck.read_text(encoding="utf-8")
        corpo = re.sub(r"^---\n.*?\n---\n", "", corpo, count=1, flags=re.S)
        slides = corpo.split("\n---\n")

        print(f"\n📊 {deck.relative_to(raiz)}  ({len(slides)} slides)")
        for n, s in enumerate(slides, 1):
            if "_class: capa" in s:                     # capa tem layout próprio
                continue
            alt, blocos = altura_do_slide(s)
            razao = alt / UTIL
            if razao > LIMITE_ERRO:
                print(f"   ❌ slide {n:>2}: ~{alt}px de {UTIL} "
                      f"({razao:.0%}) — {', '.join(blocos[:5])}")
                problemas += 1
            elif razao > LIMITE_AVISO:
                print(f"   ⚠️  slide {n:>2}: ~{alt}px de {UTIL} "
                      f"({razao:.0%}) — no limite, confira")

    print()
    if problemas:
        print(f"❌ {problemas} slide(s) provavelmente estourando. "
              f"Renderize em PNG e confirme.")
        return 1
    print("✅ Nenhum slide estourando pela estimativa.")
    return 0


if __name__ == "__main__":
    sys.exit(main())
