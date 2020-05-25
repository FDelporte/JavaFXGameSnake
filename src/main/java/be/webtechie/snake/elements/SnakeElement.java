package be.webtechie.snake.elements;

import javafx.scene.image.Image;

public enum SnakeElement {
    COOL(new Image(SnakeElement.class.getResource("/emojis/cool.png").toString())),
    DEAD(new Image(SnakeElement.class.getResource("/emojis/dead.png").toString())),
    GREED(new Image(SnakeElement.class.getResource("/emojis/greed.png").toString())),
    HYPNO(new Image(SnakeElement.class.getResource("/emojis/hypnotized.png").toString()));

    private final Image image;

    SnakeElement(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }
}
