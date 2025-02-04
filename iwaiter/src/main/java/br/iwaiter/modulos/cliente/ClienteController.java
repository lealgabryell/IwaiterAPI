package br.iwaiter.modulos.cliente;

import br.iwaiter.exceptions.ClienteNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ClienteDTO clienteDTO) {
        try {
            String c = this.clienteService.save(clienteDTO);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (ClienteNotSavedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
