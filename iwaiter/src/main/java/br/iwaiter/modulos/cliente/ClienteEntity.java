package br.iwaiter.modulos.cliente;

import br.iwaiter.modulos.pedido.PedidoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cliente")
    private List<PedidoEntity> pedidos;

    private String nome;
    @Column(unique = true)
    private String cpf;
    private int idade;

    public ClienteEntity(ClienteDTO clienteDTO) {
        this.nome = clienteDTO.getNome();
        this.cpf = clienteDTO.getCpf();
        this.idade = clienteDTO.getIdade();
    }
}
