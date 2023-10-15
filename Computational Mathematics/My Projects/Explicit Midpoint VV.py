# Explicit Midpoint method python program

# function to be solved - basically y' = f(x,y) that is, the derivative

from matplotlib import pyplot as plt
import numpy as np


def f(x,y):
    return x*y+x**3


# Explicit Midpoint method
# input parameters : x0 = x(start) : initial x (t) position      
#                    y0 = y(x0): value of y(x) at initial x0 
#                    xn = x(end) : final x (t) position 
#                    
# υλοποίηση παραλλαγής για άσκηση Βασάλου: 
#                    m  = number of nodes (should be #steps+1)
# χωρίς τη προσθήκη των input για ερώτηση αρχικών παραμέτρων

# απαιτούμενη προσθήκη άσκησης Βασάλου:

# Να γίνουν στην ίδια γραφική παράσταση τα γραφήματα με τις τιμές που
# υπολογίσατε στο προηγούμενο ερώτημα και πιο συγκεκριμένα 
# με πράσινα '*' οι τιμές που πήραμε για n =  5  &
# με μαύρα   '-' οι τιμές που πήραμε για n = 10

# πιθανή επέκταση --->  
# Παύση ρουτίνας όταν έχει επιτευχθεί 
# κάποιο δοσμένο επίπεδο ακρίβειας tolerance - πιθανότατα
# έναντι της ακριβούς αναλυτικής τιμής που θα έχει δοθεί.

# (Βρες αριθμό "κόμβων" για δοσμένη tolerance ?!)
# ---> ----->

# Συμπεριφορά μείωσης σφάλματος (έναντι ακριβούς τιμής ή εναλλακτικά
# πειραματικά μέσω των διαδοχικώνν λόγων της εκτίμησης της λύσης)

def explicit_midpoint(x0,y0,xn,m):
    
    # calculate time steps from time frames (points)
    n= m-1
    
    # Calculating step size
    h = (xn-x0)/n
    
    x_Nodes = np.linspace(x0,xn,m)
    y_Nodes = np.zeros(m)
    
    y_Nodes[0] = y0
    
    print('\n--SOLUTION-EXPLICIT MIDPOINT--')
    print('------------------------------')    
    print('x0\ty0\tslope\tyn')
    print('------------------------------')
    
    # Συνολικά 0-> m-1 = n βήματα 
    
    for i in range(1, n+1):
        
        # (1) First phase
        
        # calculate the slope of y(t) [derivative f] at x0 
        slope_0 = f(x0, y0)
        
        # calculate the function y at the midpoint
        # between the current and the next time frame
        # using the slope of y(x) at the current time frame
        # using simply : Euler's forward method
        
        yn_mid = y0 + (h/2.) * slope_0
        
        # (2) Second phase
        
        # calculate the slope of y(t) [derivative f] 
        # at the midpoint : x = x0 + (h/2)
        # using the computed value of y(x0+h/2) => yn_mid
        
        mid = x0+h/2.
        slope_mid = f(mid, yn_mid)
        
        # Calculate the function y at the next time frame x0+h
        # using the computed slope of y(x) at the midpoint x0+h/2
        # this is going to be the final estimate of  
        # the approximation of y(x) at x0 + h, that is: y(x0+h)
        
        slope = slope_mid
        yn = y0 + h * slope
        
        y_Nodes[i] = yn
        
        print('%.4f\t%.4f\t%0.4f\t%.4f'% (x0,y0,slope,yn) )
        print('------------------------------')
        
        # prepare for next step
        # Adjust - update the value of the function at the "current frame" 
        
        y0 = yn
        
        # Adjust - update the value of the "current frame" 
        x0 = x0+h
    
    print('\nAt x=%.4f, y=%.4f' %(xn,y0))
    
    return x_Nodes, y_Nodes

# Inputs

# 'Enter initial conditions:'
x0 = 0
y0 = 1

#'Enter calculation point: '
xn = 1

# Midpoint method call
nodes_5 = explicit_midpoint(x0,y0,xn,5)
nodes_10 = explicit_midpoint(x0,y0,xn,10)


#Plotting 

plt.plot(nodes_5[0],nodes_5[1],'g*', label='Nodes n = 5')
plt.plot(nodes_10[0],nodes_10[1],'b_', label='Nodes n = 10')

plt.title('Explicit Midpoint Method')
plt.xlabel('x')
plt.ylabel('y')
plt.grid()
plt.legend()
plt.show()