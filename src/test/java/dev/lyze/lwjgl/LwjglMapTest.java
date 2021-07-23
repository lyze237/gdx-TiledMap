package dev.lyze.lwjgl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class LwjglMapTest extends BaseLwjglTest {
    protected final Viewport viewport;

    protected TiledMap map;
    protected OrthogonalTiledMapRenderer renderer;

    private String mapString;

    public LwjglMapTest(Viewport viewport, String map) {
        this.viewport = viewport;
        this.mapString = map;
    }

    @Override
    public void create() {
        map = new TmxMapLoader().load(mapString);
        renderer = new OrthogonalTiledMapRenderer(map, 1f);
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
