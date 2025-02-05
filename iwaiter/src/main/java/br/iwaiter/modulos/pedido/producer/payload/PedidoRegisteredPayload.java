package br.iwaiter.modulos.pedido.producer.payload;

import br.iwaiter.modulos.pedido.PedidoEntity;

public record PedidoRegisteredPayload(PedidoEntity pedido, int confirmationCode) {
}
