package com.codeblackbelt.canyonbunny.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.codeblackbelt.canyonbunny.asset.*;
import com.codeblackbelt.canyonbunny.util.Constants;

public class Assets implements Disposable, AssetErrorListener {

	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();

	private AssetManager assetManager;
	private TextureAtlas atlas;

	// Game assets
	public Bunny bunny;
	public Rock rock;
	public Coin coin;
	public Feather feather;
	public Decoration decoration;

	private Assets() {
	}

	public void init() {
		initAssetManager();
		initTextureAtlas();
		initAssets();
	}

	private void initAssetManager() {
		this.assetManager = new AssetManager();
		// Set asset manager error handler
		this.assetManager.setErrorListener(this);
		// Load texture atlas
		this.assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		// Start loading assets and wait until finished
		this.assetManager.finishLoading();
	}

	private void initTextureAtlas() {
		this.atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		for (Texture texture : this.atlas.getTextures()) {
			texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		}
	}

	private void initAssets() {
		bunny = new Bunny(atlas);
		rock = new Rock(atlas);
		coin = new Coin(atlas);
		feather = new Feather(atlas);
		decoration = new Decoration(atlas);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Couldn't load asset '"
				+ asset.fileName + "'", (Exception) throwable);
	}
}
