package dev.lyze.lwjgl.tests;

import com.badlogic.gdx.utils.viewport.FitViewport;
import dev.lyze.lwjgl.LwjglMapTest;
import dev.lyze.lwjgl.extension.LwjglExtension;
import dev.lyze.shared.SharedObjectMapLoadingTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(LwjglExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ObjectMapLoadingTest extends LwjglMapTest {
    public ObjectMapLoadingTest() {
        super(new FitViewport(464, 320),"Objects.tmx");
    }

    @Test
    @Tag("lwjgl")
    public void loadObjectMap() {
        SharedObjectMapLoadingTest.loadObjectMap(map);
    }
}
