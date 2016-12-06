package com.mygdx.game.GameLogic.GamePieces;


public class Capstone extends GamePieces
{
    private static Capstone ourInstance = new Capstone();

    public static Capstone getInstance() {
        return ourInstance;
    }

    public Capstone()
    {
        setImage("Capstone","Capstone.png",0,60,50,50);
    }
}
