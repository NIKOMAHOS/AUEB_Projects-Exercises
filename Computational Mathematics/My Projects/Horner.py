import numpy as np
import math

# Classic Horner Algorithm for evaluating a polynomial at a point x
def Horner(polyCoefs, x_0):
# polyCoefs is a numpy array of polynomial coefficients in descending order
    result = np.zeros_like(polyCoefs)
    result[0] = polyCoefs[0]
    
    for i in range(1, polyCoefs.shape[0]):
        result[i] = x_0 * result[i-1] + polyCoefs[i]
        
    return result, result[-1]
    # return the "new" polynomial coefficients and the value of the polynomial at x_0
    
# Derivative of a polynomial using Horner Algorithm
x0 = -2
a = np.array([ 3, 0, -2, 0, 1, 0 ])
print(Horner(a, x0))
aa = np.array([[ 3, 0, -2, 0, 1, 0 ]]).T
print(Horner(aa, x0)[0], Horner(aa, x0)[1].item())  
aaa = a.reshape(-1,1)
print(Horner(aaa, x0)[0], Horner(aaa, x0)[1].item())

# VASSALOS - KEF 1. SLIDE 16/18 (AND !!!) 17/18
def DerivativeHorner(polynomialCoefs, evaluationPoint, derivativeOrder = 0):
    order = len(polynomialCoefs)
    # Polynomial Coefficients are in descending order ( !!! )
# SHOULD WE NOT CHECK FOR ORDER == 1 AS WELL?
    if order == 0: 
        print("Empty polynomial coefficients")
        return
    
    if (derivativeOrder < 0):
        print("Invalid derivative order")
        return

    if (derivativeOrder >= order): return 0

    derivativeCoefs = np.zeros(order)
    derivativeCoefs[0] = polynomialCoefs[0]

    for j in range(-1, derivativeOrder): # for each derivative
    # Start from j (dummy _ ) = -1, because the first derivative is the polynomial itself
    # So we still need the loop to run once, even if we don't want to calculate any derivatives (When derivativeOrder = 0)
        for i in range(1, order): # Classic Horner Algorithm
            derivativeCoefs[i] = derivativeCoefs[i - 1] * evaluationPoint + polynomialCoefs[i]

        polynomialCoefs = derivativeCoefs
        # the new polynomial coefficients are the previous derivative coefficients
        order -= 1 # decrease the order of the polynomial each time
    # We found the derivative coefficients but we need to multiply them by the factorial
    # of the derivative order to get the value of the derivative at the evaluation point
    return math.factorial(derivativeOrder) * derivativeCoefs[order]

a = [6, -53, 184, -295, 186]
x0 = 2
order = 2

print(DerivativeHorner(a, x0, order))

b = [1]
x_1 = 2
order = 2

print(DerivativeHorner(b, x_1, order))
