# Aula 10 — Exceções

> 🎯 Objetivos: impedir que o programa quebre na mão do usuário com `try`/`catch`, distinguir exceções *checked* e *unchecked*, lançar as suas próprias e criar exceções personalizadas.

## 1. O programa que quebra na primeira digitação errada

```java
Scanner scanner = new Scanner(System.in);
System.out.print("Sua idade: ");
int idade = scanner.nextInt();          // usuário digita "vinte"
System.out.println("Você tem " + idade);
```

```
Exception in thread "main" java.util.InputMismatchException
	at java.base/java.util.Scanner.throwFor(Scanner.java:964)
	...
```

O programa **morreu**. Não imprimiu nada, não salvou nada, não avisou nada compreensível. Em um sistema real, isso é inaceitável — e a culpa não é do usuário.

Quando algo dá errado, o Java **lança uma exceção**: cria um objeto que descreve o problema e interrompe a execução, subindo pela pilha de chamadas até alguém tratá-lo. Se ninguém tratar, o programa encerra e imprime aquele *stack trace*.

> 💡 O *stack trace* não é castigo — é mapa. A primeira linha diz **o que** aconteceu; as linhas `at ...` mostram o caminho até lá. Procure a primeira linha que cita **uma classe sua**: é ali que começa a investigação.

## 2. `try` / `catch` / `finally`

```java
Scanner scanner = new Scanner(System.in);

try {
    System.out.print("Sua idade: ");
    int idade = scanner.nextInt();
    System.out.println("Você tem " + idade);
} catch (InputMismatchException e) {
    System.out.println("Digite um número inteiro, por favor.");
    scanner.nextLine();                  // limpa a entrada inválida do buffer!
} finally {
    System.out.println("Leitura encerrada.");   // roda SEMPRE (com ou sem erro)
}
```

- **`try`** — o trecho arriscado. Se der erro no meio, o restante do bloco é **pulado**;
- **`catch`** — o plano B para **aquele tipo** de exceção. Pode haver vários, do mais específico para o mais genérico;
- **`finally`** — executa sempre, deu certo ou não. Serve para liberar recursos (fechar arquivo, conexão).

```java
try {
    int[] numeros = {1, 2, 3};
    System.out.println(numeros[5]);
    System.out.println(10 / 0);
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Índice inválido: " + e.getMessage());
} catch (ArithmeticException e) {
    System.out.println("Erro de cálculo: " + e.getMessage());
} catch (Exception e) {                   // rede de segurança: pega o resto
    System.out.println("Erro inesperado: " + e.getMessage());
}
```

> ⚠️ O `catch (Exception e)` **precisa vir por último** — ele pega tudo, e qualquer `catch` depois dele fica inalcançável (o compilador reclama). E nunca deixe um `catch` **vazio**: engolir a exceção sem dizer nada é a pior coisa que se pode fazer com um erro.

Um laço de leitura à prova de bala combina `while` + `try`:

```java
int idade = -1;
while (idade < 0) {
    try {
        System.out.print("Sua idade: ");
        idade = Integer.parseInt(scanner.nextLine());   // lê como texto e converte
    } catch (NumberFormatException e) {
        System.out.println("Valor inválido. Tente de novo.");
    }
}
```

> 💡 Ler com `nextLine()` + `Integer.parseInt()` evita de uma vez a pegadinha do `nextInt()` deixando o Enter no buffer. Adote esse padrão nos seus menus.

## 3. *Checked* × *unchecked*

```
                    Throwable
                        │
          ┌─────────────┴─────────────┐
        Error                    Exception
   (falhas graves da JVM)   ┌─────────┴──────────┐
   StackOverflowError    RuntimeException     as demais
   OutOfMemoryError      (UNCHECKED)          (CHECKED)
                         NullPointer          IOException
                         ArrayIndexOutOfBounds FileNotFoundException
                         NumberFormat
                         Arithmetic
```

| | *Unchecked* (`RuntimeException`) | *Checked* |
|---|---|---|
| O compilador obriga a tratar? | não | **sim** |
| Origem típica | bug de programação | fator externo (arquivo, rede) |
| Exemplos | `NullPointerException`, `ArrayIndexOutOfBoundsException` | `IOException` |
| O que fazer | **corrigir o código** | tratar ou declarar `throws` |

Ignorar uma *checked* nem compila: `error: unreported exception IOException; must be caught or declared to be thrown`. É o que acontece na Aula 13, ao mexer com arquivos.

> 💡 Não trate `NullPointerException` com `try/catch` — ela é um **bug**, não um imprevisto. Conserte a causa.

## 4. `throw`: lançando as suas

Na Aula 06, um saque inválido era resolvido com um `println`. O problema: quem chamou `sacar()` não fica sabendo de nada, e o programa segue como se estivesse tudo bem.

```java
public void sacar(double valor) {
    if (valor <= 0) {
        throw new IllegalArgumentException("Valor do saque deve ser positivo.");
    }
    if (valor > saldo) {
        throw new IllegalStateException("Saldo insuficiente. Disponível: " + saldo);
    }
    this.saldo -= valor;
}
```

Agora a classe **não deixa** o objeto entrar em estado inválido, e quem chama decide o que fazer:

```java
try {
    conta.sacar(500);
    System.out.println("Saque realizado!");
} catch (IllegalArgumentException | IllegalStateException e) {   // dois tipos, um catch
    System.out.println("Não foi possível sacar: " + e.getMessage());
}
```

Essa é a divisão de responsabilidades que a POO busca: **a classe garante suas regras; a interface com o usuário decide como comunicar a falha.** A mesma `ContaBancaria` serve para um app de celular, um site ou um menu de terminal — cada um trata a exceção do seu jeito.

## 5. Exceções personalizadas

Quando o problema é do **seu domínio**, crie uma exceção com o nome dele:

```java
public class SaldoInsuficienteException extends RuntimeException {

    private final double falta;

    public SaldoInsuficienteException(double saldo, double valorSolicitado) {
        super(String.format("Saldo insuficiente: disponível R$ %.2f, solicitado R$ %.2f",
                saldo, valorSolicitado));
        this.falta = valorSolicitado - saldo;
    }

    public double getFalta() {
        return falta;
    }
}
```

```java
public void sacar(double valor) {
    if (valor > saldo) {
        throw new SaldoInsuficienteException(saldo, valor);
    }
    this.saldo -= valor;
}
```

```java
try {
    conta.sacar(500);
} catch (SaldoInsuficienteException e) {
    System.out.println(e.getMessage());
    System.out.printf("Faltam R$ %.2f. Deseja depositar?%n", e.getFalta());
}
```

Ganhos: o nome documenta o erro, o `catch` pode ser específico, e a exceção carrega dados úteis (`getFalta()`).

**Como escolher a superclasse:** estenda `RuntimeException` (unchecked) para violações de regra de negócio — é o padrão que usaremos; estenda `Exception` (checked) quando quiser **obrigar** quem chama a tratar.

## 6. `try-with-resources`

Recursos que precisam ser fechados (arquivos, `Scanner`, conexões) têm sintaxe própria: declarados nos parênteses do `try`, são fechados **automaticamente**, mesmo se der erro.

```java
try (Scanner scanner = new Scanner(System.in)) {
    System.out.print("Nome: ");
    System.out.println("Olá, " + scanner.nextLine());
}   // scanner.close() acontece sozinho aqui
```

Compare com o `finally` que você teria de escrever à mão — e esquecer. Este é o padrão obrigatório da Aula 13, com arquivos.

> 💻 **Código desta aula pronto para rodar:** [`ContaBancaria.java`](exemplos/ContaBancaria.java), [`SaldoInsuficienteException.java`](exemplos/SaldoInsuficienteException.java) + [`Caixa.java`](exemplos/Caixa.java)

## 🏋️ Exercícios da aula

Na pasta `aula-10/` do seu repositório:

1. **`LeituraSegura.java`** — um método `static int lerInteiro(Scanner sc, String mensagem)` que insiste até receber um inteiro válido, tratando `NumberFormatException`. Use-o para ler idade e ano de nascimento;
2. **`ContaBancaria.java` + `Caixa.java`** — troque os `println` de erro por `throw` (`IllegalArgumentException` para valor negativo, `IllegalStateException` para saldo insuficiente) e trate os dois no `main`, mostrando que o programa continua rodando depois da falha;
3. **`SaldoInsuficienteException.java` + `Banco.java`** — crie a exceção personalizada da aula, com a mensagem formatada e o `getFalta()`, e use-a na conta. Faça um menu que tenta três saques (um válido, um negativo, um maior que o saldo) sem quebrar;
4. **`Cadastro.java`** — método `cadastrar(String nome, int idade)` que lança `IllegalArgumentException` se o nome for vazio/nulo ou se a idade estiver fora de 0–120; um `main` que testa 4 casos (um válido, três inválidos) e imprime a mensagem de cada falha;
5. **Desafio 🌶️ `Biblioteca.java`** — sistema de empréstimo com **três** exceções personalizadas: `LivroNaoEncontradoException`, `LivroIndisponivelException` e `LimiteDeEmprestimosException` (máximo 3 por usuário). O menu em `do-while` **nunca** pode quebrar: qualquer entrada, em qualquer opção, resulta em mensagem amigável. Este exercício é um esboço do projeto da Aula 12 — capriche.

## 🧠 Revisão

[8 questões de múltipla escolha](revisao/README.md) para conferir se os conceitos ficaram sólidos. Responda sem consultar a aula — depois volte e corrija.

## ✅ Entrega

```bash
git add aula-10/
git commit -m "Resolve exercícios da aula 10 (exceções)"
git push
```

---

⬅️ [Aula 09](../aula-09-colecoes/README.md) | ➡️ [Aula 11 — Organização do Código](../aula-11-organizacao-pacotes/README.md)
