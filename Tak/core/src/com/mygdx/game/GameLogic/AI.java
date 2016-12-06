package com.mygdx.game.GameLogic;

public class AI
{
    private int gameBoard[][];

    //0 is empty
    //1 is white
    //2 is black

    public AI()
    {
        gameBoard = new int[3][3];

        for(int r =0; r < gameBoard.length; r++)
        {
            for(int c=0; c <gameBoard.length; c++)
            {
                gameBoard[r][c] = 0;
            }
        }
    }

    public void addGameBoard(int row, int col)
    {
        gameBoard[row][col] = 1;

    }

    public boolean isSquareEmpty(int row, int col)
    {
        if((gameBoard[row][col] == 0))
        {
            return true;
        }

        return false;
    }
}
