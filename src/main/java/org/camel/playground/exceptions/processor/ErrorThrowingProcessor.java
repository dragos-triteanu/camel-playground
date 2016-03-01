package org.camel.playground.exceptions.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by dragos.triteanu on 3/1/16.
 */
public class ErrorThrowingProcessor implements Processor {
    private static final Logger LOG = LoggerFactory.getLogger(ErrorThrowingProcessor.class);
    private int counter;

    @Override
    public void process(Exchange exchange) throws Exception {

        if (counter++ < 2) {
            exchange.getIn().setHeader("retryCount",counter);
            LOG.info("Counter: {} . Retrying",counter);
            throw new IOException("Forced");
        }
        LOG.info("Counter: {} . Processor finished after retry",counter);
        exchange.getIn().setBody("Bye World");
    }
}
