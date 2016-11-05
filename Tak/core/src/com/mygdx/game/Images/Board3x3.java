package com.mygdx.game.Images;

import com.mygdx.game.StaticVariables;

public class Board3x3 extends GameBoardImages
{
    private static Board3x3 ourInstance = new Board3x3();

    public static Board3x3 getInstance() {
        return ourInstance;
    }

    public Board3x3()
    {
        setImage("gameBoard3x3", "GameBoard/3x3.png", (StaticVariables.V_WIDTH / 2) - 150
                , (StaticVariables.V_HEIGHT / 2) - 150,
                300,300);
    }
}
