package com.mygdx.game.GameLogic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Screens.Challenge;
import com.mygdx.game.Screens.MainMenu;
import com.mygdx.game.Screens.PauseScreen;
import com.mygdx.game.Screens.Versus;
import com.mygdx.game.Screens.WinScreen;
import com.mygdx.game.StaticVariables;
import com.mygdx.game.Tak;

import java.awt.SystemTray;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

public class GameBoard
{
    int dimensions;

    private  ArrayDeque<GamePiece> board[][];

    private int maxDepth;
    Tak app;

    //added this
    private GamePiece blackPlayer;
    private GamePiece whitePlayer;
    private GamePiece AI;

    ArrayList<String> blackPlayerSquares;
    ArrayList<String> whitePlayerSquares;

    //public GameBoard(int bSize)
    public GameBoard(int bSize, Tak obj)
    {
        dimensions = bSize;
        app = obj;

        blackPlayer = new GamePiece();
        whitePlayer = new GamePiece();

        blackPlayerSquares = new ArrayList<String>();
        whitePlayerSquares = new ArrayList<String>();

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

        board = new ArrayDeque[dimensions][dimensions];

        for(int i = 0; i < dimensions; i++)
        {
            for(int j = 0; j < dimensions; j++)
            {
                board[j][i] = new ArrayDeque<GamePiece>(maxDepth);
            }
        }

    }

    public void setGamePiece(GamePiece piece)
    {
        if(!(piece.getPlayer()))
        {
            blackPlayer = piece;
        }
        else
        {
            whitePlayer = piece;
        }

    }
    public void setboard(ArrayDeque<GamePiece> newboard[][]){
        board = newboard;
    }

    public GamePiece getWhitePiece(){return whitePlayer;}
    public GamePiece getBlackPiece(){return blackPlayer;}

    public int getDimensions(){return dimensions;}

    public void setSquare(int row, int col, boolean player, int type, int i)
    //public void setSquare(int row, int col, String player, int type)
    {
        GamePiece tmpPiece = new GamePiece(type,player, i);
        board[row][col].push(tmpPiece);

        setGamePiece(tmpPiece);
    }

    public void popSquare(int row, int col){
        board[row][col].pop();
    }
    public boolean isEmpty(int row, int col)
    {

        //added the board is idealized to false by default
        if(board[row][col].isEmpty())
        {
            return true;
        }

        return false;
    }

    public void squashTop(int row, int col){

        board[row][col].element().switchType();

    }

    public int getIndex(int row, int col){
        return board[row][col].element().getIndex();
    }

    //added this method
    //boolean Player Reference
    //black = 0
    //white = 0
    public boolean isPlayer(int row, int col, boolean player)
    {
        if (board[row][col].element().getPlayer() == player)
        {
            return true;
        }

        return false;
    }

    //added this method
    /*
    /int type
    /0 = flat tile
    1 = standing tile
    2 = capstone
    *///////////////////
    public boolean isType(int row, int col, int type)
    {
        if (board[row][col].element().getType() == type)
        {
            return true;
        }

        return false;
    }

    public ArrayDeque getStack(int row, int col){
        return board[row][col];
    }

    public void getSquare(int row, int col) {System.out.println(board[row][col]);}
    public int getXPos()
    {
        return 0;
    }
    public int getYPos()
    {
        return 0;
    }
    public int getZPos()
    {
        return 0;
    }


    public void addSelected()
    {

        getPlayerSquares();

    }

    public void getPlayerSquares()
    {
        GamePiece blackGamePiece = getBlackPiece();
        GamePiece whiteGamePiece = getWhitePiece();

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        if( (blackGamePiece != null) )
        {
            for (int i = 0; i < getDimensions(); i++)
            {
                for (int j = 0; j < getDimensions(); j++)
                {
                    if ((!board[i][j].isEmpty()) && (board[i][j].element().getPlayer() == blackGamePiece.getPlayer()))
                    {
                        findLeftConnectedSquares(i,j, blackGamePiece);
                        findRightConnectedSquares(i,j, blackGamePiece);
                        findUpConnectedSquares(i,j, blackGamePiece);
                        findDownConnectedSquares(i,j, blackGamePiece);
                        removeDuplicates();
                        completeGraph(blackPlayerSquares,false);
                        //System.out.println("Squares: row " + i + " col: " + j);

                    }
                }
            }
        }

        if( (whiteGamePiece != null) )
        {
            for (int i = 0; i < getDimensions(); i++)
            {
                for (int j = 0; j < getDimensions(); j++)
                {
                    if ((!board[i][j].isEmpty()) && (board[i][j].element().getPlayer() == whiteGamePiece.getPlayer()))
                    {
                        findLeftConnectedSquares(i,j, whiteGamePiece);
                        findRightConnectedSquares(i,j, whiteGamePiece);
                        findUpConnectedSquares(i,j, whiteGamePiece);
                        findDownConnectedSquares(i,j, whiteGamePiece);
                        removeDuplicates();
                        completeGraph(whitePlayerSquares,true);
                        //System.out.println("Squares: row " + i + " col: " + j);

                    }
                }
            }
        }


    }

    public boolean findLeftConnectedSquares(int row, int col, GamePiece player)
    {
        if( (col >= 1))
        {
            if ((!board[row][col - 1].isEmpty()) &&
                    (board[row][col - 1].element().getPlayer() == player.getPlayer()) &&
                    (board[row][col - 1].element().getType() == player.getType())
                    ) {

                //System.out.println("Left Connected");
                //System.out.println("row: " + (row) + " col: " + (col - 1));

                if(player.getPlayer() == false)
                {
                    blackPlayerSquares.add("" + row + "," + col);
                }
                else
                {
                    whitePlayerSquares.add("" + row + "," + col);
                }

                return true;
            }
        }

        return false;
    }

    public boolean findRightConnectedSquares(int row, int col, GamePiece player)
    {
        if( (col < getDimensions() - 2))
        {
            if ((!board[row][col + 1].isEmpty()) &&
                    (board[row][col + 1].element().getPlayer() == player.getPlayer()) &&
                    (board[row][col + 1].element().getType() == player.getType())
                    ) {

                //System.out.println("Right Connected");
                //System.out.println("row: " + (row) + " col: " + (col + 1));
                //blackPlayerSquares.add("" + row + "," +col);

                if(player.getPlayer() == false)
                {
                    blackPlayerSquares.add("" + row + "," + col);
                }
                else
                {
                    whitePlayerSquares.add("" + row + "," + col);
                }

                return true;
            }
        }

        return false;
    }

    public boolean findUpConnectedSquares(int row, int col, GamePiece player)
    {
        if( (row >= 1))
        {
            if ((!board[row - 1][col].isEmpty()) &&
                    (board[row - 1][col].element().getPlayer() == player.getPlayer()) &&
                    (board[row - 1][col].element().getType() == player.getType())
                    ) {

                //System.out.println("Up Connected");
                //System.out.println("Row: " + (row - 1) + "Col: " + (col));
                //blackPlayerSquares.add("" + row + "," +col);

                if(player.getPlayer() == false)
                {
                    blackPlayerSquares.add("" + row + "," + col);
                }
                else
                {
                    whitePlayerSquares.add("" + row + "," + col);
                }

                return true;
            }
        }

        return false;
    }

    public boolean findDownConnectedSquares(int row, int col, GamePiece player)
    {
        if( (row <= getDimensions() - 2))
        {
            if ((!board[row + 1][col].isEmpty()) &&
                    (board[row + 1][col].element().getPlayer() == player.getPlayer()) &&
                    (board[row + 1][col].element().getType() == player.getType())
                    ) {

                //System.out.println("Down Connected");
                //System.out.println("row: " + (row + 1) + "col: " + (col));
                blackPlayerSquares.add("" + row + "," +col);

                if(player.getPlayer() == false)
                {
                    blackPlayerSquares.add("" + row + "," + col);
                }
                else
                {
                    whitePlayerSquares.add("" + row + "," + col);
                }

                return true;
            }
        }

        return false;
    }

    public void removeDuplicates()
    {
        Set<String> set1=new HashSet<String>(blackPlayerSquares);
        blackPlayerSquares = new ArrayList<String>(set1);

        Collections.sort(blackPlayerSquares);

        Set<String> set2=new HashSet<String>(whitePlayerSquares);
        whitePlayerSquares = new ArrayList<String>(set2);

        Collections.sort(whitePlayerSquares);

    }

    public int convertXPositonToInt(String obj)
    {
        int value = Integer.parseInt( obj.substring(0,1));
        return value;
    }

    public int convertYPositonToInt(String obj)
    {
        int value = Integer.parseInt( obj.substring(obj.length() - 1 , obj.length()));
        return value;
    }

    public int topType (int row, int col)
    {
        if(board[row][col].isEmpty())
        {
            return  -1;
        }
        return board[row][col].getFirst().getType();

    }

    public boolean topPlayer (int row, int col)
    {
        if(board[row][col].isEmpty())
        {
            return  true;
        }
        return board[row][col].getFirst().getPlayer();

    }

    public void completeGraph(ArrayList<String> path,boolean player)
    {
        //only for 3x3 board
        //for demo

        //rows
        if( (getDimensions() == 3) )
        {
            //System.out.println("3x3 board test");

            for(int r=0; r <= 2; r++)
            {

                //check rows for white
                if( ((!board[r][0].isEmpty()) && (board[r][0].element().getPlayer()== false)) &&
                        ((!board[r][1].isEmpty()) && (board[r][1].element().getPlayer()== false)) &&
                        ((!board[r][2].isEmpty()) && (board[r][2].element().getPlayer()== false))
                        )
                {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new WinScreen(app,player));
                }

                //check rows for black
                if( ((!board[r][0].isEmpty()) && (board[r][0].element().getPlayer()== true)) &&
                        ((!board[r][1].isEmpty()) && (board[r][1].element().getPlayer()== true)) &&
                        ((!board[r][2].isEmpty()) && (board[r][2].element().getPlayer()== true))
                        )
                {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new WinScreen(app,player));
                }
            }

            for(int c=0; c <= 2; c++)
            {

                //check rows for white
                if( ((!board[0][c].isEmpty()) && (board[0][c].element().getPlayer()== false)) &&
                        ((!board[1][c].isEmpty()) && (board[1][c].element().getPlayer()== false)) &&
                        ((!board[2][c].isEmpty()) && (board[2][c].element().getPlayer()== false))
                        )
                {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new WinScreen(app,player));
                }

                //check rows for black
                if( ((!board[0][c].isEmpty()) && (board[0][c].element().getPlayer()== true)) &&
                        ((!board[1][c].isEmpty()) && (board[1][c].element().getPlayer()== true)) &&
                        ((!board[2][c].isEmpty()) && (board[2][c].element().getPlayer()== true))
                        )
                {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new WinScreen(app,player));
                }

            }



        }
        removeDuplicates();
        //ArrayList<String> path = blackPlayerSquares;


        System.out.println("xxxxxxxxxxxxxx");

        for(int i=0; i < path.size() - 1; i++)
        {

            int currentXPos = convertXPositonToInt(path.get(i));
            int currentYPos = convertYPositonToInt(path.get(i));
            int nextXPos = convertXPositonToInt(path.get(i + 1));
            int nextYPos = convertYPositonToInt(path.get(i + 1));
            /*
            if((nextXPos - currentXPos > 0) &&  (nextYPos - currentYPos > 1))

            {

             path.remove(i + 1);

            }

            */

            /*Comment out for demo
            if((path.size() >= getDimensions()-1 ) && (nextXPos == getDimensions()-1 ))
            {
                System.out.println();

                //System.out.println("Win");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new WinScreen(app,player));
            }



            if((path.size() >= getDimensions()-1 ) && (nextYPos == getDimensions()-1 ))
            {
                //System.out.println("Win");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new WinScreen(app,player));
            }
            */


        }
        for(int i=0; i<path.size(); i++)
        {
            System.out.println(path.get(i));
        }

    }

}
