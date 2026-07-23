# Aula 13 — Arquivos e Persistência

> 🎯 Objetivos: gravar e ler arquivos de texto com `Files`, transformar objetos em linhas de CSV e de volta em objetos, e fazer seu sistema lembrar dos dados entre execuções.

## 1. Por que os dados somem

Tudo o que seus programas fizeram até agora vive na **memória RAM** — que é rápida, cara e **volátil**: ao encerrar o processo, o sistema operacional recupera a memória e seus objetos deixam de existir.

Para os dados sobreviverem, é preciso escrevê-los em **armazenamento persistente**: um arquivo em disco, um banco de dados, um servidor. Nesta aula usamos a opção mais simples e mais didática: **arquivo de texto**.

```
   Programa rodando                  Ao fechar                  Ao abrir de novo
  ┌──────────────────┐           ┌──────────────┐            ┌──────────────────┐
  │ List<Livro>      │ ──salvar─▶│ acervo.csv   │ ──carregar▶│ List<Livro>      │
  │ (memória)        │           │ (disco)      │            │ (memória)        │
  └──────────────────┘           └──────────────┘            └──────────────────┘
```

## 2. `Path` e `Files`

A API moderna de arquivos (`java.nio.file`) resolve quase tudo com duas classes:

```java
import java.nio.file.Files;
import java.nio.file.Path;

Path arquivo = Path.of("dados", "acervo.csv");     // dados/acervo.csv

System.out.println(Files.exists(arquivo));         // true/false
System.out.println(arquivo.toAbsolutePath());      // caminho completo
```

> 💡 **Caminho relativo** (`dados/acervo.csv`) parte da pasta em que o programa está rodando; **absoluto** (`/Users/voce/projeto/dados/acervo.csv`) é o endereço completo. Use relativo nos projetos — assim funciona na máquina do colega também.

## 3. Escrevendo

```java
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Escrita {
    public static void main(String[] args) {
        Path arquivo = Path.of("dados", "notas.txt");
        List<String> linhas = List.of("Ana;8.5", "Léo;6.0", "Duda;9.5");

        try {
            Files.createDirectories(arquivo.getParent());          // cria a pasta se faltar
            Files.write(arquivo, linhas);                          // sobrescreve o arquivo
            System.out.println("Salvo em " + arquivo.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Falha ao salvar: " + e.getMessage());
        }
    }
}
```

Para **acrescentar** ao final em vez de sobrescrever:

```java
Files.write(arquivo, List.of("Bruno;7.0"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
```

> ⚠️ `IOException` é **checked** (Aula 10): o compilador **obriga** a tratar com `try/catch` ou declarar `throws`. Sem isso: `error: unreported exception IOException`.

> ⚠️ `Files.write` **apaga o conteúdo anterior** por padrão. Já perdi arquivo assim — e você também vai perder, uma vez.

## 4. Lendo

```java
Path arquivo = Path.of("dados", "notas.txt");

try {
    if (!Files.exists(arquivo)) {
        System.out.println("Nenhum dado salvo ainda.");
        return;
    }

    List<String> linhas = Files.readAllLines(arquivo);
    for (String linha : linhas) {
        System.out.println(linha);
    }
} catch (IOException e) {
    System.out.println("Falha ao ler: " + e.getMessage());
}
```

> 💡 **Sempre verifique `Files.exists`** antes de ler. Na primeira execução do seu sistema o arquivo ainda não existe — e isso é normal, não é erro.

Para arquivos grandes, o `try-with-resources` com `BufferedReader` lê linha a linha sem carregar tudo na memória:

```java
try (var reader = Files.newBufferedReader(arquivo)) {
    String linha;
    while ((linha = reader.readLine()) != null) {
        System.out.println(linha);
    }
} catch (IOException e) {
    System.out.println("Falha: " + e.getMessage());
}
```

## 5. De objeto para linha, e de volta

O arquivo guarda **texto**; seu sistema trabalha com **objetos**. Alguém precisa traduzir nos dois sentidos — e o formato mais simples é o **CSV** (valores separados por um caractere).

**Objeto → texto** (na classe de modelo):

```java
public String toCsv() {
    return String.join(";", codigo, titulo, String.valueOf(ano), autor);
}
// L001;Dom Casmurro;1899;Machado de Assis
```

**Texto → objeto** (um método `static`, porque ele *cria* o objeto):

```java
public static Livro fromCsv(String linha) {
    String[] campos = linha.split(";");
    if (campos.length != 4) {
        throw new IllegalArgumentException("Linha inválida: " + linha);
    }
    return new Livro(campos[0], campos[1], Integer.parseInt(campos[2]), campos[3]);
}
```

> ⚠️ **Escolha um separador que não apareça nos dados.** Se um título tiver `;`, seu `split` produz campos a mais. Por isso `;` costuma ser melhor que `,` em texto em português — e por isso CSV de verdade tem regras de aspas.

> ⚠️ `Integer.parseInt("mil oitocentos")` lança `NumberFormatException`. Ao ler arquivo, **sempre** trate a linha corrompida: pule com aviso em vez de derrubar a carga inteira.

## 6. Salvar e carregar o sistema inteiro

Agora junte tudo. No `BibliotecaService` da Aula 12:

```java
private static final Path ARQUIVO = Path.of("dados", "acervo.csv");

/** Grava todo o acervo em disco, uma linha por item. */
public void salvar() throws IOException {
    List<String> linhas = new ArrayList<>();
    for (ItemAcervo item : acervo) {
        linhas.add(item.toCsv());
    }
    Files.createDirectories(ARQUIVO.getParent());
    Files.write(ARQUIVO, linhas);
}

/** Recarrega o acervo a partir do disco. Arquivo inexistente = acervo vazio. */
public void carregar() throws IOException {
    acervo.clear();
    if (!Files.exists(ARQUIVO)) {
        return;
    }
    for (String linha : Files.readAllLines(ARQUIVO)) {
        if (linha.isBlank()) continue;
        try {
            acervo.add(Livro.fromCsv(linha));
        } catch (RuntimeException e) {
            System.err.println("Linha ignorada: " + linha);
        }
    }
}
```

E no `Main`:

```java
public static void main(String[] args) {
    try {
        service.carregar();                    // ao abrir
    } catch (IOException e) {
        System.out.println("Não foi possível carregar os dados: " + e.getMessage());
    }

    // ... menu ...

    try {
        service.salvar();                      // ao sair
    } catch (IOException e) {
        System.out.println("⚠️  Falha ao salvar! Seus dados podem ter se perdido.");
    }
}
```

Rode, cadastre um livro, saia, abra de novo. O livro está lá. **Seu sistema tem memória.** 🎉

> 💡 **Salvar ao sair é frágil** — se o programa fechar de forma anormal, tudo se perde. Uma opção mais segura é salvar após **cada** operação que altera dados. Em sistemas reais, esse papel é do banco de dados (assunto do próximo curso).

> 💻 **Código desta aula pronto para rodar:** [`Contato.java`](exemplos/Contato.java) + [`ArquivosDemo.java`](exemplos/ArquivosDemo.java) (rode duas vezes seguidas!)

## 🏋️ Exercícios da aula

Na pasta `aula-13/` do seu repositório:

1. **`Diario.java`** — programa que pede uma frase e a acrescenta (`APPEND`) a `diario.txt`, com data e hora à frente (`LocalDateTime.now()`); ao iniciar, mostra as entradas já registradas;
2. **`Contatos.java`** — `List<Contato>` com menu de cadastro/listagem; `toCsv()` e `fromCsv()` na classe `Contato`; carrega ao abrir, salva ao sair. Teste executando duas vezes seguidas;
3. **`LeituraRobusta.java`** — crie à mão um `notas.csv` com **erros de propósito** (linha em branco, campo faltando, `oito` no lugar de `8`); seu programa deve carregar as linhas boas, listar as ruins com o número da linha e não quebrar em nenhuma;
4. **`Relatorio.java`** — leia um CSV de vendas (`produto;quantidade;valor`), calcule total geral, produto mais vendido e média por venda, e **grave o resultado** num `relatorio.txt` formatado;
5. **Desafio 🌶️ `BibliotecaPersistente/`** — acrescente persistência ao projeto da Aula 12: salvar/carregar **acervo, usuários e empréstimos** (três arquivos), preservando o vínculo entre eles (dica: grave o **código** do item e o **id** do usuário no empréstimo, e reconecte os objetos ao carregar). Salve após cada operação que altera dados.

## 🧠 Revisão

[8 questões de múltipla escolha](revisao/README.md) para conferir se os conceitos ficaram sólidos. Responda sem consultar a aula — depois volte e corrija.

## ✅ Entrega

```bash
git add aula-13/
git commit -m "Resolve exercícios da aula 13 (arquivos e persistência)"
git push
```

> ⚠️ **Não versione a pasta `dados/`** — arquivos gerados pelo programa não vão para o Git. Acrescente `dados/` ao seu `.gitignore`.

---

⬅️ [Aula 12](../../bloco-3-poo-na-pratica/aula-12-projeto-biblioteca/README.md) | ➡️ [Aula 14 — Lambdas e Streams](../aula-14-lambdas-streams/README.md)
