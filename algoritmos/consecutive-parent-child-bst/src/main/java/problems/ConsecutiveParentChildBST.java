package problems;

import java.util.Set;

import adt.bst.BST;

/**
 * BST que permite encontrar os pares (Pai,Filho) que contem numeros consecutivos
 * (sem importar a ordem deles no par). 
 */
public interface ConsecutiveParentChildBST extends BST<Integer>{
    /**
     * Metodo para retornar um conjunto (Set) de objetos do tipo Pair contendo 
     * os pares (Pai,Filho) de numeros consecutivos sem importar a ordem 
     * deles dentro do par.
     * 
     * Restrições:
     * - Voce NÃO pode usar memória extra (outra estrutura) para resolver o problema
     * - Voce NÃO pode converter a BST em um array e ir comparando os elementos dois a dois
     * - Voce DEVE usar recursão para resolver o problema
     * - Sua solução DEVE ser em O(N)
     * - Qualquer outro método que voce necessite deve ser implementado na classe ConsecutiveParentChildBSTFullImpl
     * - Utilize a classe TreeSet como implementação da interface Set.
     * 
     * @return um conjunto contendo os pares (Pai, Filho) de números consecutivos
     */
    public Set<Pair> findConsecutives();
}
