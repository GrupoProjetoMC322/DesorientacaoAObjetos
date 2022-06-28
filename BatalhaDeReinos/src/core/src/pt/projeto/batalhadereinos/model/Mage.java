package pt.projeto.batalhadereinos.model;

import java.util.ArrayList;

public class Mage extends Troop{
    public Mage(Board board, String graphicAdress, int row, int column, int fromWhichPlayer){
        super(board, graphicAdress, row, column, 6, 5, 10, 3, 1, "Mage", fromWhichPlayer);
    }

    public void verifyMap(){
        if(board.getMap().equals("Field") ||board.getMap().equals("Mist")){
            this.attack = 5;
            this.cost = 10;
            this.range = 3;
        } else if(board.getMap().equals("Volcano")){
            this.attack = 6;
            this.cost = 12;
            this.range = 3;
        } else if(board.getMap().equals("Snow")){
            this.attack = 3;
            this.cost = 8;
            this.range = 2;
        }
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
