/*
 * Aula 03 — operadores, if/else, switch e ternário.
 * Rode com: java Condicionais.java
 */
public class Condicionais {
    public static void main(String[] args) {
        // ── Aritméticos e precedência ───────────────────────────────
        int a = 7, b = 2;
        System.out.println(a + " + " + b + " = " + (a + b));
        System.out.println(a + " / " + b + " = " + (a / b));    // 3 (divisão inteira)
        System.out.println(a + " % " + b + " = " + (a % b));    // 1 (resto)
        System.out.println(2 + 3 * 4);      // 14 — * antes de +
        System.out.println((2 + 3) * 4);    // 20 — parênteses mandam

        // ── Relacionais e lógicos ───────────────────────────────────
        int idade = 20;
        boolean temDocumento = false;
        System.out.println(idade >= 18 && temDocumento);   // false
        System.out.println(idade >= 18 || temDocumento);   // true
        System.out.println(!temDocumento);                 // true

        // ── if / else if / else ─────────────────────────────────────
        int nota = 7;
        if (nota >= 7) {
            System.out.println("Aprovado");
        } else if (nota >= 5) {
            System.out.println("Recuperação");
        } else {
            System.out.println("Reprovado");
        }

        // ── switch clássico: cuidado com o break ────────────────────
        int dia = 3;
        switch (dia) {
            case 1:
                System.out.println("Domingo");
                break;
            case 2:
                System.out.println("Segunda");
                break;
            case 3:
                System.out.println("Terça");
                break;
            default:
                System.out.println("Dia inválido");
        }

        // ── switch moderno: sem break e pode devolver valor ─────────
        String nomeDoDia = switch (dia) {
            case 1 -> "Domingo";
            case 2 -> "Segunda";
            case 3 -> "Terça";
            default -> "Dia inválido";
        };
        System.out.println(nomeDoDia);

        // ── Ternário ────────────────────────────────────────────────
        String situacao = (nota >= 7) ? "Aprovado" : "Reprovado";
        System.out.println(situacao);
    }
}
