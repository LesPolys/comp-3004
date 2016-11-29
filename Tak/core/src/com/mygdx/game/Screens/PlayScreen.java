package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameLogic.Dragable;
import com.mygdx.game.GameLogic.GameBoard;
import com.mygdx.game.GameLogic.GamePiece;
import com.mygdx.game.GamePieces.DarkCirclePiece1;
import com.mygdx.game.GamePieces.DarkSquarePiece1;
import com.mygdx.game.GamePieces.DarkTrianglePiece1;
import com.mygdx.game.GamePieces.LightCirclePiece1;
import com.mygdx.game.GamePieces.LightSquarePiece1;
import com.mygdx.game.GamePieces.LightTriangle1;
import com.mygdx.game.Images.BackgroundImage;
import com.mygdx.game.Images.Board3x3;
import com.mygdx.game.Images.Board4x4;
import com.mygdx.game.Images.Board5x5;
import com.mygdx.game.Images.Board6x6;
import com.mygdx.game.Images.Board7x7;
import com.mygdx.game.Images.Board8x8;
import com.mygdx.game.StaticVariables;
import com.mygdx.game.Tak;


import java.util.ArrayDeque;
import java.util.ArrayList;


public class PlayScreen implements Screen
{
    private final int PIECES_PER_SIZE = 4;
    private Tak app;

    public Stage stage;
    private GameBoard gameBoard;
    private ArrayList list;

    private PauseScreen pauseScreen;
    private StackScreen stackScreen;
    private Declerations declerations;
    private Dragable[] piecesDragable;

    DarkCirclePiece1 darkCirclePiece1 ;
    DarkSquarePiece1 darkSquarePiece1 ;
    DarkTrianglePiece1 darkTrianglePiece1;

    LightCirclePiece1 lightCirclePiece1 ;
    LightSquarePiece1 lightSquarePiece1;
    LightTriangle1 lightTriangle1Piece ;

    public PlayScreen(Tak gameApp, int boardSize)
    {
        app = gameApp;
        Viewport viewport = new FillViewport(StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT, gameApp.camera);
        stage = new Stage(viewport, app.batch);

        BackgroundImage backgroundImage = BackgroundImage.getInstance();
        stage.addActor(backgroundImage.getImage());

        initBoardItems(boardSize);
        //startGame();
        initGamePieces(boardSize);

        //gameBoard = new GameBoard();

        //make stage handle inputs
        Gdx.input.setInputProcessor(stage);

        //initialize the pause screen
        pauseScreen = new PauseScreen(app,stage);
        pauseScreen.returnToMainMenu();
        pauseScreen.returnToGame();

        stackScreen = new StackScreen(app,stage);
        stackScreen.returnToGame();
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

    }

    public void stackMenu(GameBoard board, int x, int y,Dragable p){
        stage.cancelTouchFocus();
        stackScreen.selectionOptions(board,x,y,p);
    }
    public void squashPiece(int i){
        Dragable tmpDragable =   piecesDragable[i];
        int bSize = tmpDragable.getDimensions();
        piecesDragable[i].remove();
        piecesDragable[i].clear();
        if (tmpDragable.getPlayer()) {
            piecesDragable[i]= new Dragable(lightSquarePiece1.getGamePiece(), i, this, gameBoard, bSize, true, 0, app);
            piecesDragable[i].setSize(30,30);
            piecesDragable[i].setPosition(tmpDragable.getX(),tmpDragable.getY());
            piecesDragable[i].makeDraggable();

        }else{
            piecesDragable[i] = new Dragable(darkSquarePiece1.getGamePiece(), i, this, gameBoard, bSize, false, 0, app);
            piecesDragable[i].setSize(30,30);
            piecesDragable[i].setPosition(tmpDragable.getX(),tmpDragable.getY());
            piecesDragable[i].makeDraggable();
        }

    }

    public Dragable getPiece(int i){
        return piecesDragable[i];
    }


    private void initGamePieces(int bSize)
    {
        gameBoard = new GameBoard(bSize, app);
        int PIECES = 36;
        int CAP_PIECES = 1;
        int TOTAL_PIECES = 37;
        int piecesType = 0;
        int counter = 1;

        switch(bSize){
            case 3:
                PIECES = 10;
                CAP_PIECES = 0;
                TOTAL_PIECES = (PIECES*4)+(CAP_PIECES*2);
                System.out.println(TOTAL_PIECES);
                break;
            case 4:
                PIECES = 15;
                CAP_PIECES = 0;
                TOTAL_PIECES = (PIECES*4)+(CAP_PIECES*2);
                System.out.println(TOTAL_PIECES);
                break;
            case 5:
                PIECES = 21;
                CAP_PIECES = 1;
                TOTAL_PIECES = (PIECES*4)+(CAP_PIECES*2);
                System.out.println(TOTAL_PIECES);
                break;
            case 6:
                PIECES = 30;
                CAP_PIECES = 1;
                TOTAL_PIECES = (PIECES*4)+(CAP_PIECES*2);
                System.out.println(TOTAL_PIECES);
                break;
            case 7:
                PIECES = 40;
                CAP_PIECES = 2;
                TOTAL_PIECES = (PIECES*4)+(CAP_PIECES*2);
                System.out.println(TOTAL_PIECES);
                break;
            case 8:
                PIECES = 50;
                CAP_PIECES = 2;
                TOTAL_PIECES = (PIECES*4)+(CAP_PIECES*2);
                System.out.println(TOTAL_PIECES);
                break;
        }

         darkCirclePiece1 = new DarkCirclePiece1();
         darkSquarePiece1 = new DarkSquarePiece1();
         darkTrianglePiece1 = new DarkTrianglePiece1();

        lightCirclePiece1 = new LightCirclePiece1();
        lightSquarePiece1 = new LightSquarePiece1();
        lightTriangle1Piece = new LightTriangle1();

         piecesDragable = new Dragable[TOTAL_PIECES];

        for(int i=0; i<TOTAL_PIECES; i++) {

            if(i  == TOTAL_PIECES /2){
                //System.out.println("HALFWAY: " );
                piecesType = 0;
            }

            //System.out.println("Counter: " + counter);
            if (i < TOTAL_PIECES / 2 ) { //white pieces
                //System.out.println("White: " );
                if (piecesType == 0) { //road
                    piecesDragable[i] = new Dragable(lightSquarePiece1.getGamePiece(), i, this, gameBoard, bSize, true, 0, app);
                    piecesDragable[i].setBounds(50, 90, 30, 30);
                    piecesDragable[i].makeDraggable();
                    stage.addActor(piecesDragable[i]);


                } else if (piecesType == 1) { //wall
                    piecesDragable[i] = new Dragable(lightTriangle1Piece.getGamePiece(), i, this, gameBoard, bSize, true, 1, app);
                    piecesDragable[i].setBounds(50, 125, 30, 30);
                    piecesDragable[i].makeDraggable();
                    stage.addActor(piecesDragable[i]);

                } else if (piecesType == 2) { //cap
                    piecesDragable[i] = new Dragable(lightCirclePiece1.getGamePiece(), i, this, gameBoard, bSize, true, 2, app);
                    piecesDragable[i].setBounds(50, 20, 30, 30);
                    piecesDragable[i].makeDraggable();
                    stage.addActor(piecesDragable[i]);


                }

            } else { //black pieces
               // System.out.println("Black: " );
                if (piecesType == 0) { //road
                    piecesDragable[i] = new Dragable(darkSquarePiece1.getGamePiece(), i, this, gameBoard, bSize, false, 0, app);
                    piecesDragable[i].setBounds(20, 90, 30, 30);
                    piecesDragable[i].makeDraggable();
                    stage.addActor(piecesDragable[i]);


                } else if (piecesType == 1) { //wall
                    piecesDragable[i] = new Dragable(darkTrianglePiece1.getGamePiece(), i, this, gameBoard, bSize, false, 1, app);
                    piecesDragable[i].setBounds(20, 125, 30, 30);
                    piecesDragable[i].makeDraggable();
                    stage.addActor(piecesDragable[i]);

                } else if (piecesType == 2) {//cap
                    piecesDragable[i] = new Dragable(darkCirclePiece1.getGamePiece(), i, this, gameBoard, bSize, false, 2, app);
                    piecesDragable[i].setBounds(20, 20, 30, 30);
                    piecesDragable[i].makeDraggable();
                    stage.addActor(piecesDragable[i]);

                }

            }


            if(counter == PIECES) {
                piecesType++;
                counter = 1;
                //System.out.println("NEXT PIECE TYPE ");
            }else if(piecesType == 2 && counter == CAP_PIECES){
                piecesType = 0;
                counter = 1;
                //System.out.println("PIECE TYPE ROLLBACK ");
            }else {
                counter++;
            }

        }
    }

    private void initBoardItems(int bSize)
    {
        switch(bSize){
            case 3:
                Board3x3 board3x3= new Board3x3();
                board3x3.getImage();
                stage.addActor(board3x3.getImage());
                break;
            case 4:
                Board4x4 board4x4= new Board4x4();
                board4x4.getImage();
                stage.addActor(board4x4.getImage());
                break;
            case 5:
                Board5x5 board5x5= new Board5x5();
                board5x5.getImage();
                stage.addActor(board5x5.getImage());
                break;
            case 6:
                Board6x6 board6x6= new Board6x6();
                board6x6.getImage();
                stage.addActor(board6x6.getImage());
                break;
            case 7:
                Board7x7 board7x7= new Board7x7();
                board7x7.getImage();
                stage.addActor(board7x7.getImage());
                break;
            case 8:
                Board8x8 board8x8= new Board8x8();
                board8x8.getImage();
                stage.addActor(board8x8.getImage());
                break;
        }

    }

    public void setGameBoard(ArrayDeque<GamePiece> newboard[][])
    {
        gameBoard.setboard(newboard);
    }

    public GameBoard getGameBoard()
    {
        return gameBoard;
    }

}

