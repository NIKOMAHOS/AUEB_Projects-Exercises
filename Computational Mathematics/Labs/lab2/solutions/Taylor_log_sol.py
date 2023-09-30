import numpy as np
import matplotlib.pyplot as plt

x = [ -5.5, -10, -5, -1, 0, 1, 5, 10 ]

m = {}
def fact( n ):
    if n <= 1:
        return 1
    if n in m:
        return m[n]
    m[n] = n * fact(n-1)
    return m[n]

def Taylor( x0, order=50 ):
    
    ans = 1
    num = 1
    
    errors = np.zeros( (order+1,1) )
    expected = np.full( (order+1,1), np.exp(x0) )

    term = 1
    errors[0] = np.abs( 1 - expected[0] )

    series_sum = 1
    pow_x = 1
    sign = 1
    for i in range(1,order+1):
        #pow_x *= x0
        #term = pow_x / fact(i)
        term = ( term ) * x0
        series_sum = series_sum + (sign) * term/i
        errors[i] = np.abs( series_sum - expected[i] )
        sign = -sign
    return series_sum, (errors,expected)

L = np.linspace( -1, 10, 40 )

print(L)
plt.plot( L, np.log(L+1), label='log(x+1)' )

plt.ylim( [ -200, 200 ] )

for myorder in range( 10 ):
    val = []
    for i in L:
        val.append( Taylor(i,myorder)[0] )
    plt.plot( L, val, label='order='+str(myorder) )
plt.grid(True)
plt.legend()
plt.show()