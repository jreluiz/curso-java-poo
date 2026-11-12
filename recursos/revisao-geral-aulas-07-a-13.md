# Revisão geral — Aulas 07 a 13

### R-01

Uma locadora tem as classes `Carro` e `Moto`. As duas declaram os atributos `placa`, `ano` e `marca`, com os mesmos getters e o mesmo `toString()`. Segundo a aula, o caminho para acabar com essa repetição é:

- **a)** fazer `Moto` estender `Carro`, já que as duas classes têm placa, ano e marca;
- **b)** manter as duas classes como estão e repetir cada mudança na outra;
- **c)** criar a superclasse `Veiculo` com o que é comum e fazer `Carro` e `Moto` estenderem `Veiculo`;
- **d)** apagar a classe `Moto` e usar a classe `Carro` também para cadastrar as motos.

↩︎ *Aula 07, seções 1 e 2 — O problema: copiar e colar entre classes; `extends` e `super`*

---

### R-02

Considere as classes abaixo:

```java
public class Produto {
    protected String nome;

    public Produto(String nome) {
        this.nome = nome;
    }

    public String descricao() {
        return nome;
    }
}

public class ProdutoImportado extends Produto {
    private String pais;

    public ProdutoImportado(String nome, String pais) {
        super(nome);
        this.pais = pais;
    }

    @Override
    public String descricao() {
        return super.descricao() + " (importado do " + pais + ")";
    }
}
```

O que este trecho imprime?

```java
ProdutoImportado p = new ProdutoImportado("Vinho", "Chile");
System.out.println(p.descricao());
```

- **a)** `Vinho (importado do Chile)`
- **b)** `Vinho`
- **c)** `(importado do Chile)`
- **d)** nada: não compila, porque `descricao()` já existe em `Produto`.

↩︎ *Aula 07, seção 4 — Sobrescrita: mudando o comportamento herdado*

---

### R-03

Um programa de desenho guarda as figuras num array `Forma[]` e calcula a área total chamando `f.area()` para cada figura. `Circulo` e `Quadrado` estendem `Forma` e sobrescrevem `area()`. Agora o programa precisa desenhar triângulos. Com polimorfismo, o que muda?

- **a)** o laço da área total ganha um `if` que pergunta se a figura é um triângulo;
- **b)** a classe `Forma` ganha um método novo, `areaDoTriangulo()`, só para esse caso;
- **c)** os triângulos ficam num array separado, com um segundo laço só para eles;
- **d)** basta criar a classe `Triangulo`, que estende `Forma` e sobrescreve `area()`.

↩︎ *Aula 08, seção 1 — Um comando, muitos comportamentos*

---

### R-04

Na linha `Funcionario f = new Gerente("Ana", 3000);`, o que decide o tipo da variável (`Funcionario`) e o que decide o tipo do objeto (`Gerente`)?

- **a)** a variável decide qual versão de cada método roda, e o objeto decide quais métodos podem ser chamados;
- **b)** a variável decide quais métodos podem ser chamados, e o objeto decide qual versão de cada método roda;
- **c)** a variável decide tudo, e o tipo do objeto só importa na hora do `new`;
- **d)** o objeto decide tudo, e o compilador ignora o tipo da variável.

↩︎ *Aula 08, seção 2 — Upcasting e ligação dinâmica*

---

### R-05

Uma loja precisa de três coleções: (1) os produtos, cada um encontrado direto pelo seu código; (2) a fila de atendimento, na ordem de chegada; e (3) as placas dos carros no estacionamento, sem repetição. As estruturas mais adequadas são, nessa ordem:

- **a)** `Map`, `List` e `Set`
- **b)** `List`, `Map` e `Set`
- **c)** `Set`, `List` e `Map`
- **d)** `Map`, `Set` e `List`

↩︎ *Aula 09, seções 2, 5 e 6 — `List` e `ArrayList`; `Map`: quando existe uma chave; `Set`: coleção sem repetição*

---

### R-06

A classe `Produto` tem os atributos `codigo` e `nome`, e **não** sobrescreveu `equals`. O que este trecho imprime?

```java
List<Produto> carrinho = new ArrayList<>();
carrinho.add(new Produto("P1", "Caneta"));

Produto outro = new Produto("P1", "Caneta");
System.out.println(carrinho.contains(outro));
```

- **a)** `true`, porque os dois produtos têm o mesmo código e o mesmo nome;
- **b)** `true`, porque `contains` compara os atributos um a um;
- **c)** `false`, porque, sem `equals` sobrescrito, a comparação é feita pela referência;
- **d)** nada: não compila, porque `contains` só aceita `String`.

↩︎ *Aula 09, seção 4 — `contains` e `remove` dependem do seu `equals`*

---

### R-07

O que este trecho imprime?

```java
int total = 10;
int quantidade = 0;

try {
    int media = total / quantidade;
    System.out.println("Média: " + media);
} catch (ArithmeticException e) {
    System.out.println("Não dá para dividir por zero.");
}
```

- **a)** `Média: 0`
- **b)** `Não dá para dividir por zero.`
- **c)** `Média: 0` e `Não dá para dividir por zero.`, em duas linhas
- **d)** nada: o programa encerra e mostra o *stack trace* da exceção.

↩︎ *Aula 10, seção 2 — `try` / `catch` / `finally`*

---

### R-08

Considere a classe abaixo:

```java
public class Produto {
    private double preco;

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser positivo.");
        }
        this.preco = preco;
    }
}
```

O que este trecho imprime?

```java
Produto p = new Produto();
try {
    p.setPreco(-5);
} catch (IllegalArgumentException e) {
    System.out.println(e.getMessage());
}
```

- **a)** `-5.0`
- **b)** `0.0`
- **c)** nada: o programa encerra e mostra o *stack trace* da exceção;
- **d)** `Preço deve ser positivo.`

↩︎ *Aula 10, seção 4 — `throw`: lançando as suas*

---

### R-09

O sistema da biblioteca está organizado nas camadas `model`, `service` e `app`. Em qual camada fica, nesta ordem: o `Scanner` que lê a opção do menu; o método `emprestar(codigo, idUsuario)`, que confere as regras e registra o empréstimo; e a classe `Livro`?

- **a)** `app`, `service` e `model`
- **b)** `service`, `app` e `model`
- **c)** `model`, `service` e `app`
- **d)** `app`, `model` e `service`

↩︎ *Aula 11, seção 2 — Camadas: cada pacote com um papel*

---

### R-10

Considere o `enum` abaixo:

```java
public enum Plano {
    BASICO(1),
    PADRAO(2),
    PREMIUM(4);

    private final int telas;

    Plano(int telas) {
        this.telas = telas;
    }

    public int getTelas() {
        return telas;
    }
}
```

O que este trecho imprime?

```java
Plano plano = Plano.PREMIUM;
System.out.println(plano.getTelas());
```

- **a)** `PREMIUM`
- **b)** `4`
- **c)** `2`
- **d)** `3`

↩︎ *Aula 11, seção 3 — `enum`: um conjunto fechado de valores*

---

### R-11

Um colega escreveu o `listarAcervo()` do `BibliotecaService` devolvendo a própria lista do serviço:

```java
public List<ItemAcervo> listarAcervo() {
    return acervo;
}
```

Com 3 itens cadastrados, o que este trecho imprime?

```java
service.listarAcervo().clear();
System.out.println(service.listarAcervo().size());
```

- **a)** `3`, porque o `clear()` apagou só uma cópia da lista;
- **b)** nada: não compila, porque a lista `acervo` é `private`;
- **c)** `0`, porque o `clear()` apagou a própria lista do serviço;
- **d)** `3`, porque o `clear()` só funciona dentro do `BibliotecaService`.

↩︎ *Aula 12, Etapa 4 — O `BibliotecaService`*

---

### R-12

No projeto da biblioteca, `ItemAcervo` é uma classe abstrata e `Emprestavel` é uma interface. Por que essa combinação?

- **a)** porque o Java exige que toda classe abstrata implemente pelo menos uma interface;
- **b)** porque só a interface pode guardar atributos como `codigo` e `titulo`;
- **c)** porque uma classe só pode estender outra se as duas implementarem a mesma interface;
- **d)** porque `ItemAcervo` reúne o que todo item tem, e `Emprestavel` descreve uma capacidade: ser emprestado.

↩︎ *Aula 08, seção 5 — Interface ou classe abstrata?; Aula 12, Etapa 3 — A interface `Emprestavel`*

---

### R-13

Por que os objetos criados por um programa Java — uma `List<Contato>` cheia de contatos, por exemplo — deixam de existir quando o programa é encerrado?

- **a)** porque vivem na memória RAM, que é volátil: ao encerrar, o sistema operacional recupera essa memória;
- **b)** porque o Java apaga todas as listas a cada execução, por motivo de segurança;
- **c)** porque o `ArrayList` só guarda os objetos até o próximo `System.out.println`;
- **d)** porque o programa não usou `try/catch`, e sem ele nenhum dado fica guardado.

↩︎ *Aula 13, seção 1 — Por que os dados somem*

---

### R-14

Considere a classe abaixo:

```java
public class Contato {
    private String nome;
    private String telefone;

    public Contato(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String toCsv() {
        return String.join(";", nome, telefone);
    }
}
```

O que este trecho imprime?

```java
Contato c = new Contato("Ana", "9999-0000");
System.out.println(c.toCsv());
```

- **a)** `Ana 9999-0000`
- **b)** `Ana;9999-0000`
- **c)** `nome;telefone`
- **d)** `Contato@1b6d3586`

↩︎ *Aula 13, seção 5 — De objeto para linha, e de volta*

---

⬅️ [Voltar ao plano de aulas](../README.md)
