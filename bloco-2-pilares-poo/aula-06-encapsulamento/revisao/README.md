# Aula 06 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 06 — Encapsulamento](../README.md). Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

📝 **As respostas vão pelo formulário:** [responder a revisão da Aula 06](https://docs.google.com/forms/d/e/1FAIpQLSfEQFqBcuujfQ8ABggQMtx5dxeaVz3Y7h3ZZ1TRZviDXRINCA/viewform)

Leia as 8 questões aqui e decida suas respostas antes de abrir o formulário: é **uma resposta por aluno**, com conta Google, e não dá para editar depois de enviar. Ele também pede seu usuário do GitHub. Se o seu nome não estiver na lista da turma, marque a última opção e escreva o nome completo no campo seguinte.

---

### Q-A06-01

Qual é o objetivo do encapsulamento?

- **a)** Reduzir o tamanho do arquivo compilado;
- **b)** manter o objeto sempre em estado válido, permitindo que o mundo externo interaja apenas pelos métodos que a classe oferece;
- **c)** permitir que uma classe herde atributos de outra;
- **d)** acelerar a execução, já que atributos privados são acessados mais rápido.

↩︎ *Aula 06, seção 1 — O problema de deixar tudo público*

---

### Q-A06-02

Com `private double saldo;`, o que acontece ao escrever `conta.saldo = 1000;` em outra classe?

- **a)** Compila e altera o saldo normalmente;
- **b)** compila, mas o valor é ignorado em execução;
- **c)** compila apenas se as duas classes estiverem no mesmo arquivo;
- **d)** não compila: `saldo has private access in ContaBancaria`.

↩︎ *Aula 06, seção 2 — `private` e os getters/setters*

---

### Q-A06-03

Por que a classe `ContaBancaria` oferece `getSaldo()` mas **não** `setSaldo()`?

- **a)** Porque o saldo só deve mudar por operações da própria classe (`depositar`, `sacar`): ler é permitido, escrever direto não;
- **b)** porque setters não podem receber parâmetros do tipo `double`;
- **c)** porque um atributo `private` não aceita setter, apenas getter;
- **d)** porque a convenção do Java proíbe setters em classes com construtor.

↩︎ *Aula 06, seção 2 — `private` e os getters/setters*

---

### Q-A06-04

O que faz `this(nome, preco, 0);` na primeira linha de um construtor?

- **a)** Cria um novo objeto da mesma classe dentro do construtor;
- **b)** chama o construtor da superclasse;
- **c)** chama outro construtor da mesma classe, e precisa ser a primeira instrução;
- **d)** atribui os três valores aos atributos automaticamente.

↩︎ *Aula 06, seção 4 — Sobrecarga de construtores*

---

### Q-A06-05

A classe `Produto` tem `private static int totalCadastrados = 0;`, incrementado no construtor. O que este trecho imprime?

```java
new Produto("Caderno");
new Produto("Caneta");
System.out.println(Produto.getTotalCadastrados());
```

- **a)** `0`
- **b)** `1`
- **c)** `2`
- **d)** Não compila: métodos `static` não podem ser chamados pelo nome da classe.

↩︎ *Aula 06, seção 5 — `static`: o que pertence à classe, não ao objeto*

---

### Q-A06-06

Por que um método `static` não consegue acessar um atributo de instância?

- **a)** Porque ele pertence à classe, e não a um objeto — não há como saber de qual objeto seria o atributo;
- **b)** porque atributos de instância são sempre `private`;
- **c)** porque métodos `static` são executados antes de a classe ser carregada;
- **d)** porque isso só é permitido dentro do método `main`.

↩︎ *Aula 06, seção 5 — `static`: o que pertence à classe, não ao objeto*

---

### Q-A06-07

O que aparece ao imprimir um objeto de uma classe que **não** sobrescreveu `toString()`?

- **a)** Uma lista com todos os atributos e seus valores;
- **b)** `null`;
- **c)** nada: a linha é ignorada;
- **d)** algo como `Produto@6d06d69c` — o nome da classe seguido de um código hexadecimal.

↩︎ *Aula 06, seção 6 — `toString()`: como o objeto se apresenta*

---

### Q-A06-08

Para que serve a anotação `@Override`?

- **a)** Para tornar o método visível às subclasses;
- **b)** para o compilador conferir que o método realmente sobrescreve outro — sem ela, um erro de assinatura vira uma sobrecarga silenciosa;
- **c)** para impedir que o método seja sobrescrito novamente;
- **d)** é apenas um comentário decorativo, sem efeito algum.

↩︎ *Aula 06, seção 6 — `toString()`: como o objeto se apresenta*

---

⬅️ [Voltar à Aula 06](../README.md) | ➡️ [Revisão da Aula 07](../../aula-07-heranca/revisao/README.md)
