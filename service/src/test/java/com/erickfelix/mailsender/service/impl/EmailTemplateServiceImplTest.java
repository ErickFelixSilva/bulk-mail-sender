package com.erickfelix.mailsender.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.erickfelix.mailsender.model.EmailTemplate;
import com.erickfelix.mailsender.repository.EmailTemplateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EmailTemplateServiceImplTest {

    @Mock
    private EmailTemplateRepository emailTemplateRepository;

    @InjectMocks
    private EmailTemplateServiceImpl emailTemplateService;

    @Test
    void testGetEmailTemplate() {
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setId(1L);
        emailTemplate.setSubject("Test Subject");
        emailTemplate.setBody("Test Body");

        when(emailTemplateRepository.findAll()).thenReturn(List.of(emailTemplate));

        Optional<EmailTemplate> result = emailTemplateService.getEmailTemplate();

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Test Subject", result.get().getSubject());
        assertEquals("Test Body", result.get().getBody());
        verify(emailTemplateRepository, times(1)).findAll();
    }

    @Test
    void testGetEmailTemplateEmpty() {
        when(emailTemplateRepository.findAll()).thenReturn(List.of());

        Optional<EmailTemplate> result = emailTemplateService.getEmailTemplate();

        assertFalse(result.isPresent());
        verify(emailTemplateRepository, times(1)).findAll();
    }

    @Test
    void testSaveEmailTemplate() {
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setId(1L);
        emailTemplate.setSubject("Test Subject");
        emailTemplate.setBody("Test Body");

        when(emailTemplateRepository.save(any(EmailTemplate.class))).thenReturn(emailTemplate);

        EmailTemplate savedTemplate = emailTemplateService.saveEmailTemplate(emailTemplate);

        assertNotNull(savedTemplate);
        assertEquals(1L, savedTemplate.getId());
        assertEquals("Test Subject", savedTemplate.getSubject());
        assertEquals("Test Body", savedTemplate.getBody());
        verify(emailTemplateRepository, times(1)).save(emailTemplate);
    }
}
