package com.mygdx.game.Images;

import com.mygdx.game.StaticVariables;

public class Board6x6 extends GameBoardImages
{
    private static Board6x6 ourInstance = new Board6x6();

    public static Board6x6 getInstance() {
        return ourInstance;
    }

    public Board6x6()
    {
        setImage("gameBoard6x6", "GameBoard/6x6.png", (StaticVariables.V_WIDTH / 2) - 150
                , (StaticVariables.V_HEIGHT / 2) - 150,
                300,300);
    }
}
