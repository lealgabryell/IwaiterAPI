package br.iwaiter.modulos.cliente;

import br.iwaiter.exceptions.cliente.ClienteNotFoundException;
import br.iwaiter.exceptions.cliente.ClienteNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ClienteDTO clienteDTO) {
        try {
            String retorno = this.clienteService.save(clienteDTO);
            return new ResponseEntity<>(retorno, HttpStatus.CREATED);
        } catch (ClienteNotSavedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ClienteEntity>> findAll() {
        try {
            List<ClienteEntity> listaClientes = this.clienteService.findAll();
            return new ResponseEntity<>(listaClientes, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ClienteEntity> findById(@PathVariable Long id) {
        try{
            ClienteEntity clienteRetornado = this.clienteService.findById(id);
            return new ResponseEntity<>(clienteRetornado, HttpStatus.OK);
        }catch(ClienteNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try{
            String retorno = this.clienteService.deleteById(id);
            return new ResponseEntity<>(retorno, HttpStatus.OK);
        }catch(ClienteNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
