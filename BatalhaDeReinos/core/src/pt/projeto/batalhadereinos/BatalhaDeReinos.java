package pt.projeto.batalhadereinos;

import com.badlogic.gdx.Game;

import pt.projeto.batalhadereinos.controller.GameScreenController;

public class BatalhaDeReinos extends Game {
	private GameScreenController screenController;

	public void create() {
		screenController = new GameScreenController(this);
		screenController.start();
	}

	public void render() {
		super.render();
	}

	public GameScreenController getScreenController() {
		return screenController;
	}

}
