package com.codeblackbelt.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.codeblackbelt.canyonbunny.game.Assets;

public class Water extends AbstractGameObject {

	private TextureRegion waterOverlay;
	private float length;

	public Water (float length) {
		this.length = length;
		init();
	}

	private void init () {
		dimension.set(length * 10, 3);
		waterOverlay = Assets.instance.decoration.waterOverlay;
		origin.x = -dimension.x / 2;
	}

	@Override
	public void render (SpriteBatch batch) {
		batch.draw(waterOverlay.getTexture(),
				position.x + origin.x, position.y + origin.y,
				origin.x, origin.y,
				dimension.x, dimension.y,
				scale.x, scale.y,
				rotation,
				waterOverlay.getRegionX(), waterOverlay.getRegionY(),
				waterOverlay.getRegionWidth(), waterOverlay.getRegionHeight(),
				false, false);
	}
}
