package com.mygdx.game.GameLogic;

/**
 * Created by Logan on 11/8/2016.
 */

public class GamePiece {

    /*
    type
      0 = flat tile
      1 = standing tile
      2 = capstone
  */
    int type;

    // true = white, false = black
    boolean player;

    int x, y, z;

    int index;

    boolean covered;

    boolean inPlay;

    boolean selected;


    GamePiece(int startType, boolean setPlayer)
    {
        type = startType;
        player = setPlayer;
        inPlay = false;
    }
    GamePiece()
    {
        inPlay = false;
    }

    void changeType(int newType)
    {
        type = newType;
    }

    void setPosition(int newX, int newY, int newZ)
    {
        x = newX;
        y = newY;
        z = newZ;

        inPlay = true;
    }

    //functions that swaps piece to a wall (and back)
    void switchType()
    {
        if(type == 0)
        {
            type = 1;
        }
        else if(type == 1)
        {
            type = 0;
        }
    }


}
