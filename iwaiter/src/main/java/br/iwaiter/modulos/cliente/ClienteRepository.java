package br.iwaiter.modulos.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    public boolean existsByCpf(String cpf);
}
