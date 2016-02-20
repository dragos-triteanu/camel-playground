package org.camel.playground.direct;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.ValueBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.camel.playground.direct.model.SomeModel;
import org.camel.playground.direct.routes.WireTapRoute;
import org.junit.Test;

/**
 * Created by dragos.triteanu on 2/19/16.
 */
public class WireTapTest extends CamelTestSupport {


    @Produce(uri = "direct:wireTapEndpoint")
    ProducerTemplate in;

    @EndpointInject(uri = "mock:normalEndpoint")
    private MockEndpoint normalEndpoint;

    @EndpointInject(uri = "mock:tappedEndpoint")
    private MockEndpoint tappedEndpoint;


    @Override
    protected RouteBuilder createRouteBuilder(){
        return new WireTapRoute();
    }

    @Test
    public void shouldWireTap() throws InterruptedException {
        SomeModel model = new SomeModel();
        model.setSomeFiled("SomeField");
        normalEndpoint.message(0).body().isEqualTo(model);

        in.requestBody("direct:wireTapEndpoint",model);
        SomeModel body = tappedEndpoint.getExchanges().get(0).getIn().getBody(SomeModel.class);
        assertEquals("Altered SomeField", body.getSomeFiled());
        assertMockEndpointsSatisfied();
    }
}
