package test;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import Algorithm.Algorithm;
import database.*;
import draw.*;

public class TEST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Algorithm algorithm = new Algorithm();
		
		ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();

		String start = "0200,0200";
		
		routes.add(algorithm.Route(start, "1400,0400"));
		System.out.println(algorithm.RouteLength(start, "1400,1400"));
		
		MapDAO v = new MapDAO();
		TaxiMap frame = new TaxiMap(v.getMap());
		frame.setTitle("Map");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(820, 840);
		frame.setBackground(Color.WHITE);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setVisible(true);

		frame.setRoute(routes);
	}

}
