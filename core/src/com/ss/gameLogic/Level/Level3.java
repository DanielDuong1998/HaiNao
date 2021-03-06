package com.ss.gameLogic.Level;

import com.badlogic.gdx.utils.Array;
import com.ss.gameLogic.StaticObjects.Config;
import com.ss.gameLogic.objects.ManageRocks;
import com.ss.gameLogic.objects.Rock.Rock;

public class Level3 extends Level {
  private int padding = 20;

  public Level3(ManageRocks manageRocks, int turn){
    this.manageRocks = manageRocks;
    this.turn = turn;
    Config.scaleTime[0] = Config.scaleTimeCtn[0];
    Config.scaleTime[1] = Config.scaleTimeCtn[1];
    System.out.println("time-scale: " + Config.scaleTime[0] + "-" + Config.scaleTime[1]);
    startLv();
  }

  @Override
  protected void begin() {
    super.begin();
  }

  @Override
  protected void start(int mode) {
    super.start(mode);
    switch (mode){
      case 0: {
        Rock rock1 = manageRocks.getRock();
        Rock rock2 = manageRocks.getRock();
        int id = 2;

        rock1.setPosition1(Config.POSSITION_ROCK_X[1], Config.POSSITION_ROCK_Y[id]);
        rock2.setPosition1(Config.POSSITION_ROCK_X[2], Config.POSSITION_ROCK_Y[id]);

        rock1.moveRock(manageRocks.balll);
        rock2.moveRock(manageRocks.ballr);
        rock1.activeNext();
        break;
      }
      case 1: {
        Rock rock1 = manageRocks.getRock();
        Rock rock2 = manageRocks.getRock();
        int id = 2;

        rock1.setPosition1(Config.POSSITION_ROCK_X[0], Config.POSSITION_ROCK_Y[id]);
        rock2.setPosition1(Config.POSSITION_ROCK_X[3], Config.POSSITION_ROCK_Y[id]);

        rock1.moveRock(manageRocks.balll);
        rock2.moveRock(manageRocks.ballr);
        rock1.activeNext();
        break;
      }
      case 2: {
        Rock rock1 = manageRocks.getRock();
        Rock rock2 = manageRocks.getRock();
        int id = 2;

        rock1.setPosition1(Config.POSSITION_ROCK_X[0], Config.POSSITION_ROCK_Y[id]);
        rock2.setPosition1(Config.POSSITION_ROCK_X[2], Config.POSSITION_ROCK_Y[id]);

        rock1.moveRock(manageRocks.balll);
        rock2.moveRock(manageRocks.ballr);
        rock1.activeNext();
        break;
      }
      case 3: {
        Rock rock1 = manageRocks.getRock();
        Rock rock2 = manageRocks.getRock();
        int id = 2;

        rock1.setPosition1(Config.POSSITION_ROCK_X[1], Config.POSSITION_ROCK_Y[id]);
        rock2.setPosition1(Config.POSSITION_ROCK_X[3], Config.POSSITION_ROCK_Y[id]);

        rock1.moveRock(manageRocks.balll);
        rock2.moveRock(manageRocks.ballr);
        rock1.activeNext();

        break;
      }
      case 4: {
        Rock rock1 = manageRocks.getRock();
        Rock rock2 = manageRocks.getRock();
        Rock rock3 = manageRocks.getRock();
        int id = 2;

        rock1.setPosition1(Config.POSSITION_ROCK_X[1], Config.POSSITION_ROCK_Y[id]);
        rock2.setPosition1(Config.POSSITION_ROCK_X[1], Config.POSSITION_ROCK_Y[id] - rock1.getHeightS()/2 - rock2.getHeightS()/2 - padding);
        rock3.setPosition1(Config.POSSITION_ROCK_X[2], Config.POSSITION_ROCK_Y[id]);

        rock1.moveRock(manageRocks.balll);
        rock2.moveRock(manageRocks.balll);
        rock3.moveRock(manageRocks.ballr);
        rock2.activeNext();

        break;
      }
      case 5: {
        Rock rock1 = manageRocks.getRock();
        Rock rock2 = manageRocks.getRock();
        Rock rock3 = manageRocks.getRock();
        int id = 2;

        rock1.setPosition1(Config.POSSITION_ROCK_X[2], Config.POSSITION_ROCK_Y[id]);
        rock2.setPosition1(Config.POSSITION_ROCK_X[2], Config.POSSITION_ROCK_Y[id] - rock1.getHeightS()/2 - rock2.getHeightS()/2 - padding);
        rock3.setPosition1(Config.POSSITION_ROCK_X[1], Config.POSSITION_ROCK_Y[id]);

        rock1.moveRock(manageRocks.ballr);
        rock2.moveRock(manageRocks.ballr);
        rock3.moveRock(manageRocks.balll);
        rock2.activeNext();

        break;
      }
      case 6: {
        Rock rock1 = manageRocks.getRock();
        Rock rock2 = manageRocks.getRock();
        int id = 2;

        rock1.setPosition1(Config.POSSITION_ROCK_X[1], Config.POSSITION_ROCK_Y[id]);
        rock2.setPosition1(Config.POSSITION_ROCK_X[2], Config.POSSITION_ROCK_Y[id]-rock1.getHeightS()/2 - rock2.getHeightS()/2 - padding);

        rock1.moveRock(manageRocks.balll);
        rock2.moveRock(manageRocks.ballr);
        rock2.activeNext();

        break;
      }
      case 7: {
        Rock rock1 = manageRocks.getRock();
        Rock rock2 = manageRocks.getRock();
        int id = 2;

        rock1.setPosition1(Config.POSSITION_ROCK_X[1], Config.POSSITION_ROCK_Y[id]-rock2.getHeightS()/2 - rock1.getHeightS()/2 - padding);
        rock2.setPosition1(Config.POSSITION_ROCK_X[2], Config.POSSITION_ROCK_Y[id]);

        rock1.moveRock(manageRocks.balll);
        rock2.moveRock(manageRocks.ballr);
        rock1.activeNext();

        break;
      }
      case 8: {
        Rock rock1 = manageRocks.getRock();
        Rock rock2 = manageRocks.getRock();
        Rock rock3 = manageRocks.getRock();
        int id = 2;

        rock1.setPosition1(Config.POSSITION_ROCK_X[0], Config.POSSITION_ROCK_Y[id]);
        rock2.setPosition1(Config.POSSITION_ROCK_X[0], Config.POSSITION_ROCK_Y[id]-rock2.getHeightS()/2 - rock1.getHeightS()/2 - padding);
        rock3.setPosition1(Config.POSSITION_ROCK_X[3], Config.POSSITION_ROCK_Y[id]);

        rock1.moveRock(manageRocks.balll);
        rock2.moveRock(manageRocks.balll);
        rock3.moveRock(manageRocks.ballr);
        rock2.activeNext();

        break;
      }
      case 9: {
        Rock rock1 = manageRocks.getRock();
        Rock rock2 = manageRocks.getRock();
        Rock rock3 = manageRocks.getRock();
        int id = 2;

        rock1.setPosition1(Config.POSSITION_ROCK_X[3], Config.POSSITION_ROCK_Y[id]);
        rock2.setPosition1(Config.POSSITION_ROCK_X[3], Config.POSSITION_ROCK_Y[id]-rock2.getHeightS()/2 - rock1.getHeightS()/2 - padding);
        rock3.setPosition1(Config.POSSITION_ROCK_X[0], Config.POSSITION_ROCK_Y[id]);

        rock1.moveRock(manageRocks.ballr);
        rock2.moveRock(manageRocks.ballr);
        rock3.moveRock(manageRocks.balll);
        rock2.activeNext();

        break;
      }
      default: break;
    }
  }

  private boolean check(int checkN, Array<Integer> nums){
    for(int num : nums){
      if(checkN == num){
        return true;
      }
    }
    return false;
  }

  private int checkMode(Array<Integer> tempArr){
    Array<Integer> nums = new Array<>();
    nums.add(0, 4, 5, 6);
    nums.add(7, 2);
    if(check(tempArr.get(0), nums) && check(tempArr.get(1), nums)){
      int temp = tempArr.get(1);
      while (check(temp, nums)){
        temp = (int)Math.floor(Math.random()*10);
      }
      tempArr.removeRange(0, 1);
      tempArr.add(temp);
      return temp;
    }
    else {
      nums.removeRange(0, 5);
      //nums.add(1, 2, 3, 8);
      nums.add(1, 8, 9, 3);
      if(check(tempArr.get(0), nums) && check(tempArr.get(1), nums)){
        int temp = tempArr.get(1);
        while (check(temp, nums)){
          temp = (int)Math.floor(Math.random()*10);
        }
        tempArr.removeRange(0, 1);
        tempArr.add(temp);
        return temp;
      }
      else {
        int temp = tempArr.get(1);
        tempArr.removeRange(0, 1);
        tempArr.add(temp);
        return temp;
      }
    }
  }

  @Override
  public int getMode(int start, int end) {
    int rs = (int)Math.floor(Math.random()*(end - start + 1) + start);
    tempRandom.add(rs);
    if(tempRandom.size == 2){
      return checkMode(tempRandom);
    }
    return rs;
  }

  @Override
  public void startLv() {
    super.startLv();
    updateTimeScroll();
    System.out.println("turn: " + turn);
    int mode = getMode(0, 9);
    System.out.println("random: " + mode);
    if(turn > 99){
      System.out.println("end");
      return;
    }
    start(mode);
  }

  protected void updateTimeScroll(){
    switch (turn){
      case 0: {
        Config.scaleTime[0] = 1000;
        Config.scaleTime[1] = 1.7f;
        System.out.println("log: " +turn);
        break;
      }
      case 10: {
        Config.scaleTime[0] = 1000;
        Config.scaleTime[1] = 1.8f;
        System.out.println("log: " +turn);
        break;
      }
      case 20: {
        Config.scaleTime[0] = 1000;
        Config.scaleTime[1] = 1.8f;
        System.out.println("log: " +turn);
        break;
      }
      case 30: {
        Config.scaleTime[0] = 1000;
        Config.scaleTime[1] = 1.9f;
        System.out.println("log: " +turn);

        break;
      }
      case 40: {
        Config.scaleTime[0] = 1000;
        Config.scaleTime[1] = 2.1f;
        System.out.println("log: " +turn);

        break;
      }
      case 50: {
        Config.scaleTime[0] = 1000;
        Config.scaleTime[1] = 2.2f;
        System.out.println("log: " +turn);

        break;
      }
      case 60: {
        Config.scaleTime[0] = 1000;
        Config.scaleTime[1] = 2.3f;
        System.out.println("log: " +turn);

        break;
      }
      case 70: {
        Config.scaleTime[0] = 1000;
        Config.scaleTime[1] = 2.5f;
        System.out.println("log: " +turn);

        break;
      }
      case 80: {
        Config.scaleTime[0] = 1000;
        Config.scaleTime[1] = 2.8f;
        System.out.println("log: " +turn);

        break;
      }
      case 90: {
        Config.scaleTime[0] = 1000;
        Config.scaleTime[1] = 2.8f;
        System.out.println("log: " +turn);

        break;
      }

      default: break;
    }
  }
}
