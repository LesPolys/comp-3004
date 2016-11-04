package com.mygdx.game.Images;


import com.mygdx.game.StaticVariables;

public class BackgroundImage extends GameBoardImages
{
    private static BackgroundImage ourInstance = new BackgroundImage();

    public static BackgroundImage getInstance() {
        return ourInstance;
    }

    private BackgroundImage()
    {
        setImage("greenBackground", "textures/felt2.png", 0, 0, StaticVariables.V_WIDTH, StaticVariables.V_HEIGHT);
    }
}
