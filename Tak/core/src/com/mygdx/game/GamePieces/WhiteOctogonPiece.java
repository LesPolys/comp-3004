package com.mygdx.game.GamePieces;

public class WhiteOctogonPiece extends GamePieces
{
    private static WhiteOctogonPiece ourInstance = new WhiteOctogonPiece();

    public static WhiteOctogonPiece getInstance() {
        return ourInstance;
    }

    public WhiteOctogonPiece()
    {
        setImage("WhiteOctogonPiece","GamePiece/WhiteOctogonPiece.png",0,60,50,50);
    }
}
