package com.mygdx.game.Images;

public class VerticalLine extends GameBoardImages
{
    private static VerticalLine ourInstance = new VerticalLine();

    public static VerticalLine getInstance() {
        return ourInstance;
    }

    public VerticalLine()
    {
        setImage("verticalLine","verticalLine.png", 0,0,150,10);
    }
}
