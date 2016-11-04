package com.mygdx.game.GameLogic;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.Screens.PlayScreen;

public class Dragable extends Image
{
    //stores the current player
    private String currentPlayer;
    boolean squares[];

    private PlayScreen screen;

    public Dragable(Image image, PlayScreen scrn)
    {
        super(image.getDrawable());
        setCurrentPlayer("");
        squares = new boolean[20];
        screen = scrn;

    }

    public void setCurrentPlayer(String newCurrentPlayer) {
        currentPlayer = newCurrentPlayer;
    }
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void makeDraggable()
    {

        final Dragable piece = this;
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
                              dnd.setDragActorPosition(-x, -y + ((Dragable) getActor()).getImageHeight());

                              return payload;
                          }

                          //event is the new position moved to
                          @Override
                          public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target)
                          {
                              //+60 for collisions
                              //+50 for box positions

                              System.out.println("event X: " + event.getStageX());
                              System.out.println("event Y: " + event.getStageY());

                              //1st row
                              if(((event.getStageX() >= 90) && (event.getStageX() <= 160)) &&
                                      ((event.getStageY() >= 0) && (event.getStageY() <= 60)))
                              {
                                  System.out.println("Hit Test");

                                  piece.setPosition(83f, 23f);
                                  screen.stage.addActor(piece);
                                  System.out.println(piece.getCurrentPlayer());
                                  squares[0] = true;
                              }

                              else if(((event.getStageX() >= 90) && (event.getStageX() <= 160)) &&
                                      ((event.getStageY() >= 60) && (event.getStageY() <= 120)))
                              {
                                  System.out.println("Hit Test");

                                  piece.setPosition(83f, 73f);
                                  screen.stage.addActor(piece);
                                  System.out.println(piece.getCurrentPlayer());
                                  squares[1] = true;
                              }

                              else if(((event.getStageX() >= 90) && (event.getStageX() <= 160)) &&
                                      ((event.getStageY() >= 120) && (event.getStageY() <= 180)))
                              {
                                  System.out.println("Hit Test");

                                  piece.setPosition(83f, 123f);
                                  screen.stage.addActor(piece);
                                  System.out.println(piece.getCurrentPlayer());
                                  squares[2] = true;
                              }

                              else if(((event.getStageX() >= 90) && (event.getStageX() <= 160)) &&
                                      ((event.getStageY() >= 180) && (event.getStageY() <= 240)))
                              {
                                  System.out.println("Hit Test");

                                  piece.setPosition(83f, 173f);
                                  screen.stage.addActor(piece);
                                  System.out.println(piece.getCurrentPlayer());
                                  squares[3] = true;
                              }

                              else if(((event.getStageX() >= 90) && (event.getStageX() <= 160)) &&
                                      ((event.getStageY() >= 240) && (event.getStageY() <= 300)))
                              {
                                  System.out.println("Hit Test");

                                  piece.setPosition(83f, 223f);
                                  screen.stage.addActor(piece);
                                  System.out.println(piece.getCurrentPlayer());
                                  squares[4] = true;
                              }

                              else if(((event.getStageX() >= 90) && (event.getStageX() <= 160)) &&
                                      ((event.getStageY() >= 300) && (event.getStageY() <= 360)))
                              {
                                  System.out.println("Hit Test");

                                  piece.setPosition(83f, 273f);
                                  screen.stage.addActor(piece);
                                  System.out.println(piece.getCurrentPlayer());
                                  squares[5] = true;
                              }

                              //second row
                              else if(((event.getStageX() >= 150) && (event.getStageX() <= 210)) &&
                                      ((event.getStageY() >= 0) && (event.getStageY() <= 60)))
                              {
                                  System.out.println("Hit Test");

                                  piece.setPosition(133f, 23f);
                                  screen.stage.addActor(piece);
                                  System.out.println(piece.getCurrentPlayer());
                              }

                              //+60 for collisions
                              //+50 for box positions
                              //down
                              else if(((event.getStageX() >= 150) && (event.getStageX() <= 210)) &&
                                      ((event.getStageY() >= 60) && (event.getStageY() <= 120)))
                              {
                                  System.out.println("Hit Test");

                                  piece.setPosition(133f, 73f);
                                  screen.stage.addActor(piece);
                                  System.out.println(piece.getCurrentPlayer());
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


