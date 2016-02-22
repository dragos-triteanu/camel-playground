package org.camel.playground.activemq.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by dragos.triteanu on 2/22/16.
 */
public class MessageConsumingRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:activemqSample")
             .bean("producerBean", "produceMessage")
        .to("mock:messageEndpoint");

    }
}
