package de.davidartmann.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App 
{
	public static void main(String[] args) {
		new App();
	}
       
    private static final String USERNAME = "YOUR_ADDRESS";
    private static final String PASSWORD = "YOUR_PASS";
    //More recipients could be added, just by separating with a comma:
    private static final String RECIPIENT = "RECIPIENT(S)";
   
    public App() {
        String from = USERNAME;
        String pass = PASSWORD;
        String to = RECIPIENT;
        String subject = "SUBJECT";
        String body = "TestBody";
        sendMail(from, pass, to, subject, body);
    }
 
    private static void sendMail(String from, String pass, String to, String subject, String body) {
        String host = "smtp.gmail.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //props.put("mail.debug", "true");
 
        Session session = Session.getDefaultInstance(props, 
    		new Authenticator() {
    	         protected PasswordAuthentication getPasswordAuthentication() {
    	        	 return new PasswordAuthentication(USERNAME, PASSWORD);
    	         }
        	}
        );
 
        try {
        	Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(RECIPIENT));
            message.setSubject(subject);
            message.setContent(body, "text/html");
            Transport.send(message);
            System.out.println("E-Mail sent.");
        } catch (MessagingException me) {
        	System.out.println("E-Mail could not be sent. Something went wrong with the messaging.");
        }
    }
}
