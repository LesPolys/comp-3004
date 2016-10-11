//package com.seg3125.project;
package com.mygdx.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


public class Declerations
{
    //initialize the items
    public static final TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
    public static final BitmapFont font = new BitmapFont();

    public static Skin mainMenuSkins[] = new Skin[4];
    public static TextButton.TextButtonStyle mainMenutextButtonStyles[];
    public static Skin playerSkin[];
    public static Skin pauseSkin[];


    public Declerations()
    {

    }

    //initialize the main menu items
    public void initMainMenu()
    {
        mainMenuSkins = new Skin[4];
        mainMenutextButtonStyles = new TextButton.TextButtonStyle[4];

        for(int i=0; i<4; i++)
        {
            mainMenuSkins[i] = new Skin();
            mainMenutextButtonStyles[i] = new TextButton.TextButtonStyle();
            mainMenutextButtonStyles[i].font = font;
        }

        mainMenuSkins[0].addRegions(buttonAtlas);
        mainMenuSkins[1].add("background", new Texture(Gdx.files.internal("mainMenu/Background2.png")));
        mainMenuSkins[2].add("title", new Texture(Gdx.files.internal("mainMenu/title.png")));
        mainMenuSkins[3].add("howToPlay", new Texture(Gdx.files.internal("mainMenu/howToPlay.png")));
    }

    //initialize the images for the playerScreen class
    public void initPlayerSkin()
    {
        playerSkin = new Skin[7];

        for(int i=0; i<7; i++)
        {
            playerSkin[i] = new Skin();
            playerSkin[i].addRegions(buttonAtlas);
        }

        playerSkin[0].add("blankStatus", new Texture("playerScreens/blankStatus.png"));
        playerSkin[1].add("player1", new Texture("playerScreens/Player1Turn.png"));
        playerSkin[2].add("player2", new Texture("playerScreens/Player2Turn.png"));
        playerSkin[3].add("player1Win", new Texture("playerScreens/player1Wins.png"));
        playerSkin[4].add("player2Win", new Texture("playerScreens/player2Wins.png"));
        playerSkin[5].add("playerRedWins", new Texture("playerScreens/playerRedWins.png"));
        playerSkin[6].add("playerBlueWins", new Texture("playerScreens/playerBlueWins.png"));
    }

    //initalize the pause screen items
    public void initPauseScreen()
    {
        pauseSkin = new Skin[5];

        for(int i=0; i<5; i++)
        {
            pauseSkin[i] = new Skin();
            pauseSkin[i].addRegions(buttonAtlas);
        }
        pauseSkin[4].add("pauseScreen", new Texture("pauseScreen/pauseScreen.png"));
    }
}
