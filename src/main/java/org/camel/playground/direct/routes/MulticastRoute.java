package org.camel.playground.direct.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by dragos.triteanu on 2/19/16.
 */
public class MulticastRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

    /**
     * This section of code does NOT represent a multicast. Any changes made to the payload in 'endpoint1' for example will be
     * reflected in 'endpoint2' and 'endpoint3'.
            from("direct:multicast")
                .to("direct:endpoint1")
                .to("direct:endpoint2")
                .to("direct:endpoint3");
    **/

    /**
     * This is how to create an actual multicast. Each endpoint will receive a separate shallow copy of the
     * payload sent to 'direct:start'. Using .parallelProcessing() after multicast, can call the multicast endpoints in parallel instead of sequencialy.
     */
        from("direct:start")
             .multicast()
                .to("direct:endpoint1")
                .to("direct:endpoint2")
                .to("direct:endpoint3")
             .end();
    }
}
