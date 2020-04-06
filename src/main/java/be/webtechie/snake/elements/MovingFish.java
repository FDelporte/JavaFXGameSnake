package be.webtechie.shark.elements;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * https://stackoverflow.com/questions/46570494/javafx-changing-the-image-of-an-imageview-using-timeline-doesnt-work
 * https://stackoverflow.com/questions/35949489/keyframe-and-duration-in-javafx-image-slideshow
 */
public class MovingFish extends Group {

    private final FishSkin skin;
    private double moveX;
    private double moveY;
    private final int width;
    private final int height;


    private final ImageView imageView;
    private int imageIndex = 0;
    private final int frameTimeMillis = 150;

    private final Timeline timeline;

    public MovingFish(FishSkin skin, double moveX, double moveY, double startX) {
        this.skin = skin;
        this.moveX = moveX;
        this.moveY = moveY;
        this.width = skin.getWidth();
        this.height = skin.getHeight();

        this.imageView = new ImageView();
        this.imageView.setFitWidth(width);
        this.imageView.setFitHeight(height);
        this.imageView.setX(startX);
        this.imageView.setY(0);
        this.getChildren().add(this.imageView);

        this.timeline = new Timeline(new KeyFrame(Duration.millis(frameTimeMillis), e -> nextImage()));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }

    private void nextImage() {
        this.imageIndex++;
        if (this.imageIndex >= this.skin.getImages().size()) {
            this.imageIndex = 0;
        }
        this.imageView.setImage(this.skin.getImages().get(imageIndex++));
    }

    /**
     * Move the fish, bouncing back back when reaching left or right side.
     *
     * @param bounds Bounds of the screen
     * @return Flag true if reached the bottom of the screen, false if not yet on bottom
     */
    public boolean moveElementDown(Bounds bounds) {
        this.imageView.setX(this.imageView.getX() + moveX);
        this.imageView.setY(this.imageView.getY() + moveY);

        if (this.imageView.getX() <= (bounds.getMinX() + this.width)
                || this.imageView.getX() >= (bounds.getMaxX() - this.width)) {
            moveX = -moveX;
            this.imageView.setScaleX(-1);
        }

        if ((this.imageView.getY() >= (bounds.getMaxY() - this.height))) {
            return true;
        }

        return false;
    }
}
