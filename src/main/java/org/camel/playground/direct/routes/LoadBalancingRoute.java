package org.camel.playground.direct.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by dragos.triteanu on 2/22/16.
 */
public class LoadBalancingRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("direct:loadBalance")
                .loadBalance().roundRobin()
                    .to("mock:endpoint1")
                    .to("mock:endpoint2")
                    .to("mock:endpoint3")
                .end();
    }
}
