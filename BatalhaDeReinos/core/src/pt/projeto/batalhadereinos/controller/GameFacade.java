package pt.projeto.batalhadereinos.controller;

import pt.projeto.batalhadereinos.model.Board;
import pt.projeto.batalhadereinos.model.Buff;
import pt.projeto.batalhadereinos.model.Troop;

public class GameFacade implements IGameFacade{
    private ITurnControllerSubject turnController;
    private TroopCreatorController troopCreatorController;
    private PlayerController playerController;
    private BuffGeneratorController buffGeneratorController;
    private MapChangerController mapChangerController;
    private Board board;
    private String gameMode;


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

    public String getMap(){
        return board.getMap();
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
        Troop troop = troopCreatorController.placeTroop(row, column, currentPlayer, getPlayerCoins(currentPlayer));
        if(troop != null){
            turnController.subscribeTroop(troop);
            playerController.getPlayer(troop.getFromWhichPlayer()).payCoins(troop.getCost());
        }
        
    }
    public void turnPlaceTroop(int row, int column){
        if(column == 0 && getCurrentPlayer() == 1 || column == 9 && getCurrentPlayer() == 2){
            Troop troop = troopCreatorController.placeTroop(row, column, getCurrentPlayer(), getPlayerCoins(getCurrentPlayer()));
            if(troop != null){
                turnController.subscribeTroop(troop);
                playerController.getPlayer(troop.getFromWhichPlayer()).payCoins(troop.getCost());
            }
        }
    }
    

    // Render

    public Troop getTroopFromSquare(int row, int column){
        return board.getTroop(row, column);
    }

    public Buff getBuffFromSquare(int row, int column){
        return board.getBuff(row, column);
    }

    public boolean getFireFromSquare(int row, int column) {
        return board.getFire(row,column);
    }

    public boolean checkEndGame(){
        boolean isOver = false;
        if(getCastleHealth(1) <= 0 || getCastleHealth(2) <= 0){
            isOver = true;
        }
        return isOver;
    }

    public String getVencedor(){
        if(getCastleHealth(1) < getCastleHealth(2)){
            return getPlayerName(2);
        } else {
            return getPlayerName(1);
        }
    }

}

