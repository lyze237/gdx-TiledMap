package dev.lyze.shared;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.junit.jupiter.api.Assertions;

public class SharedTiledMapLoadingTest {
    public static void loadBasicMap(TiledMap map) {
        TiledMapTileLayer mapLayer = (TiledMapTileLayer) map.getLayers().get(0);

        Assertions.assertEquals(29, mapLayer.getWidth());
        Assertions.assertEquals(20, mapLayer.getHeight());
    }
}
