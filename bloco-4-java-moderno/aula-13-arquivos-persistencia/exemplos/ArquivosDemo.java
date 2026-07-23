/*
 * Aula 13 — salvar e carregar dados em arquivo de texto.
 * Rode DUAS vezes seguidas: na segunda, os contatos da primeira ainda estão lá.
 *   java ArquivosDemo.java
 *
 * Os dados vão para a pasta dados/ — que NÃO deve ser versionada.
 */
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivosDemo {

    private static final Path ARQUIVO = Path.of("dados", "contatos.csv");

    public static void main(String[] args) {

        List<Contato> contatos = carregar();
        System.out.println("Carregados do disco: " + contatos.size());
        contatos.forEach(c -> System.out.println("  " + c));

        // acrescenta um contato novo a cada execução
        contatos.add(new Contato("Contato " + (contatos.size() + 1), "27 99999-0000", 20));

        salvar(contatos);
        System.out.println("Salvos: " + contatos.size() + " em " + ARQUIVO.toAbsolutePath());

        System.out.println("--- leitura tolerante a linhas corrompidas ---");
        List<String> linhas = List.of("Ana;27 1111;19", "", "Bruno;27 2222", "Duda;27 3333;oito");
        for (String linha : linhas) {
            if (linha.isBlank()) continue;
            try {
                System.out.println("  ok: " + Contato.fromCsv(linha));
            } catch (RuntimeException e) {       // pega IllegalArgument E NumberFormat
                System.out.println("  linha ignorada (" + e.getClass().getSimpleName() + "): " + linha);
            }
        }
    }

    /** Grava a lista inteira, uma linha por contato. Sobrescreve o arquivo. */
    private static void salvar(List<Contato> contatos) {
        List<String> linhas = new ArrayList<>();
        for (Contato c : contatos) {
            linhas.add(c.toCsv());
        }
        try {
            Files.createDirectories(ARQUIVO.getParent());     // cria a pasta se faltar
            Files.write(ARQUIVO, linhas);
        } catch (IOException e) {                             // IOException é CHECKED
            System.out.println("Falha ao salvar: " + e.getMessage());
        }
    }

    /** Arquivo inexistente não é erro: é a primeira execução. */
    private static List<Contato> carregar() {
        List<Contato> contatos = new ArrayList<>();
        if (!Files.exists(ARQUIVO)) {
            return contatos;
        }
        try {
            for (String linha : Files.readAllLines(ARQUIVO)) {
                if (linha.isBlank()) continue;
                try {
                    contatos.add(Contato.fromCsv(linha));
                } catch (RuntimeException e) {
                    System.err.println("Linha ignorada: " + linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Falha ao ler: " + e.getMessage());
        }
        return contatos;
    }
}
