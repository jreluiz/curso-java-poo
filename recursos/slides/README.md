# 🎬 Como as apresentações são feitas

As apresentações de cada aula são escritas em **Markdown** e convertidas em PDF pelo [Marp](https://marp.app/). Nada precisa ser instalado: o `npx` baixa as ferramentas sob demanda e o export usa o Google Chrome que já está na máquina.

Cada aula guarda a sua apresentação numa subpasta `apresentacao/`, ao lado do material escrito:

```
bloco-2-pilares-poo/aula-05-classes-objetos/
├── README.md                                  # a aula escrita
└── apresentacao/
    ├── apresentacao-05-classes-objetos.md     # a fonte   ← edite este
    ├── apresentacao-05-classes-objetos.pdf    # o gerado  ← projete este
    └── img/
        ├── classe-e-objetos.mmd               # diagrama para projeção
        └── classe-e-objetos.svg               # gerado do .mmd
```

O `img/` fica **dentro** de `apresentacao/`: os diagramas são feitos para projeção e não são usados pelo README da aula.

**O `.md` e o `.pdf` são versionados.** O `.md` para o `git diff` mostrar o que mudou; o `.pdf` para abrir na aula sem depender de gerar nada na hora.

Cada aula aponta para o seu PDF logo abaixo do título, numa linha `> 🎬 Slides da aula:`.

## Gerar

```bash
bash recursos/slides/gerar.sh           # tudo o que estiver desatualizado
bash recursos/slides/gerar.sh aula-05   # só uma aula
bash recursos/slides/gerar.sh --forcar  # regera tudo
bash recursos/slides/gerar.sh --html    # gera .html além do .pdf
```

O script compara datas: só regera o que mudou, e regera todos os decks quando o tema muda.

> ⚠️ O `trilha.css` e o `marp.config.mjs` precisam ficar **ao lado do `gerar.sh`**, nesta pasta. O script resolve os caminhos a partir da própria localização e aborta se não os encontrar.

> 💡 **Enquanto escreve**, a extensão [Marp for VS Code](https://marketplace.visualstudio.com/items?itemName=marp-team.marp-vscode) mostra o preview lado a lado e evita rodar o script a cada ajuste. Para ela enxergar o tema, adicione em `.vscode/settings.json`:
> ```json
> { "markdown.marp.themes": ["./recursos/slides/trilha.css"] }
> ```

## Conferir se algum slide estourou

```bash
python3 recursos/slides/conferir.py          # todos os decks
python3 recursos/slides/conferir.py aula-05  # só um
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
footer: '☕ Curso de Java e POO · Aula 05'
---
```

### Classes de slide

Aplicadas com um comentário na primeira linha do slide: `<!-- _class: lead -->`.

| Classe | Para quê |
|---|---|
| `capa` | Primeiro slide. Faixa colorida na lateral, sem número de página |
| `lead` | Uma ideia só, centralizada, fundo tingido — as analogias 💡 |
| `diagrama` | Título no topo e a imagem centrada no espaço restante |
| `checkpoint` | Fechamento da aula, fundo tingido |
| `lista-limpa` | Lista cujos itens já começam com emoji (tira a bolinha) |
| `tabela-densa` | Tabelas de 7 linhas ou mais |

Pode combinar: `<!-- _class: lead lista-limpa -->`.

### Diagramas

O `.mmd` do slide é **propositalmente diferente** do bloco ` ```mermaid ` do README: o do README é para ler de perto; o do slide precisa de rótulos curtos e traço grosso para sobreviver à projeção.

Ao inserir, **declare a largura** — sem ela o SVG colapsa:

```markdown
![w:1000](img/classe-e-objetos.svg)
```

## Armadilhas já resolvidas

Estas custaram tempo. Estão documentadas no CSS, no `gerar.sh` e no `marp.config.mjs`, mas ficam aqui também:

1. **O `marp-cli` lê o stdin como mais um documento** quando ele não é um TTY. Dentro de um `while read` alimentado por `find`, o stdin herdado carrega o caminho do *próximo* deck — o marp conta dois documentos e aborta com *"Output path cannot specify with processing multiple files"*. Daí o `< /dev/null` em toda chamada de `npx` dentro dos laços do `gerar.sh`. **Fica invisível enquanto o repositório tem um único deck**;

2. **`fontFamily` do Mermaid é chave de topo**, não de `themeVariables` — lá dentro é silenciosamente ignorada;

3. **O tema `default` do Marp embute o CSS de Markdown do GitHub**, que traz `section table { display: block; width: max-content }`. Como o Marp prefixa as regras do tema com `section`, um seletor solto (`td` → `section td`) perde para o `section table td` do GitHub. Por isso as regras de tabela já vêm qualificadas com `table`;

4. **Emoji do Marp virava `<img>` de CDN externo.** O `marp.config.mjs` desliga o Twemoji: sem isso, gerar o PDF e exibir o HTML exigiriam internet — e qualquer regra CSS de `img` afetaria emoji;

5. **`:only-child` ignora nós de texto.** Num parágrafo `<img>seguido de texto</p>` a imagem conta como filha única, então uma regra `p > img:only-child` pega o emoji de um callout;

6. **Caminho fixo para o tema quebra ao mover a pasta.** O `gerar.sh` resolve tudo a partir de `BASH_SOURCE` e valida a existência dos arquivos antes de começar.

## Adaptar para outro curso da trilha

Copie `recursos/slides/` inteiro e mude **apenas** as duas variáveis no topo do `trilha.css`:

| Curso | `--accent` | `--accent-suave` |
|---|---|---|
| 📚 Git e GitHub | `#f05033` | `#fdeeeb` |
| 🔵 VS Code | `#007acc` | `#e6f3fb` |
| 🟨 JavaScript | `#c9a800` | `#fdf9e3` |
| ☕ Java e POO | `#e76f00` | `#fdf1e6` |
| 🗄️ Modelagem de Dados | `#336791` | `#eaf0f5` |

O `gerar.sh` funciona sem edição — ele varre por `apresentacao-*.md` e `*.mmd` a partir da raiz do repositório, seja qual for a profundidade das pastas.

> 🔵 O `curso-vscode` é a exceção: lá o material é um `.md` por módulo na raiz, sem pasta por módulo, então os decks ficam todos juntos numa única pasta `apresentacao/`.

---

🏠 [Voltar ao início](../../README.md)
