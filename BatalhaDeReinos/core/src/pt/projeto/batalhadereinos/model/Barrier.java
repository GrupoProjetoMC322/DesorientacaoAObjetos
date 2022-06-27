package pt.projeto.batalhadereinos.model;

public class Barrier extends Troop{
    public Barrier(Board board, String graphicAdress, int row, int column, int fromWhichPlayer){
        super(board, graphicAdress, row, column, 10, 0, 5, 1, 1, "Barrier", fromWhichPlayer);
    }

    public void verifyMap(){
        
    }
}
