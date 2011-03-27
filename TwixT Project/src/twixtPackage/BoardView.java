package twixtPackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class BoardView extends JComponent implements MouseListener
{
	Test test = new Test(false);
	
	public int side = 400;
	private static final int RADIUS = 10;
	private static final int SPACE = 10;
	private int BOARDSIZE = 24;
	
	Color colourBackground = Color.WHITE;
	Color colourDefault = Color.GRAY;
	Color colourBorder = Color.BLACK;
	Color colourPlayer1 = Color.RED;
	Color colourPlayer2 = Color.BLUE;

	
	int startingOffsetX = 25;
	int startingOffsetY = 25;
	int offsetX = 0;
	int offsetY = 0;
	int componentWidth = 0;
	int componentHeight = 0;
	private HumanController player1;
	private HumanController player2;
	
	Board board;
	Game game;
	Circle[][] towerHoles = new Circle[BOARDSIZE][BOARDSIZE];
	
	public BoardView(Board _board, Dimension in)
	{
		board = _board;
		if(in.width > in.height)
			side = in.height;
		else
			side = in.width;
		BOARDSIZE = board.BOARDSIZE;
		
		componentWidth = in.width;
		componentHeight = in.height;
		if(test.getDebugModeOn())System.out.printf("%d", componentWidth);
		this.setForeground(colourBackground);
		addMouseListener(this);
	}
	
	public void paint(Graphics _g)
	{
		Graphics2D g = (Graphics2D) _g;
        
		// Inner Player Lines
        g.setStroke(new BasicStroke(2.0f));
        g.setColor(colourPlayer1);
        g.drawLine(startingOffsetX/2, startingOffsetY+RADIUS+RADIUS/2, startingOffsetX + (2*RADIUS*BOARDSIZE), startingOffsetY+RADIUS+RADIUS/2);
        g.drawLine(startingOffsetX/2, 2*RADIUS*BOARDSIZE, startingOffsetX + (2*RADIUS*BOARDSIZE), 2*RADIUS*BOARDSIZE);
        g.setColor(colourPlayer2);
        g.drawLine(startingOffsetX+RADIUS+RADIUS/2, startingOffsetY/2, startingOffsetX+RADIUS+RADIUS/2, startingOffsetY + (2*RADIUS*BOARDSIZE));
        g.drawLine(2*RADIUS*BOARDSIZE, startingOffsetY/2, 2*RADIUS*BOARDSIZE, startingOffsetY + (2*RADIUS*BOARDSIZE));

        // Border Lines
        g.setStroke(new BasicStroke(3.0f));
		g.setColor(colourBorder);
		g.drawLine(startingOffsetX/2, startingOffsetX/2, startingOffsetX + (2*RADIUS*BOARDSIZE), startingOffsetY/2); 			// Top
        g.drawLine(startingOffsetX/2, 2*RADIUS*BOARDSIZE+startingOffsetY, startingOffsetX + (2*RADIUS*BOARDSIZE), 2*RADIUS*BOARDSIZE+startingOffsetY);
        g.drawLine(startingOffsetX/2, startingOffsetY/2, startingOffsetX/2, startingOffsetY + (2*RADIUS*BOARDSIZE));
        g.drawLine(startingOffsetX + (2*RADIUS*BOARDSIZE), startingOffsetX/2, startingOffsetX + (2*RADIUS*BOARDSIZE), 2*RADIUS*BOARDSIZE+startingOffsetY);
		
        // Circle Outline
        g.setStroke(new BasicStroke(0.0f));
        g.setColor(colourDefault);
        
        offsetX = startingOffsetX;
        offsetY = startingOffsetY;
        
        for(int i=0; i < BOARDSIZE; i++)
        {
        	for(int j=0; j < BOARDSIZE; j++)
        	{
        		towerHoles[i][j] = new Circle(i, j, offsetX + i*SPACE, offsetY + j*SPACE, RADIUS);
        		g.draw(towerHoles[i][j].circ);
            	offsetY += SPACE;
        	}
        	offsetY = startingOffsetY;

        	offsetX += SPACE;
        }

        // Draw Towers on the board
        for (int i = 0; i < BOARDSIZE; i++)
        {
            for (int j = 0; j < BOARDSIZE; j++)
            {
		        if (board.getTower(i, j) != null)
		        {
		        	if (board.getTower(i, j).getPlayerId() == 1)
		        	{
		        		g.setColor(colourPlayer1);
		        		g.fillOval((SPACE*i) + startingOffsetX + i*SPACE, (SPACE*j) + startingOffsetY + j*SPACE, RADIUS, RADIUS);
		        	}
		            else if (board.getTower(i, j).getPlayerId() == 2)
		            {
		        		g.setColor(colourPlayer2);
		        		g.fillOval((SPACE*i) + startingOffsetX + i*SPACE, (SPACE*j) + startingOffsetY + j*SPACE, RADIUS, RADIUS);
		            }
		        }
		        else
		        {
		        	if(test.getDebugModeOn())System.out.println("No tower here to print.");
		       	}
	        }
        }
       
        // Draw Bridges on the board
        Bridge temp = null;
        Tower start = null;
        Tower end = null;
    	int x1 = 0;
    	int y1 = 0;
    	int x2 = 0;
    	int y2 = 0;
        g.setStroke(new BasicStroke(2.5f));
    	
        for(int i = 0; i < board.getBridgeList().size(); i++)
        {
        	temp = board.getBridge(i);
        	start = temp.getStart();
        	end = temp.getEnd();
        	
        	if(test.getDebugModeOn() == true) System.out.println("Attempting to place bridge " + start.getX()+","+start.getY() + " " + end.getX()+","+end.getY());
        	g.setColor((temp.getID() == 1) ? colourPlayer1 : colourPlayer2); // If ID is 1 then use player 1's colour, else use player 2's colour.
        	x1 = startingOffsetX + RADIUS/2 + start.getX()*(SPACE+RADIUS);
        	y1 = startingOffsetY + RADIUS/2 + start.getY()*(SPACE+RADIUS);
        	x2 = startingOffsetX + RADIUS/2 + end.getX()*(SPACE+RADIUS);
        	y2 = startingOffsetY + RADIUS/2 + end.getY()*(SPACE+RADIUS);
        	g.drawLine(x1, y1, x2, y2);
        }
	}
	public void addPlayer1Controller(HumanController p1){
		player1=p1;
	}
	public void addPlayer2Controller(HumanController p2){
		player2=p2;
	}
	@Override
	public void mouseClicked(MouseEvent click)
	{
		// Retrieve the pixel co-ords of the Mouse Click event.
		int x = click.getX();
		int y = click.getY();
		
		// Compute the array co-ordinate from the pixel values.
		x = x - 20;
		x = x/20;
		y = y - 20;
		y = y/20;
		
		//send to humanController(s)
		if(this.player1!=null){
			player1.doMove(x, y);
		}
		if(this.player2!=null){
			player2.doMove(x, y);
		}
		// Print out the array co-ords.
		if(test.getDebugModeOn() == true) System.out.println("x = " + x + "\ny = " + y);		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

class Circle extends JComponent{

	Test test = new Test(true);
	
	Ellipse2D.Double circ;
	Color colour;
	int radius = 0;
	int x = 0;
	int y = 0;
	int xOffset = 0;
	int yOffset = 0;
	
	public Circle(int _x, int _y, int _xOffset, int _yOffset, int _radius)
	{
		radius = _radius;
		x = _x;
		y = _y;
		xOffset = _xOffset;
		yOffset = _yOffset;
		circ = new Ellipse2D.Double(xOffset, yOffset, radius, radius);
	}

}


