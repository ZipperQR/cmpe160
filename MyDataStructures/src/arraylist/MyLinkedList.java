package src.arraylist;

import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;

//~~Hereeee we go!

public class MyLinkedList {
	
	private MyCanvas listCanvas;
	private GLabel operationNameTitle;
	private GLabel operationName;
	private GLabel operationNumberTitle;
	private GLabel operationNumber;

	private LinkedList<GRect> cells = new LinkedList<GRect>();
	private LinkedList<GLabel> labels = new LinkedList<GLabel>();
	private LinkedList<GImage> arrows = new LinkedList<GImage>();
	
	private static int CELL_EDGE_LEN = 30;
	private static int SPREAD;
	private static int CONTENT_FONT_SIZE;
	private Random rand = new Random();
	
	private static final int INITIAL_SIZE = 5;
	
	public MyLinkedList(int cell_edge_len) {
		CELL_EDGE_LEN = cell_edge_len;
		setSizes();
		listCanvas = new MyCanvas("LinkedList Example", 1280, 720);
		addLabels();
		
	}
	
	private void setSizes() {
		SPREAD = 300;
		CONTENT_FONT_SIZE = CELL_EDGE_LEN/2;
	}
	
	private void addLabels() {
		operationNameTitle = new GLabel("Operation:",CONTENT_FONT_SIZE,CONTENT_FONT_SIZE);
		operationNameTitle.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		operationName = new GLabel("",CONTENT_FONT_SIZE*8,CONTENT_FONT_SIZE);
		operationName.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		
		operationNumberTitle = new GLabel("Number:",CONTENT_FONT_SIZE,CONTENT_FONT_SIZE*2);
		operationNumberTitle.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		operationNumber = new GLabel("",CONTENT_FONT_SIZE*8,CONTENT_FONT_SIZE*2);
		operationNumber.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));

		listCanvas.addObject(operationNameTitle);
		listCanvas.addObject(operationName);
		listCanvas.addObject(operationNumberTitle);
		listCanvas.addObject(operationNumber);
	}
	
	public void push(int num) throws Exception {
		operationName.setLabel("Push");
		operationNumberTitle.setLabel("Number:");
		listCanvas.waitFor(500);
		operationNumber.setLabel(num+"");
		if (num > 99 || num < 0) {
			operationName.setLabel("");
			operationNumber.setLabel("");
			throw new IllegalArgumentException("Enter integers in [0,99]");
		}

		GRect boundbox = new GRect(100 + (cells.size() % 10) * 100,100 + (cells.size() / 10) * 100,CELL_EDGE_LEN,CELL_EDGE_LEN);
		listCanvas.addObject(boundbox);
		cells.add(boundbox);
		
		GLabel newNumLabel = new GLabel(num + "");
		newNumLabel.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		labels.add(newNumLabel);
		newNumLabel.setLocation(operationNumber.getX(), operationNumber.getY());
		operationNumber.setLabel("");
		listCanvas.addObject(newNumLabel);
		listCanvas.waitFor(200);
		listCanvas.transferObjectTo(newNumLabel, boundbox.getX() + CONTENT_FONT_SIZE / 3, boundbox.getY() + 4*CONTENT_FONT_SIZE/3, 10, 10);
		listCanvas.waitFor(200);
		if (cells.size() > 1){
			GImage arrow = new GImage("src/arraylist/arrow.png",operationNumber.getX(), operationNumber.getY());
			arrows.add(arrow);
			listCanvas.addObject(arrow);
			listCanvas.waitFor(200);
			listCanvas.transferObjectTo(arrow, cells.get(cells.size()-1).getX() - 50, cells.get(cells.size()-1).getY() + CELL_EDGE_LEN/2 - 5,10,10);
		}		
		operationName.setLabel("");
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MyLinkedList wew = new MyLinkedList(50);
		Random r = new Random();
		for (int i = 0; i < 15 ; i++){
			wew.push(r.nextInt(100));
		}
		

	}

}
