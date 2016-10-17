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

/**
 * Created by Logan on 10/16/2016.
 */

public class Challenge {
    private SampleGame app;
    private Stage stage;


    private TextButton.TextButtonStyle textButtonStyle[];
    private TextButton textButtons[];

    private Declerations declerations;

    public Challenge(SampleGame gameApp, Stage appStage)
    {
        app = gameApp;
        stage = appStage;
        declerations = new Declerations();
        declerations.initChallengeScreen();


        //select box back button and play button as well as a background and title
        //select box contains list of challenges to select


    }


    public void returnToMainMenu() //back button
    {

    }

    public void playGame() //launch the game
    {

    }

}
