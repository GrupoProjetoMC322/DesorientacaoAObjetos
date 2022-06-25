package pt.projeto.batalhadereinos.controller;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.view.GameScreen;
import pt.projeto.batalhadereinos.view.MainMenuScreen;

public class GameScreenMediator implements IScreenMediator{
    private BatalhaDeReinos game;
    private MainMenuScreen mainMenuScreen;
    private GameScreen gameScreen;

    public GameScreenMediator(BatalhaDeReinos game){
        this.game = game;
        this.mainMenuScreen = new MainMenuScreen(game, this);
    }

    public void changeScreen(String screen){
        GameFacade gameFacade;
        switch(screen){
            case "MainMenu":
                game.setScreen(mainMenuScreen);
                break;
            case "NewDynamicGameScreen":
                gameFacade = new GameFacade("time");
                this.gameScreen = new GameScreen(game, gameFacade, this);
                game.setScreen(gameScreen);
                break;
            case "NewTurnGameScreen":
                gameFacade = new GameFacade("turn");
                this.gameScreen = new GameScreen(game, gameFacade, this);
                game.setScreen(gameScreen);
                break;
            default:
                System.out.println("Screen Invalida");
                break;
        }

        
        
    }
}
