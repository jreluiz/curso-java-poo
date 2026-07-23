# 🧯 Erros Comuns de Java

Os erros que **todo mundo** comete, com diagnóstico e cura. Regra número 1: **leia a mensagem** — ela diz o arquivo, a linha e, no Java, quase sempre exatamente o que está errado.

Antes de tudo, saiba **em que fase o erro aconteceu**:

| Fase | Quando aparece | Cara do erro |
|------|----------------|--------------|
| **Compilação** | Ao rodar `javac` / ao salvar na IDE | `Ex01.java:7: error: cannot find symbol` |
| **Execução** | Depois que o programa já começou a rodar | `Exception in thread "main" java.lang.NullPointerException` |
| **Lógica** | Nunca — o programa roda e dá a resposta errada | 🙃 |

---

## ⚙️ Erros de compilação

### `cannot find symbol`

**Causa:** você usou um nome que o compilador não conhece — variável não declarada, método que não existe, erro de digitação (`Sistem.out`, `nomme`), ou faltou o `import`.
**Cura:** confira a grafia (maiúsculas contam! `nomeAluno` ≠ `nomealuno`), se a declaração vem **antes** do uso e se está no mesmo escopo. A linha do erro mostra o símbolo exato entre `symbol:`.

### `class Aluno is public, should be declared in a file named Aluno.java`

**Causa:** em Java, o nome do arquivo **é** o nome da classe pública. `Aluno` só pode morar em `Aluno.java`.
**Cura:** renomeie o arquivo (ou a classe). Cuidado com maiúsculas: `aluno.java` não serve.

### `incompatible types: possible lossy conversion from double to int`

**Causa:** você tentou colocar um `double` numa caixa `int` — cabe menos do que você mandou.
**Cura:** ou muda o tipo da variável para `double`, ou converte de propósito com casting: `int inteiro = (int) valorDouble;` (e aceite que a parte decimal **é jogada fora**, não arredondada).

### `missing return statement`

**Causa:** o método promete devolver algo (`public double calcularMedia()`), mas existe um caminho de execução que termina sem `return` — quase sempre um `if` sem `else`.
**Cura:** garanta um `return` em **todos** os caminhos.

### `non-static method X() cannot be referenced from a static context`

**Causa:** você chamou um método de **instância** direto do `main` (que é `static`), sem ter um objeto.
**Cura:** crie o objeto primeiro: `Aluno a = new Aluno(); a.calcularMedia();`. Este é o erro nº 1 do Bloco 2 — ele avisa que o método pertence ao **objeto**, não à classe.

### `constructor Aluno in class Aluno cannot be applied to given types`

**Causa:** você chamou `new Aluno()` mas o único construtor exige argumentos (ou os tipos estão na ordem errada).
**Cura:** passe os argumentos certos, ou declare também um construtor sem parâmetros. Lembre: **ao escrever qualquer construtor, o construtor padrão vazio deixa de existir.**

### `unreported exception ...; must be caught or declared to be thrown`

**Causa:** você chamou algo que lança uma exceção **checked** (típico de arquivos: `Files.readAllLines`) sem tratar.
**Cura:** envolva em `try`/`catch` ou declare `throws` na assinatura do método. Só acontece com checked (Aula 10).

### `variable x might not have been initialized`

**Causa:** variável **local** declarada e usada sem receber valor. Atributos de classe ganham valor padrão (`0`, `false`, `null`); variáveis locais **não**.
**Cura:** inicialize na declaração: `int soma = 0;`.

### `';' expected` / `reached end of file while parsing`

**Causa:** ponto e vírgula, parêntese ou **chave** faltando. `reached end of file` é sempre chave `}` não fechada.
**Cura:** o erro real costuma estar **antes** da linha apontada. Formate o arquivo (`Ctrl + Alt + L`): se a indentação sair estranha, ela mostra onde o bloco não fechou.

---

## 💥 Erros de execução

### `NullPointerException`

**Causa:** você chamou um método (ou acessou um atributo) de uma referência que vale `null` — objeto que nunca foi criado com `new`, atributo `String` ainda não preenchido, busca em lista que não encontrou nada e devolveu `null`.
**Cura:** leia a mensagem — o Java moderno diz exatamente qual variável era nula (*helpful NullPointerException*). Depois pergunte: quem deveria ter dado `new` aqui? Proteja com `if (obj != null)` **ou** garanta o valor no construtor.

### `ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5`

**Causa:** índice fora do array. Com 5 posições, os índices válidos são **0 a 4**.
**Cura:** use `< array.length` (não `<=`) no `for`. Em `List`, o equivalente é `IndexOutOfBoundsException`.

### `InputMismatchException`

**Causa:** você pediu `scanner.nextInt()` e o usuário digitou `abc` (ou `3,5` onde se esperava inteiro).
**Cura:** trate com `try`/`catch` ou valide antes com `scanner.hasNextInt()`. Nunca confie no que o usuário digita (Aula 10).

### `NumberFormatException`

**Causa:** `Integer.parseInt("abc")` — texto que não representa número. Comum ao ler dados de arquivo (Aula 13).
**Cura:** valide a linha antes de converter e trate a exceção.

### `ConcurrentModificationException`

**Causa:** você removeu um item da lista **enquanto** a percorria com `for-each`.
**Cura:** use `lista.removeIf(...)`, ou um `Iterator` com `it.remove()`, ou percorra de trás para frente com `for` clássico.

### `StackOverflowError`

**Causa:** recursão infinita. O clássico da POO: um `toString()` que imprime um objeto que imprime o primeiro de volta; ou um getter que chama a si mesmo (`return getNome();` dentro de `getNome()`).
**Cura:** siga a pilha do erro — ela repete o nome do método culpado dezenas de vezes.

### `ClassCastException`

**Causa:** *downcasting* para o tipo errado: `Cachorro c = (Cachorro) animal;` quando `animal` era um `Gato`.
**Cura:** teste antes com `if (animal instanceof Cachorro c)`. E desconfie: casting demais costuma ser sinal de que faltou polimorfismo (Aula 08).

---

## 🙃 Erros de lógica (o programa roda e mente)

### `if (nome == "Maria")` nunca é verdadeiro

**Causa:** `==` compara **referências** (se são o mesmo objeto na memória), não o conteúdo. Com Strings vindas do `Scanner` ou de arquivo, dá `false` mesmo com o texto igual.
**Cura:** `nome.equals("Maria")` — ou `"Maria".equals(nome)`, que ainda evita `NullPointerException`. Para ignorar maiúsculas: `equalsIgnoreCase`.

### `5 / 2` deu `2`

**Causa:** divisão entre dois `int` é **divisão inteira** — o resto é descartado.
**Cura:** faça um dos lados virar `double`: `5 / 2.0`, ou `(double) soma / quantidade`. Média calculada errado quase sempre é isto.

### Depois de `nextInt()`, o `nextLine()` vem vazio

**Causa:** `nextInt()` lê o número mas **deixa o Enter** no buffer; o `nextLine()` seguinte consome esse Enter e devolve string vazia.
**Cura:** um `scanner.nextLine()` extra logo após o `nextInt()` — ou leia tudo com `nextLine()` e converta com `Integer.parseInt`.

### Alterei um objeto e o outro mudou junto

**Causa:** variáveis de objeto guardam **referência**. `Aluno b = a;` não copia o aluno: dá um segundo nome ao mesmo objeto.
**Cura:** se quer uma cópia independente, crie um novo objeto: `Aluno b = new Aluno(a.getNome());`.

### Sobrescrevi um método e o comportamento não mudou

**Causa:** você escreveu uma **sobrecarga** sem querer — assinatura diferente da original (`equals(Aluno o)` em vez de `equals(Object o)`, ou `toString(String s)`).
**Cura:** anote **sempre** com `@Override`. Se a anotação der erro de compilação, é porque não é sobrescrita de verdade — que é justamente o que ela existe para denunciar.

### `contains()` / `remove()` não encontram o objeto que está na lista

**Causa:** sem `equals()` sobrescrito, a comparação é por referência: só encontra se for **o mesmo objeto**, não um igual.
**Cura:** sobrescreva `equals()` (e `hashCode()` junto) na sua classe (Aula 07).

### Os dados somem quando fecho o programa

**Causa:** não é erro — é a memória RAM funcionando como esperado.
**Cura:** persistência em arquivo (Aula 13).

---

🏠 [Voltar ao início](../README.md) · 🔗 [Links úteis](links-uteis.md)
