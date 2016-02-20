package org.camel.playground.direct.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 * Implementation of the ROUTER pattern.
 * Created by dragos.triteanu on 2/19/16.
 */
public class ChoiseRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("direct:choiceEndpoint")
           .choice()
                .when()
                    .simple("${body} contains 'goToOne'")
                    .to("mock:one")
                .otherwise()
                    .to("mock:two")
           .endChoice();

    }
}
