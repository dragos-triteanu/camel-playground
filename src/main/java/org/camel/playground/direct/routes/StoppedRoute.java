package org.camel.playground.direct.routes;

import org.apache.camel.builder.RouteBuilder;
import org.camel.playground.direct.processors.RouteStoppingProcessor;

/**
 * Created by dragos.triteanu on 2/19/16.
 */
public class StoppedRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:stoppedEndpoint").id("stoppedRoute")
            .log("Stopping route")
            .process(new RouteStoppingProcessor())
            .log("Route stopped")
            .to("mock:stoppedEndpoint");
    }
}
