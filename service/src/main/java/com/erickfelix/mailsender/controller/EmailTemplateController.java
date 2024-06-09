package com.erickfelix.mailsender.controller;

import com.erickfelix.mailsender.model.EmailTemplate;
import com.erickfelix.mailsender.service.EmailTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/email-template")
public class EmailTemplateController {

    private final EmailTemplateService emailTemplateService;

    public EmailTemplateController(EmailTemplateService emailTemplateService) {
        this.emailTemplateService = emailTemplateService;
    }

    @GetMapping
    public ResponseEntity<EmailTemplate> getTemplate() {
        Optional<EmailTemplate> template = emailTemplateService.getEmailTemplate();
        if (template.isPresent()) {
            return ResponseEntity.ok(template.get());
        } else {
            EmailTemplate defaultTemplate = new EmailTemplate("Non Profit Capital Distribution",
                    "Hello, {name}! We are pleased to inform you that your non-profit organization at " +
                            "{address} has been selected to receive a capital distribution, congratulations!");
            return ResponseEntity.ok(defaultTemplate);
        }
    }

     @PostMapping
     public EmailTemplate saveEmailTemplate(@RequestBody EmailTemplate emailTemplate) {
         return emailTemplateService.saveEmailTemplate(emailTemplate);
     }
}
