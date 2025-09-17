package adt.bst.extended;

import adt.bst.BST;

/**
 * BST que verifica propriedades de nós: se são primos em primeiro grau 
 * ou se são primos em segundo grau. 
 * 
 * Restricoes:
 *  - Na implementacao dos metodos voce só pode utilizar o metodo search (já implementado)
 *    e os metodos da classe BSTNode.
 *  - Voce pode implementar metodos auxiliares mas que não facam nada de algum metodo existente
 *    da BST. Por exemplo, suponha que voce queira fazer um caminhamento em pré-ordem em sua 
 *    solucao e decide implementa-lo. Essa solucao não sera valida porque caminhamento em 
 *    pré-ordem é uma funcionalidade de BST não permitida de ser utilizada.
 *  
 * @author Adalberto
 *
 * @param <T>
 */
public interface FamilyBST<T extends Comparable<T>> extends BST<T> {

	/**
	 * Diz se dois elementos de uma BST são primos em primeiro grau.
	 * Pense nas condicoes para estabelecer essa restricao e implemente.
	 * 
	 * @param elem1
	 * @param elem2
	 * @return
	 */
	public boolean primosPrimeiroGrau(T elem1, T elem2);
	
	/**
	 * Diz se dois elementos de uma BST são primos em segundo grau.
	 * Pense nas condicoes para estabelecer essa restricao e implemente.
	 * Dica: primos em primeiro grau de nossos pais são nossos primos 
	 * em segundo grau.
	 * 
	 * @param elem1
	 * @param elem2
	 * @return
	 */
	public boolean primosSegundoGrau(T elem1, T elem2);
	
}