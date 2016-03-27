package linkedList;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.graphics.GRectangle;
import arraylist.MyCanvas;
import linkedList.Node;


public class MyLinkedList {
	private MyCanvas canvas;
	private GLabel operationNameTitle;
	private GLabel operationName;
	private GLabel operationNumberTitle;
	private GLabel operationNumber;
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<GLabel> labels = new ArrayList<GLabel>();
	private static Random rand = new Random();
	
	private static int CELL_EDGE_LEN = 50;
	private static int CONTENT_FONT_SIZE;
	
	private int currentDataLength = 0;
	private double leftBorder = 100;
	
	public MyLinkedList(int cell_edge_len) {
		canvas = new MyCanvas("LinkedList Example", 1280, 720);
		CELL_EDGE_LEN = cell_edge_len;
		CONTENT_FONT_SIZE = CELL_EDGE_LEN/3;
		addLabels();
		LinkedList l = new LinkedList<>();
	}
	
	
	public void addLast(int num){
		operationName.setLabel("Insertion");
		operationNumberTitle.setLabel("Number:");
		canvas.waitFor(500);
		operationNumber.setLabel(num+"");
		if (num > 99 || num < 0) {
			operationName.setLabel("");
			operationNumber.setLabel("");
			throw new IllegalArgumentException("Enter integers in [0,99]");
		}
		
		GLabel newNumLabel = new GLabel(num + "");
		newNumLabel.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		labels.add(newNumLabel);
		newNumLabel.setLocation(operationNumber.getX(), operationNumber.getY());
		
		operationNumber.setLabel("");
		canvas.addObject(newNumLabel);
		canvas.waitFor(500);
		if(currentDataLength != 0){
			leftBorder = nodes.get(currentDataLength-1).rightBorderX();
		}
		//increment currentDataLength
		currentDataLength++;
		
		Node node = new Node(canvas,leftBorder,150,num, CELL_EDGE_LEN);
		
		canvas.transferObjectTo(newNumLabel,node.value.getX(), node.value.getY(), 10, 10);
		canvas.waitFor(500);
		
		addNode(node);
		canvas.removeObject(newNumLabel);
		
		
	}
	
	public void addAt(int num, int index){
		operationName.setLabel("Insertion to Index");
		operationNumberTitle.setLabel("Number:");
		canvas.waitFor(500);
		operationNumber.setLabel(num+"");
		if (num > 99 || num < 0) {
			operationName.setLabel("");
			operationNumber.setLabel("");
			throw new IllegalArgumentException("Enter integers in [0,99]");
		}
		
		GLabel newNumLabel = new GLabel(num + "");
		newNumLabel.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		labels.add(newNumLabel);
		canvas.addObject(newNumLabel);
		newNumLabel.setLocation(operationNumber.getX(), operationNumber.getY());
		operationNumber.setLabel("");
		
		if(currentDataLength != 0){
			leftBorder = nodes.get(currentDataLength-1).rightBorderX();
		}
		//increment currentDataLength
		currentDataLength++;
		
		Node node = new Node(canvas,leftBorder,150,0, CELL_EDGE_LEN);
		node.value.setVisible(false);
		addNode(node);
		canvas.waitFor(500);
		
		for (int i = currentDataLength-1 ; i > index ; i--){
			nodes.get(i-1).value.setVisible(false);
			nodes.get(i).value.setLabel(nodes.get(i-1).value.getLabel());
			nodes.get(i).value.setVisible(true);			
			canvas.waitFor(500);
		}
		

		canvas.transferObjectTo(newNumLabel,nodes.get(index).value.getX(), nodes.get(index).value.getY(), 10, 10);
		canvas.waitFor(500);
		nodes.get(index).value.setLabel(newNumLabel.getLabel());
		nodes.get(index).value.setVisible(true);

		canvas.removeObject(newNumLabel);
		
		canvas.waitFor(500);
	}
	
	public void deleteAt(int index){
		operationName.setLabel("Deletion from Index");
		operationNumberTitle.setLabel("Number:");
		canvas.waitFor(500);
		operationNumber.setLabel(nodes.get(index).value.getLabel());
		
		if(currentDataLength != 0){
			leftBorder = nodes.get(currentDataLength-1).rightBorderX();
		}
		//increment currentDataLength
		GOval oval = new GOval(nodes.get(index).leftTopX, nodes.get(index).leftTopY, CELL_EDGE_LEN,CELL_EDGE_LEN);
		oval.setColor(Color.RED);
		canvas.addObject(oval);
		canvas.waitFor(1000);
		canvas.removeObject(oval);
		
		nodes.get(index).value.setLabel("");
		canvas.waitFor(500);
		
		for (int i = index ; i < currentDataLength-1 ; i++){
			nodes.get(i+1).value.setVisible(false);
			nodes.get(i).value.setLabel(nodes.get(i+1).value.getLabel());
			nodes.get(i).value.setVisible(true);			
			canvas.waitFor(500);
		}
		removeNode(nodes.get(currentDataLength-1));
		
		currentDataLength--;
		canvas.waitFor(500);
	}
	
	
	public void addNode(double x, double y, int num){
		Node node = new Node(canvas, x, y, num, CELL_EDGE_LEN);
		node.addComponentsToCanvas();
		nodes.add(node);
		
	}
	
	public void addNode(Node node){
		node.addComponentsToCanvas();
		nodes.add(node);
		
	}
	
	public void removeNode(Node node){
		node.removeComponentsFromCanvas();
		nodes.remove(node);
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

		canvas.addObject(operationNameTitle);
		canvas.addObject(operationName);
		canvas.addObject(operationNumberTitle);
		canvas.addObject(operationNumber);
	}
	

	
	
	
	
}
