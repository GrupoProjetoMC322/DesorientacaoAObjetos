package pt.projeto.batalhadereinos.controller;

import java.util.ArrayList;
import java.util.HashMap;

import pt.projeto.batalhadereinos.model.ITroopObserver;
import pt.projeto.batalhadereinos.model.Troop;

public class TurnController implements ITurnControllerSubject{
        private int turn;
        private ArrayList<ITroopObserver> troopsOnBoard;

        public int getTurn(){
            return turn;
        }

        public void subscribeTroop(ITroopObserver troop){
            troopsOnBoard.add(troop);
        }

        public void unsubscribeTroop(ITroopObserver troop){
            troopsOnBoard.remove(troop);
        }

        public void notifyTroops(){
            HashMap<Integer,ArrayList<ITroopObserver>> atacantesHashMap = new HashMap<>();

            atacantesHashMap.put(10, new ArrayList<ITroopObserver>());
            atacantesHashMap.put(3, new ArrayList<ITroopObserver>());
            atacantesHashMap.put(1, new ArrayList<ITroopObserver>());


            for(ITroopObserver troop : troopsOnBoard){
                boolean couldMove = troop.move();
                if(!couldMove){
                    atacantesHashMap.get(((Troop) troop).getCost()).add(troop);
                }
            }

            for (int key : atacantesHashMap.keySet()) {
                for(ITroopObserver troop: atacantesHashMap.get(key)){
                    troop.attack();
                }
                
            }
        }

}
