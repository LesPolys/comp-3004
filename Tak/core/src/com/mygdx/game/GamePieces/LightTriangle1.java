package com.mygdx.game.GamePieces;

public class LightTriangle1 extends GamePieces
{
    private static LightTriangle1 ourInstance = new LightTriangle1();

    public static LightTriangle1 getInstance() {
        return ourInstance;
    }

    public LightTriangle1()
    {
        setImage("lightTriangle","GamePiece/lightTriangle1.png",0,60,50,50);
    }
}
