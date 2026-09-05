# Aula 05 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 05 — Classes e Objetos](../README.md). Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

📝 **As respostas vão pelo formulário:** [responder a revisão da Aula 05](https://docs.google.com/forms/d/e/1FAIpQLSf0qAdzbasNwJpnCYA61aEIx9P5HktNtc9NtotMTQofPWWIKQ/viewform)

Leia as 8 questões aqui e decida suas respostas antes de abrir o formulário: é **uma resposta por aluno**, com conta Google, e não dá para editar depois de enviar. Ele também pede seu usuário do GitHub. Se o seu nome não estiver na lista da turma, marque a última opção e escreva o nome completo no campo seguinte.

---

### Q-A05-01

Qual é a diferença entre classe e objeto?

- **a)** Classe é o arquivo `.java`; objeto é o arquivo `.class` gerado pelo compilador;
- **b)** classe é usada em programas pequenos e objeto em programas grandes;
- **c)** a classe é a planta que descreve atributos e métodos; o objeto é cada exemplar criado a partir dela, com seus próprios valores;
- **d)** são sinônimos: "objeto" é apenas o termo informal para classe.

↩︎ *Aula 05, seção 2 — Classe é a planta; objeto é a construção*

---

### Q-A05-02

O que este trecho imprime?

```java
Aluno a = new Aluno();
a.nome = "Ana";
Aluno b = a;
b.nome = "Beatriz";
System.out.println(a.nome);
```

- **a)** `Ana`, porque `b` recebeu uma cópia do objeto;
- **b)** `Beatriz`, porque `a` e `b` apontam para o mesmo objeto;
- **c)** `null`, porque a atribuição `Aluno b = a` limpa o objeto original;
- **d)** nada: falha com `NullPointerException`.

↩︎ *Aula 05, seção 4 — `new`, referência e `null`*

---

### Q-A05-03

Como se reconhece um construtor?

- **a)** Tem o mesmo nome da classe e não declara tipo de retorno — nem mesmo `void`;
- **b)** é sempre declarado como `void` e chamado de `construtor()`;
- **c)** é um método `static` chamado automaticamente pela JVM;
- **d)** é qualquer método cujo nome começa com `set`.

↩︎ *Aula 05, seção 5 — Construtores: nascer já pronto*

---

### Q-A05-04

Para que serve a palavra `this`?

- **a)** Para tornar um atributo visível fora da classe;
- **b)** para criar um novo objeto sem usar `new`;
- **c)** para indicar que o método é `static`;
- **d)** para referenciar o objeto atual, distinguindo o atributo do parâmetro de mesmo nome.

↩︎ *Aula 05, seção 5 — Construtores: nascer já pronto*

---

### Q-A05-05

Uma classe declara apenas o construtor `public Aluno(String nome, String matricula)`. O que acontece com `new Aluno();`?

- **a)** Não compila: ao declarar um construtor, o construtor vazio padrão deixa de existir;
- **b)** compila e cria um aluno com os atributos em `null`;
- **c)** compila, mas lança `NullPointerException` em execução;
- **d)** compila e chama o construtor de dois parâmetros com valores padrão.

↩︎ *Aula 05, seção 5 — Construtores: nascer já pronto*

---

### Q-A05-06

Qual é a causa mais comum de um `NullPointerException`?

- **a)** Uma divisão de inteiros com resto diferente de zero;
- **b)** um índice de array fora dos limites;
- **c)** usar um método ou atributo de uma referência que vale `null` — em geral porque faltou o `new`;
- **d)** declarar uma variável de objeto sem inicializar, o que impede a compilação.

↩︎ *Aula 05, seção 4 — `new`, referência e `null`*

---

### Q-A05-07

Chamar `calcularMedia()` diretamente do `main` produz `non-static method cannot be referenced from a static context`. Por quê?

- **a)** Porque o método precisa ser declarado antes do `main` no arquivo;
- **b)** porque o método pertence ao objeto: é preciso criar um com `new` e chamá-lo pelo objeto;
- **c)** porque `main` só pode chamar métodos que devolvem `void`;
- **d)** porque falta o `import` da própria classe.

↩︎ *Aula 05, seção 3 — Sua primeira classe*

---

### Q-A05-08

Qual é a vantagem de `Aluno[] turma` sobre três arrays paralelos (`nomes`, `matriculas`, `notas`)?

- **a)** Ocupa menos memória, porque objetos são compactados pela JVM;
- **b)** permite usar `for-each`, o que arrays de `String` não permitem;
- **c)** dispensa o uso de laços para percorrer os dados;
- **d)** cada posição guarda um objeto completo e coeso, eliminando a necessidade de manter vários arrays sincronizados.

↩︎ *Aula 05, seção 5 — Construtores: nascer já pronto*

---

⬅️ [Voltar à Aula 05](../README.md) | ➡️ [Revisão da Aula 06](../../aula-06-encapsulamento/revisao/README.md)
