package client.scenes;

import javax.inject.Inject;

public class WrongCtrl {
    private final MainCtrl mainCtrl;

    @Inject
    public WrongCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }
}
