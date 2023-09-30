import numpy as np

def isUpperTrig(A):
    for i in range( A.shape[0] ):
        for j in range( i ):
            if A[i][j] != 0:
                return False
    return True

def solve(A):
    #based on the statement use isUpperTrig to classify the matrix
    if len(A.shape) != 2:
        return -1
    if A.shape[0] != A.shape[1]:
        return -1
    
    c1 = isUpperTrig(A)
    c2 = isUpperTrig(A.T)

    if c1 and c2:
        return 3
    elif c1:
        return 1
    elif c2:
        return 2
    else:
        return 0

A = np.eye(7)
B = np.zeros( (7,7) )
C = np.ones( (10,3) )
D = np.eye(7)
D[0,6] = 1
E = D + D.T

msg = { -1: 'not square',
        0: 'just square',
        1: 'upper triangle',
        2: 'lower triangle',
        3: 'diagonal'}

matrices = [A,B,C,D,D.T,E]

for i in matrices:
    idx = solve(i)
    print( msg[idx] )