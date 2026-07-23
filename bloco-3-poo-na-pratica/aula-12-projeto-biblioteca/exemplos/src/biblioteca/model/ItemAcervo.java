package biblioteca.model;

import java.util.Objects;

/**
 * Item genérico do acervo. É abstrata porque "item genérico" não existe na
 * prateleira: cada tipo concreto precisa saber se descrever.
 */
public abstract class ItemAcervo implements Emprestavel {

    protected final String codigo;
    protected final String titulo;
    protected final int ano;
    protected boolean disponivel = true;

    protected ItemAcervo(String codigo, String titulo, int ano) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código é obrigatório.");
        }
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório.");
        }
        this.codigo = codigo;
        this.titulo = titulo;
        this.ano = ano;
    }

    /** Cada tipo de item se descreve à sua maneira. */
    public abstract String descricao();

    @Override
    public void emprestar() {
        this.disponivel = false;
    }

    @Override
    public void devolver() {
        this.disponivel = true;
    }

    @Override
    public boolean estaDisponivel() {
        return disponivel;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    /** Dois itens são o mesmo quando têm o mesmo código. */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ItemAcervo outro)) return false;
        return codigo.equalsIgnoreCase(outro.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s %s", codigo, descricao(),
                disponivel ? "✅ disponível" : "❌ emprestado");
    }
}
