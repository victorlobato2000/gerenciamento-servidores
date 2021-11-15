package com.victorlobato.gerenciamento.servidor.service.implementation;

import com.victorlobato.gerenciamento.servidor.enumaration.Status;
import com.victorlobato.gerenciamento.servidor.model.Servidor;
import com.victorlobato.gerenciamento.servidor.repository.ServidorRepository;
import com.victorlobato.gerenciamento.servidor.service.ServidorService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;



@Slf4j
@Service
public class ServidorServiceImpl implements ServidorService {
    @Autowired
    private ServidorRepository servidorRepositorio;

    @Override
    public Servidor criar(Servidor servidor) {
        log.info("Criando um novo servidor: {}", servidor.getNome());
        servidor.setImagemUrl(setServidorImagemUrl());
        return servidorRepositorio.save(servidor);
    }

    @Override
    public Servidor pingar(String enderecoIP) throws IOException {
        log.info("Pingando o endereco IP: {}", enderecoIP);
        Servidor servidor = servidorRepositorio.findByEnderecoIP(enderecoIP);
        InetAddress endereco = InetAddress.getByName(enderecoIP); // pegar o enderecoIP
        servidor.setStatus(endereco.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN); // verificar se o servidor é encontrado
        servidorRepositorio.save(servidor);
        return servidor;
    }

    @Override
    public Collection<Servidor> listar(int limite) {
        log.info("Pegando todos os servidores");
        return servidorRepositorio.findAll(PageRequest.of(0,limite)).toList();
    }

    @Override
    public Servidor buscar(Long id) {
        log.info("Buscando o servidor por id: {}", id);
        return servidorRepositorio.findById(id).get();
    }

    @Override
    public Servidor atualizar(Servidor servidor) {
        log.info("Atualizando o servidor: {}", servidor.getNome());
        return servidorRepositorio.save(servidor);
    }

    @Override
    public Boolean deletar(Long id) {
        log.info("Deletando o servidor por id : {}", id);
        servidorRepositorio.deleteById(id);
        return Boolean.TRUE;
    }


    private String setServidorImagemUrl() {
        String[] nomeImagens = { "servidor1.png", "servidor2.png" , "servidor3.png", "servidor4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/servidor/imagens/" + nomeImagens[new Random().nextInt(4)]).toUriString();
    }

}
