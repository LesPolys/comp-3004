package com.mygdx.game.Images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class GameBoardImages extends Image
{
    private Image gameImage;
    private Skin gamePieceSkin;

    private int x;
    private int y;
    private int xSize;
    private int ySize;

    public GameBoardImages()
    {
        gameImage = new Image();
        gamePieceSkin = new Skin();
    }

    public void setImage(String id, String texturePath, int xPos, int yPos, int newXSize, int newYSize)
    {

        int x = xPos;
        int y = yPos;
        int xSize = newXSize;
        int ySize = newYSize;


        gamePieceSkin.add(id, new Texture( Gdx.files.internal(texturePath)));
        gameImage = new Image(gamePieceSkin, id);
        gameImage.setBounds(x,y,xSize,ySize);
    }

    public void setImage(String id, String texturePath)
    {
        int x = 0;
        int y = 0;
        int xSize = 0;
        int ySize = 0;

        gamePieceSkin.add(id, new Texture( Gdx.files.internal(texturePath)));
        gameImage = new Image(gamePieceSkin, id);
    }

    public Image getImage()
    {
        return gameImage;
    }
    public Skin getImageSkin(){return gamePieceSkin;}

    public float getX(){return x; }
    public float getY(){return y;}
    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}
}
