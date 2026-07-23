/*
 * Aula 10 — try/catch/finally e a divisão de responsabilidades:
 * a classe decide o que é inválido; aqui decidimos como avisar.
 * Rode com: java Caixa.java
 */
public class Caixa {
    public static void main(String[] args) {

        ContaBancaria conta = new ContaBancaria("Ana", 300);

        System.out.println("--- vários catch, do específico para o genérico ---");
        try {
            int[] numeros = {1, 2, 3};
            System.out.println(numeros[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Índice inválido: " + e.getMessage());
        } catch (Exception e) {                 // rede de segurança: SEMPRE por último
            System.out.println("Erro inesperado: " + e.getMessage());
        } finally {
            System.out.println("finally executa sempre.");
        }

        System.out.println("--- saque válido ---");
        tentarSacar(conta, 100);

        System.out.println("--- valor negativo ---");
        tentarSacar(conta, -50);

        System.out.println("--- acima do saldo: exceção personalizada ---");
        tentarSacar(conta, 500);

        System.out.println("--- dois tipos, um catch só ---");
        try {
            conta.depositar(-10);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível depositar: " + e.getMessage());
        }

        System.out.println("Estado final: " + conta);
        System.out.println("O programa continuou rodando até o fim. ✅");
    }

    private static void tentarSacar(ContaBancaria conta, double valor) {
        try {
            conta.sacar(valor);
            System.out.printf("Saque de R$ %.2f realizado. Saldo: R$ %.2f%n",
                    valor, conta.getSaldo());
        } catch (SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
            System.out.printf("Faltam R$ %.2f para completar o saque.%n", e.getFalta());
        } catch (IllegalArgumentException e) {
            System.out.println("Valor inválido: " + e.getMessage());
        }
    }
}
