/*
 * Aula 06 — encapsulamento: atributos privados, setters que validam,
 * sobrecarga de construtores, static e toString().
 */
public class Produto {

    // Atributo e constante da CLASSE (existem uma vez só, não por objeto)
    private static int totalCadastrados = 0;
    public static final double DESCONTO_MAXIMO = 0.30;

    private String nome;
    private double preco;
    private int estoque;

    // Construtor principal
    public Produto(String nome, double preco, int estoque) {
        this.nome = nome;
        setPreco(preco);            // reaproveita a validação já no nascimento
        this.estoque = Math.max(estoque, 0);
        totalCadastrados++;
    }

    // Versão curta: delega para o principal (this(...) precisa ser a 1ª linha)
    public Produto(String nome, double preco) {
        this(nome, preco, 0);
    }

    // Getter sem setter: o estoque só muda por operações do negócio
    public int getEstoque() {
        return estoque;
    }

    public double getPreco() {
        return preco;
    }

    // Setter que DEFENDE a classe
    public void setPreco(double preco) {
        if (preco <= 0) {
            System.out.println("Preço inválido: " + preco + ". Mantido " + this.preco);
            return;                 // sai sem alterar nada
        }
        this.preco = preco;
    }

    // Operações do negócio — nem tudo é get/set
    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser positiva.");
            return;
        }
        this.estoque += quantidade;
    }

    public boolean vender(int quantidade) {
        if (quantidade > estoque) {
            System.out.println("Estoque insuficiente de " + nome + ". Disponível: " + estoque);
            return false;
        }
        this.estoque -= quantidade;
        return true;
    }

    public static int getTotalCadastrados() {
        return totalCadastrados;
    }

    @Override
    public String toString() {
        return String.format("%s - R$ %.2f (%d em estoque)", nome, preco, estoque);
    }
}
