from math import exp

# Define total number of steps to try (-1), step sizes and initial conditions
number_of_steps = 11
steps = 0.5, -0.5
x_0 = 0
y_0 = 0.5

# function to compute exact analytical solution to ODE to 
# check the level of accuracy of numerical approximation

solution = lambda x: 1/(1+exp(-x))
# for this particular case the function at the R.H.S of the ode
# which expresses the value of the derivative of the function,
# it just happens to not involve any explicit reference of x by itself.

# function to compute the numerical approximation to the derivative of
# the function at any given step during the implementation of the method.
def slope(x, y):
    return y * (1-y)
# in our particular case x is passed in but it never gets used.

# Function to implement the euler solution
# ---> σύμφωνα με τις οδηγίες της άσκησης <--------

# Input argumnets : (1) Function definition
#                   via a separate function
#                   (2) initial value in x
#                   (3) inital  value in y
#                   (4) step magnitude to be used
#                   (5) numbers of steps to try

def euler(f, init_x, init_y, Dx, N):

# initialize 2 lists for the x and y values
    x = [ init_x + pos*Dx  for pos  in range(N)] # compatible with initial position
    y = [init_y] # for efficiency - better make it generator? 0 or null?

# update these lists with the corresponding  - specified - initial conditions
 #   x[0] = init_x
 #   y[0] = init_y

# perform the Euler step from the current step to the next step
# calling the function f (-> slope) to compute the derivative 
# at any current step in sequence (f has been passsed as 
# an input argument) using the status of the previous step.

    for step in range(1,N):
        y.append(y[step-1] + Dx * f(x[step-1], y[step-1]))

    print(f'Περιγραφή των αποτελεσμάτων επίλυσης με μέθοδο Euler')

# print in ascending order : step index, approximate solution, exact solution each of the N steps
    if Dx > 0:
        print(f'      Bήμα i     Θέση x(i)    Εκτίμηση y(i)')
        for index in range(N):
            print(f'{index: 10d}     {x[index]:10.4f}     {y[index]:10.4f}')
        
    else : # assume proper check have been done in the main program -> Dx<0
        print(f'      Bήμα i     Θέση x(i)    Εκτίμηση y(i)')
        # Reversing the lists (in place) for the case of negative step
        # in order to print in descending order 
        x.reverse()   
        y.reverse() 
        for index in range(N):
            print(f'{(N-1)-index: 10d}     {x[index]:10.4f}     {y[index]:10.4f}')

# return both the x and y values for printing purposes (in case they might be needed) 
# otherwise for the demands of the exercise there is no need to return anything at all.
    return x, y


# -------------------------------------------------------------------------------------- #

# compute the exact analytic solution at the x values used. 
exact1 = [solution(index*steps[0]) for index in range(number_of_steps)]
exact2 = [solution(index*steps[1]) for index in range(number_of_steps)]

# call the function to actually perform the euler solution, passing into
# (1) the relevant function to compute the derivative at each step
# (2)/(3) the initial conditions (4) the step & (5) the number of steps.
xs_1, ys_1 = euler(slope, x_0, y_0, steps[0], number_of_steps)
print("")
xs_2, ys_2 = euler(slope, x_0, y_0, steps[1], number_of_steps)

