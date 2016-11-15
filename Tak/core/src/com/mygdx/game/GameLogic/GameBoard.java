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
import java.util.Vector;

public class GameBoard
{
    int dimensions;

    ArrayList<String> blackPlayer;
    ArrayList<String> whitePlayer;

    //ArrayList<String> blackPlayerPath;
    //ArrayList<String> whitePlayerPath;

    ArrayList<String> blackPlayersPath;// = new ArrayList<String>();
    ArrayList<String> whitePlayersPath;

    ArrayList<String> validateBpPath;
    ArrayList<String> validateWpPath;
    // private ArrayDeque<GamePiece> stack;

    private  ArrayDeque<GamePiece> board[][];

    private int maxDepth;
    Tak app;

    //public GameBoard(int bSize)
    public GameBoard(int bSize, Tak obj)
    {
        dimensions = bSize;
        app = obj;

        blackPlayersPath = new ArrayList<String>();
        whitePlayersPath = new ArrayList<String>();

        validateBpPath = new ArrayList<String>();
        validateWpPath = new ArrayList<String>();

        blackPlayer = new ArrayList<String>();
        //blackPlayerPath  = new ArrayList<String>();

        whitePlayer = new ArrayList<String>();
        //blackPlayerPath  = new ArrayList<String>();

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
                //board[j][i] = null;
            }
        }

    }

    public int getDimensions(){return dimensions;}

    public void setSquare(int row, int col, boolean player, int type)
    //public void setSquare(int row, int col, String player, int type)
    {
        GamePiece tmpPiece = new GamePiece(type,player);
        board[row][col].push(tmpPiece);

        /* System.out.println("printing elements using iterator:");
        for(Iterator<GamePiece> itr = board[row][col].iterator(); itr.hasNext();)  {
            System.out.println(itr.next().type);
        }*/

    }

    public void popSquare(int row, int col){
        board[row][col].pop();
    }

    public boolean isEmpty(int row, int col)
    {
       /*
       if( board[row][col] != null){
           return false;
       }
       */

        //added the board is idealized to false by default
        if(board[row][col].isEmpty())
        {
            return true;
        }

        return false;
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
    /*
    public void outPut()
    {
        System.out.println("====================");
        for(int i = 0; i < dimensions; i++)
        {
            for(int j = 0; j < dimensions; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    */

    public void addSelected()
    {
        //output selection

        //System.out.println("test" + isType(0,0,0));

        for(int i = 0; i < dimensions; i++)
        {
            for (int j = 0; j < dimensions; j++)
            {
                if( (!(isEmpty(i,j))) &&
                        (isPlayer(i,j,false)) &&
                        (isType(i,j,0))
                        )
                {
                    //System.out.println("hit" + i + " " + j);
                    //xPos.add(i);
                    //yPos.add(j);

                    blackPlayer.add("" + i + "," + "" + j);
                }

                else if( (!(isEmpty(i,j))) &&
                        (isPlayer(i,j,false)) &&
                        (isType(i,j,1))
                        )
                {
                    //System.out.println("hit" + i + " " + j);
                    //xPos.add(i);
                    //yPos.add(j);

                    blackPlayer.add("" + i + "," + "" + j);
                }

                else if( (!(isEmpty(i,j))) &&
                        (isPlayer(i,j,false)) &&
                        (isType(i,j,2))
                        )
                {
                    //System.out.println("hit" + i + " " + j);
                    //xPos.add(i);
                    //yPos.add(j);

                    blackPlayer.add("" + i + "," + "" + j);
                }

                //white
                if( (!(isEmpty(i,j))) &&
                        (isPlayer(i,j,true)) &&
                        (isType(i,j,0))
                        )
                {
                    //System.out.println("hit" + i + " " + j);
                    //xPos.add(i);
                    //yPos.add(j);

                    whitePlayer.add("" + i + "," + "" + j);
                }
                else if( (!(isEmpty(i,j))) &&
                        (isPlayer(i,j,true)) &&
                        (isType(i,j,1))
                        )
                {
                    //System.out.println("hit" + i + " " + j);
                    //xPos.add(i);
                    //yPos.add(j);

                    whitePlayer.add("" + i + "," + "" + j);
                }
                if( (!(isEmpty(i,j))) &&
                        (isPlayer(i,j,true)) &&
                        (isType(i,j,2))
                        )
                {
                    //System.out.println("hit" + i + " " + j);
                    //xPos.add(i);
                    //yPos.add(j);

                    whitePlayer.add("" + i + "," + "" + j);
                }
                /*
                if (board[i][j].contains(check))
                {
                    System.out.println("dd" + i + " " + j);
                }
                */
                //System.out.println("dd" + board[i][j].contains(check));
            }
        }

        removeDuplicates();
        createPathBlackPlayer();
        createPathWhitePlayer();
    }

    public void removeDuplicates()
    {

        Set<String> set1=new HashSet<String>(blackPlayer);
        blackPlayer = new ArrayList<String>(set1);

        Set<String> set2=new HashSet<String>(whitePlayer);
        whitePlayer = new ArrayList<String>(set2);
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

    public void createPathBlackPlayer()
    {
        int currentX = 0;
        int currentY = 0;
        int previousX = 0;
        int previousY = 0;
        int nextX = 0;
        int nextY = 0;


        for(int i=0; i < blackPlayer.size(); i++)
        {
            if( (i >= 1))
            {
                currentX = convertXPositonToInt(blackPlayer.get(i));
                currentY = convertYPositonToInt(blackPlayer.get(i));

                previousX = convertXPositonToInt(blackPlayer.get(i - 1));
                previousY = convertYPositonToInt(blackPlayer.get(i - 1));

                //System.out.println("Curr " + currentX + " " + currentY);

                //CHECK ROWS
                if((currentX == previousX) && (currentY == (previousY + 1)))
                {
                    //System.out.println("hit" + previousX + "," + previousY);
                    //System.out.println("hit" + currentX + "," + currentY);

                    blackPlayersPath.add("" + previousX + "," + previousY);
                    blackPlayersPath.add("" + currentX + "," + currentY);

                    /*
                    pathX.add(previousX);
                    pathX.add(previousY);

                    pathY.add(currentX);
                    pathY.add(currentY);
                    */

                }
                if((currentX == previousX) && (currentY == (previousY - 1)))
                {
                    //System.out.println("hit" + previousX + "," + previousY);
                    //System.out.println("hit" + currentX + "," + currentY);

                    blackPlayersPath.add("" + previousX + "," + previousY);
                    blackPlayersPath.add("" + currentX + "," + currentY);

                    /*
                    pathX.add(previousX);
                    pathX.add(previousY);

                    pathY.add(currentX);
                    pathY.add(currentY);
                    */
                }

                //System.out.println("curX" + currentX + "curY" + currentY);
                ///added this
                if((currentX == (previousX + 1)) && (currentY == previousY ))
                {
                    //System.out.println("hit" + previousX + "," + previousY);
                    //System.out.println("hit" + currentX + "," + currentY);

                    blackPlayersPath.add("" + previousX + "," + previousY);
                    blackPlayersPath.add("" + currentX + "," + currentY);

                    /*
                    pathX.add(previousX);
                    pathX.add(previousY);

                    pathY.add(currentX);
                    pathY.add(currentY);
                    */
                }
                if((currentX == (previousX - 1)) && (currentY == previousY ))
                {
                    //System.out.println("hit" + previousX + "," + previousY);
                    //System.out.println("hit" + currentX + "," + currentY);

                    blackPlayersPath.add("" + previousX + "," + previousY);
                    blackPlayersPath.add("" + currentX + "," + currentY);

                    /*
                    pathX.add(previousX);
                    pathX.add(previousY);

                    pathY.add(currentX);
                    pathY.add(currentY);
                    */
                }
            }

            //check last row
            //System.out.println("cur" + currentX + ": " +currentY);
            //if( (blackPlayer.size() >= getDimensions() - 1))
            //{
            if((currentX == previousX) && ((currentY == (previousY - 1)) && (currentY == (getDimensions() - 1))))
            {
                System.out.println("end row");
                //System.out.println("hit" + previousX + "," + previousY);
                //System.out.println("hit" + currentX + "," + currentY);

                blackPlayersPath.add("" + previousX + "," + previousY);
                blackPlayersPath.add("" + currentX + "," + currentY);

                /*
                pathX.add(previousX);
                pathX.add(previousY);

                pathY.add(currentX);
                pathY.add(currentY);
                */
            }

            //System.out.println("p" + previousX + " c" + currentX);
            if( ((currentX == getDimensions() || (previousX == getDimensions() - 1)) && (currentY == previousY)))
            {
                System.out.println("end col");
                //System.out.println("hit" + previousX + "," + previousY);
                //System.out.println("hit" + currentX + "," + currentY);

                blackPlayersPath.add("" + previousX + "," + previousY);
                blackPlayersPath.add("" + currentX + "," + currentY);


            }
            //}


        }

        Set<String> set2=new HashSet<String>(blackPlayersPath);
        validateBpPath = new ArrayList<String>(set2);


        boolean start = false;
        boolean finish = false;
        System.out.println("xxxxx");

        Collections.sort(validateBpPath);
        for(int l=0; l<validateBpPath.size(); l++)
        {
            if(validateBpPath.get(l).contains("0"))
            {
                start = true;
            }


            if(validateBpPath.get(l).contains("" + (getDimensions() - 1)))
            {
                finish = true;
            }
            System.out.println(validateBpPath.get(l));
        }

        if(start && finish == true)
        {

            System.out.println("Path Complete");

            ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(app));
            //((Game) Gdx.app.getApplicationListener()).setScreen(new PauseScreen());
        }

    }

    public void createPathWhitePlayer()
    {
        int currentX = 0;
        int currentY = 0;
        int previousX = 0;
        int previousY = 0;
        int nextX = 0;
        int nextY = 0;


        for(int i=0; i < whitePlayer.size(); i++)
        {
            if( (i >= 1))
            {
                currentX = convertXPositonToInt(whitePlayer.get(i));
                currentY = convertYPositonToInt(whitePlayer.get(i));

                previousX = convertXPositonToInt(whitePlayer.get(i - 1));
                previousY = convertYPositonToInt(whitePlayer.get(i - 1));

                //System.out.println("Curr " + currentX + " " + currentY);

                //CHECK ROWS
                if((currentX == previousX) && (currentY == (previousY + 1)))
                {
                    //System.out.println("hit" + previousX + "," + previousY);
                    //System.out.println("hit" + currentX + "," + currentY);

                    whitePlayersPath.add("" + previousX + "," + previousY);
                    whitePlayersPath.add("" + currentX + "," + currentY);

                    /*
                    pathX.add(previousX);
                    pathX.add(previousY);

                    pathY.add(currentX);
                    pathY.add(currentY);
                    */

                }
                if((currentX == previousX) && (currentY == (previousY - 1)))
                {
                    //System.out.println("hit" + previousX + "," + previousY);
                    //System.out.println("hit" + currentX + "," + currentY);

                    whitePlayersPath.add("" + previousX + "," + previousY);
                    whitePlayersPath.add("" + currentX + "," + currentY);

                    /*
                    pathX.add(previousX);
                    pathX.add(previousY);

                    pathY.add(currentX);
                    pathY.add(currentY);
                    */
                }

                //System.out.println("curX" + currentX + "curY" + currentY);
                ///added this
                if((currentX == (previousX + 1)) && (currentY == previousY ))
                {
                    //System.out.println("hit" + previousX + "," + previousY);
                    //System.out.println("hit" + currentX + "," + currentY);

                    whitePlayersPath.add("" + previousX + "," + previousY);
                    whitePlayersPath.add("" + currentX + "," + currentY);

                    /*
                    pathX.add(previousX);
                    pathX.add(previousY);

                    pathY.add(currentX);
                    pathY.add(currentY);
                    */
                }
                if((currentX == (previousX - 1)) && (currentY == previousY ))
                {
                    //System.out.println("hit" + previousX + "," + previousY);
                    //System.out.println("hit" + currentX + "," + currentY);

                    whitePlayersPath.add("" + previousX + "," + previousY);
                    whitePlayersPath.add("" + currentX + "," + currentY);

                    /*
                    pathX.add(previousX);
                    pathX.add(previousY);

                    pathY.add(currentX);
                    pathY.add(currentY);
                    */
                }
            }

            //check last row
            //System.out.println("cur" + currentX + ": " +currentY);
            //if( (blackPlayer.size() >= getDimensions() - 1))
            //{
            if((currentX == previousX) && ((currentY == (previousY - 1)) && (currentY == (getDimensions() - 1))))
            {
                System.out.println("end row");
                //System.out.println("hit" + previousX + "," + previousY);
                //System.out.println("hit" + currentX + "," + currentY);

                whitePlayersPath.add("" + previousX + "," + previousY);
                whitePlayersPath.add("" + currentX + "," + currentY);

                /*
                pathX.add(previousX);
                pathX.add(previousY);

                pathY.add(currentX);
                pathY.add(currentY);
                */
            }

            //System.out.println("p" + previousX + " c" + currentX);
            if( ((currentX == getDimensions() || (previousX == getDimensions() - 1)) && (currentY == previousY)))
            {
                System.out.println("end col");
                //System.out.println("hit" + previousX + "," + previousY);
                //System.out.println("hit" + currentX + "," + currentY);

                whitePlayersPath.add("" + previousX + "," + previousY);
                whitePlayersPath.add("" + currentX + "," + currentY);


            }
            //}


        }

        System.out.println(whitePlayersPath.size());


        Set<String> set2=new HashSet<String>(whitePlayersPath);
        validateWpPath = new ArrayList<String>(set2);

        boolean start = false;
        boolean finish = false;
        System.out.println("xxxxx");

        Collections.sort(validateWpPath);
        for(int l=0; l<validateWpPath.size(); l++)
        {
            if(validateWpPath.get(l).contains("0"))
            {
                start = true;
            }


            if(validateWpPath.get(l).contains("" + (getDimensions() - 1)))
            {
                finish = true;
            }
            System.out.println(validateWpPath.get(l));
        }

        if(start && finish == true)
        {
            System.out.println("Path Complete");
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(app));
        }

    }


    public int topType (int row, int col){
        if(board[row][col].isEmpty()){
            return  -1;
        }
        return board[row][col].getFirst().getType();

    }

}
