package pt.projeto.batalhadereinos.model;

import java.util.ArrayList;

public class Mage extends Troop{
    public Mage(Board board, String graphicAdress, int row, int column, int fromWhichPlayer){
        super(board, graphicAdress, row, column, 6, 5, 10, 3, 1, "Mage", fromWhichPlayer);
    }

    public ArrayList<Troop> verifyRange(){
        ArrayList<Troop> enemyTroopInRange = new ArrayList<>();
        for(int i = 0; i<range;i++){
            for(int j = 1; j>=-1; j--){
                boolean troopNotNull = board.getTroop(row+j, column+i) != null;
                boolean foundTroopFromOtherPlayer = troopNotNull && (board.getTroop(row+j, column+i).getFromWhichPlayer() != this.fromWhichPlayer);
                
                if(foundTroopFromOtherPlayer){
                    enemyTroopInRange.add(board.getTroop(row+j, column+i));
                }

            }
            
        }
        return enemyTroopInRange;
    }
}
