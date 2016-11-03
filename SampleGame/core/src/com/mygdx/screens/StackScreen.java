package com.mygdx.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import com.mygdx.game.SampleGame;
import com.mygdx.util.Declerations;
//import com.seg3125.project.Declerations;

//import com.seg3125.project.GameApp;

public class StackScreen
{
    private SampleGame app;
    private Stage stage;
    private Image pauseScreenImage;

    private TextButton.TextButtonStyle textButtonStyle[];
    private TextButton textButtons[];

    private Declerations declerations;

    public StackScreen(SampleGame gameApp, Stage appStage)
    {
        app = gameApp;
        stage = appStage;

        declerations = new Declerations();
        declerations.initPauseScreen();

        textButtonStyle = new TextButton.TextButtonStyle[3];
        textButtons = new TextButton[3];




    }


    public void selectionOptions()
    {


    }

    public void returnToGame()
    {

    }
}
