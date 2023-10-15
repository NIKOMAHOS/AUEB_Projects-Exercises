import math
from matplotlib import pyplot as plt
import numpy as np

def f(x):
    return np.exp(-x)*np.sin(x)
    
def trapezoid( h, y ):

    n = len(y)
    
    s = y[0]/2.0 # adds f0 -> first point

    for i in range(1,n-1): # sum all the middle points
        s += y[i] # => Σ {i=1 -> n-1} fi
    
    s += y[n-1]/2.0 # adds fn -> last point
    
    # Βάζει το 1/2 στο πρώτο και τελευταίο σημείο για να πιάσει και
    # τον απλό κανόνα του τραπεζίου όπου δεν υπάρχουν μεσαία σημεία
    # και μετά πολλαπλασιάζει με το h -> ΑΝΤΙ ΓΙΑ ΤΟ h/2
    # Ουσιαστικά περνάει το 1/2 του h μέσα στον σύνθετο κανόνα του τραπεζίου,
    # ο οποίος τύπος αντιπροσωπέυει επαρκώς και τον απλό και τον σύνθετο κανόνα
    
    return h * s # VALUE OF INTEGRAL

def myint(a, b, n): # n -> number of subintervals
    h = np.abs(b-a)/n # step length
    # x = np.arange( start=a, stop=b+h, step=h ) # other way
    x = np.linspace(a, b, n+1) # subintervals <=> nodes
    y = f(x) # value of function at subintervals
    return trapezoid(h, y)
    
def main(a, b, n, tol): # n -> number of subintervals
    results = []
    Qh = myint(a, b, n)
    Qh_2 = myint(a, b, 2*n) # 2n -> h/2
    results.append(Qh)
    results.append(Qh_2)
    n = 2*n # adjust n
    
    while np.abs(Qh - Qh_2) > tol: # tol check
        Qh = Qh_2
        Qh_2 = myint(a, b, 2*n) # 2n -> h/2
        results.append(Qh_2)
        n = 2*n # adjust n
         
    return results

# Calling main
x = np.linspace(0, 2.5, 100)
y = f(x)

nodes = np.linspace(0, 2, 3) # interval with 3 nodes
val = f(nodes)

results = main(0, 2, 1, 1e-2)
print(results)

# Calculating order of convergence of the method
print("----------------------------------")
print("----------------------------------")
print('METHOD ORDER OF CONVERGENCE :')
order = np.log2( (np.abs(results[-1] - results[-2])) ) / np.log2(np.abs(results[-3] - results[-4]))
print( order ) # approx. 2.0
print("----------------------------------")
# log2( |Qh| / |Qh/2| ) !!!!! # Only that is converging

# Check Results with built in function
print("----------------------------------")
import numpy as np
    
x1 = np.linspace(0, 2, 2) # 2 nodes in the interval [0,2] and m = n - 1 = 1 subinterval
y1 = f(x1)
print(np.trapz(x1, y1)) # 0.12306002480577674

x2 = np.linspace(0, 2, 3) # 3 -> 2 + 1 nodes and m = n - 1 = 2 subintervals
# because we now have h' = h/2 <=> n' = n + 1
y2 = f(x2)
print(np.trapz(x2, y2)) # -0.12496983844444712 # !!!

# Plotting
plt.plot(x, y, 'r-', label='e^(-x)*sin(x)') # function
plt.plot(nodes , val, 'mo', label='Nodes') # nodes'')
plt.plot(nodes , val, 'g-')
plt.plot([0, 2], [0, 0], 'k-', label='x-axis') # x-axis

# Drawing vertical lines at the boundary points 
plt.plot([nodes[1], nodes[1]], [0, val[1]], 'y-')
plt.plot([nodes[-1], nodes[-1]], [0, val[-1]], 'y-')

# Graph Information

plt.title('Graph')
plt.xlabel('X : value of x for function')
plt.ylabel('Y : value of f(x) for function')

plt.grid()
plt.legend(fontsize='small')

plt.show()
    