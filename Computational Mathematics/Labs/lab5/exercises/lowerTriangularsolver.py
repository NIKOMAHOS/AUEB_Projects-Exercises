import numpy as np

A = np.array( [ [1,0,0,0,0], [1,2,0,0,0], [1,2,3,0,0], [1,2,3,4,0], [1,2,3,4,5] ], dtype='float' )

b = np.array( [1,2,3,4,5], dtype='float' ).reshape( (-1,1) )
print( 'A' )
print( A )
print( 'b' )
print( b )

def lowerTriangularsolver( A, b ):
    #Initialiaze the x vector
    """
    YOUR CODE HERE
    """
    # Calculate x[i] from the given formula
    """
    YOUR CODE HERE
    """

    return x

def upperTriangularsolver( A, b ):
    # Same for upper triangular matrix
    # there is a trick think about it
    """
    YOUR CODE HERE
    """
    return x

print( 'x' )
x = lowerTriangularsolver( A, b )
print( x )
print( 'check' )
print( np.dot(A,x) ) # You can use np.dot(A,x) to check if the solution is correct, it should be equal to b

print( 'Upper case' )
#we know that
#Ax=b| lower case
#x^T A^T = b^T#upper case

#In this example I choosed an array B = A^{T}
#and b2 = b^T
x2 = upperTriangularsolver( A.T, b.T )

print( 'x2' )
print( x2 )

print( 'check')
print( np.dot(x2.T,A.T) )

print( 'b2' )
print( b.T )