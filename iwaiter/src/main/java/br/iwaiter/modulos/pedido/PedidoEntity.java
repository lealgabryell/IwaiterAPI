package br.iwaiter.modulos.pedido;

import br.iwaiter.modulos.cliente.ClienteEntity;
import br.iwaiter.modulos.pagamento.PagamentoEntity;
import br.iwaiter.modulos.pedido.itemPedido.ItemPedido;
import br.iwaiter.modulos.produto.ProdutoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    @ManyToOne()
    private ClienteEntity cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pagamento")
    private PagamentoEntity pagamento;

    private BigDecimal total;

}
