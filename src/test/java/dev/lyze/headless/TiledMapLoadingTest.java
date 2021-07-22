package dev.lyze.headless;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TiledMapLoadingTest extends LibGDXHeadlessUnitTest {
    @Test
    public void loadBasicMap() {
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        TiledMap map = tmxMapLoader.load("Basic.tmx");

        TiledMapTileLayer mapLayer = (TiledMapTileLayer) map.getLayers().get(0);
        Assertions.assertEquals(29, mapLayer.getWidth());
        Assertions.assertEquals(20, mapLayer.getHeight());
    }
}
