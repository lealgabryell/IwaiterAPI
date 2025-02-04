package br.iwaiter.modulos.cliente;

import br.iwaiter.exceptions.ClienteNotFoundException;
import br.iwaiter.exceptions.ClienteNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public String save(ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = new ClienteEntity(clienteDTO);
        try {
            if (!this.checaIdade(clienteDTO) ) {
                throw new ClienteNotSavedException("Cliente menor de idade!");
            } else if(this.checaCpf(clienteDTO)) {
                throw new ClienteNotSavedException("Cpf ja esta cadastrado!");
            }else {
                this.clienteRepository.save(clienteEntity);
                return clienteDTO.getNome() + " salvo com sucesso";
            }
        } catch (Exception e) {
            throw new ClienteNotSavedException("Erro ao salvar o cliente: " + e.getMessage(), e);
        }
    }

    public List<ClienteEntity> findAll(Long id) {
        try {
            List<ClienteEntity> lista = this.clienteRepository.findAll();
            if (!lista.isEmpty()) {
                return lista;
            } else {
                throw new ClienteNotFoundException("Empty list!");
            }
        } catch (Exception e) {
            throw new ClienteNotFoundException("Erro ao buscar clientes...", e);
        }
    }

    private boolean checaCpf(ClienteDTO clienteDTO) {
        return this.clienteRepository.existsByCpf(clienteDTO.getCpf());
    }

    private boolean checaIdade(ClienteDTO clienteDTO) {
        return clienteDTO.getIdade() >= 18;
    }
}
