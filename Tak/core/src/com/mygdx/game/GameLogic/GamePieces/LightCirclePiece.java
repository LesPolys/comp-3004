package com.mygdx.game.GameLogic.GamePieces;

public class LightCirclePiece extends GamePieces
{
    private static LightCirclePiece ourInstance = new LightCirclePiece();

    public static LightCirclePiece getInstance() {
        return ourInstance;
    }

    public LightCirclePiece()
    {
        setImage("lightCircle1","GamePiece/lightCircle1.png",0,60,50,50);
    }
}
