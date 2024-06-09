package com.erickfelix.mailsender.service;

import com.erickfelix.mailsender.model.EmailTemplate;
import java.util.Optional;

public interface EmailTemplateService {
    Optional<EmailTemplate> getEmailTemplate();

    EmailTemplate saveEmailTemplate(EmailTemplate emailTemplate);
}
