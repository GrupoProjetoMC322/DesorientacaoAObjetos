package pt.projeto.batalhadereinos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pt.projeto.batalhadereinos.controller.GameScreenController;

public class BatalhaDeReinos extends Game {
	public GameScreenController gameScreenController;
	public SpriteBatch batch;
	public BitmapFont font;

	public void create() {
		gameScreenController = new GameScreenController(this);
		batch = new SpriteBatch();
		
		font = new BitmapFont(); // use libGDX's default Arial font

		gameScreenController.start();
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
