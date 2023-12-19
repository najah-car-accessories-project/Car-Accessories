package carAccessories;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

	public void orderConfirmations(String messageBody, String email) {

		String messageObject = "Dear,Your Installation Request has been completed\n";

		send(email, messageBody, messageObject);

	}

	public void newInstallationRequest(String messageBody, String email) {
		String messageObject = "Dear,You have a new Installation Request\n";

		send(email, messageBody, messageObject);
	}

	public void send(String to, String messageBody, String messageObject) {

		String from = "majdbasem6@gmail.com";
		String password = System.getenv("EMAIL_PASSWORD");

		try {
			Properties properties = System.getProperties();
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});
			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to, false));
			message.setSubject(messageObject);
			message.setText(messageBody);

			Transport.send(message);

		} catch (MessagingException m) {
			m.printStackTrace();

		}

	}

}
