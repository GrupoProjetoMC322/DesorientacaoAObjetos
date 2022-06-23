package pt.projeto.batalhadereinos.model;

public class Archer extends Troop{
    public Archer(Board board, String graphicAdress, int row, int column, int fromWhichPlayer){
        super(board, graphicAdress, row, column, 2, 2, 2, 2, 1, "Archer", fromWhichPlayer);
    }
}
