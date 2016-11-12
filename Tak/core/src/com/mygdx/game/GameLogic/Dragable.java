package com.mygdx.game.GameLogic;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.Screens.PlayScreen;

//Will Clean Up and refactor, dirty like ebola

public class Dragable extends Image
{
    //stores the current player
    private Boolean currentPlayer;
    private int pieceType;
    /* pieceType
      0 = flat tile
      1 = standing tile
      2 = capstone
    */
    private PlayScreen screen;
    private int boardSize;
    private int xPos;
    private int yPos;
    private int zPos;

    /*
        newMain.set2();
		newMain.check();
		newMain.findPath();
		newMain.output();
		System.out.println(newMain.checkStartAndEnd(0,2,7, 5));

     */

    private GameBoard gameBoard;

    public Dragable(Image image, PlayScreen scrn, GameBoard newGameboard, int bSize)
    {
        super(image.getDrawable());
        screen = scrn;
        gameBoard = newGameboard;
        boardSize = bSize;

    }

    public void setCurrentPlayer(boolean newCurrentPlayer) {
        currentPlayer = newCurrentPlayer;
    }
    public boolean getCurrentPlayer() {return currentPlayer;}

    public GameBoard getGameBoard() {return gameBoard;}

    public void makeDraggable()
    {
        final Dragable piece = this;
        final Vector2 origPos = new Vector2();
        final DragAndDrop dnd = new DragAndDrop();

        dnd.addSource(new DragAndDrop.Source(piece)
        {
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer)
            {

                origPos.set(piece.getX(), piece.getY());
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject(getActor());
                payload.setDragActor(getActor());
                dnd.setDragActorPosition(-x, -y + ((Dragable) getActor()).getImageHeight());

                    return payload;
            }


            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target)
            {
                //boardSizeX[2] = 217;
                //boardSizeY[2]

                //System.out.println("mouse: " + event.getStageX()+ " " + event.getStageY());

                switch(boardSize){
                    case 3:
                        for(int i = 0; i < 3; i++){
                            for (int j = 0; j < 3; j++){
                                if(((event.getStageX() > 230 + (i* 30)) && (event.getStageX() < 260 + (i* 30) ))&&
                                        ((event.getStageY() >=120 + (j* 30)) && (event.getStageY() <= 150 + (j* 30))))
                                {
                                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                    piece.setPosition(235 + (i* 30), 125 + (j* 30));
                                    screen.stage.addActor(piece);
                                    //piece.setSquare(0,1);
                                    gameBoard.setSquare(i,j);
                                    gameBoard.check();
                                    gameBoard.findPath();
                                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                    //gameBoard.output();
                                }
                            }
                        }
                        break;
                    case 4:
                        for(int i = 0; i < 4; i++){
                            for (int j = 0; j < 4; j++){
                                if(((event.getStageX() > 215 + (i* 30)) && (event.getStageX() < 245 + (i* 30) ))&&
                                        ((event.getStageY() >=105 + (j* 30)) && (event.getStageY() <= 135 + (j* 30))))
                                {
                                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                    piece.setPosition(220 + (i* 30), 110 + (j* 30));
                                    screen.stage.addActor(piece);
                                    //piece.setSquare(0,1);
                                    gameBoard.setSquare(i,j);
                                    gameBoard.check();
                                    gameBoard.findPath();
                                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                    //gameBoard.output();
                                }
                            }
                        }
                        break;
                    case 5:
                        for(int i = 0; i < 5; i++){
                            for (int j = 0; j < 5; j++){
                                if(((event.getStageX() > 200 + (i* 30)) && (event.getStageX() < 230 + (i* 30) ))&&
                                        ((event.getStageY() >= 90 + (j* 30)) && (event.getStageY() <= 120 + (j* 30))))
                                {
                                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                    piece.setPosition(205 + (i* 30), 95 + (j* 30));
                                    screen.stage.addActor(piece);
                                    //piece.setSquare(0,1);
                                    gameBoard.setSquare(i,j);
                                    gameBoard.check();
                                    gameBoard.findPath();
                                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                    //gameBoard.output();
                                }
                            }
                        }
                        break;
                    case 6:
                        for(int i = 0; i < 6; i++){
                            for (int j = 0; j < 6; j++){
                                if(((event.getStageX() > 185 + (i* 30)) && (event.getStageX() < 215 + (i* 30) ))&&
                                        ((event.getStageY() >=75 + (j* 30)) && (event.getStageY() <= 105 + (j* 30))))
                                {
                                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                    piece.setPosition(190 + (i* 30), 80 + (j* 30));
                                    screen.stage.addActor(piece);
                                    //piece.setSquare(0,1);
                                    gameBoard.setSquare(i,j);
                                    gameBoard.check();
                                    gameBoard.findPath();
                                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                    //gameBoard.output();
                                }
                            }
                        }
                        break;
                    case 7:
                        for(int i = 0; i < 7; i++){
                            for (int j = 0; j < 7; j++){
                                if(((event.getStageX() > 170 + (i* 30)) && (event.getStageX() < 200 + (i* 30) ))&&
                                        ((event.getStageY() >= 60 + (j* 30)) && (event.getStageY() <= 90 + (j* 30))))
                                {
                                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                    piece.setPosition(175 + (i* 30), 65 + (j* 30));
                                    screen.stage.addActor(piece);
                                    //piece.setSquare(0,1);
                                    gameBoard.setSquare(i,j);
                                    gameBoard.check();
                                    gameBoard.findPath();
                                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                    //gameBoard.output();
                                }
                            }
                        }
                        break;
                    case 8:
                        for(int i = 0; i < 8; i++){
                            for (int j = 0; j < 8; j++){
                                if(((event.getStageX() > 155 + (i* 30)) && (event.getStageX() < 185 + (i* 30) ))&&
                                        ((event.getStageY() >=45 + (j* 30)) && (event.getStageY() <= 75 + (j* 30))))
                                {
                                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                    piece.setPosition(160 + (i* 30), 50 + (j* 30));
                                    screen.stage.addActor(piece);
                                    //piece.setSquare(0,1);
                                    gameBoard.setSquare(i,j);
                                    gameBoard.check();
                                    gameBoard.findPath();
                                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                    //gameBoard.output();
                                }
                            }
                        }
                        break;


                }

/*
                //row 1, col 1
                if(((event.getStageX() > 200) && (event.getStageX() < 240))&&
                        ((event.getStageY() >= 90) && (event.getStageY() <= 120)))
                {
                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                    piece.setPosition(208, 95);
                    screen.stage.addActor(piece);
                    //piece.setSquare(0,1);
                    gameBoard.setSquare(0,0);
                    gameBoard.check();
                    gameBoard.findPath();
                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                    //gameBoard.output();
                }

                //row 1, col2
                else if(((event.getStageX() >= 200) && (event.getStageX() <= 240)) &&
                        ((event.getStageY() >= 130) && (event.getStageY() <= 145)))
                {
                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                    piece.setPosition(208, 125);
                    screen.stage.addActor(piece);;
                    gameBoard.setSquare(0,1);
                    gameBoard.check();
                    gameBoard.findPath();
                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));

                   //gameBoard.output();
                }

                //row 1, col3
                else if(((event.getStageX() >= 200) && (event.getStageX() <= 240)) &&
                        ((event.getStageY() >= 145) && (event.getStageY() <= 175)))
                {
                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                    piece.setPosition(208, 155);
                    screen.stage.addActor(piece);
                    gameBoard.setSquare(0,2);
                    gameBoard.check();
                    gameBoard.findPath();
                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                   //gameBoard.output();
                }


                //row 1, col4
                else if(((event.getStageX() >= 200) && (event.getStageX() <= 240)) &&
                        ((event.getStageY() >= 175) && (event.getStageY() <= 200)))
                {
                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                    piece.setPosition(208, 185);
                    screen.stage.addActor(piece);
                    gameBoard.setSquare(0,3);
                    gameBoard.check();
                    gameBoard.findPath();
                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                    //gameBoard.output();
                }

                //row 1, col5
                else if(((event.getStageX() >= 200) && (event.getStageX() <= 240)) &&
                        ((event.getStageY() >= 200) && (event.getStageY() <= 240)))
                {
                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                    piece.setPosition(208, 215);
                    screen.stage.addActor(piece);
                    gameBoard.setSquare(0,4);
                    gameBoard.check();
                    gameBoard.findPath();
                    //gameBoard.output();
                    System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                }
                else
                {
                    //System.out.println("Test Cal8");
                    //screen.stage.addActor(getActor());
                    getActor().addAction(Actions.moveTo(origPos.x, origPos.y, 1.0f, Interpolation.pow4Out));
                }

                //gameBoard.outPut();
                //System.out.println(event.getStageX()+ " " + event.getStageY());
                              /*
                              //3x3
                              if( (event.getStageX() >= 250)
                              {

                              }
                              */

            }
        });
    }
}


