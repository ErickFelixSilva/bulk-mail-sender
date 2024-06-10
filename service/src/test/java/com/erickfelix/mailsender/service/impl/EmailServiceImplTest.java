package com.erickfelix.mailsender.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.erickfelix.mailsender.model.EmailLog;
import com.erickfelix.mailsender.model.EmailTemplate;
import com.erickfelix.mailsender.model.Nonprofit;
import com.erickfelix.mailsender.service.NonprofitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    @Mock
    private NonprofitService nonprofitService;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    void testSendEmailWithTemplate() {
        Nonprofit nonprofit = new Nonprofit();
        nonprofit.setId(1L);
        nonprofit.setName("Test Nonprofit");
        nonprofit.setAddress("123 Test St");
        nonprofit.setEmail("test@example.com");

        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setSubject("Test Subject");
        emailTemplate.setBody("Hello, {name}! Your address is {address}.");

        emailService.sendEmailWithTemplate(emailTemplate, nonprofit);

        List<EmailLog> emailLogs = emailService.getEmailLogs();
        assertEquals(1, emailLogs.size());
        EmailLog log = emailLogs.get(0);
        assertEquals(1L, log.id());
        assertEquals("test@example.com", log.recipient());
        assertEquals("Test Subject", log.subject());
        assertEquals("Hello, Test Nonprofit! Your address is 123 Test St.", log.body());
    }

    @Test
    void testSendBulkEmailWithTemplate() {
        Nonprofit nonprofit1 = new Nonprofit();
        nonprofit1.setId(1L);
        nonprofit1.setName("Nonprofit 1");
        nonprofit1.setAddress("Address 1");
        nonprofit1.setEmail("nonprofit1@example.com");

        Nonprofit nonprofit2 = new Nonprofit();
        nonprofit2.setId(2L);
        nonprofit2.setName("Nonprofit 2");
        nonprofit2.setAddress("Address 2");
        nonprofit2.setEmail("nonprofit2@example.com");

        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setSubject("Bulk Email Subject");
        emailTemplate.setBody("Hello, {name}! Your address is {address}.");

        emailService.sendBulkEmailWithTemplate(emailTemplate, Arrays.asList(nonprofit1, nonprofit2));

        List<EmailLog> emailLogs = emailService.getEmailLogs();
        assertEquals(2, emailLogs.size());

        EmailLog log1 = emailLogs.get(0);
        assertEquals(1L, log1.id());
        assertEquals("nonprofit1@example.com", log1.recipient());
        assertEquals("Bulk Email Subject", log1.subject());
        assertEquals("Hello, Nonprofit 1! Your address is Address 1.", log1.body());

        EmailLog log2 = emailLogs.get(1);
        assertEquals(2L, log2.id());
        assertEquals("nonprofit2@example.com", log2.recipient());
        assertEquals("Bulk Email Subject", log2.subject());
        assertEquals("Hello, Nonprofit 2! Your address is Address 2.", log2.body());
    }
}
