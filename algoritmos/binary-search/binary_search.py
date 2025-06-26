def binary_search_iterative(arr, value):
    left = 0
    right = len(arr) - 1
    result = None
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == value:
            result = mid
            break
        elif arr[mid] < value:
            left = mid + 1
        else:
            right = mid - 1
    return result

def binary_search_recursive(arr, value, left = 0, right=None):
    result = None
    if right is None: right = len(arr) - 1

    if left <= right:
        mid = left + (right - left) // 2
        if arr[mid] == value:
            result = mid
        elif arr[mid] > value:
            result = binary_search_recursive(arr, value, left, mid - 1)
        else:
            result = binary_search_recursive(arr, value, mid + 1, right)
    return result
    
"""
Análise assintótica:
- Ambos os algoritmos (iterativo e recursivo) executam uma busca binária sobre um array ordenado.
- A cada passo, o tamanho do intervalo de busca é reduzido pela metade.
- Isso implica um número de passos proporcional ao logaritmo da quantidade de elementos.
- Portanto, a complexidade no pior caso é O(log n), onde n é o tamanho do array.
"""