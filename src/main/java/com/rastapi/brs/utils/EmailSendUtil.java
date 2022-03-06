package com.rastapi.brs.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailSendUtil {
    public void sendEmail(String emailAddress) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("testsubject1092@gmail.com","Alpha1092"));
//            email.setAuthentication("testsubject1092@google.com","Alpha1092");
            email.setSSLOnConnect(true);
            email.setFrom("testSubject1092@gmail.com" );
            email.setSubject("this is test email");
            email.setMsg("you have registered as author... :-)");
            email.addTo(emailAddress);
            email.send();
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Email Send failed");
        }
    }
}
