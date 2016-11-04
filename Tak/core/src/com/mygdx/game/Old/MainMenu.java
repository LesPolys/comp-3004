package com.mygdx.game.Old;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.StaticVariables;
import com.mygdx.game.Tak;


public class MainMenu implements Screen
{
    private Tak app;
    public Stage stage;

    private Image images[];
    private TextButton textButtons[];

    public static final TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
    public static final BitmapFont font = new BitmapFont();

    public static Skin mainMenuSkins[] = new Skin[4];
    public static TextButton.TextButtonStyle mainMenutextButtonStyles[];

    public MainMenu(Tak gameApp)
    {
        app = gameApp;
        Viewport viewport = new FillViewport(StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT, gameApp.camera);
        stage = new Stage(viewport, app.batch);
        Gdx.input.setInputProcessor(stage);

       // declerations = new Declerations();
       // declerations.initMainMenu();

        textButtons = new TextButton[4];
        images = new Image[4];

        for(int i=0; i<4; i++)
        {
            images[i] = new Image();
        }

        //added this

        mainMenuSkins = new Skin[4];
        mainMenutextButtonStyles = new TextButton.TextButtonStyle[4];

        for(int i=0; i<4; i++)
        {
            mainMenuSkins[i] = new Skin();
            mainMenutextButtonStyles[i] = new TextButton.TextButtonStyle();
            mainMenutextButtonStyles[i].font = font;
        }

        mainMenuSkins[0].addRegions(buttonAtlas);
        mainMenuSkins[1].add("background", new Texture(Gdx.files.internal("mainMenu/Background2.png")));
        mainMenuSkins[2].add("title", new Texture(Gdx.files.internal("mainMenu/title.png")));
        mainMenuSkins[3].add("howToPlay", new Texture(Gdx.files.internal("mainMenu/howToPlay.png")));


        //draws the background, how to play instructions and title
        images[0] = new Image(mainMenuSkins[1],"background");
        images[0].setSize(StaticVariables.V_WIDTH,StaticVariables.V_HEIGHT);
        images[0].setBounds(0,0, StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT);
        stage.addActor(images[0]);

        images[1] = new Image(mainMenuSkins[3],"howToPlay");
        images[1].setSize(StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT);
        images[1].setBounds(0,0, StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT);

        images[2] = new Image(mainMenuSkins[2],"title");
        images[2].setSize(StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT);
        images[2].setBounds(50, (StaticVariables.V_HEIGHT/2)-95, 150 ,200);
        stage.addActor( images[2]);


        //for the buttons
        mainMenutextButtonStyles[0].up = mainMenuSkins[0].getDrawable("playButtonUnpressed");
        mainMenutextButtonStyles[0].down = mainMenuSkins[0].getDrawable("playButtonPresed");
        mainMenutextButtonStyles[0].checked = mainMenuSkins[0].getDrawable("playButtonPresed");
        mainMenutextButtonStyles[1].up = mainMenuSkins[0].getDrawable("instructionsButtonUnpressed");
        mainMenutextButtonStyles[1].down = mainMenuSkins[0].getDrawable("instructionsButtonPressed");
        mainMenutextButtonStyles[1].checked = mainMenuSkins[0].getDrawable("instructionsButtonPressed");

        mainMenutextButtonStyles[2].up = mainMenuSkins[0].getDrawable("backButtonUnpressed");
        mainMenutextButtonStyles[2].down = mainMenuSkins[0].getDrawable("backButtonPresed");
        mainMenutextButtonStyles[2].checked = mainMenuSkins[0].getDrawable("backButtonPresed");
        textButtons[2] = new TextButton("", mainMenutextButtonStyles[2]);
        textButtons[2].setBounds((400 ),35,50,205);

        for(int i=0; i<2; i++)
        {
            int shift = 75 * i;
            textButtons[i] = new TextButton("", mainMenutextButtonStyles[i]);
            textButtons[i].setBounds((275 + shift),40,50,200);
            stage.addActor(textButtons[i]);
        }


        //button listeners
        textButtons[0].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {

                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(app));

            }
        });

        textButtons[1].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {

                //remove the play and instructions buttons and only show the
                //how to play instruction image
                images[0].remove();
                textButtons[0].remove();
                textButtons[1].remove();

                stage.addActor(images[1]);
                images[2].remove();

                stage.addActor(textButtons[2]);

            }
        });

        textButtons[2].addListener(new ChangeListener()
        {

            @Override
            public void changed(ChangeEvent event, Actor actor)
            {

                //reload everything but the how to play image
                images[1].remove();
                textButtons[2].remove();

                stage.addActor(images[0]);
                stage.addActor(images[2]);
                stage.addActor(textButtons[0]);
                stage.addActor(textButtons[1]);

                //set the button states back to normal
                textButtons[1].setChecked(false);
                textButtons[2].setChecked(false);
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