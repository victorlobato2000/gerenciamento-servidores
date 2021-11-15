package com.victorlobato.gerenciamento.servidor.service;

import com.victorlobato.gerenciamento.servidor.model.Servidor;

import java.io.IOException;
import java.util.Collection;

public interface ServidorService {

    Servidor criar(Servidor servidor);
    Servidor pingar(String enderecoIP) throws IOException;
    Collection<Servidor> listar(int limite);
    Servidor buscar(Long id);
    Servidor atualizar(Servidor servidor);
    Boolean deletar(Long id);


}
