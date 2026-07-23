/*
 * Aula 05 — a primeira classe. Repare: NÃO tem main; ela não é um programa,
 * é um modelo. Quem executa é a Escola.java, na mesma pasta.
 */
public class Aluno {

    // ATRIBUTOS: o que o aluno SABE (o estado dele)
    String nome;
    String matricula;
    double[] notas = new double[3];

    // CONSTRUTOR: mesmo nome da classe, SEM tipo de retorno
    public Aluno(String nome, String matricula) {
        this.nome = nome;                  // this.nome = atributo; nome = parâmetro
        this.matricula = matricula;
    }

    // MÉTODOS: o que o aluno SABE FAZER (o comportamento dele)
    double calcularMedia() {
        double soma = 0;
        for (double nota : notas) {        // usa as notas do PRÓPRIO objeto
            soma += nota;
        }
        return soma / notas.length;
    }

    boolean estaAprovado() {
        return calcularMedia() >= 7;       // um método pode usar outro da classe
    }

    void imprimirBoletim() {
        System.out.printf("%s (%s) - média %.2f - %s%n",
                nome, matricula, calcularMedia(),
                estaAprovado() ? "APROVADO" : "REPROVADO");
    }
}
