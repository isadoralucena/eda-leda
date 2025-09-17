package problems;

import java.util.List;

/**
 * Dado um array de números inteiros possivelmente não ordenado, usar esta BST para encontrar os elementos
 * cujos valores estão compreendidos entre dois valores "from" e "to" (inclusive) e retorná-los em 
 * uma lista em ordem decrescente, e também para contar a quantidade destes valores (inclusive).
 *
 * Exemplo:
 *   A = [5, 7, 7, 8, 8, 10]
 *   from = 6, to = 8
 *   Saídas: 
 *     lista de valores: [8, 7]
 *     quantidade: 2
 *
 * Restrições: 
 * - Note que os métodos a serem implementados têm relação entre si, pois se você encontra
 *   a lista dos elementos compreendidos entre from e to, pode calcular o tamanho dela. Entretanto,
 *   você NÃO pode fazer isso, pois o design de percurso para contar a quantidade é DIFERENTE 
 *   do design de percurso para construir a lista. 
 * - Observe as restrições de cada método.
 */
public interface CountInRange {

  /**
   * Restrições:
   * - Seu algoritmo DEVE usar memória extra da ordem de O(1).
   * - Você DEVE usar recursão.
   * - Você só pode implementar no máximo 1 método auxiliar com até 4 parâmetros.
   * - Você NÃO pode criar atributos novos na classe.
   * - Você NÃO pode usar comando return em método void. 
   * - Você NÃO pode usar mais de um return em métodos que retornam algum valor.
   * - Você pode assumir que a BST sempre estará vazia quando for executar seu método.
   * - Considere que o array NÃO tem elementos repetidos e possivelmente NÃO ordenado.
   * - Você precisará inserir todos os elementos do array na BST para depois implementar sua lógica. 
   * - O percurso pela BST deverá ser do menor elemento para o maior elemento.
   * - A lógica de percurso para resolver o problema DEVE ser O(n).
   * 
   * @param array o array contendo os valores onde procurar 
   * @param from o valor mínimo (inclusive)
   * @param to o valor máximo (inclusive)
   * @return a quantidade de elementos compreendidos entre from (inclusive) e to (inclusive)
   */
  public int countInRange(Integer[] array, Integer from, Integer to);

  /**
   * Restrições:
   * - A lista a ser retornada tem tamanho O(n). Entretanto, para resolver
   *   o problema em si, seu algoritmo DEVE usar memória O(1). Ele deve apenas
   *   adicionar os elementos na lista a ser retornada.
   * - Você DEVE usar recursão.
   * - Você só pode implementar no máximo 1 método auxiliar com até 5 parâmetros.
   * - Você NÃO pode criar atributos novos na classe.
   * - Você pode assumir que a BST sempre estará vazia quando for executar seu método.
   * - Considere que o array NÃO tem elementos repetidos e possivelmente NÃO ordenado.
   * - Você precisará inserir todos os elementos do array na BST para depois implementar sua lógica. 
   * - Você DEVE usar a classe java.util.LinkedList como implementação da lista 
   *   a ser retornada.
   * - A lista a ser retornada DEVE estar em ordem DECRESCENTE.
   * - A lógica de percurso para resolver o problema DEVE ser O(n).
   * 
   * @param array o array contendo os valores onde procurar 
   * @param from o valor mínimo (inclusive)
   * @param to o valor máximo (inclusive)
   * @return a lista, em ordem decrescente, de elementos compreendidos entre from (inclusive) e to (inclusive)
   */
  public List<Integer> listInRange(Integer[] array, Integer from, Integer to);
}