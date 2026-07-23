/*
 * Aula 02 — entrada de dados com Scanner (este exemplo ESPERA você digitar).
 * Rode com: java LeituraTeclado.java
 */
import java.util.Scanner;

public class LeituraTeclado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Seu nome: ");
        String nome = scanner.nextLine();      // lê a linha inteira (com espaços)

        System.out.print("Sua idade: ");
        int idade = scanner.nextInt();         // lê um inteiro

        System.out.print("Sua altura (use vírgula): ");
        double altura = scanner.nextDouble();

        // ⚠️ A PEGADINHA: nextInt/nextDouble deixam o Enter no buffer.
        // Sem esta linha, o próximo nextLine() viria vazio.
        scanner.nextLine();

        System.out.print("Sua cidade: ");
        String cidade = scanner.nextLine();

        System.out.printf("%s, %d anos, %.2f m, de %s%n", nome, idade, altura, cidade);

        scanner.close();
    }
}
