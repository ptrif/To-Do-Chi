package sample.Animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class ShakeItUp {
    private TranslateTransition translateTransition;

    public ShakeItUp(Node node) {
        translateTransition = new TranslateTransition(Duration.millis(60), node);

        translateTransition.setFromY(0f);
        translateTransition.setByY(40f);
        translateTransition.setCycleCount(4);
        translateTransition.setAutoReverse(true);

    }

    public void shake(){
        translateTransition.playFromStart();
    }
}
