# Aula 11 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 11 — Organização do Código](../README.md). Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

---

### Q-A11-01

Uma classe declara `package biblioteca.model;`. O que isso exige?

- **a)** Que a classe seja `public` e tenha um método `main`;
- **b)** que todas as demais classes do projeto estejam no mesmo pacote;
- **c)** que o arquivo seja compilado com a opção `-p biblioteca.model`;
- **d)** que o arquivo esteja na pasta `biblioteca/model/` — a estrutura de diretórios precisa corresponder ao nome do pacote.

↩︎ *Aula 11, seção 1 — Pacotes*

---

### Q-A11-02

Em qual camada o `System.out.println` deve aparecer?

- **a)** Em qualquer uma: é apenas uma questão de gosto;
- **b)** apenas na camada `app` (ou `view`), que é quem conversa com o usuário;
- **c)** apenas na camada `service`, que conhece as regras do sistema;
- **d)** apenas na camada `model`, junto dos dados que serão exibidos.

↩︎ *Aula 11, seção 2 — Camadas: cada pacote com um papel*

---

### Q-A11-03

Qual é a vantagem de um `enum` sobre uma `String` para representar o status de um pedido?

- **a)** O conjunto de valores é fechado e verificado pelo compilador: um valor inexistente ou digitado errado não compila;
- **b)** enums ocupam menos memória que strings;
- **c)** enums podem ser alterados em tempo de execução;
- **d)** apenas enums podem ser usados em `switch`.

↩︎ *Aula 11, seção 3 — `enum`: um conjunto fechado de valores*

---

### Q-A11-04

Por que `status == StatusEmprestimo.ATRASADO` é seguro, mesmo `==` sendo desaconselhado para objetos?

- **a)** Porque enums são tipos primitivos;
- **b)** porque o compilador converte `==` em `equals` para enums;
- **c)** porque cada valor de um enum é uma instância única, criada uma só vez pela JVM;
- **d)** não é seguro: o correto seria `status.equals(...)`.

↩︎ *Aula 11, seção 3 — `enum`: um conjunto fechado de valores*

---

### Q-A11-05

O que `public record Endereco(String rua, String cidade, String uf) { }` gera automaticamente?

- **a)** Construtor, métodos de acesso, `equals`, `hashCode` e `toString`;
- **b)** apenas os getters no formato `getRua()`, `getCidade()` e `getUf()`;
- **c)** construtor e setters, mas nenhum método de leitura;
- **d)** nada: `record` é apenas um comentário estrutural para o programador.

↩︎ *Aula 11, seção 4 — `record`: dados imutáveis sem cerimônia*

---

### Q-A11-06

Qual afirmação sobre `record` é verdadeira?

- **a)** Records podem herdar de outras classes com `extends`;
- **b)** records são a forma recomendada para objetos com estado que muda o tempo todo;
- **c)** records não podem ter validação no construtor;
- **d)** records são imutáveis: não têm setters e seus campos são `final`.

↩︎ *Aula 11, seção 4 — `record`: dados imutáveis sem cerimônia*

---

### Q-A11-07

Qual é a convenção de nomes para pacotes em Java?

- **a)** PascalCase, como nas classes: `Biblioteca.Model`;
- **b)** camelCase, como nos métodos: `bibliotecaModel`;
- **c)** tudo minúsculo, sem acentos: `biblioteca.model`;
- **d)** MAIÚSCULAS, como nas constantes: `BIBLIOTECA.MODEL`.

↩︎ *Aula 11, seção 1 — Pacotes*

---

### Q-A11-08

Para que serve a marcação `@throws` num comentário Javadoc?

- **a)** Para fazer o método lançar a exceção indicada automaticamente;
- **b)** para documentar em que situação o método lança determinada exceção;
- **c)** para substituir a palavra-chave `throws` na assinatura do método;
- **d)** para impedir que a exceção seja capturada por quem chama o método.

↩︎ *Aula 11, seção 5 — Convenções e Javadoc*

---

⬅️ [Voltar à Aula 11](../README.md) | ➡️ [Revisão da Aula 12](../../aula-12-projeto-biblioteca/revisao/README.md)
