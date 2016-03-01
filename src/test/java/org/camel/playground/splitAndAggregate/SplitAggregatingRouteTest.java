package org.camel.playground.splitAndAggregate;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.camel.playground.splitAndAggregate.route.SplitAggregatingRoute;
import org.junit.Test;

import java.util.*;

/**
 * Created by dragos.triteanu on 3/1/16.
 */
public class SplitAggregatingRouteTest extends CamelTestSupport {

    @Produce(uri = "direct:splittingEndpoint")
    ProducerTemplate in;

    @EndpointInject(uri = "mock:outputEndpoint")
    private MockEndpoint outputEndpoint;


    @Override
    protected RouteBuilder createRouteBuilder(){
        return new SplitAggregatingRoute();
    }


    @Test
    public void shouldSplitArrayAndAggregateProcessedResults() throws InterruptedException {
        List<String> someList = new ArrayList<>(Arrays.asList("One","Two","Three"));
        outputEndpoint.setExpectedMessageCount(1);

        in.sendBody(someList);

        assertMockEndpointsSatisfied();
        Exchange exchange = outputEndpoint.getReceivedExchanges().get(0);

        Set<String> backendResponses = Collections.checkedSet(exchange.getIn().getBody(Set.class),String.class);
        assertTrue(backendResponses.containsAll(Arrays.asList("Processed: One","Processed: Two" , "Processed: Three")));
    }
}
