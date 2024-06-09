package com.erickfelix.mailsender.service.impl;

import com.erickfelix.mailsender.model.EmailTemplate;
import com.erickfelix.mailsender.repository.EmailTemplateRepository;
import com.erickfelix.mailsender.service.EmailTemplateService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

    private final EmailTemplateRepository emailTemplateRepository;

    public EmailTemplateServiceImpl(EmailTemplateRepository emailTemplateRepository) {
        this.emailTemplateRepository = emailTemplateRepository;
    }

    @Override
    public Optional<EmailTemplate> getEmailTemplate() {
        return emailTemplateRepository.findAll().stream().findFirst();
    }

    @Override
    public EmailTemplate saveEmailTemplate(EmailTemplate emailTemplate) {
        return emailTemplateRepository.save(emailTemplate);
    }
}
