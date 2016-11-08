package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameLogic.Dragable;
import com.mygdx.game.GameLogic.GameBoard;
import com.mygdx.game.GameLogic.StartGame;
import com.mygdx.game.GamePieces.DarkCirclePiece;
import com.mygdx.game.GamePieces.DarkSquarePiece1;
import com.mygdx.game.GamePieces.DarkSquarePiece2;
import com.mygdx.game.GamePieces.DarkTrianglePiece1;
import com.mygdx.game.GamePieces.DarkTrianglePiece2;
import com.mygdx.game.GamePieces.LightCirclePiece;
import com.mygdx.game.GamePieces.LightSquarePiece1;
import com.mygdx.game.GamePieces.LightSquarePiece2;
import com.mygdx.game.GamePieces.LightTriangle1;
import com.mygdx.game.GamePieces.LightTriangle2;
import com.mygdx.game.Images.BackgroundImage;
import com.mygdx.game.Images.Board3x3;
import com.mygdx.game.Images.Board4x4;
import com.mygdx.game.Images.Board5x5;
import com.mygdx.game.Images.Board6x6;
import com.mygdx.game.StaticVariables;
import com.mygdx.game.Tak;

import java.util.ArrayList;


public class PlayScreen implements Screen
{
    private final int PIECES_PER_SIZE = 4;
    private Tak app;

    public Stage stage;
    private GameBoard gameBoard;
    private ArrayList list;

    public PlayScreen(Tak gameApp)
    {
        app = gameApp;
        Viewport viewport = new FillViewport(StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT, gameApp.camera);
        stage = new Stage(viewport, app.batch);

        BackgroundImage backgroundImage = BackgroundImage.getInstance();
        stage.addActor(backgroundImage.getImage());

        initBoardItems();
        startGame();
        initGamePieces();

        //gameBoard = new GameBoard();

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
        int userSelectedSize = 3;
        //ArrayList<Integer> arr=new ArrayList<Integer>(userSelectedSize);

        //GridSquares gridSquares = GridSquares.getInstance();
        BackgroundImage backgroundImage = BackgroundImage.getInstance();
        stage.addActor(backgroundImage.getImage());
        //stage.addActor(gridSquares.getImage());


        //input ebola here
    }

    private void initGamePieces()
    {
        gameBoard = new GameBoard();

        int PIECES = 36;

        DarkCirclePiece darkCirclePiece = new DarkCirclePiece();
        DarkSquarePiece1 darkSquarePiece1 = new DarkSquarePiece1();
        DarkSquarePiece2 darkSquarePiece2 = new DarkSquarePiece2();
        DarkTrianglePiece1 darkTrianglePiece1 = new DarkTrianglePiece1();
        DarkTrianglePiece2 darkTrianglePiece2 = new DarkTrianglePiece2();

        LightCirclePiece lightCirclePiece = new LightCirclePiece();
        LightSquarePiece1 lightSquarePiece1 = new LightSquarePiece1();
        LightSquarePiece2 lightSquarePiece2 = new LightSquarePiece2();
        LightTriangle1 lightTriangle1Piece = new LightTriangle1();
        LightTriangle2 lightTriangle2Piece = new LightTriangle2();

        Dragable[] darkCirclePieceDragable = new Dragable[PIECES];
        Dragable[] darkSquarePiece1Dragable = new Dragable[PIECES];
        Dragable[] darkSquarePiece2Dragable = new Dragable[PIECES];
        Dragable[] darkTrianglePiece1Dragable = new Dragable[PIECES];
        Dragable[] darkTrianglePiece2Dragable = new Dragable[PIECES];

        Dragable[] lightCirclePieceDragable = new Dragable[PIECES];
        Dragable[] lightSquarePiece1Dragable = new Dragable[PIECES];
        Dragable[] lightSquarePiece2Dragable = new Dragable[PIECES];
        Dragable[] lightTriangle1PieceDragable = new Dragable[PIECES];
        Dragable[] lightTriangle2PieceDragable = new Dragable[PIECES];

        for(int i=0; i<PIECES; i++)
        {
            darkCirclePieceDragable[i] = new Dragable(darkCirclePiece.getGamePiece(),this, gameBoard);
            darkSquarePiece1Dragable[i] = new Dragable(darkSquarePiece1.getGamePiece(),this, gameBoard);
            darkSquarePiece2Dragable[i] = new Dragable(darkSquarePiece2.getGamePiece(),this, gameBoard);
            darkTrianglePiece1Dragable[i] = new Dragable(darkTrianglePiece1.getGamePiece(),this, gameBoard);
            darkTrianglePiece2Dragable[i] = new Dragable(darkTrianglePiece2.getGamePiece(),this, gameBoard);

            lightCirclePieceDragable[i] = new Dragable(lightCirclePiece.getGamePiece(),this, gameBoard);
            lightSquarePiece1Dragable[i] = new Dragable(lightSquarePiece1.getGamePiece(),this, gameBoard);
            lightSquarePiece2Dragable[i] = new Dragable(lightSquarePiece2.getGamePiece(),this, gameBoard);
            lightTriangle1PieceDragable[i] = new Dragable(lightTriangle1Piece.getGamePiece(),this, gameBoard);
            lightTriangle2PieceDragable[i] = new Dragable(lightTriangle2Piece.getGamePiece(),this, gameBoard);

            darkCirclePieceDragable[i].setBounds(20,20,30,30);
            darkSquarePiece1Dragable[i].setBounds(20,55,30,30);
            darkSquarePiece2Dragable[i].setBounds(20,90,30,30);
            darkTrianglePiece1Dragable[i].setBounds(20,125,30,30);
            darkTrianglePiece2Dragable[i].setBounds(20,155,30,30);

            lightCirclePieceDragable[i].setBounds(50,20,30,30);
            lightSquarePiece1Dragable[i].setBounds(50,55,30,30);
            lightSquarePiece2Dragable[i].setBounds(50,90,30,30);
            lightTriangle1PieceDragable[i].setBounds(50,125,30,30);
            lightTriangle2PieceDragable[i].setBounds(50,155,30,30);


            darkCirclePieceDragable[i].makeDraggable();
            darkSquarePiece1Dragable[i].makeDraggable();
            darkSquarePiece2Dragable[i].makeDraggable();
            darkTrianglePiece1Dragable[i].makeDraggable();
            darkTrianglePiece2Dragable[i].makeDraggable();

            lightCirclePieceDragable[i].makeDraggable();
            lightSquarePiece1Dragable[i].makeDraggable();
            lightSquarePiece2Dragable[i].makeDraggable();
            lightTriangle1PieceDragable[i].makeDraggable();
            lightTriangle2PieceDragable[i].makeDraggable();

            stage.addActor(darkCirclePieceDragable[i]);
            stage.addActor(darkSquarePiece1Dragable[i]);
            stage.addActor(darkSquarePiece2Dragable[i]);
            stage.addActor(darkTrianglePiece1Dragable[i]);
            stage.addActor(darkTrianglePiece2Dragable[i]);

            stage.addActor(lightCirclePieceDragable[i]);
            stage.addActor(lightSquarePiece1Dragable[i]);
            stage.addActor(lightSquarePiece2Dragable[i]);
            stage.addActor(lightTriangle1PieceDragable[i]);
            stage.addActor(lightTriangle2PieceDragable[i]);
        }


    }

    private void initBoardItems()
    {
        Board5x5 board5x5= new Board5x5();
        board5x5.getImage();
        stage.addActor(board5x5.getImage());
    }

    public void startGame()
    {
        StartGame startGame = new StartGame();
        startGame.start();
    }

}

