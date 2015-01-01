package com.codeblackbelt.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.codeblackbelt.canyonbunny.game.Assets;

public class Mountains extends AbstractGameObject {

	private TextureRegion mountainLeft;
	private TextureRegion mountainRight;
	private int length;

	public Mountains (int length) {
		this.length = length;
		init();
	}

	private void init() {
		// 10 meters width * 2 meters tall
		dimension.set(10, 2);
		mountainLeft = Assets.instance.decoration.mountainLeft;
		mountainRight = Assets.instance.decoration.mountainRight;
		// shift mountain and extend length
		origin.x = -dimension.x * 2;
		length += dimension.x * 2;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		// distant mountains (dark gray)
		drawMountain(spriteBatch, 0.5f, 0.5f, 0.5f);
		// distant mountains (gray)
		drawMountain(spriteBatch, 0.25f, 0.25f, 0.7f);
		// distant mountains (light gray)
		drawMountain(spriteBatch, 0.0f, 0.0f, 0.9f);
	}

	private void drawMountain (SpriteBatch batch, float offsetX, float offsetY, float tintColor) {
		batch.setColor(tintColor, tintColor, tintColor, 1);
		float xRel = dimension.x * offsetX;
		float yRel = dimension.y * offsetY;

		// mountains span the whole level
		int mountains = calculateMountains(offsetX);
		for (int i = 0; i < mountains; i++) {
			drawMountainLeft(batch, xRel, yRel);
			xRel += dimension.x;
			drawMountainRight(batch, xRel, yRel);
			xRel += dimension.x;
		}
		// reset color to white
		batch.setColor(1, 1, 1, 1);
	}

	private int calculateMountains(float offsetX) {
		int mountainLength = 0;
		mountainLength += MathUtils.ceil(length / (2 * dimension.x));
		mountainLength += MathUtils.ceil(0.5f + offsetX);
		return mountainLength;
	}

	private void drawMountainRight(SpriteBatch batch, float xRel, float yRel) {
		batch.draw(mountainLeft.getTexture(),
				origin.x + xRel, position.y + origin.y + yRel,
				origin.x, origin.y,
				dimension.x, dimension.y,
				scale.x, scale.y,
				rotation,
				mountainLeft.getRegionX(), mountainLeft.getRegionY(),
				mountainLeft.getRegionWidth(), mountainLeft.getRegionHeight(),
				false, false);
	}

	private void drawMountainLeft(SpriteBatch batch, float xRel, float yRel) {
		batch.draw(mountainRight.getTexture(),
				origin.x + xRel, position.y + origin.y + yRel,
				origin.x, origin.y,
				dimension.x, dimension.y,
				scale.x, scale.y,
				rotation,
				mountainRight.getRegionX(), mountainRight.getRegionY(),
				mountainRight.getRegionWidth(), mountainRight.getRegionHeight(),
				false, false);
	}
}
