package adt.avltree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTNode;

public class AVLTreeVerifierTest {

  private AVLTree<Integer> avl;
  private AVLTreeVerifier<Integer> verifier;

  @Before
  public void setUp() {
    avl = new AVLTreeImpl<>();
    verifier = new AVLTreeVerifierImpl<>(avl);
  }

  @Test
  public void testEmptyTreeIsAVL() {
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testSingleElementTreeIsAVL() {
    avl.insert(10);
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testBalancedTreeIsAVL() {
    Integer[] elements = { 20, 10, 30, 5, 15, 25, 35 };
    for (Integer elem : elements) {
      avl.insert(elem);
    }
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testTreeAfterRemovalsIsAVL() {
    Integer[] elements = { 20, 10, 30, 5, 15, 25, 35 };
    for (Integer elem : elements) {
      avl.insert(elem);
    }
    avl.remove(5);
    avl.remove(30);
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testLLRotationMaintainsAVL() {
    avl.insert(30);
    avl.insert(20);
    avl.insert(10);
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testRRRotationMaintainsAVL() {
    avl.insert(10);
    avl.insert(20);
    avl.insert(30);
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testLRRotationMaintainsAVL() {
    avl.insert(30);
    avl.insert(10);
    avl.insert(20);
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testRLRotationMaintainsAVL() {
    avl.insert(10);
    avl.insert(30);
    avl.insert(20);
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testTreeWithDuplicatesIsAVL() {
    avl.insert(10);
    avl.insert(10);
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testRotationsMaintainAVL() {
    AVLTree<Integer> treeLL = new AVLTreeImpl<>();
    treeLL.insert(30);
    treeLL.insert(20);
    treeLL.insert(10);
    verifier = new AVLTreeVerifierImpl<>(treeLL);
    assertTrue(verifier.isAVLTree());

    AVLTree<Integer> treeRR = new AVLTreeImpl<>();
    treeRR.insert(10);
    treeRR.insert(20);
    treeRR.insert(30);
    verifier = new AVLTreeVerifierImpl<>(treeRR);
    assertTrue(verifier.isAVLTree());

    AVLTree<Integer> treeLR = new AVLTreeImpl<>();
    treeLR.insert(30);
    treeLR.insert(10);
    treeLR.insert(20);
    verifier = new AVLTreeVerifierImpl<>(treeLR);
    assertTrue(verifier.isAVLTree());

    AVLTree<Integer> treeRL = new AVLTreeImpl<>();
    treeRL.insert(10);
    treeRL.insert(30);
    treeRL.insert(20);
    verifier = new AVLTreeVerifierImpl<>(treeRL);
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testComplexTreeIsAVL() {
    Integer[] elements = { 50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45, 55, 65, 75, 85 };
    for (Integer elem : elements) {
      avl.insert(elem);
    }
    assertTrue(verifier.isAVLTree());

    avl.remove(10);
    avl.remove(70);
    avl.remove(55);
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testAVLAfterRemovals() {
    Integer[] elements = { 50, 30, 70, 20, 40, 60, 80 };
    for (Integer e : elements)
      avl.insert(e);
    avl.remove(20);
    avl.remove(70);
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testTreeWithDuplicates() {
    avl.insert(10);
    avl.insert(10);
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testLeftChildGreaterThanRoot() {
    avl.insert(10);
    avl.insert(5);
    avl.insert(15);
    BSTNode<Integer> root = (BSTNode<Integer>) avl.getRoot();
    BSTNode<Integer> left = (BSTNode<Integer>) root.getLeft();
    left.setData(20);
    assertFalse(verifier.isAVLTree());
  }

  @Test
  public void testRightChildLessThanRoot() {
    avl.insert(10);
    avl.insert(5);
    avl.insert(15);
    BSTNode<Integer> root = (BSTNode<Integer>) avl.getRoot();
    BSTNode<Integer> right = (BSTNode<Integer>) root.getRight();
    right.setData(5);
    assertFalse(verifier.isAVLTree());
  }

  @Test
  public void testSubtreeBSTViolation() {
    Integer[] values = { 50, 30, 70, 20, 40, 60, 80 };
    for (Integer v : values)
      avl.insert(v);
    BSTNode<Integer> root = (BSTNode<Integer>) avl.getRoot();
    BSTNode<Integer> left = (BSTNode<Integer>) root.getLeft();
    BSTNode<Integer> leftRight = (BSTNode<Integer>) left.getRight();
    leftRight.setData(55);
    assertFalse(verifier.isAVLTree());
  }

  private BSTNode<Integer> insertManual(BSTNode<Integer> parent, Integer data, boolean leftChild) {
    BSTNode<Integer> newNode = new BSTNode.Builder<Integer>()
        .data(data)
        .left(new BSTNode<>())
        .right(new BSTNode<>())
        .parent(parent)
        .build();

    if (leftChild) {
      parent.setLeft(newNode);
    } else {
      parent.setRight(newNode);
    }
    return newNode;
  }

  @Test
  public void testManualNotBST() {
    AVLCountAndFill<Integer> tree = new AVLCountAndFillImpl<>();
    tree.insert(20);
    tree.insert(10);
    tree.insert(30);

    BSTNode<Integer> root = ((AVLTreeImpl<Integer>) tree).getRoot();

    ((BSTNode<Integer>) root.getLeft()).setData(25);

    AVLTreeVerifier<Integer> verifier = new AVLTreeVerifierImpl<>(tree);
    assertFalse(verifier.isAVLTree());
  }

  @Test
  public void testVeryLeftHeavy() {
    avl.insert(50);
    avl.insert(40);
    avl.insert(30);
    avl.insert(20);
    avl.insert(10);
    BSTNode<Integer> root = (BSTNode<Integer>) avl.getRoot();
    insertManual((BSTNode<Integer>) root.getLeft().getLeft(), 5, true);
    assertFalse(verifier.isAVLTree());
  }

  @Test
  public void testVeryRightHeavy() {
    avl.insert(10);
    avl.insert(20);
    avl.insert(30);
    avl.insert(40);
    avl.insert(50);
    BSTNode<Integer> root = (BSTNode<Integer>) avl.getRoot();
    insertManual((BSTNode<Integer>) root.getRight().getRight(), 55, false);
    assertFalse(verifier.isAVLTree());
  }

  @Test
  public void testBalancedTreeRemainsAVL() {
    Integer[] elements = { 50, 30, 70, 20, 40, 60, 80 };
    for (Integer e : elements)
      avl.insert(e);
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testComplexTreeAfterRemovalsStillAVL() {
    Integer[] elements = { 50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45 };
    for (Integer e : elements)
      avl.insert(e);
    avl.remove(10);
    avl.remove(70);
    avl.remove(25);
    assertTrue(verifier.isAVLTree());
  }

  @Test
  public void testUnbalancedTreeNotAVL() {
    AVLCountAndFill<Integer> tree = new AVLCountAndFillImpl<>();
    tree.insert(50);

    BSTNode<Integer> root = ((AVLTreeImpl<Integer>) tree).getRoot();
    BSTNode<Integer> left = insertManual(root, 40, true);
    BSTNode<Integer> leftLeft = insertManual(left, 30, true);
    BSTNode<Integer> leftLeftLeft = insertManual(leftLeft, 20, true);
    insertManual(leftLeftLeft, 10, true);

    AVLTreeVerifier<Integer> verifier = new AVLTreeVerifierImpl<>(tree);
    assertFalse(verifier.isAVLTree());
  }

  @Test
  public void testBalanceFactorViolation() {
    AVLCountAndFill<Integer> tree = new AVLCountAndFillImpl<>();
    tree.insert(100);

    BSTNode<Integer> root = ((AVLTreeImpl<Integer>) tree).getRoot();
    BSTNode<Integer> left = insertManual(root, 50, true);
    BSTNode<Integer> right = insertManual(root, 150, false);

    insertManual(left, 25, true);
    insertManual(left, 75, false);
    insertManual((BSTNode<Integer>) left.getLeft(), 10, true);

    AVLTreeVerifier<Integer> verifier = new AVLTreeVerifierImpl<>(tree);
    assertFalse(verifier.isAVLTree());
  }

  @Test
  public void testBSTViolationWithManualInsert() {
    AVLCountAndFill<Integer> tree = new AVLCountAndFillImpl<>();
    tree.insert(50);

    BSTNode<Integer> root = ((AVLTreeImpl<Integer>) tree).getRoot();
    BSTNode<Integer> left = insertManual(root, 60, true);
    insertManual(left, 55, true);

    AVLTreeVerifier<Integer> verifier = new AVLTreeVerifierImpl<>(tree);
    assertFalse(verifier.isAVLTree());
  }

  @Test
  public void testZigZagUnbalanced() {
    AVLCountAndFill<Integer> tree = new AVLCountAndFillImpl<>();
    tree.insert(50);

    BSTNode<Integer> root = ((AVLTreeImpl<Integer>) tree).getRoot();
    BSTNode<Integer> left = insertManual(root, 30, true);
    BSTNode<Integer> leftRight = insertManual(left, 40, false);
    insertManual(leftRight, 35, true);

    AVLTreeVerifier<Integer> verifier = new AVLTreeVerifierImpl<>(tree);
    assertFalse(verifier.isAVLTree());
  }

  @Test
  public void testMultipleViolations() {
    AVLCountAndFill<Integer> tree = new AVLCountAndFillImpl<>();
    tree.insert(100);

    BSTNode<Integer> root = ((AVLTreeImpl<Integer>) tree).getRoot();

    BSTNode<Integer> wrongLeft = insertManual(root, 120, true);
    BSTNode<Integer> wrongRight = insertManual(root, 90, false);

    insertManual(wrongLeft, 130, true);
    insertManual(wrongLeft, 140, false);

    AVLTreeVerifier<Integer> verifier = new AVLTreeVerifierImpl<>(tree);
    assertFalse(verifier.isAVLTree());
  }

  @Test
  public void testNullNodeConfiguration() {
    AVLCountAndFill<Integer> tree = new AVLCountAndFillImpl<>();
    tree.insert(50);

    BSTNode<Integer> root = ((AVLTreeImpl<Integer>) tree).getRoot();

    BSTNode<Integer> problematicNode = new BSTNode.Builder<Integer>()
        .data(30)
        .left(new BSTNode<>())
        .right(new BSTNode<>())
        .parent(root)
        .build();

    root.setLeft(problematicNode);

    BSTNode<Integer> invalidNil = new BSTNode.Builder<Integer>()
        .data(5)
        .left(null)
        .right(null)
        .parent(problematicNode)
        .build();

    problematicNode.setLeft(invalidNil);

    AVLTreeVerifier<Integer> verifier = new AVLTreeVerifierImpl<>(tree);
    assertFalse(verifier.isAVLTree());
  }

  @Test
  public void testInvalidBSTStructure() {
    AVLCountAndFill<Integer> tree = new AVLCountAndFillImpl<>();
    tree.insert(50);

    BSTNode<Integer> root = ((AVLTreeImpl<Integer>) tree).getRoot();

    BSTNode<Integer> leftChild = new BSTNode.Builder<Integer>()
        .data(30)
        .left(new BSTNode<>())
        .right(new BSTNode<>())
        .parent(root)
        .build();

    root.setLeft(leftChild);

    BSTNode<Integer> rightChildViolation = new BSTNode.Builder<Integer>()
        .data(25)
        .left(new BSTNode<>())
        .right(new BSTNode<>())
        .parent(leftChild)
        .build();

    leftChild.setRight(rightChildViolation);

    AVLTreeVerifier<Integer> verifier = new AVLTreeVerifierImpl<>(tree);
    assertFalse(verifier.isAVLTree());
  }

  @Test
  public void testInvalidBalanceFactor() {
    AVLCountAndFill<Integer> tree = new AVLCountAndFillImpl<>();
    tree.insert(50);

    BSTNode<Integer> root = ((AVLTreeImpl<Integer>) tree).getRoot();
    BSTNode<Integer> heavyLeft = new BSTNode.Builder<Integer>()
        .data(30)
        .left(new BSTNode<>())
        .right(new BSTNode<>())
        .parent(root)
        .build();

    root.setLeft(heavyLeft);

    BSTNode<Integer> level2 = new BSTNode.Builder<Integer>()
        .data(20)
        .left(new BSTNode<>())
        .right(new BSTNode<>())
        .parent(heavyLeft)
        .build();

    heavyLeft.setLeft(level2);

    BSTNode<Integer> level3 = new BSTNode.Builder<Integer>()
        .data(10)
        .left(new BSTNode<>())
        .right(new BSTNode<>())
        .parent(level2)
        .build();

    level2.setLeft(level3);

    BSTNode<Integer> level4 = new BSTNode.Builder<Integer>()
        .data(5)
        .left(new BSTNode<>())
        .right(new BSTNode<>())
        .parent(level3)
        .build();

    level3.setLeft(level4);

    AVLTreeVerifier<Integer> verifier = new AVLTreeVerifierImpl<>(tree);
    assertFalse(verifier.isAVLTree());
  }
}
