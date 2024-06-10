package com.erickfelix.mailsender.service.impl;

import com.erickfelix.mailsender.model.EmailLog;
import com.erickfelix.mailsender.model.EmailTemplate;
import com.erickfelix.mailsender.model.Nonprofit;
import com.erickfelix.mailsender.service.EmailService;
import com.erickfelix.mailsender.service.NonprofitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final NonprofitService nonprofitService;
    private final List<EmailLog> emailLogs = new ArrayList<>();

    public EmailServiceImpl(NonprofitService nonprofitService) {
        this.nonprofitService = nonprofitService;
    }

    private void sendEmail(Long nonprofitId, String recipient, String subject, String body) {
        logger.info("Email sent to: {}", recipient);
        logger.info("Subject: {}", subject);
        logger.info("Body: {}", body);

        emailLogs.add(new EmailLog(nonprofitId, recipient, subject, body));
    }

    @Override
    public void sendEmailWithTemplate(EmailTemplate emailTemplate, Nonprofit nonProfit) {
        String recipient = nonProfit.getEmail();
        String body = emailTemplate.getBody();

        if (nonProfit.getName() != null) {
            body = body.replace("{name}", nonProfit.getName());
        }
        if (nonProfit.getAddress() != null) {
            body = body.replace("{address}", nonProfit.getAddress());
        }
        sendEmail(nonProfit.getId(), recipient, emailTemplate.getSubject(), body);
    }

    @Override
    public void sendBulkEmailWithTemplate(EmailTemplate emailTemplate, List<Nonprofit> nonprofits) {
        nonprofits.forEach(nonProfit -> sendEmailWithTemplate(emailTemplate, nonProfit));
        var nonprofitIds = nonprofits.stream().map(Nonprofit::getId).toList();
        nonprofitService.markAsSent(nonprofitIds);
    }

    @Override
    public List<EmailLog> getEmailLogs() {
        return new ArrayList<>(emailLogs);
    }
}