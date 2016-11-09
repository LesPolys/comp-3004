package com.mygdx.game.Images;

import com.mygdx.game.StaticVariables;

public class Board7x7 extends GameBoardImages
{
    private static Board7x7 ourInstance = new Board7x7();

    public static Board7x7 getInstance() {
        return ourInstance;
    }

    public Board7x7()
    {
        setImage("gameBoard3x3", "GameBoard/7x7.png", (StaticVariables.V_WIDTH / 2) - 150
                , (StaticVariables.V_HEIGHT / 2) - 150,
                300,300);
    }
}