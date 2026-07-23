/*
 * Aula 14 — lambdas, Comparator, streams, Optional e Collectors.
 * Rode com: java StreamsDemo.java
 */
import java.util.*;
import java.util.stream.Collectors;

public class StreamsDemo {

    public static void main(String[] args) {

        List<Livro> acervo = new ArrayList<>(List.of(
                new Livro("Dom Casmurro", "Machado de Assis", "Romance", 1899, true),
                new Livro("Memórias Póstumas", "Machado de Assis", "Romance", 1881, false),
                new Livro("Clean Code", "Robert Martin", "Técnico", 2008, true),
                new Livro("Effective Java", "Joshua Bloch", "Técnico", 2018, true),
                new Livro("O Pequeno Príncipe", "Saint-Exupéry", "Infantil", 1943, false)));

        System.out.println("--- laço tradicional x stream ---");
        List<Livro> disponiveisComFor = new ArrayList<>();
        for (Livro l : acervo) {
            if (l.estaDisponivel()) disponiveisComFor.add(l);
        }
        List<Livro> disponiveis = acervo.stream()
                .filter(Livro::estaDisponivel)
                .toList();
        System.out.println(disponiveisComFor);
        System.out.println(disponiveis);

        System.out.println("--- map: transforma cada elemento ---");
        List<String> titulos = acervo.stream()
                .map(Livro::getTitulo)
                .toList();
        System.out.println(titulos);

        System.out.println("--- Comparator: ordenando por critérios ---");
        acervo.sort(Comparator.comparing(Livro::getTitulo));
        System.out.println("por título:  " + acervo);

        acervo.sort(Comparator.comparingInt(Livro::getAno).reversed());
        System.out.println("mais novos:  " + acervo);

        acervo.sort(Comparator.comparing(Livro::getCategoria)
                              .thenComparing(Livro::getTitulo));
        System.out.println("cat+título:  " + acervo);

        System.out.println("--- encadeando ---");
        List<String> titulosDisponiveis = acervo.stream()
                .filter(Livro::estaDisponivel)
                .sorted(Comparator.comparing(Livro::getTitulo))
                .map(Livro::getTitulo)
                .toList();
        System.out.println(titulosDisponiveis);

        System.out.println("--- respostas diretas ---");
        System.out.println("disponíveis: " + acervo.stream().filter(Livro::estaDisponivel).count());
        System.out.println("tem Machado? " + acervo.stream()
                .anyMatch(l -> l.getAutor().contains("Machado")));
        System.out.println("todos após 1800? " + acervo.stream().allMatch(l -> l.getAno() > 1800));

        System.out.println("--- números ---");
        System.out.println("ano mais recente: " + acervo.stream()
                .mapToInt(Livro::getAno).max().orElse(0));
        System.out.printf("ano médio: %.1f%n", acervo.stream()
                .mapToInt(Livro::getAno).average().orElse(0));

        System.out.println("--- Optional: pode não haver ---");
        buscarPorTitulo(acervo, "Clean Code").ifPresent(l -> System.out.println("achou: " + l));
        System.out.println(buscarPorTitulo(acervo, "Inexistente")
                .map(Livro::getTitulo)
                .orElse("(não encontrado)"));

        System.out.println("--- agrupando ---");
        Map<String, Long> porCategoria = acervo.stream()
                .collect(Collectors.groupingBy(Livro::getCategoria, Collectors.counting()));
        System.out.println(porCategoria);

        Map<String, List<String>> porAutor = acervo.stream()
                .collect(Collectors.groupingBy(Livro::getAutor,
                        Collectors.mapping(Livro::getTitulo, Collectors.toList())));
        System.out.println(porAutor);

        System.out.println(acervo.stream().map(Livro::getTitulo)
                .collect(Collectors.joining(", ")));

        System.out.println("--- a lista original NÃO mudou de tamanho ---");
        System.out.println("acervo ainda tem " + acervo.size() + " livros");
    }

    static Optional<Livro> buscarPorTitulo(List<Livro> acervo, String titulo) {
        return acervo.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }
}
