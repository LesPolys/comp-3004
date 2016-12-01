package com.mygdx.game.Screens;
/**
 * Created by Logan on 11/1/2016.
 */

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
import com.mygdx.game.StaticVariables;
import com.mygdx.game.Tak;
import com.mygdx.game.Screens.Declerations;



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
import com.mygdx.game.Tak;
import com.mygdx.game.Screens.Declerations;

/**
 * Created by Logan on 10/30/2016.
 */

public class VersusAI implements Screen
{
    private Tak app;
    public Stage stage;

    private Image images[];
    private TextButton textButtons[];
    private Declerations declerations;
    private int stageSize;
    private int difficulty;

    public VersusAI(Tak gameApp)
    {
        app = gameApp;
        Viewport viewport = new FillViewport(StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT, gameApp.camera);
        stage = new Stage(viewport, app.batch);
        Gdx.input.setInputProcessor(stage);


        declerations = new Declerations();
        declerations.initVersusAIScreen();

        textButtons = new TextButton[10];

        stageSize = 3;
        difficulty = 1;

        images = new Image[4];

        for(int i=0; i<4; i++)
        {
            images[i] = new Image();
        }

        //draws the background, how to play instructions and title
        //Background , Main Title, Versus Title, Challenge Title


        images[0] = new Image(declerations.versusAISkin[17],"background"); //Background
        images[0].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[0].setBounds(0,0, StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT);
        stage.addActor(images[0]);


        images[1] = new Image(declerations.versusAISkin[18],"versusAITitle");//title
        images[1].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[1].setBounds(25, (StaticVariables.V_HEIGHT/2)-100, 74 ,200);
        stage.addActor(images[1]);

        images[2] = new Image(declerations.versusAISkin[19],"versusMessage"); //message
        images[2].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[2].setBounds(105,(StaticVariables.V_HEIGHT/2)-100,50,200);
        stage.addActor(images[2]);

        images[3] = new Image(declerations.versusAISkin[20],"difficultyMessage"); //difficulty
        images[3].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[3].setBounds(290,(StaticVariables.V_HEIGHT/2)-100,50,200);
        stage.addActor(images[3]);


        declerations.versusAIMenutextButtonStyles[0].up = declerations.versusAISkin[13].getDrawable("playUnpressed");
        declerations.versusAIMenutextButtonStyles[0].down = declerations.versusAISkin[14].getDrawable("playButtonPresed");
        declerations.versusAIMenutextButtonStyles[0].checked = declerations.versusAISkin[14].getDrawable("playButtonPresed");

        declerations.versusAIMenutextButtonStyles[1].up = declerations.versusAISkin[15].getDrawable("backButtonUnpressed");
        declerations.versusAIMenutextButtonStyles[1].down = declerations.versusAISkin[16].getDrawable("backButtonPresed");
        declerations.versusAIMenutextButtonStyles[1].checked = declerations.versusAISkin[16].getDrawable("backButtonPresed");

        declerations.versusAIMenutextButtonStyles[2].up = declerations.versusAISkin[1].getDrawable("3x3Unpressed");// 3x3
        declerations.versusAIMenutextButtonStyles[2].down = declerations.versusAISkin[2].getDrawable("3x3Pressed");
        declerations.versusAIMenutextButtonStyles[2].checked = declerations.versusAISkin[2].getDrawable("3x3Pressed");

        declerations.versusAIMenutextButtonStyles[3].up = declerations.versusAISkin[3].getDrawable("4x4Unpressed");//x4
        declerations.versusAIMenutextButtonStyles[3].down = declerations.versusAISkin[4].getDrawable("4x4Pressed");
        declerations.versusAIMenutextButtonStyles[3].checked = declerations.versusAISkin[4].getDrawable("4x4Pressed");

        declerations.versusAIMenutextButtonStyles[4].up = declerations.versusAISkin[5].getDrawable("5x5Unpressed");//5x5
        declerations.versusAIMenutextButtonStyles[4].down = declerations.versusAISkin[6].getDrawable("5x5Pressed");
        declerations.versusAIMenutextButtonStyles[4].checked = declerations.versusAISkin[6].getDrawable("5x5Pressed");

        declerations.versusAIMenutextButtonStyles[5].up = declerations.versusAISkin[7].getDrawable("6x6Unpressed");//6x6
        declerations.versusAIMenutextButtonStyles[5].down = declerations.versusAISkin[8].getDrawable("6x6Pressed");
        declerations.versusAIMenutextButtonStyles[5].checked = declerations.versusAISkin[8].getDrawable("6x6Pressed");

        declerations.versusAIMenutextButtonStyles[6].up = declerations.versusAISkin[9].getDrawable("7x7Unpressed");//7x7
        declerations.versusAIMenutextButtonStyles[6].down = declerations.versusAISkin[10].getDrawable("7x7Pressed");
        declerations.versusAIMenutextButtonStyles[6].checked = declerations.versusAISkin[10].getDrawable("7x7Pressed");

        declerations.versusAIMenutextButtonStyles[7].up = declerations.versusAISkin[11].getDrawable("8x8Unpressed");//8x8
        declerations.versusAIMenutextButtonStyles[7].down = declerations.versusAISkin[12].getDrawable("8x8Pressed");
        Declerations.versusAIMenutextButtonStyles[7].checked = declerations.versusAISkin[12].getDrawable("8x8Pressed");

        declerations.versusAIMenutextButtonStyles[8].up = declerations.versusAISkin[21].getDrawable("difficultyOneUnpressed");// Difficulty 1
        declerations.versusAIMenutextButtonStyles[8].down = declerations.versusAISkin[22].getDrawable("difficultyOnePressed");
        Declerations.versusAIMenutextButtonStyles[8].checked = declerations.versusAISkin[22].getDrawable("difficultyOnePressed");

        declerations.versusAIMenutextButtonStyles[9].up = declerations.versusAISkin[23].getDrawable("difficultyTwoUnpressed");// Difficulty 2
        declerations.versusAIMenutextButtonStyles[9].down = declerations.versusAISkin[24].getDrawable("difficultyTwoPressed");
        Declerations.versusAIMenutextButtonStyles[9].checked = declerations.versusAISkin[24].getDrawable("difficultyTwoPressed");

        for(int i=0; i < 2; i++) //play and back
        {
            int shift = 75 * i;
            textButtons[i] = new TextButton("", declerations.versusAIMenutextButtonStyles[i]);
            textButtons[i].setBounds((400 + shift),(StaticVariables.V_HEIGHT/2)-100,50,200);
            stage.addActor(textButtons[i]);
        }

        int counter = 2; // board size
        for(int x=0; x < 2; x++)
        {
            for(int y=0; y < 3; y++) {
                int xshift = 75 * x ;
                int yshift = 75 * y;
                textButtons[counter] = new TextButton("", declerations.versusAIMenutextButtonStyles[counter]);
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



        textButtons[8] = new TextButton("", declerations.versusAIMenutextButtonStyles[8]);
        textButtons[8].setBounds(345, (StaticVariables.V_HEIGHT/2)-75 , 50, 50);
        stage.addActor(textButtons[8]);

        textButtons[9] = new TextButton("", declerations.versusAIMenutextButtonStyles[9]);
        textButtons[9].setBounds(345, ((StaticVariables.V_HEIGHT/2)+25), 50, 50);
        stage.addActor(textButtons[9]);

        ButtonGroup difficultyGroup = new ButtonGroup(textButtons[8],textButtons[9]);
//next set the max and min amount to be checked
        difficultyGroup.setMaxCheckCount(1);
        difficultyGroup.setMinCheckCount(1);
        difficultyGroup.setChecked("");
//it may be useful to use this method:
        difficultyGroup.setUncheckLast(true); //If true, when the maximum number of buttons are checked and an additional button is checked, the last button to be checked is unchecked so that the maximum is not exceeded.


        //button listeners
        textButtons[0].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {//play



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
                stageSize = 3;
                //System.out.println(stageSize);

            }
        });

        textButtons[3].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                stageSize = 4;

               // System.out.println(stageSize);
            }
        });

        textButtons[4].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                stageSize = 5;
               // System.out.println(stageSize);

            }
        });

        textButtons[5].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                stageSize = 6;

               // System.out.println(stageSize);
            }
        });

        textButtons[6].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                stageSize = 7;
                ///System.out.println(stageSize);

            }
        });

        textButtons[7].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                stageSize = 8;
               // System.out.println(stageSize);

            }
        });

        textButtons[8].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                difficulty = 1;
                // System.out.println(stageSize);

            }
        });

        textButtons[9].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                difficulty = 2;
                // System.out.println(stageSize);

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

    }
}