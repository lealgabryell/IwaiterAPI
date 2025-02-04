package br.iwaiter.modulos.produto;

import br.iwaiter.exceptions.produto.ProdutoNotFoundException;
import br.iwaiter.exceptions.produto.ProdutoNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public String save(ProdutoDTO produtoDTO) {
        try {
            ProdutoEntity produto = new ProdutoEntity(produtoDTO);
            this.produtoRepository.save(produto);
            return produtoDTO.getDescricao() + " salvo com sucesso!";
        } catch (ProdutoNotSavedException e) {
            throw new ProdutoNotSavedException("Produto nao foi salvo...", e);
        } catch (Exception e) {
            throw new ProdutoNotSavedException(e.getMessage(), e);
        }
    }

    public List<ProdutoEntity> findAll() {
        try {
            List<ProdutoEntity> produtos = this.produtoRepository.findAll();
            if (!produtos.isEmpty()) {
                return produtos;
            } else {
                throw new ProdutoNotFoundException("Produto nao encontrado...");
            }
        } catch (Exception e) {
            throw new ProdutoNotFoundException(e.getMessage(), e);
        }
    }
}
