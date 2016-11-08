package com.mygdx.game.GamePieces;


public class LightTriangle2 extends GamePieces
{
    private static LightTriangle2 ourInstance = new LightTriangle2();

    public static LightTriangle2 getInstance() {
        return ourInstance;
    }

    public LightTriangle2()
    {
        setImage("lightTriangle","GamePiece/lightTriangle2.png",0,60,50,50);
    }
}
