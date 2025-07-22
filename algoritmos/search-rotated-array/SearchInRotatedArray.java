public class SearchInRotatedArray{
    public int search(int[] nums, int target) {
        int answer = bs(nums, target, 0, nums.length - 1);
        return answer;
    }

    public int bs(int[] nums, int target, int left, int right){
        int mid = (left + right)/2;
        int answer = -1;
        if (left <= right){
            if(nums[mid] == target){
                answer = mid;
            } else if (nums[mid] >= nums[left]){
                if(target >= nums[left] && target <= nums[mid]){
                    answer = bs(nums, target, left, mid - 1);
                }else{
                    answer = bs(nums, target, mid + 1, right);
                }
            } else{
                if (target >= nums[mid] && target <= nums[right]){
                    answer = bs(nums, target, mid + 1, right);
                }else{
                    answer = bs(nums, target, left, mid - 1);
                }
            }
        }
        return answer;
    }

}

/**
 * Implementação de Busca Rotacionada (Search in Rotated Sorted Array)
 *
 * A classe implementa uma versão modificada da busca binária para localizar
 * um elemento em um array que foi previamente ordenado em ordem crescente
 * e rotacionado em algum ponto desconhecido
 *
 * Exemplo de array rotacionado: [4,5,6,7,0,1,2]
 *
 * A busca é feita determinando, em cada etapa, qual lado do array está
 * ordenado e decidindo em qual dos lados o alvo pode estar presente.
 *
 * Complexidade Assintótica:
 * - Tempo: O(log n), pois a busca é feita em metade do array a cada chamada
 * - Espaço: O(log n) devido à recursão (pode ser reduzido para O(1) com abordagem iterativa)
 */