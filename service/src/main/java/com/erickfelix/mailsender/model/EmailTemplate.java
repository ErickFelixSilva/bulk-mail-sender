package com.erickfelix.mailsender.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class EmailTemplate {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    public EmailTemplate(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }
}
