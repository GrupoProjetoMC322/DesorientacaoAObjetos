package pt.projeto.batalhadereinos.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pt.projeto.batalhadereinos.model.Board;
import pt.projeto.batalhadereinos.model.Troop;

public class GameFacade {
    ITurnControllerSubject turnController;
    TroopCreatorController troopCreatorController;
    PlayerController playerController;
    BuffGeneratorController buffGeneratorController;
    MapChangerController mapChangerController;
    Board board;

    String gameMode;


    // Creation

    public GameFacade(String gameMode){
        this.gameMode = gameMode;
        determineTurnController();
    }

    public void setBoard(Board board){
        this.board = board;
        this.troopCreatorController = new TroopCreatorController(this.board);
        this.buffGeneratorController = new BuffGeneratorController(this.board);
        this.playerController = new PlayerController(this.board);
        this.mapChangerController = new MapChangerController(this.board);
        turnController.subscribePlayers(playerController.getPlayer(1), playerController.getPlayer(2));
    }

    public String getGameMode(){
        return this.gameMode;
    }


    // Turns

    public void determineTurnController(){
        if(gameMode.equals("time")){
            this.turnController = new TimeTurnController();
        } else if(gameMode.equals("time2")){
            this.turnController = new InsaneTimeTurnController();
        } else{
            this.turnController = new ManualTurnController();
        }
    }

    public int getTurn(){
        return turnController.getTurn();
    }

    public void passTurn(){
        turnController.passTurn();
    }

    public void tryGenerateBuff(){
        buffGeneratorController.tryGenerateBuff(getTurn());
    }

    public void tryChangeMap(){
        mapChangerController.tryChangeMap(getTurn());
    }
   
    //Player

    public int getCurrentPlayer(){
       return (turnController.getTurn() % 2 != 0) ? 1 : 2;
    }

    public String getPlayerName(int num){
        return playerController.getPlayerName(num);
    }

    public int getCastleHealth(int num){
        return playerController.getCastleHealth(num);
    }

    public int getPlayerCoins(int num){
        return playerController.getPlayerCoins(num);
    }


    // Troops

    public void selectTroop(String troop, int numberOfPlayer){
        troopCreatorController.setSelectedTroop(troop, numberOfPlayer);
    }

    public String getSelectedTroop(int numberOfPlayer){
        return troopCreatorController.getSelectedTroop(numberOfPlayer);
    }

    public void dynamicPlaceTroop(int row, int column, int currentPlayer){
        Troop troop = troopCreatorController.placeTroop(row, column, currentPlayer,getPlayerCoins(currentPlayer));
        if(troop != null){
            turnController.subscribeTroop(troop);
            playerController.getPlayer(troop.getFromWhichPlayer()).payCoins(troop.getCost());
        }
        
    }
    public void turnPlaceTroop(int row, int column){
        Troop troop = troopCreatorController.placeTroop(row, column, getCurrentPlayer(),getPlayerCoins(getCurrentPlayer()));
        if(troop != null){
            turnController.subscribeTroop(troop);
            playerController.getPlayer(troop.getFromWhichPlayer()).payCoins(troop.getCost());
        }
    }
    

    // Render 

    public void renderBoard(SpriteBatch batch){
        board.draw(batch);
    }
}
