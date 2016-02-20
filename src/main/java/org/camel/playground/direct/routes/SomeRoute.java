package org.camel.playground.direct.routes;

import org.apache.camel.builder.RouteBuilder;
import org.camel.playground.direct.beans.SomeBean;
import org.camel.playground.direct.processors.SomeProcessor;

/**
 * Implementation that exemplifies the use of multiple routes, with logging , bean processing and processor processing.
 * Created by dragos.triteanu on 2/18/16.
 */
public class SomeRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:prefixBodyWithHello")
          .transform(simple("Hello, ${body}"))
          .bean(SomeBean.class,"doSmth(${body})")
          .process(new SomeProcessor())
          .to("direct:prefixed");

        from("direct:prefixed")
                .log("${body}");
    }
}
