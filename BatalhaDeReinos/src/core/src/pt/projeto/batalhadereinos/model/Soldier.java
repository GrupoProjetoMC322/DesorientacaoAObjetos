package pt.projeto.batalhadereinos.model;

public class Soldier extends Troop{
    public Soldier(Board board, String graphicAdress, int row, int column, int fromWhichPlayer){
        super(board, graphicAdress, row, column, 4, 2, 1, 1, 1, "Soldier", fromWhichPlayer);
    }

    public void verifyMap(){
    
    }
}
