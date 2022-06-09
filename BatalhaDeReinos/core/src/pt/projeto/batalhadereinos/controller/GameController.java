package pt.projeto.batalhadereinos.controller;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.view.MainMenuScreen;

public class GameController {
    public void start(BatalhaDeReinos game){
        System.out.println("funcionou");
        game.setScreen(new MainMenuScreen(game));
    }
}
