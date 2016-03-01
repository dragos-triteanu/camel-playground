package org.camel.playground.exceptions.route;

import org.apache.camel.builder.RouteBuilder;
import org.camel.playground.exceptions.processor.ErrorThrowingProcessor;

/**
 * Created by dragos.triteanu on 3/1/16.
 */
public class RetryErrorHandlerRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        errorHandler(defaultErrorHandler().maximumRedeliveries(2));
//        errorHandler(defaultErrorHandler().retryWhile(simple("${header.retryCount} < 2 ")));
        from("direct:errors")
                .bean(ErrorThrowingProcessor.class)
                .to("mock:resultEndpoint");
    }
}
