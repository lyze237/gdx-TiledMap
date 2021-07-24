package dev.lyze.lwjgl.tests;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import dev.lyze.lwjgl.LwjglTiledMapPixmapCompareTest;
import dev.lyze.lwjgl.extension.LwjglExtension;
import dev.lyze.shared.SharedObjectMapLoadingTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.TimeUnit;

@ExtendWith(LwjglExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ObjectMapLoadingTest extends LwjglTiledMapPixmapCompareTest {
    @Test
    @Tag("lwjgl")
    public void mapObjects() throws InterruptedException {
        TiledMap map = runOnOpenGlContext(() -> new TmxMapLoader().load("Objects.tmx"), 5, TimeUnit.SECONDS);
        SharedObjectMapLoadingTest.loadObjectMap(map);
    }
}
