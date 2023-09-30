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

def Taylor( x0 ):
    
    # ans = 1

    order = 50
    # num = 1
    
    errors = np.zeros( (order+1,1) )
    expected = np.full( (order+1,1), np.exp(x0) )
    
    term = 1
    errors[0] = np.abs( 1 - expected[0] )
    
    for i in range(1, order+1):
        term += x0**i / fact(i)
        errors[i] = np.abs( term - expected[i] )
        
    return term, (errors,expected)

L = np.linspace( -2.5, 2.5, 20 )
print(L)

#plot np.exp(x)
plt.plot(L, np.exp(L), label = 'exp')
#for each order i=0... calculate Taylor per point L and plot values
for myorder in range(5):
    val = []
    for i in L:
        val.append( Taylor(i, myorder)[0] )
    #plt.show()
    plt.plot(L, val, label = 'order = ' + str(myorder+1))
    plt.show()
    
for i in x:
    ans, errors = Taylor( i )
'''
cnt = 0
plt.figure( )
for i in x:
    cnt += 1
    #plt.subplot()
    #plt.plot( )#'absolute relative error' )
    #plt.xlabel( 'order' )
    #if False:
    #    plt.ylabel( 'absolute relative error' )
    #plt.title( 'x = ' + str(i) )
plt.show();
'''