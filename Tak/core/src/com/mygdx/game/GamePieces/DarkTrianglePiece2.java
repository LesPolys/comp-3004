package com.mygdx.game.GamePieces;

public class DarkTrianglePiece2 extends GamePieces
{
    private static DarkTrianglePiece2 ourInstance = new DarkTrianglePiece2();

    public static DarkTrianglePiece2 getInstance() {
        return ourInstance;
    }

    public DarkTrianglePiece2()
    {
        setImage("darkTriangle","GamePiece/darkTriangle2.png",0,60,50,50);
    }
}
