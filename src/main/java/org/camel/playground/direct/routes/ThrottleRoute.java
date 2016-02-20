package org.camel.playground.direct.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by dragos.triteanu on 2/19/16.
 */
public class ThrottleRoute extends RouteBuilder {


    /**
     * Accepts only 5 requests in a 2s interval from the throttleEndpoint.
     * @throws Exception
     */
    @Override
    public void configure() throws Exception {
        from("direct:throttledEndpoint")
            .throttle(5).timePeriodMillis(2000);
    }
}
