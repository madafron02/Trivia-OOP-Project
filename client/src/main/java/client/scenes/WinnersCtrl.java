package client.scenes;

import javax.inject.Inject;

public class WinnersCtrl {

    private final MainCtrl mainCtrl;

    @Inject
    public WinnersCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }
}
