package com.gla.aircare.tools;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	private final String SENDER = "gl.aircare@gmail.com";
	private final String PASSWORD = "mdpnotsecure";
	private final String OBJET = "Maintenance a effectuer";
	private final String DESTINATAIRE = "gl.aircare@gmail.com";
	private final String CC = "alexandrebessa26@gmail.com";
	private String corps;

	public Mail(String corps) {
		this.corps = corps;
	}

	public void sendMail() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;
		InternetAddress ccAddress = null;
		
		try {
			fromAddress = new InternetAddress(SENDER, "GLA Airlines");
			toAddress = new InternetAddress(DESTINATAIRE);
			ccAddress = new InternetAddress(CC);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return;
		} catch (AddressException e) {
			e.printStackTrace();
			return;
		}

		try {
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(SENDER, PASSWORD);
				}
			});

			Message mail = new MimeMessage(session);
			mail.setFrom(fromAddress);
			mail.setRecipient(Message.RecipientType.TO, toAddress);
			mail.setRecipient(Message.RecipientType.CC, ccAddress);
			mail.setSubject(OBJET);
			mail.setText(corps);

			Transport.send(mail);

		} catch (MessagingException e) {
			e.printStackTrace();
			return;
		}
	}
}