package br.iwaiter.modulos.produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private String descricao;
    private int quantidade;
    private double preco;
}
