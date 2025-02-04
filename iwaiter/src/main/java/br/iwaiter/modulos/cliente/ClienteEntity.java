package br.iwaiter.modulos.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
