package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return this.height(this.root);
	}

	private int height(BSTNode<T> node){
		int height;

		if(node.isEmpty() || node.isLeaf()){
			height = -1;
		}else if(node.getRight() != null && node.getLeft() == null){
			height = 1 + height((BSTNode<T>) node.getRight());
		}else if(node.getLeft() != null && node.getRight() == null){
			height = 1 + height((BSTNode<T>) node.getLeft());
		}else{
			height = 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
		}
		return height;
	}

	@Override
	public BSTNode<T> search(T element) {
		return this.search(this.root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> answer = node;

		if(!node.isEmpty()){
			if(node.getData().compareTo(element) > 0){
				answer = search((BSTNode<T>) node.getLeft(), element);
			}else if (node.getData().compareTo(element) < 0){
				answer = search((BSTNode<T>) node.getRight(), element);
			}
		}
		return answer;
	}

	@Override
	public void insert(T element) {
		if(element != null){
			this.insert(this.root, element);
		}
	}

	private void insert(BSTNode<T> node, T element){
		if(element != null){
			if(node.isEmpty()){
				node.setData(element);
				BSTNode<T> leftNode = (BSTNode<T>) new BSTNode.Builder<T>()
					.data(null)
					.left(null)
					.right(null)
					.parent(node)
					.build();

				BSTNode<T> rightNode = (BSTNode<T>) new BSTNode.Builder<T>()
					.data(null)
					.left(null)
					.right(null)
					.parent(node)
					.build();
				node.setLeft(leftNode);
				node.setRight(rightNode);
			}else{
				if(element.compareTo(node.getData()) < 0){
					insert((BSTNode<T>) node.getLeft(), element);
				}else if (element.compareTo(node.getData()) > 0){
					insert((BSTNode<T>) node.getRight(), element);
				}
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return this.maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node){
		while(!node.getRight().isEmpty()){
			node = (BSTNode<T>) node.getRight();
		}
		return node;
	}

	@Override
	public BSTNode<T> minimum() {
		return this.minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node){
		while(!node.getLeft().isEmpty()){
			node = (BSTNode<T>) node.getLeft();
		}
		return node;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> elementNode = search(element);
		if(element != null){
			if(!elementNode.getRight().isEmpty()){
				elementNode = minimum((BSTNode<T>) elementNode.getRight());
			}else{
				BSTNode<T> parentElementNode = (BSTNode<T>) elementNode.getParent();
				while(parentElementNode != null && elementNode.equals(parentElementNode.getRight())){
					elementNode = parentElementNode;
					parentElementNode = (BSTNode<T>) parentElementNode.getParent();
				}
				elementNode = parentElementNode;
			}
		}
		return elementNode;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> elementNode = search(element);
		if(element != null){
			if(!elementNode.getLeft().isEmpty()){
				elementNode = maximum((BSTNode<T>) elementNode.getLeft());
			}else{
				BSTNode<T> parentElementNode = (BSTNode<T>) elementNode.getParent();
				while(parentElementNode != null && elementNode.equals(parentElementNode.getLeft())){
					elementNode = parentElementNode;
					parentElementNode = (BSTNode<T>) parentElementNode.getParent();
				}
				elementNode = parentElementNode;
			}
		}
		return elementNode;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> elementNode = search(element);
		if(elementNode != null){
			if(elementNode.isLeaf()){
				elementNode = null;
			}else if((elementNode.getLeft() != null && elementNode.getRight() == null) || (elementNode.getLeft() == null && elementNode.getRight() != null)){
				if(!elementNode.equals(this.root)){
					if(elementNode.getParent().getLeft().equals(elementNode)){
						if(elementNode.getLeft() != null){
							elementNode.getParent().setLeft(elementNode.getLeft());
							elementNode.getLeft().setParent(elementNode.getParent());
						}else{
							elementNode.getParent().setLeft(elementNode.getRight());
							elementNode.getRight().setParent(elementNode.getParent());
						}
					}else{ // right child
						if(elementNode.getLeft() != null){
							elementNode.getParent().setRight(elementNode.getLeft());
							elementNode.getLeft().setParent(elementNode.getParent());
						}else{
							elementNode.getParent().setRight(elementNode.getRight());
							elementNode.getRight().setParent(elementNode.getParent());
						}
					}
				}else{
					
				}
			}
		}
	}

	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(this.root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}
}
