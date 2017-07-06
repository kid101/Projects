package com.test.ds;

public class LListTest {
	public static void main(String[] args) {
		LListTest test = new LListTest();
		test.testLinkedListADT();
	}

	private void testLinkedListADT() {
		LinkedListADT adt = new LinkedListADT();
		LinkedListADT adt2 = new LinkedListADT();
		adt.insert(1);
		adt.insert(2);
		adt.insert(3);
		adt.insert(4);
		adt.insert(15);
		System.out.println("List before adding");
		adt.printLListADT();
		System.out.println("List after adding");
		adt.sortInsertedNode(12);
		adt.printLListADT();
		System.out.println("Rotating List at 3");
		adt.rotateList(3);
		adt.printLListADT();
		
		System.out.println("reagarnging");
		adt.rearrange();
		adt.printLListADT();
		/*adt.removeAll(1);
		System.out.println("List after removing");
		adt.printLListADT();
		System.out.println("List after removing node at location");
		adt.deleteNodeAtLocation(3);
		adt.printLListADT();
		System.out.println("size of the node is:" + adt.size());
		System.out.println("index of search");
		String a[] = adt.searchAll(3);
		for (int i = 0; i < a.length; i++) {
			if (a[i] !=null) {
				System.out.println(a[i]);
			}
		}

		int a1[] = new int[10];
		System.out.println(a1.length);
		System.out.println("Find middle value:"+adt.middleValue());
		
		
		System.out.println("After reversing the linkedList");
		adt.reverse();
		adt.printLListADT();
		System.out.println("After reversing alternatly");
		adt.reverseAlternateNodes(3);
		adt.printLListADT();*/
	}
}
