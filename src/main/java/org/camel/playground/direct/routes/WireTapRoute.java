package org.camel.playground.direct.routes;

import org.apache.camel.builder.RouteBuilder;
import org.camel.playground.direct.beans.SomeBean;

/**
 * Implementation of the WireTap pattern, that processes the same message separately in another thread.
 * A common use case of this, is sending the same message to a logger at the same time it is being send to another endpoint.
 * Created by dragos.triteanu on 2/19/16.
 */
public class WireTapRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {

           from("direct:wireTapEndpoint")
                .wireTap("direct:processingEndpoint")
                .delay(2000)
                .to("mock:normalEndpoint");

            from("direct:processingEndpoint")
                .bean(SomeBean.class,"alterModel(${body})")
                .to("mock:tappedEndpoint");
    }
}
