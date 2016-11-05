package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameLogic.Dragable;
import com.mygdx.game.GamePieces.BlackCirclePiece;
import com.mygdx.game.GamePieces.BlackOctogonPiece;
import com.mygdx.game.GamePieces.BlackSquarePiece;
import com.mygdx.game.GamePieces.WhiteCirclePiece;
import com.mygdx.game.GamePieces.WhiteOctogonPiece;
import com.mygdx.game.GamePieces.WhiteSquarePiece;
import com.mygdx.game.Images.BackgroundImage;
import com.mygdx.game.Images.Board3x3;

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

        initBoardItems();
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
        int userSelectedSize = 3;
        //ArrayList<Integer> arr=new ArrayList<Integer>(userSelectedSize);

        //GridSquares gridSquares = GridSquares.getInstance();
        BackgroundImage backgroundImage = BackgroundImage.getInstance();
        stage.addActor(backgroundImage.getImage());
        //stage.addActor(gridSquares.getImage());

        /*
        GridSquares[][] gridSquares = new GridSquares[userSelectedSize][userSelectedSize];

        for(int i=0;i<=gridSquares.length;i++)
        {
            for(int j=0;i<=gridSquares.length;i++)
            {
                gridSquares[i][j]= new GridSquares();
                //gridSquares[i][j].setBounds( (10 + i + 10 + i, 10 + i + 10 + j, 50, 50);
                gridSquares[i][j].setBounds(10 + i, 10 + i, 50,50);

                stage.addActor(gridSquares[i][j].getImage());
            }
        }
        */
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

        BlackCirclePiece redCirclePiece = BlackCirclePiece.getInstance();
        Dragable[] blackCircleDragable = new Dragable[20];

        BlackOctogonPiece blackOctogonPiece = BlackOctogonPiece.getInstance();
        Dragable[] blackOctogonDragable = new Dragable[20];

        BlackSquarePiece blackSquarePiece = BlackSquarePiece.getInstance();
        Dragable[] blackSquareDragable = new Dragable[20];

        WhiteCirclePiece whiteCirclePiece = WhiteCirclePiece.getInstance();
        Dragable[] whiteCircleDragable = new Dragable[20];

        WhiteOctogonPiece whiteOctogonPiece = WhiteOctogonPiece.getInstance();
        Dragable[] whiteOctogonDragable = new Dragable[20];

        WhiteSquarePiece whiteSquarePiece = WhiteSquarePiece.getInstance();
        Dragable[] whiteSquareDragable = new Dragable[20];


        for(int i=0; i<10; i++)
        {
            blackCircleDragable[i] = new Dragable(redCirclePiece.getGamePiece(),this);
            blackOctogonDragable[i] = new Dragable(blackOctogonPiece.getGamePiece(),this);
            blackSquareDragable[i] = new Dragable(blackSquarePiece.getGamePiece(),this);

            whiteCircleDragable[i] = new Dragable(whiteCirclePiece.getGamePiece(),this);
            whiteOctogonDragable[i] = new Dragable(whiteOctogonPiece.getGamePiece(),this);
            whiteSquareDragable[i] = new Dragable(whiteSquarePiece.getGamePiece(),this);

            blackCircleDragable[i].setBounds(20,20,40,40);
            blackCircleDragable[i].makeDraggable();

            blackOctogonDragable[i].setBounds(20,70,40,40);
            blackOctogonDragable[i].makeDraggable();

            blackSquareDragable[i].setBounds(20,120,40,40);
            blackSquareDragable[i].makeDraggable();

            whiteCircleDragable[i].setBounds(80,20,40,40);
            whiteCircleDragable[i].makeDraggable();

            whiteOctogonDragable[i].setBounds(80,70,40,40);
            whiteOctogonDragable[i].makeDraggable();

            whiteSquareDragable[i].setBounds(80,120,40,40);
            whiteSquareDragable[i].makeDraggable();


            stage.addActor(blackCircleDragable[i]);
            stage.addActor(blackOctogonDragable[i]);
            stage.addActor(blackSquareDragable[i]);
            stage.addActor(whiteCircleDragable[i]);
            stage.addActor(whiteOctogonDragable[i]);
            stage.addActor(whiteSquareDragable[i]);
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

    private void initBoardItems()
    {
        BackgroundImage backgroundImage = BackgroundImage.getInstance();

        Board3x3 board3x3= new Board3x3();
        board3x3.getImage();

        stage.addActor(backgroundImage.getImage());
        stage.addActor(board3x3.getImage());

    }

}

