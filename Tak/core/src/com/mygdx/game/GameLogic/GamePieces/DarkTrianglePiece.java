package com.mygdx.game.GameLogic.GamePieces;

/**
 * Created by BenIII on 11/28/2016.
 */

public class DarkTrianglePiece extends GamePieces
{
    private static DarkTrianglePiece ourInstance = new DarkTrianglePiece();

    public static DarkTrianglePiece getInstance() {
        return ourInstance;
    }

    public DarkTrianglePiece()
    {
        setImage("darkTriangle","GamePiece/darkTriangle1.png",0,60,50,50);
    }
}
