
package com.mygdx.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
//import com.seg3125.project.screens.PlayScreen;
import com.mygdx.screens.PlayScreen;

public class Grid
{
    public final float SIZE;
    public final float SQUARE_SIZE;
    PlayScreen screen;
    Image bg;
    Image grid;

    public Grid(PlayScreen scrn, float size)
    {
        SIZE = size;
        SQUARE_SIZE = size / 4;
        screen = scrn;

        //display grid and background
        grid = new Image(screen.skin, "grid");
        bg = new Image(screen.skin, "board");
        grid.setBounds(screen.stage.getWidth() / 2 - SIZE / 2, screen.stage.getHeight() / 2 - SIZE / 2, SIZE, SIZE);
        bg.setBounds(grid.getX() - 15, grid.getY() - 15, grid.getWidth() + 30, grid.getHeight() + 30);
        screen.stage.addActor(bg);
        screen.stage.addActor(grid);
    }

    /**
     * Returns the row and col index inside the grid given coordinates (x,y)
     */
    public Vector2 getSquareIndices(float x, float y)
    {
        int rowIndex = (int) ((x - getX()) / SQUARE_SIZE);
        int colIndex = (int) ((y - getY()) / SQUARE_SIZE);
        return new Vector2(rowIndex, colIndex);
    }

    /**
     * Returns true if coordinates (x,y) are inside the game grid
     */
    public boolean isInsideGrid(float x, float y)
    {
        return x > getX(Align.bottomLeft) && y > getY(Align.bottomLeft)
                && x < getX(Align.topRight) && y < getY(Align.topRight);
    }

    public float getX() {
        return grid.getX();
    }

    public float getY() {
        return grid.getY();
    }

    public float getX(int align) {
        return grid.getX(align);
    }

    public float getY(int align) {
        return grid.getY(align);
    }

/*
    *//**
     * Inner class that represents the state of each square in the game grid.
     * Keeps track of the which pieces are currently on the square.
     *//*
    public class Square {
        int largestSize = -1; //-1 means empty square, 0,1,2 are actual sizes for game pieces
        GamePiece[] pieces = new GamePiece[3]; //upto 3 max game pieces can be on square
        GamePiece.Player owner; //the player that currently has control of the square

        *//**
         * Moves the given game piece to this Square if a valid play was made. Otherwise no change is made.
         * @param piece
         * @return true if a valid play was made and false otherwise
         *//*
        public boolean movePiece(GamePiece piece) {
            //must move to square with same owner or no owner
            if (owner != null && owner != piece.getPlayer()) {
                return false;
            }
            owner = piece.getPlayer();

            int size = piece.getSize() == GamePiece.Size.SMALL ? 0 :
                    (piece.getSize() == GamePiece.Size.MEDIUM ? 1 : 2);

            //must move to square that has only smaller game pieces
            if (largestSize >= size) {
                return false;
            }
            largestSize = size;
            pieces[largestSize] = piece;

            if(piece.getSquare() != null){
                piece.getSquare().removePiece(piece);
            }
            piece.setSquare(this);

            return true;
        }

        *//**
         * Removes the given game piece from this Square, while making the necessary changes to its internal state.
         * @param piece
         *//*
        private void removePiece(GamePiece piece) {
            System.out.println("before: " + largestSize);
            pieces[largestSize] = null;
            int i;
            for (i = largestSize; i >= 0; i--) {
                if (pieces[i] != null) {
                    break;
                }
            }
            largestSize = i;
            if (largestSize == -1) {
                owner = null;
            }
            System.out.println("after: " + largestSize + "\n");
        }
    }*/
}
