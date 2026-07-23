# 📚 Projeto de referência — Sistema de Biblioteca

Versão completa do sistema construído nas 6 etapas da [Aula 12](../README.md). Use como **consulta**, não como cópia: construa o seu, e venha aqui quando travar.

## Como executar

Este projeto usa **pacotes**, então o comando tem duas partes: compilar para uma pasta de saída e executar informando o *classpath*.

```bash
cd exemplos
javac -d out $(find src -name "*.java")     # compila tudo em out/
java -cp out biblioteca.app.Main            # executa
```

No Windows (PowerShell), a busca de arquivos muda:

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out biblioteca.app.Main
```

**No IntelliJ é mais simples:** abra a pasta `exemplos` como projeto, marque `src` como *Sources Root* (botão direito → *Mark Directory as* → *Sources Root*) e clique no ▶️ ao lado do `main`.

O sistema já sobe com 4 itens e 2 usuários cadastrados, para você testar sem digitar nada.

## Estrutura

```
src/biblioteca/
├── model/                        ← o domínio: dados + regras do próprio objeto
│   ├── Emprestavel.java          interface (capacidade)
│   ├── ItemAcervo.java           classe abstrata (implementa Emprestavel)
│   ├── Livro.java                subclasse concreta
│   ├── Revista.java              subclasse concreta
│   ├── ObraDeReferencia.java     subclasse que RECUSA empréstimo
│   ├── TipoUsuario.java          enum com limite e prazo
│   ├── Usuario.java
│   └── Emprestimo.java           registro com datas e multa
├── service/                      ← orquestra as regras; NÃO imprime nada
│   ├── BibliotecaService.java
│   └── excecoes/                 4 exceções personalizadas
└── app/
    └── Main.java                 ← única camada que lê teclado e imprime
```

## O roteiro de teste que vale a pena repetir

1. `5` — listar acervo (repare nas três descrições diferentes: polimorfismo);
2. `3` com `L001` e `U001` — empréstimo válido, com data de devolução calculada pelo `TipoUsuario`;
3. `3` com `L001` de novo — `ItemIndisponivelException`;
4. `3` com `O001` — a obra de referência recusa (`UnsupportedOperationException`);
5. `3` com `L999` ou `U999` — item/usuário inexistente;
6. `3` quatro vezes com o mesmo `U001` (ALUNO, limite 3) — `LimiteExcedidoException`;
7. `4` com `L001` — devolução;
8. Digite `abc` em qualquer pergunta numérica — o programa avisa e continua.

## O que este projeto exemplifica

| Conceito | Onde olhar |
|----------|-----------|
| Classe abstrata + método abstrato | `ItemAcervo.descricao()` |
| Interface | `Emprestavel`, implementada por `ItemAcervo` |
| Polimorfismo | `toString()` do `ItemAcervo` chamando `descricao()` |
| Encapsulamento | `Usuario.getItensEmMaos()` devolvendo cópia |
| `equals`/`hashCode` | `ItemAcervo` (por código) e `Usuario` (por id) |
| Enum com atributos | `TipoUsuario` |
| Exceções personalizadas | `service/excecoes/` |
| Camadas | nenhum `System.out` fora de `app/` |
| Javadoc | `BibliotecaService.emprestar()` |

## Próximo passo

Na [Aula 13](../../../bloco-4-java-moderno/aula-13-arquivos-persistencia/README.md) este projeto ganha **persistência em arquivo** — e passa a lembrar dos dados entre execuções.
