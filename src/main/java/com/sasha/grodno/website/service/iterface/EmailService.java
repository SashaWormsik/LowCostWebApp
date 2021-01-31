package com.sasha.grodno.website.service.iterface;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {

    void sendEmail(String email, String link);
}
