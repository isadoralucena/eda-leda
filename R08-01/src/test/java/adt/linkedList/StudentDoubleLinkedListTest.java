package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	private DoubleLinkedList<Integer> lista3;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		lista3.insert(1);
	}

	private void getImplementations() {
		lista1 = new DoubleLinkedListImpl<>();
		lista2 = new DoubleLinkedListImpl<>();;
		lista3 = new DoubleLinkedListImpl<>();;
	}

	@Test
	public void testInsertFirst() {
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());

		((DoubleLinkedList<Integer>) lista2).insertFirst(10);
		Assert.assertArrayEquals(new Integer[] { 10 }, lista2.toArray());
	}

	@Test
	public void testRemoveFirst() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());

		((DoubleLinkedList<Integer>) lista3).removeFirst();
		Assert.assertTrue(lista3.isEmpty());
		Assert.assertArrayEquals(new Integer[] {}, lista3.toArray());
	}

	@Test
	public void testRemoveLast() {
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());

		((DoubleLinkedList<Integer>) lista3).removeLast();
		Assert.assertTrue(lista3.isEmpty());
		Assert.assertArrayEquals(new Integer[] {}, lista3.toArray());
	}

		@Test
	public void testInsertAndRemoveFirstInEmptyList() {
		((DoubleLinkedList<Integer>) lista2).insertFirst(99);
		Assert.assertArrayEquals(new Integer[] { 99 }, lista2.toArray());

		((DoubleLinkedList<Integer>) lista2).removeFirst();
		Assert.assertTrue(lista2.isEmpty());
	}

	@Test
	public void testInsertAndRemoveLastInEmptyList() {
		((DoubleLinkedList<Integer>) lista2).insert(88);
		Assert.assertArrayEquals(new Integer[] { 88 }, lista2.toArray());

		((DoubleLinkedList<Integer>) lista2).removeLast();
		Assert.assertTrue(lista2.isEmpty());
	}

	@Test
	public void testInsertFirstMultiple() {
		DoubleLinkedList<Integer> list = (DoubleLinkedList<Integer>) lista2;
		list.insertFirst(5);
		list.insertFirst(4);
		list.insertFirst(3);
		list.insertFirst(2);
		list.insertFirst(1);
		Assert.assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5 }, list.toArray());
	}

	@Test
	public void testRemoveFirstAndLastSequence() {
		DoubleLinkedList<Integer> list = (DoubleLinkedList<Integer>) lista1;
		list.removeFirst();
		list.removeLast();
		Assert.assertArrayEquals(new Integer[] { 2 }, list.toArray());

		list.removeFirst();
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	public void testRemoveElementMiddle() {
		DoubleLinkedList<Integer> list = (DoubleLinkedList<Integer>) lista1;
		list.remove(2);
		Assert.assertArrayEquals(new Integer[] { 3, 1 }, list.toArray());
	}

	@Test
	public void testRemoveFirstElementByValue() {
		DoubleLinkedList<Integer> list = (DoubleLinkedList<Integer>) lista1;
		lista1.remove(3);
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveLastElementByValue() {
		DoubleLinkedList<Integer> list = (DoubleLinkedList<Integer>) lista1;
		list.remove(1);
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, list.toArray());
	}

	@Test
	public void testRemoveNonExistentElement() {
		DoubleLinkedList<Integer> list = (DoubleLinkedList<Integer>) lista1;
		list.remove(99); 
		Assert.assertArrayEquals(new Integer[] { 3, 2, 1 }, list.toArray());
	}

	@Test
	public void testRemoveFromEmptyList() {
		DoubleLinkedList<Integer> list = (DoubleLinkedList<Integer>) lista2;
		list.remove(10);
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	public void testMixedInsertAndRemovals() {
		DoubleLinkedList<Integer> list = (DoubleLinkedList<Integer>) lista2;

		list.insert(1);
		list.insertFirst(0);
		list.insert(2);
		list.removeFirst(); 
		list.removeLast();  
		Assert.assertArrayEquals(new Integer[] { 1 }, list.toArray());

		list.remove(1);
		Assert.assertTrue(list.isEmpty());
	}
	@Test
	public void testInsertNullDoesNothing() {
		DoubleLinkedList<Integer> list = (DoubleLinkedList<Integer>) lista2;
		list.insert(null);
		list.insertFirst(null);
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	public void testRemoveNullDoesNothing() {
		DoubleLinkedList<Integer> list = (DoubleLinkedList<Integer>) lista1;
		list.remove(null);
		Assert.assertArrayEquals(new Integer[] { 3, 2, 1 }, list.toArray());
	}

}