package com.mygdx.game.GamePieces;

/**
 * Created by Logan on 11/8/2016.
 */

public class LightCirclePiece2 extends GamePieces{
    private static LightCirclePiece2 ourInstance = new LightCirclePiece2();

    public static LightCirclePiece2 getInstance() {
        return ourInstance;
    }

    public LightCirclePiece2()
    {
        setImage("lightCircle2","GamePiece/lightCircle2.png",0,60,50,50);
    }
}
