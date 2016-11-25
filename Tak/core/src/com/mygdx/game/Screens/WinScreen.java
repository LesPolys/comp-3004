package com.mygdx.game.Screens;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.mygdx.game.StaticVariables;
import com.mygdx.game.Tak;
import com.mygdx.game.Screens.Declerations;

//import com.seg3125.project.GameApp;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.awt.ScrollPane;

public class WinScreen implements Screen
{
    private Tak app;
    public Stage stage;

    private Image images[];
    private TextButton textButtons[];
    private Declerations declerations;

    public WinScreen(Tak gameApp,boolean winner)
    {
        app = gameApp;
        Viewport viewport = new FillViewport(StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT, gameApp.camera);
        stage = new Stage(viewport, app.batch);
        Gdx.input.setInputProcessor(stage);

        declerations = new Declerations();
        declerations.initWinScreen();

        textButtons = new TextButton[4];


        images = new Image[4];

        for(int i=0; i<4; i++)
        {
            images[i] = new Image();
        }

        //draws the background, how to play instructions and title
        //Background , Main Title, Versus Title, Challenge Title


        images[0] = new Image(declerations.winSkin[0],"background");
        images[0].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[0].setBounds(0,0, StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT);
        stage.addActor(images[0]);

        images[1] = new Image(declerations.winSkin[1],"winTitle");//TITLE
        images[1].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[1].setBounds(25, (StaticVariables.V_HEIGHT/2)-100, 74 ,200);
        stage.addActor( images[1]);

        images[2] = new Image(declerations.winSkin[2],"whiteStatus");//WHITEWIN
        images[2].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[2].setBounds(105,(StaticVariables.V_HEIGHT/2)-100,50,200);

        images[3] = new Image(declerations.winSkin[3],"blackStatus");//BLACKWIN
        images[3].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[3].setBounds(105,(StaticVariables.V_HEIGHT/2)-100,50,200);

        if(winner){
            stage.addActor( images[2]);
        }else {
            stage.addActor( images[3]);
        }



        //for the buttons
        declerations.winMenutextButtonStyles[0].up = declerations.winSkin[4].getDrawable("backButtonUnpressed"); //VS
        declerations.winMenutextButtonStyles[0].down = declerations.winSkin[5].getDrawable("backButtonPresed");
        declerations.winMenutextButtonStyles[0].checked = declerations.winSkin[4].getDrawable("backButtonUnpressed");


            textButtons[0] = new TextButton("", declerations.winMenutextButtonStyles[0]);
            textButtons[0].setBounds((250 ),(StaticVariables.V_WIDTH/2)-210,50,200);
            stage.addActor(textButtons[0]);


        //button listeners
        textButtons[0].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {

                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(app));

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

    public Stage getStage()
    {
        return stage;
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
