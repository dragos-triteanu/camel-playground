package org.camel.playground.exceptions;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.camel.playground.exceptions.route.RetryErrorHandlerRoute;
import org.junit.Test;

/**
 * Created by dragos.triteanu on 3/1/16.
 */
public class RetryErrorHandlerRouteTest extends CamelTestSupport {

    @Produce(uri = "direct:errors")
    ProducerTemplate in;

    @EndpointInject(uri = "mock:resultEndpoint")
    private MockEndpoint resultEndpoint;


    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RetryErrorHandlerRoute();
    }


    @Test
    public void shouldRetrySendingOperation() throws InterruptedException {
        resultEndpoint.setExpectedMessageCount(1);

        resultEndpoint.allMessages().header(Exchange.REDELIVERED).isEqualTo(true);
        resultEndpoint.allMessages().header(Exchange.REDELIVERY_COUNTER).isEqualTo(2);
        resultEndpoint.allMessages().header(Exchange.REDELIVERY_MAX_COUNTER).isEqualTo(2);
        resultEndpoint.allMessages().header(Exchange.REDELIVERY_DELAY).isNull();

        in.sendBody("SomeBody");

        assertMockEndpointsSatisfied();

    }
}
