package org.camel.playground.direct.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.camel.playground.direct.model.SomeModel;

/**
 * Created by dragos.triteanu on 2/22/16.
 */
public class JSONFormattingRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:marshalingEndpoint")
             .marshal().json(JsonLibrary.Gson, SomeModel.class)
                .to("mock:marshalled")
             .unmarshal().json(JsonLibrary.Gson,SomeModel.class)
                .to("mock:unmarshalled");
    }
}
