package com.victorlobato.gerenciamento.servidor;

import com.victorlobato.gerenciamento.servidor.enumaration.Status;
import com.victorlobato.gerenciamento.servidor.model.Servidor;
import com.victorlobato.gerenciamento.servidor.repository.ServidorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GerenciamentoServidorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoServidorApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServidorRepository servidorRepository){
		return  args -> {
				servidorRepository.save(new Servidor(null, "192.168.1.168","Ubuntu Linux","16 GB", "PC",
						"http://localhost:8080/servidor/imagens/servidor1.png", Status.SERVER_UP));

				servidorRepository.save(new Servidor(null, "192.168.1.18","Fedora Linux","32 GB", "Dell Tower",
					"http://localhost:8080/servidor/imagens/servidor1.png", Status.SERVER_DOWN));

				servidorRepository.save(new Servidor(null, "192.168.1.16","MS 2008","32 GB", "Servidor Web",
					"http://localhost:8080/servidor/imagens/servidor1.png", Status.SERVER_UP));

		};
	}
}
