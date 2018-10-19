/*
 * TCSS 305 - Spring 2016
 * Assignment 6 - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Board;

/**
 * A panel displaying the player's score.
 * 
 * @author Danielle Lambion
 * @version 24 May 2016
 */
public class ScorePanel extends JPanel implements Observer 
{
    /**
     * Pixel identation of displayed scoring strings.
     */
    private static final int STRING_INDENTATION = 20;
    
    /**
     * The base pixel distance that the strings are printed at on the y-axis of the GUI.
     */
    private static final int STRING_LINE_DOWN = 30;
    
    /**
     * Amount of lines needed for level-up.
     */
    private static final int LEVEL_UP_LINE_AMOUNT = 5;
    
    /**
     * Amount of lines needed for a tetris.
     */
    private static final int TETRIS_LINE_AMOUNT = 4;
    
    /**
     * Points awarded for a tetris.
     */
    private static final int TETRIS_POINTS = 40;

    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = 7790700994635768911L;
    
    /**
     * The panel size for the score panel.
     */
    private static final Dimension DEFAULT_PANEL_SIZE = new Dimension(150, 150);
    
    /**
     * An instance of the Board class, representing the Tetris board.
     */
    private final Board myBoard;
    
    /**
     * The player's current score.
     */
    private int myScore;
    
    /**
     * The number of lines cleared throughout game.
     */
    private int myCleared;

    /**
     * Constructor for Score Panel.
     * 
     * @param theBoard the current game board.
     */
    public ScorePanel(final Board theBoard) 
    {
        super();
        myBoard = theBoard;
        myBoard.addObserver(this);
        myScore = 0;
        setBackground(Color.GRAY);
        setPreferredSize(DEFAULT_PANEL_SIZE);
        myCleared = 0;
    }
    
    /**
     * Resets the score and lines cleared to 0 for when a new game is selected.
     */
    public void reset()
    {
        myScore = 0;
        myCleared = 0;
    }

    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(final Observable arg0, final Object arg1) 
    {
        if (arg1 instanceof Integer[])
        {
            final int clearedLines = ((Integer[]) arg1).length;
            myCleared = myCleared + clearedLines;
            if (clearedLines > 0 && clearedLines < TETRIS_LINE_AMOUNT)
            {
                myScore = myScore + (LEVEL_UP_LINE_AMOUNT * clearedLines);
            }
            else
            {
                myScore = myScore + TETRIS_POINTS;
            }
        }
    }
    
    @Override
    public void paintComponent(final Graphics theGraphic)
    {
        super.paintComponent(theGraphic);
        final Graphics2D graphic = (Graphics2D) theGraphic;
        graphic.setColor(Color.BLACK);
        graphic.drawString("SCORE", STRING_INDENTATION, STRING_LINE_DOWN);
        
        graphic.drawString("Current Score: " + Integer.toString(myScore), STRING_INDENTATION, 
                           STRING_LINE_DOWN + STRING_INDENTATION);
        
        graphic.drawString("Cleared Lines: " + Integer.toString(myCleared), 
                           STRING_INDENTATION, 
                           STRING_LINE_DOWN + STRING_INDENTATION * 2);
        
        graphic.drawString("Lines To Next Level: " + Integer.toString
                           (LEVEL_UP_LINE_AMOUNT - (myCleared % LEVEL_UP_LINE_AMOUNT)), 
                           STRING_INDENTATION, STRING_LINE_DOWN + STRING_INDENTATION * 3);
        
        graphic.drawString("Current Level: " + Integer.toString
                           (myCleared / LEVEL_UP_LINE_AMOUNT + 1), STRING_INDENTATION, 
                           STRING_LINE_DOWN + STRING_INDENTATION * 4);
        repaint();
    }
}
