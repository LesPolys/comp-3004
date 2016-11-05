package com.mygdx.game.GamePieces;

public class WhiteSquarePiece extends GamePieces
{
    private static WhiteSquarePiece ourInstance = new WhiteSquarePiece();

    public static WhiteSquarePiece getInstance() {
        return ourInstance;
    }

    public WhiteSquarePiece()
    {
        setImage("WhiteSquarePiece","GamePiece/WhiteSquarePiece.png",0,60,50,50);
    }
}
