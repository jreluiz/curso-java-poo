# Revisão geral — Aulas 01 a 06

### R-01

Você compilou `Ola.java` com `javac` e rodou com `java Ola`. Agora precisa levar o programa para uma máquina com outro sistema operacional. O que basta levar?

- **a)** o `Ola.class`, desde que naquela máquina exista uma JVM instalada;
- **b)** o `Ola.java`, porque o `.class` só funciona na máquina onde foi gerado;
- **c)** o `Ola.class`, depois de recompilá-lo para aquele sistema operacional;
- **d)** o JDK inteiro, porque o `.class` depende do compilador para executar.

↩︎ *Aula 01, seção 2 — JDK, JVM e o ciclo compilar → executar*

---

### R-02

O que este trecho imprime?

```java
int a = 9, b = 2;
System.out.println("Media: " + a / b);
```

- **a)** `Media: 4.5`
- **b)** `Media: 4.0`
- **c)** `Media: 4`
- **d)** `Media: 92`

↩︎ *Aula 02, seções 3 e 4 — `String` não é primitivo — é objeto; Casting e a armadilha da divisão inteira*

---

### R-03

O que este trecho imprime?

```java
String a = new String("ok");

if (a == "ok") {
    System.out.println("igual");
} else if (a.equals("ok")) {
    System.out.println("equivalente");
} else {
    System.out.println("diferente");
}
```

- **a)** `igual`
- **b)** `equivalente`
- **c)** `diferente`
- **d)** `igual` e `equivalente`, em duas linhas

↩︎ *Aula 03, seções 3 e 5 — `if`, `else if`, `else`; `==` vs `.equals()`: a armadilha das Strings*

---

### R-04

O que este trecho imprime?

```java
int[] v = {3, 0, 7};
int cont = 0;

for (int i = 0; i < v.length; i++) {
    if (v[i] > 0) {
        cont++;
    }
}

System.out.println(cont);
```

- **a)** `3`
- **b)** `10`
- **c)** `0`
- **d)** `2`

↩︎ *Aula 04, seções 2 e 3 — `for`: quando você sabe quantas vezes; Arrays: muitos valores, um nome*

---

### R-05

Considere o método abaixo, na mesma classe do `main`:

```java
static int soma(int a, int b) {
    return a + b;
}
```

O que `System.out.println(soma(2, 3) * 2);` imprime?

- **a)** `10`
- **b)** `7`
- **c)** `12`
- **d)** `23`

↩︎ *Aula 04, seção 4 — Métodos: dando nome a um pedaço de lógica*

---

### R-06

O que este trecho imprime?

```java
class Ponto {
    int x;
    Ponto(int x) {
        this.x = x;
    }
}

// no main:
Ponto p = new Ponto(5);
Ponto q = p;
q.x = 9;
System.out.println(p.x);
```

- **a)** `5`
- **b)** `9`
- **c)** `0`
- **d)** `14`

↩︎ *Aula 05, seção 4 — `new`, referência e `null`*

---

### R-07

A classe `Livro` tem o atributo `titulo` e o construtor `Livro(String t)`. O que acontece ao executar este trecho?

```java
Livro[] estante = new Livro[3];
estante[0] = new Livro("Java");
System.out.println(estante[2].titulo);
```

- **a)** imprime `null`, porque a posição 2 do array está vazia;
- **b)** imprime uma linha em branco;
- **c)** lança `NullPointerException`, porque a posição 2 nunca recebeu um objeto;
- **d)** não compila, porque só uma das três posições do array foi preenchida.

↩︎ *Aula 05, seções 4 e 5 — `new`, referência e `null`; Construtores: nascer já pronto*

---

### R-08

O que este trecho imprime?

```java
class Conta {
    private double saldo = 100;

    public void sacar(double v) {
        if (v > saldo) {
            return;
        }
        saldo -= v;
    }

    public double getSaldo() {
        return saldo;
    }
}

// no main:
Conta c = new Conta();
c.sacar(150);
System.out.println(c.getSaldo());
```

- **a)** `-50.0`
- **b)** `0.0`
- **c)** `150.0`
- **d)** `100.0`

↩︎ *Aula 06, seções 2 e 3 — `private` e os getters/setters; O setter que defende a classe*

---

### R-09

O que este trecho imprime?

```java
class Item {
    static int criados = 0;
    String nome;

    Item(String n) {
        nome = n;
        criados++;
    }

    @Override
    public String toString() {
        return nome + "/" + criados;
    }
}

// no main:
Item a = new Item("A");
Item b = new Item("B");
System.out.println(a);
```

- **a)** `A/2`
- **b)** `A/1`
- **c)** `Item@1b6d3586`
- **d)** `A/0`

↩︎ *Aula 06, seções 5 e 6 — `static`: o que pertence à classe, não ao objeto; `toString()`: como o objeto se apresenta*

---

### R-10

A classe `Pedido` tem `private int quantidade;` e oferece `getQuantidade()` e `setQuantidade(int)`, ambos públicos e sem nenhuma verificação. Segundo a aula, para que o encapsulamento sirva para alguma coisa nessa classe, falta:

- **a)** tornar o atributo `public`, já que qualquer código pode alterá-lo de qualquer jeito;
- **b)** decidir quem, de fora, tem o direito de mudar a quantidade — e, se ninguém tiver, não oferecer o setter;
- **c)** renomear os dois métodos para `lerQuantidade` e `escreverQuantidade`;
- **d)** declarar os dois métodos como `static`, para que pertençam à classe.

↩︎ *Aula 06, seção 2 — `private` e os getters/setters*

---

⬅️ [Voltar ao plano de aulas](../README.md)
