package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.mygdx.game.SampleGame;
import com.mygdx.util.Declerations;
import com.mygdx.util.GameState;

import com.mygdx.objects.GamePiece;
import com.mygdx.objects.GamePiece.Player;
import com.mygdx.objects.GamePiece.Size;
import com.mygdx.objects.Grid;

/*
import com.seg3125.project.Declerations;
import com.seg3125.project.GameApp;
import com.seg3125.project.GameState;
import com.seg3125.project.objects.GamePiece;
import com.seg3125.project.objects.GamePiece.Player;
import com.seg3125.project.objects.GamePiece.Size;
import com.seg3125.project.objects.Grid;
*/

public class PlayScreen implements Screen
{
    private final int PIECES_PER_SIZE = 4;
    private SampleGame app;
    private Image bg;

    //public vars, needed by other Classes
    public Stage stage;
    public Skin skin;
    public Grid grid;
    public GameState gameState;
    public GameState.Square gameSquare;
    private Image playerImage[];

    //calls the PauseScreen and declerations class
    private PauseScreen pauseScreen;
    private StackScreen stackScreen;
    private Declerations declerations;

    public PlayScreen(SampleGame gameApp)
    {
        app = gameApp;
        Viewport viewport = new FillViewport(SampleGame.V_WIDTH, SampleGame.V_HEIGHT, gameApp.camera);
        stage = new Stage(viewport, app.batch);

        initSkin();

        //set background
        bg = new Image(skin, "felt");
        bg.setBounds(0, 0, SampleGame.V_WIDTH, SampleGame.V_HEIGHT);
        stage.addActor(bg);

        //init grid
        grid = new Grid(this, 200);

        //init game pieces
        for (Size sz : Size.values())
        {
            initPieces(Player.BLUE, sz, PIECES_PER_SIZE);
            initPieces(Player.RED, sz, PIECES_PER_SIZE);
        }

        //initialize the gameState which keeps track of the logic
        gameState = new GameState();

        //make stage handle inputs
        Gdx.input.setInputProcessor(stage);


        //initialize the pause screen
        pauseScreen = new PauseScreen(app,stage);
        pauseScreen.returnToMainMenu();
        pauseScreen.returnToGame();

        stackScreen = new StackScreen(app,stage);
        stackScreen.initStack();
        stackScreen.selectionOptions();
        stackScreen.select();
        stackScreen.returnToGame();





        //initialize the images
        playerImage = new Image[7];
        for(int i=0; i<6; i++) {playerImage[i] = new Image();};

        //initialize the declearation object
        declerations = new Declerations();
        declerations.initPlayerSkin();

        //set the images
        playerImage[0] = new Image(declerations.playerSkin[0],"blankStatus");
        playerImage[1] = new Image(declerations.playerSkin[1],"player1");
        playerImage[2] = new Image(declerations.playerSkin[2],"player2");
        playerImage[3] = new Image(declerations.playerSkin[3],"player1Win");
        playerImage[4] = new Image(declerations.playerSkin[4] ,"player2Win");
        playerImage[5] = new Image(declerations.playerSkin[5],"playerRedWins");
        playerImage[6] = new Image(declerations.playerSkin[6],"playerBlueWins");
       // stage.addActor(playerImage[1]);

        for(int i=0; i<5; i++)
        {
            playerImage[i].setSize((SampleGame.V_WIDTH - 150), (SampleGame.V_HEIGHT - 150));
            playerImage[i].setBounds(15,0,50,200);
        };

        for(int i=5; i<=6; i++)
        {
            playerImage[i].setSize(SampleGame.V_WIDTH,SampleGame.V_HEIGHT);
            playerImage[i].setBounds( (SampleGame.V_WIDTH /2) - 100, (SampleGame.V_HEIGHT/2) - 75, SampleGame.V_WIDTH /2, SampleGame.V_HEIGHT/2);
        };

    }
     @Override
    public void show()
    {
    }

    public void update(float delta)
    {
        app.camera.update();
        stage.act(delta);
    }

    @Override
    public void render(float delta)
    {
        update(delta);


        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.draw();


        if(gameState.getWinner()!=null)
        {
            app.batch.begin();
            //GameApp.genFont("roboto", 15).draw(app.batch,"The winner is "+gameState.getWinner(),20,20);
            //System.out.println("WINNER!!: "+gameState.getWinner());
            app.batch.end();


            //output the winner and clear the stage items
            if(gameState.getWinner().toString().contains("RED"))
            {
                stage.addActor(playerImage[3]);
                playerImage[1].remove();
                playerImage[2].remove();

                stage.addActor(playerImage[5]);

            }
            else if(gameState.getWinner().toString().contains("BLUE"))
            {
                stage.addActor(playerImage[4]);
                playerImage[1].remove();
                playerImage[2].remove();

                stage.addActor(playerImage[6]);
            }

            //show the win screen for half a second and go back to the main menu
            Timer.schedule(new Timer.Task()
            {

                @Override
                public void run()
                {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(app));
                }

            }, 0.5f);

          }

        //display whos turn a next
        if((gameState.getCurrentPlayer() != null) && (gameState.getWinner() == null))
        {
            String colour = (gameState.getCurrentPlayer());

            if(colour.contains("BLUE"))
            {
                playerImage[2].remove();
                stage.addActor(playerImage[1]);
            }


            if(colour.contains("RED"))
            {
                playerImage[1].remove();
                stage.addActor(playerImage[2]);
            }

        }

    }

    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause(){}

    @Override
    public void resume(){ }

    @Override
    public void hide() {
        stage.clear();
    }

    @Override
    public void dispose()
    {
        stage.dispose();
        skin.dispose();
        //TODO: add dispose methods for Grid and GamePiece
        //I don't think Grid and GamePiece need to be disposed
    }

    private void initSkin()
    {
        //create skin and add all the textures to it
        skin = new Skin();
        skin.add("board", new Texture("textures/board.png"));
        skin.add("grid", new Texture("grid2.png"));
        skin.add("glow", new Texture("glow.png"));
        skin.add("felt", new Texture("textures/felt2.png"));
        skin.add("piece_red", new Texture(Gdx.files.internal("piece_red2.png")));
        skin.add("piece_blue", new Texture(Gdx.files.internal("piece_blue2.png")));
    }


    private void initPieces(Player pl, Size sz, int num)
    {
        String player = pl == Player.BLUE ? "piece_blue" : "piece_red";

        for (int i = 0; i < num; ++i)
        {
            GamePiece piece = new GamePiece(skin, player, this);
            piece.initForNewGame(pl, sz);
            piece.makeDraggable();
            stage.addActor(piece);

        }

    }
}

