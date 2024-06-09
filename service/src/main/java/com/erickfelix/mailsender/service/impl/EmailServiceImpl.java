package com.erickfelix.mailsender.service.impl;

import com.erickfelix.mailsender.model.EmailLog;
import com.erickfelix.mailsender.model.EmailTemplate;
import com.erickfelix.mailsender.model.NonProfit;
import com.erickfelix.mailsender.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final List<EmailLog> emailLogs = new ArrayList<>();

    private void sendEmail(String recipient, String subject, String body) {
        logger.info("Email sent to: {}", recipient);
        logger.info("Subject: {}", subject);
        logger.info("Body: {}", body);

        emailLogs.add(new EmailLog(recipient, subject, body));
    }

    @Override
    public void sendEmailWithTemplate(EmailTemplate emailTemplate, NonProfit nonProfit) {
        String recipient = nonProfit.getEmail();
        String body = emailTemplate.getBody()
                .replace("{name}", nonProfit.getName())
                .replace("{address}", nonProfit.getAddress());

        sendEmail(recipient, emailTemplate.getSubject(), body);
    }

    @Override
    public void sendBulkEmailWithTemplate(EmailTemplate emailTemplate, List<NonProfit> nonProfits) {
        nonProfits.forEach(nonProfit -> sendEmailWithTemplate(emailTemplate, nonProfit));
    }

    public List<EmailLog> getEmailLogs() {
        return new ArrayList<>(emailLogs);
    }
}