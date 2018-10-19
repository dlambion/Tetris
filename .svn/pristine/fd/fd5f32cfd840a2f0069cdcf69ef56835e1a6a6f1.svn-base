/*
 * TCSS 305 - Spring 2016
 * Assignment 6 - Tetris
 */

package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Board;

/**
 * Adds the keyboard controls for the Tetris game.
 * 
 * @author Danielle Lambion
 * @version 27 May 2016
 */
public class KeyboardActions extends KeyAdapter
{
    /**
     * An instance of the Board class, representing the Tetris board.
     */
    private final Board myBoard;

    /**
     * Constructor for the keyboardActions class.
     * 
     * @param theBoard the current instance of the Board class, representing the Tetris board.
     */
    public KeyboardActions(final Board theBoard)
    {
        super();
        myBoard = theBoard;
    }
    
    @Override
    public void keyPressed(final KeyEvent theEvent)
    {
        if (theEvent.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            myBoard.right();
        }
        else if (theEvent.getKeyCode() == KeyEvent.VK_LEFT)
        {
            myBoard.left();
        }
        else if (theEvent.getKeyCode() == KeyEvent.VK_DOWN)
        {
            myBoard.down();
        }
        else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE)
        {
            myBoard.drop();
        }
        else if (theEvent.getKeyCode() == KeyEvent.VK_X)
        {
            myBoard.rotateCW();
        }
        else if (theEvent.getKeyCode() == KeyEvent.VK_Z)
        {
            myBoard.rotateCCW();
        }
    }

}
