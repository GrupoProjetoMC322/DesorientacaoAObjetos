package pt.projeto.batalhadereinos.controller;

import java.util.Random;

import pt.projeto.batalhadereinos.model.Board;

public class MapChangerController {
    private Board board;

    public MapChangerController(Board board){
        this.board = board;
    }

    public void tryChangeMap(int turn){
        if(turn % 10 == 0){
            Random random = new Random();
            int nextMap = random.nextInt(4);
            System.out.println("Trocou: " + nextMap);
            switch (nextMap) {
                case 1:
                    board.setMap("Mist");
                    break;
                case 2:
                    board.setMap("Volcano");
                    break;
                case 3:
                    board.setMap("Snow");
                    break;
                default:
                    board.setMap("Field");
                    break;
            }
        }
    }
}
