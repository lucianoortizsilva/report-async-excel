package com.report.queue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.report.service.CatalogoNetflixService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitMQConsumer {

	@Autowired
	private CatalogoNetflixService service;

	@RabbitListener(queues = "QUEUE-REPORT")
	public void x(final Report report) {
		log.info("message received QUEUE-REPORT");
		Resource resource = null;
		try {
			long inicio = System.currentTimeMillis();
			final File file = this.service.generateFile(report.getReleaseYear());
			long tempo = System.currentTimeMillis() - inicio;
			System.out.println("Tempo: " + tempo);
			
			if (Objects.isNull(file)) {
				System.out.println("sem relatorio");
			}
			final Path path = Paths.get(file.getPath());
			resource = new UrlResource(path.toUri());
			final String filename = resource.getFilename();
			System.out.println("Filename gerado: " + filename);
		} catch (final Exception e) {
			System.err.println("erroooooooooooo");
		}
	}

}