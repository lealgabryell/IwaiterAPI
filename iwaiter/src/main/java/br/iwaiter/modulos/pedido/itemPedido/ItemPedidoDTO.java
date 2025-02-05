package br.iwaiter.modulos.pedido.itemPedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {
    private Long produtoId;
    private Integer quantidade;
}
