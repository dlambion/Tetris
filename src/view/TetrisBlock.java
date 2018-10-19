/*
 * TCSS 305 - Spring 2016
 * Assignment 6 - Tetris
 */

package view;

/**
 * A class that encapsulates the x and y coordinates for current tetris blocks.
 * 
 * @author Danielle Lambion
 * @version 27 May 2016
 */
public class TetrisBlock
{
    /**
     * The x-coordinate location on the board for a specific block.
     */
    private final int myX;
    
    /**
     * The y-coordinate location on the board for a specific block.
     */
    private final int myY;

    /**
     * Constructor for the TetrisBlock class.
     * 
     * @param theX the x-coordinate location on the board for a specific block.
     * @param theY the y-coordinate location on the board for a specific block.
     */
    public TetrisBlock(final int theX, final int theY)
    {
        myX = theX;
        myY = theY;
    }
    
    /**
     * Retrieves the x-coordinate location on the board for a specific block.
     * 
     * @return returns the x-coordinate location.
     */
    public int getX()
    {
        return myX;
    }
    
    /**
     * Retrieves the y-coordinate location on the board for a specific block.
     * 
     * @return returns the y-coordinate location.
     */
    public int getY()
    {
        return myY;
    }

}
