package lwjgl;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LibGDXLwjglUnitTest extends ApplicationAdapter {
    private Application application;

    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeEach
    public void init() throws InterruptedException {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.vSyncEnabled = true;
        config.width = 1280;
        config.height = 720;

        application = new LwjglApplication(this, config);

        lock.await(5, TimeUnit.SECONDS);
    }

    @Override
    public void create() {
        lock.countDown();
    }

    @AfterEach
    public void cleanUp() {
        application.exit();
    }
}
