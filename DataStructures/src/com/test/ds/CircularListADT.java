package com.test.ds;

public class CircularListADT {
	Node node;

	static class Node {
		Node next;
		int value;
	}

	void printclADT() {
		Node intersection = null;
		System.out.print("Node -> ");
		if (node.next == null) {
			System.out.print(node.value);
		}
		while (node.next != null) {
			if (node == intersection) {
				return;
			}
			System.out.print(node.value + " ,");
			node = node.next;
			intersection = node.next.next;
		}
		System.out.println();
	}

	void add(Node currentNode) {
		if (node == null) {
			node = currentNode;
			node.next=currentNode;
		}
		while(node.next!=null){
			
		}
	}

}
