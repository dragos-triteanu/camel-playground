package org.camel.playground.direct;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.camel.playground.direct.routes.FilterRoute;
import org.junit.Test;

/**
 * Created by dragos.triteanu on 2/19/16.
 */
public class FilterRoutingTest extends CamelTestSupport {

    @Produce(uri = "direct:choiceEndpoint")
    ProducerTemplate in;

    @EndpointInject(uri = "mock:filterOut")
    private MockEndpoint filterOut;


    @Override
    protected RouteBuilder createRouteBuilder(){
        return new FilterRoute();
    }

    @Test
    public void shouldPassFilter() throws InterruptedException {
        filterOut.setExpectedMessageCount(1);
        in.requestBody("direct:filterEndpoint","Fuck");
        assertMockEndpointsSatisfied();
    }

    @Test
    public void shouldNotPassFilter() throws InterruptedException {
        filterOut.setExpectedMessageCount(0);
        in.requestBody("direct:filterEndpoint","Suck");
        assertMockEndpointsSatisfied();
    }

}
