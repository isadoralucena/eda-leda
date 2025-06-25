def trial_division_iterative(number):
    result = True
    if number < 2:
        result = False
    else:
        for i in range(2, int(number**0.5) + 1):
            if number % i == 0:
                result = False
                break
    return result

def trial_division_recursive(number, divider = 2):
    result = True
    if number < 2:
        result = False
    elif divider > int(number**0.5):
        result = True
    elif number % divider == 0:
        result = False
    else:
        result = trial_division_recursive(number, divider + 1)
    return result

"""
Análise assintótica:
- No pior caso (quando n é primo ou tem divisores próximos a √n), 
  o algoritmo precisa verificar todos os números até n.
- A complexidade é O(√n).
"""