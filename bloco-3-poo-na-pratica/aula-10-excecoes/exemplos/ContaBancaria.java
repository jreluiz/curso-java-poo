/*
 * Aula 10 — a classe garante suas regras lançando exceções.
 * Repare: nenhum System.out aqui. Quem avisa o usuário é quem chamou.
 */
public class ContaBancaria {

    private final String titular;
    private double saldo;

    public ContaBancaria(String titular, double saldoInicial) {
        if (titular == null || titular.isBlank()) {
            throw new IllegalArgumentException("Titular é obrigatório.");
        }
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Depósito deve ser positivo.");
        }
        this.saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do saque deve ser positivo.");
        }
        if (valor > saldo) {
            throw new SaldoInsuficienteException(saldo, valor);
        }
        this.saldo -= valor;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return String.format("%s - saldo R$ %.2f", titular, saldo);
    }
}
