package pt.projeto.batalhadereinos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pt.projeto.batalhadereinos.controller.GameController;

public class BatalhaDeReinos extends Game {
	public GameController game;
	public SpriteBatch batch;
	public BitmapFont font;

	public void create() {
		game = new GameController();
		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font
		game.start(this);
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
