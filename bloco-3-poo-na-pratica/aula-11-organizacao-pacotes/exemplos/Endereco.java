/*
 * Aula 11 — record: dados imutáveis sem cerimônia.
 * Esta única linha gera construtor, acessores, equals, hashCode e toString.
 * O bloco abaixo é o "construtor compacto", onde entra a validação.
 */
public record Endereco(String rua, String cidade, String uf) {

    public Endereco {
        if (uf == null || uf.length() != 2) {
            throw new IllegalArgumentException("UF deve ter 2 letras: " + uf);
        }
    }
}
