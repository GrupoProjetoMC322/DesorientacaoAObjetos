package pt.projeto.batalhadereinos.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.RunElement;

import com.badlogic.gdx.Gdx;

import pt.projeto.batalhadereinos.model.IPlayerObserver;
import pt.projeto.batalhadereinos.model.ITroopObserver;
import pt.projeto.batalhadereinos.model.Troop;

public class TimeTurnController implements ITurnControllerSubject {
        float time;
        int turn;
        private ArrayList<ITroopObserver> troopsOnBoard;
        private IPlayerObserver[] playersOnGame;

        public TimeTurnController(){
            this.turn = 1;
            this.time = 0;
            this.troopsOnBoard = new ArrayList<>();
            this.playersOnGame = new IPlayerObserver[2];
        }

        public int getTurn(){
            return this.turn;
        }

        public void passTurn(){
            time += Gdx.graphics.getDeltaTime();
            if(time > 3){
                time -= 3;
                notifyTroops();
                notifyPlayers();
                turn++;
            }
        }

        public void subscribeTroop(ITroopObserver troop){
            troopsOnBoard.add(troop);
            System.out.println(troopsOnBoard.size());
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

            atacantesTreeMap.put(10, new ArrayList<ITroopObserver>());
            atacantesTreeMap.put(5, new ArrayList<ITroopObserver>());
            atacantesTreeMap.put(3, new ArrayList<ITroopObserver>());
            atacantesTreeMap.put(2, new ArrayList<ITroopObserver>());
            atacantesTreeMap.put(1, new ArrayList<ITroopObserver>());

            for(int i = 0; i<troopsOnBoard.size();i++){
                System.out.println("Notified: " + (((Troop)troopsOnBoard.get(i)).getCost()));
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
            if(numberOfPlayer == 1){
                playersOnGame[0].gainCoins(numberOfCoins);
            } else {
                playersOnGame[1].gainCoins(numberOfCoins);
            }
            
        }

        public void notifyPlayers(){
            int coinsEarnedPerTurn = (turn / 5) + 1;
            givePlayerCoins(1, coinsEarnedPerTurn);
            givePlayerCoins(2, coinsEarnedPerTurn);
        }
}
