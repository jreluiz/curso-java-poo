/*
 * Aula 06 — a classe protegendo o próprio estado.
 * Rode com: java Loja.java
 */
public class Loja {
    public static void main(String[] args) {

        Produto caderno = new Produto("Caderno", 12.90, 50);
        Produto caneta = new Produto("Caneta", 3.50);       // estoque começa em 0

        System.out.println(caderno);      // toString() é chamado sozinho
        System.out.println(caneta);

        // caderno.preco = 0.01;   ← descomente: preco has private access in Produto

        System.out.println("--- validações ---");
        caderno.setPreco(-5);             // rejeitado, preço se mantém
        caneta.adicionarEstoque(0);       // rejeitado
        caneta.adicionarEstoque(100);     // aceito

        System.out.println("--- vendas ---");
        System.out.println(caderno.vender(10));    // true
        System.out.println(caderno.vender(999));   // false + aviso
        System.out.println(caderno);

        System.out.println("--- static: pertence à classe ---");
        System.out.println("Produtos cadastrados: " + Produto.getTotalCadastrados());
        System.out.println("Desconto máximo: " + Produto.DESCONTO_MAXIMO);
    }
}
