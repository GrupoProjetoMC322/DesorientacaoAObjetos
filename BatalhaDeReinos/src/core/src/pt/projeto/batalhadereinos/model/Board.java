package pt.projeto.batalhadereinos.model;

import java.util.Random;

public class Board{
    private Square[][] boardPositions;

    private Castle player1Castle;
    private Castle player2Castle;

    private String map;

    public Board(){
      boardPositions = new Square[4][10];
      for(int i = 0;i<4;i++){
        for(int j = 0;j<10;j++){
          boardPositions[i][j] = new Square("Rectangle", i, j);
        }
      }
      player1Castle = new Castle();
      player2Castle = new Castle();

      this.map = "Field";
    }

    public void addTroop(Troop troop,int row, int column){
      boardPositions[row][column].setTroop(troop);
    }

    public Troop getTroop(int row, int column) {
      Troop troop = null;
      if(row >= 0 && row <= 3 && column >= 0 && column <= 9){
        troop = boardPositions[row][column].getTroop();
      }
      return troop;
    }

    public void removeTroop(int row, int column){
      boardPositions[row][column].removeTroop();
    }

    public Castle getCastle(int num) {
      return num == 1 ? player1Castle : player2Castle;
    }

    public void addBuff(Buff buff,int row, int column){
      boardPositions[row][column].setBuff(buff);
    }

    public Buff getBuff(int row, int column) {
      Buff buff = null;
      if(row >= 0 && row <= 4 && column >= 0 && column <= 9){
        buff = boardPositions[row][column].getBuff();
      }
      return buff;
    }

    public void removeBuff(int row, int column){
      boardPositions[row][column].removeBuff();
    }
    
    public String getMap(){
      return this.map;
    }

    public void setFires(){
      Random random = new Random();
      for(int i = 0; i<5;i++){
        int fireRow = random.nextInt(4);
        int fireColumn = random.nextInt(8) + 1;
        boardPositions[fireRow][fireColumn].setFire(true);
      }
    }

    public void removeFires(){
      for(int i = 0;i<4;i++){
        for(int j = 0;j<10;j++){
          boardPositions[i][j].setFire(false);
        }
      }
    }

    public boolean getFires(int row, int column){
      return boardPositions[row][column].getFire();
    }

    public void setMap(String map){
      this.map = map;
      if(this.map.equals("Volcano")){
        setFires();
      } else {
        removeFires();
      }
    }

    public boolean getFire(int row, int column) {
      return boardPositions[row][column].getFire();
    }
}