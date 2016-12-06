package com.mygdx.game.GameLogic.GamePieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

//public abstract class GamePieces extends Image
public class GamePieces extends Image
{
    private Image gamePiece;
    private Skin gamePieceSkin;

    private int x;
    private int y;
    private int xSize;
    private int ySize;

    private String imageID;
    private String imageTexturePath;

    public GamePieces()
    {
        gamePiece = new Image();
        gamePieceSkin = new Skin();
    }

    public void setImage(String id, String texturePath, int xPos, int yPos, int newXSize, int newYSize)
    {

        int x = xPos;
        int y = yPos;
        int xSize = newXSize;
        int ySize = newYSize;

        imageID = id;
        imageTexturePath = texturePath;

        gamePieceSkin.add(imageID, new Texture( Gdx.files.internal(imageTexturePath)));
        gamePiece = new Image(gamePieceSkin, id);
        gamePiece.setBounds(x,y,xSize,ySize);
    }

    public Image getGamePiece()
    {
        return gamePiece;
    }
    public Skin getGamePieceSkin(){return gamePieceSkin;}

    public float getX(){return x; }
    public float getY(){return y;}
    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}

    public String getId(){return imageID;}
    public String getTexturePath(){return imageTexturePath;}
}
