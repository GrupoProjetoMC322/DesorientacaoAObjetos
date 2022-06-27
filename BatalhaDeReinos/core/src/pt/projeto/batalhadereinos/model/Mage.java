package pt.projeto.batalhadereinos.model;

import java.util.ArrayList;

public class Mage extends Troop{
    public Mage(Board board, String graphicAdress, int row, int column, int fromWhichPlayer){
        super(board, graphicAdress, row, column, 6, 5, 10, 3, 1, "Mage", fromWhichPlayer);
    }

    public ArrayList<Troop> verifyRange(){
        ArrayList<Troop> enemyTroopInRange = new ArrayList<>();
        if(fromWhichPlayer == 1){
            for(int i = 0; i<range;i++){
                for(int j = 1; j>=-1; j--){
                    boolean troopNotNull = board.getTroop(row+j, column+i) != null;
                    boolean foundTroopFromOtherPlayer = troopNotNull && (board.getTroop(row+j, column+i).getFromWhichPlayer() != this.fromWhichPlayer);
                    
                    if(foundTroopFromOtherPlayer){
                        Troop troopFound = board.getTroop(row+j, column+i);
                        if(troopFound.getType() == "Barrier"){
                            ArrayList<Troop> barrierTroopInRange = new ArrayList<>();
                            barrierTroopInRange.add(troopFound);
                            return barrierTroopInRange;
                        }
                        enemyTroopInRange.add(troopFound);
                    }
    
                }
                
            }       
        } else {
            for(int i = 0; i<range;i++){
                for(int j = 1; j>=-1; j--){
                    boolean troopNotNull = board.getTroop(row+j, column-i) != null;
                    boolean foundTroopFromOtherPlayer = troopNotNull && (board.getTroop(row+j, column-i).getFromWhichPlayer() != this.fromWhichPlayer);
                    
                    if(foundTroopFromOtherPlayer){
                        Troop troopFound = board.getTroop(row+j, column-i);
                        if(troopFound.getType() == "Barrier"){
                            ArrayList<Troop> barrierTroopInRange = new ArrayList<>();
                            barrierTroopInRange.add(troopFound);
                            return barrierTroopInRange;
                        }
                        enemyTroopInRange.add(troopFound);
                    }
    
                }
                
            }     
        }
        
        return enemyTroopInRange;
    }
}
