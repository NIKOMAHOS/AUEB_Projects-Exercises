import matplotlib.pyplot as plt
import math
import numpy as np

# Real Value
r = math.pow(21,1/3) # 2.7589241763811203
print(f'Real Value: {r}')

#******************************************************#
"""
Ο λόγος για τον οποίο το δεύτερο επαναληπτικό σχήμα δεν 'συγκλίνει' στην ρίζα
ή συγκλίνει 'πολύ αργά', είναι ο εξής:
g2'(x) = (20*x**3 - 42) / (21*x**3)

Παρατηρούμε όμως ότι η τιμή της παραγώγου στη ρίζα είναι πολύ κοντά στο μηδέν.
Δηλαδή ουσιαστικά ισχύει ότι: g2'(r) = 0.
Πραγματικά είναι : g2'(2.7589241763811203) = 0.18615384615384695.
"""
#******************************************************#

# First Iteration Scheme
g1 = lambda x: x - ( math.pow(x,3) - 21 ) / ( 3 * math.pow(x,2) )

# Second Iterative Scheme
g2 = lambda x : (20*x + ( 21/math.pow(x,2) ) ) /21

#  Fixed Point Iteration Method
def FixedPointIteration(g, x0, tol=1e-8, maxIter = 100):
    
    init = x0 # save initial guess for print message
    success = False # flag to indicate if root was found
    x1 = -1 # initialize variable
    errors = [] # initialize list to store errors
    log = [] # initialize list to store iterations
    
    for i in range(maxIter):
        x1 = g(x0)
        
        log.append(x1)
        errors.append(abs(x1 - r))
        
        if np.abs(x1 - r) < tol:
            success = True
            print(f'Root found at {x1} after {i} iterations for initial guess {init}')
            break
            
        x0 = x1
    
    if not success:
        print(f'Root not found after {maxIter} iterations')
        
    return x1, log, errors
    
def Aitken(g, x, maxIter=100, tol = 1e-8):
    p0 = x
    p1 = g(p0)
    p2 = g(p1)
    success = False
    
    for i in range(maxIter):
        p0 = p2 - math.pow(p1 - p0, 2) / (p2 - 2 * p1 + p0)
        p1 = g(p0)
        p2 = g(p1)
        
        if (np.abs(p2 - r) < tol):
            success = True
            print(f'Root found at {p2} after {i} iterations for initial guess {x}')
            break
    if not success:
        print(f'Root not found after {maxIter} iterations')
        
    return p2


# Calling the function

r1 = FixedPointIteration(g1, 1)
r2 = FixedPointIteration(g2, 1)
r3 = Aitken(g2, 1)

# Plotting
plt.title('Fixed Point Iteration Method')
plt.xlabel('approximation')
plt.ylabel('error')

plt.plot(r1[1][0:11], r1[2][0:11], 'ro', label='g1(x)')    
plt.plot(r2[1][0:11], r2[2][0:11], 'g*', label = 'g2(x)')


plt.grid()
plt.legend()
plt.show()