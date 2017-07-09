package com.test.ds;

import com.test.ds.DoublyLinkedListADT.Node;

public class DLLTest {
	public static void main(String[] args) {
		DoublyLinkedListADT adt= new DoublyLinkedListADT();
		adt.pushAtBegining(new Node(10));
		adt.pushAtBegining(new Node(20));
		adt.pushAtBegining(new Node(10));
		adt.pushAtBegining(new Node(25550));
		adt.pushAtBegining(new Node(40));
		System.out.println("Pushed in begining: ");
		adt.printADT();
		System.out.println("Pushed at End: ");
		adt.pushAtEnd(new Node(40));
		adt.printADT();
		
		Node node=adt.getNodeAtLocation(2);
		System.out.println("Deleting Node at loction 2");
		adt.deleteNode(node);
		adt.printADT();
	}
}
