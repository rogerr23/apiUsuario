package br.com.cotiinformatica.components;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.collections.LogMensageria;
import br.com.cotiinformatica.dtos.MensagemUsuarioDto;
import br.com.cotiinformatica.repositories.LogMensageriaRepository;

@Component
public class RabbitMQProducerComponent {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private Queue queue;

	@Autowired
	private LogMensageriaRepository logMensageriaRepository;

	// Metodo para gravarmos um conteudo na fila
	public void sendMessage(MensagemUsuarioDto dto) throws Exception {

		LogMensageria logMensageria = new LogMensageria();
		logMensageria.setId(UUID.randomUUID());
		logMensageria.setEmailUsuario(dto.getEmailUsuario());
		logMensageria.setOperacao("GRAVAÇÃO DE MSG NA FILA");
		logMensageria.setDataHora(new Date());

		try {

			// serializando os dados em formato JSON
			String json = objectMapper.writeValueAsString(dto);

			// gravando conteudo na fila
			rabbitTemplate.convertAndSend(queue.getName(), json);

			logMensageria.setDescricao("Mensagem gravada com sucesso na fila.");
		}

		catch (Exception e) {

			logMensageria.setDescricao("Erro ao gravar mensagem na fila: " + e.getMessage());

		}

		finally {

			// gravar o log no banco do MongoDB
			logMensageriaRepository.save(logMensageria);
		}

	}

}
