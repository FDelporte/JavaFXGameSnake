package be.webtechie.snake.skin;

import be.webtechie.snake.elements.SnakeElement;
import javafx.scene.image.Image;

public enum Emotion {
    COOL(new Image(SnakeElement.class.getResource("/emojis/cool.png").toString())),
    DEAD(new Image(SnakeElement.class.getResource("/emojis/dead.png").toString())),
    GREED(new Image(SnakeElement.class.getResource("/emojis/greed.png").toString())),
    HYPNO(new Image(SnakeElement.class.getResource("/emojis/hypnotized.png").toString()));

    private Image image;

    Emotion(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }
}
