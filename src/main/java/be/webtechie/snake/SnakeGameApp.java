package be.webtechie.snake;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameState;

import be.webtechie.snake.component.SnakeSkinComponent;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import java.util.Map;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SnakeGameApp extends GameApplication {

    /**
     * Reference to the factory which will defines how all the types must be created.
     */
    private final SnakeGameFactory sharkGameFactory = new SnakeGameFactory();

    /**
     * Player object we are going to use to provide to the factory so it can start a bullet from the player center.
     */
    private Entity player;

    /**
     * Main entry point where the application starts.
     *
     * @param args Start-up arguments
     */
    public static void main(String[] args) {
        // Launch the FXGL game application
        launch(args);
    }

    /**
     * General game settings. For now only the title is set, but a longer list of options is available.
     *
     * @param settings The settings of the game which can be further extended here.
     */
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Viks Snake Game");
    }

    /**
     * General game variables. Used to hold the points and lives.
     *
     * @param vars The variables of the game which can be further extended here.
     */
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
        vars.put("lives", 5);
    }

    @Override
    protected void initUI() {
        Text scoreLabel = getUIFactoryService().newText("Score", Color.BLACK, 22);
        Text scoreValue = getUIFactoryService().newText("", Color.BLACK, 22);
        Text livesLabel = getUIFactoryService().newText("Lives", Color.BLACK, 22);
        Text livesValue = getUIFactoryService().newText("", Color.BLACK, 22);

        scoreLabel.setTranslateX(20);
        scoreLabel.setTranslateY(20);

        scoreValue.setTranslateX(90);
        scoreValue.setTranslateY(20);

        livesLabel.setTranslateX(getAppWidth() - 100);
        livesLabel.setTranslateY(20);

        livesValue.setTranslateX(getAppWidth() - 30);
        livesValue.setTranslateY(20);

        scoreValue.textProperty().bind(getGameState().intProperty("score").asString());
        livesValue.textProperty().bind(getGameState().intProperty("lives").asString());

        getGameScene().addUINodes(scoreLabel, scoreValue, livesLabel, livesValue);
    }

    /**
     * Input configuration, here you configure all the input events like key presses, mouse clicks, etc.
     */
    @Override
    protected void initInput() {
        onKey(KeyCode.LEFT, () -> this.player.getComponentOptional(SnakeSkinComponent.class).ifPresent(
                SnakeSkinComponent::left));
        onKey(KeyCode.RIGHT, () -> this.player.getComponentOptional(SnakeSkinComponent.class).ifPresent(
                SnakeSkinComponent::right));
        onKey(KeyCode.UP, () -> this.player.getComponentOptional(SnakeSkinComponent.class).ifPresent(
                SnakeSkinComponent::up));
        onKey(KeyCode.DOWN, () -> this.player.getComponentOptional(SnakeSkinComponent.class).ifPresent(
                SnakeSkinComponent::down));
    }

    /**
     * Initialization of the game by providing the {@link EntityFactory}.
     */
    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(this.sharkGameFactory);

        // Add the player
        this.player = spawn("snake", getAppWidth() / 2, getAppHeight() / 2);
        this.player.addComponent(new SnakeSkinComponent());
    }

    @Override
    protected void onUpdate(double tpf) {

    }

    @Override
    protected void initPhysics() {

    }
}