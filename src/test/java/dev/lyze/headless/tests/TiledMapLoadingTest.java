package dev.lyze.headless.tests;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import dev.lyze.headless.HeadlessTest;
import dev.lyze.shared.SharedTiledMapLoadingTest;
import org.junit.jupiter.api.Test;

public class TiledMapLoadingTest extends HeadlessTest {
    @Test
    public void loadBasicMap() {
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        TiledMap map = tmxMapLoader.load("Basic.tmx");

        SharedTiledMapLoadingTest.loadBasicMap(map);
    }
}
