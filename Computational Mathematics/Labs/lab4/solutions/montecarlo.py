import numpy as np
import matplotlib.pyplot as plt

np.random.seed(0)

def random( a, b ):
    return a + (b-a)*np.random.random()

y = [ 1.000000, 0.731689, 0.070737, -0.628174, -0.989992, -0.820559, -0.210796, 0.512085, 0.960170 ]
z = [ 0.00000, 0.68164, 0.99749, 0.77807, 0.14112, -0.57156, -0.97753, -0.85893, -0.27942  ]

len_y = len(y)
xEnd = np.linspace( 0, 2, len_y )

minFG = min( min(y), min(z) )
maxFG = max( max(y), max(z) )

n = 2000

def S( x, x_1, y_1, x_2, y_2 ):
    return y_1 + ( (y_2-y_1) / ( x_2 - x_1 ) ) * ( x-x_1 )

tot = cntIn = 0

for p_iter in range( 2000 ):

    p_x, p_y = random(0,2), random( minFG, maxFG )

    #find interval
    idx = len_y-2
    for j in range( 1, len_y-1 ):
        if xEnd[j-1] < p_x < xEnd[j]:
            idx = j-1
            break
    
    spline_y = S( p_x, xEnd[idx], y[idx], xEnd[idx+1], y[idx+1] )
    spline_z = S( p_x, xEnd[idx], z[idx], xEnd[idx+1], z[idx+1] )

    tot += 1

    if min( spline_y, spline_z ) < p_y < max( spline_y, spline_z ):
        plt.plot( p_x, p_y, 'r+' )
        cntIn += 1
    else:
        plt.plot( p_x, p_y, 'g+' )

integral = ( ( xEnd[len_y-1] - xEnd[0]  ) * ( maxFG - minFG ) )  * (cntIn/tot)

print( 'Integral := ', integral )

plt.title( 'Integral %s' % (integral) )
plt.show()
    


