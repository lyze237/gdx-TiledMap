package dev.lyze.lwjgl;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import dev.lyze.lwjgl.extension.LwjglExtension;
import dev.lyze.lwjgl.utils.ReturnableRunnable;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(LwjglExtension.class)
public class BaseLwjglTest extends ApplicationAdapter {
    @BeforeAll
    public void init() throws InterruptedException {
        System.out.println("Before all");
        CountDownLatch latch = new CountDownLatch(1);
        Gdx.app.postRunnable(() -> {
            System.out.println("In create runnable");
            create();
            resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            LwjglExtension.getWrapper().currentAdapter = this;
            latch.countDown();
        });

        Assertions.assertTrue(latch.await(15, TimeUnit.SECONDS));
    }

    @AfterEach
    public void sleepy() throws InterruptedException {
        System.out.println("After each sleepy");
        Thread.sleep(3000);
    }

    @AfterAll
    public void cleanUp() {
        System.out.println("After all");
        dispose();
    }

    public <T> T runOnOpenGlContext(ReturnableRunnable<T> runnable, long timeout, TimeUnit timeUnit) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        AtomicReference<T> value = new AtomicReference<>();
        Gdx.app.postRunnable(() -> {
            value.set(runnable.run());
            latch.countDown();
        });

        Assertions.assertTrue(latch.await(timeout, timeUnit));
        return value.get();
    }

    public void runOnOpenGlContext(Runnable runnable, long timeout, TimeUnit timeUnit) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Gdx.app.postRunnable(() -> {
            runnable.run();
            latch.countDown();
        });

        Assertions.assertTrue(latch.await(timeout, timeUnit));
    }
}
