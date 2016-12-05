package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameLogic.Dragable;
import com.mygdx.game.GamePieces.DarkSquarePiece1;
import com.mygdx.game.GamePieces.LightSquarePiece1;
import com.mygdx.game.StaticVariables;
import com.mygdx.game.Tak;
import com.mygdx.game.Screens.Declerations;

/**
 * Created by Logan on 10/30/2016.
 */

public class Challenge implements Screen
{
    private Tak app;
    public Stage stage;

    private Image images[];
    private TextButton textButtons[];
    private Declerations declerations;
    private int challengeSelect;

    public Challenge(Tak gameApp)
    {
        app = gameApp;
        Viewport viewport = new FillViewport(StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT, gameApp.camera);
        stage = new Stage(viewport, app.batch);
        Gdx.input.setInputProcessor(stage);


        declerations = new Declerations();
        declerations.initChallengeScreen();

        textButtons = new TextButton[10];

        challengeSelect = 1;

        images = new Image[4];

        for(int i=0; i<4; i++)
        {
            images[i] = new Image();
        }

        //draws the background, how to play instructions and title
        //Background , Main Title, Versus Title, Challenge Title


        images[0] = new Image(declerations.challengeSkin[1],"background"); //Background
        images[0].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[0].setBounds(0,0, StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT);
        stage.addActor(images[0]);


        images[1] = new Image(declerations.challengeSkin[2],"titleChallenge"); //Background
        images[1].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[1].setBounds(25, (StaticVariables.V_HEIGHT/2)-100, 74 ,200);
        stage.addActor(images[1]);

        images[2] = new Image(declerations.challengeSkin[3],"challengeMessage"); //Background
        images[2].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[2].setBounds(105,(StaticVariables.V_HEIGHT/2)-100,50,200);
        stage.addActor(images[2]);


        declerations.challengeMenutextButtonStyles[0].up = declerations.challengeSkin[16].getDrawable("playUnpressed");
        declerations.challengeMenutextButtonStyles[0].down = declerations.challengeSkin[17].getDrawable("playButtonPresed");
        declerations.challengeMenutextButtonStyles[0].checked = declerations.challengeSkin[17].getDrawable("playButtonPresed");

        declerations.challengeMenutextButtonStyles[1].up = declerations.challengeSkin[18].getDrawable("backButtonUnPressed");
        declerations.challengeMenutextButtonStyles[1].down = declerations.challengeSkin[19].getDrawable("backButtonPresed");
        declerations.challengeMenutextButtonStyles[1].checked = declerations.challengeSkin[19].getDrawable("backButtonPresed");

        declerations.challengeMenutextButtonStyles[2].up = declerations.challengeSkin[4].getDrawable("challengeUnpressedOne");// 3x3
        declerations.challengeMenutextButtonStyles[2].down = declerations.challengeSkin[5].getDrawable("challengePressedOne");
        declerations.challengeMenutextButtonStyles[2].checked = declerations.challengeSkin[5].getDrawable("challengePressedOne");

        declerations.challengeMenutextButtonStyles[3].up = declerations.challengeSkin[6].getDrawable("challengeUnpressedTwo");//x4
        declerations.challengeMenutextButtonStyles[3].down = declerations.challengeSkin[7].getDrawable("challengePressedTwo");
        declerations.challengeMenutextButtonStyles[3].checked = declerations.challengeSkin[7].getDrawable("challengePressedTwo");

        declerations.challengeMenutextButtonStyles[4].up = declerations.challengeSkin[8].getDrawable("challengeUnpressedThree");//5x5
        declerations.challengeMenutextButtonStyles[4].down = declerations.challengeSkin[9].getDrawable("challengePressedThree");
        declerations.challengeMenutextButtonStyles[4].checked = declerations.challengeSkin[9].getDrawable("challengePressedThree");

        declerations.challengeMenutextButtonStyles[5].up = declerations.challengeSkin[10].getDrawable("challengeUnpressedFour");//6x6
        declerations.challengeMenutextButtonStyles[5].down = declerations.challengeSkin[11].getDrawable("challengePressedFour");
        declerations.challengeMenutextButtonStyles[5].checked = declerations.challengeSkin[11].getDrawable("challengePressedFour");

        declerations.challengeMenutextButtonStyles[6].up = declerations.challengeSkin[12].getDrawable("challengeUnpressedFive");//7x7
        declerations.challengeMenutextButtonStyles[6].down = declerations.challengeSkin[13].getDrawable("challengePressedFive");
        declerations.challengeMenutextButtonStyles[6].checked = declerations.challengeSkin[13].getDrawable("challengePressedFive");

        declerations.challengeMenutextButtonStyles[7].up = declerations.challengeSkin[14].getDrawable("challengeUnpressedSix");//8x8
        declerations.challengeMenutextButtonStyles[7].down = declerations.challengeSkin[15].getDrawable("challengePressedSix");
        declerations.challengeMenutextButtonStyles[7].checked = declerations.challengeSkin[15].getDrawable("challengePressedSix");



        for(int i=0; i < 2; i++)
        {
            int shift = 75 * i;
            textButtons[i] = new TextButton("", declerations.challengeMenutextButtonStyles[i]);
            textButtons[i].setBounds((300 + shift),(StaticVariables.V_HEIGHT/2)-100,50,200);
            stage.addActor(textButtons[i]);
        }

        int counter = 2;
        for(int x=0; x < 2; x++)
        {
            for(int y=0; y < 3; y++) {
                int xshift = 75 * x ;
                int yshift = 75 * y;
                textButtons[counter] = new TextButton("", declerations.challengeMenutextButtonStyles[counter]);
                textButtons[counter].setBounds((160 + xshift), ((StaticVariables.V_HEIGHT/2)-100 + yshift), 50, 50);
                stage.addActor(textButtons[counter]);
                counter++;
            }
        }
        ButtonGroup buttonGroup = new ButtonGroup(textButtons[2],textButtons[3],textButtons[4],textButtons[5],textButtons[6],textButtons[7]);
//next set the max and min amount to be checked
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(1);
        buttonGroup.setChecked("");
//it may be useful to use this method:
        buttonGroup.setUncheckLast(true); //If true, when the maximum number of buttons are checked and an additional button is checked, the last button to be checked is unchecked so that the maximum is not exceeded.

        //button listeners
        textButtons[0].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {

                //launch challenge based on challenge select variable proabbaly use a switch here for it
//launch challenge based on challenge select variable proabbaly use a switch here for it
                int bsize;
                PlayScreen challengeScreen;
                DarkSquarePiece1 darkSquarePiece1;
                LightSquarePiece1 lightSquarePiece1;

                Dragable[] darkSquarePiece1Dragable;
                Dragable[] lightSquarePiece1Dragable;

                int posX, posY, i, type, index;
                boolean player;

                switch(challengeSelect) {
                    case 1:
                        bsize = 3;

                        challengeScreen = new PlayScreen(app, bsize);

                        darkSquarePiece1 = new DarkSquarePiece1();
                        lightSquarePiece1 = new LightSquarePiece1();

                        darkSquarePiece1Dragable = new Dragable[4];
                        lightSquarePiece1Dragable = new Dragable[4];


                        //black piece creation and placement
                        player = false;
                        index = 0;
                        //0,0
                        i = 0;posX = 0;posY = 0;type = 0;
                        darkSquarePiece1Dragable[i] = new Dragable(darkSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        darkSquarePiece1Dragable[i].setGridPos(posX, posY);
                        darkSquarePiece1Dragable[i].setBounds(235+(posX*30),125+(posY*30),30,30);
                        darkSquarePiece1Dragable[i].makeDraggable();
                        darkSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(darkSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //0,2
                        i = 1;posX = 0;posY = 2;type = 0;
                        darkSquarePiece1Dragable[i] = new Dragable(darkSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        darkSquarePiece1Dragable[i].setGridPos(posX, posY);
                        darkSquarePiece1Dragable[i].setBounds(235+(posX*30),125+(posY*30),30,30);
                        darkSquarePiece1Dragable[i].makeDraggable();
                        darkSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(darkSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //2,0
                        i = 2;posX = 2;posY = 0;type = 0;
                        darkSquarePiece1Dragable[i] = new Dragable(darkSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        darkSquarePiece1Dragable[i].setGridPos(posX, posY);
                        darkSquarePiece1Dragable[i].setBounds(235+(posX*30),125+(posY*30),30,30);
                        darkSquarePiece1Dragable[i].makeDraggable();
                        darkSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(darkSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //2,2
                        i = 3;posX = 2;posY = 2;type = 0;
                        darkSquarePiece1Dragable[i] = new Dragable(darkSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        darkSquarePiece1Dragable[i].setGridPos(posX, posY);
                        darkSquarePiece1Dragable[i].setBounds(235+(posX*30),125+(posY*30),30,30);
                        darkSquarePiece1Dragable[i].makeDraggable();
                        darkSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(darkSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //white piece creation and placement
                        player = true;
                        //1,0
                        i = 0;posX = 1;posY = 0;type = 0;
                        lightSquarePiece1Dragable[i] = new Dragable(lightSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        lightSquarePiece1Dragable[i].setGridPos(posX, posY);
                        lightSquarePiece1Dragable[i].setBounds(235+(posX*30),125+(posY*30),30,30);
                        lightSquarePiece1Dragable[i].makeDraggable();
                        lightSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(lightSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //1,1 1
                        i = 1;posX = 1;posY = 1;type = 0;
                        lightSquarePiece1Dragable[i] = new Dragable(lightSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        lightSquarePiece1Dragable[i].setGridPos(posX, posY);
                        lightSquarePiece1Dragable[i].setBounds(235+(posX*30),125+(posY*30),30,30);
                        lightSquarePiece1Dragable[i].makeDraggable();
                        lightSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(lightSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //1,1 2
                        i = 2;posX = 1;posY = 1;type = 0;index =1;
                        lightSquarePiece1Dragable[i] = new Dragable(lightSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        lightSquarePiece1Dragable[i].setGridPos(posX, posY);
                        lightSquarePiece1Dragable[i].setBounds(235+(posX*30),125+(posY*30),30,30);
                        lightSquarePiece1Dragable[i].makeDraggable();
                        lightSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(lightSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index );

                        //2,1
                        i = 3;posX = 2;posY = 1;type = 0;index =0;
                        lightSquarePiece1Dragable[i] = new Dragable(lightSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        lightSquarePiece1Dragable[i].setGridPos(posX, posY);
                        lightSquarePiece1Dragable[i].setBounds(235+(posX*30),125+(posY*30),30,30);
                        lightSquarePiece1Dragable[i].makeDraggable();
                        lightSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(lightSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        ((Game) Gdx.app.getApplicationListener()).setScreen(challengeScreen);


                        break;
                    case 2:
                        //challenge 2
                        bsize = 4;

                        challengeScreen = new PlayScreen(app, bsize);

                        darkSquarePiece1 = new DarkSquarePiece1();
                        lightSquarePiece1 = new LightSquarePiece1();

                        darkSquarePiece1Dragable = new Dragable[6];
                        lightSquarePiece1Dragable = new Dragable[7];


                        //0,0
                        i = 0;posX = 0;posY = 0;type = 0;player = false;index = 0;
                        darkSquarePiece1Dragable[i] = new Dragable(darkSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        darkSquarePiece1Dragable[i].setGridPos(posX, posY);
                        darkSquarePiece1Dragable[i].setBounds(220+(posX*30),110+(posY*30),30,30);
                        darkSquarePiece1Dragable[i].makeDraggable();
                        darkSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(darkSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //0,1
                        i = 1;posX = 0;posY = 1;type = 0;player = false;
                        darkSquarePiece1Dragable[i] = new Dragable(darkSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        darkSquarePiece1Dragable[i].setGridPos(posX, posY);
                        darkSquarePiece1Dragable[i].setBounds(220+(posX*30),110+(posY*30),30,30);
                        darkSquarePiece1Dragable[i].makeDraggable();
                        darkSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(darkSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //0,2
                        i = 0;posX = 0;posY = 2;type = 0;player = true;
                        lightSquarePiece1Dragable[i] = new Dragable(lightSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        lightSquarePiece1Dragable[i].setGridPos(posX, posY);
                        lightSquarePiece1Dragable[i].setBounds(220+(posX*30),110+(posY*30),30,30);
                        lightSquarePiece1Dragable[i].makeDraggable();
                        lightSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(lightSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //1,1 1
                        i = 1;posX = 1;posY = 1;type = 0;player = true;
                        lightSquarePiece1Dragable[i] = new Dragable(lightSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        lightSquarePiece1Dragable[i].setGridPos(posX, posY);
                        lightSquarePiece1Dragable[i].setBounds(220+(posX*30),110+(posY*30),30,30);
                        lightSquarePiece1Dragable[i].makeDraggable();
                        lightSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(lightSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //1,1 2
                        i = 2;posX = 1;posY = 1;type = 0;player = false;index = 1;
                        darkSquarePiece1Dragable[i] = new Dragable(darkSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        darkSquarePiece1Dragable[i].setGridPos(posX, posY);
                        darkSquarePiece1Dragable[i].setBounds(220+(posX*30),110+(posY*30),30,30);
                        darkSquarePiece1Dragable[i].makeDraggable();
                        darkSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(darkSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //1,2
                        i = 2;posX = 1;posY = 2;type = 0;player = true;index = 0;
                        lightSquarePiece1Dragable[i] = new Dragable(lightSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        lightSquarePiece1Dragable[i].setGridPos(posX, posY);
                        lightSquarePiece1Dragable[i].setBounds(220+(posX*30),110+(posY*30),30,30);
                        lightSquarePiece1Dragable[i].makeDraggable();
                        lightSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(lightSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index );

                        //1,3
                        i = 3;posX = 1;posY = 3;type = 0;player = false;
                        darkSquarePiece1Dragable[i] = new Dragable(darkSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        darkSquarePiece1Dragable[i].setGridPos(posX, posY);
                        darkSquarePiece1Dragable[i].setBounds(220+(posX*30),110+(posY*30),30,30);
                        darkSquarePiece1Dragable[i].makeDraggable();
                        darkSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(darkSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //2,0
                        i = 3;posX = 2;posY = 0;type = 0;player = true;
                        lightSquarePiece1Dragable[i] = new Dragable(lightSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        lightSquarePiece1Dragable[i].setGridPos(posX, posY);
                        lightSquarePiece1Dragable[i].setBounds(220+(posX*30),110+(posY*30),30,30);
                        lightSquarePiece1Dragable[i].makeDraggable();
                        lightSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(lightSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //2,2
                        i = 4;posX = 2;posY = 2;type = 0;player = false;
                        darkSquarePiece1Dragable[i] = new Dragable(darkSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        darkSquarePiece1Dragable[i].setGridPos(posX, posY);
                        darkSquarePiece1Dragable[i].setBounds(220+(posX*30),110+(posY*30),30,30);
                        darkSquarePiece1Dragable[i].makeDraggable();
                        darkSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(darkSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //2,3
                        i = 5;posX = 2;posY = 3;type = 0;player = false;
                        darkSquarePiece1Dragable[i] = new Dragable(darkSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        darkSquarePiece1Dragable[i].setGridPos(posX, posY);
                        darkSquarePiece1Dragable[i].setBounds(220+(posX*30),110+(posY*30),30,30);
                        darkSquarePiece1Dragable[i].makeDraggable();
                        darkSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(darkSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //3,1
                        i = 4;posX = 3;posY = 1;type = 0;player = true;
                        lightSquarePiece1Dragable[i] = new Dragable(lightSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        lightSquarePiece1Dragable[i].setGridPos(posX, posY);
                        lightSquarePiece1Dragable[i].setBounds(220+(posX*30),110+(posY*30),30,30);
                        lightSquarePiece1Dragable[i].makeDraggable();
                        lightSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(lightSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index );

                        //3,2
                        i = 5;posX = 3;posY = 2;type = 0;player = true;
                        lightSquarePiece1Dragable[i] = new Dragable(lightSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        lightSquarePiece1Dragable[i].setGridPos(posX, posY);
                        lightSquarePiece1Dragable[i].setBounds(220+(posX*30),110+(posY*30),30,30);
                        lightSquarePiece1Dragable[i].makeDraggable();
                        lightSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(lightSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);

                        //3,3
                        i = 6;posX = 3;posY = 3;type = 0;player = true;
                        lightSquarePiece1Dragable[i] = new Dragable(lightSquarePiece1.getGamePiece(),index ,challengeScreen, challengeScreen.getGameBoard(), bsize, player, type,app);
                        lightSquarePiece1Dragable[i].setGridPos(posX, posY);
                        lightSquarePiece1Dragable[i].setBounds(220+(posX*30),110+(posY*30),30,30);
                        lightSquarePiece1Dragable[i].makeDraggable();
                        lightSquarePiece1Dragable[i].toggleInPlay();
                        challengeScreen.stage.addActor(lightSquarePiece1Dragable[i]);
                        challengeScreen.getGameBoard().setSquare(posX,posY,player,type,index);


                        ((Game) Gdx.app.getApplicationListener()).setScreen(challengeScreen);

                        break;
                    case 3:
                        //challenge 3
                        break;
                    case 4:
                        //challenge 4
                        break;
                    case 5:
                        //challenge 5
                        break;
                    case 6:
                        //challenge 6
                        break;
                    default:
                        break;

                }
            }
        });

        textButtons[1].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {

                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(app));

            }
        });

        textButtons[2].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                challengeSelect = 1;


            }
        });

        textButtons[3].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                challengeSelect = 2;


            }
        });

        textButtons[4].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                //challengeSelect = 3 ;


            }
        });

        textButtons[5].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                //challengeSelect = 4;



            }
        });

        textButtons[6].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
               // challengeSelect = 5;



            }
        });

        textButtons[7].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                //challengeSelect = 6;



            }
        });
    }

    @Override
    public void show()
    {
    }

    public void update(float delta)
    {
        app.camera.update();
        stage.act(delta);
    }

    @Override
    public void render(float delta)
    {
        update(delta);
        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();

    }



    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {    }

    @Override
    public void resume() {  }

    @Override
    public void hide() {
        stage.clear();
    }

    @Override
    public void dispose()
    {
        stage.dispose();
        //TODO: add dispose methods for Grid and GamePiece
    }
}