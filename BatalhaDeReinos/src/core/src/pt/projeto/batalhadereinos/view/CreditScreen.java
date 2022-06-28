package pt.projeto.batalhadereinos.view;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.controller.IScreenMediator;

public class CreditScreen extends Screen {

    public CreditScreen(BatalhaDeReinos game, IScreenMediator gameScreenMediator) {
        super(game,gameScreenMediator);
    }

    public void show() {
        super.show();

        setBackgroundImage("background/CreditBackground.jpg");

        createButton("buttons/btnVoltar.png", 605, 28,
                    new IButtonCommand() {public void execute() {
                        gameScreenMediator.changeScreen("MainMenu");
                    }});
    }

}
