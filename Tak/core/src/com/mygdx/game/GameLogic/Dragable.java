package com.mygdx.game.GameLogic;

import com.badlogic.gdx.ai.btree.decorator.Random;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.GamePieces.DarkCirclePiece1;
import com.mygdx.game.GamePieces.DarkSquarePiece1;
import com.mygdx.game.GamePieces.DarkTrianglePiece1;
import com.mygdx.game.GamePieces.GamePieces;
import com.mygdx.game.GamePieces.LightCirclePiece1;
import com.mygdx.game.GamePieces.LightSquarePiece1;
import com.mygdx.game.GamePieces.LightTriangle1;
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
    private int index;

    //added this temp for demo reasons s
    private Tak tak;

    public Dragable(Image image, int i, PlayScreen scrn, GameBoard newGameboard, int bSize, boolean player, int type, Tak obj)
    //public Dragable(Image image, PlayScreen scrn, GameBoard newGameboard, int bSize, boolean player, int type)
    {
        super(image.getDrawable());
        index = i;
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

        //System.out.println("gameAI" + scrn.isGameAISet());


    }

    public void setCurrentPlayer(boolean newCurrentPlayer) {
        currentPlayer = newCurrentPlayer;
    }
    public boolean getCurrentPlayer() {return currentPlayer;}

    public GameBoard getGameBoard() {return gameBoard;}
    public int getDimensions(){return boardSize;}

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

    public boolean getPlayer(){return currentPlayer;}

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
                            //  screen.stackMenu(gameBoard,piece.getGridXPos(), piece.getGridYPos(),piece);

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
                //System.out.println(screen.isGameAISet());
                switch(boardSize)
                {
                    case 3: //3x3 board

                        //ADDED HERE
                        //=====================================================================
                        for(int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (((event.getStageX() > 230 + (i * 30)) && (event.getStageX() < 260 + (i * 30))) &&
                                        ((event.getStageY() >= 120 + (j * 30)) && (event.getStageY() <= 150 + (j * 30))))
                                {
                                    if(screen.isGameAISet() == true)
                                    {
                                        drawAI(event.getStageX(), event.getStageY(), i, j, 3);
                                    }

                                }
                            }
                        }




                        for(int i = 0; i < 3; i++){
                            for (int j = 0; j < 3; j++){
                                if(((event.getStageX() > 230 + (i* 30)) && (event.getStageX() < 260 + (i* 30) ))&&
                                        ((event.getStageY() >=120 + (j* 30)) && (event.getStageY() <= 150 + (j* 30))))
                                {
                                    // System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));
                                    if(piece.inPlay)
                                    {

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


                                            //System.out.println("gameAI" + screen.isGameAISet());

                                            gameBoard.setSquare(piece.getGridXPos(),piece.getGridYPos(),currentPlayer,pieceType,index);
                                                     //setSquare(int row, int col, boolean player, int type, int i)

                                        } else if(piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || gameBoard.topType(i,j) == 2  ) ){ //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(235 + (piece.getGridXPos() * 30), 125 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);


                                        }
                                        else if (piece.pieceType == 1 && gameBoard.topType(i,j) == 1)
                                        {
                                            piece.setPosition(235 + (piece.getGridXPos() * 30), 125 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);

                                        }
                                        else
                                        {
                                            piece.setPosition(235 + (i * 30), 125 + (j * 30));
                                            screen.stage.addActor(piece);
//System.out.println("ITEST");
                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType, index);
                                            gameBoard.addSelected();
                                            //gameBoard.check();
                                            //gameBoard.findPath();

                                        }

                                    }
                                    else
                                    {
                                        //test screen call
                                        //DRAW HERE
                                        //drawAI();
                                        //System.out.println("d" + screen.isGameAISet());


                                        if((piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || (gameBoard.topType(i,j) == 2  ))||
                                                //road tries to move onto a wall or cap
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ))||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 1 )||
                                                (piece.pieceType == 0 && gameBoard.topType(i,j) == 0 )||
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 1 )||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 2 )||
                                                (piece.pieceType == 2 && gameBoard.topType(i,j) == 0 )||
                                                (piece.pieceType == 1 && gameBoard.topType(i,j) == 0 ))
                                        { //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(startingX,startingY);
                                            screen.stage.addActor(piece);


                                        }
                                        else
                                        {

                                            piece.setPosition(235 + (i * 30), 125 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType, index);
                                            gameBoard.addSelected();
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            inPlay = true;
                                            //  System.out.println(gameBoard.checkStartAndEnd(0,0,0,4));

                                            //DRAW HERE
                                            //drawAI();
                                        }
                                    }

                                }
                            }
                        }
                        if(((event.getStageX() < 230 ) || (event.getStageX() > 260 ))||
                                ((event.getStageY() <=120 ) || (event.getStageY() >= 150 )))
                        {
                            //DRAW HERE

                            //System.out.println("eventX" + event.getStageX() + "eventY" + event.getStageY());


                            if(piece.inPlay)
                            {
                                // System.out.println("OUT OF BOUNDS");
                                piece.setPosition(235 + (piece.getGridXPos() * 30), 125 + (piece.getGridYPos() * 30));
                                screen.stage.addActor(piece);
                                gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);

                                //DRAW HERE
                                //drawAI();
                            }
                            else
                            {
                                piece.setPosition(startingX,startingY);
                                screen.stage.addActor(piece);

                            }
                        }



                        break;
                    case 4: //4x4 board

                        //ADDED HERE
                        //=====================================================================
                        for(int i = 0; i < 4; i++)
                        {
                            for (int j = 0; j < 4; j++)
                            {

                                if (((event.getStageX() > 215 + (i * 30)) && (event.getStageX() < 245 + (i * 30))) &&
                                        ((event.getStageY() >= 105 + (j * 30)) && (event.getStageY() <= 135 + (j * 30))))
                                {
                                    if(screen.isGameAISet() == true)
                                    {
                                        drawAI(event.getStageX(), event.getStageY(), i, j, 4);
                                    }
                                }

                            }
                        }
                        //=====================================================================



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
                                            gameBoard.setSquare(piece.getGridXPos(),piece.getGridYPos(),currentPlayer,pieceType, index);


                                        } else if(piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || gameBoard.topType(i,j) == 2  ) ){ //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(220 + (piece.getGridXPos() * 30), 110 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else if(piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ){ //wall tries to move onto a cap
                                            piece.setPosition(220 + (piece.getGridXPos() * 30), 110 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else if (piece.pieceType == 1 && gameBoard.topType(i,j) == 1){
                                            piece.setPosition(220 + (piece.getGridXPos() * 30), 110 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else {
                                            piece.setPosition(220 + (i * 30), 110 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType, index);
                                            gameBoard.addSelected();
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                        }

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
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType, index);
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


                        if(((event.getStageX() < 215 ) || (event.getStageX() > 245 ))||
                                ((event.getStageY() <=105 ) || (event.getStageY() >= 135 ))){

                            if(piece.inPlay){
                                // System.out.println("OUT OF BOUNDS");
                                piece.setPosition(220 + (piece.getGridXPos() * 30), 110 + (piece.getGridYPos() * 30));
                                screen.stage.addActor(piece);
                                gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                            }else{
                                piece.setPosition(startingX,startingY);
                                screen.stage.addActor(piece);
                                // System.out.println("PUTTING BACK");
                            }
                        }

                        break;
                    case 5: //5x5 board

                        //added here
                        //=================================================
                        for(int i = 0; i < 5; i++) {
                            for (int j = 0; j < 5; j++) {
                                if (((event.getStageX() > 200 + (i * 30)) && (event.getStageX() < 230 + (i * 30))) &&
                                        ((event.getStageY() >= 90 + (j * 30)) && (event.getStageY() <= 120 + (j * 30)))) {

                                    //System.out.println("row" + i + "col" + j);
                                    if(screen.isGameAISet() == true)
                                    {
                                        drawAI(event.getStageX(), event.getStageY(), i, j, 5);
                                    }
                                }
                            }
                        }

                        //===============================================

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
                                            gameBoard.setSquare(piece.getGridXPos(),piece.getGridYPos(),currentPlayer,pieceType, index);


                                        } else if(piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || gameBoard.topType(i,j) == 2  ) ){ //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(205 + (piece.getGridXPos() * 30), 95 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else if(piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ){ //wall tries to move onto a cap
                                            piece.setPosition(205 + (piece.getGridXPos() * 30), 95 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else if (piece.pieceType == 1 && gameBoard.topType(i,j) == 1){
                                            piece.setPosition(205 + (piece.getGridXPos() * 30), 95 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else if((piece.pieceType == 2 && gameBoard.topType(i,j) == 1)&&(piece.getPlayer() !=  gameBoard.topPlayer(i,j)) ) { //cap tries to move onto a wall
                                            //flatten it
                                            piece.setPosition(205 + (i * 30), 95 + (j * 30));
                                            screen.squashPiece(gameBoard.getIndex(i,j));
                                            screen.stage.addActor(screen.getPiece(gameBoard.getIndex(i,j)));
                                            screen.stage.addActor(piece);
                                            gameBoard.squashTop(i,j);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType,index);


                                        }else {
                                            piece.setPosition(205 + (i * 30), 95 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType, index);
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            gameBoard.addSelected();
                                        }

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
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType, index);
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

                        if(((event.getStageX() < 200 ) || (event.getStageX() > 230 ))||
                                ((event.getStageY() <=90 ) || (event.getStageY() >= 120 ))){

                            if(piece.inPlay){
                                // System.out.println("OUT OF BOUNDS");
                                piece.setPosition(205 + (piece.getGridXPos() * 30), 95 + (piece.getGridYPos() * 30));
                                screen.stage.addActor(piece);
                                gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                            }else{
                                piece.setPosition(startingX,startingY);
                                screen.stage.addActor(piece);
                                // System.out.println("PUTTING BACK");
                            }
                        }
                        break;
                    case 6: //6x6 board

                        //added here
                        //======================================
                        for(int i = 0; i < 6; i++)
                        {
                            for (int j = 0; j < 6; j++)
                            {
                                if(((event.getStageX() > 185 + (i* 30)) && (event.getStageX() < 215 + (i* 30) ))&&
                                        ((event.getStageY() >=75 + (j* 30)) && (event.getStageY() <= 105 + (j* 30))))
                                {
                                    if(screen.isGameAISet() == true)
                                    {
                                        drawAI(event.getStageX(), event.getStageY(), i, j, 6);
                                    }
                                }
                            }
                        }
                        //========================================

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
                                            gameBoard.setSquare(piece.getGridXPos(),piece.getGridYPos(),currentPlayer,pieceType, index);


                                        } else if(piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || gameBoard.topType(i,j) == 2  ) ){ //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(190 + (piece.getGridXPos() * 30), 80 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else if(piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ){ //wall tries to move onto a cap
                                            piece.setPosition(190 + (piece.getGridXPos() * 30), 80 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else if (piece.pieceType == 1 && gameBoard.topType(i,j) == 1){
                                            piece.setPosition(190 + (piece.getGridXPos() * 30), 80 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else if((piece.pieceType == 2 && gameBoard.topType(i,j) == 1)&&(piece.getPlayer() !=  gameBoard.topPlayer(i,j)) ) { //cap tries to move onto a wall
                                            //flatten it
                                            piece.setPosition(190 + (i * 30), 80 + (j * 30));
                                            screen.squashPiece(gameBoard.getIndex(i,j));
                                            screen.stage.addActor(screen.getPiece(gameBoard.getIndex(i,j)));
                                            screen.stage.addActor(piece);
                                            gameBoard.squashTop(i,j);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType,index);


                                        }else {
                                            piece.setPosition(190 + (i * 30), 80 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType, index);
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            gameBoard.addSelected();
                                        }

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
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType, index);
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

                        if(((event.getStageX() < 185 ) || (event.getStageX() > 215 ))||
                                ((event.getStageY() <=75 ) || (event.getStageY() >= 105 ))){

                            if(piece.inPlay){
                                // System.out.println("OUT OF BOUNDS");
                                piece.setPosition(190 + (piece.getGridXPos() * 30), 80 + (piece.getGridYPos() * 30));
                                screen.stage.addActor(piece);
                                gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                            }else{
                                piece.setPosition(startingX,startingY);
                                screen.stage.addActor(piece);
                                // System.out.println("PUTTING BACK");
                            }
                        }
                        break;
                    case 7: //7x7 board

                        for(int i = 0; i < 7; i++)
                        {
                            for (int j = 0; j < 7; j++)
                            {
                                if(((event.getStageX() > 170 + (i* 30)) && (event.getStageX() < 200 + (i* 30) ))&&
                                        ((event.getStageY() >= 60 + (j* 30)) && (event.getStageY() <= 90 + (j* 30))))
                                {
                                    //System.out.println("row" + i + "col" + j);
                                    if(screen.isGameAISet() == true)
                                    {
                                        drawAI(event.getStageX(), event.getStageY(), i, j, 7);
                                    }
                                }
                            }
                        }

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
                                            gameBoard.setSquare(piece.getGridXPos(),piece.getGridYPos(),currentPlayer,pieceType, index);


                                        } else if(piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || gameBoard.topType(i,j) == 2  ) ){ //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(175 + (piece.getGridXPos() * 30), 65 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else if(piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ){ //wall tries to move onto a cap
                                            piece.setPosition(175 + (piece.getGridXPos() * 30), 65 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else if (piece.pieceType == 1 && gameBoard.topType(i,j) == 1){
                                            piece.setPosition(175 + (piece.getGridXPos() * 30), 65 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else if((piece.pieceType == 2 && gameBoard.topType(i,j) == 1)&&(piece.getPlayer() !=  gameBoard.topPlayer(i,j)) ) { //cap tries to move onto a wall
                                            //flatten it
                                            piece.setPosition(175 + (i * 30), 65 + (j * 30));
                                            screen.squashPiece(gameBoard.getIndex(i,j));
                                            screen.stage.addActor(screen.getPiece(gameBoard.getIndex(i,j)));
                                            screen.stage.addActor(piece);
                                            gameBoard.squashTop(i,j);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType,index);


                                        }else {
                                            piece.setPosition(175 + (i * 30), 65 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType, index);
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            gameBoard.addSelected();
                                        }

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
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType, index);
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

                        if(((event.getStageX() < 170 ) || (event.getStageX() > 200 ))||
                                ((event.getStageY() <=60 ) || (event.getStageY() >= 90 ))){

                            if(piece.inPlay){
                                // System.out.println("OUT OF BOUNDS");
                                piece.setPosition(175 + (piece.getGridXPos() * 30), 65 + (piece.getGridYPos() * 30));
                                screen.stage.addActor(piece);
                                gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                            }else{
                                piece.setPosition(startingX,startingY);
                                screen.stage.addActor(piece);
                                // System.out.println("PUTTING BACK");
                            }
                        }
                        break;
                    case 8: //8x8 board


                        //added this

                        for(int i = 0; i < 8; i++)
                        {
                            for (int j = 0; j < 8; j++)
                            {
                                if (((event.getStageX() > 155 + (i * 30)) && (event.getStageX() < 185 + (i * 30))) &&
                                        ((event.getStageY() >= 45 + (j * 30)) && (event.getStageY() <= 75 + (j * 30))))
                                {
                                    //System.out.println("row" + i + "col" + j);
                                    if(screen.isGameAISet() == true)
                                    {
                                        drawAI(event.getStageX(), event.getStageY(), i, j, 8);
                                    }
                                }
                            }
                        }

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
                                            gameBoard.setSquare(piece.getGridXPos(),piece.getGridYPos(),currentPlayer,pieceType, index);


                                        } else if(piece.pieceType == 0 && (gameBoard.topType(i,j) == 1 || gameBoard.topType(i,j) == 2  ) ){ //road tries to move onto a wall or cap
                                            //invalid move, put them back to their old spot
                                            piece.setPosition(160 + (piece.getGridXPos() * 30), 50 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else if(piece.pieceType == 1 && gameBoard.topType(i,j) == 2 ){ //wall tries to move onto a cap
                                            piece.setPosition(160 + (piece.getGridXPos() * 30), 50 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else if (piece.pieceType == 1 && gameBoard.topType(i,j) == 1){
                                            piece.setPosition(160 + (piece.getGridXPos() * 30), 50 + (piece.getGridYPos() * 30));
                                            screen.stage.addActor(piece);
                                            gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                                        }else if((piece.pieceType == 2 && gameBoard.topType(i,j) == 1)&&(piece.getPlayer() !=  gameBoard.topPlayer(i,j)) ) { //cap tries to move onto a wall
                                            //flatten it
                                            piece.setPosition(160 + (i * 30), 50 + (j * 30));
                                            screen.squashPiece(gameBoard.getIndex(i,j));
                                            screen.stage.addActor(screen.getPiece(gameBoard.getIndex(i,j)));
                                            screen.stage.addActor(piece);
                                            gameBoard.squashTop(i,j);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType,index);


                                        }else {
                                            piece.setPosition(160 + (i * 30), 50 + (j * 30));
                                            screen.stage.addActor(piece);

                                            piece.setGridPos(i, j);
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType, index);
                                            //gameBoard.check();
                                            //gameBoard.findPath();
                                            gameBoard.addSelected();
                                            //gameBoard.createPath();
                                        }

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
                                            gameBoard.setSquare(i, j, currentPlayer, pieceType, index);
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
                        //.out.println("k" + event.getStageX());
                        //System.out.println("k" + event.getStageY());

                        if(((event.getStageX() < 155 ) || (event.getStageX() > 185 ))||
                                ((event.getStageY() <45 ) || (event.getStageY() > 75 ))){

                            if(piece.inPlay){
                                // System.out.println("OUT OF BOUNDS");
                                piece.setPosition(160 + (piece.getGridXPos() * 30), 50 + (piece.getGridYPos() * 30));
                                screen.stage.addActor(piece);
                                gameBoard.setSquare(piece.getGridXPos(), piece.getGridYPos(), currentPlayer, pieceType, index);
                            }else{
                                piece.setPosition(startingX,startingY);
                                screen.stage.addActor(piece);
                                // System.out.println("PUTTING BACK");
                            }
                        }
                        break;


                }
            }
        });
    }

    public void drawAI(float x, float y, int row, int col, int boardSize)
    {
        Random rand = new Random();

        int randomNum;
        int minimum =0;
        int maximum =5;
        randomNum = minimum + (int)(Math.random() * maximum);

        //System.out.println(randomNum);
        //System.out.println("x" +  x + "" + " " + y);

        int topX = 0;
        int topy = 0;

        //y = left right
        //x = up down
        switch(boardSize)
        {
            case 3:
            {
                int randomSpot = 0 + (int)(Math.random() * 2);
                topX = 236 + (randomSpot * 30);
                topy = 123 + (randomSpot * 30);
            }
            break;

            case 4:
            {
                int randomSpot = 0 + (int)(Math.random() * 3);
                topX = 219 + (randomSpot * 30);
                topy = 111 + (randomSpot * 30);
            }
            break;

            case 5:
            {
                int randomSpot = 0 + (int)(Math.random() * 3);
                topX = 207 + (randomSpot * 30);
                topy = 95 + (randomSpot * 30);
            }
            break;

            case 6:
            {
                int randomSpot = 0 + (int)(Math.random() * 3);
                topX = 193 + (randomSpot * 30);
                topy = 79 + (randomSpot * 30);
            }
            break;

            case 7:
            {
                int randomSpot = 0 + (int)(Math.random() * 3);
                topX = 178 + (randomSpot * 30);
                topy = 65 + (randomSpot * 30);
            }
            break;

            case 8:
            {
                int randomSpot = 0 + (int)(Math.random() * 3);
                topX = 158 + (randomSpot * 30);
                topy = 50 + (randomSpot * 30);
            }
            break;
        }

        DarkCirclePiece1 darkCirclePiece1 = new DarkCirclePiece1() ;
        DarkSquarePiece1 darkSquarePiece1 = new DarkSquarePiece1() ;
        DarkTrianglePiece1 darkTrianglePiece1 = new DarkTrianglePiece1();

        LightCirclePiece1 lightCirclePiece1 = new LightCirclePiece1() ;
        LightSquarePiece1 lightSquarePiece1 = new LightSquarePiece1();
        LightTriangle1 lightTriangle1Piece = new LightTriangle1() ;


        int randomPiece = 0 + (int)(Math.random() * 2);

        if(!getCurrentPlayer()) {
            switch (randomPiece)
            {
                case 0: {
                    lightCirclePiece1.setImage(lightCirclePiece1.getImageId(), lightCirclePiece1.getImageTexturePath(), topX, topy, 30, 30);
                    screen.stage.addActor(lightCirclePiece1.getGamePiece());
                }
                break;

                case 1: {
                    lightSquarePiece1.setImage(lightSquarePiece1.getImageId(), lightSquarePiece1.getImageTexturePath(), topX, topy, 30, 30);
                    screen.stage.addActor(lightSquarePiece1.getGamePiece());
                }
                break;

                case 2: {
                    lightTriangle1Piece.setImage(lightTriangle1Piece.getImageId(), lightTriangle1Piece.getImageTexturePath(), topX, topy, 30, 30);
                    screen.stage.addActor(lightTriangle1Piece.getGamePiece());
                }
                break;
            }
        }
        else
        {
            switch (randomPiece)
            {
                case 0: {
                    darkCirclePiece1.setImage(darkCirclePiece1.getImageId(), darkCirclePiece1.getImageTexturePath(), topX, topy, 30, 30);
                    screen.stage.addActor(darkCirclePiece1.getGamePiece());
                }
                break;

                case 1: {
                    darkSquarePiece1.setImage(darkSquarePiece1.getImageId(), darkSquarePiece1.getImageTexturePath(), topX, topy, 30, 30);
                    screen.stage.addActor(darkSquarePiece1.getGamePiece());
                }
                break;

                case 2: {
                    darkTrianglePiece1.setImage(darkTrianglePiece1.getImageId(), darkTrianglePiece1.getImageTexturePath(), topX, topy, 30, 30);
                    screen.stage.addActor(darkTrianglePiece1.getGamePiece());
                }
                break;

            }
        }


    }


        /*
            darkCirclePiece1.setImage(darkCirclePiece1.getImageId(), darkCirclePiece1.getImageTexturePath(), topX, topy, 30, 30);
            screen.stage.addActor(darkCirclePiece1.getGamePiece());
        */

}




