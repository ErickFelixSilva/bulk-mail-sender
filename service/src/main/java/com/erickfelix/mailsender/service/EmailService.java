package com.erickfelix.mailsender.service;

import com.erickfelix.mailsender.model.EmailTemplate;
import com.erickfelix.mailsender.model.NonProfit;

import java.util.List;

public interface EmailService {
    void sendEmailWithTemplate(EmailTemplate emailTemplate, NonProfit nonProfit);
    void sendBulkEmailWithTemplate(EmailTemplate emailTemplate, List<NonProfit> nonProfits);
}
