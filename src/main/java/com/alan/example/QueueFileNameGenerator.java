package com.alan.example;

import org.springframework.integration.file.FileNameGenerator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author Alan Dávila<br>
 * 05 Sep. 2017 21:20
 */
@Component
public class QueueFileNameGenerator implements FileNameGenerator {
    @Override
    public String generateFileName(final Message<?> message) {
        LocalDate localDate = LocalDate.now();
        return message.getHeaders().get("queuename").toString().concat("_").concat(String.valueOf(localDate.getYear()
        ).concat(String.valueOf(localDate.getMonthValue())).concat(String.valueOf(localDate.getDayOfMonth()))).concat
                (".txt");
    }
}
