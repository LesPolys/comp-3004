package com.mygdx.game.GamePieces;

public class LightCirclePiece extends GamePieces
{
    private static LightCirclePiece ourInstance = new LightCirclePiece();

    public static LightCirclePiece getInstance() {
        return ourInstance;
    }

    public LightCirclePiece()
    {
        setImage("lightCirclePiece","GamePiece/lightCircle.png",0,60,50,50);
    }
}
