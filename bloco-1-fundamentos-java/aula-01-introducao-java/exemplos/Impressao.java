/*
 * Aula 01 — println x print, comentários e caracteres especiais.
 * Rode com: java Impressao.java
 */
public class Impressao {
    public static void main(String[] args) {
        System.out.println("Primeira linha");   // imprime e PULA linha
        System.out.print("Sem pular... ");      // imprime e FICA na mesma linha
        System.out.print("continua aqui");
        System.out.println();                   // só pula a linha

        // Comentário de uma linha: o compilador ignora

        /* Comentário
           de várias linhas */

        System.out.println("Fim");

        // Caracteres especiais usam a barra invertida
        System.out.println("Quebra\nde linha");
        System.out.println("Coluna1\tColuna2");
        System.out.println("Ele disse \"oi\"");
        System.out.println("Barra invertida: \\");
    }
}
