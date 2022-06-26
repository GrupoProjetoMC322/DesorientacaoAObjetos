package pt.projeto.batalhadereinos.view;

import pt.projeto.batalhadereinos.BatalhaDeReinos;

public class CreditScreen extends Screen {

    public CreditScreen(BatalhaDeReinos game) {
        super(game);
    }

    public void show() {
        super.show();

        setBackgroundImage("background/CreditBackground.jpg");

        createButton("buttons/btnVoltar.png", 605, 28,
                    new IButtonCommand() {public void execute() {
                        screenController.update(new MainMenuScreen(game));
                    }});
    }

}
