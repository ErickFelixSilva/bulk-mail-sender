package com.erickfelix.mailsender.service;

import com.erickfelix.mailsender.model.EmailLog;
import com.erickfelix.mailsender.model.EmailTemplate;
import com.erickfelix.mailsender.model.Nonprofit;

import java.util.List;

public interface EmailService {
    void sendEmailWithTemplate(EmailTemplate emailTemplate, Nonprofit nonProfit);
    void sendBulkEmailWithTemplate(EmailTemplate emailTemplate, List<Nonprofit> nonprofits);
    List<EmailLog> getEmailLogs();

    boolean isRecentlySent(Long nonprofitId);
}
