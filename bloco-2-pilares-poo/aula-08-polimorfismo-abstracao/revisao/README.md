# Aula 08 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 08 — Polimorfismo e Abstração](../README.md). Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

📝 **As respostas vão pelo formulário:** [responder a revisão da Aula 08](https://docs.google.com/forms/d/e/1FAIpQLScuv-0YHfYwxeXoUYQeG-z0F0XrcOV6rjvFCD4fL1Y_B7lCiA/viewform)

Leia as 8 questões aqui e decida suas respostas antes de abrir o formulário: é **uma resposta por aluno**, com conta Google, e não dá para editar depois de enviar. Ele também pede seu usuário do GitHub. Se o seu nome não estiver na lista da turma, marque a última opção e escreva o nome completo no campo seguinte.

---

### Q-A08-01

O que é polimorfismo?

- **a)** A mesma chamada de método executar a versão adequada ao tipo real de cada objeto;
- **b)** uma classe poder ter vários construtores diferentes;
- **c)** uma variável poder mudar de tipo durante a execução;
- **d)** um método poder ser chamado tanto pela classe quanto pelo objeto.

↩︎ *Aula 08, seção 1 — Um comando, muitos comportamentos*

---

### Q-A08-02

`receberBonus()` existe apenas em `Gerente`. O que acontece neste trecho?

```java
Funcionario f = new Gerente("Ana", 5000);
f.receberBonus();
```

- **a)** Executa normalmente, porque o objeto é um `Gerente`;
- **b)** não compila: quem determina os métodos disponíveis é o tipo da variável (`Funcionario`);
- **c)** compila e lança `ClassCastException` em execução;
- **d)** compila e o método é simplesmente ignorado.

↩︎ *Aula 08, seção 2 — Upcasting e ligação dinâmica*

---

### Q-A08-03

Com `Funcionario f = new Vendedor(...)`, quem decide **qual versão** de `calcularSalario()` executa?

- **a)** O tipo da variável, decidido na compilação;
- **b)** a ordem em que as classes foram compiladas;
- **c)** sempre a versão da superclasse, por ser a mais genérica;
- **d)** o tipo do objeto, em tempo de execução — é a ligação dinâmica.

↩︎ *Aula 08, seção 2 — Upcasting e ligação dinâmica*

---

### Q-A08-04

O que acontece ao escrever `new Funcionario("Ana", 3000)` sendo `Funcionario` uma classe abstrata?

- **a)** Compila e cria um objeto com os métodos abstratos vazios;
- **b)** compila, mas lança `UnsupportedOperationException`;
- **c)** não compila: `Funcionario is abstract; cannot be instantiated`;
- **d)** compila apenas se a classe não tiver construtor declarado.

↩︎ *Aula 08, seção 3 — Classe abstrata: um molde que não vira objeto*

---

### Q-A08-05

Uma classe concreta estende uma classe abstrata e **não** implementa o método abstrato dela. O resultado é:

- **a)** Compila: o método herdado fica valendo `null`;
- **b)** compila, mas o método não pode ser chamado;
- **c)** compila com aviso do compilador;
- **d)** não compila — implementar o método abstrato é obrigatório para uma classe concreta.

↩︎ *Aula 08, seção 3 — Classe abstrata: um molde que não vira objeto*

---

### Q-A08-06

Qual afirmação sobre interfaces e classes abstratas está correta?

- **a)** Interfaces podem ter atributos com estado; classes abstratas, não;
- **b)** uma classe pode estender várias superclasses, mas implementar só uma interface;
- **c)** uma classe estende uma superclasse, mas pode implementar várias interfaces; a interface define capacidade, sem guardar estado;
- **d)** interfaces só podem ser implementadas por classes abstratas.

↩︎ *Aula 08, seção 5 — Interface ou classe abstrata?*

---

### Q-A08-07

O que faz `if (f instanceof Vendedor v) { ... }`?

- **a)** Testa se `f` é um `Vendedor` e, em caso positivo, já disponibiliza a variável `v` convertida;
- **b)** converte `f` em `Vendedor` mesmo que ele não seja, causando erro em execução;
- **c)** compara o conteúdo de `f` com o de `v`;
- **d)** declara que `f` passará a ser do tipo `Vendedor` daqui em diante.

↩︎ *Aula 08, seção 6 — `instanceof` e o downcasting*

---

### Q-A08-08

Encontrar uma longa cadeia de `if (x instanceof A) ... else if (x instanceof B) ...` costuma indicar:

- **a)** Um uso avançado e recomendado de polimorfismo;
- **b)** que faltou polimorfismo: o comportamento deveria estar num método declarado na superclasse ou interface;
- **c)** que as classes precisam ser transformadas em `record`;
- **d)** que a hierarquia deveria ser substituída por métodos `static`.

↩︎ *Aula 08, seção 6 — `instanceof` e o downcasting*

---

⬅️ [Voltar à Aula 08](../README.md) | ➡️ [Revisão da Aula 09](../../../bloco-3-poo-na-pratica/aula-09-colecoes/revisao/README.md)
