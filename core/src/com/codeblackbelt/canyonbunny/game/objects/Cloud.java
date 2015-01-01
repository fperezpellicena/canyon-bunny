package com.codeblackbelt.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class Cloud extends AbstractGameObject {

	private TextureRegion cloud;

	public Cloud() {
	}

	public void setRegion (TextureRegion region) {
		cloud = region;
	}

	@Override
	public void render (SpriteBatch batch) {
		batch.draw(cloud.getTexture(),
				position.x + origin.x, position.y + origin.y,
				origin.x, origin.y,
				dimension.x, dimension.y,
				scale.x, scale.y,
				rotation,
				cloud.getRegionX(), cloud.getRegionY(),
				cloud.getRegionWidth(), cloud.getRegionHeight(),
				false, false);
	}
}
