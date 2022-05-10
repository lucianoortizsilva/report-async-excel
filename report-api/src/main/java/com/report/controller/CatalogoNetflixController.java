package com.report.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.report.queue.Report;
import com.report.util.ResponseUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(value = "catalogo-netflix")
public class CatalogoNetflixController {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@PostMapping(value = "/download/async/excel")
	public ResponseEntity<String> download(@RequestBody final Report Report) {
		try {
			this.rabbitTemplate.convertAndSend("QUEUE-REPORT", Report);
			log.info("Send message to QUEUE-REPORT");
			return ResponseUtil.ok("Relatório está sendo processado. Aguarde via e-mail");
		} catch (final Exception e) {
			return ResponseUtil.cathException(e);
		}
	}

}