package com.clouway.jobex.server.emailservice;

import com.clouway.jobex.server.job.Job;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EmailSenderImpl implements EmailSender {

  /**
   * Send email to user
   *
   * @param job - job
   * @param email - user's email
   */
  public void sendEmail(Job job, String email) {

    Properties properties = new Properties();
    Session session = Session.getInstance(properties);

    String messageBody = "You are approved for '" + job.getPosition() + "', at company '" + job.getCompany() + "'.";

    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("ivan.lazov@clouway.com", "JobEx"));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
      message.setSubject("JobEx - Approved CV");
      message.setText(messageBody);
      Transport.send(message);

    } catch (AddressException e) {
      e.printStackTrace();
    } catch (MessagingException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
}
