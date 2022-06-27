package pt.projeto.batalhadereinos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pt.projeto.batalhadereinos.controller.GameScreenMediator;
import pt.projeto.batalhadereinos.controller.IScreenMediator;

public class BatalhaDeReinos extends Game {
	public IScreenMediator gameScreenMediator;
	public SpriteBatch batch;
	public BitmapFont font;

	public void create() {
		gameScreenMediator = new GameScreenMediator(this);
		batch = new SpriteBatch();
		
		font = new BitmapFont(); // use libGDX's default Arial font

		gameScreenMediator.changeScreen("MainMenu");
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
