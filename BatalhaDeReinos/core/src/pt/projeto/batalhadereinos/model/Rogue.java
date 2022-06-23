package pt.projeto.batalhadereinos.model;

import java.util.ArrayList;

public class Rogue extends Troop{
    public Rogue(Board board, String graphicAdress, int row, int column, int fromWhichPlayer){
        super(board, graphicAdress, row, column, 2, 3, 3, 1, 1, "Rogue", fromWhichPlayer);
    }

    public ArrayList<Troop> verifyRange(){
        ArrayList<Troop> enemyTroopInRange = new ArrayList<>();
        for(int i = 1; i<=range;i++){

            boolean troopNotNull = board.getTroop(row, column+i) != null;
            boolean foundTroopFromOtherPlayer = troopNotNull && (board.getTroop(row, column+i).getFromWhichPlayer() != this.fromWhichPlayer);
            
            if(foundTroopFromOtherPlayer){
                enemyTroopInRange.add(board.getTroop(row, column+i));
            }
        }
        return enemyTroopInRange;
    }
}
