package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		BSTImpl<Integer> tree = this.createTree(array);
		return floor(tree.getRoot(), numero);
	}

	private Integer floor(BSTNode<Integer> node, double numero) {
		Integer answer = null;
		if(!node.isEmpty()){
			if(node.getData().doubleValue() == numero){
				answer = node.getData();
			}else if (node.getData() > numero){
				answer = floor((BSTNode<Integer>) node.getLeft(), numero);
			}else{
				Integer rightFloor = floor((BSTNode<Integer>) node.getRight(), numero);
        if (rightFloor != null) {
          answer = rightFloor;
        } else {
          answer = node.getData();
        }
			}
		}
		return answer;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		BSTImpl<Integer> tree = this.createTree(array);
		return ceil(tree.getRoot(), numero);
	}

	private Integer ceil(BSTNode<Integer> node, double numero) {
		Integer answer = null;
		if(!node.isEmpty()){
			if(node.getData().doubleValue() == numero){
				answer = node.getData();
			}else if (node.getData() < numero){
				answer = ceil((BSTNode<Integer>) node.getRight(), numero);
			}else{
				Integer leftCeil = ceil((BSTNode<Integer>) node.getLeft(), numero);
        if (leftCeil != null) {
          answer = leftCeil;
        } else {
          answer = node.getData();
        }
			}
		}
		return answer;
	}

	private BSTImpl<Integer> createTree(Integer[] array){
		BSTImpl<Integer> tree = new BSTImpl<>();
		for (int i : array) {
			tree.insert(i);
		}
		return tree;
	}
}
