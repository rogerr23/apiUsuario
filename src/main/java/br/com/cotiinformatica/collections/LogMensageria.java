package br.com.cotiinformatica.collections;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document(collection = "LogMensageria")
public class LogMensageria {

	@Id
	private UUID id;
	private Date dataHora;
	private String operacao;
	private String descricao;
	private String emailUsuario;

}
