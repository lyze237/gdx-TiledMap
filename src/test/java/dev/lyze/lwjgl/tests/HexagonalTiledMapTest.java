package dev.lyze.lwjgl.tests;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import dev.lyze.lwjgl.LwjglMapTest;
import dev.lyze.lwjgl.extension.LwjglExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.TimeUnit;

@ExtendWith(LwjglExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HexagonalTiledMapTest extends LwjglMapTest {
    private SpriteBatch batch;
    private BitmapFont font;
    private String text = "Setting up";

    public HexagonalTiledMapTest() {
        super(new FitViewport(464, 320), "Basic.tmx");
    }

    @Override
    public void create() {
        super.create();

        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Test
    @Tag("lwjgl")
    public void fourByFour() throws InterruptedException {
        text = "XXXX\n  XXXX\nXXXX\n  XXXX";
        runOnOpenGlContext(() -> {
            map = new TmxMapLoader().load("Hex4x4.tmx");
            renderer = new HexagonalTiledMapRenderer(map, 1f);
        }, 5, TimeUnit.SECONDS);

        Assertions.assertTrue(true);
    }

    @Test
    @Tag("lwjgl")
    public void threeByThree() throws InterruptedException {
        text = "XXX\n  XXX\nXXX";
        runOnOpenGlContext(() -> {
            map = new TmxMapLoader().load("Hex3x3.tmx");
            renderer = new HexagonalTiledMapRenderer(map, 1f);
        }, 5, TimeUnit.SECONDS);

        Assertions.assertTrue(true);
    }

    @Override
    public void render() {
        super.render();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        font.draw(batch, text, 100, 100);
        batch.end();
    }
}
