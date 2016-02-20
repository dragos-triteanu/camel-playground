package org.camel.playground.direct.beans;

import org.camel.playground.direct.model.SomeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dragos.triteanu on 2/18/16.
 */
public class SomeBean {
    private static final Logger LOG = LoggerFactory.getLogger(SomeBean.class);


    public String doSmth(final String body){
        String newBody = String.format("BeanCode(%s)",body);
        LOG.info(newBody);
        return newBody;
    }

    public SomeModel alterModel(SomeModel someModel){
        someModel.setSomeFiled("Altered "+someModel.getSomeFiled());
        return someModel;
    }
}
