package com.test.ds;

public class DoublyLinkedListADT {
	Node head;
	int size;

	static class Node {
		Node next;
		Node previous;
		int data;

		Node(int data) {
			this.next = null;
			this.previous = null;
			this.data = data;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Node [data=" + data + "]";
		}
		
	}

	boolean pushAtBegining(Node node) {
		if (head == null) {
			head = node;
			size++;
			return true;
		} else {
			node.previous = null;
			node.next = head;
			head.previous = node;
			head = node;
			size++;
			return true;
		}
	}

	boolean pushAtEnd(Node node) {
		if (head == null) {
			head = node;
			size++;
			return true;
		} else {
			Node currentNode = head;
			while (currentNode.next != null) {
				currentNode = currentNode.next;
			}
			currentNode.next = node;
			node.previous = currentNode;
			size++;
			return true;
		}
	}

	void printADT() {
		Node currentNode = head;
		if (currentNode == null) {
			System.out.println("Empty List");
		} else {
			while (currentNode != null) {
				System.out.println(currentNode.data);
				currentNode = currentNode.next;
			}
		}
	}

	boolean deleteNode(Node node) {
		if (head == null || node==null) {
			return false;
		} else if (head == node) {
			head = head.next;
			head.previous = null;
			size--;
			return true;
		} else {
			Node currentNode = head;
			while (currentNode.next != null) {
				/*if (currentNode.data == node.data && currentNode.next == node.next
						&& currentNode.previous == node.previous) {
					currentNode.previous.next=currentNode.next;
					currentNode.next.previous=currentNode.previous;
					return true;
				}*/
				if(currentNode==node){
					currentNode.previous.next=currentNode.next;
					currentNode.next.previous=currentNode.previous;
					return true;
				}
				currentNode = currentNode.next;
			}
		}
		return false;
	}

	Node getNodeAtLocation(int location) {
		if (head == null) {
			return null;
		} else {
			Node currentNode = head;
			for (int i = 0; currentNode.next != null; i++) {
				if (i == location) {
					return currentNode;
				}
				currentNode = currentNode.next;
			}
		}
		return null;
	}
}
