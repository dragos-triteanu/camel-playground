package org.camel.playground.activemq.beans;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

/**
 * Created by dragos.triteanu on 2/22/16.
 */
public class SomeMessageProducerBean {

    @Produce
    private ProducerTemplate template;


    public String produceMessage(String payload) {
        String processedPayload = template.requestBody("activemq:queue:someQueue" , payload, String.class);
        return processedPayload;
    }

}
