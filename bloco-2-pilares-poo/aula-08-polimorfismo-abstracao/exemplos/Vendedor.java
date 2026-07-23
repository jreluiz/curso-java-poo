public class Vendedor extends Funcionario {

    private double vendasDoMes;

    public Vendedor(String nome, double salarioBase, double vendasDoMes) {
        super(nome, salarioBase);
        this.vendasDoMes = vendasDoMes;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + vendasDoMes * 0.05;    // 5% de comissão
    }

    public double getVendasDoMes() {
        return vendasDoMes;
    }
}
