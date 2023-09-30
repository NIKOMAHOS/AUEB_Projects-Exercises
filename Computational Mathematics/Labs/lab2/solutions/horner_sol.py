import numpy as np

a = np.array( [ [ 3, 0, -2, 0, 1, 0 ] ] ).T
x0 = -2

def horner( a, x0 ):
    b = np.zeros_like( a )
    b[0] = a[0]
    #print( b.shape )
    for i in range( 1, b.shape[0] ):
        b[i] = x0 * b[i-1] + a[i]
    #print( b )
    return ( b[-1].item() )

print( horner( a, x0 ) )