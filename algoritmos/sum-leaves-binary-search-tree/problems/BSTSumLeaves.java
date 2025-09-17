package problems;

import adt.bst.BST;

public interface BSTSumLeaves {

    /**
     * Soma todos os valores armazenados nas folhas de uma BST.
     * 
     * @param bst a árvore binária de busca cujas folhas serão somadas
     * @return a soma dos valores das folhas
     */
    int sumLeaves(BST<Integer> bst);
}