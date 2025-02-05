package br.iwaiter.modulos.produto;

import br.iwaiter.exceptions.produto.ProdutoNotFoundException;
import br.iwaiter.exceptions.produto.ProdutoNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProdutoService {

    Logger logger = Logger.getLogger(ProdutoService.class.getName());

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

    public ProdutoEntity findById(Long id) {
        try {
            if (this.checaId(id)) {
                return this.produtoRepository.findById(id).get();
            } else {
                logger.info("Entrei no else");
                throw new ProdutoNotFoundException("Produto nao encontrado...");
            }
        } catch (ProdutoNotFoundException e) {
            throw new ProdutoNotFoundException("Produto nao encontrado...", e);
        } catch (Exception e) {
            throw new ProdutoNotFoundException(e.getMessage(), e);
        }
    }

    public String deleteById(Long id) {
        try {
            if (this.checaId(id)) {
                this.produtoRepository.deleteById(id);
                return "Produto removido com sucesso!";
            } else {
                throw new ProdutoNotFoundException("Produto nao encontrado...");
            }
        } catch(Exception e){
            throw new ProdutoNotFoundException(e.getMessage(), e);
        }
    }

    private boolean checaId(Long id) {
        return this.produtoRepository.existsById(id);
    }
}
