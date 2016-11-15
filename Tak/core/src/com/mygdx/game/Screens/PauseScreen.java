package com.mygdx.game.Screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import com.mygdx.game.StaticVariables;
import com.mygdx.game.Tak;
import com.mygdx.game.Screens.Declerations;
public class PauseScreen
{
    private Tak app;
    private Stage stage;
    private Image pauseScreenImage;

    private TextButton.TextButtonStyle textButtonStyle[];
    private TextButton textButtons[];

    private Declerations declerations;

    public PauseScreen()
    {
        initalize(app.getTak(), new MainMenu(app.getTak()).getStage());

    }

    public PauseScreen(Tak gameApp, Stage appStage)
    {
        initalize(gameApp,appStage);

    }

    public void initalize(Tak gameApp, Stage appStage)
    {
        app = gameApp;
        stage = appStage;

        declerations = new Declerations();
        declerations.initPauseScreen();

        textButtonStyle = new TextButton.TextButtonStyle[3];
        textButtons = new TextButton[3];

        for(int i=0; i<3; i++)
        {
            textButtonStyle[i] = new TextButton.TextButtonStyle();
        }

        pauseScreenImage = new Image(declerations.pauseSkin[5],"pauseScreen");
        pauseScreenImage.setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        pauseScreenImage.setBounds( (StaticVariables.V_WIDTH /2) - 100, (StaticVariables.V_HEIGHT/2) - 75, StaticVariables.V_WIDTH /2, StaticVariables.V_HEIGHT/2);

        textButtonStyle[0].font = declerations.font;
        textButtonStyle[0].up = declerations.pauseSkin[0].getDrawable("pauseButtonPressed");
        textButtonStyle[0].down = declerations.pauseSkin[0].getDrawable("pauseButtonPressed");
        textButtonStyle[0].checked =  declerations.pauseSkin[0].getDrawable("pauseButtonPressed");

        textButtonStyle[1].font = declerations.font;
        textButtonStyle[1].up = declerations.pauseSkin[1].getDrawable("backButtonUnpressed");
        textButtonStyle[1].down = declerations.pauseSkin[2].getDrawable("backButtonPresed");
        textButtonStyle[1].checked = declerations.pauseSkin[2].getDrawable("backButtonPresed");

        textButtonStyle[2].font = declerations.font;
        textButtonStyle[2].up = declerations.pauseSkin[3].getDrawable("quitButtonUnpressed");
        textButtonStyle[2].down = declerations.pauseSkin[4].getDrawable("quitButtonPresed");
        textButtonStyle[2].checked = declerations.pauseSkin[4].getDrawable("quitButtonPresed");




        for(int i=0; i<3; i++)
        {
            textButtons[i] = new TextButton("", textButtonStyle[i]);
        }

        textButtons[0].setBounds(10,(StaticVariables.V_HEIGHT/5*4),40,40);
        textButtons[1].setBounds(320,(StaticVariables.V_HEIGHT/2)-35,30,90);
        textButtons[2].setBounds(270,(StaticVariables.V_HEIGHT/2)-35,30,90);


        stage.addActor(textButtons[0]);




    }

    public void returnToMainMenu()
    {
        textButtons[0].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                stage.addActor(pauseScreenImage);

                //adds image t
                stage.addActor(textButtons[1]);
                stage.addActor(textButtons[2]);
            }
        });

        textButtons[2].addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(app));
            }
        });

    }

    public void returnToGame()
    {
        textButtons[1].addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                pauseScreenImage.remove();
                textButtons[1].setChecked(false);

                textButtons[1].remove();
                textButtons[2].remove();
            }
        });
    }
}
