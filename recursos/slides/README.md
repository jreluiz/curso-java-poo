# 🎬 Como as apresentações são feitas

As apresentações de cada módulo são escritas em **Markdown** e convertidas em PDF pelo [Marp](https://marp.app/). Nada precisa ser instalado: o `npx` baixa as ferramentas sob demanda e o export usa o Google Chrome que já está na máquina.

Cada módulo guarda a sua apresentação ao lado do material escrito:

```
01-conceitos/
├── README.md                        # a aula escrita
├── apresentacao-01-conceitos.md     # a fonte dos slides  ← edite este
├── apresentacao-01-conceitos.pdf    # o gerado            ← projete este
└── img/
    ├── tres-estados.mmd             # diagrama, versão para projeção
    └── tres-estados.svg             # gerado do .mmd
```

**Os dois são versionados.** O `.md` para o `git diff` mostrar o que mudou; o `.pdf` para abrir na aula sem depender de gerar nada na hora.

## Gerar

```bash
bash recursos/slides/gerar.sh                 # tudo o que estiver desatualizado
bash recursos/slides/gerar.sh 01-conceitos    # só um módulo
bash recursos/slides/gerar.sh --forcar        # regera tudo
bash recursos/slides/gerar.sh --html          # gera .html além do .pdf
```

O script compara datas: só regera o que mudou, e regera todos os decks quando o tema muda.

> 💡 **Enquanto escreve**, a extensão [Marp for VS Code](https://marketplace.visualstudio.com/items?itemName=marp-team.marp-vscode) mostra o preview lado a lado e evita rodar o script a cada ajuste. Para ela enxergar o tema, adicione em `.vscode/settings.json`:
> ```json
> { "markdown.marp.themes": ["./recursos/slides/trilha.css"] }
> ```

## Conferir se algum slide estourou

```bash
python3 recursos/slides/conferir.py            # todos os decks
python3 recursos/slides/conferir.py 06-fluxo   # só um
```

**O Marp não avisa quando o conteúdo passa do slide** — ele simplesmente deixa o texto atravessar o rodapé, e o defeito só aparece olhando o PDF. Este script estima a altura de cada slide a partir das métricas do `trilha.css` e marca:

- ❌ acima de 102% da área útil — quase certamente estourando;
- ⚠️ acima de 92% — no limite, vale abrir o PNG e olhar.

É estimativa, não medição: aponta onde olhar, não substitui a conferida visual. Rode antes de fechar cada deck.

> 💡 Regra de bolso que sai das métricas: um bloco de código sozinho com o título cabe até **~11 linhas**; uma tabela de 2 colunas, até **~8 linhas**. Passou disso, divida em dois slides.

## Escrever um deck

`---` separa slides. O cabeçalho vai só no começo do arquivo:

```markdown
---
marp: true
theme: trilha
paginate: true
lang: pt-BR
footer: '📚 Curso de Git e GitHub · Módulo 1'
---
```

### Classes de slide

Aplicadas com um comentário na primeira linha do slide: `<!-- _class: lead -->`.

| Classe | Para quê |
|---|---|
| `capa` | Primeiro slide. Faixa colorida na lateral, sem número de página |
| `lead` | Uma ideia só, centralizada, fundo tingido — as analogias 💡 |
| `diagrama` | Título no topo e a imagem centrada no espaço restante |
| `checkpoint` | Fechamento do módulo, fundo tingido |
| `lista-limpa` | Lista cujos itens já começam com emoji (tira a bolinha) |
| `tabela-densa` | Tabelas de 7 linhas ou mais |

Pode combinar: `<!-- _class: lead lista-limpa -->`.

### Diagramas

O `.mmd` do slide é **propositalmente diferente** do bloco ` ```mermaid ` do README: o do README é para ler de perto; o do slide precisa de rótulos curtos e traço grosso para sobreviver à projeção.

Ao inserir, **declare a largura** — sem ela o SVG colapsa:

```markdown
![w:1140](img/tres-estados.svg)
```

## Armadilhas já resolvidas

Estas cinco custaram tempo. Estão documentadas no CSS, no `gerar.sh` e no `marp.config.mjs`, mas ficam aqui também:

0. **O `marp-cli` lê o stdin como mais um documento** quando ele não é um TTY. Dentro de um `while read` alimentado por `find`, o stdin herdado carrega o caminho do *próximo* deck — o marp conta dois documentos e aborta com *"Output path cannot specify with processing multiple files"*. Daí o `< /dev/null` em toda chamada de `npx` dentro dos laços do `gerar.sh`. **Fica invisível enquanto o repositório tem um único deck**;

1. **`fontFamily` do Mermaid é chave de topo**, não de `themeVariables` — lá dentro é silenciosamente ignorada;
2. **O tema `default` do Marp embute o CSS de Markdown do GitHub**, que traz `section table { display: block; width: max-content }`. Como o Marp prefixa as regras do tema com `section`, um seletor solto (`td` → `section td`) perde para o `section table td` do GitHub. Por isso as regras de tabela já vêm qualificadas com `table`;
3. **Emoji do Marp virava `<img>` de CDN externo.** O `marp.config.mjs` desliga o Twemoji: sem isso, gerar o PDF e exibir o HTML exigiriam internet — e qualquer regra CSS de `img` afetaria emoji;
4. **`:only-child` ignora nós de texto.** Num parágrafo `<img>seguido de texto</p>` a imagem conta como filha única, então uma regra `p > img:only-child` pega o emoji de um callout.

## Adaptar para outro curso da trilha

Copie `recursos/slides/` inteiro e mude **apenas** as duas variáveis no topo do `trilha.css`:

| Curso | `--accent` | `--accent-suave` |
|---|---|---|
| 📚 Git e GitHub | `#f05033` | `#fdeeeb` |
| 🟨 JavaScript | `#c9a800` | `#fdf9e3` |
| ☕ Java POO | `#e76f00` | `#fdf1e6` |
| 🗄️ Modelagem de Dados | `#336791` | `#eaf0f5` |
| 🔵 VS Code | `#007acc` | `#e6f3fb` |

O `gerar.sh` funciona sem edição — ele varre por `apresentacao-*.md` e `*.mmd`.

---

🏠 [Voltar ao início](../../README.md)
