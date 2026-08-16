# Aula 14 — Lambdas e Streams

> 🎯 Objetivos: escrever lambdas, ordenar coleções com `Comparator`, transformar dados com a Streams API e devolver ausência com `Optional`.
> 🎬 Slides da aula: [apresentacao-14-lambdas-streams.pdf](apresentacao/apresentacao-14-lambdas-streams.pdf)

Este é o Java moderno. Nada aqui substitui o que você aprendeu — mas boa parte dos laços que você escreveu no Bloco 3 pode virar uma linha legível.

## 1. Interface funcional e lambda

Lembre da Aula 08: uma interface é um contrato. Quando ela tem **um único método abstrato**, chama-se **interface funcional** — e para essas o Java aceita uma sintaxe enxuta.

```java
public interface Filtro {
    boolean testar(Livro livro);
}
```

O jeito verboso (classe anônima), que você ainda vai encontrar em códigos antigos:

```java
Filtro disponiveis = new Filtro() {
    @Override
    public boolean testar(Livro livro) {
        return livro.estaDisponivel();
    }
};
```

O mesmo, com **lambda**:

```java
Filtro disponiveis = livro -> livro.estaDisponivel();
//                   ↑ parâmetro  ↑ o que devolve
```

Anatomia:

```java
() -> System.out.println("oi")                 // sem parâmetro
x -> x * 2                                     // um parâmetro, retorno implícito
(a, b) -> a + b                                // dois parâmetros
(a, b) -> {                                    // corpo com várias linhas: precisa de return
    int soma = a + b;
    return soma * 2;
}
```

E a *method reference*, um atalho para quando a lambda só repassa o argumento:

```java
lista.forEach(item -> System.out.println(item));
lista.forEach(System.out::println);            // idêntico, mais curto
```

## 2. Ordenando com `Comparator`

Ordenar uma lista de objetos exige responder: **por qual critério?** É isso que um `Comparator` diz.

```java
List<Livro> acervo = new ArrayList<>(...);

acervo.sort((a, b) -> a.getTitulo().compareTo(b.getTitulo()));      // por título
acervo.sort(Comparator.comparing(Livro::getTitulo));                // idêntico, mais legível
acervo.sort(Comparator.comparingInt(Livro::getAno).reversed());     // mais novos primeiro

// Dois critérios: ano e, em caso de empate, título
acervo.sort(Comparator.comparingInt(Livro::getAno)
                      .thenComparing(Livro::getTitulo));
```

> 💡 `compareTo` devolve negativo, zero ou positivo (antes, igual, depois). `Comparator.comparing` monta isso para você a partir de um getter — prefira essa forma.

## 3. Streams: descrever o resultado, não o passo a passo

Um `stream` é um **fluxo de dados** sobre o qual você encadeia operações. Compare:

```java
// Antes: como fazer
List<Livro> disponiveis = new ArrayList<>();
for (Livro l : acervo) {
    if (l.estaDisponivel()) {
        disponiveis.add(l);
    }
}

// Agora: o que você quer
List<Livro> disponiveis = acervo.stream()
        .filter(Livro::estaDisponivel)
        .toList();
```

As operações mais usadas:

```java
// filter — mantém só o que passa no teste
List<Livro> antigos = acervo.stream()
        .filter(l -> l.getAno() < 1950)
        .toList();

// map — TRANSFORMA cada elemento em outra coisa
List<String> titulos = acervo.stream()
        .map(Livro::getTitulo)
        .toList();

// sorted + limit — ordena e corta
List<Livro> top3 = acervo.stream()
        .sorted(Comparator.comparingInt(Livro::getAno).reversed())
        .limit(3)
        .toList();

// count / anyMatch / allMatch — respostas diretas
long total = acervo.stream().filter(Livro::estaDisponivel).count();
boolean temMachado = acervo.stream().anyMatch(l -> l.getAutor().contains("Machado"));
boolean todosRecentes = acervo.stream().allMatch(l -> l.getAno() > 2000);

// encadeando tudo
List<String> titulosDisponiveis = acervo.stream()
        .filter(Livro::estaDisponivel)
        .sorted(Comparator.comparing(Livro::getTitulo))
        .map(Livro::getTitulo)
        .toList();
```

> ⚠️ **A stream não altera a lista original** — ela produz um resultado novo. E é de uso único: depois de um `.toList()`, aquela stream acabou.

## 4. Números: soma, média, máximo

```java
double valorTotal = produtos.stream()
        .mapToDouble(Produto::getPreco)          // vira um fluxo de double
        .sum();

double mediaNotas = alunos.stream()
        .mapToDouble(Aluno::calcularMedia)
        .average()
        .orElse(0);                              // e se a lista estiver vazia?

int maiorAno = acervo.stream()
        .mapToInt(Livro::getAno)
        .max()
        .orElse(0);
```

## 5. `Optional`: dizer "pode não haver" sem `null`

Na Aula 09, buscas sem resultado devolviam `null` — e quem esquecesse de verificar ganhava um `NullPointerException`. `Optional` é uma caixinha que **pode ou não** conter um valor, e obriga quem recebe a considerar as duas hipóteses:

```java
public Optional<Livro> buscarPorCodigo(String codigo) {
    return acervo.stream()
            .filter(l -> l.getCodigo().equalsIgnoreCase(codigo))
            .findFirst();                         // devolve Optional<Livro>
}
```

```java
Optional<Livro> resultado = service.buscarPorCodigo("L001");

if (resultado.isPresent()) {
    System.out.println(resultado.get());
}

// formas mais idiomáticas
resultado.ifPresent(System.out::println);
Livro livro = resultado.orElseThrow(() -> new ItemNaoEncontradoException("L001"));
String titulo = resultado.map(Livro::getTitulo).orElse("(não encontrado)");
```

## 6. Agrupando com `Collectors`

Relatórios ficam curtos com `groupingBy` — o `Map` sai pronto:

```java
import java.util.stream.Collectors;

// Quantos itens por categoria?
Map<String, Long> porCategoria = acervo.stream()
        .collect(Collectors.groupingBy(Livro::getCategoria, Collectors.counting()));
// {Romance=4, Técnico=7, Infantil=2}

// Os títulos de cada autor
Map<String, List<String>> porAutor = acervo.stream()
        .collect(Collectors.groupingBy(Livro::getAutor,
                 Collectors.mapping(Livro::getTitulo, Collectors.toList())));

// Uma string única separada por vírgula
String listaDeTitulos = acervo.stream()
        .map(Livro::getTitulo)
        .collect(Collectors.joining(", "));
```

> 💡 **Quando NÃO usar stream:** quando o laço tradicional for mais claro. Stream de cinco operações aninhadas com lambdas de três linhas cada é pior que um `for` honesto. O objetivo é legibilidade — se ficou difícil de ler, volte ao laço.

> 💻 **Código desta aula pronto para rodar:** [`Livro.java`](exemplos/Livro.java) + [`StreamsDemo.java`](exemplos/StreamsDemo.java)

## 🏋️ Exercícios da aula

Na pasta `aula-14/` do seu repositório:

1. **`Ordenacao.java`** — dada uma `List<Produto>`, imprima-a ordenada por preço (crescente), por nome (alfabética) e por categoria + preço (dois critérios), usando `Comparator.comparing` e `thenComparing`;
2. **`Refatorar.java`** — reescreva com stream três laços dos seus exercícios da Aula 09 (um `filter`, um `map`, uma contagem) e deixe a versão antiga comentada logo acima de cada um, para comparar;
3. **`Relatorio.java`** — sobre uma `List<Aluno>`: quantos foram aprovados, a média geral da turma, o nome do aluno com maior média, e a lista de nomes dos reprovados em ordem alfabética — tudo com streams;
4. **`Busca.java`** — troque um `buscarPorX` que devolvia `null` por um que devolve `Optional`; mostre no `main` os três desfechos: `ifPresent`, `orElse` com valor padrão e `orElseThrow` com exceção personalizada;
5. **Desafio 🌶️ `Estatisticas.java`** — a partir de um CSV de vendas carregado com o que você aprendeu na Aula 13, produza com streams: faturamento total, faturamento **por categoria** (`groupingBy`), os 3 produtos mais vendidos, o ticket médio e a lista de produtos que nunca venderam. Grave o relatório em arquivo.

### 📤 Entrega

Estes exercícios são feitos em sala e vão para o **seu repositório** `exercicios-java-poo`:

```bash
cd ..                 # da pasta da aula para a raiz do repositório
git add aula-14/
git commit -m "Resolve exercícios da aula 14"
git push
```

Confira no navegador que a pasta apareceu em `github.com/SEU-USUARIO/exercicios-java-poo`.

## 🧠 Revisão

[8 questões de múltipla escolha](revisao/README.md) para conferir se os conceitos ficaram sólidos. Responda sem consultar a aula — depois volte e corrija.

---

⬅️ [Aula 13](../aula-13-arquivos-persistencia/README.md) | ➡️ [Aula 15 — Laboratório: do requisito ao sistema](../aula-15-projeto-final/README.md)
