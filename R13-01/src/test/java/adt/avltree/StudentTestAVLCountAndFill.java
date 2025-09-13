package adt.avltree;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class StudentTestAVLCountAndFill {

	protected AVLCountAndFill<Integer> tree1;
	protected AVLCountAndFill<Integer> tree2;
	protected AVLCountAndFill<Integer> tree3;
	protected static int SIZE = 10;

	@Before
	public void setUp() throws Exception {
		tree1 = new AVLCountAndFillImpl<Integer>();
		tree2 = new AVLCountAndFillImpl<Integer>();
		for (int i = 0; i < SIZE; i++) {
			tree1.insert(i);
			tree2.insert(SIZE - i);
		}
		tree3 = new AVLCountAndFillImpl<Integer>();
		Integer[] data = { 8, 4, 6, 12, 10 };
		for (Integer integer : data) {
			tree3.insert(integer);
		}
	}

	@Test
	public void testLLcount() {
		assertEquals(0, tree1.LLcount());
		assertEquals(6, tree2.LLcount());
		assertEquals(0, tree3.LLcount());
	}

	@Test
	public void testRRcount() {
		assertEquals(6, tree1.RRcount());
		assertEquals(0, tree2.RRcount());
		assertEquals(0, tree3.RRcount());
	}

	@Test
	public void testLRcount() {
		assertEquals(0, tree1.LRcount());
		assertEquals(0, tree2.LRcount());
		assertEquals(1, tree3.LRcount());
	}

	@Test
	public void testRLcount() {
		assertEquals(0, tree1.RLcount());
		assertEquals(0, tree2.RLcount());
		assertEquals(1, tree3.RLcount());
	}

	@Test
	public void testFillWithoutRebalance() {
		tree1 = new AVLCountAndFillImpl<Integer>();
		Integer[] keys = { 8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15 };
		tree1.fillWithoutRebalance(keys);
		assertEquals(0, tree1.LLcount());
		assertEquals(0, tree1.RRcount());
		assertEquals(0, tree1.LRcount());
		assertEquals(0, tree1.RLcount());
	}

	@Test
	public void testMultipleRotations() {
		AVLCountAndFill<Integer> tree = new AVLCountAndFillImpl<>();

		tree.insert(30);
		tree.insert(20);
		tree.insert(10);
		assertEquals(1, tree.LLcount());

		tree.insert(40);
		tree.insert(50);
		assertEquals(1, tree.RRcount());

		tree = new AVLCountAndFillImpl<>();
		tree.insert(30);
		tree.insert(10);
		tree.insert(20);
		assertEquals(1, tree.LRcount());

		tree = new AVLCountAndFillImpl<>();
		tree.insert(10);
		tree.insert(30);
		tree.insert(20);
		assertEquals(1, tree.RLcount());
	}

	@Test
	public void testCountersAfterRemovals() {
		AVLCountAndFill<Integer> tree = new AVLCountAndFillImpl<>();
		Integer[] values = { 50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45 };
		for (Integer v : values)
			tree.insert(v);

		int initialLL = tree.LLcount();
		int initialRR = tree.RRcount();

		tree.remove(80);
		assertTrue(tree.RRcount() >= initialRR);

		tree.remove(10);
		assertTrue(tree.LLcount() >= initialLL);
	}

	@Test
	public void testFillWithoutRebalanceWithDuplicates() {
		AVLCountAndFill<Integer> tree = new AVLCountAndFillImpl<>();
		Integer[] initial = { 10, 20, 30 };
		Integer[] newElements = { 20, 40, 50 };

		tree.fillWithoutRebalance(initial);
		tree.fillWithoutRebalance(newElements);

		assertEquals(0, tree.LLcount());
		assertEquals(0, tree.RRcount());
		assertEquals(0, tree.LRcount());
		assertEquals(0, tree.RLcount());

		Integer[] expected = { 10, 20, 30, 40, 50 };
		assertArrayEquals(expected, tree.order());
	}

	@Test
	public void testLargeTreeRotations() {
		AVLCountAndFill<Integer> tree2 = new AVLCountAndFillImpl<>();
		for (int i = 1; i <= 15; i++)
			tree2.insert(i);
		assertTrue(tree2.RRcount() > 0);

		AVLCountAndFill<Integer> tree3 = new AVLCountAndFillImpl<>();
		for (int i = 15; i >= 1; i--)
			tree3.insert(i);
		assertTrue(tree3.LLcount() > 0);
	}

	@Test
	public void testPrePostOrderConsistency() {
		AVLCountAndFill<Integer> tree = new AVLCountAndFillImpl<>();
		Integer[] values = { 8, 4, 12, 2, 6, 10, 14 };
		for (Integer v : values)
			tree.insert(v);

		Comparable[] preOrder = tree.preOrder();
		Comparable[] postOrder = tree.postOrder();

		for (Integer v : values) {
			assertTrue(contains(preOrder, v));
			assertTrue(contains(postOrder, v));
		}
	}

	private boolean contains(Comparable[] arr, Integer value) {
		for (Comparable v : arr)
			if (v.equals(value))
				return true;
		return false;
	}

}
