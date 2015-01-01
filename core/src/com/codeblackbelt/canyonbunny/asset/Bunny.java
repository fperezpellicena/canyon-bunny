package com.codeblackbelt.canyonbunny.asset;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Bunny {

	public final TextureAtlas.AtlasRegion head;

	public Bunny(TextureAtlas textureAtlas) {
		this.head = textureAtlas.findRegion("bunny_head");
	}
}
