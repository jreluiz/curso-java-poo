# Aula 02 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 02 — Variáveis e Tipos](../README.md). Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

---

### Q-A02-01

O que acontece ao compilar este trecho?

```java
int idade = 19;
idade = "vinte";
```

- **a)** Compila e executa: a variável passa a guardar texto;
- **b)** não compila: `incompatible types: String cannot be converted to int`;
- **c)** compila, mas falha em execução com `NumberFormatException`;
- **d)** compila e guarda o valor `0`, por não conseguir converter.

↩︎ *Aula 02, seção 1 — Tipagem estática: declarar é assumir compromisso*

---

### Q-A02-02

O que há de errado em `char inicial = "M";`?

- **a)** Nada: é a forma correta de declarar um caractere;
- **b)** o nome da variável deveria começar com maiúscula;
- **c)** não compila: `char` usa aspas simples (`'M'`); aspas duplas formam uma `String`;
- **d)** compila, mas guarda apenas o código numérico da letra.

↩︎ *Aula 02, seção 2 — Os tipos primitivos*

---

### Q-A02-03

O que acontece com este trecho dentro de um método?

```java
int soma;
System.out.println(soma);
```

- **a)** Imprime `0`, o valor padrão de um `int`;
- **b)** imprime `null`;
- **c)** compila e falha em execução com `NullPointerException`;
- **d)** não compila: `variable soma might not have been initialized`.

↩︎ *Aula 02, seção 2 — Os tipos primitivos*

---

### Q-A02-04

Qual é a saída de `System.out.println(7 / 2);`?

- **a)** `3`
- **b)** `3.5`
- **c)** `4`
- **d)** `3,5`

↩︎ *Aula 02, seção 4 — Casting e a armadilha da divisão inteira*

---

### Q-A02-05

Qual é o valor de `int x = (int) 7.9;`?

- **a)** `8`, porque o casting arredonda para o inteiro mais próximo;
- **b)** `0`, porque a conversão falha;
- **c)** `7`, porque o casting descarta a parte decimal;
- **d)** não compila: `double` não pode virar `int` de jeito nenhum.

↩︎ *Aula 02, seção 4 — Casting e a armadilha da divisão inteira*

---

### Q-A02-06

O que acontece neste trecho?

```java
double valor = 9.87;
int arredondado = valor;
```

- **a)** Compila e `arredondado` fica com `10`;
- **b)** não compila: `incompatible types: possible lossy conversion from double to int`;
- **c)** compila e `arredondado` fica com `9`, silenciosamente;
- **d)** compila apenas se `valor` for declarado como `final`.

↩︎ *Aula 02, seção 4 — Casting e a armadilha da divisão inteira*

---

### Q-A02-07

Dado `String frase = "Java é divertido";`, o que `frase.substring(0, 4)` devolve?

- **a)** `Java`
- **b)** `Jav`
- **c)** `ava`
- **d)** `Java ` (com o espaço no fim)

↩︎ *Aula 02, seção 3 — `String` não é primitivo — é objeto*

---

### Q-A02-08

Um programa lê `int idade = scanner.nextInt();` e, logo em seguida, `String cidade = scanner.nextLine();`. O usuário digita a idade, tecla Enter — e o programa nem espera a cidade. Por quê?

- **a)** Porque `nextLine()` só funciona antes de qualquer `nextInt()`;
- **b)** porque o `Scanner` precisa ser recriado a cada leitura;
- **c)** porque `nextInt()` fecha a entrada padrão depois de ler;
- **d)** porque `nextInt()` lê o número mas deixa o Enter no buffer, e o `nextLine()` consome esse Enter, devolvendo uma string vazia.

↩︎ *Aula 02, seção 6 — Lendo dados do teclado com `Scanner`*

---

⬅️ [Voltar à Aula 02](../README.md) | ➡️ [Revisão da Aula 03](../../aula-03-operadores-condicionais/revisao/README.md)
