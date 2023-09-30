import numpy as np
import matplotlib.pyplot as plt

np.random.seed(0)

def random( a, b ):
    return a + (b-a)*np.random.random()

#F and G values
y = [ 1.000000, 0.731689, 0.070737, -0.628174, -0.989992, -0.820559, -0.210796, 0.512085, 0.960170 ]
z = [ 0.00000, 0.68164, 0.99749, 0.77807, 0.14112, -0.57156, -0.97753, -0.85893, -0.27942  ]

len_y = len(y)

#Make the vector with the points at x axis "starting from 0, ending to 2, equal number to the y vector"
xEnd = []

#store minimum value of y and z
minFG = min( ... )
#store minimum value of y and y
maxFG = max( ... )

#number of points to be generated
n = 2000

#define linear spline
def S( x, x_1, y_1, x_2, y_2 ):
    #x: x-value that we want to estimate its y-value
    #x_1, y_1 corresponds to x_{j}, f( x_{j} )
    #x_2, y_2 corresponds to x_{j+1}, f( x_{j+1} )
    pass

tot = cntIn = 0

for p_iter in range( 2000 ):

    #create a random point x and y in the desired bounds as explained in the statement
    p_x, p_y = random( ..., ... ), random( ..., ... )

    #find interval
    idx = len_y-2#if nothing holds set that the point belongs to the last interval
    for j in range( 1, len_y-1 ):
        if xEnd[j-1] < p_x < xEnd[j]:
            idx = j-1#p_x is withing an interval break and set idx to the leftmost index
            break
    
    #Calculate the Y-values for spline
    spline_y = S( ..., ...., ...., ..., ... )
    spline_z = S( ..., ...., ..., ..., ... )

    tot += 1

    if ....:
        #if p_y is within the splines values plot red +
        cntIn += 1
    else:
        #else plot green +
        pass

#Area_of_rectangle * ratio, where ratio = total_points_in / total points
integral = ...

print( 'Integral := ', integral )

plt.title( 'Integral %s' % (integral) )
plt.show()
    


