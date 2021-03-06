package com.report.queue;

import java.io.File;

import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.report.service.CatalogoNetflixService;
import com.report.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitMQConsumer {

	@Autowired
	private CatalogoNetflixService service;

	@Autowired
	private EmailService emailService;

	@RabbitListener(queues = "QUEUE-REPORT")
	public void consumerQueue(final String message) {
		log.info("> Mensagem recebida da fila: QUEUE-REPORT");
		try {
			final JSONObject json = new JSONObject(message);
			Report report = new Report();
			report.setEmailTo(json.getString("emailTo"));
			report.setReleaseYear(json.getInt("releaseYear"));
			long inicio = System.currentTimeMillis();
			log.info("> Gerando relatorio");
			final File file = this.service.generateFile(report.getReleaseYear());
			long tempo = System.currentTimeMillis() - inicio;
			log.info("> Tempo gasto em milissegundos: {}", tempo);
			log.info("> Relatorio gerado: {}", file.getName());
			log.info("> Path: {}", file.getAbsoluteFile());
			emailService.sendMailWithReport(report, file);
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}