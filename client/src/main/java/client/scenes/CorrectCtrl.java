package client.scenes;

import com.google.inject.Inject;

public class CorrectCtrl {
    private final MainCtrl mainCtrl;

    @Inject
    public CorrectCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    //to do: timeout of about 5-10 seconds until next question appears
}
