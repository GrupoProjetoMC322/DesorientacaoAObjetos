package pt.projeto.batalhadereinos.controller;

import pt.projeto.batalhadereinos.model.Board;
import pt.projeto.batalhadereinos.model.Buff;
import pt.projeto.batalhadereinos.model.Troop;

public interface IGameFacade {
    public void setBoard(Board board);
    public String getGameMode();
    public String getMap();
    public int getTurn();
    public void passTurn();
    public void tryGenerateBuff();
    public void tryChangeMap();
    public int getCurrentPlayer();
    public String getPlayerName(int num);
    public int getCastleHealth(int num);
    public int getPlayerCoins(int num);
    public void selectTroop(String troop, int numberOfPlayer);
    public String getSelectedTroop(int numberOfPlayer);
    public void dynamicPlaceTroop(int row, int column, int currentPlayer);
    public void turnPlaceTroop(int row, int column);
    public Troop getTroopFromSquare(int row, int column);
    public Buff getBuffFromSquare(int row, int column);
    public boolean getFireFromSquare(int row, int column);
    public boolean checkEndGame();
    public String getVencedor();
}
