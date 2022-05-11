package com.report.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(SimpleMailMessage simpleMailMessage) {
		LOG.info("Simulando envio de e-mail");
		this.mailSender.send(simpleMailMessage);
		LOG.info("E-mail enviado!");
	}

	public void sendEmailHtml(MimeMessage mimeMessage) {
		LOG.info("Simulando envio de e-mail html");
		this.javaMailSender.send(mimeMessage);
		LOG.info("E-mail enviado!");
	}

}
