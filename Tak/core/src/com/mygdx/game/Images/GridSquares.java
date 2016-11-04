package com.mygdx.game.Images;

import com.mygdx.game.GamePieces.GamePieces;
import com.mygdx.game.StaticVariables;

public class GridSquares extends GameBoardImages
{
    private static GridSquares ourInstance = new GridSquares();

    public static GridSquares getInstance() {
        return ourInstance;
    }

    private GridSquares()
    {
        setImage("guidlines","guideline.png", 400, 0, 50,50);
    }
}
