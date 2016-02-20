package org.camel.playground.direct.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.camel.playground.direct.model.SomeModel;

/**
 * A clone of the initial object must be made, so that the original endpoint does not receive the same processed instance
 * of the object, but a clone of it.
 * Created by dragos.triteanu on 2/19/16.
 */
public class WireTapProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        SomeModel model = in.getBody(SomeModel.class);
        if(model != null){
            in.setBody(model.clone());
        }
    }
}
