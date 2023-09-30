import numpy as np

A = np.array( [ [1,0,0,0,0], [1,2,0,0,0], [1,2,3,0,0], [1,2,3,4,0], [1,2,3,4,5] ], dtype='float' )

S = np.dot( A.T, A )

def cholesky(a):
    #Initialize the L matrix
    """
    YOUR CODE HERE
    """

    #Implement Cholesky algorithm, remember that L is lower triangular and A= L L^T
    """
    YOUR CODE HERE
    """ 

    return L

L = cholesky(S)

print(L)
print( np.dot(L,L.T) ) # You can use np.dot(L,L.T) to check if the solution is correct, it should be equal to S