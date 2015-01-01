package com.codeblackbelt.canyonbunny.asset;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Decoration {

	public final TextureAtlas.AtlasRegion cloud01;
	public final TextureAtlas.AtlasRegion cloud02;
	public final TextureAtlas.AtlasRegion cloud03;
	public final TextureAtlas.AtlasRegion mountainLeft;
	public final TextureAtlas.AtlasRegion mountainRight;
	public final TextureAtlas.AtlasRegion waterOverlay;

	public Decoration (TextureAtlas atlas) {
		cloud01 = atlas.findRegion("cloud01");
		cloud02 = atlas.findRegion("cloud02");
		cloud03 = atlas.findRegion("cloud03");
		mountainLeft = atlas.findRegion("mountain_left");
		mountainRight = atlas.findRegion("mountain_right");
		waterOverlay = atlas.findRegion("water_overlay");
	}
}
