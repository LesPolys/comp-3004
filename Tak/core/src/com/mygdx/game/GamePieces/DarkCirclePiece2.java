package com.mygdx.game.GamePieces;

/**
 * Created by Logan on 11/8/2016.
 */

public class DarkCirclePiece2 extends GamePieces {
    private static DarkCirclePiece2 ourInstance = new DarkCirclePiece2();

    public static DarkCirclePiece2 getInstance() {
        return ourInstance;
    }

    public DarkCirclePiece2()
    {
        setImage("darkcircle2","GamePiece/darkCircle2.png",0,60,50,50);
    }
}
