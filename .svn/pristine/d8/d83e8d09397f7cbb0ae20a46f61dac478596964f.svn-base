/*
 * TCSS 305 - Spring 2016
 * Assignment 6 - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

/**
 * Creates a GUI version of the game board and paints the board and the pieces.
 * 
 * @author Danielle Lambion
 * @version 24 May 2016
 */
public class GamePanel extends JPanel
{
    /**
     * The pixel height and width for the blocks.
     */
    private static final int DEFAULT_BLOCK_SIZE = 20;
    
    /**
     * The panel size for the GUI game board.
     */
    private static final Dimension DEFAULT_PANEL_SIZE = new Dimension(250, 500);

    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = -996104457414022004L;
    
    /**
     * A list of TetrisBlocks, which contain the locations of the blocks to make up 
     * the pieces on the board.
     */
    private List<TetrisBlock> myBlockPoints;

    /**
     * Constructor for the playable game panel.
     * 
     * @param theList A list of TetrisBlocks, which contain the locations 
     * of the blocks to make up the pieces on the board.
     */
    public GamePanel(final List<TetrisBlock> theList) 
    {
        super();
        setPreferredSize(DEFAULT_PANEL_SIZE);
        setBackground(Color.WHITE);
        myBlockPoints = theList;
    }
    
    @Override
    public void paintComponent(final Graphics theGraphic)
    {
        super.paintComponent(theGraphic);
        final Graphics2D graphic = (Graphics2D) theGraphic;
        for (int i = 0; i < myBlockPoints.size(); i++)
        {
            graphic.setColor(Color.GRAY);
            graphic.fillRect(myBlockPoints.get(i).getX(), myBlockPoints.get(i).getY(), 
                             DEFAULT_BLOCK_SIZE, DEFAULT_BLOCK_SIZE);
            graphic.setColor(Color.BLACK);
            graphic.drawRect(myBlockPoints.get(i).getX(), myBlockPoints.get(i).getY(), 
                             DEFAULT_BLOCK_SIZE, DEFAULT_BLOCK_SIZE);
        }
        repaint();
    }
    
    /**
     * Updates the list for the current board blocks.
     * 
     * @param theBlocks an updated list of the current TetrisBlocks on the board.
     */
    public void updatePoints(final List<TetrisBlock> theBlocks)
    {
        myBlockPoints = theBlocks;
    }

}
