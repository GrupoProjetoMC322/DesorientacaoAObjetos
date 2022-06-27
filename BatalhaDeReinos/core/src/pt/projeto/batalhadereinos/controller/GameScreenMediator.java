package pt.projeto.batalhadereinos.controller;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.view.GameScreen;
import pt.projeto.batalhadereinos.view.MainMenuScreen;
import pt.projeto.batalhadereinos.view.CreditScreen;
import pt.projeto.batalhadereinos.view.SettingsScreen;
import pt.projeto.batalhadereinos.view.PauseScreen;

public class GameScreenMediator implements IScreenMediator{
    private BatalhaDeReinos game;
    private MainMenuScreen mainMenuScreen;
    private SettingsScreen settingsScreen;
    private GameScreen gameScreen;
    private CreditScreen creditScreen;
    private PauseScreen pauseScreen;

    public GameScreenMediator(BatalhaDeReinos game){
        this.game = game;
        this.mainMenuScreen = new MainMenuScreen(game);
        this.settingsScreen = new SettingsScreen(game);
        this.creditScreen = new CreditScreen(game);
        this.pauseScreen = new PauseScreen(game,gameScreen);
    }

    public void changeScreen(String screen){
        GameFacade gameFacade;
        switch(screen){
            case "MainMenu":
                game.setScreen(mainMenuScreen);
                break;
            case "Settings":
                game.setScreen(settingsScreen);
                break;
            case "Credit":
                game.setScreen(creditScreen);
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
            case "NewInsaneDynamicGameScreen":
                gameFacade = new GameFacade("time2");
                this.gameScreen = new GameScreen(game, gameFacade, this);
                game.setScreen(gameScreen);
                break;
            default:
                System.out.println("Screen Invalida");
                break;
        }

        
        
    }
}
