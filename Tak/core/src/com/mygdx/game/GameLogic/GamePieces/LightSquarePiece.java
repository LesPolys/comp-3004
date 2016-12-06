package com.mygdx.game.GameLogic.GamePieces;

/**
 * Created by BenIII on 11/28/2016.
 */

public class LightSquarePiece extends GamePieces
{
    private static LightSquarePiece ourInstance = new LightSquarePiece();

    public static LightSquarePiece getInstance() {
        return ourInstance;
    }

    public LightSquarePiece()
    {
        setImage("lightSquarePiece","GamePiece/lightSquare1.png",0,60,50,50);
    }
}
