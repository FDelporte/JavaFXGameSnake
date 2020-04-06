package be.webtechie.snake.view;

import be.webtechie.shark.elements.BitingShark;
import be.webtechie.shark.elements.FishSkin;
import be.webtechie.shark.elements.MovingFish;
import be.webtechie.shark.util.RandomHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameView extends Pane {

    /**
     * Drawing on Canvas http://zetcode.com/gui/javafx/canvas/
     * <p>
     * Canvas animation https://mkyong.com/javafx/javafx-animated-ball-example/
     */

    private static final int MAX_FISH = 5;
    private static final int MILLES_BETWEEN_FISH = 200;

    private final int width;
    private final int height;

    private final BitingShark shark;
    private final List<MovingFish> fishList;
    private final Label lbl;
    private final Timeline timeline;

    private long timestampPreviousFish = System.currentTimeMillis();

    private int lives = 5;
    private int points = 0;

    public GameView(int width, int height) {
        this.width = width;
        this.height = height;
        this.setStyle("-fx-background-color: lightblue;");

        this.shark = new BitingShark(width, height);
        this.getChildren().add(this.shark);

        this.lbl = new Label();
        this.lbl.setStyle("-fx-font-size: 18px; -fx-text-fill: darkblue;");
        this.lbl.setLayoutX(10);
        this.lbl.setLayoutY(10);
        this.getChildren().add(this.lbl);

        this.fishList = new ArrayList<>();

        this.timeline = new Timeline(new KeyFrame(Duration.millis(20), this::timelineEvent));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }

    public void handleMouseMoved(MouseEvent e) {
        if (this.lives <= 0) {
            return;
        }
        
        this.shark.setX((int) e.getSceneX());
    }

    public void handleKeyPress(KeyEvent e) {
        if (this.lives <= 0) {
            return;
        }

        if (e.getCode() == KeyCode.LEFT) {
            this.shark.moveLeft();
        } else  if (e.getCode() == KeyCode.RIGHT) {
            this.shark.moveRight();
        }
    }

    private MovingFish createFish() {
        this.timestampPreviousFish = System.currentTimeMillis();

        MovingFish fish = new MovingFish(
                FishSkin.getRandom(),
                RandomHelper.getRandomNumberInRange(1, 5),
                RandomHelper.getRandomNumberInRange(1, 5),
                RandomHelper.getRandomNumberInRange(20, width - 20));
        this.fishList.add(fish);
        this.getChildren().add(fish);
        return fish;
    }

    private void timelineEvent(ActionEvent e) {
        Bounds bounds = this.getBoundsInLocal();
        boolean anyTouchedShark = false;
        boolean anyReachedBottom = false;

        if (this.fishList.size() < (MAX_FISH + (this.points / MAX_FISH))
                && (System.currentTimeMillis() - this.timestampPreviousFish) > MILLES_BETWEEN_FISH) {
            this.createFish();
        }

        for (Iterator<MovingFish> iterator = this.fishList.iterator(); iterator.hasNext();) {
            MovingFish fish = iterator.next();
            boolean touchesShark = checkTouchedShark(fish);
            boolean reachedBottom = fish.moveElementDown(bounds);
            if (touchesShark) {
                anyTouchedShark = true;
                this.points++;
            }
            if (reachedBottom) {
                anyReachedBottom = true;
                this.lives--;
            }
            if (touchesShark || reachedBottom) {
                this.getChildren().remove(fish);
                iterator.remove();
            }
        }

        if (anyReachedBottom) {
            this.setStyle("-fx-background-color: red;");
        } else if (anyTouchedShark) {
            this.setStyle("-fx-background-color: green;");
        }

        this.lbl.setText("Lives: " + this.lives + " - Fish eaten: " + this.points);

        if (this.lives <= 0) {
            Label gameOver = new Label("Game over");
            gameOver.setAlignment(Pos.CENTER);
            gameOver.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: darkred; -fx-stroke: black; -fx-stroke-width: 2px;");
            gameOver.setPrefWidth(this.width);
            gameOver.setLayoutY(this.height / 3);
            this.getChildren().add(gameOver);
            this.timeline.stop();
        }
    }

    private boolean checkTouchedShark(MovingFish fish) {
        return fish.getBoundsInParent().intersects(this.shark.getBoundsInParent());
    }
}
