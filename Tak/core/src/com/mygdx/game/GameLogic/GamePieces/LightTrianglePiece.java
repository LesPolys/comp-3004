package com.mygdx.game.GameLogic.GamePieces;

/**
 * Created by BenIII on 11/28/2016.
 */

public class LightTrianglePiece extends GamePieces
{
    private static LightTrianglePiece ourInstance = new LightTrianglePiece();

    public static LightTrianglePiece getInstance() {
        return ourInstance;
    }

    public LightTrianglePiece()
    {
        setImage("lightTriangle","GamePiece/lightTriangle1.png",0,60,50,50);
    }
}
