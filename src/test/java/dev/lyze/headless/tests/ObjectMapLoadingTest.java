package dev.lyze.headless.tests;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import dev.lyze.headless.HeadlessTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ObjectMapLoadingTest extends HeadlessTest {
    /*
    @Test
    public void loadObjectMap() {
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        TiledMap map = tmxMapLoader.load("Objects.tmx");
    
        MapObjects objects = map.getLayers().get(0).getObjects();
    
        Assertions.assertEquals(RectangleMapObject.class, objects.get("Cool Rectangle").getClass());
        Assertions.assertEquals(CircleMapObject.class, objects.get("Fantastic Circle").getClass());
        Assertions.assertEquals(EllipseMapObject.class, objects.get("Amazing Ellipse").getClass());
        Assertions.assertEquals(PolygonMapObject.class, objects.get("Sweet Polygon").getClass());
        // TODO Text Assertions.assertEquals(CircleMapObject.class, objects.get("Fantastic Rectangle").getClass());
    }
    
    @Test
    @Disabled // FIXME Fixed in 1.10.1
    public void loadSymbolsInProperties() {
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        TiledMap map = tmxMapLoader.load("Objects.tmx");
    
        MapObjects objects = map.getLayers().get(0).getObjects();
        MapObject obj = objects.get("\"'<>&");
    
        Assertions.assertNotNull(map.getLayers().get("\"'<>&"));
    
        Assertions.assertEquals("\"'<>&",  obj.getName(), "name");
        Assertions.assertEquals("\"'<>&",  obj.getProperties().get("type", String.class), "type");
        Assertions.assertEquals("\"'<>&",  obj.getProperties().get("\"'<>&", String.class), "custom property");
    }
     */
}
