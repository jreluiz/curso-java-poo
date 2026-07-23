/*
 * Aula 16 — os quatro pilares da POO num arquivo só.
 * Rode com: java PilaresCompletos.java
 */
import java.util.List;

// ABSTRAÇÃO: define o que é um funcionário, sem dizer de que tipo
abstract class Funcionario {

    // ENCAPSULAMENTO: estado protegido, acesso controlado
    private final String nome;
    private double salarioBase;

    public Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        setSalarioBase(salarioBase);            // valida já no nascimento
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase < 1518) {
            throw new IllegalArgumentException("Abaixo do salário mínimo: " + salarioBase);
        }
        this.salarioBase = salarioBase;
    }

    protected double getSalarioBase() { return salarioBase; }
    public String getNome() { return nome; }

    public abstract double calcularSalario();   // cada tipo calcula do seu jeito
}

// HERANÇA: reaproveita nome, salário e validação
class Gerente extends Funcionario {
    public Gerente(String nome, double salarioBase) {
        super(nome, salarioBase);
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() * 1.20;
    }
}

class Vendedor extends Funcionario {
    private final double vendasDoMes;

    public Vendedor(String nome, double salarioBase, double vendasDoMes) {
        super(nome, salarioBase);
        this.vendasDoMes = vendasDoMes;
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + vendasDoMes * 0.05;
    }
}

public class PilaresCompletos {
    public static void main(String[] args) {

        // POLIMORFISMO: um laço, comportamentos diferentes
        List<Funcionario> folha = List.of(
                new Gerente("Ana", 8000),
                new Vendedor("Léo", 2000, 30000));

        folha.forEach(f -> System.out.printf("%s: R$ %.2f%n", f.getNome(), f.calcularSalario()));

        double total = folha.stream()
                .mapToDouble(Funcionario::calcularSalario)
                .sum();
        System.out.printf("Total da folha: R$ %.2f%n", total);

        String maisBemPago = folha.stream()
                .max(java.util.Comparator.comparingDouble(Funcionario::calcularSalario))
                .map(Funcionario::getNome)
                .orElse("(ninguém)");
        System.out.println("Mais bem pago: " + maisBemPago);

        // O encapsulamento defendendo a classe
        try {
            new Gerente("Duda", 500);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejeitado: " + e.getMessage());
        }
    }
}
