package org.camel.playground.direct;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.camel.playground.direct.routes.StoppedRoute;
import org.junit.Test;

/**
 * Created by dragos.triteanu on 2/19/16.
 */
public class RouteStoppingTest extends CamelTestSupport {

    @Produce(uri = "direct:stoppedEndpoint")
    ProducerTemplate in;

    @EndpointInject(uri = "mock:stoppedEndpoint")
    private MockEndpoint out;


    @Override
    protected RouteBuilder createRouteBuilder(){
        return new StoppedRoute();
    }

    @Test
    public void shouldStopRoute() throws InterruptedException {
        out.setExpectedMessageCount(1);

        template.requestBody("direct:stoppedEndpoint","stoppedRoute");

        assertMockEndpointsSatisfied();
    }





}
