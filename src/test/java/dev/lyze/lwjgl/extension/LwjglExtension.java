package dev.lyze.lwjgl.extension;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import dev.lyze.lwjgl.ApplicationAdapterWrapper;
import org.junit.jupiter.api.Assertions;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LwjglExtension extends BaseLwjglExtension {
    private static CountDownLatch lock = new CountDownLatch(1);

    private static LwjglApplication application;
    private static ApplicationAdapterWrapper wrapper;

    @Override
    void setup() throws InterruptedException {
        wrapper = new ApplicationAdapterWrapper(new ApplicationAdapter() {
            @Override
            public void create() {
                lock.countDown();
            }
        });

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.vSyncEnabled = true;
        config.width = 1280;
        config.height = 720;

        application = new LwjglApplication(wrapper, config);

        Assertions.assertTrue(lock.await(5, TimeUnit.SECONDS));
    }

    @Override
    public void close() throws Throwable {
        application.exit();
    }

    public static ApplicationAdapterWrapper getWrapper() {
        return wrapper;
    }
}
