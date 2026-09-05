# Aula 08 — Polimorfismo e Abstração

> 🎯 Objetivos: tratar objetos diferentes pelo mesmo tipo, entender a ligação dinâmica, e escolher entre **classe abstrata** e **interface** para definir contratos.
> 🎬 Slides da aula: [apresentacao-08-polimorfismo-abstracao.pdf](apresentacao/apresentacao-08-polimorfismo-abstracao.pdf)

Este é o último pilar — e o que dá à POO o seu superpoder: escrever código que funciona com objetos que **ainda não existem**.

## 1. Um comando, muitos comportamentos

No desafio da Aula 07, você escreveu isto:

```java
Funcionario[] folha = { new Gerente(...), new Vendedor(...), new Estagiario(...) };

for (Funcionario f : folha) {
    System.out.println(f.getNome() + ": " + f.calcularSalario());
}
```

Repare no que aconteceu: **um único laço**, uma única chamada `calcularSalario()`, e três cálculos completamente diferentes executados. Nenhum `if` perguntando "que tipo é esse?".

**Polimorfismo** (do grego, "muitas formas") é exatamente isso: a mesma chamada produz o comportamento certo para cada objeto. Compare com a alternativa sem POO:

```java
// 😖 Sem polimorfismo: um if para cada tipo, em TODO lugar que usa funcionário
if (tipo.equals("gerente")) {
    salario = base * 1.2 + AUXILIO;
} else if (tipo.equals("vendedor")) {
    salario = base + vendas * 0.05;
} else if (tipo.equals("estagiario")) {
    salario = bolsa;
}
```

Nesta versão, adicionar um cargo novo significa caçar todos os `if` espalhados pelo sistema. Na versão polimórfica, significa **criar uma classe nova** — e nada do código existente muda.

## 2. Upcasting e ligação dinâmica

A chave é que uma variável pode ter um tipo **mais genérico** que o objeto:

```java
Funcionario f = new Gerente("Ana", "1001", 5000);
//    ↑ tipo da variável        ↑ tipo do objeto (o que importa na hora de executar)
```

Isso se chama **upcasting**: guardar uma subclasse numa variável da superclasse. É automático e sempre seguro (todo gerente *é* funcionário).

```java
f.calcularSalario();     // executa a versão do GERENTE
f.receberBonus();        // ❌ error: cannot find symbol — o TIPO da variável é Funcionario
```

Duas regras que explicam tudo:

| Quem decide | O quê |
|---|---|
| O **tipo da variável** (compilação) | quais métodos você **pode chamar** |
| O **tipo do objeto** (execução) | qual **versão** do método roda |

A segunda regra tem nome: **ligação dinâmica** (*late binding*). O Java só descobre qual `calcularSalario()` chamar quando o programa está rodando e olha o objeto de verdade.

## 3. Classe abstrata: um molde que não vira objeto

Existe um problema no desenho atual: `new Funcionario("Ana", "1", 3000)` é possível — e o que essa pessoa faz na empresa? Que salário ela tem? A superclasse existe só para ser herdada; ela é um **conceito**, não algo concreto.

```java
public abstract class Funcionario {
    protected String nome;
    protected double salarioBase;

    public Funcionario(String nome, double salarioBase) {   // construtor existe (para o super)
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    // MÉTODO ABSTRATO: sem corpo. "Toda subclasse é OBRIGADA a implementar."
    public abstract double calcularSalario();

    // Métodos concretos convivem normalmente com os abstratos
    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return String.format("%s: R$ %.2f", nome, calcularSalario());
    }
}
```

```java
new Funcionario("Ana", 3000);      // ❌ error: Funcionario is abstract; cannot be instantiated
Funcionario f = new Gerente(...);  // ✅ a variável pode ser do tipo abstrato
```

Duas garantias valiosas de graça:

1. Ninguém cria um funcionário "genérico" sem cargo;
2. Se alguém criar `Diretor extends Funcionario` e esquecer o `calcularSalario()`, **não compila**. O contrato é cobrado pelo compilador.

Repare no `toString()` da superclasse chamando `calcularSalario()` — um método que ela nem sabe implementar. Funciona porque, na execução, quem responde é o objeto real. Isso é abstração: programar contra a **ideia** de funcionário.

## 4. Interface: contrato puro

Herança resolve "é um". Mas e capacidades que atravessam hierarquias diferentes? Um `Livro` pode ser emprestado; uma `SalaDeAula` também — e uma não tem nada a ver com a outra.

```java
public interface Emprestavel {
    void emprestar(String responsavel);      // sem corpo, public e abstract implícitos
    void devolver();
    boolean estaDisponivel();
}
```

```java
public class Livro implements Emprestavel {
    private String titulo;
    private boolean disponivel = true;
    private String comQuem;

    public Livro(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public void emprestar(String responsavel) {
        if (!disponivel) {
            System.out.println(titulo + " já está emprestado para " + comQuem);
            return;
        }
        this.disponivel = false;
        this.comQuem = responsavel;
    }

    @Override
    public void devolver() {
        this.disponivel = true;
        this.comQuem = null;
    }

    @Override
    public boolean estaDisponivel() {
        return disponivel;
    }
}
```

Agora qualquer classe — `Livro`, `Projetor`, `SalaDeAula` — pode assinar o mesmo contrato, e o código que empresta funciona com todas:

```java
Emprestavel[] itens = { new Livro("Dom Casmurro"), new Projetor("Epson X1") };

for (Emprestavel item : itens) {
    if (item.estaDisponivel()) {
        item.emprestar("Ana");
    }
}
```

Uma classe **estende uma** superclasse, mas pode **implementar várias** interfaces:

```java
public class Livro extends Publicacao implements Emprestavel, Comparable<Livro> {
```

## 5. Interface ou classe abstrata?

| | Classe abstrata | Interface |
|---|---|---|
| Relação | **"é um"** (`Gerente` é `Funcionario`) | **"é capaz de"** (`Livro` é emprestável) |
| Atributos | sim, com estado (`protected double salarioBase`) | só constantes (`public static final`) |
| Métodos concretos | sim | só `default`/`static` (uso avançado) |
| Construtor | sim | não |
| Quantas por classe | **uma** | **várias** |

Na prática: **classe abstrata** quando as subclasses compartilham dados e boa parte do comportamento; **interface** quando você quer só definir uma capacidade, sem impor uma hierarquia. E as duas juntas são comuns — é o desenho do projeto da Aula 12.

## 6. `instanceof` e o downcasting

Às vezes é preciso voltar ao tipo específico para usar algo que só ele tem:

```java
for (Funcionario f : folha) {
    if (f instanceof Vendedor v) {          // testa E já converte (Java 16+)
        System.out.println("Vendas: " + v.getTotalVendas());
    }
}
```

A forma antiga, ainda muito comum em códigos por aí:

```java
if (f instanceof Vendedor) {
    Vendedor v = (Vendedor) f;              // downcasting explícito
    System.out.println(v.getTotalVendas());
}
```

Sem o `instanceof`, um cast errado explode em execução: `ClassCastException`.

> ⚠️ **`instanceof` em excesso é cheiro de polimorfismo mal feito.** Se você escreveu uma cadeia de `if (x instanceof A) ... else if (x instanceof B)`, quase sempre o certo era um método polimórfico na superclasse. Use `instanceof` como exceção, não como estratégia.

> 💻 **Código desta aula pronto para rodar:** a hierarquia `Funcionario` e a interface `Emprestavel` em [`Demo.java`](exemplos/Demo.java)

## 🏋️ Exercícios da aula

Na pasta `aula-08/` do seu repositório:

1. **`Forma.java`, `Circulo.java`, `Retangulo.java`, `Triangulo.java`, `Geometria.java`** — `Forma` é **abstrata** com `calcularArea()` e `calcularPerimetro()` abstratos e um `toString()` concreto que usa os dois. No `main`, crie um `Forma[]` com as três e imprima a área de cada uma e a soma total — sem nenhum `if` de tipo;
2. **`Funcionario.java` (abstrata) + subclasses** — refaça o desafio da Aula 07 tornando `Funcionario` abstrata com `calcularSalario()` abstrato. Depois **crie um `Diretor`** sem implementar o método e copie o erro do compilador num comentário;
3. **`Emprestavel.java`, `Livro.java`, `Projetor.java`, `Almoxarifado.java`** — implemente a interface da aula em duas classes sem parentesco e escreva um método `static void emprestarTodos(Emprestavel[] itens, String responsavel)` que funciona com qualquer uma;
4. **`Pagamento.java` + `Pix.java`, `CartaoCredito.java`, `Boleto.java`** — interface `Pagamento` com `double calcularTaxa(double valor)` e `String descricao()`; Pix sem taxa, cartão com 3,5% + parcelamento, boleto com R$ 2,50 fixo. Um `main` recebe o valor da compra e imprime o total em cada forma;
5. **Desafio 🌶️ `Reprodutor.java`** — modele um player de mídia: classe abstrata `Midia` (título, duração, `reproduzir()` abstrato), com `Musica`, `Podcast` e `Video`; a interface `Baixavel` (`baixar()`, `getTamanhoMb()`) é implementada só por `Musica` e `Video`. Uma "playlist" (`Midia[]`) reproduz tudo em sequência e, num segundo laço, baixa **apenas** o que for baixável — use `instanceof` com pattern matching aqui e explique num comentário por que, neste caso, ele se justifica.

### 📤 Entrega

Estes exercícios são feitos em sala e vão para o **seu repositório** `exercicios-java-poo`:

```bash
cd ..                 # da pasta da aula para a raiz do repositório
git add aula-08/
git commit -m "Resolve exercícios da aula 08"
git push
```

Confira no navegador que a pasta apareceu em `github.com/SEU-USUARIO/exercicios-java-poo`.

## 🧠 Revisão

[8 questões de múltipla escolha](revisao/README.md) para conferir se os conceitos ficaram sólidos. Responda sem consultar a aula — depois volte e corrija.

**A entrega é pelo formulário:** [responder a revisão da Aula 08](https://docs.google.com/forms/d/e/1FAIpQLScuv-0YHfYwxeXoUYQeG-z0F0XrcOV6rjvFCD4fL1Y_B7lCiA/viewform)

Entre com uma conta Google, selecione seu nome na lista e informe seu usuário do GitHub — só o usuário, não o endereço do perfil. Se o seu nome ainda não estiver na lista, marque a última opção e escreva o nome completo no campo seguinte. É **uma resposta por aluno** e não dá para editar depois de enviar, então confira antes. A nota é liberada no AVA depois da revisão em sala e da divulgação do gabarito.

---

⬅️ [Aula 07](../aula-07-heranca/README.md) | ➡️ [Aula 09 — Coleções](../../bloco-3-poo-na-pratica/aula-09-colecoes/README.md)
