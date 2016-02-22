package org.camel.playground.activemq;

import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Created by dragos.triteanu on 2/22/16.
 */
public class ActiveMQTest extends CamelSpringTestSupport {


    @EndpointInject(uri = "mock:messageEndpoint")
    private MockEndpoint messageEndpoint;

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("META-INF/spring/messageConsumer.xml");
    }

    @Test
    public void shouldConsumeMessage() {
        final String response =  template.requestBody("activemq:queue:someQueue","SomePayload",String.class);

        assertEquals("Processed:SomePayload",response);
    }

    @Test
    public void shouldProduceAndConsumeMessage() throws InterruptedException {
        messageEndpoint.setExpectedMessageCount(1);
        messageEndpoint.message(0).body().isEqualTo("Processed:SomePayload");
        String somePayload = template.requestBody("direct:activemqSample", "SomePayload", String.class);

        assertEquals("Processed:SomePayload",somePayload);
        assertMockEndpointsSatisfied();
    }

}
