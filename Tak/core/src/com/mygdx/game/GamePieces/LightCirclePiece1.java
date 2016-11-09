package com.mygdx.game.GamePieces;

/**
 * Created by Logan on 11/8/2016.
 */

public class LightCirclePiece1 extends GamePieces {
    private static LightCirclePiece1 ourInstance = new LightCirclePiece1();

    public static LightCirclePiece1 getInstance() {
        return ourInstance;
    }

    public LightCirclePiece1()
    {
        setImage("lightCircle1","GamePiece/lightCircle1.png",0,60,50,50);
    }

}
