package Controllers;

import com.sun.mail.smtp.SMTPTransport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class Mail {

    private static final String SMTP_SERVER = "smtp.gmail.com";

    public Mail(String fromEmail, String fromPass, String toEmail, String subject, String text) {
        System.out.println("Sending Email");
        String USERNAME = fromEmail;
        String PASSWORD = fromPass;
        String EMAIL_FROM = fromEmail;
        String EMAIL_TO = toEmail;
        String EMAIL_TO_CC = "";
        String EMAIL_SUBJECT = subject;
        String EMAIL_TEXT = text;

        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport 
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        // AN ERROR OCCURS SOMEWHERE AFTER THIS COMMENT WHEN EMAILING FROM THE BUILT VERSION OF THE APP
        prop.put("mail.smtp.port", "25");

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);
        JOptionPane.showMessageDialog(null, "Properties done");
        try {

            // from
            msg.setFrom(new InternetAddress(EMAIL_FROM));
            // to 
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO, false));
            // cc
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(EMAIL_TO_CC, false));
            // subject
            msg.setSubject(EMAIL_SUBJECT);
            // content 
            msg.setText(EMAIL_TEXT);
            msg.setSentDate(new Date());
            // connect
            try ( // Get SMTPTransport
                    SMTPTransport t = (SMTPTransport) session.getTransport("smtp")) {
                // connect
//                JOptionPane.showMessageDialog(null, "Connecting");
                t.connect(SMTP_SERVER, USERNAME, PASSWORD);
//                JOptionPane.showMessageDialog(null, "Connected");

                // send
//                int c = JOptionPane.showConfirmDialog(null, "Email Preview:\n"
//                        + "To:\t" + EMAIL_TO + "\n"
//                        + "From:\t" + EMAIL_FROM + "\n"
//                        + "Subject:\t" + EMAIL_SUBJECT + "\n"
//                        + "Message:\n" + EMAIL_TEXT + "\n\n"
//                        + "Do you want to send this email?");
//                if (c == 0) {
                t.sendMessage(msg, msg.getAllRecipients());
                JOptionPane.showMessageDialog(null, "Email Attempt:\n " + t.getLastServerResponse());
//                }

            }

        } catch (MessagingException e) {
            System.out.println("Error email");
            JOptionPane.showMessageDialog(null, "Error while emailing:\nEnsure you send from a Gmail account with its 'Less secure apps' setting allowed.\n" + e, "Email Error", ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
