package dev.lyze.headless.tests;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import dev.lyze.headless.HeadlessTest;
import dev.lyze.shared.SharedObjectMapLoadingTest;
import org.junit.jupiter.api.Test;

public class ObjectMapLoadingTest extends HeadlessTest {
    @Test
    public void mapObjects() {
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        TiledMap map = tmxMapLoader.load("Objects.tmx");

        SharedObjectMapLoadingTest.loadObjectMap(map);
    }
}
