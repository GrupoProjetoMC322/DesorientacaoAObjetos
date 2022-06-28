package pt.projeto.batalhadereinos.controller;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.view.GameScreen;
import pt.projeto.batalhadereinos.view.HowToPlayScreen;
import pt.projeto.batalhadereinos.view.MainMenuScreen;
import pt.projeto.batalhadereinos.view.CreditScreen;
import pt.projeto.batalhadereinos.view.EndScreen;
import pt.projeto.batalhadereinos.view.SettingsScreen;
import pt.projeto.batalhadereinos.view.PauseScreen;

public class GameScreenMediator implements IScreenMediator{
    private BatalhaDeReinos game;
    private MainMenuScreen mainMenuScreen;
    private SettingsScreen settingsScreen;
    private GameScreen gameScreen;
    private CreditScreen creditScreen;
    private PauseScreen pauseScreen;
    private HowToPlayScreen howToPlayScreen;
    private EndScreen endScreen;

    public GameScreenMediator(BatalhaDeReinos game){
        this.game = game;
        this.mainMenuScreen = new MainMenuScreen(game,this);
        this.settingsScreen = new SettingsScreen(game,this);
        this.creditScreen = new CreditScreen(game,this);
        this.howToPlayScreen = new HowToPlayScreen(game,this);

    }

    public void changeScreen(String screen){

        IGameFacade gameFacade;

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
            case "Pause":
                game.setScreen(pauseScreen);
                break;
            case "EndScreen":
                game.setScreen(endScreen);
                break;
            case "HowToPlay":
                game.setScreen(howToPlayScreen);
                break;
            case "ReturnGameScreen":
                game.setScreen(gameScreen);
                break;
            case "NewDynamicGameScreen":
                gameFacade = new GameFacade("time");
                this.pauseScreen = new PauseScreen(game,"time",this);
                this.endScreen = new EndScreen(game, this);
                this.gameScreen = new GameScreen(game, gameFacade,this,pauseScreen,endScreen);
                game.setScreen(gameScreen);
                break;
            case "NewTurnGameScreen":
                gameFacade = new GameFacade("turn");
                this.pauseScreen = new PauseScreen(game,"turn",this);
                this.endScreen = new EndScreen(game, this);
                this.gameScreen = new GameScreen(game, gameFacade,this,pauseScreen,endScreen);
                game.setScreen(gameScreen);
                break;
            case "NewInsaneDynamicGameScreen":
                gameFacade = new GameFacade("time2");
                this.pauseScreen = new PauseScreen(game,"time2",this);
                this.endScreen = new EndScreen(game, this);
                this.gameScreen = new GameScreen(game, gameFacade,this,pauseScreen,endScreen);
                game.setScreen(gameScreen);
                break;
            default:
                System.out.println("Screen Invalida");
                break;
        }

        
        
    }
}
