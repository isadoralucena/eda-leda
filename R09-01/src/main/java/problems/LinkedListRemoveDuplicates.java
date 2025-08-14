package problems;

import adt.linkedList.SingleLinkedListNode;

/**
 * Interface definindo um metodo para remover as duplicadas de uma lista
 * possivelmente não ordenada, a partir de um determinado nó.
 */
public interface LinkedListRemoveDuplicates<T> {
    /**
     * Remove as duplicadas de uma lista a partir de determinado nó, passado como parâmetro
     * deixando apenas uma ocorrência de cada elemento na lista. 
     * Restrições:
     * - OBSERVE AS RESTRICOES EXTRAS NA CLASSE QUE IMPLEMENTA ESSA INTERFACE!
     * - Você NÃO pode usar nenhuma estrutura auxiliar como memória extra. Ou seja,
     *   sua solução deve usar espaco extra O(1)
     * - Você NÃO pode criar nenhuma outra classe auxiliar
     * - Você NÃO pode criar atributos de classe
     * - Voce NÃO pode alterar nada na classe SingleLinkedListNode
     * - Você NÃO pode usar mais de um return nos métodos não void
     * - Voce NÃO pode usar return em métodos void
     * - Você NÃO pode ordenar a lista para depois remover as duplicatas
     * - Você NÃO pode criar uma lista nova e retornar
     * - Sua solução DEVE ir varrendo a lista da esquerda pra direita e ir resolvendo
     *   o problema durante essa varredura.
     * - Seu algoritmo deve ser da ordem exata (Theta) de n^2.
     */
    public void removeDuplicates(SingleLinkedListNode<T> node);
}

