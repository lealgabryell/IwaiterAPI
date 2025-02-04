package br.iwaiter.modulos.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public String save(ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = new ClienteEntity(clienteDTO);
        try {
            this.clienteRepository.save(clienteEntity);
            return clienteDTO.getNome() + " salvo com sucesso";
        }catch(Exception e) {
            return e.getMessage();
        }
    }
}
