package pt.projeto.batalhadereinos;

import com.badlogic.gdx.Game;

import pt.projeto.batalhadereinos.controller.GameScreenMediator;
import pt.projeto.batalhadereinos.controller.IScreenMediator;

public class BatalhaDeReinos extends Game {
	public IScreenMediator gameScreenMediator;

	public void create() {
		this.gameScreenMediator = new GameScreenMediator(this);
		gameScreenMediator.changeScreen("MainMenu");

	}

	public void render() {
		super.render();
	}
}
