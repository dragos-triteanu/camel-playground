package org.camel.playground.activemq.beans;

import org.apache.camel.Consume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dragos.triteanu on 2/22/16.
 */
public class SomeMessageDrivenBean {
    private static final Logger LOG = LoggerFactory.getLogger(SomeMessageDrivenBean.class);

    @Consume(uri = "activemq:queue:someQueue")
    public String onMessage(String message){
        LOG.info("Message = {}",message);
        return "Processed:" + message;
    }
}
