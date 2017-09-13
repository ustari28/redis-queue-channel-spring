package com.alan.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.file.splitter.FileSplitter;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alan Dávila<br>
 * 19 Aug. 2017 13:33
 */
@Slf4j
@MessageEndpoint
public class TransformerFileMessage {


    @Transformer
    public String transformerFileMessage(final String input, MessageHeaders headers) throws InterruptedException {
        log.info("mensaje entrante");
        //Thread.sleep(100);
        return input;
    }

    @Transformer
    public String transformerFileMessageMark(final FileSplitter.FileMarker fileMarker) {
        FileSplitter.FileMarker.Mark mark = fileMarker.getMark();
        Map<String, Object> mapHeaders = new HashMap<>();
        MessageHeaders newHeaders = new MessageHeaders(mapHeaders);
        log.info("File {} mark {} with {} line(s)", fileMarker.getFilePath(), mark.name(), fileMarker.getLineCount());
        return null;
    }

    @Transformer(inputChannel = "channel-recieves-files")
    public String zipFile(final GenericMessage<String> message) {

        return "File has been finished " + message.getHeaders().get("file_name");
    }
}
