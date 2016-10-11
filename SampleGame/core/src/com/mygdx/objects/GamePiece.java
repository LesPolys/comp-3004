package com.mygdx.objects;
//package com.seg3125.project.objects;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.SampleGame;
import com.mygdx.screens.PlayScreen;
import com.mygdx.util.Declerations;
import com.mygdx.util.GameState;

//import com.seg3125.project.Declerations;
//import com.seg3125.project.GameApp;
//import com.seg3125.project.GameState;
//import com.seg3125.project.screens.PlayScreen;

public class GamePiece extends Image
{
    //stores the current player
    private String currentPlayer;
    private Declerations declerations;

    public enum Size
    {
        SMALL, MEDIUM, LARGE
    }

    public enum Player
    {
        RED, BLUE
    }

    public final static float LARGE_WIDTH = 45;

    private Player PLAYER;
    private Size SIZE;
    private GameState.Square square; //the logical square this piece belongs to (null if not on board)
    private float width, height, posX, posY;
    private PlayScreen screen; //the screen this game piece belongs on

    public GamePiece(Skin skin, String id, PlayScreen scrn)
    {
        super(skin, id);
        screen = scrn;
        square = null;
        PLAYER = null;
        SIZE = null;

        declerations = new Declerations();
    }

    public void initForNewGame(Player PL, Size SZ)
    {
        PLAYER = PL;
        SIZE = SZ;
        switch (SIZE)
        {
            case SMALL:
                width = height = LARGE_WIDTH * 0.6f;
                posY = SampleGame.V_HEIGHT - 80;
                //posX = 20 + LARGE_WIDTH * 0.4f / 2;
                posX = 70 + LARGE_WIDTH * 0.4f / 2;
                break;
            case MEDIUM:
                width = height = LARGE_WIDTH * 0.8f;
                posY = SampleGame.V_HEIGHT / 2 - 20;
                //posX = 20 + LARGE_WIDTH * 0.2f / 2;
                posX = 70 + LARGE_WIDTH * 0.2f / 2;
                break;
            case LARGE:
                width = height = LARGE_WIDTH;
                posY = 30;
                //posX = 20;
                posX = 70;
                break;
        }

        if (PLAYER == Player.BLUE)
        {
            posX = SampleGame.V_WIDTH - posX - width;
        }

        this.setBounds(posX, posY, width, height);
    }

    public Size getSize() {
        return SIZE;
    }
    public Player getPlayer() {
        return PLAYER;
    }
    public GameState.Square getSquare() {
        return square;
    }
    public void setSquare(GameState.Square sqr) {
        square = sqr;
    }
    public void setCurrentPlayer(String newCurrentPlayer) {
        currentPlayer = newCurrentPlayer;
    }
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void makeDraggable()
    {

        final GamePiece piece = this;
        final Vector2 origPos = new Vector2();
        final DragAndDrop dnd = new DragAndDrop();



            dnd.addSource(new DragAndDrop.Source(piece)
            {

                              @Override
                              public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer)
                              {

                                  //System.out.println("Test Cal6");
                                  origPos.set(piece.getX(), piece.getY());
                                  DragAndDrop.Payload payload = new DragAndDrop.Payload();
                                  payload.setObject(getActor());
                                  payload.setDragActor(getActor());
                                  dnd.setDragActorPosition(-x, -y + ((GamePiece) getActor()).getImageHeight());
                                  setCurrentPlayer(" " + getPlayer());
                                  //System.out.println("x: " + x + "y:" + y);
                                  return payload;
                              }

                              @Override
                              public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target)
                              {
                                  //System.out.println("Test Cal7");
                                  Vector2 gridPos = screen.grid.getSquareIndices(event.getStageX(), event.getStageY());

                                  if (screen.grid.isInsideGrid(event.getStageX(), event.getStageY())
                                          && screen.gameState.getSquare((int) gridPos.x, (int) gridPos.y).movePiece(piece))
                                  {

                                      piece.setPosition(screen.grid.getX() + gridPos.x * (screen.grid.SQUARE_SIZE + 1.25f) + 0.9f + (GamePiece
                                                      .LARGE_WIDTH - piece.getWidth()) / 2,
                                              screen.grid.getY() + gridPos.y * (screen.grid.SQUARE_SIZE + 1.15f) + 0.8f + (GamePiece
                                                      .LARGE_WIDTH - piece.getHeight()) / 2);

                                      screen.stage.addActor(piece);
                                      //System.out.println("new x: " + screen.grid.getX() + " new y:" + screen.grid.getY());
                                      //System.out.println("Curent Player2" + getPlayer());

                                  }
                                  else
                                  {
                                      //System.out.println("Test Cal8");
                                      screen.stage.addActor(getActor());
                                      getActor().addAction(Actions.moveTo(origPos.x, origPos.y, 1.0f, Interpolation.pow4Out));
                                  }
                              }
                          }
            );

    }
}


