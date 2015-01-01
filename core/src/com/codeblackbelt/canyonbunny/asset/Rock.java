package com.codeblackbelt.canyonbunny.asset;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Rock {

	private final TextureAtlas.AtlasRegion edgeRock;
	private final TextureAtlas.AtlasRegion middleRock;

	public Rock(TextureAtlas textureAtlas) {
		this.edgeRock = textureAtlas.findRegion("rock_edge");
		this.middleRock = textureAtlas.findRegion("rock_middle");
	}
}
