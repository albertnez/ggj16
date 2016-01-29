package com.mygdx.ritualggj16.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.ritualggj16.Gaem;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = 1024;
        config.height = 768;
        config.resizable = false;
        config.vSyncEnabled = false;
        config.foregroundFPS = 0;
        config.backgroundFPS = 0;

        config.fullscreen = false;

		new LwjglApplication(new Gaem(), config);
	}
}
