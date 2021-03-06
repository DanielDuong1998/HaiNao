package com.ss.gameLogic.objects.Rock;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;
import com.ss.effects.SoundEffect;
import com.ss.gameLogic.StaticObjects.Config;
import com.ss.gameLogic.objects.Ball;
import com.ss.scenes.PlayScene;

import static com.badlogic.gdx.math.Interpolation.*;

public class Rock extends Actor {
  protected String info;
  protected TextureAtlas atlas;
  protected GLayerGroup group;
  protected Image shape;
  protected PlayScene game;
  protected Ball ball;

  public boolean isAvai = true;
  private boolean isNext = false;
  private boolean countTurn = false;

  //test
  private boolean testBo = true;
  private int tick = 0;

  public Rock(){
    info = "rock";
  }
  public void info(){
    System.out.println(info);
  }

  protected void init(){
    initShape();
  }

  protected void initShape(){
    shape = GUI.createImage(atlas, info);
    group.addActor(shape);

  }

  public void setPosition1(float x, float y){
    shape.setPosition(x, y, Align.center);

  }

  public void moveRock(Ball ball){

    this.ball = ball;
    shape.setVisible(true);
    Vector2 vt1 = new Vector2(shape.getX(), shape.getY());
    Vector2 vt2 = new Vector2(shape.getX(), Config.HeightScreen + shape.getHeight());
    //float dua = Config.module(vt1, vt2)/Config.velocity;
    float dua = (Config.HeightScreen + shape.getHeight() - shape.getY())/Config.velocity;
    shape.addAction(Actions.sequence(
      Actions.parallel(
        Actions.moveTo(shape.getX(), Config.HeightScreen + shape.getHeight(), dua,linear),
        GSimpleAction.simpleAction((d, a)->{

          //test
//          tick++;
//          System.out.println("tick: " + tick);
//          if(tick >= 40){
//            tick = 0;
//            if(testBo){
//              testBo = false;
//              shape.getColor().a = 0;
//            }
//            else {
//              testBo = true;
//              shape.getColor().a = 255;
//            }
//          }
          //shape.getColor().a = 0;

          //end test


          if(countTurn){
            if(shape.getY() >= Config.HeightScreen/2 + Config.HeightScreen/20){
              game.score++;
              countTurn = false;
            }
          }
          if(isNext){
            if(shape.getY() >= 0){
              game.lvtest.startLv();
              isNext = false;
            }
          }
          else{
            if(checkOverlap(new Vector2(shape.getWidth(), shape.getHeight()), ball.getWH(), new Vector2(shape.getX() + shape.getWidth()/2, shape.getY() + shape.getHeight()/2),ball.getXY())){
              SoundEffect.Play(SoundEffect.broken);
              SoundEffect.Stopmusic(1);
              System.out.println("overlap!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
              ball.overlap();
              ball.setPause(true);
              group.setPause(true);
              game.endGame();
            }
          }
            return true;
        })
      ),
      GSimpleAction.simpleAction((d, a)->{
        reset();
        return true;
      })
    ));
  }

  @Override
  public void act(float delta) {
    super.act(delta);
  }

  public void activeNext(){
    isNext = true;
    countTurn = true;
    showRedLine();
    if(game.getTurn() == 100){
      showFinishRace();
    }
  }

  private void showRedLine(){
    System.out.println("enter best score");
    switch (Config.modeSelecting){
      case 0: {
        if(game.getTurn() == Config.bestScoreInf ){
          Image redLine = GUI.createImage(atlas, "redLine");
          Image bestTxt = GUI.createImage(atlas, "bestTxt");
          group.addActor(redLine);
          group.addActor(bestTxt);
          redLine.setPosition(0, shape.getY() - redLine.getHeight());
          bestTxt.setPosition(Config.POSSITION_ROCK_X[0], shape.getY() - redLine.getHeight() - bestTxt.getHeight()/2, Align.center);
          float dua = (Config.HeightScreen + shape.getHeight() - redLine.getY())/Config.velocity;
          redLine.addAction(Actions.moveTo(0, Config.HeightScreen + shape.getHeight(), dua, linear));

          float dua1 = (Config.HeightScreen + shape.getHeight() - bestTxt.getY())/Config.velocity;
          bestTxt.addAction(Actions.moveTo(bestTxt.getX(), Config.HeightScreen + shape.getHeight(), dua1, linear));

        }
        break;
      }
      case 1: {
        System.out.println("turn-bestScore1: " + game.getTurn() + "-" + Config.bestScoreLv1);
        if(game.getTurn() == Config.bestScoreLv1 && Config.bestScoreLv1 != 100){
          System.out.println("enter best score 1");
          Image redLine = GUI.createImage(atlas, "redLine");
          Image bestTxt = GUI.createImage(atlas, "bestTxt");
          group.addActor(redLine);
          group.addActor(bestTxt);
          redLine.setPosition(0, shape.getY() - redLine.getHeight());
          bestTxt.setPosition(Config.POSSITION_ROCK_X[0], shape.getY() - redLine.getHeight() - bestTxt.getHeight()/2, Align.center);
          float dua = (Config.HeightScreen + shape.getHeight() - redLine.getY())/Config.velocity;
          redLine.addAction(Actions.moveTo(0, Config.HeightScreen + shape.getHeight(), dua, linear));

          float dua1 = (Config.HeightScreen + shape.getHeight() - bestTxt.getY())/Config.velocity;
          bestTxt.addAction(Actions.moveTo(bestTxt.getX(), Config.HeightScreen + shape.getHeight(), dua1, linear));

        }
        break;
      }
      case 2: {
        System.out.println("turn-bestScore2: " + game.getTurn() + "-" + Config.bestScoreLv2);
        if(game.getTurn() == Config.bestScoreLv2 && Config.bestScoreLv2 != 100){
          Image redLine = GUI.createImage(atlas, "redLine");
          Image bestTxt = GUI.createImage(atlas, "bestTxt");
          group.addActor(redLine);
          group.addActor(bestTxt);
          redLine.setPosition(0, shape.getY() - redLine.getHeight());
          bestTxt.setPosition(Config.POSSITION_ROCK_X[0], shape.getY() - redLine.getHeight() - bestTxt.getHeight()/2, Align.center);

          Vector2 vt1 = new Vector2(redLine.getX(), redLine.getY());
          Vector2 vt2 = new Vector2(0, Config.HeightScreen + shape.getHeight());
          //float dua = Config.module(vt1, vt2)/Config.velocity;
          float dua = (Config.HeightScreen + shape.getHeight() - redLine.getY())/Config.velocity;
          redLine.addAction(Actions.moveTo(0, Config.HeightScreen + shape.getHeight(), dua, linear));

          float dua1 = (Config.HeightScreen + shape.getHeight() - bestTxt.getY())/Config.velocity;
          bestTxt.addAction(Actions.moveTo(bestTxt.getX(), Config.HeightScreen + shape.getHeight(), dua1, linear));
        }
        break;
      }
      case 3: {
        System.out.println("turn-bestScore3: " + game.getTurn() + "-" + Config.bestScoreLv3);
        if(game.getTurn() == Config.bestScoreLv3 && Config.bestScoreLv3 != 100){
          Image redLine = GUI.createImage(atlas, "redLine");
          Image bestTxt = GUI.createImage(atlas, "bestTxt");
          group.addActor(redLine);
          group.addActor(bestTxt);
          redLine.setPosition(0, shape.getY() - redLine.getHeight());
          bestTxt.setPosition(Config.POSSITION_ROCK_X[0], shape.getY() - redLine.getHeight() - bestTxt.getHeight()/2, Align.center);

          Vector2 vt1 = new Vector2(redLine.getX(), redLine.getY());
          Vector2 vt2 = new Vector2(0, Config.HeightScreen + shape.getHeight());
          //float dua = Config.module(vt1, vt2)/Config.velocity;
          float dua = (Config.HeightScreen + shape.getHeight() - redLine.getY())/Config.velocity;
          redLine.addAction(Actions.moveTo(0, Config.HeightScreen + shape.getHeight(), dua, linear));

          float dua1 = (Config.HeightScreen + shape.getHeight() - bestTxt.getY())/Config.velocity;
          bestTxt.addAction(Actions.moveTo(bestTxt.getX(), Config.HeightScreen + shape.getHeight(), dua1, linear));
        }
        break;
      }
      default: {
        break;
      }
    }
  }

  private void showFinishRace(){
    if(Config.modeSelecting != 0){
      Image finishRace = GUI.createImage(atlas, "finish");
      group.addActor(finishRace);
      finishRace.setPosition(0, shape.getY() - shape.getHeight()-finishRace.getHeight());
      float dua = (Config.HeightScreen-finishRace.getY())/Config.velocity;
      System.out.println("dua: " + dua + " y: " + finishRace.getY());
      finishRace.addAction(Actions.parallel(
        Actions.moveTo(0, Config.HeightScreen, dua, linear),
        GSimpleAction.simpleAction((d, a)->{
          if(finishRace.getY() > ball.getXY().y + ball.getWH().y/2){
            SoundEffect.Stopmusic(1);
            SoundEffect.Play(SoundEffect.win);
            ball.setPause(true);
            group.setPause(true);
            game.endGame();
          }
          return true;
        })
      ));
    }
  }

  protected void reset(){
    isNext = false;
    countTurn = false;
    shape.setVisible(false);
  }

  public float getWidthS(){
    return shape.getWidth();
  }

  public float getHeightS(){
    return shape.getHeight();
  }

  private boolean checkOverlap(Vector2 whBigRock, Vector2 whSmallRock, Vector2 pB, Vector2 pSm){
    if(isPointOfRect(new Vector2(pSm.x - whSmallRock.x/2, pSm.y - whSmallRock.y/2), whBigRock, pB) ||
      isPointOfRect(new Vector2(pSm.x + whSmallRock.x/2, pSm.y - whSmallRock.y/2), whBigRock, pB) ||
      isPointOfRect(new Vector2(pSm.x + whSmallRock.x/2, pSm.y + whSmallRock.y/2), whBigRock, pB) ||
      isPointOfRect(new Vector2(pSm.x - whSmallRock.x/2, pSm.y + whSmallRock.y/2), whBigRock, pB))
      return true;
    return false;
  }

  private boolean isPointOfRect(Vector2 p, Vector2 whRect, Vector2 pRect) {
    if (p.x >= pRect.x - whRect.x / 2 && p.x <= pRect.x + whRect.x / 2 && p.y >= pRect.y - whRect.y / 2 && p.y <= pRect.y + whRect.y / 2){
      //System.out.println("true");
      return true;
    }
    //System.out.println("false");
    return false;
  }
}
