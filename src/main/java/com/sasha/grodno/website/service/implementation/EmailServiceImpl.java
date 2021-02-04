package com.sasha.grodno.website.service.implementation;

import com.sasha.grodno.website.service.iterface.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender emailSender;


    @Override
    public void sendEmail(String email, String link) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String content = "<p>Hello,</p>"
                + "<p>You requested an action.</p>"
                + "<p>Click on the link below to confirm your action:</p>"
                + "<p><a href=\"" + link + "\">CONFIRM</a></p>"
                + "<br>"
                + "<p>Ignore this email if you haven't made the request on the site</p>";
        try {
            helper.setFrom("from@mail.by", "company");
            helper.setTo(email);
            helper.setSubject("Confirmation of actions on the site");
            helper.setText(content, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            LOGGER.error("Error send EMAIL: ", e);
        }
        emailSender.send(message);
    }
}
