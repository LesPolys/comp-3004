package com.mygdx.game.GameLogic;

/**
 * Created by BenIII on 11/30/2016.
 */

public class Sets
{
    private int array[][];
    public Sets()
    {
        /*
        array = new int[7][7];


        for(int row=0; row < 7; row ++)
        {
            for(int col =0; col < 7; col ++)
            {
                array[row][col] = 5;
            }
        }
        */
    }

    public void board3x3()
    {
        array = new int[3][3];

        for(int row=0; row < 3; row ++)
        {
            for(int col =0; col < 3; col ++)
            {
                array[row][col] = 5;
            }
        }
    }

    public void board4x4()
    {
        array = new int[4][4];

        for(int row=0; row < 4; row ++)
        {
            for(int col =0; col < 4; col ++)
            {
                array[row][col] = 5;
            }
        }

    }

    public void board5x5()
    {
        array = new int[5][5];

        for(int row=0; row < 5; row ++)
        {
            for(int col =0; col < 5; col ++)
            {
                array[row][col] = 5;
            }
        }

    }

    public void board6x6()
    {

    }

    public void board7x7()
    {

    }

}
