package com.mygdx.game.Images;

import com.mygdx.game.StaticVariables;

public class Board8x8 extends GameBoardImages
{
    private static Board8x8 ourInstance = new Board8x8();

    public static Board8x8 getInstance() {
        return ourInstance;
    }

    public Board8x8()
    {
        setImage("gameBoard3x3", "GameBoard/8x8.png", (StaticVariables.V_WIDTH / 2) - 150
                , (StaticVariables.V_HEIGHT / 2) - 150,
                300,300);
    }
}