package biblioteca.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/** Registra quem levou o quê, quando, e até quando pode ficar. */
public class Emprestimo {

    private static final double MULTA_POR_DIA = 1.0;

    private final ItemAcervo item;
    private final Usuario usuario;
    private final LocalDate dataRetirada;
    private final LocalDate dataPrevista;
    private LocalDate dataDevolucao;      // null enquanto não devolvido

    public Emprestimo(ItemAcervo item, Usuario usuario) {
        this.item = item;
        this.usuario = usuario;
        this.dataRetirada = LocalDate.now();
        this.dataPrevista = dataRetirada.plusDays(usuario.getTipo().getDiasEmprestimo());
    }

    public boolean estaAtivo() {
        return dataDevolucao == null;
    }

    /** @return o valor da multa apurada na devolução (0 se estiver em dia) */
    public double registrarDevolucao() {
        this.dataDevolucao = LocalDate.now();
        return calcularMulta();
    }

    public double calcularMulta() {
        LocalDate referencia = (dataDevolucao != null) ? dataDevolucao : LocalDate.now();
        if (!referencia.isAfter(dataPrevista)) {
            return 0;
        }
        long diasAtraso = ChronoUnit.DAYS.between(dataPrevista, referencia);
        return diasAtraso * MULTA_POR_DIA;
    }

    public ItemAcervo getItem() {
        return item;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    @Override
    public String toString() {
        return String.format("%s → %s | retirada %s | devolver até %s | %s",
                item.getTitulo(), usuario.getNome(), dataRetirada, dataPrevista,
                estaAtivo() ? "em aberto" : "devolvido em " + dataDevolucao);
    }
}
