import numpy as np
import matplotlib.pyplot as plt
from math import log2, ceil
tol = 1e-6
np.set_precision(4)
#declare f
def f(x):
    return np.exp(x) - 2

#declare df
def df(x):
    return np.exp(x)

#Plot f

#declare Newton
def Newton( x0, nmax = 100):
    #Execute newton algorithm
    #return x_n, list_of_errors
    errors = []
    for i in range(nmax):
        x_next = x0 - f(x0)/df(x0)
        err = x_next-x0 #error
        errors.append(err)
        if np.abs(err) < tol:
            break
        x0 = x_next
    
    return x_next, errors

#declare bisection
def bisection( lo, hi ):

    errors = [ hi-lo ]

    while hi-lo > tol:
        mid = lo + ( hi-lo ) / 2
        f_mid = f(mid)
        f_hi = f(hi)

        if f_mid * f_hi < 0:
            lo = mid
        else:
            hi = mid

        errors.append( hi-lo )

    return lo + ( hi-lo ) / 2, errors

n_sol, n_errors = Newton(20)
b_sol, b_errors = bisection(-10, 10)

print(b_sol)
#plot bisections errors
plt.plot(b_errors, label = "bisection")
#plot newtown errors
plt.plot(n_errors, label= "Newton")
#print bisection and newton solutions
plt.legend()
plt.show()