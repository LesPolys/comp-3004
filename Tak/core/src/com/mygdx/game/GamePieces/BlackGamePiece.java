package com.mygdx.game.GamePieces;

public class BlackGamePiece extends GamePieces
{
    private static BlackGamePiece ourInstance = new BlackGamePiece();
    public static BlackGamePiece getInstance() {return ourInstance;}

    public BlackGamePiece()
    {
        setImage("blackGamePiece","BlackGamePiece.png", 300,60,50,50);
    }
}
