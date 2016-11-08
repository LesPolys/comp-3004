package com.mygdx.game.GamePieces;

public class LightSquarePiece2 extends GamePieces
{
    private static LightSquarePiece2 ourInstance = new LightSquarePiece2();

    public static LightSquarePiece2 getInstance() {
        return ourInstance;
    }

    public LightSquarePiece2()
    {
        setImage("lightSquarePiece","GamePiece/lightSquare2.png",0,60,50,50);
    }
}
