package lwjgl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TiledMapLoadingTest extends LibGDXLwjglUnitTest {
    private final Viewport viewport = new FitViewport(464, 320);

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    @Test
    @Tag("lwjgl")
    public void loadBasicMap() {
        TiledMapTileLayer mapLayer = (TiledMapTileLayer) map.getLayers().get(0);

        Assertions.assertEquals(29, mapLayer.getWidth());
        Assertions.assertEquals(20, mapLayer.getHeight());
    }

    @Override
    public void create() {
        map = new TmxMapLoader().load("Basic.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1f);

        super.create();
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);

        viewport.apply();

        renderer.setView((OrthographicCamera) viewport.getCamera());
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
