package com.mygdx.game.GamePieces;

public class WhiteCirclePiece extends GamePieces
{
    private static WhiteCirclePiece ourInstance = new WhiteCirclePiece();

    public static WhiteCirclePiece getInstance() {
        return ourInstance;
    }

    public WhiteCirclePiece()
    {
        setImage("WhiteCirclePiece","GamePiece/WhiteCirclePiece.png",0,60,50,50);
        //setImage("WhiteCirclePiece","GamePiece/WhiteCirclePiece.png",0,60,50,50);
    }
}
