import math

def fibonacci_recursive(num):
    result = 0
    if num == 1:
        result = 1
    elif num == 0:
        result = 0
    else:
        result = num(num - 1) + num(num - 2)
    return result

def fibonacci_iterative(num):
    current = 0
    next_value = 1

    for _ in range(num):
        current, next_value = next_value, current + next_value

    return current

def fibonacci_backtracking(num, visited={}):
    if num in visited:
        return visited[num]
    if num <= 1:
        visited[num] = num
    else:
        visited[num] = fibonacci_backtracking(num-1, visited) + fibonacci_backtracking(num-2, visited)
    return visited[num]

def fibonacci_matrix(n):
    def multiply(a, b):
        return [
            [a[0][0]*b[0][0] + a[0][1]*b[1][0],
             a[0][0]*b[0][1] + a[0][1]*b[1][1]],
            [a[1][0]*b[0][0] + a[1][1]*b[1][0],
             a[1][0]*b[0][1] + a[1][1]*b[1][1]]
        ]

    def power(matrix, exponent):
        result = [[1, 0], [0, 1]] 
        while exponent > 0:
            if exponent % 2 == 1:
                result = multiply(result, matrix)
            matrix = multiply(matrix, matrix)
            exponent //= 2
        return result

    if n == 0:
        return 0
    base = [[1, 1], [1, 0]]
    result = power(base, n - 1)
    return result[0][0]

def fibonacci_binet(n): # Não é precisa quando n é muito grande, n > 71 começar a falhar
    sqrt5 = math.sqrt(5)
    phi = (1 + sqrt5) / 2
    psi = (1 - sqrt5) / 2
    return round((phi**n - psi**n) / sqrt5)