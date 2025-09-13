package adt.bst;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {

	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}

	private BSTImpl<T> getBST() {
		return this.bst;
	}

	@Override
	public boolean isBST() {
		return isBST(this.getBST().getRoot());
	}

	private boolean isValid(BSTNode<T> node) {
		return node != null && !node.isEmpty();
	}

	private boolean isBST(BSTNode<T> node) {
		return !isValid(node) || (verifyLeft(node)
				&& verifyRight(node)
				&& isBST((BSTNode<T>) node.getLeft())
				&& isBST((BSTNode<T>) node.getRight()));
	}

	private boolean verifyLeft(BSTNode<T> node) {
		return verifyLeft(node, (BSTNode<T>) node.getLeft());
	}

	// verifiyLeft is about the "left" data being smaller than the node
	private boolean verifyLeft(BSTNode<T> node, BSTNode<T> left) {
		return !isValid(left) || (left.getData().compareTo(node.getData()) < 0
				&& verifyLeft(node, (BSTNode<T>) left.getLeft())
				&& verifyLeft(node, (BSTNode<T>) left.getRight()));
	}

	private boolean verifyRight(BSTNode<T> node) {
		return verifyRight(node, (BSTNode<T>) node.getRight());
	}

	// verifiyRight is about the "right" data being greater than the node data
	private boolean verifyRight(BSTNode<T> node, BSTNode<T> right) {
		return !isValid(right) || (right.getData().compareTo(node.getData()) > 0
				&& verifyRight(node, (BSTNode<T>) right.getLeft())
				&& verifyRight(node, (BSTNode<T>) right.getRight()));
	}
}
