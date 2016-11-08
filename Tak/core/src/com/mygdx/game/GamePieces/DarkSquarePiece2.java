package com.mygdx.game.GamePieces;


public class DarkSquarePiece2 extends GamePieces
{
    private static DarkSquarePiece2 ourInstance = new DarkSquarePiece2();

    public static DarkSquarePiece2 getInstance() {
        return ourInstance;
    }

    public DarkSquarePiece2()
    {
        setImage("darkSquare2","GamePiece/darkSquare2.png",0,60,50,50);
    }
}
