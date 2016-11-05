package com.mygdx.game.GamePieces;

public class BlackSquarePiece extends GamePieces
{
    private static BlackSquarePiece ourInstance = new BlackSquarePiece();

    public static BlackSquarePiece getInstance() {
        return ourInstance;
    }

    public BlackSquarePiece()
    {
        setImage("BlackSqaurePiece","GamePiece/BlackSquarePiece.png",0,60,50,50);
    }
}
