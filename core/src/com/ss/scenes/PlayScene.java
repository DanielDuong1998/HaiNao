package com.ss.scenes;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.ss.commons.Tweens;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.exSprite.GShapeSprite;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.effects.SoundEffect;
import com.ss.gameLogic.Background;
import com.ss.gameLogic.Level.Level;
import com.ss.gameLogic.Level.Level1;
import com.ss.gameLogic.Level.Level2;
import com.ss.gameLogic.Level.Level3;
import com.ss.gameLogic.Level.LevelInfinity;
import com.ss.gameLogic.PanelEndGame;
import com.ss.gameLogic.StaticObjects.Config;
import com.ss.gameLogic.objects.Ball;
import com.ss.gameLogic.objects.ManageRocks;
import com.ss.gameLogic.objects.Thumb.LeftThumb;
import com.ss.gameLogic.objects.Thumb.RightThumb;
import com.ss.gameLogic.objects.Thumb.Thumb;

public class PlayScene extends GScreen {
  private TextureAtlas atlas;
  private GLayerGroup mainGroup, pauseGroup, endGameGroup;
  private GLayerGroup foreGroundGroup, groupF;
  private Background background;
  private float accum = 0;
  public Ball balll;
  public Ball ballr;
  private Thumb thumbl;
  private Thumb thumbr;
  private ManageRocks manageRocks;
  public Level lvtest;

  private Image pauseBtn, continueBtn, quitBtn;
  private Array<Image> txtCountDown;
  private GShapeSprite blackOverlay, blackOverlay2;
  private PanelEndGame panelEndGame;
  int tick = 0;
  public int score = 0;

  private BitmapFont font, fontPlayBall;
  private Label labelScore;
  private Image underFrame, frameScore;
  private Image power;
  private Image helpStartImg;

  private int turnGame = 0;


  @Override
  public void dispose() {

  }



  @Override
  public void init() {
    System.out.println("music: " + SoundEffect.music);
    SoundEffect.Playmusic(1);
    initAtlas();
    initGroup();
    initBackground();
    initBitmapfont();
    initUI();
    initDarkScreen();
    initCountDownTxt();
    if(Config.showHelpStart){
      Config.showHelpStart = false;
      initHelp();
    }
    else{
      background.activeScroll();
      balll = new Ball(groupF);
      ballr = new Ball(groupF);

      thumbl = new LeftThumb(atlas, groupF, balll);
      thumbr = new RightThumb(atlas, groupF, ballr);

      if(Config.isContinue){
        setUpContinue();
      }
      else {
        setUpTimeScale();
      }
      startGame();
    }
  }



  private void setUpTimeScale(){
    switch (Config.modeSelecting){
      case 0: {
        Config.scaleTimeCtn[0] = 1000;
        Config.scaleTimeCtn[1] = 1.8f;
        break;
      }
      case 1: {
        Config.scaleTimeCtn[0] = 1000;
        Config.scaleTimeCtn[1] = 1.2f;
        break;
      }
      case 2: {
        Config.scaleTimeCtn[0] = 1000;
        Config.scaleTimeCtn[1] = 1.4f;
        break;
      }
      case 3: {
        Config.scaleTimeCtn[0] = 1000;
        Config.scaleTimeCtn[1] = 1.6f;
        break;
      }
    }
  }

  private void initAtlas(){
    atlas = GAssetsManager.getTextureAtlas("GameAtlas.atlas");
  }

  private void initGroup(){
    mainGroup = new GLayerGroup(){
      @Override
      public void act(float var1) {
      super.act(var1);
        if(labelScore != null){
          if(Config.modeSelecting == 0)
            labelScore.setText(score + "m");
          else {
            labelScore.setText(score + "%");
            power.setScaleX(score*0.01f);
          }
        }
      }
    };
    pauseGroup = new GLayerGroup();
    endGameGroup = new GLayerGroup();
    foreGroundGroup = new GLayerGroup(){
      @Override
      public void act(float var1) {
        if(Config.scaleTime[0] != 0){
          accum += var1;
        }
        if (accum < Config.scaleTime[0]) {
          super.act(var1*Config.scaleTime[1]);
        }
        else {
          super.act(var1);
          accum = 0;
          //Config.scaleTime[0] = 0;
          //Config.scaleTime[1] = 2;
        }
      }
    };
    groupF = new GLayerGroup();
    mainGroup.addActor(foreGroundGroup);
    mainGroup.addActor(groupF);
    GStage.addToLayer(GLayer.ui, mainGroup);
    GStage.addToLayer(GLayer.ui, pauseGroup);
    GStage.addToLayer(GLayer.ui, endGameGroup);
  }

  //start test
  private void startGame(){
    boolean isPause = false;
    manageRocks = new ManageRocks(atlas, foreGroundGroup, balll, ballr, this);

    switch (Config.modeSelecting){
      case 0: {
        lvtest = new LevelInfinity(manageRocks, turnGame);
        break;
      }
      case 1: {
        lvtest = new Level1(manageRocks, turnGame);
        break;
      }
      case 2: {
        lvtest = new Level2(manageRocks, turnGame);
        break;
      }
      case 3: {
        lvtest = new Level3(manageRocks, turnGame);
        break;
      }
    }
  }
  //end test


  private void initBackground(){
    background = new Background(atlas, foreGroundGroup);
  }

  private void initUI(){
    panelEndGame = new PanelEndGame(atlas, endGameGroup, this, score);

    pauseBtn = GUI.createImage(atlas, "pauseBtn");
    continueBtn = GUI.createImage(atlas, "continueBtn");
    quitBtn = GUI.createImage(atlas, "quitBtn");
    pauseGroup.addActor(pauseBtn);
    pauseGroup.addActor(continueBtn);
    pauseGroup.addActor(quitBtn);
    pauseBtn.setPosition(Config.WidthScreen/8, Config.HeightScreen/18, Align.center);
    continueBtn.setPosition(Config.WidthScreen/2, Config.HeightScreen/2 - continueBtn.getHeight(), Align.center);
    quitBtn.setPosition(Config.WidthScreen/2, Config.HeightScreen/2 + continueBtn.getHeight(), Align.center);
    pauseBtn.setOrigin(Align.center);
    continueBtn.setOrigin(Align.center);
    quitBtn.setOrigin(Align.center);

    continueBtn.setVisible(false);
    quitBtn.setVisible(false);
    addEventPauseBtn();
    addEventContinueBtn();
    addEventQuitBtn();
  }

  private void addEventPauseBtn(){
    pauseBtn.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
      pauseBtn.setTouchable(Touchable.disabled);
      SoundEffect.Play(SoundEffect.click);
      clickBtnEffect(pauseBtn, ()->{
        continueBtn.setTouchable(Touchable.enabled);
        continueBtn.setVisible(true);
        quitBtn.setVisible(true);
        mainGroup.setPause(true);
        setColorDarkScreen(true, 0.8f);
      });
      return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void addEventContinueBtn(){
    continueBtn.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        continueBtn.setTouchable(Touchable.disabled);
        SoundEffect.Play(SoundEffect.click);
        clickBtnEffect(continueBtn, ()->{
          continueBtn.setVisible(false);
          quitBtn.setVisible(false);
          countDown();
          Tweens.setTimeout(pauseGroup, 3f, ()->{
            pauseBtn.setTouchable(Touchable.enabled);
            setColorDarkScreen(false, 0);
            mainGroup.setPause(false);
          });
        });
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void addEventQuitBtn(){
    quitBtn.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
      quitBtn.setTouchable(Touchable.disabled);
      SoundEffect.Play(SoundEffect.click);
      clickBtnEffect(quitBtn, ()->{
        SoundEffect.Stopmusic(1);
        Config.showHelpStart = true;
        setScreen(new StartScene());
      });
      return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void clickBtnEffect(Image btn, Runnable runnable){
    btn.addAction(Actions.sequence(
      Actions.scaleBy(-0.3f, -0.3f, 0.05f, Interpolation.swingIn),
      Actions.scaleBy(0.3f, 0.3f, 0.05f, Interpolation.swingOut),
      Actions.run(runnable)
    ));
  }

  private void initCountDownTxt(){
    txtCountDown = new Array<>();
    Image readyTxt = GUI.createImage(atlas, "readyTxt");
    Image one = GUI.createImage(atlas, "1");
    Image two = GUI.createImage(atlas, "2");
    Image three = GUI.createImage(atlas, "3");
    txtCountDown.add(readyTxt, one, two, three);
    pauseGroup.addActor(readyTxt);
    pauseGroup.addActor(one);
    pauseGroup.addActor(two);
    pauseGroup.addActor(three);
    for(int i = 0; i < txtCountDown.size; i++){
      txtCountDown.get(i).setOrigin(Align.center);
    }
    readyTxt.setPosition(Config.WidthScreen/2, Config.HeightScreen/3, Align.center);
    one.setPosition(Config.WidthScreen/2, Config.HeightScreen/2, Align.center);
    two.setPosition(Config.WidthScreen/2, Config.HeightScreen/2, Align.center);
    three.setPosition(Config.WidthScreen/2, Config.HeightScreen/2, Align.center);
    readyTxt.setVisible(false);
    one.setVisible(false);
    two.setVisible(false);
    three.setVisible(false);
  }

  private void initHelp(){
    helpStartImg = GUI.createImage(atlas, "helpStart");
    helpStartImg.setPosition(Config.WidthScreen/2, Config.HeightScreen/2, Align.center);
    GLayerGroup groupDark = new GLayerGroup();
    GStage.addToLayer(GLayer.top, groupDark);
    blackOverlay2 = new GShapeSprite();
    blackOverlay2.createRectangle(true, -GStage.getWorldWidth()/2,-GStage.getWorldHeight()/2, GStage.getWorldWidth()*2, GStage.getWorldHeight()*2);
    groupDark.addActor(blackOverlay2);
    groupDark.addActor(helpStartImg);
    blackOverlay2.setVisible(true);
    blackOverlay2.setColor(0, 0, 0, 0.3f);
    eventHelp(groupDark);
  }

  private void eventHelp(GLayerGroup group){
    group.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        group.remove();
        group.clear();
        background.activeScroll();
        balll = new Ball(groupF);
        ballr = new Ball(groupF);

        thumbl = new LeftThumb(atlas, groupF, balll);
        thumbr = new RightThumb(atlas, groupF, ballr);
        setUpTimeScale();
        startGame();
        return super.touchDown(event, x, y, pointer, button);
      }
    });

  }

  private void initBitmapfont(){
    underFrame = GUI.createImage(atlas, "underFrame");
    power = GUI.createImage(atlas, "power");
    frameScore = GUI.createImage(atlas, "frame");

    font = GAssetsManager.getBitmapFont("sigmarOne.fnt");
    fontPlayBall = GAssetsManager.getBitmapFont("playBall.fnt");
    labelScore = new Label("" + 0, new Label.LabelStyle(fontPlayBall, null));

    mainGroup.addActor(underFrame);
    mainGroup.addActor(power);
    mainGroup.addActor(frameScore);
    underFrame.setPosition(Config.WidthScreen*3.5f/5, Config.HeightScreen/17f, Align.center);
    power.setPosition(Config.WidthScreen*3.5f/5, Config.HeightScreen/17f, Align.center);
    frameScore.setPosition(Config.WidthScreen*3.5f/5, Config.HeightScreen/18, Align.center);
    power.setScaleX(0);
    labelScore.setPosition(frameScore.getX() + frameScore.getWidth()/2, frameScore.getY() + frameScore.getHeight()*1.25f/2, Align.center);
    mainGroup.addActor(labelScore);
  }

  private void pauseBtnClick(){

  }

  private void countDown(){
    txtCountDown.get(0).setVisible(true);
    showTxt(1);
  }

  private void showTxt(int index){
    if(index == txtCountDown.size){
      txtCountDown.get(0).setVisible(false);
      return;
    }
    else{
      txtCountDown.get(index).setVisible(true);
      txtCountDown.get(index).setScale(0.3f);
      final int indexTemp = index + 1;
      txtCountDown.get(index).addAction(Actions.sequence(
        Actions.scaleTo(1, 1, 0.8f, Interpolation.swingIn),
        GSimpleAction.simpleAction((d, a)->{
          SoundEffect.Play(SoundEffect.tick);
          return true;
        }),
        Actions.delay(0.2f),
        GSimpleAction.simpleAction((d, a)->{
          txtCountDown.get(index).setVisible(false);
          showTxt(indexTemp);
          return true;
        })
      ));
    }
  }

  private void initDarkScreen(){
    blackOverlay = new GShapeSprite();
    blackOverlay.createRectangle(true, -GStage.getWorldWidth()/2,-GStage.getWorldHeight()/2, GStage.getWorldWidth()*2, GStage.getWorldHeight()*2);
    mainGroup.addActor(blackOverlay);
    blackOverlay.setVisible(false);
  }


  private void setColorDarkScreen(boolean isVisible, float blur){
    blackOverlay.setVisible(isVisible);
    blackOverlay.setColor(0, 0, 0, blur);
  }

  @Override
  public void run() {

  }

  public void menu(){
    setScreen(new ModeScene());
  }

  public void lobby(){
    setScreen(new StartScene());
  }


  public void endGame(){
    groupF.setTouchable(Touchable.disabled);
    Tweens.setTimeout(endGameGroup, 0.5f, ()->{
      SoundEffect.Play(SoundEffect.lose);
      SoundEffect.Playmusic(2);
      panelEndGame.setScore(score);
      panelEndGame.setVisibleGroup(true);
    });
  }

  public BitmapFont getFont(){
    return font;
  }

  public void replay(){
    setScreen(new PlayScene());
  }

  public void coninue(){
    Config.isContinue = true;
    Config.scoreCtn = score;
    Config.scaleTimeCtn[0] = Config.scaleTime[0];
    Config.scaleTimeCtn[1] = Config.scaleTime[1];
    System.out.println("time-rto: " + Config.scaleTimeCtn[0] + "-" + Config.scaleTimeCtn[1]);
    setScreen(new PlayScene());
  }

  public void setUpContinue(){
    System.out.println("Complete setUp");
    Config.isContinue = false;
    turnGame = Config.scoreCtn;
    if(Config.modeSelecting != 0){
      labelScore.setText(turnGame + "%");
    }
    else {
      labelScore.setText(turnGame + "m");
    }
    score = turnGame;
    Config.scaleTime[0] = Config.scaleTimeCtn[0];
    Config.scaleTime[1] = Config.scaleTimeCtn[1];
    power.setScaleX(0.01f*1);
  }
//
  public int getTurn(){
    if(lvtest != null)
      return lvtest.getTurn();
    return 0;
  }
}
