package org.camel.playground.direct;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.camel.playground.direct.routes.ChoiseRoute;
import org.junit.Test;

/**
 * Created by dragos.triteanu on 2/19/16.
 */
public class ChoiceRoutingTest extends CamelTestSupport {

    @Produce(uri = "direct:choiceEndpoint")
    ProducerTemplate in;

    @EndpointInject(uri = "mock:one")
    private MockEndpoint one;

    @EndpointInject(uri = "mock:two")
    private MockEndpoint two;


    @Override
    protected RouteBuilder createRouteBuilder(){
        return new ChoiseRoute();
    }

    @Test
    public void shouldGoToOne() throws InterruptedException {
        one.setExpectedMessageCount(1);
        two.setExpectedMessageCount(0);
        in.requestBody("direct:choiceEndpoint","goToOne");
        assertMockEndpointsSatisfied();
    }

    @Test
    public void shouldGoToTwo() throws InterruptedException {
        one.setExpectedMessageCount(0);
        two.setExpectedMessageCount(1);
        in.requestBody("direct:choiceEndpoint","goToTwo");
        assertMockEndpointsSatisfied();
    }
}
