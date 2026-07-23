/*
 * Aula 02 — tipos primitivos, String, casting e divisão inteira.
 * Rode com: java TiposEVariaveis.java
 */
public class TiposEVariaveis {
    public static void main(String[] args) {
        // ── Primitivos ──────────────────────────────────────────────
        int quantidade = 42;
        double preco = 19.90;
        boolean aprovado = true;
        char inicial = 'M';                  // aspas SIMPLES
        long populacao = 8_000_000_000L;     // L no fim; _ é separador visual

        System.out.println(quantidade + " | " + preco + " | " + aprovado
                + " | " + inicial + " | " + populacao);

        // ── String é objeto: vem com métodos ────────────────────────
        String frase = "Java é divertido";
        System.out.println(frase.length());          // 16
        System.out.println(frase.toUpperCase());     // JAVA É DIVERTIDO
        System.out.println(frase.contains("Java"));  // true
        System.out.println(frase.charAt(0));         // J
        System.out.println(frase.substring(0, 4));   // Java (0 até ANTES do 4)
        System.out.println("  espaços  ".trim());    // espaços

        // ── printf: %s texto, %d inteiro, %.2f decimal, %n quebra ───
        System.out.printf("Aluno: %s | Média: %.2f%n", "Maria", 8.456);

        // ── Casting ─────────────────────────────────────────────────
        int inteiro = 10;
        double comDecimais = inteiro;        // automático: int cabe num double
        int truncado = (int) 9.87;           // explícito: DESCARTA a parte decimal
        System.out.println(comDecimais + " | " + truncado);   // 10.0 | 9

        // ── A armadilha da divisão inteira ──────────────────────────
        int soma = 7;
        int qtd = 2;
        System.out.println(soma / qtd);             // 3   ← int / int = int!
        System.out.println((double) soma / qtd);    // 3.5 ✅
        System.out.println(7 / 2.0);                // 3.5 ✅
        System.out.println(7 % 2);                  // 1   (resto)

        // ── final: o valor não pode mudar ───────────────────────────
        final double PI = 3.14159;
        System.out.println("PI = " + PI);
        // PI = 3.15;   ← descomente para ver: cannot assign a value to final variable PI
    }
}
