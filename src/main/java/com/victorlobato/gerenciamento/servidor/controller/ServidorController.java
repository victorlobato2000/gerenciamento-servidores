package com.victorlobato.gerenciamento.servidor.controller;

import com.victorlobato.gerenciamento.servidor.enumaration.Status;
import com.victorlobato.gerenciamento.servidor.model.Response;
import com.victorlobato.gerenciamento.servidor.model.Servidor;
import com.victorlobato.gerenciamento.servidor.service.implementation.ServidorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/servidor")
public class ServidorController {
    @Autowired
    private ServidorServiceImpl servidorService;

    @GetMapping("/listar")
    public ResponseEntity<Response> listarServidores(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .dados(Map.of("servidores", servidorService.listar(30)))
                        .mensagem("Servidores retornados")
                        .status(OK)
                        .codigoStatus(OK.value())
                        .build()
        );
    }


    @GetMapping("/ping/{enderecoIP}")
    public ResponseEntity<Response> pingarServidor(@PathVariable("enderecoIP") String enderecoIP) throws IOException {
        Servidor servidor = servidorService.pingar(enderecoIP);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .dados(Map.of("servidor", servidor))
                        .mensagem(servidor.getStatus() == Status.SERVER_UP ? "Ping funcionou" : "Ping não funcionou")
                        .status(OK)
                        .codigoStatus(OK.value())
                        .build()
        );
    }

    @PostMapping("/salvar")
    public ResponseEntity<Response> salvarServidor(@RequestBody @Valid Servidor servidor) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .dados(Map.of("servidor", servidorService.criar(servidor)))
                        .mensagem("Servidor foi criado")
                        .status(OK)
                        .codigoStatus(OK.value())
                        .build()
        );
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<Response> buscarServidor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .dados(Map.of("servidor", servidorService.buscar(id)))
                        .mensagem("Servidor encontrado")
                        .status(OK)
                        .codigoStatus(OK.value())
                        .build()
        );
    }


    @DeleteMapping("/buscar/{id}")
    public ResponseEntity<Response> deletarServidor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .dados(Map.of("servidor", servidorService.deletar(id)))
                        .mensagem("Servidor deletado")
                        .status(OK)
                        .codigoStatus(OK.value())
                        .build()
        );
    }


    @GetMapping(path = "/imagens/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] pegarServidorImagem(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.name" + "Downloads/imagens/" + fileName)));
    }
}
