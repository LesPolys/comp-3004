package com.mygdx.game.GamePieces;


public class WhiteGamePiece extends GamePieces
{
    private static WhiteGamePiece ourInstance = new WhiteGamePiece();

    public static WhiteGamePiece getInstance() {
        return ourInstance;
    }

    private WhiteGamePiece()
    {
        setImage("whiteGamePiece","WhiteGamePiece.png",300,60,50,50);
    }
}
