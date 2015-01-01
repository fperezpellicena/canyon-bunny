package com.codeblackbelt.canyonbunny.asset;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Coin {

	public final TextureAtlas.AtlasRegion coin;

	public Coin(TextureAtlas textureAtlas) {
		this.coin = textureAtlas.findRegion("item_gold_coin");
	}
}
