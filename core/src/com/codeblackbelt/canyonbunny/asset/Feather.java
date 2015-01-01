package com.codeblackbelt.canyonbunny.asset;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Feather {

	public final TextureAtlas.AtlasRegion feather;

	public Feather(TextureAtlas textureAtlas) {
		this.feather = textureAtlas.findRegion("item_feather");
	}
}
