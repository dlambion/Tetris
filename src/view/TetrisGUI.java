/*
 * TCSS 305 - Spring 2016
 * Assignment 6 - Tetris
 */

package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import model.Board;
import model.Point;
import model.TetrisPiece;

/**
 * The user interface for Tetris.
 * 
 * @author Danielle Lambion
 * @version 24 May 2016
 */
public class TetrisGUI extends JFrame implements Observer
{
    /**
     * The millisecond change in the timer speed for each level.
     */
    private static final int LEVEL_DELAY = 20;
    
    /**
     * Amount of lines needed for level-up.
     */
    private static final int LEVEL_UP_LINE_AMOUNT = 5;
    
    /**
     * The default board width.
     */
    private static final int DEFAULT_BOARD_WIDTH = 10;
    
    /**
     * The default board height.
     */
    private static final int DEFAULT_BOARD_HEIGHT = 20;
    
    /** 
     * The default delay (in milliseconds) for the timer. 
     */
    private static final int DEFAULT_DELAY = 1000;

    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = 106474944905625785L;
    
    /**
     * A boolean flag to check whether the game is paused.
     */
    private boolean myPaused;
    
    /**
     * The panel that displays the game board.
     */
    private final GamePanel myTetris;
    
    /**
     * The panel that displays a preview of the next tetris piece.
     */
    private final PreviewPanel myPreview;
    
    /**
     * The timer to move pieces during the game.
     */
    private final Timer myTimer;
    
    /**
     * Menu bar for buttons, such as, start, pause, and controls.
     */
    private final JMenuBar myMenu;
    
    /**
     * An instance of the Board class, representing the Tetris board.
     */
    private final Board myBoard;
    
    /**
     * A list of TetrisBlocks, which contain the locations of the blocks to make up 
     * the pieces on the board.
     */
    private final List<TetrisBlock> myBlockPoints;
    
    /**
     * A list of TetrisBlocks, which contain the locations of the blocks to make up 
     * the preview piece.
     */
    private final List<TetrisBlock> myPreviewPoints;
    
    /**
     * The panel displaying the score.
     */
    private ScorePanel myScore;
    
    /**
     * The number of lines cleared throughout game.
     */
    private int myCleared;

    /**
     * Constructor for the entire GUI for Tetris.
     */
    public TetrisGUI() 
    {
        super("TETRIS");
        myBlockPoints = new ArrayList<TetrisBlock>();
        myPreviewPoints = new ArrayList<TetrisBlock>();
        myBoard = new Board();
        myPaused = false;
        myMenu = new JMenuBar();
        myTetris = new GamePanel(myBlockPoints);
        myPreview = new PreviewPanel();
        myScore = new ScorePanel(myBoard);
        myCleared = 0;
        addKeyListener(new KeyboardActions(myBoard));
        generateFrame();
        myTimer = new Timer(DEFAULT_DELAY, theAction ->
        {
            myBoard.step();
        });
    }
    
    /**
     * Generates and makes the GUI visible for Tetris.
     */
    public void start()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        myBoard.addObserver(this);
        pack();
        setResizable(false);
        setVisible(true);
    }
    
    
    /**
     * Generates the frame with buttons and panels.
     */
    private void generateFrame()
    {
        final JMenu file = new JMenu("File");
        
        final JMenu about = new JMenu("About");
        
        final JMenu size = new JMenu("Size");
        
        final JMenuItem newGame = new JMenuItem("New Game");
        
        final JMenuItem pause = new JMenuItem("Pause");
        pause.setEnabled(false);
        pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        
        final JMenuItem endGame = new JMenuItem("End Game");
        endGame.setEnabled(false);
        
        final JMenuItem ctrls = new JMenuItem("Game Controls");
        
        final JMenuItem scoreInfo = new JMenuItem("Score Info");
        
        final ButtonGroup bg = new ButtonGroup();
        
        final JRadioButton defaultSize = new JRadioButton("Default");
        
        final JRadioButton largeSize = new JRadioButton("Large");
        
        bg.add(defaultSize);
        bg.add(largeSize);
        
        newGame.addActionListener(theEvent ->
        {
            myBoard.newGame();
            myTimer.start();
            pause.setEnabled(true);
            endGame.setEnabled(true);
            myScore.reset();
            myCleared = 0;
        });
        
        pause.addActionListener(theEvent ->
        {
            if (!myPaused)
            {
                myTimer.stop();
                removeKeyListener(this.getKeyListeners()[0]);
                myPaused = true;
            }
            else
            {
                myTimer.start();
                addKeyListener(new KeyboardActions(myBoard));
                myPaused = false;
            }
        });
        
        endGame.addActionListener(theEvent ->
        {
            myTimer.stop();
            newGame.setEnabled(true);
            pause.setEnabled(false);
            final String message = "You Have Ended the Game";
            JOptionPane.showMessageDialog(new JFrame(), message, "Game Ended", 
                                          JOptionPane.INFORMATION_MESSAGE);
        });
        
        ctrls.addActionListener(theEvent ->
        {
            final String message = "Move Left: \u2190\nMoveRight: \u2192\nMove Down: \u2193\n"
                            + "Rotate Clockwise: X\nRotate Counterclockwise: Z\n"
                            + "Drop: Spacebar\nPause/Unpause: Ctrl-P";
            JOptionPane.showMessageDialog(new JFrame(), message, "Controls", 
                                          JOptionPane.INFORMATION_MESSAGE);
        });
        
        scoreInfo.addActionListener(theEvent ->
        {
            final String message = "5 points for every line cleared\n"
                            + "40 points for a Tetris (4 lines simultaneously cleared)\n"
                            + "New level every 5 lines cleared";
            JOptionPane.showMessageDialog(new JFrame(), message, "Scoring", 
                                          JOptionPane.INFORMATION_MESSAGE);
        });
        
        file.add(newGame);
        file.add(pause);
        file.add(endGame);       
        size.add(defaultSize);
        size.add(largeSize);       
        about.add(ctrls);
        about.add(scoreInfo);        
        myMenu.add(file);
        myMenu.add(size);
        myMenu.add(about);        
        setJMenuBar(myMenu);        
        add(myPreview, BorderLayout.EAST);
        add(myTetris, BorderLayout.CENTER);
        add(myScore, BorderLayout.WEST);
    }

    @Override
    public void update(final Observable arg0, final Object arg1) 
    {
        if (arg1 instanceof String)
        {
            myBlockPoints.clear();
            String boardString = ((String) arg1).substring((myBoard.getWidth() + 3) * 5 - 1);
            boardString = boardString.replace("|", "");
            boardString = boardString.replace("-", "");
            boardString = boardString.replace("\n", "");
            for (int i = 0; i < boardString.length(); i++)
            {
                if (boardString.charAt(i) != ' ')
                {
                    myBlockPoints.add(new TetrisBlock((i % DEFAULT_BOARD_WIDTH) 
                                                      * myTetris.getWidth() 
                                                      / DEFAULT_BOARD_WIDTH, 
                                                     (i / DEFAULT_BOARD_WIDTH) 
                                                     * myTetris.getHeight() 
                                                     / DEFAULT_BOARD_HEIGHT));
                }
            }
            myTetris.updatePoints(myBlockPoints);
        }
        if (arg1 instanceof Boolean)
        {
            final String message = "Game Over";
            JOptionPane.showMessageDialog(new JFrame(), message, "You Have Lost", 
                                        JOptionPane.INFORMATION_MESSAGE);
            removeKeyListener(this.getKeyListeners()[0]);
        }
        
        if (arg1 instanceof TetrisPiece)
        {
            myPreviewPoints.clear();
            for (final Point point: ((TetrisPiece) arg1).getPoints())
            {
                if (((TetrisPiece) arg1).toString().equals("I"))
                {
                    myPreviewPoints.add(new TetrisBlock(point.x() * 25 + 28, point.y() * -25 + 115));
                }
                else if (((TetrisPiece) arg1).toString().equals("O"))
                {
                    myPreviewPoints.add(new TetrisBlock(point.x() * 25 + 28, point.y() * -25 + 100));
                }
                else
                {
                    myPreviewPoints.add(new TetrisBlock(point.x() * 25 + 40, point.y() * -25 + 100));
                }
            }
            myPreview.updatePoints(myPreviewPoints);
        }
        
        if (arg1 instanceof Integer[])
        {
            final int clearedLines = ((Integer[]) arg1).length;
            myCleared = myCleared + clearedLines;
            if ((myCleared / LEVEL_UP_LINE_AMOUNT) > 0)
            {
                myTimer.setDelay(DEFAULT_DELAY - ((myCleared / LEVEL_UP_LINE_AMOUNT + 1) 
                                * LEVEL_DELAY));
            }
        }
    }
}
