package pt.projeto.batalhadereinos.controller;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.view.MainMenuScreen;
import pt.projeto.batalhadereinos.view.Screen;

public class GameScreenController {
    private BatalhaDeReinos game;
    private Screen currentScreen;

    public GameScreenController(BatalhaDeReinos game){
        this.game = game;
    }

    public void start(){
        currentScreen = new MainMenuScreen(game);
        game.setScreen(currentScreen);
    }

    public void update(Screen newScreen){
        this.currentScreen = newScreen;
        game.setScreen(newScreen);
    }
}
