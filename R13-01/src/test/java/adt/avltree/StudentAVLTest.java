package adt.avltree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import adt.bst.BSTNode;

public class StudentAVLTest {

	private AVLTree<Integer> avl;
	private BSTNode<Integer> NIL = new BSTNode<Integer>();

	@Before
	public void setUp() {
		avl = new AVLTreeImpl<>();
	}

	@Test
	public void testInit() {
		assertTrue(avl.isEmpty());
		assertEquals(0, avl.size());
		assertEquals(-1, avl.height());
		assertEquals(NIL, avl.getRoot());
	}

	@Test
	public void testInsert() {
		avl.insert(-10);
		assertEquals(1, avl.size());
		assertEquals(0, avl.height());
		assertArrayEquals(new Integer[] { -10 }, avl.preOrder());

		assertFalse(avl.isEmpty());
		assertEquals(new Integer(-10), avl.getRoot().getData());

		avl.insert(-15);
		assertEquals(2, avl.size());
		assertEquals(1, avl.height());
		assertArrayEquals(new Integer[] { -10, -15 }, avl.preOrder());

		avl.insert(20);
		assertEquals(3, avl.size());
		assertEquals(1, avl.height());
		assertArrayEquals(new Integer[] { -10, -15, 20 }, avl.preOrder());
	}

	@Test
	public void testRemove() {
		avl.insert(55);
		avl.insert(9);
		avl.insert(91);
		avl.insert(12);

		avl.remove(-1);
		assertEquals(4, avl.size());

		avl.remove(91);
		assertEquals(3, avl.size());
		assertArrayEquals(new Integer[] { 12, 9, 55 }, avl.preOrder());

		avl.remove(12);
		assertEquals(2, avl.size());
		assertArrayEquals(new Integer[] { 55, 9 }, avl.preOrder());

		avl.remove(9);
		avl.remove(55);
		assertEquals(NIL, avl.getRoot());
		assertTrue(avl.isEmpty());
	}

	@Test
	public void testRotationsAndBalance() {
		avl.insert(30);
		avl.insert(20);
		avl.insert(10);
		assertArrayEquals(new Integer[] { 20, 10, 30 }, avl.preOrder());

		avl = new AVLTreeImpl<>();
		avl.insert(10);
		avl.insert(20);
		avl.insert(30);
		assertArrayEquals(new Integer[] { 20, 10, 30 }, avl.preOrder());

		avl = new AVLTreeImpl<>();
		avl.insert(30);
		avl.insert(10);
		avl.insert(20);
		assertArrayEquals(new Integer[] { 20, 10, 30 }, avl.preOrder());

		avl = new AVLTreeImpl<>();
		avl.insert(10);
		avl.insert(30);
		avl.insert(20);
		assertArrayEquals(new Integer[] { 20, 10, 30 }, avl.preOrder());
	}

	@Test
	public void testHeightAndSize() {
		assertEquals(-1, avl.height());
		assertEquals(0, avl.size());

		avl.insert(15);
		assertEquals(0, avl.height());
		assertEquals(1, avl.size());

		avl.insert(10);
		avl.insert(20);
		assertEquals(1, avl.height());
		assertEquals(3, avl.size());

		avl.remove(15);
		assertEquals(1, avl.height());
		assertEquals(2, avl.size());
	}

	@Test
	public void testPreAndPostOrder() {
		avl.insert(10);
		avl.insert(5);
		avl.insert(15);
		avl.insert(3);
		avl.insert(7);

		Integer[] preOrderExpected = { 10, 5, 3, 7, 15 };
		Integer[] postOrderExpected = { 3, 7, 5, 15, 10 };

		assertArrayEquals(preOrderExpected, avl.preOrder());
		assertArrayEquals(postOrderExpected, avl.postOrder());
	}

	@Test
	public void testInsertDuplicate() {
		avl.insert(10);
		avl.insert(10); 

		assertEquals(1, avl.size());
		assertArrayEquals(new Integer[] { 10 }, avl.preOrder());
	}

	@Test
	public void testRemoveFromEmptyTree() {
		avl.remove(10);
		assertTrue(avl.isEmpty());
		assertEquals(0, avl.size());
		assertEquals(-1, avl.height());
	}

	@Test
	public void testComplexInsertionsAndRemovals() {
		int[] values = { 50, 25, 75, 10, 30, 60, 80, 5, 15, 27, 55 };
		for (int v : values)
			avl.insert(v);

		assertEquals(values.length, avl.size());
		assertTrue(avl.height() <= (1.44 * (Math.log(values.length) / Math.log(2)))); 

		avl.remove(25);
		avl.remove(75);

		assertFalse(avl.isEmpty());
		assertEquals(values.length - 2, avl.size());
	}

	@Test
	public void testRemoveLeaf() {
		avl.insert(20);
		avl.insert(10);
		avl.insert(30);

		avl.remove(10);
		assertEquals(2, avl.size());
		assertArrayEquals(new Integer[] { 20, 30 }, avl.preOrder());
	}

	@Test
	public void testRemoveRoot() {
		avl.insert(10);
		avl.insert(5);
		avl.insert(15);

		avl.remove(10);
		assertEquals(2, avl.size());
		assertNotEquals(new Integer(10), avl.getRoot().getData());
	}

	@Test
	public void testInOrderTraversal() {
		int[] values = { 40, 20, 60, 10, 30, 50, 70 };
		for (int v : values)
			avl.insert(v);

		Integer[] expectedInOrder = { 10, 20, 30, 40, 50, 60, 70 };
		assertArrayEquals(expectedInOrder, avl.order());
	}

	@Test
	public void testSingleNode() {
		avl.insert(42);
		assertEquals(0, avl.height());
		assertEquals(1, avl.size());
		assertArrayEquals(new Integer[] { 42 }, avl.preOrder());

		avl.remove(42);
		assertTrue(avl.isEmpty());
		assertEquals(-1, avl.height());
	}
}
