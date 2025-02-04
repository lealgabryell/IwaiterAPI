package br.iwaiter.modulos.cliente;

import br.iwaiter.exceptions.cliente.ClienteNotFoundException;
import br.iwaiter.exceptions.cliente.ClienteNotSavedException;
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
            if (!this.checaIdade(clienteDTO)) {
                throw new ClienteNotSavedException("Cliente menor de idade!");
            } else if (this.checaCpf(clienteDTO)) {
                throw new ClienteNotSavedException("Cpf ja esta cadastrado!");
            } else {
                this.clienteRepository.save(clienteEntity);
                return clienteDTO.getNome() + " salvo com sucesso";
            }
        } catch (Exception e) {
            throw new ClienteNotSavedException("Erro ao salvar o cliente: " + e.getMessage(), e);
        }
    }

    public List<ClienteEntity> findAll() {
        try {
            List<ClienteEntity> listaClientes = this.clienteRepository.findAll();
            if (!listaClientes.isEmpty()) {
                return listaClientes;
            } else {
                throw new ClienteNotFoundException("Nenhum cliente encontrado!");
            }
        } catch (Exception e) {
            throw new ClienteNotFoundException("Erro ao buscar clientes...", e);
        }
    }

    public ClienteEntity findById(Long id) {
        try {
            return this.clienteRepository.findById(id).get();
        } catch (ClienteNotFoundException e) {
            throw new ClienteNotFoundException("Cliente nao encontrado", e);
        } catch (Exception e) {
            throw new ClienteNotFoundException("Erro ao buscar cliente", e);
        }
    }

    public String deleteById(Long id) {
        try {
            if (this.checaId(id)) {
                this.clienteRepository.deleteById(id);
                return "Cliente deletado com sucesso";
            } else {
                throw new ClienteNotFoundException("Cliente nao encontrado");
            }
        } catch (ClienteNotFoundException e) {
            throw new ClienteNotFoundException("Cliente nao encontrado", e);
        } catch (Exception e) {
            throw new ClienteNotFoundException("Erro ao deletar cliente", e);
        }

    }

    private boolean checaId(Long id) {
        return this.clienteRepository.existsById(id);
    }

    private boolean checaCpf(ClienteDTO clienteDTO) {
        return this.clienteRepository.existsByCpf(clienteDTO.getCpf());
    }

    private boolean checaIdade(ClienteDTO clienteDTO) {
        return clienteDTO.getIdade() >= 18;
    }
}
