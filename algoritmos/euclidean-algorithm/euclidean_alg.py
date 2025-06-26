def mdc_recursive(a, b):
    if b == 0: return a
    return mdc_recursive(b, a%b)

def mdc_iterative(a, b):
    while b != 0:
        a, b = b, a % b
    return a

"""
Análise assintótica:

- O tempo de execução do Algoritmo de Euclides depende do número de passos até que o valor de b se torne zero.
- Esse número de passos é limitado pelo menor valor entre a e b, independentemente de qual seja o maior.
- O pior caso ocorre quando os restos sucessivos são os menores possíveis — isso aumenta o número de 
  cálculos e ocorre quando a e b pertencem à sequência de Fibonacci.
- Como essa sequência cresce exponencialmente, o número de passos, nesse caso, é proporcional ao logaritmo 
  do menor número na base φ (phi), a razão áurea. Ou seja: número de passos ≈ logφ(min(a, b)).
- Portanto, a complexidade Big O do algoritmo é O(log(min(a, b))).
"""