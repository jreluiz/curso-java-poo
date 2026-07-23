/*
 * Aula 08 — polimorfismo, classe abstrata e interface funcionando juntos.
 * Rode com: java Demo.java
 */
public class Demo {

    /** Funciona com QUALQUER coisa emprestável — inclusive classes que ainda não existem. */
    public static void emprestarTodos(Emprestavel[] itens, String responsavel) {
        for (Emprestavel item : itens) {
            if (item.estaDisponivel()) {
                item.emprestar(responsavel);
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("--- polimorfismo: um laço, três cálculos ---");
        Funcionario[] folha = {
                new Gerente("Ana", 8000),
                new Vendedor("Léo", 2000, 30000),
                new Vendedor("Duda", 2000, 12000)
        };

        double total = 0;
        for (Funcionario f : folha) {
            System.out.println(f);              // toString() → calcularSalario() do objeto real
            total += f.calcularSalario();
        }
        System.out.printf("Total da folha: R$ %.2f%n", total);

        // Funcionario generico = new Funcionario("X", 1000);
        //   ↑ descomente: Funcionario is abstract; cannot be instantiated

        System.out.println("--- o tipo da variável limita o que se pode chamar ---");
        Funcionario f = new Gerente("Ana", 8000);
        // f.aprovarFerias("Léo");   ← descomente: cannot find symbol

        if (f instanceof Gerente g) {           // testa E já converte
            g.aprovarFerias("Léo");
        }

        System.out.println("--- interface: capacidade sem parentesco ---");
        Emprestavel[] itens = {
                new Livro("Dom Casmurro"),
                new Projetor("Epson X1", 3000)
        };
        emprestarTodos(itens, "Prof. Carlos");
        emprestarTodos(itens, "Prof. Marina");  // nada acontece: já estão emprestados
    }
}
