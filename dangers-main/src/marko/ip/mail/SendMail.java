package marko.ip.mail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import marko.ip.dto.Warning;

public class SendMail implements Runnable{
	
	private String username;
	private String password;
	
	private List<String> recipients;
	private Warning warning;
	
	public SendMail(List<String> recipients, Warning warning) {
		this.recipients = recipients;
		this.warning = warning;
	}

	public boolean sendMail(String to, String title, String body) throws FileNotFoundException, IOException {
		
		Properties properties = loadMailConfig();

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(title);
			message.setText(body);
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private Properties loadMailConfig() throws FileNotFoundException, IOException {
		ResourceBundle bundle =
			      PropertyResourceBundle.getBundle("marko.ip.mail.SendMail");
		
		Properties mailProp = new Properties();
		mailProp.setProperty("mail.smtp.host", bundle.getString("mail.smtp.host"));
		mailProp.setProperty("mail.smtp.socketFactory.port", bundle.getString("mail.smtp.socketFactory.port"));
		mailProp.setProperty("mail.smtp.socketFactory.class", bundle.getString("mail.smtp.socketFactory.class"));
		mailProp.setProperty("mail.smtp.auth", bundle.getString("mail.smtp.auth"));
		mailProp.setProperty("mail.smtp.port", bundle.getString("mail.smtp.port"));
		mailProp.setProperty("username", bundle.getString("username"));
		mailProp.setProperty("password", bundle.getString("password"));
		
		username = mailProp.getProperty("username");
		password = mailProp.getProperty("password");
		System.out.println(username);
		System.out.println(password);
		return mailProp;
	}

	@Override
	public void run() {
		for(String to:recipients) {
			String title = warning.getCategories().get(0).getName();
			String author = warning.getAuthor().getFirstName();
			String body = author + ": " + warning.getDescription();
			try {
				sendMail(to, title, body);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
