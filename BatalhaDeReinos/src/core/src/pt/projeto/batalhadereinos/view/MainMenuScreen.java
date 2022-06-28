package pt.projeto.batalhadereinos.view;

import com.badlogic.gdx.Gdx;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.controller.IScreenMediator;

public class MainMenuScreen extends Screen{
	public MainMenuScreen(final BatalhaDeReinos game, IScreenMediator gameScreenMediator) {
		super(game,gameScreenMediator);
	}

	public void show() {
		super.show();

		setBackgroundImage("background/TitleBackground.jpg");


		createButton("buttons/btnJogar.png", 141, 563,
					new IButtonCommand() {public void execute() {gameScreenMediator.changeScreen("Settings");}});

		createButton("buttons/btnCreditos.png", 141, 385,
					new IButtonCommand() {public void execute() {gameScreenMediator.changeScreen("Credit");}});

		createButton("buttons/btnSair.png", 141, 207,
					new IButtonCommand() {public void execute() {Gdx.app.exit();}});
	}
}
