package com.example.daily.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author: zhiyuan
 * @date: 2017年11月22日
 * @project: alertapp
 * @description:
 */
@Component
public class EmailSendUtil {
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.template}")
	private String template;

	@Value("${spring.mail.subject}")
	private String subject;

	/**
	 * 
	 * @param emailAddress
	 * @param nodeName
	 * @param nodeType
	 */
	public void sendEmail(String emailAddress, String nodeName, String nodeType) {
		String mailContent = String.format(template, nodeName, nodeType);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(username);
		message.setTo(emailAddress);
		message.setSubject(subject);
		message.setText(mailContent);

		javaMailSender.send(message);
	}

	/**
	 * 
	 * @param emailAddessess
	 * @param nodeName
	 * @param nodeType
	 */
	public void sendEmail2Notifiers(final List<String> emailAddessess, final String nodeName, final String nodeType) {
		String[] emails = new String[emailAddessess.size()];
		for (int i = 0; i < emailAddessess.size(); i++) {
			emails[i] = emailAddessess.get(i);
		}
		String mailContent = String.format(template, nodeName, nodeType);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(username);
		message.setTo(emails);
		message.setSubject(subject);
		message.setText(mailContent);

		javaMailSender.send(message);
	}
}
