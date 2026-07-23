# Aula 03 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 03 — Operadores e Condicionais](../README.md). Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

---

### Q-A03-01

Qual é a saída de `System.out.println(2 + 3 * 4);`?

- **a)** `20`
- **b)** `24`
- **c)** `9`
- **d)** `14`

↩︎ *Aula 03, seção 1 — Operadores aritméticos e precedência*

---

### Q-A03-02

O que acontece ao compilar `if (nota = 10) { ... }`?

- **a)** Não compila: `=` é atribuição, e o `if` exige uma expressão `boolean`;
- **b)** compila e a condição é sempre verdadeira;
- **c)** compila e a condição é verdadeira apenas quando `nota` já valia 10;
- **d)** compila, mas lança `ArithmeticException` em execução.

↩︎ *Aula 03, seção 2 — Operadores relacionais e lógicos*

---

### Q-A03-03

Num `switch` clássico, o que acontece quando um `case` não termina com `break`?

- **a)** O `switch` inteiro é ignorado;
- **b)** o compilador acusa erro e não gera o `.class`;
- **c)** a execução **continua nos casos seguintes** até encontrar um `break` ou o fim do bloco;
- **d)** apenas o `default` passa a ser executado.

↩︎ *Aula 03, seção 4 — `switch`: quando é o mesmo valor comparado a várias opções*

---

### Q-A03-04

O que este trecho imprime?

```java
String a = "Java";
String b = new String("Java");
System.out.println(a == b);
System.out.println(a.equals(b));
```

- **a)** `true` e `true`
- **b)** `false` e `true`
- **c)** `true` e `false`
- **d)** `false` e `false`

↩︎ *Aula 03, seção 5 — `==` vs `.equals()`: a armadilha das Strings*

---

### Q-A03-05

Um programa lê a senha com `String senha = scanner.nextLine();` e testa `if (senha == "1234")`. Digitando exatamente `1234`, o resultado é:

- **a)** `true`, porque o conteúdo é idêntico;
- **b)** `false`, porque `==` compara **referências**, e o texto lido é um objeto diferente do literal;
- **c)** erro de compilação, porque `String` não aceita `==`;
- **d)** `true` apenas se a senha for declarada como `final`.

↩︎ *Aula 03, seção 5 — `==` vs `.equals()`: a armadilha das Strings*

---

### Q-A03-06

Qual é o valor de `situacao` neste trecho?

```java
int nota = 7;
String situacao = (nota >= 7) ? "Aprovado" : "Reprovado";
```

- **a)** `"Reprovado"`, porque o ternário avalia primeiro o segundo valor;
- **b)** `null`, porque falta um `if`;
- **c)** não compila: ternário não pode devolver `String`;
- **d)** `"Aprovado"`.

↩︎ *Aula 03, seção 6 — Operador ternário*

---

### Q-A03-07

Com `idade = 20` e `temDocumento = false`, qual expressão resulta em `true`?

- **a)** `idade >= 18 || temDocumento`
- **b)** `idade >= 18 && temDocumento`
- **c)** `!(idade >= 18)`
- **d)** `idade < 18 || temDocumento`

↩︎ *Aula 03, seção 2 — Operadores relacionais e lógicos*

---

### Q-A03-08

O que este trecho imprime quando `aprovado` vale `false`?

```java
if (aprovado)
    System.out.println("Parabéns!");
    System.out.println("Você passou!");
```

- **a)** Nada;
- **b)** `Parabéns!` e `Você passou!`;
- **c)** apenas `Você passou!`, porque sem chaves só a **primeira** linha pertence ao `if`;
- **d)** não compila: `if` sem chaves é proibido em Java.

↩︎ *Aula 03, seção 3 — `if`, `else if`, `else`*

---

⬅️ [Voltar à Aula 03](../README.md) | ➡️ [Revisão da Aula 04](../../aula-04-lacos-arrays-metodos/revisao/README.md)
