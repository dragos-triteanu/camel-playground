package org.camel.playground.direct;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.camel.playground.direct.routes.LoadBalancingRoute;
import org.junit.Test;

/**
 * Created by dragos.triteanu on 2/22/16.
 */
public class LoadBalancingRouteTest extends CamelTestSupport {

    @Produce(uri = "direct:loadBalance")
    ProducerTemplate in;

    @EndpointInject(uri = "mock:endpoint1")
    private MockEndpoint endpoint1;

    @EndpointInject(uri = "mock:endpoint2")
    private MockEndpoint endpoint2;

    @EndpointInject(uri = "mock:endpoint3")
    private MockEndpoint endpoint3;


    @Override
    protected RouteBuilder createRouteBuilder(){
        return new LoadBalancingRoute();
    }


    @Test
    public void loadBalancingTest() throws InterruptedException {
        endpoint1.setExpectedMessageCount(3);
        endpoint2.setExpectedMessageCount(2);
        endpoint1.setExpectedMessageCount(2);

        for(int i=0; i<6 ; i++) {
            in.sendBody("direct:loadBalance","somePayload");
        }
        assertMockEndpointsSatisfied();
    }
}
