package com.example.emailsendconsumer;

import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.stereotype.Service;

@Service
public class EmailSendConsumer {

  @KafkaListener(
          topics = "email.send",
          groupId = "email-send-group",
          concurrency = "3"
  )

  @RetryableTopic(
          attempts = "5",
          backOff = @BackOff(delay = 1000, multiplier = 2),
          dltTopicSuffix = ".dlt",   // e.g, email.send.dlt
          dltStrategy = DltStrategy.FAIL_ON_ERROR   // DLT 실패 시 재전송 금지
  )

  public void consume(String message) {
    System.out.println("kafka로부터 받아온 메시지: " + message);

    EmailSendMessage emailSendMessage = EmailSendMessage.fromJson(message);

    if (emailSendMessage.getTo().equals("fail@test.com")) {
      System.out.println("잘못된 이메일 주소로 인한 발송 실패");

      throw new RuntimeException("잘못된 이메일 주소로 인한 발송 실패");
    }

    // 실제 이메일 발송 로직 생략
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException("이메일 발송 실패");
    }

    System.out.println("이메일 발송 완료");
  }
}
