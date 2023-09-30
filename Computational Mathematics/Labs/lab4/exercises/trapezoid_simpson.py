import numpy as np
import matplotlib.pyplot as plt

#real integral/area value
real_value = 0.74682413279

#our function f
def f( x ):
    return np.exp( -np.square(x) )

#number of intervals, e.x. 10 means 10 subintervals and 11 equally distanced points
trapezoid_subs = [10,20,40,80,160]
simpson_subs = [2,4,8,16,32]

#store the absolute value of error that you will calculate in trapezoid and simpson functions
trapezoid_error = []
simpson_error = []

def trapezoid( h, y ):

    n = len(y)
    
    #add first term
    s = 0

    #add intemediate terms
    for i in range(1,n-1):
        s += ...

    #add last term
    s += ...

    return ...

def simpson( h, y ):

    n = len(y)
     
    #add first term
    s = ...

    #add intemediate terms
    for i in range(1,n-1):
        val = ...
        if i % 2 == 1:
            val = ...
        s += ...

    #add last term
    s += ...

    return ...

#x-axis is between [0,1]
lo = 0
hi = 1

#Execute trapezoid
for num in trapezoid_subs:
    #Calculate h
    h = 0
    #Store num+1 points into x
    #Use np.arange( start=..., stop=..., step=... )
    x = []
    y = f(x)
    estimated = trapezoid( h, y )
    trapezoid_error.append( np.abs(real_value - estimated) )

for num in simpson_subs:
    #Calculate h
    h = 0
    #Store num+1 points into x
    #Use np.arange( start=..., stop=..., step=... )
    x = []
    y = f(x)
    estimated = simpson( h, y )
    simpson_error.append( np.abs(real_value - estimated) )

print( 'Trapezoid error')
print( trapezoid_error )

#check that it's 4
for i in range( 1, len(trapezoid_error) ):
    print( trapezoid_error[i-1]/trapezoid_error[i] )

print( 'Simpson error' )
print( simpson_error )

#check that it's 16
for i in range( 1, len(simpson_error) ):
    print( simpson_error[i-1]/simpson_error[i] )

plt.subplot( 2, 1, 1 )
plt.plot( trapezoid_subs, trapezoid_error, '*' )
plt.plot( trapezoid_subs, trapezoid_error )
plt.title( 'Trapezoid')
plt.ylabel( 'Error' )
plt.grid()

plt.subplot( 2, 1, 2 )
plt.plot( simpson_subs, simpson_error )
plt.title( 'Simpson 1/3')
plt.ylabel( 'Error' )
plt.xlabel( 'Num of intervals' )
plt.grid()

plt.show()
