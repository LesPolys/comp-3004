package com.mygdx.game.Images;

public class SetImage extends GameBoardImages
{
    private static SetImage ourInstance = new SetImage();

    public static SetImage getInstance() {
        return ourInstance;
    }

    private SetImage()
    {
        setImage("horizontalLine","horizontalLine.png", 0,0,0,300);
    }
}
