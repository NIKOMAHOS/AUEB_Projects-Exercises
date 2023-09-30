import numpy as np

n = 20000

def random():
    return np.random.random()

#create n random points
p = [ ]

#count points that are withing the unit circle
s = sum( [] )

#answer is 4 * points_in_circle /total_circles
print( 4 * s / n )