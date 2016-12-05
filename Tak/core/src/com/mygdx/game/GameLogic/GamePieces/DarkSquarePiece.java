package com.mygdx.game.GameLogic.GamePieces;

/**
 * Created by BenIII on 11/28/2016.
 */

public class DarkSquarePiece extends GamePieces
{
    private static DarkSquarePiece ourInstance = new DarkSquarePiece();

    public static DarkSquarePiece getInstance()
    {
        return ourInstance;
    }

    public DarkSquarePiece()
    {
        setImage("darkSquare1","GamePiece/darkSquare1.png",0,60,50,50);
    }
}
