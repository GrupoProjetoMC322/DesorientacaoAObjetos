package pt.projeto.batalhadereinos.view;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.controller.IScreenMediator;

public class PauseScreen extends Screen {
	private String mode;

    public PauseScreen(BatalhaDeReinos game, String mode, IScreenMediator gameScreenMediator) {
        super(game,gameScreenMediator);
		this.mode = mode;
    }


    public void show() {
		super.show();

		if(bgImg == null){
			setBackgroundImage("background/FieldPauseBackground.jpg");
		}

		createButton("buttons/btnVoltarJogo.png", 483, 561,
					new IButtonCommand() {public void execute() {gameScreenMediator.changeScreen("ReturnGameScreen");}});

		createButton("buttons/btnReiniciar.png", 426, 420,
					new IButtonCommand() {public void execute() {
						if(mode.equals("time")){
							gameScreenMediator.changeScreen("NewDynamicGameScreen");
						} else if(mode.equals("time2")){
							gameScreenMediator.changeScreen("NewInsaneDynamicGameScreen");
						} else {
							gameScreenMediator.changeScreen("NewTurnGameScreen");
						}
					}});

		createButton("buttons/btnVoltarMenu.png", 407, 279,
					new IButtonCommand() {public void execute() {gameScreenMediator.changeScreen("MainMenu");}});
	}
    
}
