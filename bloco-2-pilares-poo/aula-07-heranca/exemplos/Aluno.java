/*
 * Aula 07 — subclasse: "todo Aluno é uma Pessoa".
 * Mostra super(...), sobrescrita, equals() e hashCode().
 */
import java.util.Objects;

public class Aluno extends Pessoa {

    private String matricula;
    private double[] notas = new double[3];

    public Aluno(String nome, String cpf, int idade, String matricula) {
        super(nome, cpf, idade);        // PRIMEIRA linha: constrói a parte Pessoa
        System.out.println("2 - construtor de Aluno");
        this.matricula = matricula;
    }

    public void lancarNotas(double n1, double n2, double n3) {
        notas[0] = n1;
        notas[1] = n2;
        notas[2] = n3;
    }

    public double calcularMedia() {
        double soma = 0;
        for (double n : notas) soma += n;
        return soma / notas.length;
    }

    @Override
    public String toString() {
        // super.toString() reaproveita a versão da superclasse
        return super.toString() + " - matrícula " + matricula
                + " - média " + String.format("%.2f", calcularMedia());
    }

    // Dois alunos são "o mesmo" quando têm a mesma matrícula
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Aluno)) return false;
        Aluno outro = (Aluno) obj;
        return this.matricula.equals(outro.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);   // sempre com os MESMOS atributos do equals
    }
}
