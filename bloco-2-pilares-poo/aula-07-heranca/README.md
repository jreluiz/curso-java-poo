# Aula 07 — Herança

> 🎯 Objetivos: reaproveitar código com `extends`, entender `super` e a ordem dos construtores, sobrescrever métodos com `@Override` e saber **quando não herdar**.

## 1. O problema: copiar e colar entre classes

Uma escola tem alunos e professores. Você escreve as duas classes:

```java
public class Aluno {
    private String nome;      // ┐
    private String cpf;       // │ igual
    private int idade;        // ┘
    private String matricula;
    // getters, setters, toString... tudo duplicado
}

public class Professor {
    private String nome;      // ┐
    private String cpf;       // │ igual de novo
    private int idade;        // ┘
    private double salario;
    // ...os mesmos getters, setters, toString...
}
```

Agora o CPF passa a exigir validação. Você tem que alterar **os dois** arquivos — e no dia em que esquecer um, nasce um bug. Amanhã entra `Funcionario`, e são três.

A causa é que existe um conceito escondido: **pessoa**. Aluno *é uma* pessoa; professor *é uma* pessoa. Herança é a ferramenta para dizer isso em código.

## 2. `extends` e `super`

```java
public class Pessoa {
    protected String nome;      // protected: visível para as SUBCLASSES
    protected String cpf;
    protected int idade;

    public Pessoa(String nome, String cpf, int idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public boolean ehMaiorDeIdade() {
        return idade >= 18;
    }

    @Override
    public String toString() {
        return nome + " (" + cpf + ")";
    }
}
```

```java
public class Aluno extends Pessoa {      // "Aluno é uma Pessoa"
    private String matricula;
    private double[] notas = new double[3];

    public Aluno(String nome, String cpf, int idade, String matricula) {
        super(nome, cpf, idade);         // chama o construtor da superclasse — 1ª linha!
        this.matricula = matricula;
    }

    public double calcularMedia() {
        double soma = 0;
        for (double n : notas) soma += n;
        return soma / notas.length;
    }
}
```

```java
public class Professor extends Pessoa {
    private double salario;

    public Professor(String nome, String cpf, int idade, double salario) {
        super(nome, cpf, idade);
        this.salario = salario;
    }

    public double calcularSalarioAnual() {
        return salario * 13;             // com 13º
    }
}
```

O que a subclasse ganha:

```java
Aluno ana = new Aluno("Ana", "111", 19, "1001");

System.out.println(ana.getNome());          // Ana        ← método herdado de Pessoa
System.out.println(ana.ehMaiorDeIdade());   // true       ← herdado
System.out.println(ana.calcularMedia());    // 0.0        ← próprio de Aluno
```

Vocabulário: `Pessoa` é a **superclasse** (ou classe-mãe, ou classe-base); `Aluno` e `Professor` são **subclasses** (ou classes-filhas, ou derivadas).

### `protected` e o novo nível de acesso

| Modificador | Quem enxerga |
|-------------|--------------|
| `private` | só a própria classe |
| `protected` | a própria classe **e as subclasses** |
| `public` | todo mundo |

`protected` é o meio-termo que a herança pede: a subclasse acessa o atributo direto, o resto do mundo não.

### A ordem dos construtores

```java
public class Pessoa {
    public Pessoa(String nome) {
        System.out.println("1 - Construtor de Pessoa");
    }
}

public class Aluno extends Pessoa {
    public Aluno(String nome) {
        super(nome);
        System.out.println("2 - Construtor de Aluno");
    }
}

new Aluno("Ana");
// 1 - Construtor de Pessoa
// 2 - Construtor de Aluno
```

**Primeiro a mãe, depois a filha** — sempre. Faz sentido: a parte `Pessoa` do objeto precisa existir antes de a parte `Aluno` ser construída em cima dela.

> ⚠️ `super(...)` tem de ser a **primeira linha** do construtor. Se você não escrever, o compilador insere um `super()` sem argumentos — e se a superclasse não tiver construtor vazio, o erro é `constructor Pessoa in class Pessoa cannot be applied to given types`. Quando vir isso, a cura é chamar `super(...)` com os argumentos certos.

## 3. Todo mundo herda de `Object`

Você já usou herança sem saber: em Java, **toda** classe que não estende ninguém estende automaticamente `Object`. De lá vêm métodos que você já viu:

- `toString()` — a apresentação em texto;
- `equals(Object o)` — comparação de conteúdo;
- `hashCode()` — o "código de arquivamento" usado por coleções.

As versões originais são propositalmente burras (`toString` devolve `Produto@6d06d69c`; `equals` compara referências). Cabe a você sobrescrevê-las.

## 4. Sobrescrita: mudando o comportamento herdado

```java
public class Aluno extends Pessoa {
    // ...

    @Override
    public String toString() {
        // super.toString() chama a versão da superclasse — reaproveita em vez de repetir
        return super.toString() + " - matrícula " + matricula
                + " - média " + String.format("%.2f", calcularMedia());
    }
}
```

```java
Pessoa p = new Pessoa("Carlos", "999", 40);
Aluno a = new Aluno("Ana", "111", 19, "1001");

System.out.println(p);    // Carlos (999)
System.out.println(a);    // Ana (111) - matrícula 1001 - média 8,00
```

Mesma chamada (`println`), comportamentos diferentes conforme a classe do objeto. Isso já é **polimorfismo** — a Aula 08 explora o assunto a fundo.

> ⚠️ **Use sempre `@Override`.** Escrever `toString(String s)` ou `equals(Aluno o)` cria uma **sobrecarga** nova, que ninguém chama, e o comportamento antigo continua valendo — um bug silencioso. Com `@Override`, o compilador denuncia na hora.

## 5. `equals()` e `hashCode()`

Dois objetos com os mesmos dados são iguais? Por padrão, **não**:

```java
Aluno a1 = new Aluno("Ana", "111", 19, "1001");
Aluno a2 = new Aluno("Ana", "111", 19, "1001");

System.out.println(a1 == a2);        // false — objetos distintos na memória
System.out.println(a1.equals(a2));   // false — equals herdado só compara referências
```

Você decide o critério de igualdade da sua classe. Para `Aluno`, dois objetos são o mesmo aluno se têm a **mesma matrícula**:

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;                        // é o mesmo objeto
    if (!(obj instanceof Aluno)) return false;           // não é nem Aluno
    Aluno outro = (Aluno) obj;                           // agora pode converter
    return this.matricula.equals(outro.matricula);       // o critério
}

@Override
public int hashCode() {
    return Objects.hash(matricula);      // import java.util.Objects;
}
```

```java
System.out.println(a1.equals(a2));   // true ✅
```

> ⚠️ **Sobrescreveu `equals`? Sobrescreva `hashCode` junto** — sempre com os mesmos atributos. Coleções como `HashMap` e `HashSet` usam o `hashCode` para achar o objeto e só depois o `equals` para confirmar; com um sem o outro, o objeto some dentro da coleção. Na dúvida: `Alt + Insert` → *equals() and hashCode()*.

Sem `equals`, `lista.contains(a2)` devolve `false` mesmo com o aluno lá dentro. Isso volta com força na Aula 09.

## 6. Quando **não** herdar: prefira composição

Herança é a relação mais forte que existe entre duas classes — e por isso a mais fácil de usar errado. O teste é uma frase:

> **"Todo X é um Y"** — se soa estranho, não é herança.

```java
// ❌ Todo Cliente é um Endereço?  Não!
public class Cliente extends Endereco { }

// ✅ Todo Cliente TEM um Endereço → composição
public class Cliente {
    private String nome;
    private Endereco endereco;      // um objeto dentro de outro

    public Cliente(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getCidade() {
        return endereco.getCidade();
    }
}
```

Mais exemplos: `Carro` **tem** um `Motor` (não é um motor); `Pedido` **tem** vários `Item`; `Aluno` **é** uma `Pessoa`. Na dúvida entre as duas, **componha** — é mais flexível e não amarra sua classe à evolução da outra.

> 💡 Herança boa costuma ter 2 ou 3 níveis, no máximo. Hierarquias fundas viram labirinto: para entender uma classe, você precisa abrir cinco arquivos.

> 💻 **Código desta aula pronto para rodar:** [`Pessoa.java`](exemplos/Pessoa.java), [`Aluno.java`](exemplos/Aluno.java), [`Professor.java`](exemplos/Professor.java) + [`Escola.java`](exemplos/Escola.java)

## 🏋️ Exercícios da aula

Na pasta `aula-07/` do seu repositório:

1. **`Pessoa.java`, `Aluno.java`, `Professor.java`, `Escola.java`** — implemente a hierarquia da aula completa, com `toString()` sobrescrito nas três classes (usando `super.toString()` nas filhas) e um `main` que cria 2 alunos e 1 professor e imprime todos;
2. **`Ordem.java`** — coloque um `System.out.println` no construtor de cada classe da hierarquia (avó, mãe, filha, três níveis) e crie um objeto da filha. Anote **num comentário** a ordem impressa e explique por quê;
3. **`Aluno.java` (equals)** — sobrescreva `equals` e `hashCode` por matrícula; no `main`, crie dois alunos com a mesma matrícula e mostre o resultado de `==`, `.equals()` e de um `Aluno[]` percorrido com `.equals()` para encontrar o aluno. Depois **comente** o `@Override` do `equals` e observe o que muda;
4. **`Veiculo.java`, `Carro.java`, `Moto.java`** — `Veiculo` tem `marca`, `modelo`, `ano` e `calcularIpva()` (4% do valor); `Carro` acrescenta `portas`; `Moto` sobrescreve `calcularIpva()` para 2%. Imprima o IPVA dos dois;
5. **Desafio 🌶️ `Funcionario.java` + subclasses** — `Funcionario` (abstrata na prática: nome, matrícula, `salarioBase`) com `Gerente` (bônus de 20% + auxílio fixo), `Vendedor` (salário base + comissão sobre as vendas do mês) e `Estagiario` (bolsa fixa, sem 13º). Cada subclasse sobrescreve `calcularSalario()`. No `main`, crie um `Funcionario[]` com os três e imprima a folha de pagamento total — repare que o mesmo laço trata os três tipos: é o gancho da Aula 08.

## 🧠 Revisão

[8 questões de múltipla escolha](revisao/README.md) para conferir se os conceitos ficaram sólidos. Responda sem consultar a aula — depois volte e corrija.

## ✅ Entrega

```bash
git add aula-07/
git commit -m "Resolve exercícios da aula 07 (herança)"
git push
```

---

⬅️ [Aula 06](../aula-06-encapsulamento/README.md) | ➡️ [Aula 08 — Polimorfismo e Abstração](../aula-08-polimorfismo-abstracao/README.md)
