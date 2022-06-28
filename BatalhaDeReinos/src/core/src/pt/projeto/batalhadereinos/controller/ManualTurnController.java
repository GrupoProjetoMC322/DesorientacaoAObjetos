package pt.projeto.batalhadereinos.controller;

import java.util.ArrayList;

import pt.projeto.batalhadereinos.model.IPlayerObserver;

public class ManualTurnController extends TurnController implements ITurnControllerSubject{
        public ManualTurnController(){
            this.turn = 1;
            this.troopsOnBoard = new ArrayList<>();
            this.playersOnGame = new IPlayerObserver[2];
        }

        public void passTurn(){
            notifyTroops();
            notifyPlayers();
            turn++;
        }

        public void notifyPlayers(){
            int coinsEarnedPerTurn = (turn / 5) + 1;
            int playerPastTurn = (turn % 2 == 0) ? 1 : 0;
            givePlayerCoins(playerPastTurn, coinsEarnedPerTurn);
        }

}
