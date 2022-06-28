package pt.projeto.batalhadereinos.view;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.controller.IScreenMediator;

public class HowToPlayScreen extends Screen {
    public HowToPlayScreen(BatalhaDeReinos game, IScreenMediator gameScreenMediator) {
        super(game,gameScreenMediator);
    }

    public void show() {
        super.show();

        setBackgroundImage("background/HowToPlayBackground.jpg");

        createButton("buttons/btnVoltar.png", 605, 20,
                    new IButtonCommand() {public void execute() {
                        gameScreenMediator.changeScreen("Settings");
                    }});
    }

}
