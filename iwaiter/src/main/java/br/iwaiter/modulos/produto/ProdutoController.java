package br.iwaiter.modulos.produto;

import br.iwaiter.exceptions.produto.ProdutoNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/save")
    public ResponseEntity<String> save(ProdutoDTO produtoDTO) {
        try{
            String retorno = this.produtoService.save(produtoDTO);
            return new ResponseEntity<>(retorno, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProdutoEntity>> findAll() {
        try{
            List<ProdutoEntity> produtos = this.produtoService.findAll();
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        }catch(ProdutoNotSavedException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ProdutoEntity> findById(@PathVariable Long id) {
        try{
            ProdutoEntity produto = this.produtoService.findById(id);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } catch(ProdutoNotSavedException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try{
            String retorno = this.produtoService.deleteById(id);
            return new ResponseEntity<>(retorno, HttpStatus.CREATED);
        } catch (ProdutoNotSavedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
