package pt.projeto.batalhadereinos.view;

import com.badlogic.gdx.Gdx;

import pt.projeto.batalhadereinos.BatalhaDeReinos;

public class MainMenuScreen extends Screen {

	public MainMenuScreen(final BatalhaDeReinos game) {
		super(game);
	}

	public void show() {
		super.show();

		setBackgroundImage("background/TitleBackground.jpg");


		createButton("buttons/btnJogar.png", 141, 615,
					new IButtonCommand() {public void execute() {screenController.update(new GameScreen(game));}});

		createButton("buttons/btnOpcoes.png", 141, 449,
					new IButtonCommand() {public void execute() {screenController.update(new SettingsScreen(game));}});

		createButton("buttons/btnCreditos.png", 141, 283,
					new IButtonCommand() {public void execute() {screenController.update(new CreditScreen(game));}});

		createButton("buttons/btnSair.png", 141, 117,
					new IButtonCommand() {public void execute() {Gdx.app.exit();}});
	}
}
