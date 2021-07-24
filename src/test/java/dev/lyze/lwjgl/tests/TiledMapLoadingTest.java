package dev.lyze.lwjgl.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import dev.lyze.lwjgl.LwjglTiledMapPixmapCompareTest;
import dev.lyze.lwjgl.extension.LwjglExtension;
import dev.lyze.shared.SharedTiledMapLoadingTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.TimeUnit;

@ExtendWith(LwjglExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TiledMapLoadingTest extends LwjglTiledMapPixmapCompareTest {
    @Test
    @Tag("lwjgl")
    public void mapProperties() throws InterruptedException {
        TiledMap map = runOnOpenGlContext(() -> new TmxMapLoader().load("Basic.tmx"), 5, TimeUnit.SECONDS);
        SharedTiledMapLoadingTest.loadBasicMap(map);
    }
    @Test
    @Tag("lwjgl")
    public void basicComparison() throws InterruptedException {
        compareMap("Ortho", "Basic.tmx", TiledMapRendererType.Ortho, Gdx.files.internal("Basic.png"));
    }

    @Test
    @Tag("lwjgl")
    public void basicCachedComparison() throws InterruptedException {
        compareMap("Cached", "Basic.tmx", TiledMapRendererType.Cached, Gdx.files.internal("Basic.png"));
    }
}
