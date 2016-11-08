package com.mygdx.game.GamePieces;

public class DarkSquarePiece1 extends GamePieces
{
    private static DarkSquarePiece1 ourInstance = new DarkSquarePiece1();

    public static DarkSquarePiece1 getInstance() {
        return ourInstance;
    }

    public DarkSquarePiece1()
    {
        setImage("darkSquare1","GamePiece/darkSquare1.png",0,60,50,50);
    }
}
