package dev.lyze.lwjgl;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import dev.lyze.lwjgl.extension.LwjglExtension;
import org.junit.jupiter.api.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseLwjglTest extends ApplicationAdapter {
    @BeforeAll
    public void init() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Gdx.app.postRunnable(() -> {
            create();
            resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            LwjglExtension.getWrapper().currentAdapter = this;
            latch.countDown();
        });

        Assertions.assertTrue(latch.await(15, TimeUnit.SECONDS));
    }

    @AfterEach
    public void sleepy() throws InterruptedException {
        Thread.sleep(1000);
    }

    @AfterAll
    public void cleanUp() {
        dispose();
    }
}
