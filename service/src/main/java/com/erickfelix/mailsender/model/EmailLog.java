package com.erickfelix.mailsender.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailLog {
    private String recipient;
    private String subject;
    private String body;
}