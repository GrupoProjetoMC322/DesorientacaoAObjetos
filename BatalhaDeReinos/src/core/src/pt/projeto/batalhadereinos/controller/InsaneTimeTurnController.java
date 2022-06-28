package pt.projeto.batalhadereinos.controller;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

import pt.projeto.batalhadereinos.model.IPlayerObserver;

public class InsaneTimeTurnController extends TurnController implements ITurnControllerSubject{
    private float time;

    public InsaneTimeTurnController(){
        this.turn = 1;
        this.time = 0;
        this.troopsOnBoard = new ArrayList<>();
        this.playersOnGame = new IPlayerObserver[2];
    }

    public void passTurn(){
        time += Gdx.graphics.getDeltaTime();
        if(time > 1){
            time -= 1;
            notifyTroops();
            notifyPlayers();
            turn++;
        }
    }

    public void notifyPlayers(){
        int coinsEarnedPerTurn = 2*((turn / 5) + 1);
        givePlayerCoins(0, coinsEarnedPerTurn);
        givePlayerCoins(1, coinsEarnedPerTurn);
    }
}
