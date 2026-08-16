# Aula 09 — Coleções

> 🎯 Objetivos: substituir arrays por `ArrayList`, entender generics, buscar e remover objetos com segurança e conhecer o `HashMap`.
> 🎬 Slides da aula: [apresentacao-09-colecoes.pdf](apresentacao/apresentacao-09-colecoes.pdf)

## 1. Onde o array não dá conta

```java
Aluno[] turma = new Aluno[30];
```

Três problemas aparecem no primeiro cadastro real:

1. **Tamanho fixo.** Chegou o aluno 31? Você precisa criar um array maior e copiar tudo na mão;
2. **Buracos.** Removeu o aluno da posição 5? Fica um `null` no meio, e todo laço precisa desviar dele;
3. **Contagem manual.** `turma.length` é 30 desde o início, mesmo com 3 alunos cadastrados. Você acaba mantendo um `int quantidade` do lado — e sincronizando na mão.

O Java resolve isso com o **Java Collections Framework**: estruturas que crescem, encolhem e se organizam sozinhas.

## 2. `List` e `ArrayList`

```java
import java.util.ArrayList;
import java.util.List;

List<Aluno> turma = new ArrayList<>();      // cresce conforme a necessidade
```

Essa linha tem duas ideias importantes:

- **`<Aluno>` é o *generic*:** a lista só aceita alunos. Tentar `turma.add("texto")` não compila — de novo, o compilador trabalhando por você;
- **`List` à esquerda, `ArrayList` à direita:** você declara pelo **contrato** (`List` é uma interface!) e escolhe a **implementação** na hora de criar. Amanhã, trocar para `LinkedList` é mudar uma palavra. É a lição da Aula 08 aplicada na biblioteca padrão.

> ⚠️ Generics não aceitam primitivos: é `List<Integer>` e `List<Double>`, não `List<int>`. As classes *wrapper* (`Integer`, `Double`, `Boolean`, `Character`) embrulham os primitivos, e a conversão é automática: `numeros.add(42)` funciona.

## 3. As operações do dia a dia

```java
List<String> nomes = new ArrayList<>();

nomes.add("Ana");                  // adiciona no fim
nomes.add("Bruno");
nomes.add("Carla");
nomes.add(1, "Beto");              // insere na posição 1, empurrando o resto

System.out.println(nomes.size());          // 4     ← size() é MÉTODO (array usa .length)
System.out.println(nomes.get(0));          // Ana   ← get(i), não [i]
System.out.println(nomes.contains("Ana")); // true
System.out.println(nomes.indexOf("Carla"));// 3     (-1 se não existir)
System.out.println(nomes.isEmpty());       // false

nomes.set(0, "Ana Paula");         // substitui a posição 0
nomes.remove("Beto");              // remove pelo objeto
nomes.remove(0);                   // remove pela posição
System.out.println(nomes);         // [Bruno, Carla]   ← imprime bonito de graça
```

Percorrer é igual ao array — e o `for-each` continua sendo a forma preferida:

```java
for (String nome : nomes) {
    System.out.println(nome);
}

for (int i = 0; i < nomes.size(); i++) {   // quando você precisa do índice
    System.out.println((i + 1) + " - " + nomes.get(i));
}
```

> ⚠️ **Nunca remova dentro de um `for-each`** — dá `ConcurrentModificationException`. Para remover em bloco, use `lista.removeIf(a -> a.getMedia() < 5);` (a sintaxe da seta é assunto da Aula 14).

### Buscando objetos

O padrão que você vai repetir o curso inteiro:

```java
public Aluno buscarPorMatricula(String matricula) {
    for (Aluno aluno : turma) {
        if (aluno.getMatricula().equals(matricula)) {
            return aluno;              // achou: devolve o objeto
        }
    }
    return null;                       // não achou
}
```

E quem chama **precisa** tratar o `null`:

```java
Aluno encontrado = buscarPorMatricula("1001");
if (encontrado != null) {
    System.out.println(encontrado);
} else {
    System.out.println("Aluno não encontrado.");
}
```

> 💡 Devolver `null` é a fonte número 1 de `NullPointerException`. Na Aula 10 você aprende a **lançar exceção** nesses casos, e na Aula 14, a devolver um `Optional` — as duas alternativas profissionais.

## 4. `contains` e `remove` dependem do seu `equals`

Com objetos criados por você, isto **falha**:

```java
List<Aluno> turma = new ArrayList<>();
turma.add(new Aluno("Ana", "1001"));

Aluno procurado = new Aluno("Ana", "1001");
System.out.println(turma.contains(procurado));   // false 😱
```

Motivo: `contains()` usa `equals()` internamente, e sem sobrescrita o `equals` herdado de `Object` compara **referências** — são dois objetos distintos. Sobrescreva `equals` (e `hashCode`) na sua classe, como na Aula 07, e o mesmo código passa a devolver `true`.

**Toda classe que vai morar numa coleção precisa de `equals` e `hashCode`.**

## 5. `Map`: quando existe uma chave

Lista é bom para "todos os alunos". Mas para *"me dê o aluno da matrícula 1001"*, percorrer a lista inteira é desperdício. O `Map` guarda pares **chave → valor** e encontra pela chave direto:

```java
import java.util.HashMap;
import java.util.Map;

Map<String, Aluno> porMatricula = new HashMap<>();

porMatricula.put("1001", new Aluno("Ana", "1001"));      // chave → valor
porMatricula.put("1002", new Aluno("Léo", "1002"));

Aluno ana = porMatricula.get("1001");                     // busca direta
Aluno x = porMatricula.get("9999");                       // null se não existir
Aluno y = porMatricula.getOrDefault("9999", null);        // com valor padrão

System.out.println(porMatricula.containsKey("1001"));     // true
System.out.println(porMatricula.size());                  // 2
porMatricula.remove("1002");
```

Percorrendo:

```java
for (String chave : porMatricula.keySet()) {
    System.out.println(chave + " → " + porMatricula.get(chave));
}

for (Map.Entry<String, Aluno> entrada : porMatricula.entrySet()) {
    System.out.println(entrada.getKey() + " → " + entrada.getValue());
}
```

Características do `HashMap`: **chaves únicas** (`put` com chave repetida **substitui** o valor) e **sem ordem garantida** — se a ordem importa, use `LinkedHashMap` (ordem de inserção) ou `TreeMap` (ordenado pela chave).

> 💡 É aqui que o `hashCode()` da Aula 07 aparece: o `HashMap` usa o hash da chave para achar a "gaveta" certa quase instantaneamente. Chave com `hashCode` mal implementado = objeto que entra no mapa e nunca mais é encontrado.

## 6. `Set`: coleção sem repetição

```java
import java.util.HashSet;
import java.util.Set;

Set<String> cursos = new HashSet<>();
cursos.add("Java");
cursos.add("Python");
cursos.add("Java");                 // ignorado: já existe

System.out.println(cursos.size());  // 2
```

Use `Set` quando repetição não faz sentido (CPFs cadastrados, tags, códigos únicos). Assim como no `Map`, a unicidade é decidida por `equals`/`hashCode`.

### Resumo

| Estrutura | Use quando | Cuidado |
|-----------|-----------|---------|
| `List` / `ArrayList` | ordem importa e repetição é permitida | busca percorre tudo |
| `Map` / `HashMap` | existe uma chave natural (código, CPF) | sem ordem garantida |
| `Set` / `HashSet` | elementos únicos, ordem não importa | sem índice, sem `get(i)` |

> 💻 **Código desta aula pronto para rodar:** [`Colecoes.java`](exemplos/Colecoes.java) e [`BuscaComEquals.java`](exemplos/BuscaComEquals.java)

## 🏋️ Exercícios da aula

Na pasta `aula-09/` do seu repositório:

1. **`ListaDeCompras.java`** — leia itens do teclado até o usuário digitar `fim`, guardando numa `List<String>`; depois imprima a lista numerada, o total de itens e permita remover um item pelo nome (avisando se não existir);
2. **`Turma.java` + `Aluno.java`** — a classe `Turma` guarda uma `List<Aluno>` **privada** e oferece `matricular(Aluno)`, `buscarPorMatricula(String)`, `remover(String)`, `listarAprovados()` (devolvendo uma nova `List<Aluno>`) e `calcularMediaDaTurma()`. Nenhum `main` deve tocar na lista direto;
3. **`Igualdade.java`** — crie uma `List<Aluno>` e teste `contains()` com um aluno "igual" (mesma matrícula, outro objeto) **antes** e **depois** de sobrescrever `equals`/`hashCode`. Registre os dois resultados num comentário;
4. **`Agenda.java`** — um `Map<String, String>` de nome → telefone com menu (adicionar, buscar, remover, listar tudo). Trate a busca sem resultado sem deixar o programa quebrar;
5. **Desafio 🌶️ `Estoque.java`** — `Map<String, Produto>` indexado pelo código do produto, com: cadastro (rejeitando código repetido), venda (que baixa a quantidade e recusa estoque insuficiente), relatório de produtos com estoque abaixo do mínimo e o **valor total** do estoque. Some ainda um `Set<String>` com as categorias já cadastradas, sem repetição.

### 📤 Entrega

Estes exercícios são feitos em sala e vão para o **seu repositório** `exercicios-java-poo`:

```bash
cd ..                 # da pasta da aula para a raiz do repositório
git add aula-09/
git commit -m "Resolve exercícios da aula 09"
git push
```

Confira no navegador que a pasta apareceu em `github.com/SEU-USUARIO/exercicios-java-poo`.

## 🧠 Revisão

[8 questões de múltipla escolha](revisao/README.md) para conferir se os conceitos ficaram sólidos. Responda sem consultar a aula — depois volte e corrija.

---

⬅️ [Aula 08](../../bloco-2-pilares-poo/aula-08-polimorfismo-abstracao/README.md) | ➡️ [Aula 10 — Exceções](../aula-10-excecoes/README.md)
