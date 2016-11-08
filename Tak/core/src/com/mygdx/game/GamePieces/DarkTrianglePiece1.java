package com.mygdx.game.GamePieces;

public class DarkTrianglePiece1 extends GamePieces
{
    private static DarkTrianglePiece1 ourInstance = new DarkTrianglePiece1();

    public static DarkTrianglePiece1 getInstance() {
        return ourInstance;
    }

    public DarkTrianglePiece1()
    {
        setImage("darkTriangle","GamePiece/darkTriangle1.png",0,60,50,50);
    }
}
