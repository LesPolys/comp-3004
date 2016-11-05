package com.mygdx.game.GamePieces;

public class BlackOctogonPiece extends GamePieces
{
    private static BlackOctogonPiece ourInstance = new BlackOctogonPiece();

    public static BlackOctogonPiece getInstance() {
        return ourInstance;
    }

    public BlackOctogonPiece()
    {
        setImage("BlackOctogonPiece","GamePiece/BlackOctogonPiece.png",0,60,50,50);
    }
}
