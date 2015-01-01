package com.codeblackbelt.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.codeblackbelt.canyonbunny.game.Assets;

public class Rock extends AbstractGameObject {

	private TextureRegion edge;
	private TextureRegion middle;

	// Number of middle parts
	private int length;

	public Rock() {
		init();
	}

	private void init() {
		// 1 meter width x 1.5 meters tall
		dimension.set(1, 1.5f);
		edge = Assets.instance.rock.edge;
		middle = Assets.instance.rock.middle;
		length = 1;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		drawLeftEdge(spriteBatch);
		drawMiddle(spriteBatch);
		drawRightEdge(spriteBatch);
	}

	private void drawLeftEdge(SpriteBatch spriteBatch) {
		float relX = -dimension.x / 4;
		float relY = 0;
		spriteBatch.draw(edge.getTexture(),
				position.x + relX, position.y + relY,
				origin.x, origin.y,
				dimension.x / 4, dimension.y,
				scale.x, scale.y,
				rotation,
				edge.getRegionX(), edge.getRegionY(),
				edge.getRegionWidth(), edge.getRegionHeight(),
				false,false);
	}

	private void drawMiddle(SpriteBatch spriteBatch) {
		float relX = 0;
		float relY = 0;
		for (int i = 0; i < length; i++) {
			spriteBatch.draw(middle.getTexture(),
					position.x + relX, position.y + relY,
					origin.x, origin.y,
					dimension.x, dimension.y,
					scale.x, scale.y,
					rotation,
					middle.getRegionX(), middle.getRegionY(),
					middle.getRegionWidth(), middle.getRegionHeight(),
					false, false);
			relX += dimension.x;
		}
	}

	private void drawRightEdge(SpriteBatch spriteBatch) {
		float relX = length * dimension.x;
		float relY = 0;
		spriteBatch.draw(edge.getTexture(),
				position.x + relX, position.y + relY,
				origin.x + dimension.x / 8, origin.y,
				dimension.x / 4, dimension.y,
				scale.x, scale.y,
				rotation,
				edge.getRegionX(), edge.getRegionY(),
				edge.getRegionWidth(), edge.getRegionHeight(),
				true, false);
	}
}
