package com.victorlobato.gerenciamento.servidor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    protected LocalDateTime timeStamp;
    protected int codigoStatus;
    protected HttpStatus status;
    protected String motivo;
    protected String mensagem;
    protected String mensagemDev;
    protected Map<?,?> dados;
}
