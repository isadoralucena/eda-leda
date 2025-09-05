package adt.bst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTImpl;
import adt.bst.extended.FloorCeilBSTImpl;
import adt.bt.BTNode;

public class StudentBSTTest<T> {

	private BSTImpl<Integer> tree;
	private BTNode<Integer> NIL = new BTNode<Integer>();
	private SimpleBSTManipulation<Integer> bstManipulation;
	private FloorCeilBSTImpl floorCeil;
	private Integer[] treeArray = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };

	private void fillTree() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}
	}

	private BSTImpl<Integer> createTree(Integer... values) {
    BSTImpl<Integer> newTree = new BSTImpl<>();
    for (Integer v : values) {
        newTree.insert(v);
    }
    return newTree;
	}

	@Before
	public void setUp() {
		tree = new BSTImpl<>();
		bstManipulation = new SimpleBSTManipulationImpl<>();
		floorCeil = new FloorCeilBSTImpl();
	}

	@Test
	public void testInit() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertEquals(-1, tree.height());

		assertEquals(NIL, tree.getRoot());

		assertArrayEquals(new Integer[] {}, tree.order());
		assertArrayEquals(new Integer[] {}, tree.preOrder());
		assertArrayEquals(new Integer[] {}, tree.postOrder());

		assertEquals(NIL, tree.search(12));
		assertEquals(NIL, tree.search(-23));
		assertEquals(NIL, tree.search(0));

		assertEquals(null, tree.minimum());
		assertEquals(null, tree.maximum());

		assertEquals(null, tree.sucessor(12));
		assertEquals(null, tree.sucessor(-23));
		assertEquals(null, tree.sucessor(0));

		assertEquals(null, tree.predecessor(12));
		assertEquals(null, tree.predecessor(-23));
		assertEquals(null, tree.predecessor(0));
	}

	@Test
	public void testMinMax() {
		tree.insert(6);
		assertEquals(Integer.valueOf(6), tree.minimum().getData());
		assertEquals(Integer.valueOf(6), tree.maximum().getData());

		tree.insert(23);
		assertEquals(Integer.valueOf(6), tree.minimum().getData());
		assertEquals(Integer.valueOf(23), tree.maximum().getData());

		tree.insert(-34);
		assertEquals(Integer.valueOf(-34), tree.minimum().getData());
		assertEquals(Integer.valueOf(23), tree.maximum().getData());

		tree.insert(5);
		assertEquals(Integer.valueOf(-34), tree.minimum().getData());
		assertEquals(Integer.valueOf(23), tree.maximum().getData());

		tree.insert(9);
		assertEquals(Integer.valueOf(-34), tree.minimum().getData());
		assertEquals(Integer.valueOf(23), tree.maximum().getData());
	}

	@Test
	public void testSucessorPredecessor() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertNull(tree.predecessor(-40));
		assertEquals(Integer.valueOf(-34), tree.sucessor(-40).getData());

		assertEquals(Integer.valueOf(-40), tree.predecessor(-34).getData());
		assertEquals(Integer.valueOf(0), tree.sucessor(-34).getData());

		assertEquals(Integer.valueOf(-34), tree.predecessor(0).getData());
		assertEquals(Integer.valueOf(2), tree.sucessor(0).getData());

		assertEquals(Integer.valueOf(0), tree.predecessor(2).getData());
		assertEquals(Integer.valueOf(5), tree.sucessor(2).getData());
	}

	@Test
	public void testSize() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		int size = treeArray.length;
		assertEquals(size, tree.size());

		while (!tree.isEmpty()) {
			tree.remove(tree.getRoot().getData());
			assertEquals(--size, tree.size());
		}
	}

	@Test
	public void testHeight() {
		fillTree();
		assertEquals(4, tree.height());

		tree.remove(0);
		assertEquals(3, tree.height());

		tree.remove(2);
		assertEquals(3, tree.height());
	}

	@Test
	public void testRemove() {
		fillTree();
		Integer[] order = { -40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(6);
		order = new Integer[] { -40, -34, 0, 2, 5, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(9);
		order = new Integer[] { -40, -34, 0, 2, 5, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		assertEquals(NIL, tree.search(6));
    assertEquals(NIL, tree.search(9));
	}

	@Test
	public void testSearch() {
		fillTree();
		assertEquals(Integer.valueOf(-40), tree.search(-40).getData());
		assertEquals(Integer.valueOf(-34), tree.search(-34).getData());
		assertEquals(Integer.valueOf(23), tree.search(23).getData());
		assertEquals(Integer.valueOf(0), tree.search(0).getData());
		assertEquals(NIL, tree.search(2534));
	}

	@Test
	public void testEquals(){
		BSTImpl<Integer> tree1 = createTree(treeArray);
		BSTImpl<Integer> tree2 = createTree(treeArray);
		BSTImpl<Integer> tree3 = createTree(6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -50);

		assertTrue(bstManipulation.equals(tree1, tree2));
		assertFalse(bstManipulation.equals(tree1, tree3));
		assertFalse(bstManipulation.equals(tree2, tree3));
	}

	@Test
	public void testIsSimilar(){
		BSTImpl<Integer> tree1 = createTree(6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40);
    BSTImpl<Integer> tree2 = createTree(60, 230, -340, 50, 90, 20, 10, 760, 120, 670, 2320, -400);
    assertTrue(bstManipulation.isSimilar(tree1, tree2));

		BSTImpl<Integer> tree3 = createTree(6, 23, -34, 5, 9, 2, 0, 76);
    assertFalse(bstManipulation.isSimilar(tree1, tree3));

		BSTImpl<Integer> tree4 = createTree(23, 6, -34, 5, 9, 2, 0, 76, 12, 67, 232, -50);
    assertFalse(bstManipulation.isSimilar(tree1, tree4));

		BSTImpl<Integer> empty1 = createTree();
    BSTImpl<Integer> empty2 = createTree();
    assertTrue(bstManipulation.isSimilar(empty1, empty2));

    assertFalse(bstManipulation.isSimilar(tree1, empty1));
	}

	@Test
	public void testOrderStatistic(){
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232
		assertEquals(Integer.valueOf(-40), bstManipulation.orderStatistic(tree, 1));
    assertEquals(Integer.valueOf(-34), bstManipulation.orderStatistic(tree, 2));
    assertEquals(Integer.valueOf(0), bstManipulation.orderStatistic(tree, 3));

		assertEquals(Integer.valueOf(6), bstManipulation.orderStatistic(tree, 6));
    assertEquals(Integer.valueOf(9), bstManipulation.orderStatistic(tree, 7));

		assertEquals(Integer.valueOf(76), bstManipulation.orderStatistic(tree, 11));
    assertEquals(Integer.valueOf(232), bstManipulation.orderStatistic(tree, 12)); 

		assertNull(bstManipulation.orderStatistic(tree, 0));
    assertNull(bstManipulation.orderStatistic(tree, 13));
	}

	@Test
public void testFloorCeil() {
	Integer[] treeArray = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };

	assertEquals(Integer.valueOf(-40), floorCeil.floor(treeArray, -39.5));
	assertEquals(Integer.valueOf(-34), floorCeil.floor(treeArray, -34.0));
	assertEquals(Integer.valueOf(0), floorCeil.floor(treeArray, 1.5));
	assertEquals(Integer.valueOf(9), floorCeil.floor(treeArray, 10));
	assertEquals(Integer.valueOf(232), floorCeil.floor(treeArray, 300));
	assertNull(floorCeil.floor(treeArray, -100));                           

	assertEquals(Integer.valueOf(-34), floorCeil.ceil(treeArray, -35));    
	assertEquals(Integer.valueOf(0), floorCeil.ceil(treeArray, 0));        
	assertEquals(Integer.valueOf(2), floorCeil.ceil(treeArray, 1.5)); 
	assertEquals(Integer.valueOf(12), floorCeil.ceil(treeArray, 10));
	assertEquals(Integer.valueOf(232), floorCeil.ceil(treeArray, 232));    
	assertNull(floorCeil.ceil(treeArray, 300));
	}

	@Test
	public void testPreOrderEmptyTree() {
		assertArrayEquals(new Integer[] {}, tree.preOrder());
	}

	@Test
	public void testInOrderEmptyTree() {
		assertArrayEquals(new Integer[] {}, tree.order());
	}

	@Test
	public void testPostOrderEmptyTree() {
		assertArrayEquals(new Integer[] {}, tree.postOrder());
	}

	@Test
	public void testTraversalsFilledTree() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] expectedPreOrder = { 6, -34, -40, 5, 2, 0, 23, 9, 12, 76, 67, 232 };
		assertArrayEquals(expectedPreOrder, tree.preOrder());

		Integer[] expectedInOrder = { -40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(expectedInOrder, tree.order());

		Integer[] expectedPostOrder = { -40, 0, 2, 5, -34, 12, 9, 67, 232, 76, 23, 6 };
		assertArrayEquals(expectedPostOrder, tree.postOrder());
	}

}
