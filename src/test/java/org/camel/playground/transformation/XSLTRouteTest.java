package org.camel.playground.transformation;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.camel.playground.transformation.route.XSLTRoute;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by dragos.triteanu on 2/22/16.
 */
public class XSLTRouteTest extends CamelTestSupport {

    @Produce(uri = "direct:transformXSLT")
    ProducerTemplate in;

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new XSLTRoute();
    }

    @Test
    public void shouldTransform() {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("xml/bookstore.xml");
        final String request = context().getTypeConverter().convertTo(String.class,resourceAsStream);

        String response = in.requestBody("direct:transformXSLT",request,String.class);

        log.info("Response = {}",response);
    }
}
