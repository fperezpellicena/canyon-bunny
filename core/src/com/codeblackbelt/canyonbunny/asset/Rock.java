package com.codeblackbelt.canyonbunny.asset;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Rock {

	public final TextureAtlas.AtlasRegion edge;
	public final TextureAtlas.AtlasRegion middle;

	public Rock(TextureAtlas textureAtlas) {
		this.edge = textureAtlas.findRegion("rock_edge");
		this.middle = textureAtlas.findRegion("rock_middle");
	}
}
