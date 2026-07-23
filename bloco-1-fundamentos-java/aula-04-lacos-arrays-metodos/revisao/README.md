# Aula 04 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 04 — Laços, Arrays e Métodos](../README.md). Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

---

### Q-A04-01

Quantas vezes o corpo deste laço executa?

```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

- **a)** 5
- **b)** 4
- **c)** 6
- **d)** Infinitas, porque `i` é declarado dentro do `for`.

↩︎ *Aula 04, seção 2 — `for`: quando você sabe quantas vezes*

---

### Q-A04-02

Dado `double[] notas = new double[4];`, quais são os índices válidos?

- **a)** De 1 a 4;
- **b)** de 1 a 3;
- **c)** de 0 a 4;
- **d)** de 0 a 3.

↩︎ *Aula 04, seção 3 — Arrays: muitos valores, um nome*

---

### Q-A04-03

Qual é a forma correta de obter a quantidade de posições de um array chamado `notas`?

- **a)** `notas.size()`
- **b)** `notas.length()`
- **c)** `notas.length`
- **d)** `length(notas)`

↩︎ *Aula 04, seção 3 — Arrays: muitos valores, um nome*

---

### Q-A04-04

O que este trecho imprime?

```java
for (int i = 1; i <= 6; i++) {
    if (i % 2 != 0) continue;
    if (i > 4) break;
    System.out.print(i + " ");
}
```

- **a)** `1 3 5`
- **b)** `2 4`
- **c)** `2 4 6`
- **d)** `1 2 3 4`

↩︎ *Aula 04, seção 2 — `for`: quando você sabe quantas vezes*

---

### Q-A04-05

Um método declarado como `public static double calcularMedia(double[] v)` tem um `return` apenas dentro de um `if`. O que acontece?

- **a)** Compila: o Java devolve `0.0` quando o `if` não é satisfeito;
- **b)** compila, mas devolve `null` nesse caso;
- **c)** compila e lança `NullPointerException` em execução;
- **d)** não compila: `missing return statement`.

↩︎ *Aula 04, seção 4 — Métodos: dando nome a um pedaço de lógica*

---

### Q-A04-06

O que caracteriza a **sobrecarga** de métodos?

- **a)** Métodos com o **mesmo nome** e listas de parâmetros diferentes na mesma classe;
- **b)** métodos com nomes diferentes que fazem a mesma coisa;
- **c)** um método que chama a si mesmo repetidamente;
- **d)** um método `static` que é chamado sem objeto.

↩︎ *Aula 04, seção 4 — Métodos: dando nome a um pedaço de lógica*

---

### Q-A04-07

O que acontece ao tentar usar, **depois** do laço, uma variável declarada dentro dele?

```java
for (int i = 0; i < 3; i++) {
    int dobro = i * 2;
}
System.out.println(dobro);
```

- **a)** Imprime `4`, o último valor calculado;
- **b)** não compila: `cannot find symbol` — a variável só existe dentro das chaves onde foi declarada;
- **c)** imprime `0`, porque a variável é reinicializada;
- **d)** compila, mas lança `ArrayIndexOutOfBoundsException`.

↩︎ *Aula 04, seção 5 — Escopo: onde cada variável existe*

---

### Q-A04-08

Um `while (contador <= 5)` cujo corpo nunca altera `contador` produz:

- **a)** Erro de compilação, detectado pelo `javac`;
- **b)** exatamente uma repetição;
- **c)** um **laço infinito**: o programa trava repetindo para sempre;
- **d)** nenhuma repetição, porque o compilador remove laços sem efeito.

↩︎ *Aula 04, seção 1 — `while` e `do-while`*

---

⬅️ [Voltar à Aula 04](../README.md) | ➡️ [Revisão da Aula 05](../../../bloco-2-pilares-poo/aula-05-classes-objetos/revisao/README.md)
