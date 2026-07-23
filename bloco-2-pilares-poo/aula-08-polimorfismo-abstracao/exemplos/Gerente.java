public class Gerente extends Funcionario {

    private static final double AUXILIO = 800.0;

    public Gerente(String nome, double salarioBase) {
        super(nome, salarioBase);
    }

    @Override
    public double calcularSalario() {
        return salarioBase * 1.20 + AUXILIO;
    }

    /** Método que só existe no Gerente — não é visível por uma variável Funcionario. */
    public void aprovarFerias(String nomeDoLiderado) {
        System.out.println(nome + " aprovou as férias de " + nomeDoLiderado);
    }
}
