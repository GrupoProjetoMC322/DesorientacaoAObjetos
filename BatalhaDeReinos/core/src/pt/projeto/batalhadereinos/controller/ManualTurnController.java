package pt.projeto.batalhadereinos.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import pt.projeto.batalhadereinos.model.IPlayerObserver;
import pt.projeto.batalhadereinos.model.ITroopObserver;
import pt.projeto.batalhadereinos.model.Troop;

public class ManualTurnController implements ITurnControllerSubject{
        private int turn;
        private ArrayList<ITroopObserver> troopsOnBoard;
        private IPlayerObserver[] playersOnGame;

        public ManualTurnController(){
            this.turn = 1;
            this.troopsOnBoard = new ArrayList<>();
            this.playersOnGame = new IPlayerObserver[2];
        }

        public int getTurn(){
            return turn;
        }

        public void passTurn(){
            notifyTroops();
            notifyPlayers();
            turn++;
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

            atacantesTreeMap.put(10, new ArrayList<ITroopObserver>());
            atacantesTreeMap.put(5, new ArrayList<ITroopObserver>());
            atacantesTreeMap.put(3, new ArrayList<ITroopObserver>());
            atacantesTreeMap.put(2, new ArrayList<ITroopObserver>());
            atacantesTreeMap.put(1, new ArrayList<ITroopObserver>());

            for(int i = 0; i<troopsOnBoard.size();i++){
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
                    int playerEarning = (((Troop)troopsOnBoard.get(i)).getFromWhichPlayer()) == 1 ? 1 : 0;
                    givePlayerCoins(playerEarning,coinsEarned);
                    unsubscribeTroop(((Troop)troopsOnBoard.get(i)));
                }
            }
        }

        public void givePlayerCoins(int numberOfPlayer,int numberOfCoins){
            playersOnGame[numberOfPlayer].gainCoins(numberOfCoins);
        }

        public void notifyPlayers(){
            int coinsEarnedPerTurn = (turn / 5) + 1;
            int playerPastTurn = (turn % 2 == 0) ? 1 : 0;
            givePlayerCoins(playerPastTurn, coinsEarnedPerTurn);
        }

}
