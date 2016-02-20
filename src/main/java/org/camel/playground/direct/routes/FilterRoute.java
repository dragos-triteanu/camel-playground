package org.camel.playground.direct.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 * Implementation of the FILTER pattern.
 * Created by dragos.triteanu on 2/19/16.
 */
public class FilterRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("direct:filterEndpoint")
                .filter()
                    .simple("${body} regex '^F.*'")
                    .to("mock:filterOut")
                .end();
    }
}
