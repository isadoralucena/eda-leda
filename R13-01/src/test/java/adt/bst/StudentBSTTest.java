package adt.bst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTImpl;
import adt.bt.BTNode;

public class StudentBSTTest<T> {

	private BSTImpl<Integer> tree;
	private BTNode<Integer> NIL = new BTNode<Integer>();
	private BSTVerifierImpl verifier;
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

	@Test
	public void testNotBST() {
		BSTNode<Integer> left = new BSTNode.Builder<Integer>()
				.data(5)
				.left(null)
				.right(null)
				.parent(null)
				.build();

		BSTNode<Integer> wrongLeft = new BSTNode.Builder<Integer>()
				.data(6)
				.left(null)
				.right(null)
				.parent(null)
				.build();

		BSTNode<Integer> right = new BSTNode.Builder<Integer>()
				.data(15)
				.left(wrongLeft)
				.right(null)
				.parent(null)
				.build();

		BSTNode<Integer> root = new BSTNode.Builder<Integer>()
				.data(10)
				.left(left)
				.right(right)
				.parent(null)
				.build();

		BSTImpl<Integer> badTree = new BSTImpl<>();
		badTree.root = root;
		verifier = new BSTVerifierImpl<>(badTree);

		assertFalse(verifier.isBST());
	}

	@Test
	public void testEmptyTreeIsBST() {
		BSTImpl<Integer> emptyTree = new BSTImpl<>();
		verifier = new BSTVerifierImpl<>(emptyTree);
		assertTrue(verifier.isBST());
	}

	@Test
	public void testSingleNodeTreeIsBST() {
		BSTNode<Integer> root = new BSTNode.Builder<Integer>()
				.data(10)
				.left(null)
				.right(null)
				.parent(null)
				.build();

		BSTImpl<Integer> singleNodeTree = new BSTImpl<>();
		singleNodeTree.root = root;
		verifier = new BSTVerifierImpl<>(singleNodeTree);
		assertTrue(verifier.isBST());
	}

	@Test
	public void testRightChildSmallerThanRoot() {
		BSTNode<Integer> left = new BSTNode.Builder<Integer>()
				.data(5)
				.left(null)
				.right(null)
				.parent(null)
				.build();

		BSTNode<Integer> right = new BSTNode.Builder<Integer>()
				.data(3)
				.left(null)
				.right(null)
				.parent(null)
				.build();

		BSTNode<Integer> root = new BSTNode.Builder<Integer>()
				.data(10)
				.left(left)
				.right(right)
				.parent(null)
				.build();

		BSTImpl<Integer> tree = new BSTImpl<>();
		tree.root = root;
		verifier = new BSTVerifierImpl<>(tree);

		assertFalse(verifier.isBST());
	}

	@Test
	public void testLeftChildGreaterThanRoot() {
		BSTNode<Integer> left = new BSTNode.Builder<Integer>()
				.data(15)
				.left(null)
				.right(null)
				.parent(null)
				.build();

		BSTNode<Integer> right = new BSTNode.Builder<Integer>()
				.data(20)
				.left(null)
				.right(null)
				.parent(null)
				.build();

		BSTNode<Integer> root = new BSTNode.Builder<Integer>()
				.data(10)
				.left(left)
				.right(right)
				.parent(null)
				.build();

		BSTImpl<Integer> tree = new BSTImpl<>();
		tree.root = root;
		verifier = new BSTVerifierImpl<>(tree);

		assertFalse(verifier.isBST());
	}

	@Test
	public void testDeepSubtreeViolation() {
		BSTNode<Integer> leftLeft = new BSTNode.Builder<Integer>()
				.data(2)
				.left(null)
				.right(null)
				.parent(null)
				.build();

		BSTNode<Integer> leftRight = new BSTNode.Builder<Integer>()
				.data(12)
				.left(null)
				.right(null)
				.parent(null)
				.build();

		BSTNode<Integer> left = new BSTNode.Builder<Integer>()
				.data(5)
				.left(leftLeft)
				.right(leftRight)
				.parent(null)
				.build();

		BSTNode<Integer> right = new BSTNode.Builder<Integer>()
				.data(15)
				.left(null)
				.right(null)
				.parent(null)
				.build();

		BSTNode<Integer> root = new BSTNode.Builder<Integer>()
				.data(10)
				.left(left)
				.right(right)
				.parent(null)
				.build();

		BSTImpl<Integer> tree = new BSTImpl<>();
		tree.root = root;
		verifier = new BSTVerifierImpl<>(tree);

		assertFalse(verifier.isBST());
	}

	@Test
	public void testValidBST() {
		BSTNode<Integer> leftLeft = new BSTNode.Builder<Integer>()
				.data(2)
				.left(null)
				.right(null)
				.parent(null)
				.build();

		BSTNode<Integer> leftRight = new BSTNode.Builder<Integer>()
				.data(7)
				.left(null)
				.right(null)
				.parent(null)
				.build();

		BSTNode<Integer> left = new BSTNode.Builder<Integer>()
				.data(5)
				.left(leftLeft)
				.right(leftRight)
				.parent(null)
				.build();

		BSTNode<Integer> rightLeft = new BSTNode.Builder<Integer>()
				.data(12)
				.left(null)
				.right(null)
				.parent(null)
				.build();

		BSTNode<Integer> rightRight = new BSTNode.Builder<Integer>()
				.data(20)
				.left(null)
				.right(null)
				.parent(null)
				.build();

		BSTNode<Integer> right = new BSTNode.Builder<Integer>()
				.data(15)
				.left(rightLeft)
				.right(rightRight)
				.parent(null)
				.build();

		BSTNode<Integer> root = new BSTNode.Builder<Integer>()
				.data(10)
				.left(left)
				.right(right)
				.parent(null)
				.build();

		BSTImpl<Integer> tree = new BSTImpl<>();
		tree.root = root;
		verifier = new BSTVerifierImpl<>(tree);

		assertTrue(verifier.isBST());
	}

}
