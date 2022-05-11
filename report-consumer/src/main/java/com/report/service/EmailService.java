package com.report.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.report.queue.Report;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @see https://ethereal.email/create
 * @see https://ethereal.email/messages
 *
 */
@Slf4j
@Service
public class EmailService {

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMailWithReport(final Report report, final File file) throws IOException {
		try {
			log.info("> Enviando e-mail");
			Multipart multipart = new MimeMultipart();
			
			MimeBodyPart anexo = new MimeBodyPart();
			anexo.attachFile(file);
			
			MimeBodyPart body = new MimeBodyPart();
			body.setText(htmlFromTemplateReport(report), "UTF-8", "html");
			
			multipart.addBodyPart(body);
			multipart.addBodyPart(anexo);
			
			MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
			mimeMessage.setSubject("Catálogo Netflix [Ano]: ".concat(report.getReleaseYear().toString()));
			mimeMessage.setSentDate(new Date(System.currentTimeMillis()));

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo(report.getEmailTo());
			mimeMessageHelper.setFrom("moshe.rau41@ethereal.email");

			mimeMessage.setContent(multipart);

			this.javaMailSender.send(mimeMessage);
			log.info("> E-mail enviado com sucesso!");
			log.info("> Verifique o e-mail enviado na sua conta em: https://ethereal.email/messages\n");
		} catch (MessagingException e) {
			log.error(e.getMessage(), e);
		}
	}

	private String htmlFromTemplateReport(final Report report) {
		Context context = new Context();
		context.setVariable("report", report);
		return this.templateEngine.process("email/report", context);
	}

}