//package com.seg3125.project;
package com.mygdx.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;


public class Declerations
{
    //initialize the items
    public static final TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
    public static final BitmapFont font = new BitmapFont();


    public static Skin mainMenuSkins[] = new Skin[8];
    public static TextButton.TextButtonStyle mainMenutextButtonStyles[];

    public static Skin versusSkin[] = new Skin[5];
    public static TextButton.TextButtonStyle versusMenutextButtonStyles[];

    public static Skin challengeSkin[] = new Skin[5];
    public static TextButton.TextButtonStyle challengeMenutextButtonStyles[];

    public static Skin playerSkin[];
    public static Skin pauseSkin[];





    public Declerations()
    {

    }

    //initialize the main menu items
    public void initMainMenu()
    {
        mainMenuSkins = new Skin[8];
        mainMenutextButtonStyles = new TextButton.TextButtonStyle[8];

        for(int i=0; i<8; i++)
        {
            mainMenuSkins[i] = new Skin();
            mainMenutextButtonStyles[i] = new TextButton.TextButtonStyle();
            mainMenutextButtonStyles[i].font = font;
        }

        mainMenuSkins[0].addRegions(buttonAtlas);
        mainMenuSkins[1].add("background", new Texture(Gdx.files.internal("mainMenu/Background2.png")));
        mainMenuSkins[2].add("title", new Texture(Gdx.files.internal("mainMenu/title.png")));
        mainMenuSkins[3].add("howToPlay", new Texture(Gdx.files.internal("mainMenu/howToPlay.png")));
        mainMenuSkins[4].add("versusButtonUnpressed", new Texture(Gdx.files.internal("versusButtonUnpressed.png")));
        mainMenuSkins[5].add("versusButtonPresed", new Texture(Gdx.files.internal("versusButtonPresed.png")));
        mainMenuSkins[6].add("challengeButtonUnpressed", new Texture(Gdx.files.internal("challengeButtonUnpressed.png")));
        mainMenuSkins[7].add("challengeButtonPresed", new Texture(Gdx.files.internal("challengeButtonPresed.png")));
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

    //initalize the challenge screen items
    public void initChallengeScreen()
    {
        challengeSkin = new Skin[5];
        challengeMenutextButtonStyles = new TextButton.TextButtonStyle[5];

        for(int i=0; i<5; i++)
        {
            challengeSkin[i] = new Skin();
            challengeMenutextButtonStyles[i] = new TextButton.TextButtonStyle();
            challengeMenutextButtonStyles[i].font = font;
        }

        challengeSkin[0].addRegions(buttonAtlas);
        challengeSkin[1].add("background", new Texture(Gdx.files.internal("mainMenu/Background2.png")));



    }

    //initalize the versus screen items
    public void initVersusScreen()
    {
        versusSkin = new Skin[5];
        versusMenutextButtonStyles = new TextButton.TextButtonStyle[5];

        for(int i=0; i<5; i++)
        {
            versusSkin[i] = new Skin();
            versusMenutextButtonStyles[i] = new TextButton.TextButtonStyle();
            versusMenutextButtonStyles[i].font = font;
        }

        versusSkin[0].addRegions(buttonAtlas);
        versusSkin[1].add("3x3Unpressed", new Texture(Gdx.files.internal("versusScreen/")));
        versusSkin[2].add("3x3Pressed", new Texture(Gdx.files.internal("versusScreen/")));
        versusSkin[3].add("4x4Unpressed", new Texture(Gdx.files.internal("versusScreen/")));
        versusSkin[4].add("4x4Pressed", new Texture(Gdx.files.internal("versusScreen/")));
        versusSkin[5].add("5x5Unpressed", new Texture(Gdx.files.internal("versusScreen/")));
        versusSkin[6].add("5x5Pressed", new Texture(Gdx.files.internal("versusScreen/")));
        versusSkin[7].add("6x6Unpressed", new Texture(Gdx.files.internal("versusScreen/")));
        versusSkin[8].add("6x6Pressed", new Texture(Gdx.files.internal("versusScreen/")));
        versusSkin[9].add("7x7Unpressed", new Texture(Gdx.files.internal("versusScreen/")));
        versusSkin[10].add("7x7Pressed", new Texture(Gdx.files.internal("versusScreen/")));
        versusSkin[11].add("8x8Unpressed", new Texture(Gdx.files.internal("versusScreen/")));
        versusSkin[12].add("8x8Pressed", new Texture(Gdx.files.internal("versusScreen/")));





    }

}
