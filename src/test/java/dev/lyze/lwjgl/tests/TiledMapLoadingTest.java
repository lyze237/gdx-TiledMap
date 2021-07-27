package dev.lyze.lwjgl.tests;

import com.badlogic.gdx.Gdx;
import dev.lyze.lwjgl.LwjglTiledMapPixmapCompareTest;
import dev.lyze.lwjgl.extension.LwjglExtension;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(LwjglExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TiledMapLoadingTest extends LwjglTiledMapPixmapCompareTest {
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
