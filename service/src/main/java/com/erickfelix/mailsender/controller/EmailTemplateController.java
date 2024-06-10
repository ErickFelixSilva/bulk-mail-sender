package com.erickfelix.mailsender.controller;

import com.erickfelix.mailsender.model.EmailTemplate;
import com.erickfelix.mailsender.service.EmailTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email-template")
public class EmailTemplateController {

    private final EmailTemplateService emailTemplateService;
    public static final EmailTemplate DEFAULT_TEMPLATE = new EmailTemplate("Non Profit Capital Distribution",
                                                                      "Hello, {name}! We are pleased to inform you that your non-profit organization at " +
                                                                      "{address} has been selected to receive a capital distribution, congratulations!");

    public EmailTemplateController(EmailTemplateService emailTemplateService) {
        this.emailTemplateService = emailTemplateService;
    }

    @GetMapping
    public EmailTemplate getTemplate() {
        return emailTemplateService.getEmailTemplate().orElse(DEFAULT_TEMPLATE);
    }

     @PostMapping
     public EmailTemplate saveEmailTemplate(@RequestBody EmailTemplate emailTemplate) {
         return emailTemplateService.saveEmailTemplate(emailTemplate);
     }
}
