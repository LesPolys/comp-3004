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

    public static Skin versusAISkin[] = new Skin[5];
    public static TextButton.TextButtonStyle versusAIMenutextButtonStyles[];

    public static Skin challengeSkin[] = new Skin[5];
    public static TextButton.TextButtonStyle challengeMenutextButtonStyles[];

    public static Skin playerSkin[];
    public static Skin pauseSkin[];
    public static Skin stackSkin[];




    public Declerations()
    {

    }

    //initialize the main menu items
    public void initMainMenu()
    {
        mainMenuSkins = new Skin[10];
        mainMenutextButtonStyles = new TextButton.TextButtonStyle[10];

        for(int i=0; i<10; i++)
        {
            mainMenuSkins[i] = new Skin();
            mainMenutextButtonStyles[i] = new TextButton.TextButtonStyle();
            mainMenutextButtonStyles[i].font = font;
        }

        mainMenuSkins[0].addRegions(buttonAtlas);
        mainMenuSkins[1].add("background", new Texture(Gdx.files.internal("mainMenu/Background2.png")));
        mainMenuSkins[2].add("title", new Texture(Gdx.files.internal("mainMenu/titleCard.png")));
        mainMenuSkins[3].add("howToPlay", new Texture(Gdx.files.internal("mainMenu/howToPlay.png")));
        mainMenuSkins[4].add("versusButtonUnpressed", new Texture(Gdx.files.internal("versusButtonUnpressed.png")));
        mainMenuSkins[5].add("versusButtonPresed", new Texture(Gdx.files.internal("versusButtonPresed.png")));
        mainMenuSkins[6].add("challengeButtonUnpressed", new Texture(Gdx.files.internal("challengeButtonUnpressed.png")));
        mainMenuSkins[7].add("challengeButtonPresed", new Texture(Gdx.files.internal("challengeButtonPresed.png")));
        mainMenuSkins[8].add("versusAIButtonUnpressed", new Texture(Gdx.files.internal("versusAIButtonUnpressed.png")));
        mainMenuSkins[9].add("versusAIButtonPresed", new Texture(Gdx.files.internal("versusAIButtonPresed.png")));
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
        pauseSkin = new Skin[6];

        for(int i=0; i<=5; i++)
        {
            pauseSkin[i] = new Skin();

        }


        pauseSkin[0].addRegions(buttonAtlas);
        pauseSkin[1].add("backButtonUnpressed", new Texture("pauseScreen/backButtonUnpressed.png"));
        pauseSkin[2].add("backButtonPresed", new Texture("pauseScreen/backButtonPresed.png"));
        pauseSkin[3].add("quitButtonUnpressed", new Texture("pauseScreen/quitButtonUnpressed.png"));
        pauseSkin[4].add("quitButtonPresed", new Texture("pauseScreen/quitButtonPresed.png"));
        pauseSkin[5].add("pauseScreen", new Texture("pauseScreen/pauseScreen.png"));
    }

    public void initStackScreen()
    {
        stackSkin = new Skin[15];

        for(int i=0; i<15; i++)
        {
            stackSkin[i] = new Skin();

        }

        stackSkin[0].add("selectBackground", new Texture("stackMenu/selectBackground.png"));//background

        stackSkin[1].add("roadUnselected", new Texture("stackMenu/roadUnselected.png"));//road unselected
        stackSkin[2].add("roadSelected", new Texture("stackMenu/roadSelected.png"));//road selected

        stackSkin[3].add("wallUnselected", new Texture("stackMenu/wallUnselected.png"));//wall unselected
        stackSkin[4].add("wallSelected", new Texture("stackMenu/wallSelected.png"));//wall selected

        stackSkin[5].add("capUnselected", new Texture("stackMenu/capUnselected.png"));//capstone unselected
        stackSkin[6].add("capSelected", new Texture("stackMenu/capSelected.png"));//capstone selected

        stackSkin[7].add("upButtonPressed", new Texture("stackMenu/upButtonPressed.png"));//upButton UnPressed
        stackSkin[8].add("upButtonUnpressed", new Texture("stackMenu/upButtonUnpressed.png"));//upButton Pressed

        stackSkin[9].add("downButtonUnpressed", new Texture("stackMenu/downButtonUnpressed.png"));//downButton UnPressed
        stackSkin[10].add("downButtonPressed", new Texture("stackMenu/downButtonPressed.png"));//downButton Pressed

        stackSkin[11].add("selectButtonUnpressed", new Texture("stackMenu/selectButtonUnpressed.png"));//selectButton UnPressed
        stackSkin[12].add("selectButtonPressed", new Texture("stackMenu/selectButtonPressed.png"));//selectButton Pressed

        stackSkin[13].add("backButtonUnpressed", new Texture("stackMenu/backButtonUnpressed.png"));//backButton UnPressed
        stackSkin[14].add("backButtonPresed", new Texture("stackMenu/backButtonPresed.png"));//backButton Pressed



    }

    //initalize the challenge screen items
    public void initChallengeScreen()
    {
        challengeSkin = new Skin[20];
        challengeMenutextButtonStyles = new TextButton.TextButtonStyle[20];

        for(int i=0; i<=19; i++)
        {
            challengeSkin[i] = new Skin();
            challengeMenutextButtonStyles[i] = new TextButton.TextButtonStyle();
            challengeMenutextButtonStyles[i].font = font;
        }

        challengeSkin[0].addRegions(buttonAtlas);
        challengeSkin[1].add("background", new Texture(Gdx.files.internal("mainMenu/Background2.png"))); //back
        challengeSkin[2].add("titleChallenge", new Texture(Gdx.files.internal("challengeScreen/titleChallenge.png")));//title
        challengeSkin[3].add("challengeMessage", new Texture(Gdx.files.internal("challengeScreen/challengeMessage.png")));//message

        challengeSkin[4].add("challengeUnpressedOne", new Texture(Gdx.files.internal("challengeScreen/challengeUnpressedOne.png")));//ch1-6 P and U
        challengeSkin[5].add("challengePressedOne", new Texture(Gdx.files.internal("challengeScreen/challengePressedButtonOne.png")));
        challengeSkin[6].add("challengeUnpressedTwo", new Texture(Gdx.files.internal("challengeScreen/challengeUnpressedTwo.png")));
        challengeSkin[7].add("challengePressedTwo", new Texture(Gdx.files.internal("challengeScreen/challengePressedButtonTwo.png")));
        challengeSkin[8].add("challengeUnpressedThree", new Texture(Gdx.files.internal("challengeScreen/challengeUnpressedThree.png")));
        challengeSkin[9].add("challengePressedThree", new Texture(Gdx.files.internal("challengeScreen/challengePressedButtonThree.png")));
        challengeSkin[10].add("challengeUnpressedFour", new Texture(Gdx.files.internal("challengeScreen/challengeUnpressedFour.png")));
        challengeSkin[11].add("challengePressedFour", new Texture(Gdx.files.internal("challengeScreen/challengePressedButtonFour.png")));
        challengeSkin[12].add("challengeUnpressedFive", new Texture(Gdx.files.internal("challengeScreen/challengeUnpressedFive.png")));
        challengeSkin[13].add("challengePressedFive", new Texture(Gdx.files.internal("challengeScreen/challengePressedButtonFive.png")));
        challengeSkin[14].add("challengeUnpressedSix", new Texture(Gdx.files.internal("challengeScreen/challengeUnpressedSix.png")));
        challengeSkin[15].add("challengePressedSix", new Texture(Gdx.files.internal("challengeScreen/challengePressedButtonSix.png")));

        challengeSkin[16].add("playUnpressed", new Texture(Gdx.files.internal("challengeScreen/playUnpressed.png")));//Play P and U
        challengeSkin[17].add("playButtonPresed", new Texture(Gdx.files.internal("challengeScreen/playButtonPresed.png")));
        challengeSkin[18].add("backButtonUnPressed", new Texture(Gdx.files.internal("challengeScreen/backButtonUnpressed.png")));//back P and U
        challengeSkin[19].add("backButtonPresed", new Texture(Gdx.files.internal("challengeScreen/backButtonPresed.png")));




    }

    //initalize the versus screen items
    public void initVersusScreen()
    {
        versusSkin = new Skin[20];
        versusMenutextButtonStyles = new TextButton.TextButtonStyle[20];

        for(int i=0; i<=19; i++)
        {
            versusSkin[i] = new Skin();
            versusMenutextButtonStyles[i] = new TextButton.TextButtonStyle();
            versusMenutextButtonStyles[i].font = font;
        }

        versusSkin[0].addRegions(buttonAtlas);
        versusSkin[1].add("3x3Unpressed", new Texture(Gdx.files.internal("versusScreen/3x3Button.png")));
        versusSkin[2].add("3x3Pressed", new Texture(Gdx.files.internal("versusScreen/3x3PressedButton.png")));
        versusSkin[3].add("4x4Unpressed", new Texture(Gdx.files.internal("versusScreen/4x4Button.png")));
        versusSkin[4].add("4x4Pressed", new Texture(Gdx.files.internal("versusScreen/4x4PressedButton.png")));
        versusSkin[5].add("5x5Unpressed", new Texture(Gdx.files.internal("versusScreen/5x5Button.png")));
        versusSkin[6].add("5x5Pressed", new Texture(Gdx.files.internal("versusScreen/5x5PressedButton.png")));
        versusSkin[7].add("6x6Unpressed", new Texture(Gdx.files.internal("versusScreen/6x6Button.png")));
        versusSkin[8].add("6x6Pressed", new Texture(Gdx.files.internal("versusScreen/6x6PressedButton.png")));
        versusSkin[9].add("7x7Unpressed", new Texture(Gdx.files.internal("versusScreen/7x7Button.png")));
        versusSkin[10].add("7x7Pressed", new Texture(Gdx.files.internal("versusScreen/7x7PressedButton.png")));
        versusSkin[11].add("8x8Unpressed", new Texture(Gdx.files.internal("versusScreen/8x8Button.png")));
        versusSkin[12].add("8x8Pressed", new Texture(Gdx.files.internal("versusScreen/8x8PressedButton.png")));

        versusSkin[13].add("playUnpressed", new Texture(Gdx.files.internal("versusScreen/playUnpressed.png")));
        versusSkin[14].add("playButtonPresed", new Texture(Gdx.files.internal("versusScreen/playButtonPresed.png")));
        versusSkin[15].add("backButtonUnpressed", new Texture(Gdx.files.internal("versusScreen/backButtonUnpressed.png")));
        versusSkin[16].add("backButtonPresed", new Texture(Gdx.files.internal("versusScreen/backButtonPresed.png")));
        versusSkin[17].add("background", new Texture(Gdx.files.internal("mainMenu/Background2.png")));
        versusSkin[18].add("versusTitle", new Texture(Gdx.files.internal("versusScreen/titleVersus.png")));
        versusSkin[19].add("versusMessage", new Texture(Gdx.files.internal("versusScreen/versusMessage.png")));

    }

    public void initVersusAIScreen()
    {
        versusAISkin = new Skin[20];
        versusAIMenutextButtonStyles = new TextButton.TextButtonStyle[20];

        for(int i=0; i<=19; i++)
        {
            versusAISkin[i] = new Skin();
            versusAIMenutextButtonStyles[i] = new TextButton.TextButtonStyle();
            versusAIMenutextButtonStyles[i].font = font;
        }

        versusAISkin[0].addRegions(buttonAtlas);
        versusAISkin[1].add("3x3Unpressed", new Texture(Gdx.files.internal("versusScreen/3x3Button.png")));
        versusAISkin[2].add("3x3Pressed", new Texture(Gdx.files.internal("versusScreen/3x3PressedButton.png")));
        versusAISkin[3].add("4x4Unpressed", new Texture(Gdx.files.internal("versusScreen/4x4Button.png")));
        versusAISkin[4].add("4x4Pressed", new Texture(Gdx.files.internal("versusScreen/4x4PressedButton.png")));
        versusAISkin[5].add("5x5Unpressed", new Texture(Gdx.files.internal("versusScreen/5x5Button.png")));
        versusAISkin[6].add("5x5Pressed", new Texture(Gdx.files.internal("versusScreen/5x5PressedButton.png")));
        versusAISkin[7].add("6x6Unpressed", new Texture(Gdx.files.internal("versusScreen/6x6Button.png")));
        versusAISkin[8].add("6x6Pressed", new Texture(Gdx.files.internal("versusScreen/6x6PressedButton.png")));
        versusAISkin[9].add("7x7Unpressed", new Texture(Gdx.files.internal("versusScreen/7x7Button.png")));
        versusAISkin[10].add("7x7Pressed", new Texture(Gdx.files.internal("versusScreen/7x7PressedButton.png")));
        versusAISkin[11].add("8x8Unpressed", new Texture(Gdx.files.internal("versusScreen/8x8Button.png")));
        versusAISkin[12].add("8x8Pressed", new Texture(Gdx.files.internal("versusScreen/8x8PressedButton.png")));
        versusAISkin[13].add("playUnpressed", new Texture(Gdx.files.internal("versusScreen/playUnpressed.png")));
        versusAISkin[14].add("playButtonPresed", new Texture(Gdx.files.internal("versusScreen/playButtonPresed.png")));
        versusAISkin[15].add("backButtonUnpressed", new Texture(Gdx.files.internal("versusScreen/backButtonUnpressed.png")));
        versusAISkin[16].add("backButtonPresed", new Texture(Gdx.files.internal("versusScreen/backButtonPresed.png")));
        versusAISkin[17].add("background", new Texture(Gdx.files.internal("mainMenu/Background2.png")));
        versusAISkin[18].add("versusAITitle", new Texture(Gdx.files.internal("versusAI/titleAI.png")));
        versusAISkin[19].add("versusMessage", new Texture(Gdx.files.internal("versusScreen/versusMessage.png")));

    }

}
