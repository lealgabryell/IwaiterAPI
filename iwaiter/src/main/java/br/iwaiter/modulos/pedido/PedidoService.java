package br.iwaiter.modulos.pedido;

import br.iwaiter.exceptions.cliente.ClienteNotFoundException;
import br.iwaiter.exceptions.pedido.PedidoNotSavedException;
import br.iwaiter.exceptions.produto.ProdutoNotFoundException;
import br.iwaiter.modulos.cliente.ClienteEntity;
import br.iwaiter.modulos.cliente.ClienteService;
import br.iwaiter.modulos.pagamento.PagamentoEntity;
import br.iwaiter.modulos.pagamento.PagamentoRepository;
import br.iwaiter.modulos.pedido.itemPedido.ItemPedido;
import br.iwaiter.modulos.pedido.itemPedido.ItemPedidoDTO;
import br.iwaiter.modulos.produto.ProdutoEntity;
import br.iwaiter.modulos.produto.ProdutoRepository;
import br.iwaiter.modulos.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public PedidoEntity save(PedidoDTO pedidoDTO) throws PedidoNotSavedException {
        try {
            ClienteEntity cliente = this.clienteService.findById(pedidoDTO.getClienteId());
            if (cliente != null) {
                PedidoEntity pedido = new PedidoEntity();

                pedido.setCliente(cliente);
                pedido.setItens(new ArrayList<>());

                BigDecimal total = BigDecimal.ZERO;

                for (ItemPedidoDTO item : pedidoDTO.getItens()) {
                    ProdutoEntity produto = this.produtoService.findById(item.getProdutoId());
                    if (produto == null) {
                        throw new ProdutoNotFoundException("Produto nao encontrado!");
                    } else if (produto.getQuantidade() < item.getQuantidade()) {
                        throw new ProdutoNotFoundException("Quantidade do produto nao encontrada!");
                    } else {
                        produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
                        this.produtoRepository.save(produto); //atualiza qauantidade do produto em estoque

                        BigDecimal subtotal = BigDecimal.valueOf(produto.getPreco()) // Calcular subtotal
                                .multiply(BigDecimal.valueOf(item.getQuantidade()));

                        ItemPedido itemPedido = new ItemPedido(); // Montar ItemPedido
                        itemPedido.setProduto(produto);
                        itemPedido.setQuantidade(item.getQuantidade());
                        itemPedido.setSubtotal(subtotal);
                        itemPedido.setPedido(pedido);

                        pedido.getItens().add(itemPedido);
                        total = total.add(subtotal);
                    }
                }
                PagamentoEntity pagamento = new PagamentoEntity(); //Pagamento inicializado, sempre com status FALSE

                pedido.setTotal(total); // 4) Setar total e salvar pedido
                pedido.setPagamento(pagamento); // 5) Setar status do Pagamento no Pedido
                pagamento.setPedido(pedido); // 6) Setar Pedido no objeto Pagamento

                this.pagamentoRepository.save(pagamento);//Salva pagamento no bd

                this.pedidoRepository.save(pedido);

                return pedido;
            } else {
                throw new ClienteNotFoundException("Cliente nao encontrado!");
            }
        } catch (Exception e) {
            throw new PedidoNotSavedException("Erro o salvar pedido", e);
        }
    }
}
