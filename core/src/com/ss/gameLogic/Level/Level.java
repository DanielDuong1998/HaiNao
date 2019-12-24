package com.ss.gameLogic.Level;


import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.ss.gameLogic.objects.ManageRocks;

public class Level {
  protected ManageRocks manageRocks;
  protected Array<Integer> modes;
  protected int turn = 0;
  protected Array<Integer> tempRandom = new Array<>();
  protected Image finishRace;

  protected void begin(){

  }

  protected void start(int mode){
    turn++;
  }

  public void startLv(){

  }

  public void reset(){
    turn = 0;
    startLv();
  }

  public int getMode(int start, int end){
    return 0;
  }

  public int getTurn(){
    System.out.println("turn here!: " + turn);
    return turn;
  }

  public void setTurn(int turn){
    this.turn = turn;
  }
}
