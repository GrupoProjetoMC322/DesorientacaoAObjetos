package pt.projeto.batalhadereinos.view;

import pt.projeto.batalhadereinos.BatalhaDeReinos;

public class HowToPlayScreen extends Screen {
    private Screen previous;

    public HowToPlayScreen(BatalhaDeReinos game, Screen previous) {
        super(game);
        this.previous = previous;
    }

    public void show() {
        super.show();

        setBackgroundImage("background/HowToPlayBackground.jpg");

        createButton("buttons/btnVoltar.png", 605, 28,
                    new IButtonCommand() {public void execute() {
                        screenController.update(previous);
                    }});
    }

}
