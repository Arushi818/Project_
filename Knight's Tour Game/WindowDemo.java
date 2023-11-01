import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;
import java.util.ArrayList;
/*
 *  The main window of the gui.
 *  Notice that it extends JFrame - so we can add our own components.
 *  Notice that it implements ActionListener - so we can handle user input.
 *  This version also implements MouseListener to show equivalent functionality (compare with the other demo).
 *  @author mhatcher
 */
public class WindowDemo extends JFrame implements ActionListener, MouseListener
{
	// gui components that are contained in this frame:
	private JPanel topPanel, bottomPanel;	// top and bottom panels in the main window
	private JLabel instructionLabel;		// a text label to tell the user what to do
	private JLabel infoLabel;            // a text label to show the coordinate of the selected square
    private JButton topButton;				// a 'reset' button to appear in the top panel
	private GridSquare [][] gridSquares;	// squares to appear in grid formation in the bottom panel
	private int rows,columns;				// the size of the grid
	private int moves;
	private ArrayList<GridSquare> queue; 
	/*
	 *  constructor method takes as input how many rows and columns of gridsquares to create
	 *  it then creates the panels, their subcomponents and puts them all together in the main frame
	 *  it makes sure that action listeners are added to selectable items
	 *  it makes sure that the gui will be visible
	 */
	public WindowDemo(int rows, int columns)
	{
		this.rows = rows;
		this.columns = columns;
		this.setSize(600,600);
		
		// first create the panels
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(rows, columns));
		bottomPanel.setSize(500,500);
		
		// then create the components for each panel and add them to it
		
		// for the top panel:
		//instructionLabel = new JLabel("Sir Lancelot, visit every square once!");
        infoLabel = new JLabel("Sir Lancelot, visit every square once!");
		topButton = new JButton("New Game");
		topButton.addActionListener(this);			// IMPORTANT! Without this, clicking the square does nothing.
		
		//topPanel.add(instructionLabel);
		topPanel.add(infoLabel);
		topPanel.add (topButton);
        //topPanel.add(infoLabel);
		
	
		// for the bottom panel:	
		// create the squares and add them to the grid
		gridSquares = new GridSquare[rows][columns];
		for ( int x = 0; x < columns; x ++)
		{
			for ( int y = 0; y < rows; y ++)
			{
				gridSquares[x][y] = new GridSquare(x, y);
				gridSquares[x][y].setSize(20, 20);
				gridSquares[x][y].setColor(x + y);
				
				gridSquares[x][y].addMouseListener(this);		// AGAIN, don't forget this line!
				
				bottomPanel.add(gridSquares[x][y]);
			}
		}
		
		// now add the top and bottom panels to the main frame
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(bottomPanel, BorderLayout.CENTER);		// needs to be center or will draw too small
		
		// housekeeping : behaviour
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		queue = new ArrayList<GridSquare>();
		moves = 0;
	}
	
	
	/*
	 *  handles actions performed in the gui
	 *  this method must be present to correctly implement the ActionListener interface
	 */
	public void actionPerformed(ActionEvent aevt)
	{
		// get the object that was selected in the gui
		Object selected = aevt.getSource();
		if ( selected.equals(topButton) ){
			reset();
		}
		
				
		// if resetting the squares' colours is requested then do so
		
	}

	// Mouse Listener events
	public void mouseClicked(MouseEvent mevt)
	{
		// get the object that was selected in the gui
		Object selected = mevt.getSource(); 
		
		/*
		 * I'm using instanceof here so that I can easily cover the selection of any of the gridsquares
		 * with just one piece of code.
		 * In a real system you'll probably have one piece of action code per selectable item.
		 * Later in the course we'll see that the Command Holder pattern is a much smarter way to handle actions.
		 */
		
		// if a gridsquare is selected then switch its color
		if (selected instanceof GridSquare)
		{
			if ((moves == 0)||(queue.isEmpty())) {
			    moves++;
                GridSquare square = (GridSquare) selected;
			    square.switchColorYell();
                int x = square.getXcoord();
                int y = square.getYcoord();
                queue.add(square);
                square.setVisited(true);
                //queue.enqueue(y);
                infoLabel.setText("moves: " + moves);
			}
			else {
				GridSquare square = (GridSquare) selected;
			    //square.switchColor();
                int x = square.getXcoord();
                int y = square.getYcoord();
                
                if(queue.size( ) > 2) {queue.remove(0);}
                queue.add(square);
                
                
               // queue.enqueue(y);
                //write validate method: return true if difference is valid 
                //if return is true then switch colour to yellow, update moves and dequeue
               //if return is false, dequeue x1,y1 and save them. Dequeue x2,y2 and enqueue 
                 //x1,y1 again. Set message to invalid move. DO NOT INCREASE MOVES 
                if (validate(queue)) {
                	//System.out.println("if here");
                	square.setVisited(true);
                	queue.get(0).switchColor();
                	square.switchColorYell();
                	moves++;
                	GridSquare oldSquare = queue.remove(0);
                	
                	infoLabel.setText("moves: " + moves);
                	winGame();
                }
                else {
                	//System.out.println(" else here");
                	GridSquare correctSquare = queue.remove(0);
                	
                	queue.remove(0);
                	
                	queue.add(correctSquare);
                	
                	infoLabel.setText("You can't go there!");	
                }
                
                           
                }
			}
		}
	
	public boolean validate(ArrayList<GridSquare> somequeue) {
		boolean val = false;
		GridSquare square1 = somequeue.get(0);
		
		GridSquare square2 = somequeue.get(1);
		
		int x1 = square1.getXcoord();
        int y1 = square1.getYcoord();
        
		int x2 = square2.getXcoord();
        int y2 = square2.getYcoord();
        
        int diff1 = Math.abs(x2-x1); 
        int diff2 = Math.abs(y2-y1);
        
        boolean square2NotVisited = !square2.getVisited();
        
        //System.out.println("diff1: " + diff1 + "\ndiff2: " + diff2 + "\nsquare2NotVisited" + square2NotVisited + "\n");
        
        

          if (((diff1 == 1 && diff2 == 2) || (diff1 == 2 && diff2 == 1)) && square2NotVisited)
          {
           val = true;
           return val;
		}
		else {
			return val;
		}
		
	}
	
	public void winGame() {
		int visitedSquares = 0;
		for ( int x = 0; x < columns; x ++)
		{
			for ( int y = 0; y < rows; y ++)
			{
				if (gridSquares[x][y].getVisited()) {visitedSquares++;}	
			}
		}
		if(visitedSquares == (columns * rows)) {infoLabel.setText("You did it!");}
		
		
	}
	
	public void reset() {
			for ( int x = 0; x < columns; x ++)
			{
				for ( int y = 0; y < rows; y ++)
				{
					gridSquares [x][y].setColor(x + y);
					gridSquares[x][y].setVisited(false);
					moves = 0;
					infoLabel.setText("Sir Lancelot, visit every square once!");
					queue = new ArrayList<GridSquare>();
					
				}
			}
	}
	
	// not used but must be present to fulfil MouseListener contract
	public void mouseEntered(MouseEvent arg0){}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
