package pt.projeto.batalhadereinos.controller;

import pt.projeto.batalhadereinos.model.Board;
import pt.projeto.batalhadereinos.model.Player;

public class PlayerController {
    private Player player1;
    private Player player2;

    public PlayerController(Board board){
        player1 = new Player(1,board,"Player 1");
        player2 = new Player(2,board,"Player 2");
    }
    public Player getPlayer(int num){
        return (num == 1) ? player1 : player2;
    }

    public String getPlayerName(int num){
        return (num == 1) ? player1.getName() : player2.getName();
    }

    public int getPlayerCoins(int num){
        return (num == 1) ? player1.getCoins() : player2.getCoins();
    }

    public int getCastleHealth(int num){
        return (num==1) ? player1.getCastle().getHealth() : player2.getCastle().getHealth();
    }
}
