package com.mygdx.game.Screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.CharArray;
import com.mygdx.game.GameLogic.Dragable;
import com.mygdx.game.GameLogic.GameBoard;
import com.mygdx.game.GameLogic.GamePiece;
import com.mygdx.game.StaticVariables;
import com.mygdx.game.Tak;
import com.mygdx.game.Screens.Declerations;

import java.util.ArrayDeque;
import java.util.Iterator;


//import com.seg3125.project.GameApp;

public class StackScreen
{
    private Tak app;
    private Stage stage;
    private Image images[];

    private TextButton.TextButtonStyle textButtonStyle[];
    private TextButton textButtons[];

    private Declerations declerations;

    private int selected;
    private int stack;

    public StackScreen(Tak gameApp, Stage appStage)
    {
        app = gameApp;
        stage = appStage;

        declerations = new Declerations();
        declerations.initStackScreen();


        images = new Image[20];

        for(int i=0; i<11; i++)
        {
            images[i] = new Image();
        }


        images[0] = new Image(declerations.stackSkin[0],"selectBackground");
        images[0].setBounds(StaticVariables.V_WIDTH/10 ,StaticVariables.V_HEIGHT/10, StaticVariables.V_WIDTH - (StaticVariables.V_WIDTH/5) , StaticVariables.V_HEIGHT - (StaticVariables.V_HEIGHT/5));



        textButtonStyle = new TextButton.TextButtonStyle[8];
        textButtons = new TextButton[8];

        for(int i=0; i<8; i++)
        {
            textButtonStyle[i] = new TextButton.TextButtonStyle();
        }

        textButtonStyle[0].font = declerations.font;
        textButtonStyle[0].up = declerations.stackSkin[13].getDrawable("upButtonPressed");//up
        textButtonStyle[0].down = declerations.stackSkin[14].getDrawable("upButtonUnpressed");
        textButtonStyle[0].checked =  declerations.stackSkin[13].getDrawable("upButtonPressed");

        textButtonStyle[1].font = declerations.font;
        textButtonStyle[1].up = declerations.stackSkin[15].getDrawable("downButtonUnpressed");//down
        textButtonStyle[1].down = declerations.stackSkin[16].getDrawable("downButtonPressed");
        textButtonStyle[1].checked =  declerations.stackSkin[15].getDrawable("downButtonUnpressed");

        textButtonStyle[2].font = declerations.font;
        textButtonStyle[2].up = declerations.stackSkin[17].getDrawable("selectButtonUnpressed");//select
        textButtonStyle[2].down = declerations.stackSkin[18].getDrawable("selectButtonPressed");
        textButtonStyle[2].checked =  declerations.stackSkin[17].getDrawable("selectButtonUnpressed");

        textButtonStyle[3].font = declerations.font;
        textButtonStyle[3].up = declerations.stackSkin[19].getDrawable("backButtonUnpressed");//back
        textButtonStyle[3].down = declerations.stackSkin[20].getDrawable("backButtonPresed");
        textButtonStyle[3].checked =  declerations.stackSkin[19].getDrawable("backButtonUnpressed");


        for(int i=0; i<4; i++)
        {
            textButtons[i] = new TextButton("", textButtonStyle[i]);
        }

        textButtons[0].setBounds(150,(StaticVariables.V_HEIGHT/2)-95,80,80);
        textButtons[1].setBounds(240,(StaticVariables.V_HEIGHT/2)-95,80,80);
        textButtons[2].setBounds(340,(StaticVariables.V_HEIGHT/2)-100,30,90);
        textButtons[3].setBounds(380,(StaticVariables.V_HEIGHT/2)-100,30,90);





    }

    public void initStack(GameBoard board, int x, int y){

       ArrayDeque<GamePiece> array = board.getStack(x,y);
        Iterator <GamePiece> tmp = array.iterator();
      // GamePiece tmpPiece = (GamePiece)tmp.next();
        GamePiece tmpPiece;
        selected = 1;
        stack = 2;

        int shift;
        int loop = 0;
        int i = 1;

        while(tmp.hasNext()){
            tmpPiece = (GamePiece)tmp.next();

            shift = 25 * i;
            if (tmpPiece.getPlayer()) { //WHITE PIECES
                if (tmpPiece.getType() == 0) { //road
                    images[i] = new Image(declerations.stackSkin[1], "roadUnselectedWhite"); //unselected
                    //images[i].setSize(0, 0);
                    images[i].setBounds(100 + shift, (StaticVariables.V_HEIGHT / 2) + 50, 50, 50);


                    images[i + 1] = new Image(declerations.stackSkin[2], "roadSelectedWhite"); //selected
                    // images[i+1].setSize(0, 0);
                    images[i + 1].setBounds(100 + shift, (StaticVariables.V_HEIGHT / 2) + 50, 50, 50);


                } else if (tmpPiece.getType() == 1) { //wall
                    images[i] = new Image(declerations.stackSkin[3], "wallUnselectedWhite"); //unselected
                    // images[i].setSize(0, 0);
                    images[i].setBounds(100 + shift, (StaticVariables.V_HEIGHT / 2) + 50, 50, 50);


                    images[i + 1] = new Image(declerations.stackSkin[4], "wallSelectedWhite"); //selected
                    // images[i+1].setSize(0, 0);
                    images[i + 1].setBounds(100 + shift, (StaticVariables.V_HEIGHT / 2) + 50, 50, 50);


                } else if (tmpPiece.getType() == 2) { //cap
                    images[i] = new Image(declerations.stackSkin[5], "capUnselectedWhite"); //unselected
                    //images[i].setSize(0, 0);
                    images[i].setBounds(100 + shift, (StaticVariables.V_HEIGHT / 2) + 50, 50, 50);

                    images[i + 1] = new Image(declerations.stackSkin[6], "capSelectedWhite"); //selected
                    //images[i+1].setSize(0, 0);
                    images[i + 1].setBounds(100 + shift, (StaticVariables.V_HEIGHT / 2) + 50, 50, 50);
                }
            } else { //BLACK PIECES
                if (tmpPiece.getType() == 0) { //road
                    images[i] = new Image(declerations.stackSkin[7], "roadUnselectedBlack"); //unselected
                    //images[i].setSize(0, 0);
                    images[i].setBounds(100 + shift, (StaticVariables.V_HEIGHT / 2) + 50, 50, 50);


                    images[i + 1] = new Image(declerations.stackSkin[8], "roadSelectedBlack"); //selected
                    // images[i+1].setSize(0, 0);
                    images[i + 1].setBounds(100 + shift, (StaticVariables.V_HEIGHT / 2) + 50, 50, 50);


                } else if (tmpPiece.getType() == 1) { //wall
                    images[i] = new Image(declerations.stackSkin[9], "wallUnselectedBlack"); //unselected
                    // images[i].setSize(0, 0);
                    images[i].setBounds(100 + shift, (StaticVariables.V_HEIGHT / 2) + 50, 50, 50);


                    images[i + 1] = new Image(declerations.stackSkin[10], "wallSelectedBlack"); //selected
                    // images[i+1].setSize(0, 0);
                    images[i + 1].setBounds(100 + shift, (StaticVariables.V_HEIGHT / 2) + 50, 50, 50);


                } else if (tmpPiece.getType() == 2) { //cap
                    images[i] = new Image(declerations.stackSkin[11], "capUnselectedBlack"); //unselected
                    //images[i].setSize(0, 0);
                    images[i].setBounds(100 + shift, (StaticVariables.V_HEIGHT / 2) + 50, 50, 50);

                    images[i + 1] = new Image(declerations.stackSkin[12], "capSelectedBlack"); //selected
                    //images[i+1].setSize(0, 0);
                    images[i + 1].setBounds(100 + shift, (StaticVariables.V_HEIGHT / 2) + 50, 50, 50);
                }
            }

            loop++;
            i+=2;

        }
        displayStackMenu();

    }

    private void displayStackMenu(){

        stage.addActor( images[0]);
        stage.addActor(textButtons[0]);
        stage.addActor(textButtons[1]);
        stage.addActor(textButtons[2]);
        stage.addActor(textButtons[3]);

        for(int i = 1; i <images.length ; i+=2) {
            if(images[i] != null) {
                if (i == 1) {
                    stage.addActor(images[i + 1]);
                } else {
                    stage.addActor(images[i]);
                }
            }
        }

    }



    public void selectionOptions(final GameBoard board,final int x, final int y,final Dragable p)
    {


        initStack(board,x,y);

        textButtons[0].addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                if(selected > 1) {

                    images[stack].remove();
                    stage.addActor(images[stack-1]);
                    selected--;
                    stack -=2;
                   // System.out.println(selected);
                }

            }
        });

        textButtons[1].addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                if(selected < 4) {
                    selected++;
                    stack += 2;
                   // System.out.println(selected);
                    images[stack - 1].remove();
                    stage.addActor(images[stack]);
                }
            }
        });

        textButtons[2].addListener(new ChangeListener() //select button
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
               //piece.setSelected(selected);
                p.setSelected(selected);
                p.fillStack(board,x,y);
                clear();
            }
        });



    }



    public void returnToGame()
    {
        textButtons[3].addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {

              clear();
            }
        });

    }

    private void clear()
    {
        images[0].remove();

        textButtons[0].remove();
        textButtons[1].remove();
        textButtons[2].remove();
        textButtons[3].remove();

        for(int i=0; i<images.length;i++){
            if(images[i] != null) {
                images[i].clear();
                images[i].remove();
            }
        }
    }
}
