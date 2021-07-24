package dev.lyze.lwjgl.tests;

import com.badlogic.gdx.Gdx;
import dev.lyze.lwjgl.LwjglTiledMapPixmapCompareTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class HexagonalTiledMapTest extends LwjglTiledMapPixmapCompareTest {
    @Test
    @Tag("lwjgl")
    public void fourByFour() throws InterruptedException {
        compareMap("4x4", "Hex/4x4.tmx", TiledMapRendererType.Hex, Gdx.files.internal("Hex/4x4.png"));
    }

    @Test
    @Tag("lwjgl")
    public void threeByThree() throws InterruptedException {
        compareMap("3x3", "Hex/3x3.tmx", TiledMapRendererType.Hex, Gdx.files.internal("Hex/3x3.png"));
    }
}
