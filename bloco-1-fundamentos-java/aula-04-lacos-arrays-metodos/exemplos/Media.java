/*
 * Aula 04 — métodos static: dando nome a um pedaço de lógica.
 * Rode com: java Media.java
 */
public class Media {

    // ┌ visível de fora
    // │      ┌ pertence à classe (não a um objeto)
    // │      │      ┌ tipo do retorno (void = não devolve nada)
    // │      │      │      ┌ nome (verbo, camelCase)   ┌ parâmetros
    public static double calcularMedia(double[] valores) {
        double soma = 0;
        for (double v : valores) {
            soma += v;
        }
        return soma / valores.length;
    }

    public static double encontrarMaior(double[] valores) {
        double maior = valores[0];
        for (double v : valores) {
            if (v > maior) {
                maior = v;
            }
        }
        return maior;
    }

    public static void imprimirSituacao(double media) {
        System.out.println(media >= 7 ? "Aprovado" : "Reprovado");
    }

    // Sobrecarga: mesmo nome, listas de parâmetros diferentes
    public static double somar(double a, double b) {
        return a + b;
    }

    public static double somar(double a, double b, double c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        double[] notas = {8.5, 7.0, 9.5, 6.0};

        double media = calcularMedia(notas);
        System.out.printf("Média: %.2f%n", media);
        System.out.printf("Maior: %.2f%n", encontrarMaior(notas));
        imprimirSituacao(media);

        System.out.println(somar(2, 3));       // usa a versão de 2 parâmetros
        System.out.println(somar(2, 3, 4));    // usa a de 3 — quem escolhe é o compilador
    }
}
