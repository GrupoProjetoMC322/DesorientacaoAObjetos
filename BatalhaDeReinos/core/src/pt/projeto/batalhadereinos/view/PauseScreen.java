package pt.projeto.batalhadereinos.view;

import pt.projeto.batalhadereinos.BatalhaDeReinos;

public class PauseScreen extends Screen {
    private GameScreen match;

    public PauseScreen(BatalhaDeReinos game, GameScreen match) {
        super(game);
        this.match = match;
    }

    public void show() {
		super.show();

		setBackgroundImage("background/PauseBackground.jpg");

		createButton("buttons/btnVoltarJogo.png", 483, 561,
					new IButtonCommand() {public void execute() {screenController.update(match);}});

		createButton("buttons/btnReiniciar.png", 426, 420,
					new IButtonCommand() {public void execute() {screenController.update(new GameScreen(game));}});

		createButton("buttons/btnVoltarMenu.png", 407, 279,
					new IButtonCommand() {public void execute() {screenController.update(new MainMenuScreen(game));}});
	}
    
}
