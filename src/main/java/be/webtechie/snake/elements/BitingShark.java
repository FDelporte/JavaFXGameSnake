package be.webtechie.shark.elements;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BitingShark extends Group {

    private final static Image SHARK_1 = new Image(BitingShark.class.getResource("/shark/shark1.png").toString());
//    private final static Image SHARK_2 =  new Image(BitingShark.class.getResource(".png").toString());
//    private final static Image SHARK_3 =  new Image(BitingShark.class.getResource("3.png").toString());
//    private final static Image SHARK_4 =  new Image(BitingShark.class.getResource("4.png").toString());
//    private final static Image SHARK_5 =  new Image(BitingShark.class.getResource("5.png").toString());

    private static final int SHARK_MOVE_X = 20;

    private final int gameWidth;
    private final int gameHeight;

    public BitingShark(int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        final ImageView shark1 = new ImageView(SHARK_1);
        final ImageView shark2 = new ImageView(SHARK_1);
        final ImageView shark3 = new ImageView(SHARK_1);
        final ImageView shark4 = new ImageView(SHARK_1);
        final ImageView shark5 = new ImageView(SHARK_1);

        this.getChildren().add(shark1);
        shark1.setFitHeight(100);
        shark1.setFitWidth(100);
        shark1.setLayoutY(gameHeight - 100);
    }

    public void setX(int x) {
        this.setLayoutX(x);
    }

    public void moveLeft() {
        if (this.getLayoutX() > 0) {
            this.setLayoutX(this.getLayoutX() - SHARK_MOVE_X);
        }
    }

    public void moveRight() {
        if (this.getLayoutX() < (this.gameWidth - 100)) {
            this.setLayoutX(this.getLayoutX() + SHARK_MOVE_X);
        }
    }
}
