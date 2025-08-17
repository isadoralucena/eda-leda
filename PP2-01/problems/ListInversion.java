package problems;

import adt.linkedList.LinkedList;

/**
 * Interface especificando uma lista que pode ser invertida usando-se um algoritmo
 * especifico mostrado na figura/animacao (a ser aberta no browser o arquivo gif ou o html)
 * Abra o arquivo algoritmo.html em seu browser e observe a animação mostrando exatamente
 * como a inversão funciona. Voce DEVE implementar essa lógica!
 * 
 * VOCE NÃO PRECISA MEXER EM ABSOLUTAMENTE NADA NA CLASSE SingleLinkedListImpl.java
 * 
 * OBSERVE OS COMENTARIOS E RESTRICOES ESPECIFICAS DE CADA METODO
 */
public interface ListInversion<T extends Comparable<T>> extends LinkedList<T>{
	
	/**
	 * Metodo que inverte a lista, implementado de forma iterativa.
	 * 
	 * Restricoes:
	 * - Voce NAO pode usar estrutura auxiliar. Seu uso de memória deve ser O(1)
	 * - Voce DEVE seguir o algoritmo mostrado pela animação, com a diferença de que no lugar
	 *   de NULL entenda-se NIL
	 * - Voce NAO pode criar uma lista nova
	 * - Voce NÃO pode criar um metodo auxiliar
	 * - Você não pode usar break para quebrar laços
	 * - Você NÃO pode usar return em método void
	 * - Você NÃO pode usar mais de um return em método que retorna valores
	 * - Seu algoritmo DEVE ser O(k.n) onde k = 1
	 */
	public void reverseIterative();
	
	/**
	 * Metodo que inverte a lista, implementado de forma RECURSIVA.
	 * 
	 * Restricoes:
	 * - Voce NAO pode usar estrutura auxiliar. Seu uso de memória deve ser O(1)
	 * - Voce DEVE seguir o algoritmo mostrado pela figura, com a diferença de que no lugar
	 *   de NULL entenda-se NIL
	 * - Voce NAO pode criar uma lista nova
	 * - Voce pode criar apenas um metodo auxiliar 
	 * - O método auxiliar pode ter no máximo 2 parâmetros
	 * - Você NÃO pode declarar um novo atributo na classe
	 * - Você NÃO pode usar return em método void
	 * - Você NÃO pode usar mais de um return em método que retorna valores
	 * - Seu algoritmo DEVE ser O(k.n) onde k = 1
	 */
	public void reverseRecursive();
}