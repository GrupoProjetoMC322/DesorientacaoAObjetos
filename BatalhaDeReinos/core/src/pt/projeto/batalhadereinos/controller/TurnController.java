package pt.projeto.batalhadereinos.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import pt.projeto.batalhadereinos.model.IPlayerObserver;
import pt.projeto.batalhadereinos.model.ITroopObserver;
import pt.projeto.batalhadereinos.model.Troop;

public abstract class TurnController {
    protected int turn;
    protected ArrayList<ITroopObserver> troopsOnBoard;
    protected IPlayerObserver[] playersOnGame;
    
    public int getTurn(){
        return this.turn;
    }

    public void subscribeTroop(ITroopObserver troop){
        troopsOnBoard.add(troop);
    }

    public void unsubscribeTroop(ITroopObserver troop){
        troopsOnBoard.remove(troop);
    }

    public void subscribePlayers(IPlayerObserver player1, IPlayerObserver player2){
        playersOnGame[0] = player1;
        playersOnGame[1] = player2;
    }

    public void notifyTroops(){
        TreeMap<Integer,ArrayList<ITroopObserver>> atacantesTreeMap = new TreeMap<>(Collections.reverseOrder());

        atacantesTreeMap.put(24, new ArrayList<ITroopObserver>());
        atacantesTreeMap.put(20, new ArrayList<ITroopObserver>());
        atacantesTreeMap.put(16, new ArrayList<ITroopObserver>());
        atacantesTreeMap.put(6, new ArrayList<ITroopObserver>());
        atacantesTreeMap.put(4, new ArrayList<ITroopObserver>());
        atacantesTreeMap.put(12, new ArrayList<ITroopObserver>());
        atacantesTreeMap.put(10, new ArrayList<ITroopObserver>());
        atacantesTreeMap.put(8, new ArrayList<ITroopObserver>());
        atacantesTreeMap.put(5, new ArrayList<ITroopObserver>());
        atacantesTreeMap.put(3, new ArrayList<ITroopObserver>());
        atacantesTreeMap.put(2, new ArrayList<ITroopObserver>());
        atacantesTreeMap.put(1, new ArrayList<ITroopObserver>());

        for(int i = 0; i<troopsOnBoard.size();i++){
            //System.out.println("Notified: " + (((Troop)troopsOnBoard.get(i)).getCost()));
            if(this.turn % 10 == 0){
                troopsOnBoard.get(i).verifyMap();
            }
            boolean attacking = troopsOnBoard.get(i).move();
            if(attacking){
                atacantesTreeMap.get(((Troop)troopsOnBoard.get(i)).getCost()).add(troopsOnBoard.get(i));
            }
        }
        

        for (int key : atacantesTreeMap.keySet()) {
            for(ITroopObserver troop: atacantesTreeMap.get(key)){
                troop.attack();
                verifyDeadTroops();
            }
        }
    }

    public void verifyDeadTroops(){
        for(int i = 0; i<troopsOnBoard.size();i++){
            if(!(((Troop)troopsOnBoard.get(i)).isAlive())){
                int coinsEarned = (((Troop)troopsOnBoard.get(i)).getCost())/2;
                int playerEarning = ((((Troop)troopsOnBoard.get(i)).getFromWhichPlayer()) == 1) ? 2 : 1;
                givePlayerCoins(playerEarning,coinsEarned);
                unsubscribeTroop(((Troop)troopsOnBoard.get(i)));
            }
        }
    }

    public void givePlayerCoins(int numberOfPlayer,int numberOfCoins){
        if(numberOfPlayer == 0){
            playersOnGame[0].gainCoins(numberOfCoins);
        } else {
            playersOnGame[1].gainCoins(numberOfCoins);
        }
    }
}
