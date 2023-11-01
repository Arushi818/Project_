import java.awt.Color;
import javax.swing.*;

/*
 *  A GUI component
 *
 *  A simple extension of JPanel which records its
 *  coordinates in xcoord and ycoord, NOT in 'x' and 'y'.
 *  Why not? Because 'x' and 'y' are already attributes of
 *  the panel (super) class which say where to draw it in the window.
 *
 *  The game grid and allows the background colour to be set with ease.
 *
 *  @author mhatcher
 */
public class GridSquare extends JPanel
{
    private int xcoord, ycoord;  // location in the grid
    private boolean visited;     //whether this grid has been selected during a valid move 
	
	// constructor takes the x and y coordinates of this square
	public GridSquare(int xcoord, int ycoord)
	{
		super();
		this.setSize(50,50);
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		this.visited = false;
	}
	
	// if the decider is even, it chooses black, otherwise white (for 'column+row' will allow a chequerboard effect)
    public void setColor( int decider)
    {
        Color colour = (int) (decider/2.0) == (decider/2.0) ? Color.black : Color.white;
        this.setBackground( colour);
    }
    
    // Set Square colour to blue
    public void switchColor()
    {
        Color colour = Color.blue;
        this.setBackground( colour);
    }
    
    //Set square colour to yellow
    public void switchColorYell()
    {
        Color colour = Color.yellow;
        this.setBackground( colour);	
    }
    
    public boolean isBluOrYell() {
    	boolean decide = (getBackground() == Color.blue) || (getBackground() == Color.yellow);
		return decide;
    }
    
    
    // simple setters and getters
    public void setXcoord(int value)       { xcoord = value; }
    public void setYcoord(int value)       { ycoord = value; }
    public void setVisited(boolean value)  {this.visited = value;}  
    public int getXcoord()                 { return xcoord; }
    public int getYcoord()                 { return ycoord; }
    public boolean getVisited()            {return this.visited;}
}
