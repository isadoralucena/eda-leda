package adt.bst;

import java.util.ArrayList;

import adt.bt.BT;

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
		
		if(node.isEmpty()){
			height = -1;
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

		if(!node.isEmpty() && element != null){
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
		BSTNode<T> maximum = null;
		if(!this.isEmpty()){
			maximum =  this.maximum(this.root);
		}
		return maximum;
	}

	private BSTNode<T> maximum(BSTNode<T> node){
		while(!node.getRight().isEmpty()){
			node = (BSTNode<T>) node.getRight();
		}
		return node;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> minimum = null;
		if(!this.isEmpty()){
			minimum =  this.minimum(this.root);
		}
		return minimum;
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
		BSTNode<T> successor = null;

		if(element != null && !elementNode.isEmpty()){
			if(!elementNode.getRight().isEmpty()){
				successor = minimum((BSTNode<T>) elementNode.getRight());
			}else{
				BSTNode<T> parentElementNode = (BSTNode<T>) elementNode.getParent();
				while(parentElementNode != null && elementNode.equals(parentElementNode.getRight())){
					elementNode = parentElementNode;
					parentElementNode = (BSTNode<T>) parentElementNode.getParent();
				}
				successor = parentElementNode;
			}
		}
		return successor;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> elementNode = search(element);
		BSTNode<T> predecessor = null;

		if(element != null && !elementNode.isEmpty()){
			if(!elementNode.getLeft().isEmpty()){
				predecessor = maximum((BSTNode<T>) elementNode.getLeft());
			}else{
				BSTNode<T> parentElementNode = (BSTNode<T>) elementNode.getParent();
				while(parentElementNode != null && elementNode.equals(parentElementNode.getLeft())){
					elementNode = parentElementNode;
					parentElementNode = (BSTNode<T>) parentElementNode.getParent();
				}
				predecessor = parentElementNode;
			}
		}
		return predecessor;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> elementNode = search(element);
		if(element != null && !elementNode.isEmpty()){
			if(elementNode.isLeaf()){
				elementNode.setData(null);
				elementNode.setLeft(null);
				elementNode.setRight(null);
			}else if((!elementNode.getLeft().isEmpty() && elementNode.getRight().isEmpty()) || (elementNode.getLeft().isEmpty() && !elementNode.getRight().isEmpty())){
				if(!elementNode.equals(this.root)){
					if(elementNode.getParent().getLeft().equals(elementNode)){
						if(!elementNode.getLeft().isEmpty()){
							elementNode.getParent().setLeft(elementNode.getLeft());
							elementNode.getLeft().setParent(elementNode.getParent());
						}else{
							elementNode.getParent().setLeft(elementNode.getRight());
							elementNode.getRight().setParent(elementNode.getParent());
						}
					}else{
						if(!elementNode.getLeft().isEmpty()){
							elementNode.getParent().setRight(elementNode.getLeft());
							elementNode.getLeft().setParent(elementNode.getParent());
						}else{
							elementNode.getParent().setRight(elementNode.getRight());
							elementNode.getRight().setParent(elementNode.getParent());
						}
					}
				}else{
					if(!elementNode.getLeft().isEmpty()){
						elementNode.getLeft().setParent(null);
						this.root = (BSTNode<T>) elementNode.getLeft();
					}else{
						elementNode.getRight().setParent(null);
						this.root = (BSTNode<T>) elementNode.getRight();
					}
				}
			}else{
				T successor = sucessor(elementNode.getData()).getData();
				remove(successor);
				elementNode.setData(successor);
			}
		}
	}

	@Override
	public T[] preOrder() {
		ArrayList<T> list = new ArrayList<>();
		preOrder(this.root, list);
		T[] array = (T[]) new Comparable[list.size()];
    return list.toArray(array);
	}

	private void preOrder(BSTNode<T> node, ArrayList<T> preOrder){
		if(!node.isEmpty()){
			preOrder.add(node.getData());
			preOrder((BSTNode<T>) node.getLeft(), preOrder);
			preOrder((BSTNode<T>) node.getRight(), preOrder);
		}
	}

	@Override
	public T[] order() {
		ArrayList<T> list = new ArrayList<>();
		order(this.root, list);
		T[] array = (T[]) new Comparable[list.size()];
    return list.toArray(array);
	}

	private void order(BSTNode<T> node, ArrayList<T> order){
		if(!node.isEmpty()){
			order((BSTNode<T>) node.getLeft(), order);
			order.add(node.getData());
			order((BSTNode<T>) node.getRight(), order);
		}
	}

	@Override
	public T[] postOrder() {
		ArrayList<T> list = new ArrayList<>();
		postOrder(this.root, list);
		T[] array = (T[]) new Comparable[list.size()];
    return list.toArray(array);
	}

	private void postOrder(BSTNode<T> node, ArrayList<T> postOrder){
		if(!node.isEmpty()){
			postOrder((BSTNode<T>) node.getLeft(), postOrder);
			postOrder((BSTNode<T>) node.getRight(), postOrder);
			postOrder.add(node.getData());
		}
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
