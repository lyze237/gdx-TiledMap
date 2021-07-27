package dev.lyze.headless.tests;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import dev.lyze.headless.HeadlessTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TiledMapLoadingTest extends HeadlessTest {
    @Test
    public void mapProperties() {
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        TiledMap map = tmxMapLoader.load("Basic.tmx");

        TiledMapTileLayer mapLayer = (TiledMapTileLayer) map.getLayers().get(0);

        Assertions.assertEquals(29, mapLayer.getWidth());
        Assertions.assertEquals(20, mapLayer.getHeight());
    }
}
