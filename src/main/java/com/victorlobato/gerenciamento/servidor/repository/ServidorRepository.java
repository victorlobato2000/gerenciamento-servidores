package com.victorlobato.gerenciamento.servidor.repository;

import com.victorlobato.gerenciamento.servidor.model.Servidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor, Long> {
    Servidor findByEnderecoIP(String enderecoIP);
}
