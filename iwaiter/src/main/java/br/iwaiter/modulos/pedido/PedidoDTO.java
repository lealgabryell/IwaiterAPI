package br.iwaiter.modulos.pedido;
import br.iwaiter.modulos.pedido.itemPedido.ItemPedidoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private List<ItemPedidoDTO> itens = new ArrayList<>();

    private Long clienteId;
}
