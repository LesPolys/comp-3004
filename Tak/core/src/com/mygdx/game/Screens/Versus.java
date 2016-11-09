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
import com.mygdx.game.StaticVariables;
import com.mygdx.game.Tak;
import com.mygdx.game.Screens.Declerations;

/**
 * Created by Logan on 10/30/2016.
 */

public class Versus implements Screen
{
    private Tak app;
    public Stage stage;

    private Image images[];
    private TextButton textButtons[];
    private Declerations declerations;
    private int stageSize;

    public Versus(Tak gameApp)
    {
        app = gameApp;
        Viewport viewport = new FillViewport(StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT, gameApp.camera);
        stage = new Stage(viewport, app.batch);
        Gdx.input.setInputProcessor(stage);


        declerations = new Declerations();
        declerations.initVersusScreen();

        textButtons = new TextButton[10];

        stageSize = 3;

        images = new Image[4];

        for(int i=0; i<4; i++)
        {
            images[i] = new Image();
        }

        //draws the background, how to play instructions and title
        //Background , Main Title, Versus Title, Challenge Title


        images[0] = new Image(declerations.versusSkin[17],"background");
        images[0].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[0].setBounds(0,0, StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT);
        stage.addActor(images[0]);


        images[1] = new Image(declerations.versusSkin[18],"versusTitle");
        images[1].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[1].setBounds(25, (StaticVariables.V_HEIGHT/2)-100, 74 ,200);
        stage.addActor(images[1]);

        images[2] = new Image(declerations.versusSkin[19],"versusMessage");
        images[2].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[2].setBounds(105,(StaticVariables.V_HEIGHT/2)-100,50,200);
        stage.addActor(images[2]);


        declerations.versusMenutextButtonStyles[0].up = declerations.versusSkin[13].getDrawable("playUnpressed");
        declerations.versusMenutextButtonStyles[0].down = declerations.versusSkin[14].getDrawable("playButtonPresed");
        declerations.versusMenutextButtonStyles[0].checked = declerations.versusSkin[14].getDrawable("playButtonPresed");

        declerations.versusMenutextButtonStyles[1].up = declerations.versusSkin[15].getDrawable("backButtonUnpressed");
        declerations.versusMenutextButtonStyles[1].down = declerations.versusSkin[16].getDrawable("backButtonPresed");
        declerations.versusMenutextButtonStyles[1].checked = declerations.versusSkin[16].getDrawable("backButtonPresed");

        declerations.versusMenutextButtonStyles[2].up = declerations.versusSkin[1].getDrawable("3x3Unpressed");// 3x3
        declerations.versusMenutextButtonStyles[2].down = declerations.versusSkin[2].getDrawable("3x3Pressed");
        declerations.versusMenutextButtonStyles[2].checked = declerations.versusSkin[2].getDrawable("3x3Pressed");

        declerations.versusMenutextButtonStyles[3].up = declerations.versusSkin[3].getDrawable("4x4Unpressed");//x4
        declerations.versusMenutextButtonStyles[3].down = declerations.versusSkin[4].getDrawable("4x4Pressed");
        declerations.versusMenutextButtonStyles[3].checked = declerations.versusSkin[4].getDrawable("4x4Pressed");

        declerations.versusMenutextButtonStyles[4].up = declerations.versusSkin[5].getDrawable("5x5Unpressed");//5x5
        declerations.versusMenutextButtonStyles[4].down = declerations.versusSkin[6].getDrawable("5x5Pressed");
        declerations.versusMenutextButtonStyles[4].checked = declerations.versusSkin[6].getDrawable("5x5Pressed");

        declerations.versusMenutextButtonStyles[5].up = declerations.versusSkin[7].getDrawable("6x6Unpressed");//6x6
        declerations.versusMenutextButtonStyles[5].down = declerations.versusSkin[8].getDrawable("6x6Pressed");
        declerations.versusMenutextButtonStyles[5].checked = declerations.versusSkin[8].getDrawable("6x6Pressed");

        declerations.versusMenutextButtonStyles[6].up = declerations.versusSkin[9].getDrawable("7x7Unpressed");//7x7
        declerations.versusMenutextButtonStyles[6].down = declerations.versusSkin[10].getDrawable("7x7Pressed");
        declerations.versusMenutextButtonStyles[6].checked = declerations.versusSkin[10].getDrawable("7x7Pressed");

        declerations.versusMenutextButtonStyles[7].up = declerations.versusSkin[11].getDrawable("8x8Unpressed");//8x8
        declerations.versusMenutextButtonStyles[7].down = declerations.versusSkin[12].getDrawable("8x8Pressed");
        declerations.versusMenutextButtonStyles[7].checked = declerations.versusSkin[12].getDrawable("8x8Pressed");



        for(int i=0; i < 2; i++)
        {
            int shift = 75 * i;
            textButtons[i] = new TextButton("", declerations.versusMenutextButtonStyles[i]);
            textButtons[i].setBounds((300 + shift),(StaticVariables.V_HEIGHT/2)-100,50,200);
            stage.addActor(textButtons[i]);
        }

        int counter = 2;
        for(int x=0; x < 2; x++)
        {
            for(int y=0; y < 3; y++) {
                int xshift = 75 * x ;
                int yshift = 75 * y;
                textButtons[counter] = new TextButton("", declerations.versusMenutextButtonStyles[counter]);
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
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(app,stageSize));


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
                System.out.println(stageSize);

            }
        });

        textButtons[3].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                stageSize = 4;
              //  System.out.println(stageSize);

            }
        });

        textButtons[4].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                stageSize = 5;
                //System.out.println(stageSize);

            }
        });

        textButtons[5].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                stageSize = 6;
                //System.out.println(stageSize);

            }
        });

        textButtons[6].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                stageSize = 7;
                //System.out.println(stageSize);

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