package linkedList;

import java.util.Random;

import linkedList.MyLinkedList;

public class Test {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyLinkedList l = new MyLinkedList(50);
		Random rand = new Random();
		
		for (int i = 0; i < 9; i++) {
			l.addLast(rand.nextInt(100));
			
		}
		l.addAt(8,5);
		l.deleteAt(3);
		
	}

}
