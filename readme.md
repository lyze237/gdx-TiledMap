# gdx-TiledMap

A libgdx parser for tiled https://www.mapeditor.org/

[![Build, Test, Publish](https://github.com/lyze237/gdx-TiledMap/workflows/Test/badge.svg?branch=main)](https://github.com/lyze237/gdx-TiledMap/actions?query=workflow%3A%22Test%22)
[![License](https://img.shields.io/github/license/lyze237/gdx-TiledMap)](https://github.com/lyze237/gdx-TiledMap/blob/main/LICENSE)
[![Jitpack](https://jitpack.io/v/lyze237/gdx-TiledMap.svg)](https://jitpack.io/#lyze237/gdx-TiledMap)

# What's this about?

The current libgdx implementation is kinda wonky, broken and unmaintained.

This is a project to re-implement the tiled map parser and make it up to date.

# Installation

1. Open or create `gradle.properties` in the root folder of your project, add the following line:

```properties
gdxTiledMapVersion=VERSION
```

Check [Jitpack](https://jitpack.io/#lyze237/gdx-TiledMap/) for the latest version and replace `VERSION` with that.

2. Add the jitpack repo to your build file.

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

3. Add that to your core modules dependencies inside your root `build.gradle`

```groovy
project(":core") {
    ...

    dependencies {
        ...
        implementation "com.github.lyze237:gdx-TiledMap:$gdxTiledMapVersion"
    }
}
```

## Html/Gwt project

1. Gradle dependency:

```groovy
implementation "com.github.lyze237:gdx-TiledMap:$gdxTiledMapVersion:sources"
```

2. In your application's `.gwt.xml` file add (Normally `GdxDefinition.gwt.xml`):

```xml

<inherits name="dev.lyze.tiledmap"/>
```

## How to test

By default, if you run `./gradlew test` gradle runs headless tests. If you want to test `lwjgl` tests (so with an actual
gui), then you need to run them with `./gradlew test -Plwjgl=true`

Set environment variable `SLEEPY` to a millisecond number to sleep between each LWJGL test. (For example: SLEEPY=3000 would wait 3 seconds after every test.)

## Credits

Test TileMap tiles: https://opengameart.org/content/a-platformer-in-the-forest
