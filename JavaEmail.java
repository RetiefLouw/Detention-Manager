package Controllers;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaEmail {

    Session mailSession;

    public JavaEmail() {
        try {
            this.setMailServerProperties();
            this.draftEmailMessage();
            this.sendEmail();
        } catch (MessagingException ex) {
            Logger.getLogger(JavaEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static void main(String args[]) throws AddressException, MessagingException {
//        JavaEmail javaEmail = new JavaEmail();
//        javaEmail.setMailServerProperties();
//        javaEmail.draftEmailMessage();
//        javaEmail.sendEmail();
//    }

    private void setMailServerProperties() {
        System.out.println("set propert");
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "465");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        mailSession = Session.getDefaultInstance(emailProperties, null);
    }

    private MimeMessage draftEmailMessage() throws AddressException, MessagingException {
        System.out.println("draft");
        String[] toEmails = {"retieflouw@gmail.com"};
        String emailSubject = "Test email subject";
        String emailBody = "This is an email sent by <b>//howtodoinjava.com</b>.";
        MimeMessage emailMessage = new MimeMessage(mailSession);
        /**
         * Set the mail recipients
         *
         */
        for (int i = 0; i < toEmails.length; i++) {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }
        emailMessage.setSubject(emailSubject);
        /**
         * If sending HTML mail
         *
         */
        emailMessage.setContent(emailBody, "text/html");
        /**
         * If sending only text mail
         *
         */
        //emailMessage.setText(emailBody);// for a text email
        return emailMessage;
    }

    private void sendEmail() throws AddressException, MessagingException {
        System.out.println("send");
        String fromUser = "retieflouw@gmail.com";
        String fromUserEmailPassword = "Retief+7231";

        String emailHost = "173.194.73.108";
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        /**
         * Draft the message
         *
         */
        System.out.println("connected");
        MimeMessage emailMessage = draftEmailMessage();
        /**
         * Send the mail
         *
         */
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully.");
    }
}
