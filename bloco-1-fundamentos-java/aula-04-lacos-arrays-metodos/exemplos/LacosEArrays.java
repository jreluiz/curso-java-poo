/*
 * Aula 04 — while, for, for-each, arrays, break e continue.
 * Rode com: java LacosEArrays.java
 */
public class LacosEArrays {
    public static void main(String[] args) {
        // ── while ───────────────────────────────────────────────────
        int contador = 1;
        while (contador <= 3) {
            System.out.println("while: repetição " + contador);
            contador++;              // esquecer esta linha = laço infinito
        }

        // ── for: quando existe um contador ──────────────────────────
        for (int i = 1; i <= 10; i++) {
            System.out.printf("7 x %d = %d%n", i, 7 * i);
        }

        // ── break e continue ────────────────────────────────────────
        for (int i = 1; i <= 10; i++) {
            if (i % 2 != 0) continue;    // ímpar? pula
            if (i > 8) break;            // passou de 8? encerra
            System.out.print(i + " ");   // 2 4 6 8
        }
        System.out.println();

        // ── Arrays ──────────────────────────────────────────────────
        double[] notas = new double[4];      // 4 posições, todas 0.0
        notas[0] = 8.5;
        notas[1] = 7.0;
        notas[2] = 9.5;
        notas[3] = 6.0;

        String[] nomes = {"Ana", "Bruno", "Carla"};    // já com valores

        System.out.println("Primeira nota: " + notas[0]);
        System.out.println("Quantos nomes: " + nomes.length);   // length SEM parênteses
        // System.out.println(notas[4]);   ← descomente: ArrayIndexOutOfBoundsException

        // for clássico: quando você precisa do índice
        for (int i = 0; i < notas.length; i++) {
            System.out.printf("Nota %d: %.1f%n", i + 1, notas[i]);
        }

        // for-each: quando você só quer os valores
        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        System.out.printf("Média: %.2f%n", soma / notas.length);
    }
}
