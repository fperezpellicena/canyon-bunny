package com.codeblackbelt.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.codeblackbelt.canyonbunny.game.Assets;

public class Clouds extends AbstractGameObject {

	private static final int DISTANCE_BETWEEN_CLOUDS = 5;

	private Array<TextureRegion> cloudTextures;
	private Array<Cloud> clouds;
	private float length;

	public Clouds(float length) {
		this.length = length;
		init();
	}

	private void init() {
		dimension.set(3.0f, 1.5f);
		loadCloudTextures();
		prepareCloudObjects();
	}

	private void loadCloudTextures() {
		cloudTextures = new Array<TextureRegion>();
		cloudTextures.add(Assets.instance.decoration.cloud01);
		cloudTextures.add(Assets.instance.decoration.cloud02);
		cloudTextures.add(Assets.instance.decoration.cloud03);
	}

	private void prepareCloudObjects() {
		int numClouds = (int) (length / DISTANCE_BETWEEN_CLOUDS);
		clouds = new Array<Cloud>(2 * numClouds);
		for (int i = 0; i < numClouds; i++) {
			clouds.add(spawnCloud(i));
		}
	}

	private Cloud spawnCloud(int index) {
		Cloud cloud = new Cloud();
		cloud.dimension.set(dimension);
		// select random cloud image
		cloud.setRegion(cloudTextures.random());
		// position
		Vector2 pos = new Vector2();
		pos.x = index * DISTANCE_BETWEEN_CLOUDS;
		pos.y += 1.75; // base position
		pos.y += MathUtils.random(0.0f, 0.2f) * (MathUtils.randomBoolean() ? 1 : -1); // random additional position
		cloud.position.set(pos);
		return cloud;
	}

	@Override
	public void render(SpriteBatch batch) {
		for (Cloud cloud : clouds) {
			cloud.render(batch);
		}
	}
}
