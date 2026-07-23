/*
 * Aula 08 — classe ABSTRATA: um molde que não vira objeto.
 * "Funcionário genérico" não existe; cada cargo calcula o salário do seu jeito.
 */
public abstract class Funcionario {

    protected String nome;
    protected double salarioBase;

    public Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    /** Método ABSTRATO: sem corpo. Toda subclasse concreta é obrigada a implementar. */
    public abstract double calcularSalario();

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        // chama calcularSalario() sem saber implementá-lo:
        // na execução, quem responde é o objeto real
        return String.format("%s: R$ %.2f", nome, calcularSalario());
    }
}
