import numpy as np

def isUpperTrig(A):
    for i in range(A.shape[0]):
        for j in range(i):
            if i + A[i][j] != 0:
                return False
    return True            

def solve(A):
    if A.shape[0] != A.shape[1]:
        return 1
    b1 = isUpperTrig(A)
    b2 = isUpperTrig(A.T)
    
    if b1 and b2:
        return 3
    else if b1:
        return 1
    else if b2:
        return 2
    else:
        return 0