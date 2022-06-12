package pt.projeto.batalhadereinos.controller;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.view.GameScreen;
import pt.projeto.batalhadereinos.view.MainMenuScreen;

public class GameScreenController {
    private BatalhaDeReinos game;
    private MainMenuScreen mainMenuScreen;
    private GameScreen gameScreen;

    public GameScreenController(BatalhaDeReinos game){
        this.game = game;
        this.mainMenuScreen = new MainMenuScreen(game, this);
        this.gameScreen = new GameScreen(game);
    }

    public void start(){
        System.out.println("funcionou");
        game.setScreen(mainMenuScreen);
    }
    public void update(String screen){
        if(screen.equals("GameScreen")){
            game.setScreen(gameScreen);
        } else {
            System.out.println("Screen Invalida");
        }
        
    }
}
