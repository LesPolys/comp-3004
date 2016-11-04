package com.mygdx.util;

import com.mygdx.objects.GamePiece;
//import com.seg3125.project.objects.GamePiece;

import com.mygdx.objects.GamePiece.Player;
//import com.seg3125.project.objects.GamePiece.Player;


public class GameState
{
    public Square[][] gridState;
    String currentPlayer;

    public GameState()
    {
        //initialize game state
        gridState = new Square[4][4];
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                gridState[i][j] = new Square();
            }
        }


    }


    public Square getSquare(int row, int col)
    {
        return gridState[row][col];
    }

    public Player getWinner()
    {
        Player winner;
        winner = getRowWinner();
        if (winner != null)
        {
            return winner;
        }

        winner = getColWinner();
        if (winner != null)
        {
            return winner;
        }

        winner = getDiagonalWinner();

        return winner;
    }

    private Player getRowWinner()
    {
        boolean winnerExists;
        for (int row = 0; row < gridState.length; row++)
        {
            winnerExists = true;
            for (int col = 0; col < gridState[0].length - 1; col++)
            {
                if (gridState[row][col].owner == null || gridState[row][col].owner != gridState[row][col + 1].owner)
                {
                    winnerExists = false;
                    break;
                }
            }
            if (winnerExists)
            {
                return gridState[row][0].owner;
            }
        }
        return null;
    }

    private Player getColWinner()
    {
        boolean winnerExists;
        for (int col = 0; col < gridState.length; col++)
        {
            winnerExists = true;
            for (int row = 0; row < gridState[0].length - 1; row++)
            {
                if (gridState[row][col].owner == null || gridState[row][col].owner != gridState[row + 1][col].owner)
                {
                    winnerExists = false;
                    break;
                }
            }
            if (winnerExists)
            {
                return gridState[0][col].owner;
            }
        }
        return null;
    }

    private Player getDiagonalWinner()
    {
        boolean winnerExists = true;

        //check "\" diagonal
        for (int i = 0; i < gridState.length-1; i++)
        {
            if(gridState[i][i].owner == null || gridState[i][i].owner != gridState[i+1][i+1].owner)
            {
                winnerExists = false;
                break;
            }
        }
        if(winnerExists)
        {
            return gridState[0][0].owner;
        }

        winnerExists = true;
        //check "/" diagonal
        for (int i = 0; i < gridState.length-1; i++)
        {
            int j = gridState.length-1-i;
            if(gridState[i][j].owner == null || gridState[i][j].owner != gridState[i+1][j-1].owner)
            {
                winnerExists = false;
                break;
            }
        }
        if(winnerExists)
        {
            return gridState[0][gridState.length-1].owner;
        }

        return null;
    }

    public String getCurrentPlayer()
    {
        return currentPlayer;
    }

    /**
     * Inner class that represents the state of each square in the game grid.
     * Keeps track of the which pieces are currently on the square.
     */
    public class Square
    {
        int largestSize = -1; //-1 means empty square, 0,1,2 are actual sizes for game pieces
        GamePiece[] pieces = new GamePiece[3]; //upto 3 max game pieces can be on square
        GamePiece.Player owner; //the player that currently has control of the square


        /**
         * Moves the given game piece to this Square if a valid play was made. Otherwise no change is made.
         *
         * @param piece
         * @return true if a valid play was made and false otherwise
         */


        public boolean movePiece(GamePiece piece)
        {
            //must move to square with same owner or no owner
            if (owner != null && owner != piece.getPlayer())
            {

                return false;
            }
            owner = piece.getPlayer();

            int size = piece.getSize() == GamePiece.Size.SMALL ? 0 :
                    (piece.getSize() == GamePiece.Size.MEDIUM ? 1 : 2);

            //must move to square that has only smaller game pieces
            if (largestSize >= size)
            {
                System.out.println("test2");
                return false;
            }
            largestSize = size;
            pieces[largestSize] = piece;

            //remove the piece from its old square
            if (piece.getSquare() != null)
            {
                piece.getSquare().removePiece(piece);
                System.out.println("test3");

            }
            piece.setSquare(this);

            currentPlayer = piece.getCurrentPlayer().toString();
            return true;
        }
        /**
         * Removes the given game piece from this Square, while making the necessary changes to its internal state.
         *
         * @param piece
         */
        private void removePiece(GamePiece piece)
        {
            System.out.println("before: " + largestSize);
            pieces[largestSize] = null;
            int i;
            for (i = largestSize; i >= 0; i--)
            {
                if (pieces[i] != null)
                {
                    break;
                }
            }
            largestSize = i;
            if (largestSize == -1)
            {
                owner = null;
            }
            System.out.println("after: " + largestSize + "\n");
        }

        public String toString()
        {
            return "" + owner.toString();
        }


    }
}
