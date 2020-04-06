package be.webtechie.shark.elements;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;

public enum FishSkin {
    BLUE(45,115, Arrays.asList(
            new Image(BitingShark.class.getResource("/fish/blue1.png").toString()),
            new Image(BitingShark.class.getResource("/fish/blue2.png").toString()),
            new Image(BitingShark.class.getResource("/fish/blue3.png").toString()))),
    RED(30, 75, Arrays.asList(
            new Image(BitingShark.class.getResource("/fish/red1.png").toString()))),
    GREY(45, 95, Arrays.asList(
            new Image(BitingShark.class.getResource("/fish/grey1.png").toString()))),
    DARK_GREY(43, 90, Arrays.asList(
            new Image(BitingShark.class.getResource("/fish/darkgrey1.png").toString()))),
    ;

    private final int width;
    private final int height;
    private final List<Image> images;

    FishSkin(int width, int height, List<Image> images) {
        this.width = width;
        this.height = height;
        this.images = images;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Image> getImages() {
        return images;
    }

    public static FishSkin getRandom() {
        Random random = new Random();
        return Collections.unmodifiableList(Arrays.asList(values())).get(random.nextInt(values().length));
    }
}
