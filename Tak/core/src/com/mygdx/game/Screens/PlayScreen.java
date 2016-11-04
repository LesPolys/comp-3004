package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameLogic.Dragable;
import com.mygdx.game.GamePieces.BlackGamePiece;
import com.mygdx.game.Images.BackgroundImage;
import com.mygdx.game.Images.GridSquares;
import com.mygdx.game.StaticVariables;
import com.mygdx.game.Tak;


public class PlayScreen implements Screen
{
    private final int PIECES_PER_SIZE = 4;
    private Tak app;

    public Stage stage;

    public PlayScreen(Tak gameApp)
    {
        app = gameApp;
        Viewport viewport = new FillViewport(StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT, gameApp.camera);
        stage = new Stage(viewport, app.batch);


        initGameImages();
        initGamePieces();


        //make stage handle inputs
        Gdx.input.setInputProcessor(stage);

    }
     @Override
    public void show(){}

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
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause(){}

    @Override
    public void resume(){ }

    @Override
    public void hide() {
        stage.clear();
    }

    @Override
    public void dispose()
    {
        stage.dispose();
        //skin.dispose();
    }

    private void initGameImages()
    {
        GridSquares gridSquares = GridSquares.getInstance();
        BackgroundImage backgroundImage = BackgroundImage.getInstance();

        stage.addActor(backgroundImage.getImage());
        stage.addActor(gridSquares.getImage());
    }

    private void initGamePieces()
    {
        /*
        BlackGamePiece blackGamePiece = BlackGamePiece.getInstance();
        //stage.addActor(blackGamePiece.getBlackGamePiece());
        Dragable pieceBlue = new Dragable(blackGamePiece.getGamePiece(),this);
        pieceBlue.setBounds(300,60,50,50);
        pieceBlue.makeDraggable();
        stage.addActor(pieceBlue);
        */


        /*
        BlackGamePiece blackGamePiece = BlackGamePiece.getInstance();
        Dragable pieceBlue = new Dragable(blackGamePiece.getGamePiece(),this);
        */

        BlackGamePiece blackGamePiece = BlackGamePiece.getInstance();
        Dragable[] pieceBlue = new Dragable[20];

        for(int i=0; i<10; i++)
        {
            pieceBlue[i] = new Dragable(blackGamePiece.getGamePiece(),this);
            pieceBlue[i].setBounds(300,60,50,50);
            pieceBlue[i].makeDraggable();
            stage.addActor(pieceBlue[i]);
        }

        /*
        for(int i=0; i<19; i++)
        {
            pieceRed[i] = new GamePiece(skin, "piece_red", this);
            pieceRed[i].setBounds(0,0,50,50);
            pieceRed[i].initForNewGame();
            pieceRed[i].makeDraggable();
            pieceRed[i].setCurrentPlayer("Red");
            stage.addActor(pieceRed[i]);
        }
        */
    }

}

