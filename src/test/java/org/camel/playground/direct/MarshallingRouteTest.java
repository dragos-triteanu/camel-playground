package org.camel.playground.direct;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.camel.playground.direct.model.SomeModel;
import org.camel.playground.direct.routes.JSONFormattingRoute;
import org.junit.Test;

/**
 * Created by dragos.triteanu on 2/22/16.
 */
public class MarshallingRouteTest extends CamelTestSupport {

    @EndpointInject(uri = "mock:marshalled")
    private MockEndpoint marshalled;

    @EndpointInject(uri = "mock:unmarshalled")
    private MockEndpoint unmarshalled;

    @Produce(uri = "direct:marshalingEndpoint")
    ProducerTemplate in;

    @Override
    protected RouteBuilder createRouteBuilder(){
        return new JSONFormattingRoute();
    }


    @Test
    public void shouldMarshall() throws InterruptedException {
        SomeModel model = new SomeModel();
        model.setSomeFiled("someField");

        marshalled.setExpectedMessageCount(1);
        marshalled.message(0).body().isEqualTo("{\"someFiled\":\"someField\"}");
        unmarshalled.setExpectedMessageCount(1);

        in.requestBody(model);
        SomeModel body = unmarshalled.getExchanges().get(0).getIn().getBody(SomeModel.class);
        assertEquals(model.getSomeFiled(),body.getSomeFiled());
        assertMockEndpointsSatisfied();

    }

}
