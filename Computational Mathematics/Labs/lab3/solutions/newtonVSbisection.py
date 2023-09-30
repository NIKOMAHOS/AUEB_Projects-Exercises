import numpy as np
import matplotlib.pyplot as plt

tol = 1e-6

#declare f
def f(x):
    return np.exp(x) - 2

#declare df
def df(x):
    return np.exp(x)

#Plot f

#declare Newton
def Newton( x0 ):
    #Execute newton algorithm

    errors = []
    for i in range( 50 ):
        x_next = x0 - f(x0) / df(x0)
        errors.append( x_next - x0 )
        if np.abs(x_next - x0) < tol:
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

b_sol, b_errors = bisection( -10,10 )
n_sol, n_errors = Newton( 20 )
print( n_sol )
plt.plot( b_errors, label='bisection' )
plt.plot( n_errors, label='newton' )
plt.grid()
plt.legend()
plt.show()