package be.webtechie.snake.component;

import be.webtechie.snake.skin.Emotion;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.TransformComponent;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

public class SnakeSkinComponent extends Component {

    // note that this component is injected automatically
    private TransformComponent position;

    private final List<Image> images;

    public SnakeSkinComponent() {
        this.images = new ArrayList<>();
        this.images.add(Emotion.COOL.getImage());
    }

    public List<Image> getImages() {
        return images;
    }

    private double speed = 0;

    @Override
    public void onUpdate(double tpf) {
        speed = tpf * 60;
    }

    public void up() {
        position.translateY(-5 * speed);
    }

    public void down() {
        position.translateY(5 * speed);
    }

    public void left() {
        position.translateX(-5 * speed);
    }

    public void right() {
        position.translateX(5 * speed);
    }
}