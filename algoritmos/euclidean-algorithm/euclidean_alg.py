def mdc_recursive(a, b):
    if b == 0: return a
    return mdc_recursive(b, a%b)

def mdc_iterative(a, b):
    while b != 0:
        a, b = b, a % b
    return a

"""
Análise assintótica:
- O tempo de execução depende do número de passos até que o resto a % b seja zero.
- O pior caso ocorre quando os restos são os menores possíveis a cada passo,
  ou seja, quando os valores de a e b seguem a sequência de Fibonacci.
- A complexidade é O(log min(a,b)).
"""