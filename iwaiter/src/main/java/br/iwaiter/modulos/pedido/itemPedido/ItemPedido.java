package br.iwaiter.modulos.pedido.itemPedido;

import br.iwaiter.modulos.pedido.PedidoEntity;
import br.iwaiter.modulos.produto.ProdutoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    private Integer quantidade;

    private BigDecimal subtotal;

    public ItemPedido(PedidoEntity pedido, ProdutoEntity produto, Integer quantidade, BigDecimal subtotal) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
    }
}