/*
 * TCSS 305 - Spring 2016
 * Assignment 6 - Tetris
 */

package view;

/**
 * Runs Tetris by instantiating and starting the GUI.
 * 
 * @author Danielle Lambion
 * @version 24 May 2016
 */
public final class TetrisMain 
{

    /**
     * Private constructor for prevention of instantiation.
     */
    private TetrisMain() 
    {
        throw new IllegalStateException();
    }
    
    /**
     * Invokes the Tetris GUI.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs)
    {       
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new TetrisGUI().start();
            }
        });
    }

}
