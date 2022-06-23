package pt.projeto.batalhadereinos.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import pt.projeto.batalhadereinos.model.ITroopObserver;
import pt.projeto.batalhadereinos.model.Troop;

public class TurnController implements ITurnControllerSubject{
        private int turn;
        private ArrayList<ITroopObserver> troopsOnBoard;

        public TurnController(){
            this.turn = 1;
            this.troopsOnBoard = new ArrayList<>();
        }

        public int getTurn(){
            return turn;
        }

        public void passTurn(){
            notifyTroops();
            turn++;
        }

        public void subscribeTroop(ITroopObserver troop){
            troopsOnBoard.add(troop);
        }

        public void unsubscribeTroop(ITroopObserver troop){
            troopsOnBoard.remove(troop);
        }

        public void notifyTroops(){
            TreeMap<Integer,ArrayList<ITroopObserver>> atacantesTreeMap = new TreeMap<>(Collections.reverseOrder());

            atacantesTreeMap.put(10, new ArrayList<ITroopObserver>());
            atacantesTreeMap.put(5, new ArrayList<ITroopObserver>());
            atacantesTreeMap.put(3, new ArrayList<ITroopObserver>());
            atacantesTreeMap.put(2, new ArrayList<ITroopObserver>());
            atacantesTreeMap.put(1, new ArrayList<ITroopObserver>());


            for(int i = 0; i<troopsOnBoard.size();i++){
                System.out.println("Notificou "+((Troop)troopsOnBoard.get(i)).getCost());
                if(((Troop)troopsOnBoard.get(i)).getHealth() <= 0){
                    System.out.println("Morreu "+((Troop)troopsOnBoard.get(i)).getCost());
                    unsubscribeTroop(troopsOnBoard.get(i));
                } else{
                    boolean attacking = troopsOnBoard.get(i).move();
                    if(attacking){
                        atacantesTreeMap.get(((Troop)troopsOnBoard.get(i)).getCost()).add(troopsOnBoard.get(i));
                    }
                }
            }

            for (int key : atacantesTreeMap.keySet()) {
                for(ITroopObserver troop: atacantesTreeMap.get(key)){
                    troop.attack();
                }
                
            }
        }

}
