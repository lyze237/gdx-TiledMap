package dev.lyze.shared;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import org.junit.jupiter.api.Assertions;

public class SharedObjectMapLoadingTest {
    public static void loadObjectMap(TiledMap map) {
        MapObjects objects = map.getLayers().get(0).getObjects();

        Assertions.assertEquals(RectangleMapObject.class, objects.get("Cool Rectangle").getClass());
        Assertions.assertEquals(CircleMapObject.class, objects.get("Fantastic Circle").getClass());
        Assertions.assertEquals(EllipseMapObject.class, objects.get("Amazing Ellipse").getClass());
        Assertions.assertEquals(PolygonMapObject.class, objects.get("Sweet Polygon").getClass());
        // TODO Text Assertions.assertEquals(CircleMapObject.class, objects.get("Fantastic Rectangle").getClass());
    }
}
