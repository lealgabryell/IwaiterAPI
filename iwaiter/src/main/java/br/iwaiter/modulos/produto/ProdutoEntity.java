package br.iwaiter.modulos.produto;

import br.iwaiter.modulos.pedido.PedidoEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private int quantidade;
    private double preco;

    public ProdutoEntity(ProdutoDTO produtoDTO) {
        this.descricao = produtoDTO.getDescricao();
        this.quantidade = produtoDTO.getQuantidade();
        this.preco = produtoDTO.getPreco();
    }
}
