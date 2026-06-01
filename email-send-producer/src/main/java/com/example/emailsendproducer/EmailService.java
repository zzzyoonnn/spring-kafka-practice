package com.example.emailsendproducer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class EmailService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public EmailService(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendEmail(SendEmailRequestDto sendEmailRequestDto) {
    EmailSendMessage emailSendMessage = new EmailSendMessage(
            sendEmailRequestDto.getFrom(),
            sendEmailRequestDto.getTo(),
            sendEmailRequestDto.getSubject(),
            sendEmailRequestDto.getBody()
    );

    this.kafkaTemplate.send("email.send", toJsonString(emailSendMessage));
  }

  private String toJsonString(Object object) {
    ObjectMapper objectMapper = new ObjectMapper();

    String message = objectMapper.writeValueAsString(object);
    return message;
  }
}
