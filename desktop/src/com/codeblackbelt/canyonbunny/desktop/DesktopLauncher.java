package com.codeblackbelt.canyonbunny.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.codeblackbelt.canyonbunny.CanyonBunny;

public class DesktopLauncher {

	public static void main (String[] arg) {
		buildTextureAtlas();
		LwjglApplicationConfiguration config = buildConfiguration();
		new LwjglApplication(new CanyonBunny(), config);
	}

	private static LwjglApplicationConfiguration buildConfiguration() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Canyon Bunny";
		config.useGL30 = false;
		config.width = 800;
		config.height = 480;
		return config;
	}

	private static void buildTextureAtlas() {
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.maxWidth = 1024;
		settings.maxHeight = 1024;
		settings.duplicatePadding = false;
		settings.debug = true;
//		TexturePacker.process(
//				settings,
//				"assets-raw/images",
//				"../android/assets/images",
//				"canyonbunny.pack");
	}
}
