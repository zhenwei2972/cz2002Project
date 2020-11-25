import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * This is the mail class
 * it handles all the mailing
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */
public class Mail {
   /** 
    * this is the sending mail function
    * @param recepient as the recevier's email
    * @throws MessagingException to catch when mail fails
    */
   public void sendMail(String recepient) throws MessagingException {
      Properties prop = new Properties();
      prop.put("mail.smtp.auth", "true");
      prop.put("mail.smtp.starttls.enable", "true");
      prop.put("mail.smtp.host", "smtp.gmail.com");
      prop.put("mail.smtp.port", "587");

      String myAccount = "unwantedntu@gmail.com";
      String password = "cpl12454";

      Session session = Session.getInstance(prop, new Authenticator() {
         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccount, password);
         }
      });
      Message message = prepareMessage(session, myAccount, recepient);
      Transport.send(message);
      System.out.println("Course added successfully, confirmation has been sent by email");
   }

   
   /** 
    * this is the preping of email
    * @param session as the new email to send
    * @param myAccountEmail as the systems email
    * @param recepient as the reciever's email
    * @return Message as the email content
    */
   private Message prepareMessage(Session session, String myAccountEmail, String recepient) {
      try {
         Message msg = new MimeMessage(session);
         msg.setFrom(new InternetAddress(myAccountEmail));
         msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
         msg.setSubject("Course Information");
         msg.setText("You course have been successfully registered!");
         return msg;
      } catch (Exception ex) {
         Logger.getLogger("Error!").log(Level.SEVERE, null, ex);
      }
      return null;
   }
}