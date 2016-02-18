package org.camel.playground.direct.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by dragos.triteanu on 2/18/16.
 */
public class SomeProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        String body = (String) exchange.getIn().getBody();
        String newBody = String.format("Processed(%s)",body);
        exchange.getIn().setBody(newBody);
    }
}
