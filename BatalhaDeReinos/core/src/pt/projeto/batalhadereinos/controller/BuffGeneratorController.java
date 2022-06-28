package pt.projeto.batalhadereinos.controller;


import java.util.Random;

import pt.projeto.batalhadereinos.model.Board;
import pt.projeto.batalhadereinos.model.Buff;

public class BuffGeneratorController {
    private Board board;

    public BuffGeneratorController(Board board){
        this.board = board;
    }

    public Buff randomBuff(int row, int column){
        Buff buff;
        Random random = new Random();
        int buffType = random.nextInt(10);

        if(buffType <= 1){
            buff = new Buff(row, column, "MixedPotion");
        } else if(buffType > 1 && buffType <= 5){
            buff = new Buff(row, column, "AttackPotion");
        } else{ 
            buff = new Buff(row, column, "HealthPotion");
        }

        return buff;
    }

    public void tryGenerateBuff(int turn){
        if(turn % 7 == 0){
            Random random = new Random();
            for(int i = 0; i<3; i++){
                int row = random.nextInt(4);
                int column = random.nextInt(2) + 4;
                if(board.getTroop(row, column) == null && board.getBuff(row, column) == null && !board.getFire(row, column)){
                    Buff buff = randomBuff(row,column);
                    board.addBuff(buff, row, column);
                    break;
                }
            }
        }
    }
}
