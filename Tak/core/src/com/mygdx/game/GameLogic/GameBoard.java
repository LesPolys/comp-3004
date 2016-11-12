package com.mygdx.game.GameLogic;

import java.util.ArrayList;

public class GameBoard
{
    int dimensions;


    ArrayList<Integer> xPos;
    ArrayList <Integer> yPos;
    ArrayList <Integer> zPos;
    ArrayList<Integer> pathX;
    ArrayList<Integer> pathY;

    private int board[][][];
    private int maxDepth;
    public GameBoard(int bSize)
    {
        dimensions = bSize;
        xPos = new ArrayList();
        yPos = new ArrayList();
        zPos = new ArrayList();

        pathX = new ArrayList();
        pathY = new ArrayList();

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

        board = new int[dimensions][dimensions][maxDepth];

        for(int i = 0; i < dimensions; i++)
        {
            for(int j = 0; j < dimensions; j++)
            {
                for (int k = 0; k <maxDepth; k++) {
                    board[j][i][k]=0;
                }
            }
        }

    }

    public void setSquare(int row, int col) {board[row][col][0] = 1;}
    public void getSquare(int row, int col, int depth) {System.out.println(board[row][col][depth]);}

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

    public void output()
    {
        //System.out.println(xPos.size());

        //output selection
        /*
        for(int i=0; i < board.length; i++)
        {
            for(int j=0; j < board.length; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

        System.out.println("=======================");
        for(int i =0; i < xPos.size(); i++)
        {
            System.out.println("x:" + (xPos.get(i) + " y:" + yPos.get(i)));
        }
        */
        System.out.println("=======================");

        for(int i =0; i < (pathX.size()); i++)
        {
            System.out.println("x:" + (pathX.get(i) + " y:" + pathY.get(i)));
        }

    }

    public void check()
    {

        for(int i=0; i < board.length; i++)
        {
            for(int j=0; j < board.length; j++)
            {
                checkForConnectedSquares(i, j, 0);
            }
        }
    }

    public void checkForConnectedSquares(int x, int y, int player)
    {
        //System.out.println(" " + x + " " + y);

        if( ( (board[1])[0][0] == 1) && ( (board[1])[1][0] == 1))
        {
            System.out.println("test");
        }

        if( (x >= 1 ) && (x < board.length - 1))
        {
            if( (board[x + 1][y][0]) == 1)
            {
                //System.out.println("X: " + (x + 1) + "Y: " + y);
                xPos.add(x + 1);
                yPos.add(y);
            }

            if( (board[x - 1][y][0]) == 1)
            {
                //System.out.println("X: " + ( x - 1) + "Y: " + y);
                xPos.add(x - 1);
                yPos.add(y);
            }
        }

        if( (y > 0 ) && (y < board.length - 1))
        {
            if( (board[x][y + 1][0]) == 1)
            {
                //System.out.println("X: " + x + "Y: " + (y + 1));
                xPos.add(x);
                yPos.add(y + 1);
            }

            if( (board[x][y - 1][0]) == 1)
            {
                //System.out.println("X: " + x + "Y: " + (y - 1));
                xPos.add(x);
                yPos.add(y - 1);
            }
        }
    }


    public void checkleftColumn( int SIZE)
    {
        for(int i =0; i < SIZE; i++)
        {
            if((pathY.get(i) == pathY.get(i)) &&
            (pathX.get(i) == (pathX.get(i) + 1)))
            {
                System.out.println("hit");
            }
        }
    }


    public boolean checkStartAndEnd(int startX, int startY, int endX, int endY)
    {
        boolean start = false;
        boolean finish = false;
        boolean result = false;

        for(int i=0; i < pathX.size(); i++)
        {
            //up and down
            //System.out.println(xPos.get(i));
            if((pathX.get(i)== startX + 1) && (pathY.get(i)==startY))
            {
                //System.out.println(" " + (pathX.get(i) - 1) + ": " + pathY.get(i));
                start = true;
            }

            if( ((pathX.get(i) + 1) == endX ) && (pathY.get(i)==endY))
            {
                //System.out.println(" " + (pathX.get(i) - 1) + ": " + pathY.get(i));
                finish = true;
            }

            //left to right
            if((pathX.get(i)== startX) && (pathY.get(i)==startY  + 1))
            {
                //System.out.println(" " + (pathX.get(i) - 1) + ": " + pathY.get(i));
                start = true;
            }

            if( (pathX.get(i) == endX ) && ( (pathY.get(i)+ 1)== endY))
            {
                //System.out.println(" " + (pathX.get(i) - 1) + ": " + pathY.get(i));
                finish = true;
            }

        }

        //System.out.println(start + "" + finish);
        if((!start == false) && (!finish == false)){ result = true;}

        return result;
        //return start;
    }

    public void findPath()
    {
        for(int i=0; i < xPos.size(); i++)
        {
            if( (i >= 1) & (i < xPos.size() - 1))
            {
                int currentX = xPos.get(i);
                int currentY = yPos.get(i);

                int nextX = xPos.get(i + 1);
                int nextY = yPos.get(i + 1);

                //up
                if( (currentX == nextX) && (currentY == (nextY + 1)) )
                {
                    //System.out.println(currentX + ": " + currentY);
                    pathX.add(currentX);
                    pathY.add(currentY);
                }

                //down
                if( (currentX == nextX) && (currentY == (nextY - 1)) )
                {
                    //System.out.println(currentX + ": " + currentY);
                    pathX.add(currentX);
                    pathY.add(currentY);
                }

                //right
                if( (currentX == (nextX + 1)) && (currentY == nextY) )
                {
                    //System.out.println(currentX + ": " + currentY);
                    pathX.add(currentX);
                    pathY.add(currentY);
                }

                //left
                if( (currentX == (nextX - 1)) && (currentY == nextY) )
                {
                    //System.out.println(currentX + ": " + currentY);
                    pathX.add(currentX);
                    pathY.add(currentY);
                }

            }

        }

		/*
		System.out.println("=======================");

		for(int i =0; i < pathX.size(); i++)
		{
			System.out.println("x:" + (pathX.get(i) + " y:" + pathY.get(i)));
		}
		*/
    }
}
