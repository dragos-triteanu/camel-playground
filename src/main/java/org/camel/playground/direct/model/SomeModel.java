package org.camel.playground.direct.model;

/**
 * Created by dragos.triteanu on 2/19/16.
 */
public class SomeModel {

    private String someFiled;

    public String getSomeFiled() {
        return someFiled;
    }

    public void setSomeFiled(String someFiled) {
        this.someFiled = someFiled;
    }

    public SomeModel clone() {
        SomeModel model = new SomeModel();
        model.setSomeFiled(this.getSomeFiled());
        return model;
    }

}
