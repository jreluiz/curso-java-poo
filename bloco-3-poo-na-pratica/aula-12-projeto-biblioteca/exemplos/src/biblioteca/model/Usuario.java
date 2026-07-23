package biblioteca.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {

    private final String id;
    private final String nome;
    private final TipoUsuario tipo;
    private final List<ItemAcervo> itensEmMaos = new ArrayList<>();

    public Usuario(String id, String nome, TipoUsuario tipo) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id é obrigatório.");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        this.id = id;
        this.nome = nome;
        this.tipo = Objects.requireNonNull(tipo, "Tipo é obrigatório.");
    }

    /** Regra que é do usuário: ele sabe se ainda pode levar item. */
    public boolean podePegarMais() {
        return itensEmMaos.size() < tipo.getLimiteItens();
    }

    public void registrarRetirada(ItemAcervo item) {
        itensEmMaos.add(item);
    }

    public void registrarDevolucao(ItemAcervo item) {
        itensEmMaos.remove(item);
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    /** Cópia: ninguém altera a lista interna por fora. */
    public List<ItemAcervo> getItensEmMaos() {
        return new ArrayList<>(itensEmMaos);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Usuario outro)) return false;
        return id.equalsIgnoreCase(outro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %s) - %d/%d itens",
                nome, id, tipo, itensEmMaos.size(), tipo.getLimiteItens());
    }
}
