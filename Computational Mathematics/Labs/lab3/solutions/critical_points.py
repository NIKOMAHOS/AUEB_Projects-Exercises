import numpy as np
import matplotlib.pyplot as plt

tol = 1e-6

#declare f
def f(x):
    return x**5 + 7*x**4 - 5*x**3 - 75*x**2 - 36*x + 108

#declare df
def df(x):
    return 5*x**4 + 28*x**3 - 15*x**2 - 150*x - 36

#declare ddf
def ddf(x):
    return 20 * x**3 + 84 * x**2 - 30*x - 150

x = np.linspace( -6, 3, 30 )

#Plot f and df
plt.plot( x, f(x), label='f' )
plt.plot( x, df(x), label='df' )
plt.legend()
plt.grid()

#choose method to approximate solution
def Newton( x0, f, df ):
    #Execute newton algorithm
    errors = []
    for i in range( 100 ):
        x_next = x0 - f(x0) / df(x0)
        errors.append( x_next - x0 )
        if np.abs(x_next - x0) < tol:
            break
        x0 = x_next
    print( errors[-1])
    return x_next, errors

#collect possible solutions
p = [ -5, -3, -1, 1.2, 2 ]

val = []
for i in p:
    val.append( Newton( i, df, ddf )[0] )

val = np.array(val)

plt.plot( val, f(val), '*' )

#print each solution
print( 'points found at' )
print( val )

plt.show()