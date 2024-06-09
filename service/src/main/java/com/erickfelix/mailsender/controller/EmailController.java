package com.erickfelix.mailsender.controller;

import com.erickfelix.mailsender.infra.EmailTemplateNotFoundException;
import com.erickfelix.mailsender.service.EmailService;
import com.erickfelix.mailsender.service.EmailTemplateService;
import com.erickfelix.mailsender.service.NonProfitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;
    private final EmailTemplateService emailTemplateService;
    private final NonProfitService nonProfitService;

    public EmailController(EmailService emailService, EmailTemplateService emailTemplateService,
                           NonProfitService nonProfitService) {
        this.emailService = emailService;
        this.nonProfitService = nonProfitService;
        this.emailTemplateService = emailTemplateService;
    }

    @PostMapping("/send")
    public String sendEmail(List<Long> nonProfitIds) {
        var nonProfits = nonProfitService.getAllNonProfitsByIds(nonProfitIds);
        var template = emailTemplateService.getEmailTemplate()
                .orElseThrow(() -> new EmailTemplateNotFoundException("Email template not found, please save you template first"));
        emailService.sendBulkEmailWithTemplate(template, nonProfits);
        return "Emails sent successfully";
    }
}
