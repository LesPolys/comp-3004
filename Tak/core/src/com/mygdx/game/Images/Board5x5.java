package com.mygdx.game.Images;

import com.mygdx.game.StaticVariables;

public class Board5x5 extends GameBoardImages
{
    private static Board5x5 ourInstance = new Board5x5();

    public static Board5x5 getInstance() {
        return ourInstance;
    }

    public Board5x5()
    {
        setImage("gameBoard3x3", "GameBoard/5x5.png", (StaticVariables.V_WIDTH / 2) - 150
                , (StaticVariables.V_HEIGHT / 2) - 150,
                300,300);
    }
}
