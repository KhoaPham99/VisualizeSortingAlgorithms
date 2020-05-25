package cnode;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CNode extends Rectangle {

    private int value;
    public static int timeSpd;
    public CNode(int n) {
        this.value = n;
    }

    public int getValue() {
        return this.value;
    }

    public TranslateTransition moveX(int x) {
        TranslateTransition t = new TranslateTransition();
        t.setNode(this);
        t.setDuration(Duration.millis(timeSpd));
        t.setByX(x);

        return t;
    }
}
