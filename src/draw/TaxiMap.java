package draw;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import model.*;

@SuppressWarnings("serial")
public class TaxiMap extends JFrame {

	ArrayList<Intersection> b = new ArrayList<Intersection>();
	ArrayList<ArrayList<Integer>> drawRoute = new ArrayList<ArrayList<Integer>>();

	public TaxiMap(ArrayList<Intersection> a) 
	{
		b.addAll(a);
		add(new PaintPanel());
	}

	public void setRoute(ArrayList<ArrayList<Integer>> route)
	{
		drawRoute = route;	
		repaint();
	}
	
	class PaintPanel extends JPanel 
	{		
		double scale = 0.4;
		CoordinateSystem S2 = new CoordinateSystem (scale, scale, 0, scale*2000);
		
		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);

			S2.drawAxis(g);

			int xLength = 2000;
			int yLength = 2000;
			
			S2.drawLine(g, new Vector(0, 0), new Vector(0, yLength));
			S2.drawLine(g, new Vector(0, yLength), new Vector(xLength, yLength));
			S2.drawLine(g, new Vector(xLength, yLength), new Vector(xLength, 0));
			S2.drawLine(g, new Vector(xLength, 0), new Vector(0, 0));

			g.setColor(Color.BLACK);
		
			for (int i = 0; i < b.size(); i++) 
			{		
				S2.drawStation(g, b.get(i).getXCoord(), b.get(i).getYCoord());			
			}
		
			for (int t = 0; t < b.size(); t++) 
			{
				
				for(int i = b.get(t).getLinks(); i > 0; i--)
				{
					int ax = b.get(t).getXCoord();
					int ay = b.get(t).getYCoord();
					
					int neighbor = b.get(t).getNn(i);
					
					int bx = b.get(neighbor).getXCoord();
					int by = b.get(neighbor).getYCoord();
					
					S2.drawStationLine(g, ax, ay, bx, by, false);
				}
											
			}
			
			if (drawRoute.size() > 0)
			{
				int tempId;
				for(int i=0; i<drawRoute.size(); i++) {
					int j=0;
					while(j < drawRoute.get(i).size()-1)
					{
						tempId = drawRoute.get(i).get(j);
						int Ax = b.get(tempId).getXCoord();
						int Ay = b.get(tempId).getYCoord();
						j++;
						tempId = drawRoute.get(i).get(j);
						int Bx = b.get(tempId).getXCoord();
						int By = b.get(tempId).getYCoord();
						
						S2.drawRouteLine(g, Ax, Ay, Bx, By);
						
					}
				}
			}
			
		}

	}
}
