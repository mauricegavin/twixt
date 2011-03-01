package twixtPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class BoardView extends JComponent
{

	public int side = 400;
	private static final int RADIUS = 10;
	
	Color colourBackground = Color.WHITE;
	Color colourDefault = Color.GRAY;
	Color colourPlayer1 = Color.RED;
	Color colourPlayer2 = Color.BLUE;

	int startingOffsetX = 25;
	int startingOffsetY = 25;
	int offsetX = 0;
	int offsetY = 0;
	int componentWidth = 0;
	int componentHeight = 0;
	
	public BoardView(Dimension in)
	{
		if(in.width > in.height)
			side = in.height;
		else
			side = in.width;
		
		componentWidth = in.width;
		componentHeight = in.height;
		System.out.printf("%d", componentWidth);
		this.setForeground(colourBackground);
	}
	
	public void paint(Graphics _g)
	{
		if(this.getWidth() > this.getHeight())
			side = (int) (this.getHeight()*0.8);
		else
			side = (int) (this.getWidth()*0.8);
		
		componentWidth = this.getHeight();
		componentHeight = this.getHeight();
		
		Graphics2D g = (Graphics2D) _g;
        g.setColor(colourPlayer1);
        // 		   x1             y1 			     x2               y2
        //g.drawLine(componentWidth/5, 25, ((componentWidth-50)/5)+side, 25);
        //g.drawLine(25, 25+(componentWidth - side)/2, 25+side, 25+(componentWidth - side)/2);
        //g.drawLine(25, componentWidth-(componentWidth - side)/2, 25+side, componentWidth-(componentWidth - side)/2);
        g.setColor(colourPlayer2);
        //g.drawLine(25+(componentHeight - side)/2, 25, 25+(componentHeight - side)/2, 25+side);
        //g.drawLine(575, 0, 575, 600);
        g.setColor(colourDefault);
        
        offsetX = startingOffsetX;
        offsetY = startingOffsetY;
        
        for(int i=0; i < 24; i++)
        {
        	for(int j=0; j < 24; j++)
        	{
        		g.drawOval(offsetX + i*10, offsetY + j*10, RADIUS, RADIUS);
            	offsetY += 10;
        	}
        	offsetY = startingOffsetY;

        	offsetX += 10;
        }

	}

}
