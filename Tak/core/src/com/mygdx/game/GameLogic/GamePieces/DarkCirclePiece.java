package com.mygdx.game.GameLogic.GamePieces;


public class DarkCirclePiece extends GamePieces
{
    private static DarkCirclePiece ourInstance = new DarkCirclePiece();

    public static DarkCirclePiece getInstance() {
        return ourInstance;
    }

    public DarkCirclePiece()
    {
        setImage("darkcircle1","GamePiece/darkCircle1.png",0,60,50,50);
    }
}
