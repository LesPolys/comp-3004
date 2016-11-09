package com.mygdx.game.GamePieces;

/**
 * Created by Logan on 11/8/2016.
 */

public class DarkCirclePiece1 extends GamePieces {
    private static DarkCirclePiece1 ourInstance = new DarkCirclePiece1();

    public static DarkCirclePiece1 getInstance() {
        return ourInstance;
    }

    public DarkCirclePiece1()
    {
        setImage("darkcircle1","GamePiece/darkCircle1.png",0,60,50,50);
    }
}
