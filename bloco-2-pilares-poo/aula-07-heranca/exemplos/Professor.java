/*
 * Aula 07 — outra subclasse de Pessoa, com dados e comportamento próprios.
 */
public class Professor extends Pessoa {

    private double salario;

    public Professor(String nome, String cpf, int idade, double salario) {
        super(nome, cpf, idade);
        this.salario = salario;
    }

    public double calcularSalarioAnual() {
        return salario * 13;      // com 13º
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" - salário anual R$ %.2f", calcularSalarioAnual());
    }
}
