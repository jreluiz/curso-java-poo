/*
 * Aula 11 — enum e record em ação.
 * Rode com: java Demo.java
 *
 * Obs.: os exemplos estão no pacote padrão para rodar com um comando só.
 * No projeto da Aula 12 eles ficam em biblioteca/model/, com a declaração
 * `package biblioteca.model;` na primeira linha de cada arquivo.
 */
public class Demo {
    public static void main(String[] args) {

        System.out.println("--- enum com atributos ---");
        TipoUsuario tipo = TipoUsuario.PROFESSOR;
        System.out.println(tipo + " pode levar " + tipo.getLimiteItens()
                + " itens por " + tipo.getDiasEmprestimo() + " dias");

        for (TipoUsuario t : TipoUsuario.values()) {          // todos os valores
            System.out.printf("  %-10s limite %2d | %2d dias%n",
                    t, t.getLimiteItens(), t.getDiasEmprestimo());
        }

        System.out.println("--- enum em comparação e switch ---");
        StatusPedido status = StatusPedido.ENVIADO;

        if (status == StatusPedido.ENVIADO) {                 // == é seguro em enum
            System.out.println("Pedido a caminho");
        }

        String mensagem = switch (status) {
            case AGUARDANDO -> "Aguardando pagamento";
            case PAGO -> "Pagamento confirmado";
            case ENVIADO -> "Saiu para entrega";
            case ENTREGUE -> "Finalizado";
            case CANCELADO -> "Cancelado";
        };
        System.out.println(mensagem);

        System.out.println("--- record: tudo de graça ---");
        Endereco e1 = new Endereco("Rua A, 100", "Vitória", "ES");
        Endereco e2 = new Endereco("Rua A, 100", "Vitória", "ES");

        System.out.println(e1);                    // toString gerado
        System.out.println(e1.cidade());           // acessor: cidade(), não getCidade()
        System.out.println(e1.equals(e2));         // true — equals gerado
        System.out.println(e1.hashCode() == e2.hashCode());   // true

        try {
            new Endereco("Rua B", "Serra", "ESPÍRITO SANTO");
        } catch (IllegalArgumentException ex) {
            System.out.println("Validação do construtor compacto: " + ex.getMessage());
        }
    }
}
