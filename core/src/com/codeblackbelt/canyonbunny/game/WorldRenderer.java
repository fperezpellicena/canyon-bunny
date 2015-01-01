package com.codeblackbelt.canyonbunny.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.codeblackbelt.canyonbunny.util.Constants;

public class WorldRenderer implements Disposable {

	private static final String TAG = WorldRenderer.class.getName();

	// The rendering is accomplished by using an orthographic camera that is suitable for two-dimensional projections.
	private OrthographicCamera camera;

	// Draws all our objects with respect to the camera's current settings to the screen.
	private SpriteBatch batch;

	private WorldController worldController;

	public WorldRenderer(WorldController worldController) {
		this.worldController = worldController;
		init();
	}

	private void init() {
		batch = new SpriteBatch();
		// Create a camera and define its viewport properly.
		// The camera's viewport defines the size of the captured game world it is looking at.
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();
	}

	/**
	 * Define in which order game objects are drawn over others.
	 */
	public void render() {
		renderTestObjects();
	}

	private void renderTestObjects() {
		worldController.cameraHelper.applyTo(camera);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Sprite sprite : worldController.testSprites) {
			sprite.draw(batch);
		}
		batch.end();
	}

	/**
	 * Calculates the aspect ratio between our desired visible world height
	 * and the currently available display height.
	 * The answer is then multiplied with the available display width
	 * to find the new viewport width for the camera.
	 * The resulting effect of this calculation is that the world's visible height will
	 * always be kept to its full extent, while the world's width will scale accordingly to the
	 * calculated aspect ratio.
	 *
	 * @param width
	 * @param height
	 */
	public void resize(int width, int height) {
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
		camera.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
