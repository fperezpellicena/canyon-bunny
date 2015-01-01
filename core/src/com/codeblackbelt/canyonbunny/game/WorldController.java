package com.codeblackbelt.canyonbunny.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.codeblackbelt.canyonbunny.util.CameraHelper;

public class WorldController extends InputAdapter {

	private static final String TAG = WorldController.class.getName();

	// Holds instances of the Sprite objects.
	public Sprite[] testSprites;

	// Holds the index of the currently selected sprite that is	stored in the array.
	public int selectedSprite;

	public CameraHelper cameraHelper;

	public WorldController () {
		init();
	}

	/**
	 * Helpful in many ways when an initialization code is available in a separate method.
	 * This approach can greatly reduce the interruptions by the Garbage Collector (GC).
	 * Instead, we try to actively re-use existing objects, which is always a recommended
	 * design goal to maximize performance and minimize memory usage.
	 * This is especially true for smartphones such as Android with limited resources.
	 */
	private void init () {
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		initGameObjects();
	}

	private void initGameObjects() {
		// Create new array for 5 sprites
		testSprites = new Sprite[5];
		Array<TextureRegion> regions = new Array<TextureRegion>(){{
			add(Assets.instance.bunny.head);
			add(Assets.instance.feather.feather);
			add(Assets.instance.coin.coin);
		}};
		for (int i = 0; i < testSprites.length; i++) {
			Sprite spr = new Sprite(regions.random());
			// Define sprite size to be 1m x 1m in game world
			spr.setSize(1, 1);
			// Set origin to sprite's center
			spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);
			// Calculate random position for sprite
			float randomX = MathUtils.random(-2.0f, 2.0f);
			float randomY = MathUtils.random(-2.0f, 2.0f);
			spr.setPosition(randomX, randomY);
			// Put new sprite into array
			testSprites[i] = spr;
		}

		// Set first sprite as selected one
		selectedSprite = 0;
	}

	private Pixmap createProceduralPixmap (int width, int height) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		// Fill square with red color at 50% opacity
		pixmap.setColor(1, 0, 0, 0.5f);
		pixmap.fill();
		// Draw a yellow-colored X shape on square
		pixmap.setColor(1, 1, 0, 1);
		pixmap.drawLine(0, 0, width, height);
		pixmap.drawLine(width, 0, 0, height);
		// Draw a cyan-colored border around square
		pixmap.setColor(0, 1, 1, 1);
		pixmap.drawRectangle(0, 0, width, height);
		return pixmap;
	}

	/**
	 * Contain the game logic and will be called several hundred times per second.
	 *
	 * @param deltaTime It requires a delta time so that it can apply updates to the game world
	 *                  according to the fraction of time that has passed since the last rendered frame.
	 */
	public void update (float deltaTime) {
		handleDebugInput(deltaTime);
		updateTestObjects(deltaTime);
		cameraHelper.update(deltaTime);
	}

	private void updateTestObjects (float deltaTime) {
		// Get current rotation from selected sprite
		float rotation = testSprites[selectedSprite].getRotation();
		// Rotate sprite by 90 degrees per second
		rotation += 90 * deltaTime;
		// Wrap around at 360 degrees
		rotation %= 360;
		// Set new rotation value to selected sprite
		testSprites[selectedSprite].setRotation(rotation);
	}

	private void handleDebugInput (float deltaTime) {
		if (Gdx.app.getType() != ApplicationType.Desktop){
			return;
		}

		// Selected Sprite Controls
		// Move the sprite at 5 m/s in the direction given by the key
		float sprMoveSpeed = 5 * deltaTime;
		if (Gdx.input.isKeyPressed(Keys.A)) {
			// -5 m/s in X axis
			moveSelectedSprite(-sprMoveSpeed, 0);
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			// 5 m/s in X axis
			moveSelectedSprite(sprMoveSpeed, 0);
		}
		if (Gdx.input.isKeyPressed(Keys.W)) {
			// 5 m/s in Y axis
			moveSelectedSprite(0, sprMoveSpeed);
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			// -5 m/s in Y axis
			moveSelectedSprite(0, -sprMoveSpeed);
		}

		// Camera Controls (move)
		float camMoveSpeed = 5 * deltaTime;
		float camMoveSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			camMoveSpeed *= camMoveSpeedAccelerationFactor;
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			moveCamera(-camMoveSpeed, 0);
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			moveCamera(camMoveSpeed, 0);
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			moveCamera(0, camMoveSpeed);
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			moveCamera(0, -camMoveSpeed);
		}
		if (Gdx.input.isKeyPressed(Keys.BACKSPACE)) {
			cameraHelper.setPosition(0, 0);
		}

		// Camera Controls (zoom)
		float camZoomSpeed = 1 * deltaTime;
		float camZoomSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			camZoomSpeed *= camZoomSpeedAccelerationFactor;
		}
		if (Gdx.input.isKeyPressed(Keys.COMMA)) {
			cameraHelper.addZoom(camZoomSpeed);
		}
		if (Gdx.input.isKeyPressed(Keys.PERIOD)) {
			cameraHelper.addZoom(-camZoomSpeed);
		}
		if (Gdx.input.isKeyPressed(Keys.SLASH)) {
			cameraHelper.setZoom(1);
		}
	}

	private void moveSelectedSprite (float xSpeed, float ySpeed) {
		testSprites[selectedSprite].translate(xSpeed, ySpeed);
	}

	private void moveCamera (float x, float y) {
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x, y);
	}

	@Override
	public boolean keyUp (int keycode) {
		if (keycode == Keys.R) {
			// Reset game world
			init();
			Gdx.app.debug(TAG, "Game world resetted");
		} else if (keycode == Keys.SPACE) {
			// Select next sprite
			selectedSprite = (selectedSprite + 1) % testSprites.length;
			// Update camera's target to follow the currently selected sprite
			if (cameraHelper.hasTarget()) {
				cameraHelper.setTarget(testSprites[selectedSprite]);
			}
			Gdx.app.debug(TAG, "Sprite #" + selectedSprite + " selected");
		} else if (keycode == Keys.ENTER) {
			// Toggle camera follow
			cameraHelper.setTarget(cameraHelper.hasTarget() ? null : testSprites[selectedSprite]);
			Gdx.app.debug(TAG, "Camera follow enabled: " + cameraHelper.hasTarget());
		}
		return false;
	}
}
