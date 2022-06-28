package pt.projeto.batalhadereinos.model;

public class Archer extends Troop{
    public Archer(Board board, String graphicAdress, int row, int column, int fromWhichPlayer){
        super(board, graphicAdress, row, column, 2, 2, 2, 2, 1, "Archer", fromWhichPlayer);
    }

    public void verifyMap(){
        if(board.getMap().equals("Field") ||board.getMap().equals("Mist") || board.getMap().equals("Volcano")){
            this.attack = 2;
        } else{
            this.attack = 4;
        }
    }
}
