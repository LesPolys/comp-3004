package com.mygdx.game.GamePieces;


public class DarkCirclePiece extends GamePieces
{
    private static DarkCirclePiece ourInstance = new DarkCirclePiece();

    public static DarkCirclePiece getInstance() {
        return ourInstance;
    }

    public DarkCirclePiece()
    {
        setImage("darkCriclePiece","GamePiece/darkCircle.png",0,60,50,50);
    }
}
