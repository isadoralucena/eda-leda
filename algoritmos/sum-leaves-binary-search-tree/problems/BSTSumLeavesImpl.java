package problems;

import adt.bst.BST;
import adt.bst.BSTNode;

public class BSTSumLeavesImpl implements BSTSumLeaves{
  public int sumLeaves(BST<Integer> bst){
    int answer = 0;
    if(!bst.isEmpty()){
      answer = sumLeaves((BSTNode<Integer>) bst.getRoot());
    }
    return answer;
  }

  private int sumLeaves(BSTNode<Integer> node){
    int sum = 0;
    if(!node.isEmpty()){
      if(node.isLeaf()){
        sum += node.getData();
      } else{
        sum += sumLeaves((BSTNode<Integer>) node.getLeft());
        sum += sumLeaves((BSTNode<Integer>) node.getRight());
      }
    }
    return sum;
  }
}
