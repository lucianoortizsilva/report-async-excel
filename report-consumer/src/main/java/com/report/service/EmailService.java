package com.report.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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

	@Autowired
	private SmtpEmailService smtpEmailService;

	public void sendMailWithReport(final Report report) {
		MimeMessage mimeMessage;
		try {
			mimeMessage = prepararMimeMessageFromPedido(report);
			smtpEmailService.sendEmailHtml(mimeMessage);
		} catch (MessagingException e) {
			log.error(e.getMessage(), e);
		}
	}

	private MimeMessage prepararMimeMessageFromPedido(final Report report) throws MessagingException {
		MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setTo(report.getEmailTo());
		mimeMessageHelper.setFrom("moshe.rau41@ethereal.email");
		mimeMessage.setSubject("Catálogo Netflix [Ano]: ".concat(report.getReleaseYear().toString()));
		mimeMessage.setSentDate(new Date(System.currentTimeMillis()));
		mimeMessage.setText(htmlFromTemplatePedido(report), "UTF-8", "html");
		return mimeMessage;
	}

	private String htmlFromTemplatePedido(final Report report) {
		Context context = new Context();
		context.setVariable("report", report);
		return this.templateEngine.process("email/report", context);
	}

}