package br.iwaiter.modulos.cliente;

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
        String c = this.clienteService.save(clienteDTO);
        if(c.contains("salvo com sucesso!")){
        return new ResponseEntity<>(c, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(c, HttpStatus.BAD_REQUEST);
        }
    }

}
