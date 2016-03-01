package org.camel.playground.transformation.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by dragos.triteanu on 2/22/16.
 */
public class XSLTRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:transformXSLT")
            .to("xslt:xslt/book.xslt");
    }
}
