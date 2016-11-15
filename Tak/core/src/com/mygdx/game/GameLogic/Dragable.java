package com.mygdx.game.GameLogic;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Screens.StackScreen;
import com.mygdx.game.Tak;

import java.util.ArrayDeque;
import java.util.ArrayList;

//Will Clean Up and refactor, dirty like ebola

public class Dragable extends Image
{

    //stores the current player
    private Boolean currentPlayer;
    /*
        true = white
        false = black
     */
    private int pieceType;
    /* pieceType
      0 = flat tile
      1 = standing tile
      2 = capstone
    */
    private PlayScreen screen;
    private int boardSize;
    private boolean inPlay;

    private int xPos;
    private int yPos;

    private int startingX;
    private int startingY;

    private int maxDepth;
    private Integer selected;

    private GameBoard gameBoard;
    private StackScreen stackScreen;
    private Dragable stack[];

    //added this temp for demo reasons s
    private Tak tak;

    public Dragable(Image image, PlayScreen scrn, GameBoard newGameboard, int bSize, boolean player, int type, Tak obj)
    //public Dragable(Image image, PlayScreen scrn, GameBoard newGameboard, int bSize, boolean player, int type)
    {
        super(image.getDrawable());
        screen = scrn;
        gameBoard = newGameboard;
        boardSize = bSize;
        currentPlayer = player;
        pieceType = type;
        inPlay = false;
        xPos= -1;
        yPos= -1;
        tak = obj;

        if(pieceType == 0) {
            if(currentPlayer) {
                startingX =50;
                startingY =90;
            }else{
                startingX =20;
                startingY =90;
            }
        }else if(pieceType == 1){
            if(currentPlayer) {
                startingX =50;
                startingY =125;
            }else{
                startingX =20;
                startingY =125;
            }
        }else if(pieceType == 2){
            if(currentPlayer) {
                startingX =50;
                startingY =20;
            }else{
                startingX =20;
                startingY =20;
            }
        }

        switch(bSize){
            case 3:
                maxDepth = 20;
                break;
            case 4:
                maxDepth = 30;
                break;
            case 5:
                maxDepth = 43;
                break;
            case 6:
                maxDepth = 61;
                break;
            case 7:
                maxDepth = 81;
                break;
            case 8:
                maxDepth = 101;
                break;
        }

        stack = new Dragable[maxDepth];

    }

    public void setCurrentPlayer(boolean newCurrentPlayer) {
        currentPlayer = newCurrentPlayer;
    }
    public boolean getCurrentPlayer() {return currentPlayer;}

    public GameBoard getGameBoard() {return gameBoard;}

    public void toggleInPlay(){inPlay = !inPlay;}
    public int getGridXPos(){return xPos;}
    public int getGridYPos(){return yPos;}
    public void setGridPos(int x,int y){
        xPos = x;
        yPos = y;


        selected = 0;
    }

    public int getSelected(){return selected;}
    public void setSelected(int s){selected = s; }

    public void fillStack(GameBoard board, int x, int y){
        for (int i =0; i < selected; i++){

        }
    }

    public void clearStack(){

    }


    public void makeDraggable()
    {
        final Dragable piece = this;
        final Vector2 origPos = new Vector2();
        final DragAndDrop dnd = new DragAndDrop();



        this.addListener(new ClickListener(){
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
        {
            if(inPlay) {
                if (gameBoard.isEmpty(piece.getGridXPos(), piece.getGridYPos()) == false) {
                    if (gameBoard.getStack(piece.getGridXPos(), piece.getGridYPos()).size() > 1) {
                     screen.stackMenu(gameBoard,piece.getGridXPos(), piece.getGridYPos(),piece);

                    }
                }
            }
            return true;
        }});



        dnd.addSource(new DragAndDrop.Source(piece)
        {
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer)
            {

                origPos.set(piece.getX(), piece.getY());


                if(piece.inPlay == true){gameBoard.popSquare(piece.getGridXPos(),piece.getGridYPos());}
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject(getActor());
                payload.setDragActor(getActor());
                dnd.setDragActorPosition(-x, -y + ((Dragable) getActor()).getImageHeight());

                    return payload;
            }




            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target)
            {


                switch(boardSize){
                    case 3: //3x3 board
                        for(int i = 0; i < 3; i++){
                            for (int j = 0; j < 3; j++){
                                if(((event.getStageX() > 230 + (i* 30)) && (event.getStageX() < 260 + (i* 30) ))&&
                                        ((event.getStageY() >=120 + (j* 30)) && (event.getStageY() <= 150 + (j* 30))))
                                {
                                   // System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                    if(piece.inPlay) {
                                        if(
                                                ((i < piece.getGridXPos())&&(j < piece.getGridYPos()))||
                                                ((i > piece.getGridXPos())&&(j > piece.getGridYPos()))||
                                                ((i < piece.getGridXPos())&&(j > piece.getGridYPos()))||
                                                ((i > piece.getGridXPos())&&(j < piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos()+1))||
                                                        ((i < piece.getGridXPos()-1))||
                                                        ((j > piece.getGridYPos()+1))||
                                                        ((j < piece.getGridYPos()-1)))
                                        { //tries to move diagonally (both x and y are less,both and y are greater, x is less and y is greater,y is less and x is greater) or tries to move more than one space

                                            piece.setPosition(235 + (piece.getGridXPos() * 30), 125 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(),piece.getGridYPos(),currentPlayer,pieceType);


                                        } else if(piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || gameBoard.topType(i,j) == 2  ) ){ //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(235 + (piece.getGridXPos() * 30), 125 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType);
                                        }else if(piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ){ //wall tries to move onto a cap
                                            piece.setPosition(235 + (piece.getGridXPos() * 30), 125 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType);
                                        }else {
                                            piece.setPosition(235 + (i * 30), 125 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            gameBoard.addSelected();
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                        }
                                        /*else if(piece.pieceType == 2 && gameBoard.topType(i,j) == 1 ) { //cap tries to move onto a wall
                                            //flatten it
                                            piece.setPosition(235 + (i * 30), 125 + (j * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.squashTop(i,j);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            gameBoard.check();
                                            gameBoard.findPath();

                                        }
                                        }*/
                                    }else {

                                        if((piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || (gameBoard.topType(i,j) == 2  ))|| //road tries to move onto a wall or cap
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ))||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 1 )||
                                                (piece.pieceType == 0 && gameBoard.topType(i,j) == 0 )||
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 1 )||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 2 )||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 0 )||
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 0 )) { //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(startingX,startingY);
                                            screen.stage.addActor(piece);


                                        }else {

                                            piece.setPosition(235 + (i * 30), 125 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            gameBoard.addSelected();
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            inPlay = true;
                                            //  System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                        }
                                    }

                                }
                            }


                        }
                        break;
                    case 4: //4x4 board
                        for(int i = 0; i < 4; i++){
                            for (int j = 0; j < 4; j++){
                                if(((event.getStageX() > 215 + (i* 30)) && (event.getStageX() < 245 + (i* 30) ))&&
                                        ((event.getStageY() >=105 + (j* 30)) && (event.getStageY() <= 135 + (j* 30))))
                                {

                                    if(piece.inPlay) {
                                        if(
                                                ((i < piece.getGridXPos())&&(j < piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos())&&(j > piece.getGridYPos()))||
                                                        ((i < piece.getGridXPos())&&(j > piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos())&&(j < piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos()+1))||
                                                        ((i < piece.getGridXPos()-1))||
                                                        ((j > piece.getGridYPos()+1))||
                                                        ((j < piece.getGridYPos()-1)))
                                        { //tries to move diagonally (both x and y are less,both and y are greater, x is less and y is greater,y is less and x is greater) or tries to move more than one space

                                            piece.setPosition(220 + (piece.getGridXPos() * 30), 110 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(),piece.getGridYPos(),currentPlayer,pieceType);


                                        } else if(piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || gameBoard.topType(i,j) == 2  ) ){ //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(220 + (piece.getGridXPos() * 30), 110 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType);
                                        }else if(piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ){ //wall tries to move onto a cap
                                            piece.setPosition(220 + (piece.getGridXPos() * 30), 110 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType);
                                        }else {
                                            piece.setPosition(220 + (i * 30), 110 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            gameBoard.addSelected();
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                        }
                                        /*else if(piece.pieceType == 2 && gameBoard.topType(i,j) == 1 ) { //cap tries to move onto a wall
                                            //flatten it
                                            piece.setPosition(235 + (i * 30), 125 + (j * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.squashTop(i,j);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            gameBoard.check();
                                            gameBoard.findPath();

                                        }
                                        }*/
                                    }else {

                                        if((piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || (gameBoard.topType(i,j) == 2  ))|| //road tries to move onto a wall or cap
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ))||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 1 )||
                                                (piece.pieceType == 0 && gameBoard.topType(i,j) == 0 )||
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 1 )||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 2 )||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 0 )||
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 0 )) { //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(startingX,startingY);
                                            screen.stage.addActor(piece);


                                        }else {

                                            piece.setPosition(220 + (i * 30), 110 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            gameBoard.addSelected();
                                            inPlay = true;
                                            //  System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 5: //5x5 board
                        for(int i = 0; i < 5; i++){
                            for (int j = 0; j < 5; j++){
                                if(((event.getStageX() > 200 + (i* 30)) && (event.getStageX() < 230 + (i* 30) ))&&
                                        ((event.getStageY() >= 90 + (j* 30)) && (event.getStageY() <= 120 + (j* 30))))
                                {

                                    if(piece.inPlay) {
                                        if(
                                                ((i < piece.getGridXPos())&&(j < piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos())&&(j > piece.getGridYPos()))||
                                                        ((i < piece.getGridXPos())&&(j > piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos())&&(j < piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos()+1))||
                                                        ((i < piece.getGridXPos()-1))||
                                                        ((j > piece.getGridYPos()+1))||
                                                        ((j < piece.getGridYPos()-1)))
                                        { //tries to move diagonally (both x and y are less,both and y are greater, x is less and y is greater,y is less and x is greater) or tries to move more than one space

                                            piece.setPosition(205 + (piece.getGridXPos() * 30), 95 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(),piece.getGridYPos(),currentPlayer,pieceType);


                                        } else if(piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || gameBoard.topType(i,j) == 2  ) ){ //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(205 + (piece.getGridXPos() * 30), 95 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType);
                                        }else if(piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ){ //wall tries to move onto a cap
                                            piece.setPosition(205 + (piece.getGridXPos() * 30), 95 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType);
                                        }else {
                                            piece.setPosition(205 + (i * 30), 95 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            gameBoard.addSelected();
                                        }
                                        /*else if(piece.pieceType == 2 && gameBoard.topType(i,j) == 1 ) { //cap tries to move onto a wall
                                            //flatten it
                                            piece.setPosition(235 + (i * 30), 125 + (j * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.squashTop(i,j);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            gameBoard.check();
                                            gameBoard.findPath();

                                        }
                                        }*/
                                    }else {

                                        if((piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || (gameBoard.topType(i,j) == 2  ))|| //road tries to move onto a wall or cap
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ))||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 1 )||
                                                (piece.pieceType == 0 && gameBoard.topType(i,j) == 0 )||
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 1 )||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 2 )||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 0 )||
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 0 )) { //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(startingX,startingY);
                                            screen.stage.addActor(piece);


                                        }else {

                                            piece.setPosition(205 + (i * 30), 95 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            gameBoard.addSelected();
                                            inPlay = true;
                                            //  System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                        }
                                    }

                                }
                            }
                        }
                        break;
                    case 6: //6x6 board
                        for(int i = 0; i < 6; i++){
                            for (int j = 0; j < 6; j++){
                                if(((event.getStageX() > 185 + (i* 30)) && (event.getStageX() < 215 + (i* 30) ))&&
                                        ((event.getStageY() >=75 + (j* 30)) && (event.getStageY() <= 105 + (j* 30))))
                                {
                                    if(piece.inPlay) {
                                        if(
                                                ((i < piece.getGridXPos())&&(j < piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos())&&(j > piece.getGridYPos()))||
                                                        ((i < piece.getGridXPos())&&(j > piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos())&&(j < piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos()+1))||
                                                        ((i < piece.getGridXPos()-1))||
                                                        ((j > piece.getGridYPos()+1))||
                                                        ((j < piece.getGridYPos()-1)))
                                        { //tries to move diagonally (both x and y are less,both and y are greater, x is less and y is greater,y is less and x is greater) or tries to move more than one space

                                            piece.setPosition(190 + (piece.getGridXPos() * 30), 80 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(),piece.getGridYPos(),currentPlayer,pieceType);


                                        } else if(piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || gameBoard.topType(i,j) == 2  ) ){ //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(190 + (piece.getGridXPos() * 30), 80 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType);
                                        }else if(piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ){ //wall tries to move onto a cap
                                            piece.setPosition(190 + (piece.getGridXPos() * 30), 80 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType);
                                        }else {
                                            piece.setPosition(190 + (i * 30), 80 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            gameBoard.addSelected();
                                        }
                                        /*else if(piece.pieceType == 2 && gameBoard.topType(i,j) == 1 ) { //cap tries to move onto a wall
                                            //flatten it
                                            piece.setPosition(235 + (i * 30), 125 + (j * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.squashTop(i,j);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            gameBoard.check();
                                            gameBoard.findPath();

                                        }
                                        }*/
                                    }else {

                                        if((piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || (gameBoard.topType(i,j) == 2  ))|| //road tries to move onto a wall or cap
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ))||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 1 )||
                                                (piece.pieceType == 0 && gameBoard.topType(i,j) == 0 )||
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 1 )||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 2 )||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 0 )||
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 0 )) { //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(startingX,startingY);
                                            screen.stage.addActor(piece);


                                        }else {

                                            piece.setPosition(190 + (i * 30), 80 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            gameBoard.addSelected();
                                            inPlay = true;
                                            //  System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 7: //7x7 board
                        for(int i = 0; i < 7; i++){
                            for (int j = 0; j < 7; j++){
                                if(((event.getStageX() > 170 + (i* 30)) && (event.getStageX() < 200 + (i* 30) ))&&
                                        ((event.getStageY() >= 60 + (j* 30)) && (event.getStageY() <= 90 + (j* 30))))
                                {

                                    if(piece.inPlay) {
                                        if(
                                                ((i < piece.getGridXPos())&&(j < piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos())&&(j > piece.getGridYPos()))||
                                                        ((i < piece.getGridXPos())&&(j > piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos())&&(j < piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos()+1))||
                                                        ((i < piece.getGridXPos()-1))||
                                                        ((j > piece.getGridYPos()+1))||
                                                        ((j < piece.getGridYPos()-1)))
                                        { //tries to move diagonally (both x and y are less,both and y are greater, x is less and y is greater,y is less and x is greater) or tries to move more than one space

                                            piece.setPosition(175 + (piece.getGridXPos() * 30), 65 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(),piece.getGridYPos(),currentPlayer,pieceType);


                                        } else if(piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || gameBoard.topType(i,j) == 2  ) ){ //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(175 + (piece.getGridXPos() * 30), 65 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType);
                                        }else if(piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ){ //wall tries to move onto a cap
                                            piece.setPosition(175 + (piece.getGridXPos() * 30), 65 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType);
                                        }else {
                                            piece.setPosition(175 + (i * 30), 65 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            gameBoard.addSelected();
                                        }
                                        /*else if(piece.pieceType == 2 && gameBoard.topType(i,j) == 1 ) { //cap tries to move onto a wall
                                            //flatten it
                                            piece.setPosition(235 + (i * 30), 125 + (j * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.squashTop(i,j);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            gameBoard.check();
                                            gameBoard.findPath();

                                        }
                                        }*/
                                    }else {

                                        if((piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || (gameBoard.topType(i,j) == 2  ))|| //road tries to move onto a wall or cap
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ))||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 1 )||
                                                (piece.pieceType == 0 && gameBoard.topType(i,j) == 0 )||
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 1 )||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 2 )||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 0 )||
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 0 )) { //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(startingX,startingY);
                                            screen.stage.addActor(piece);


                                        }else {

                                            piece.setPosition(175 + (i * 30), 65 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            gameBoard.addSelected();
                                            inPlay = true;
                                            //  System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                        }
                                    }

                                }
                            }
                        }
                        break;
                    case 8: //8x8 board
                        for(int i = 0; i < 8; i++){
                            for (int j = 0; j < 8; j++){
                                if(((event.getStageX() > 155 + (i* 30)) && (event.getStageX() < 185 + (i* 30) ))&&
                                        ((event.getStageY() >=45 + (j* 30)) && (event.getStageY() <= 75 + (j* 30))))
                                {
                                    if(piece.inPlay) {
                                        if(
                                                ((i < piece.getGridXPos())&&(j < piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos())&&(j > piece.getGridYPos()))||
                                                        ((i < piece.getGridXPos())&&(j > piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos())&&(j < piece.getGridYPos()))||
                                                        ((i > piece.getGridXPos()+1))||
                                                        ((i < piece.getGridXPos()-1))||
                                                        ((j > piece.getGridYPos()+1))||
                                                        ((j < piece.getGridYPos()-1)))
                                        { //tries to move diagonally (both x and y are less,both and y are greater, x is less and y is greater,y is less and x is greater) or tries to move more than one space

                                            piece.setPosition(160 + (piece.getGridXPos() * 30), 50 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(),piece.getGridYPos(),currentPlayer,pieceType);


                                        } else if(piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || gameBoard.topType(i,j) == 2  ) ){ //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(160 + (piece.getGridXPos() * 30), 50 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType);
                                        }else if(piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ){ //wall tries to move onto a cap
                                            piece.setPosition(160 + (piece.getGridXPos() * 30), 50 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType);
                                        }else {
                                            piece.setPosition(160 + (i * 30), 50 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            gameBoard.addSelected();
                                            //gameBoard.createPath();
                                        }
                                        /*else if(piece.pieceType == 2 && gameBoard.topType(i,j) == 1 ) { //cap tries to move onto a wall
                                            //flatten it
                                            piece.setPosition(235 + (i * 30), 125 + (j * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.squashTop(i,j);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            gameBoard.check();
                                            gameBoard.findPath();

                                        }
                                        }*/
                                    }else {

                                        if((piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || (gameBoard.topType(i,j) == 2  ))|| //road tries to move onto a wall or cap
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ))||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 1 )||
                                                (piece.pieceType == 0 && gameBoard.topType(i,j) == 0 )||
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 1 )||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 2 )||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 0 )||
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 0 )) { //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(startingX,startingY);
                                            screen.stage.addActor(piece);


                                        }else {

                                            piece.setPosition(160 + (i * 30), 50 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType);
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            gameBoard.addSelected();
                                            inPlay = true;
                                            //  System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                        }
                                    }
                                }
                            }
                        }
                        break;


                }
            }
        });
    }
}


