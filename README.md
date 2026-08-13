# ☕ Curso de Programação Orientada a Objetos com Java

> 📋 Pré-requisito: [Curso de Git e GitHub](https://github.com/jreluiz/curso-git-github) concluído.
> 🎒 Não é preciso saber nenhuma outra linguagem — o curso começa do zero em Java.

## 🎯 Objetivos do curso

Ao final do curso, você será capaz de:

- Escrever, compilar e executar programas Java, com domínio de **tipagem estática**, estruturas de controle e métodos;
- Modelar problemas com **classes e objetos**, aplicando os quatro pilares da POO: abstração, encapsulamento, herança e polimorfismo;
- Guardar dados em **coleções** (`List`, `Map`) e proteger seu programa com **exceções**;
- Organizar um sistema em **pacotes e camadas** e comunicar o modelo com um **diagrama de classes**;
- **Persistir dados em arquivos** e gerar relatórios com **lambdas e a Streams API**;
- Trabalhar como um profissional: todo código versionado com Git e entregue via GitHub.

## 🗺️ Plano de aulas

### Bloco 1 — Fundamentos da linguagem

| Aula | Tema | Conteúdo |
|:---:|------|----------|
| 01 | [Introdução ao Java e à JVM](bloco-1-fundamentos-java/aula-01-introducao-java/README.md) | JDK e JVM, compilar × executar, `main`, primeiro programa |
| 02 | [Variáveis e tipos](bloco-1-fundamentos-java/aula-02-variaveis-tipos/README.md) | Tipagem estática, primitivos, `String`, casting, `Scanner` |
| 03 | [Operadores e condicionais](bloco-1-fundamentos-java/aula-03-operadores-condicionais/README.md) | Operadores, `if`/`else`, `switch`, `==` vs `.equals()` |
| 04 | [Laços, arrays e métodos](bloco-1-fundamentos-java/aula-04-lacos-arrays-metodos/README.md) | `while`, `for`, `for-each`, arrays, métodos e sobrecarga |

### Bloco 2 — Os pilares da POO

| Aula | Tema | Conteúdo |
|:---:|------|----------|
| 05 | [Classes e objetos](bloco-2-pilares-poo/aula-05-classes-objetos/README.md) | Atributos, métodos, `new`, referência, construtores, `this` |
| 06 | [Encapsulamento](bloco-2-pilares-poo/aula-06-encapsulamento/README.md) | `private`, getters/setters com validação, `static`, `toString()` |
| 07 | [Herança](bloco-2-pilares-poo/aula-07-heranca/README.md) | `extends`, `super`, `@Override`, `equals()`, composição |
| 08 | [Polimorfismo e abstração](bloco-2-pilares-poo/aula-08-polimorfismo-abstracao/README.md) | Ligação dinâmica, classes abstratas, interfaces |

### Bloco 3 — POO na prática

| Aula | Tema | Conteúdo |
|:---:|------|----------|
| 09 | [Coleções](bloco-3-poo-na-pratica/aula-09-colecoes/README.md) | `ArrayList`, generics, busca e remoção, `HashMap` |
| 10 | [Exceções](bloco-3-poo-na-pratica/aula-10-excecoes/README.md) | `try`/`catch`, checked × unchecked, exceção personalizada |
| 11 | [Organização do código](bloco-3-poo-na-pratica/aula-11-organizacao-pacotes/README.md) | Pacotes, camadas, `enum`, `record`, diagrama de classes |
| 12 | [Projeto guiado](bloco-3-poo-na-pratica/aula-12-projeto-biblioteca/README.md) | Sistema de biblioteca em console, em 6 etapas |

### Bloco 4 — Java moderno e fechamento

| Aula | Tema | Conteúdo |
|:---:|------|----------|
| 13 | [Arquivos e persistência](bloco-4-java-moderno/aula-13-arquivos-persistencia/README.md) | `Path` e `Files`, CSV, salvar e carregar objetos |
| 14 | [Lambdas e Streams](bloco-4-java-moderno/aula-14-lambdas-streams/README.md) | Interfaces funcionais, `Comparator`, `filter`/`map`/`collect` |
| 15 | [Projeto final](bloco-4-java-moderno/aula-15-projeto-final/README.md) | Desenvolvimento orientado do projeto |
| 16 | [Revisão e próximos passos](bloco-4-java-moderno/aula-16-revisao-proximos-passos/README.md) | Mapa do curso, degustação de JUnit, o que estudar depois |

## 📦 Projetos práticos

| Projeto | Quando | Modalidade |
|---------|:---:|------------|
| [Trabalho em dupla — Sistema via Pull Request](projetos/trabalho-em-dupla.md) | Bloco 3 | Dupla (PRs revisados) |
| [Projeto final — Sistema com persistência](projetos/projeto-final.md) | Bloco 4 | Individual |

## 🔁 O ritual Git de toda aula

**Todo laboratório começa e termina com Git.** Sem exceção:

```bash
# ── Início da aula ──
cd exercicios-java-poo
git pull                                # atualiza (se você usa mais de um PC)

# ── Durante a aula ──
mkdir aula-XX-tema && cd aula-XX-tema   # uma pasta por aula
# ... escreve, compila, erra, lê o compilador, conserta ...
java Ex01.java                          # roda direto, sem javac (JDK 11+)
git add .
git commit -m "Resolve exercícios da aula XX"   # commit por exercício concluído

# ── Fim da aula (OBRIGATÓRIO) ──
git push                                # sem push = sem entrega!
```

> ⚠️ **Nunca versione `.class`.** O `.gitignore` de Java resolve isso — copie o [modelo do curso](.gitignore) para o seu repositório de exercícios.

## 🛠️ Ambiente

Consulte o [guia de preparação do ambiente](recursos/ambiente.md): JDK, IntelliJ IDEA e o primeiro programa rodando.

## ⚡ Links rápidos

- 🧯 [Erros comuns de Java](recursos/erros-comuns.md) — do `cannot find symbol` ao `NullPointerException`
- 📐 [Diagrama de classes em 10 minutos](recursos/diagrama-de-classes.md)
- 🔗 [Links úteis](recursos/links-uteis.md)
- 📚 [Curso de Git e GitHub](https://github.com/jreluiz/curso-git-github) (pré-requisito)

## 📚 Bibliografia

**Livro-base:**

- DEITEL, Paul; DEITEL, Harvey. **Java: como programar**. 10. ed. São Paulo: Pearson, 2017.

**Bibliografia de apoio:**

| Obra | Onde ela ajuda mais |
|---|---|
| SIERRA, Kathy; BATES, Bert. **Use a cabeça! Java**. 2. ed. Rio de Janeiro: Alta Books, 2007. | Blocos 1 e 2 — a linguagem e os pilares explicados devagar; é o livro para **ler junto** com as aulas |
| SANTOS, Rafael. **Introdução à programação orientada a objetos usando Java**. 2. ed. Rio de Janeiro: Elsevier, 2013. | Bloco 2 — POO escrita em português e com olhar acadêmico, do encapsulamento ao polimorfismo |
| BLOCH, Joshua. **Java efetivo: as melhores práticas para a plataforma Java**. 3. ed. Rio de Janeiro: Alta Books, 2019. | Blocos 2 e 4 — por que sobrescrever `equals()` junto com `hashCode()`, e o capítulo de lambdas e streams |
| FOWLER, Martin. **UML essencial**. 3. ed. Porto Alegre: Bookman, 2005. | Aula 11 — diagrama de classes: o que cada seta significa e o que não vale a pena desenhar |
| ORACLE. **The Java® language specification**. Edição corrente. | a norma da linguagem: a resposta final quando duas fontes discordam |

**O que é gratuito e vale abrir agora:** os [Java Tutorials da Oracle](https://dev.java/learn/), a [documentação da API](https://docs.oracle.com/en/java/javase/21/docs/api/index.html) — que você vai consultar em quase toda aula — e a própria [especificação da linguagem](https://docs.oracle.com/javase/specs/).

**O curso é autocontido** — os livros são o passo seguinte, não um pré-requisito. As referências de consulta diária, as ferramentas e os sites de prática estão em [Links úteis](recursos/links-uteis.md).

---

*Este repositório continua evoluindo — exemplos e materiais novos são commitados aqui. Primeiro passo de toda sessão de estudo: `git pull`.* 🙂
