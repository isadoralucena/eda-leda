package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class FamilyBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements FamilyBST<T> {

  @Override
  public boolean primosPrimeiroGrau(T elem1, T elem2) {
    boolean answer = false;
    if (!this.isEmpty() && elem1 != null && elem2 != null) {
      BSTNode<T> node1 = this.search(elem1);
      BSTNode<T> node2 = this.search(elem2);
      if (node1 != null && node2 != null && !node1.isEmpty() && !node2.isEmpty()) {
        answer = primosPrimeiroGrau(node1, node2);
      }
    }
    return answer;
  }

  private boolean primosPrimeiroGrau(BSTNode<T> node1, BSTNode<T> node2) {
    boolean answer = false;

    BSTNode<T> father1 = (BSTNode<T>) node1.getParent();
    BSTNode<T> father2 = (BSTNode<T>) node2.getParent();

    if (father1 != null && father2 != null && !father1.isEmpty() && !father2.isEmpty()) {
      BSTNode<T> grandfather1 = (BSTNode<T>) father1.getParent();
      BSTNode<T> grandfather2 = (BSTNode<T>) father2.getParent();

      if (grandfather1 != null && grandfather2 != null && !grandfather1.isEmpty() && !grandfather2.isEmpty()) {
        answer = grandfather1.getData().compareTo(grandfather2.getData()) == 0
                  && father1 != father2;
      }
    }
    return answer;
  }

  @Override
  public boolean primosSegundoGrau(T elem1, T elem2) {
    boolean answer = false;
    if (!this.isEmpty() && elem1 != null && elem2 != null) {
      BSTNode<T> node1 = this.search(elem1);
      BSTNode<T> node2 = this.search(elem2);
      if (node1 != null && node2 != null && !node1.isEmpty() && !node2.isEmpty()) {
        BSTNode<T> parent1 = (BSTNode<T>) node1.getParent();
        BSTNode<T> parent2 = (BSTNode<T>) node2.getParent();
        if (parent1 != null && parent2 != null) {
          answer = primosPrimeiroGrau(parent1, parent2);
        }

      }
    }
    return answer;
  }

  /**
   * NAO ALTERAR OS METODOS ABAIXO PORQUE SERÃO UTILIZADOS PELOS TESTES
   */
  @Override
  public void insert(T element) {
    insert(root, element);
  }

  protected void insert(BSTNode<T> node, T element) {
    if (node.isEmpty()) {
      node.setData(element);
      node.setLeft(new BSTNode<T>());
      node.getLeft().setParent(node);
      node.setRight(new BSTNode<T>());
      node.getRight().setParent(node);
    } else {
      if (element.compareTo(node.getData()) < 0) {
        insert((BSTNode<T>) node.getLeft(), element);
      } else if (element.compareTo(node.getData()) > 0) {
        insert((BSTNode<T>) node.getRight(), element);
      }
    }
  }

  @Override
  public BSTNode<T> search(T element) {
    return search(root, element);
  }

  protected BSTNode<T> search(BSTNode<T> node, T element) {
    BSTNode<T> result = node;
    if (element != null) {
      if (!node.isEmpty()) {
        if (element.compareTo(node.getData()) == 0) {
          result = node;
        } else if (element.compareTo(node.getData()) < 0) {
          result = search((BSTNode<T>) node.getLeft(), element);
        } else {
          result = search((BSTNode<T>) node.getRight(), element);
        }
      }
    }

    return result;
  }
}