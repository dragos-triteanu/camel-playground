package org.camel.playground.direct.processors;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by dragos.triteanu on 2/19/16.
 */
public class RouteStoppingProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        final String routeName = exchange.getIn().getBody(String.class);
        final CamelContext context = exchange.getContext();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    context.stopRoute(routeName);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
