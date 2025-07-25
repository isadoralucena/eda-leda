/**
* Dado um array A ordenado de inteiros de tamanho N e um valor x, que pode pertencer 
* ou não ao array, usar busca binaria para encontrar o numero de elementos do array 
* que sao maiores ou iguais a x.
*
* Exemplo:
*   A = [5, 7, 7, 8, 8, 10]
*   x = 6
*   Saida: 5 (todas os números a partir do primeiro 7 são maiores que 6)
*
*   A = [5, 17, 100, 111]
*   x = 200
*   Saida: 0 (nao tem nenhum elemento do array maior ou igual a 200)
*
* Restricoes: 
* - Seu algoritmo NÃO pode usar memória extra (qualquer coleção) 
* - O tempo de seu algoritmo deve ser O(k.log n), onde k = 1
* - Voce DEVE usar a estratégia de busca binária
* - Voce DEVE usar recursão
* - Você NÃO pode declarar atributos novos na classe
* - Você PODE modificar apenas a classe que implementa esta interface
* - Você PODE implementar no máximo 1 método auxiliar
* - O método auxiliar NÃO pode ter mais que 4 parâmetros
*
*/
package problems;

public interface CountGreaterThanEqual {
    public int countGreaterThanEqual(Integer[] array, Integer x);
}