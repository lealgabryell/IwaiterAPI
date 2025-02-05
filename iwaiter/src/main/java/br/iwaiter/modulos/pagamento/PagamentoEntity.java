package br.iwaiter.modulos.pagamento;

import br.iwaiter.modulos.pedido.PedidoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
@Table(name = "pagamento")
public class PagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedido;

    private boolean status;

    public PagamentoEntity() {
        this.status = false;
    }
}
