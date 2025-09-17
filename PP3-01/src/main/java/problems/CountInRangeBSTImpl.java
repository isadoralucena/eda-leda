package problems;

import java.util.LinkedList;
import java.util.List;

import adt.bst.BSTNode;

public class CountInRangeBSTImpl implements CountInRange {

  protected BSTNode<Integer> root;

  public CountInRangeBSTImpl() {
    this.root = new BSTNode<Integer>();
  }

  @Override
  public int countInRange(Integer[] array, Integer from, Integer to) {
    int answer = 0;
    if (array != null && array.length > 0 && from != null && to != null) {
      for (Integer elem : array) {
        this.insert(elem);
      }
      answer = this.countInRange(this.root, from, to);
    }
    return answer;
  }

  private int countInRange(BSTNode<Integer> node, Integer from, Integer to) {
    int sum = 0;
    if (!node.isEmpty()) {
      sum += countInRange((BSTNode<Integer>) node.getLeft(), from, to);

      if (node.getData() >= from && node.getData() <= to) {
        sum += 1;
      }

      sum += countInRange((BSTNode<Integer>) node.getRight(), from, to);
    }
    return sum;
  }

  @Override
  public List<Integer> listInRange(Integer[] array, Integer from, Integer to) {
    List<Integer> list = new LinkedList<>();
    if (array != null && array.length > 0 && from != null && to != null) {
      for (Integer elem : array) {
        this.insert(elem);
      }
      list = listInRange(this.root, list, from, to);
    }
    return list;
  }

  private List<Integer> listInRange(BSTNode<Integer> node, List<Integer> list, Integer from, Integer to) {
    if (!node.isEmpty()) {
      listInRange((BSTNode<Integer>) node.getRight(), list, from, to);

      if (node.getData() >= from && node.getData() <= to) {
        list.add(node.getData());
      }

      listInRange((BSTNode<Integer>) node.getLeft(), list, from, to);
    }
    return list;
  }

  // MÉTODOS EXISTENTES. NÃO MODIFIQUE ESTES MÉTODOS.
  // ELES SERVIRÃO PARA VOCÊ TESTAR SUA IMPLEMENTAÇÃO
  public void insert(Integer elem) {
    this.insert(elem, root);
  }

  private void insert(Integer elem, BSTNode<Integer> node) {
    if (node.isEmpty()) {
      node.setData(elem);
      node.setLeft(new BSTNode<>());
      node.getLeft().setParent(node);
      node.setRight(new BSTNode<>());
      node.getRight().setParent(node);
    } else if (elem.compareTo(node.getData()) < 0) {
      insert(elem, (BSTNode<Integer>) node.getLeft());
    } else {
      insert(elem, (BSTNode<Integer>) node.getRight());
    }
  }

}