package com.mygdx.game.Images;

import com.mygdx.game.StaticVariables;

public class Board4x4 extends GameBoardImages
{
    private static Board4x4 ourInstance = new Board4x4();

    public static Board4x4 getInstance() {
        return ourInstance;
    }

    public Board4x4()
    {
        setImage("gameBoard3x3", "GameBoard/4x4.png", (StaticVariables.V_WIDTH / 2) - 150
                , (StaticVariables.V_HEIGHT / 2) - 150,
                300,300);
    }
}
