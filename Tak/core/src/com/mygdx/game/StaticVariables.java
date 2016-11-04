package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Declear all static variables to be used in the program here that way we only have to update one variable instead of searching
 * all over the program to make changes ie screen size or screen width.
 */
public class StaticVariables
{
    public static String TITLE = "Travellers Tak";
    public static float VERSION = 1.0f;

    public static int V_WIDTH = 560;
    public static int V_HEIGHT = 340;

    public static Skin mainMenuSkins[] = new Skin[4];
    public static Skin gamePieceSkin = new Skin();

}
