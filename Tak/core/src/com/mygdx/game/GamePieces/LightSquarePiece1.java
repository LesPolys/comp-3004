package com.mygdx.game.GamePieces;


public class LightSquarePiece1 extends GamePieces
{
    private static LightSquarePiece1 ourInstance = new LightSquarePiece1();

    public static LightSquarePiece1 getInstance() {
        return ourInstance;
    }

    public LightSquarePiece1()
    {
        setImage("lightSquarePiece","GamePiece/lightSquare1.png",0,60,50,50);
    }
}
