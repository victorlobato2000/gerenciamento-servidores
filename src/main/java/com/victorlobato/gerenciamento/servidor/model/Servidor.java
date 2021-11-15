package com.victorlobato.gerenciamento.servidor.model;

import com.victorlobato.gerenciamento.servidor.enumaration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servidor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "O endereço de IP não pode estar vazio ou nulo")
    private String enderecoIP;
    private String nome;
    private String memoria;
    private String tipo;
    private String imagemUrl;
    private Status status;
    
}
