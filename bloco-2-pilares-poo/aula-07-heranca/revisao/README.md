# Aula 07 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 07 — Herança](../README.md). Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

---

### Q-A07-01

O que significa `public class Aluno extends Pessoa`?

- **a)** Que `Aluno` contém um atributo do tipo `Pessoa`;
- **b)** que `Pessoa` passa a enxergar os atributos privados de `Aluno`;
- **c)** que as duas classes precisam estar no mesmo arquivo;
- **d)** que `Aluno` **é uma** `Pessoa` e herda seus atributos e métodos.

↩︎ *Aula 07, seção 2 — `extends` e `super`*

---

### Q-A07-02

Quem enxerga um atributo declarado como `protected`?

- **a)** Somente a própria classe;
- **b)** qualquer classe do programa, como se fosse `public`;
- **c)** a própria classe **e suas subclasses**;
- **d)** somente as classes que estiverem no pacote `model`.

↩︎ *Aula 07, seção 2 — `extends` e `super`*

---

### Q-A07-03

Ao executar `new Aluno("Ana")`, em que ordem os construtores rodam?

- **a)** Apenas o de `Aluno`: o da superclasse só roda se for chamado explicitamente no `main`;
- **b)** primeiro o de `Pessoa` (superclasse), depois o de `Aluno`;
- **c)** primeiro o de `Aluno`, depois o de `Pessoa`;
- **d)** os dois em paralelo, sem ordem definida.

↩︎ *Aula 07, seção 2 — `extends` e `super`*

---

### Q-A07-04

Onde a chamada `super(...)` precisa aparecer?

- **a)** Como **primeira instrução** do construtor da subclasse;
- **b)** na última linha do construtor, depois de inicializar os atributos próprios;
- **c)** em qualquer lugar do construtor, contanto que apareça uma vez;
- **d)** fora do construtor, junto à declaração da classe.

↩︎ *Aula 07, seção 2 — `extends` e `super`*

---

### Q-A07-05

A classe `Aluno` **não** sobrescreveu `equals`. O que este trecho imprime?

```java
Aluno a1 = new Aluno("Ana", "1001");
Aluno a2 = new Aluno("Ana", "1001");
System.out.println(a1.equals(a2));
```

- **a)** `true`, porque todos os atributos são iguais;
- **b)** `false`, porque o `equals` herdado de `Object` compara **referências**;
- **c)** não compila: `equals` precisa ser sobrescrito antes de ser usado;
- **d)** lança `NullPointerException`.

↩︎ *Aula 07, seção 5 — `equals()` e `hashCode()`*

---

### Q-A07-06

Qual é o risco de sobrescrever `equals()` sem sobrescrever `hashCode()`?

- **a)** Nenhum: `hashCode` é opcional em qualquer situação;
- **b)** o programa deixa de compilar;
- **c)** o `toString()` passa a imprimir o endereço de memória;
- **d)** coleções baseadas em hash (`HashMap`, `HashSet`) podem **não encontrar** um objeto que está lá dentro.

↩︎ *Aula 07, seção 5 — `equals()` e `hashCode()`*

---

### Q-A07-07

Um `Cliente` precisa guardar rua, cidade e UF, já modelados na classe `Endereco`. Qual é o desenho correto?

- **a)** `Cliente extends Endereco`, para reaproveitar os três atributos;
- **b)** `Endereco extends Cliente`, porque o endereço é mais específico;
- **c)** **composição**: `Cliente` tem um atributo do tipo `Endereco`;
- **d)** copiar os três atributos para `Cliente` e apagar a classe `Endereco`.

↩︎ *Aula 07, seção 6 — Quando **não** herdar: prefira composição*

---

### Q-A07-08

O que faz `super.toString()` dentro do `toString()` de uma subclasse?

- **a)** Chama a versão da **superclasse**, permitindo reaproveitá-la e complementar o texto;
- **b)** chama recursivamente o próprio `toString()`, causando `StackOverflowError`;
- **c)** imprime o endereço de memória do objeto;
- **d)** não compila: `super` só pode ser usado em construtores.

↩︎ *Aula 07, seção 4 — Sobrescrita: mudando o comportamento herdado*

---

⬅️ [Voltar à Aula 07](../README.md) | ➡️ [Revisão da Aula 08](../../aula-08-polimorfismo-abstracao/revisao/README.md)
