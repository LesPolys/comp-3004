package com.mygdx.game.GamePieces;

public class BlackCirclePiece extends GamePieces
{
    private static BlackCirclePiece ourInstance = new BlackCirclePiece();

    public static BlackCirclePiece getInstance() {
        return ourInstance;
    }

    public BlackCirclePiece()
    {
        setImage("BlackCriclePiece","GamePiece/BlackCirclePiece.png",0,60,50,50);
    }
}
