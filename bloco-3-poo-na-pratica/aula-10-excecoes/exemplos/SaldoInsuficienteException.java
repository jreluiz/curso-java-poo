/*
 * Aula 10 — exceção personalizada: o nome documenta o erro
 * e ela ainda carrega dados úteis para quem trata.
 */
public class SaldoInsuficienteException extends RuntimeException {

    private final double falta;

    public SaldoInsuficienteException(double saldo, double valorSolicitado) {
        super(String.format("Saldo insuficiente: disponível R$ %.2f, solicitado R$ %.2f",
                saldo, valorSolicitado));
        this.falta = valorSolicitado - saldo;
    }

    public double getFalta() {
        return falta;
    }
}
