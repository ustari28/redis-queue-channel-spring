package com.alan.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;

/**
 * @author Alan Dávila<br>
 * 19 Aug. 2017 13:33
 */
@Slf4j
@MessageEndpoint
public class TransformerFileMessage {

    @Transformer
    public String transformerFileMessage(final String input) throws InterruptedException {
        log.info("mensaje entrante");
        Thread.sleep(100);
        return input;
    }
}
