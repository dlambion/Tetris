/*
 * TCSS 305 - Spring 2016
 * Assignment 6 - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * Creates the panel for the preview piece and paints the piece.
 * 
 * @author Danielle Lambion
 * @version 24 May 2016
 */
public class PreviewPanel extends JPanel
{
    /**
     * The pixel height and width for the blocks.
     */
    private static final int DEFAULT_BLOCK_SIZE = 20;
    
    /**
     * The x and y for the corner of the square outlining the preview piece.
     */
    private static final int SQUARE_COORD = 5;
    
    /**
     * The pixel length of one of the sides of the square outlining the preview piece.
     */
    private static final int SQUARE_SIDE_LENGTH = 140;

    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = 3457243357781833162L;
    
    /**
     * The panel size for the preview piece panel.
     */
    private static final Dimension DEFAULT_PANEL_SIZE = new Dimension(150, 150);
    
    /**
     * A list of TetrisBlocks, which contain the locations of the blocks to make up 
     * the preview piece.
     */
    private List<TetrisBlock> myPreviewPoints;

    /**
     * Constructor for the preview piece panel.
     */
    public PreviewPanel() 
    {
        super();
        myPreviewPoints = new ArrayList<TetrisBlock>();
        setBackground(Color.GRAY);
        setPreferredSize(DEFAULT_PANEL_SIZE);
    }
    
    @Override
    public void paintComponent(final Graphics theGraphic)
    {
        super.paintComponent(theGraphic);
        final Graphics2D graphic = (Graphics2D) theGraphic;
        for (TetrisBlock pts: myPreviewPoints)
        {
            graphic.setColor(Color.WHITE);
            graphic.fillRect(pts.getX(), pts.getY(), 
                             DEFAULT_BLOCK_SIZE, DEFAULT_BLOCK_SIZE);
            graphic.setColor(Color.BLACK);
            graphic.drawRect(pts.getX(), pts.getY(), 
                             DEFAULT_BLOCK_SIZE, DEFAULT_BLOCK_SIZE);
        }        
        graphic.drawRect(SQUARE_COORD, SQUARE_COORD, SQUARE_SIDE_LENGTH, SQUARE_SIDE_LENGTH);
        repaint();
    }
    
    /**
     * Updates the list for the preview blocks.
     * 
     * @param theBlocks an updated list of the preview TetrisBlocks.
     */
    public void updatePoints(final List<TetrisBlock> theBlocks)
    {
        myPreviewPoints = theBlocks;
    }

}
