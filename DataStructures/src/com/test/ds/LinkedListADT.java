package com.test.ds;

import java.awt.MultipleGradientPaint.CycleMethod;
import java.util.concurrent.CountDownLatch;

public class LinkedListADT {
	Node head;

	static class Node {
		Node next;
		int value;

		Node(int d) {
			value = d;
			next = null;
		}
	}

	public void printLListADT() {
		Node node = head;
		while (node != null) {
			System.out.println("value is :" + node.value);
			node = node.next;
		}
	}

	public boolean insert(int data) {
		Node node = head;
		if (head == null) {
			head = new Node(data);
			return true;
		}
		while (node.next != null) {
			node = node.next;
		}
		node.next = new Node(data);

		return true;
	}

	public boolean insertAfter(Node previousNode, int data) {
		Node node = new Node(data);
		node.next = previousNode.next;
		previousNode.next = node;
		return true;
	}

	public boolean deleteAfter(Node previousNode) {
		Node tempNode = previousNode.next.next;
		previousNode.next = tempNode;
		return true;
	}

	public boolean deleteFirstNode(int key) {
		Node tempNode;
		Node previousNode = null;
		Node currentNode = head;
		if (head.value == key) {
			head = head.next;
			return true;
		}
		while (currentNode != null) {
			if (currentNode.value == key) {
				tempNode = currentNode.next;
				previousNode.next = tempNode;
				return true;
			}
			previousNode = currentNode;
			currentNode = currentNode.next;
		}
		return false;
	}

	public boolean removeAll(int data) {
		Node previousNode = null;
		Node currentNode = head;
		Node nextNode;
		// MOST IMP part. Consider heads first.
		while (currentNode != null && head.value == data) {
			head = head.next;
			currentNode = currentNode.next;
		}
		while (currentNode != null) {
			if (currentNode.value == data) {
				deleteFirstNode(data);
			}
			previousNode = currentNode;
			currentNode = currentNode.next;
		}
		return true;
	}

	public boolean deleteNodeAtLocation(int location) {
		Node previousNode = null;
		if (location == 0) {
			head = head.next;
			return true;
		}
		Node currentNode = head;
		for (int i = 0; currentNode != null; i++) {
			if (location == i) {
				previousNode.next = currentNode.next;
				return true;
			}
			previousNode = currentNode;
			currentNode = currentNode.next;
		}
		return false;
	}

	public int size() {
		Node currentNode = head;
		int counter = 0;
		while (currentNode != null) {
			counter++;
			currentNode = currentNode.next;
		}
		return counter;
	}

	public String[] searchAll(int key) {
		Node currentNode = head;
		String[] occurances = new String[10];
		int counter = 0;
		for (int i = 0; currentNode != null; i++) {
			if (currentNode.value == key) {
				occurances[counter++] = String.valueOf(i);
			}
			currentNode = currentNode.next;

		}
		return occurances;
	}

	public int middleValue() {
		Node slowPointer = head;
		Node fastPointer = head;
		while (fastPointer != null && fastPointer.next != null) {
			fastPointer = fastPointer.next.next;
			slowPointer = slowPointer.next;
		}
		return slowPointer.value;

	}

	public void reverse() {
		if (head == null) {
			return;
		}
		Node currentNode = head;
		Node previousNode = null;
		Node nextNode = null;
		while (currentNode != null) {
			nextNode = currentNode.next;
			currentNode.next = previousNode;
			previousNode = currentNode;
			currentNode = nextNode;
		}
		head = previousNode;
	}

	public void sortInsertedNode(int newval) {
		if (head == null) {
			return;
		}
		Node currentNode = head;
		Node previousNode = null;
		Node newNode = new Node(newval);
		while (currentNode != null) {
			if (currentNode.value > newNode.value) {
				previousNode.next = newNode;
				newNode.next = currentNode;
				return;
			}
			previousNode = currentNode;
			currentNode = currentNode.next;

		}
		head = previousNode;
	}

	/*
	 * public void reverseAlternateNodes(int k) { if (head == null) { return; }
	 * Node currentNode = head; Node previousNode = null; Node nextNode = null;
	 * boolean flag = true; int counter=0; for (int i = 0; currentNode != null;
	 * i++) { if (flag) { nextNode=currentNode.next; currentNode.next =
	 * previousNode; previousNode = currentNode; currentNode = nextNode; }
	 * if(counter<k){ flag=true; if(counter!=0 && counter%k==0){ counter=0; } }
	 * counter++; } }
	 */
	public void rotateList(int pivotal) {
		if (head == null || pivotal <= 0 || pivotal == this.size()) {
			return;
		}
		Node currentNode = head;
		Node newStart = null;
		Node tempNode = null;
		for (int i = 0; currentNode != null; i++) {
			if (i == pivotal) {
				//tempNode is the new Tail
				tempNode=currentNode;
				newStart=currentNode.next;
				while (currentNode.next != null) {
					currentNode=currentNode.next;
				}
				tempNode.next=null;
				currentNode.next=head;
				head=newStart;
				return;
			}
			currentNode = currentNode.next;
		}
	}
}
