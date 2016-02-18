package org.camel.playground.direct;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.camel.playground.direct.routes.SomeRoute;
import org.junit.Test;

/**
 * Created by dragos.triteanu on 2/18/16.
 */
public class SomeTest extends CamelTestSupport {

    @Produce(uri = "direct:prefixBodyWithHello")
    ProducerTemplate in;

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new SomeRoute();
    }

    @Test
    public void someTest(){
        String message = "SomeMessage";
        String response = (String) in.requestBody(message);
        assertEquals("Processed(BeanCode(Hello, SomeMessage))",response);
    }


}
