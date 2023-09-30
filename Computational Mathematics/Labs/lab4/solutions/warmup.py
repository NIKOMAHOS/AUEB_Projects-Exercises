import numpy as np

n = 200000

def random():
    return np.random.random()

#create n random points
p = [ ( random(), random() ) for i in range(n) ]

#count points that are withing the unit circle
s = sum( [ 1 for i in p if i[0]*i[0] + i[1]*i[1] <= 1 ] )

#answer is 4 * points_in_circle /total_circles
print( 4 * s / n )