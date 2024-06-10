package com.erickfelix.mailsender.controller;

import com.erickfelix.mailsender.model.EmailLog;
import com.erickfelix.mailsender.service.EmailService;
import com.erickfelix.mailsender.service.EmailTemplateService;
import com.erickfelix.mailsender.service.NonprofitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.erickfelix.mailsender.controller.EmailTemplateController.DEFAULT_TEMPLATE;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    private final EmailService emailService;
    private final EmailTemplateService emailTemplateService;
    private final NonprofitService nonProfitService;

    public EmailController(EmailService emailService, EmailTemplateService emailTemplateService,
                           NonprofitService nonProfitService) {
        this.emailService = emailService;
        this.nonProfitService = nonProfitService;
        this.emailTemplateService = emailTemplateService;
    }

    @PostMapping
    public String sendEmail(@RequestBody List<Long> nonProfitIds) {
        var nonProfits = nonProfitService.getAllNonprofitsByIds(nonProfitIds);
        var template = emailTemplateService.getEmailTemplate().orElse(DEFAULT_TEMPLATE);
        emailService.sendBulkEmailWithTemplate(template, nonProfits);
        return "Emails sent successfully";
    }

    @GetMapping("/logs")
    public List<EmailLog> getEmailLogs() {
        return emailService.getEmailLogs();
    }
}
