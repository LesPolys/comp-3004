package com.mygdx.screens;


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
import com.mygdx.game.SampleGame;
import com.mygdx.util.Declerations;
//import com.seg3125.project.Declerations;

//import com.seg3125.project.GameApp;

public class StackScreen
{
    private SampleGame app;
    private Stage stage;
    private Image images[];

    private TextButton.TextButtonStyle textButtonStyle[];
    private TextButton textButtons[];

    private Declerations declerations;

    private int selected;
    private int stack;

    public StackScreen(SampleGame gameApp, Stage appStage)
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
        images[0].setBounds(SampleGame.V_WIDTH/10 ,SampleGame.V_HEIGHT/10, SampleGame.V_WIDTH - (SampleGame.V_WIDTH/5) , SampleGame.V_HEIGHT - (SampleGame.V_HEIGHT/5));



        textButtonStyle = new TextButton.TextButtonStyle[8];
        textButtons = new TextButton[8];

        for(int i=0; i<8; i++)
        {
            textButtonStyle[i] = new TextButton.TextButtonStyle();
        }

        textButtonStyle[0].font = declerations.font;
        textButtonStyle[0].up = declerations.stackSkin[7].getDrawable("upButtonPressed");//up
        textButtonStyle[0].down = declerations.stackSkin[8].getDrawable("upButtonUnpressed");
        textButtonStyle[0].checked =  declerations.stackSkin[7].getDrawable("upButtonPressed");

        textButtonStyle[1].font = declerations.font;
        textButtonStyle[1].up = declerations.stackSkin[9].getDrawable("downButtonUnpressed");//down
        textButtonStyle[1].down = declerations.stackSkin[10].getDrawable("downButtonPressed");
        textButtonStyle[1].checked =  declerations.stackSkin[9].getDrawable("downButtonUnpressed");

        textButtonStyle[2].font = declerations.font;
        textButtonStyle[2].up = declerations.stackSkin[11].getDrawable("selectButtonUnpressed");//select
        textButtonStyle[2].down = declerations.stackSkin[12].getDrawable("selectButtonPressed");
        textButtonStyle[2].checked =  declerations.stackSkin[12].getDrawable("selectButtonPressed");

        textButtonStyle[3].font = declerations.font;
        textButtonStyle[3].up = declerations.stackSkin[13].getDrawable("backButtonUnpressed");//back
        textButtonStyle[3].down = declerations.stackSkin[14].getDrawable("backButtonPresed");
        textButtonStyle[3].checked =  declerations.stackSkin[14].getDrawable("backButtonPresed");


        for(int i=0; i<4; i++)
        {
            textButtons[i] = new TextButton("", textButtonStyle[i]);
        }

        textButtons[0].setBounds(120,55,80,80);
        textButtons[1].setBounds(210,55,80,80);
        textButtons[2].setBounds(310,50,30,90);
        textButtons[3].setBounds(350,50,30,90);





    }

    private void initStack(){ //TODO TAKES IN AN ARAY OF GAME PIECES


        selected = 1;
        stack = 2;

        int shift;
        int loop = 0;

       Character test[] = {'c','r','r','r','r','c'};

        for(int i = 1; i < test.length*2 ; i+=2){

            shift = 25 * i;

            if(test[loop] == 'r') { //road
                images[i] = new Image(declerations.stackSkin[1],"roadUnselected"); //unselected
                //images[i].setSize(0, 0);
                images[i].setBounds(100 + shift,150, 50, 50);


                images[i+1] = new Image(declerations.stackSkin[2],"roadSelected"); //selected
               // images[i+1].setSize(0, 0);
                images[i+1].setBounds(100 + shift, 150, 50, 50);


            }else if(false){ //wall
                images[i] = new Image(declerations.stackSkin[3],"wallUnselected"); //unselected
               // images[i].setSize(0, 0);
                images[i].setBounds(100 + shift, 150, 50, 50);



                images[i+1] = new Image(declerations.stackSkin[4],"wallSelected"); //selected
               // images[i+1].setSize(0, 0);
                images[i+1].setBounds(100 + shift, 150, 50, 50);


            }else if(test[loop] == 'c'){ //cap
                images[i] = new Image(declerations.stackSkin[5],"capUnselected"); //unselected
                //images[i].setSize(0, 0);
                images[i].setBounds(100 + shift, 150, 50, 50);

                images[i+1] = new Image(declerations.stackSkin[6],"capSelected"); //selected
                //images[i+1].setSize(0, 0);
                images[i+1].setBounds(100 + shift, 150, 50, 50);
            }

            if(i == 1){
                stage.addActor(images[i+1]);
            }else{
                stage.addActor(images[i]);
            }

            loop++;
        }

    }

    private void display(){

        stage.addActor( images[0]);
        stage.addActor(textButtons[0]);
        stage.addActor(textButtons[1]);
        stage.addActor(textButtons[2]);
        stage.addActor(textButtons[3]);

    }



    public void selectionOptions() //TODO TAKES IN AN ARAY OF GAME PIECES
    {

        display();
        initStack(); //TODO PASS IN AN ARAY OF GAME PIECES

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
                    System.out.println(selected);
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
                    System.out.println(selected);
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
                select();
                clear();
            }
        });



    }

    private void select(){

        System.out.println(selected);
        //return selected;
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
            images[i].remove();
        }
    }
}
