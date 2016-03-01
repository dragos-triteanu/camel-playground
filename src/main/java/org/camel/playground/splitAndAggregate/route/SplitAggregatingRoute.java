package org.camel.playground.splitAndAggregate.route;

import org.apache.camel.builder.RouteBuilder;
import org.camel.playground.splitAndAggregate.aggregation.SetAggregationStrategy;

/**
 * Created by dragos.triteanu on 3/1/16.
 */
public class SplitAggregatingRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:splittingEndpoint")
                .split(body(), new SetAggregationStrategy())
                    .inOut("direct:backendEndpoint")
                .end()
                .to("mock:outputEndpoint");

        from("direct:backendEndpoint")
                .transform(simple("Processed: ${body}"));
    }
}
