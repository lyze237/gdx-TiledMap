package dev.lyze.lwjgl;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.concurrent.TimeUnit;

public abstract class LwjglTiledMapPixmapCompareTest extends BaseLwjglTest {
    private Stage stage;

    private Label title;
    private Label status;
    private Image expected, actual;
    private Image expectedStack, actualStack, differenceStack;

    @Override
    public void create() {
        super.create();

        stage = new Stage(new FitViewport(1280, 540));

        Table table = new Table();
        table.defaults().pad(12);
        table.setFillParent(true);
        table.setFillParent(true);

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.WHITE;

        table.add(title = new Label("Title", style)).expandX().colspan(3).row();
        table.add(new Label("Expected", style)).expandX();
        table.add(new Label("Actual", style)).expandX();
        table.add(new Label("Stack", style)).expandX().row();

        expected = new Image();
        expected.setScaling(Scaling.fit);
        actual = new Image();
        actual.setScaling(Scaling.fit);

        Stack stack = new Stack();
        expectedStack = new Image();
        expectedStack.setScaling(Scaling.fit);
        actualStack = new Image();
        actualStack.setScaling(Scaling.fit);
        differenceStack = new Image();
        differenceStack.setScaling(Scaling.fit);
        actualStack.setColor(1, 1, 1, 0.5f);
        differenceStack.setColor(1, 1, 1, 0.5f);

        stack.add(expectedStack);
        stack.add(actualStack);
        stack.add(differenceStack);

        table.add(expected).grow();
        table.add(actual).grow();
        table.add(stack).grow().row();

        table.add(status = new Label("Nothing defined", style)).expandX().colspan(3);

        stage.addActor(table);
    }

    @BeforeEach
    public void reset() throws InterruptedException {
        runOnOpenGlContext(this::create, 5, TimeUnit.SECONDS);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);

        stage.getViewport().apply();

        stage.act();
        stage.draw();
    }

    public void compareMap(String title, String tiledMap, TiledMapRendererType rendererType, FileHandle expectedImageFileHandle) throws InterruptedException {
        final Pixmap expectedPixmap = new Pixmap(expectedImageFileHandle);

        this.title.setText(title + " (" + tiledMap + ", " + rendererType + ")");

        boolean result = runOnOpenGlContext(() -> {
            Pixmap actualPixmap = renderMap(tiledMap, rendererType, expectedPixmap);

            Pixmap differencePixmap = new Pixmap(expectedPixmap.getWidth(), expectedPixmap.getHeight(), expectedPixmap.getFormat());
            boolean isEqual = isPixmapEqual(expectedPixmap, actualPixmap, differencePixmap);

            status.setText(isEqual ? "Correct" : "Incorrect");
            status.setColor(isEqual ? Color.CYAN : Color.RED);

            this.expected.setDrawable(new TextureRegionDrawable(new Texture(expectedPixmap)));
            this.actual.setDrawable(new TextureRegionDrawable(new Texture(actualPixmap)));
            this.expectedStack.setDrawable(new TextureRegionDrawable(new Texture(expectedPixmap)));
            this.actualStack.setDrawable(new TextureRegionDrawable(new Texture(actualPixmap)));
            this.differenceStack.setDrawable(new TextureRegionDrawable(new Texture(differencePixmap)));

            return isEqual;
        }, 15, TimeUnit.SECONDS);

        Assertions.assertTrue(result, "Pixel difference in map");
    }

    private Pixmap renderMap(String tiledMap, TiledMapRendererType rendererType, Pixmap expected) {
        FrameBuffer buffer = new FrameBuffer(Pixmap.Format.RGBA8888, expected.getWidth(), expected.getHeight(), false);

        OrthographicCamera fboCamera = new OrthographicCamera();
        fboCamera.setToOrtho(true, expected.getWidth(), expected.getHeight());

        buffer.begin();
        ScreenUtils.clear(Color.CLEAR);

        TiledMap map = new TmxMapLoader().load(tiledMap);
        TiledMapRenderer actualRenderer = rendererType.generate(map);

        actualRenderer.setView(fboCamera);
        actualRenderer.render();

        Pixmap pixmap = Pixmap.createFromFrameBuffer(0, 0, expected.getWidth(), expected.getHeight());
        buffer.end();
        buffer.dispose();

        return pixmap;
    }

    public boolean isPixmapEqual(Pixmap expected, Pixmap actual, Pixmap difference) {
        Assertions.assertEquals(expected.getWidth(), actual.getWidth());
        Assertions.assertEquals(expected.getHeight(), actual.getHeight());

        boolean error = false;

        for (int x = 0; x < expected.getWidth(); x++) {
            for (int y = 0; y < expected.getHeight(); y++) {
                if (expected.getPixel(x, y) != actual.getPixel(x, y)) {
                    System.err.println("Pixel difference at " + x + "/" + y + " => expected: " + expected.getPixel(x, y) + " but was: " + actual.getPixel(x, y));

                    difference.setColor(Color.RED);
                    difference.drawPixel(x, y);

                    error = true;
                }
            }
        }

        return !error;
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public enum TiledMapRendererType {
        Ortho, Hex, Cached;

        public TiledMapRenderer generate(TiledMap map) {
            switch (this) {
                case Hex:
                    return new HexagonalTiledMapRenderer(map, 1f);
                case Ortho:
                    return new OrthogonalTiledMapRenderer(map, 1f);
                case Cached:
                    OrthoCachedTiledMapRenderer renderer = new OrthoCachedTiledMapRenderer(map, 1f);
                    renderer.setBlending(true);
                    return renderer;
            }

            return null;
        }
    }
}
