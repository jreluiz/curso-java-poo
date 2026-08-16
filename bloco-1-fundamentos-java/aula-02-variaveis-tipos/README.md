# Aula 02 — Variáveis e Tipos

> 🎯 Objetivos: declarar variáveis com tipagem estática, conhecer os tipos primitivos e a `String`, converter tipos com segurança e ler dados do teclado.
> 🎬 Slides da aula: [apresentacao-02-variaveis-tipos.pdf](apresentacao/apresentacao-02-variaveis-tipos.pdf)

## 1. Tipagem estática: declarar é assumir compromisso

Uma variável é uma "caixa com etiqueta" — e em Java a etiqueta diz **que tipo de coisa cabe ali**, para sempre:

```java
int idade = 19;              // esta caixa só aceita números inteiros
String nome = "Maria";       // esta só aceita texto
double preco = 19.90;        // esta só aceita números com casas decimais

idade = 20;                  // ✅ ok, continua sendo int
idade = "vinte";             // ❌ error: incompatible types: String cannot be converted to int
```

Isso é **tipagem estática**: o tipo é decidido na declaração e checado pelo compilador **antes** de o programa rodar. Parece burocracia, e é — em troca, uma classe inteira de bugs simplesmente não acontece.

O padrão de nomes é o **camelCase**, começando por minúscula:

```java
double mediaFinal = 8.5;     // ✅ camelCase: padrão do Java
double media_final = 8.5;    // ⚠️ funciona, mas não é o estilo Java
int 2nota = 7;               // ❌ não pode começar com número
int class = 1;               // ❌ palavras reservadas não podem ser nomes
```

Use nomes **descritivos**: `mediaDoAluno` conta uma história; `x` não conta nada.

## 2. Os tipos primitivos

São 8 tipos que **não são objetos** — guardam o valor puro. Estes quatro resolvem 95% do curso:

```java
int quantidade = 42;             // inteiros (-2 bilhões a +2 bilhões)
double preco = 19.90;            // números com casas decimais
boolean aprovado = true;         // só true ou false
char inicial = 'M';              // UM caractere, entre ASPAS SIMPLES
```

Os outros quatro aparecem menos: `long` (inteiros gigantes), `float` (decimal de menor precisão), `short` e `byte` (inteiros pequenos, economia de memória).

```java
long populacaoMundial = 8_000_000_000L;   // L no fim; o _ é só separador visual
```

> ⚠️ **`char` usa aspas simples; `String` usa aspas duplas.** `'M'` é um caractere, `"M"` é um texto de tamanho 1. Trocar as aspas dá erro de compilação.

Valores padrão importam: variáveis **locais** (dentro de um método) **não** têm valor padrão. Usar antes de atribuir é erro de compilação:

```java
int soma;
System.out.println(soma);   // ❌ error: variable soma might not have been initialized
```

## 3. `String` não é primitivo — é objeto

`String` começa com letra maiúscula porque é uma **classe**. Isso significa que texto em Java vem com **métodos** de brinde:

```java
String frase = "Java é divertido";

System.out.println(frase.length());          // 16   (quantidade de caracteres)
System.out.println(frase.toUpperCase());     // JAVA É DIVERTIDO
System.out.println(frase.contains("Java"));  // true
System.out.println(frase.charAt(0));         // J    (começa do zero!)
System.out.println(frase.substring(0, 4));   // Java (do 0 até antes do 4)
System.out.println("  espaços  ".trim());    // "espaços" (remove as bordas)
```

Concatenar texto é com `+`:

```java
String nome = "Maria";
int idade = 19;

System.out.println("Olá, " + nome + "! Você tem " + idade + " anos.");
```

Para saídas mais bem formatadas, use `printf` — `%s` recebe texto, `%d` inteiro, `%.2f` decimal com 2 casas, `%n` quebra a linha:

```java
double media = 8.456;
System.out.printf("Aluno: %s | Média: %.2f%n", nome, media);
// Aluno: Maria | Média: 8,46
```

> 💡 `printf` usa a **configuração regional** do seu computador — em português, o separador decimal é a vírgula. Não é bug.

## 4. Casting e a armadilha da divisão inteira

Java converte **automaticamente** quando não há risco de perder informação (caixa menor → caixa maior):

```java
int inteiro = 10;
double comDecimais = inteiro;      // ✅ 10.0 — int cabe folgado num double
```

No sentido contrário, você precisa **assumir a responsabilidade** com um *cast*:

```java
double valor = 9.87;
int truncado = valor;              // ❌ error: possible lossy conversion from double to int
int certo = (int) valor;           // ✅ 9 — a parte decimal é DESCARTADA, não arredondada
```

E aqui está o erro que mais estraga cálculo de média no mundo:

```java
int soma = 7;
int quantidade = 2;

System.out.println(soma / quantidade);              // 3    ← divisão INTEIRA! 😱
System.out.println((double) soma / quantidade);     // 3.5  ✅
System.out.println(7 / 2.0);                        // 3.5  ✅
System.out.println(7 % 2);                          // 1    (resto da divisão)
```

**Regra:** `int / int` sempre dá `int`. Se você quer casas decimais, pelo menos um dos lados precisa ser `double`.

## 5. `final`: quando o valor não pode mudar

```java
final double PI = 3.14159;
final int IDADE_MINIMA = 18;

PI = 3.15;      // ❌ error: cannot assign a value to final variable PI
```

Constantes são escritas em `MAIUSCULA_COM_UNDERLINE`, por convenção. Use `final` sempre que um valor **não deveria** mudar: o compilador passa a proteger essa decisão.

## 6. Lendo dados do teclado com `Scanner`

Para ler o que o usuário digita, importamos a classe `Scanner`:

```java
import java.util.Scanner;                       // antes da classe!

public class Cadastro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Seu nome: ");
        String nome = scanner.nextLine();       // lê a linha inteira (com espaços)

        System.out.print("Sua idade: ");
        int idade = scanner.nextInt();          // lê um inteiro

        System.out.print("Sua altura: ");
        double altura = scanner.nextDouble();   // lê um decimal

        System.out.printf("%s, %d anos, %.2f m%n", nome, idade, altura);

        scanner.close();                        // libera o recurso ao terminar
    }
}
```

> ⚠️ **A pegadinha clássica do `Scanner`:** `nextInt()` lê o número mas **deixa o Enter** para trás. O `nextLine()` seguinte engole esse Enter e devolve uma string vazia — parece que o programa "pulou" a pergunta. Solução: um `scanner.nextLine()` extra logo depois do `nextInt()`.

> 💡 `nextDouble()` espera o separador do seu sistema: em português, digite `1,75` e não `1.75`.

> 💻 **Código desta aula pronto para rodar:** [`TiposEVariaveis.java`](exemplos/TiposEVariaveis.java) e [`LeituraTeclado.java`](exemplos/LeituraTeclado.java)

## 🏋️ Exercícios da aula

Na pasta `aula-02/` do seu repositório:

1. **`Apresentacao.java`** — declare variáveis com seu nome (`String`), idade (`int`), altura (`double`) e se estuda à noite (`boolean`); imprima uma apresentação em uma frase usando `printf`, com a altura em 2 casas decimais;
2. **`Conversor.java`** — dada `final double COTACAO = 5.42;` e um valor em dólares, imprima o valor em reais formatado. Depois converta uma temperatura de Celsius para Fahrenheit (`F = C * 9/5 + 32`) — **cuidado**: por que `9/5` dá `1`? Conserte;
3. **`Previsao.java`** — **antes de rodar**, escreva num comentário o que cada linha imprime; depois execute e confira:
   ```java
   System.out.println(7 / 2);
   System.out.println(7 / 2.0);
   System.out.println(7 % 2);
   System.out.println((int) 7.9);
   System.out.println(10 / 4 * 4);
   ```
4. **`Calculadora.java`** — leia dois números `double` do teclado com `Scanner` e imprima soma, subtração, multiplicação, divisão e resto (use `%.2f`);
5. **Desafio 🌶️ `Imc.java`** — leia nome, peso e altura do teclado; calcule o IMC (`peso / (altura * altura)`) e imprima assim: `Maria, seu IMC é 21,45`. Use `nextLine()` depois do último número lido e explique **num comentário** por que ele foi necessário.

### 📤 Entrega

Estes exercícios são feitos em sala e vão para o **seu repositório** `exercicios-java-poo`:

```bash
cd ..                 # da pasta da aula para a raiz do repositório
git add aula-02/
git commit -m "Resolve exercícios da aula 02"
git push
```

Confira no navegador que a pasta apareceu em `github.com/SEU-USUARIO/exercicios-java-poo`.

## 🧠 Revisão

[8 questões de múltipla escolha](revisao/README.md) para conferir se os conceitos ficaram sólidos. Responda sem consultar a aula — depois volte e corrija.

---

⬅️ [Aula 01](../aula-01-introducao-java/README.md) | ➡️ [Aula 03 — Operadores e Condicionais](../aula-03-operadores-condicionais/README.md)
