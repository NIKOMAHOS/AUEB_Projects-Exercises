import numpy
import math

def Bisection(function, a, b, tol, maxIter):
    
    iter = 0
    c = 0 # initialize variable
    
    while iter < maxIter:
        c = a + (b - a) / 2

        if function(c) == 0 or b - a < tol:
            return c
        
        if numpy.sign(function(a)) == numpy.sign(function(c)):
            a = c
        else:
            b = c

    iter += 1

    return c

def RegulaFalsi(function, a, b, tol, maxIter):
    
    iter = 0  
    c = 0 # initialize variable
    
    while iter < maxIter:

        c = (a * function(b) - b * function(a)) / (function(b) - function(a))

        if function(c) == 0 or b - a < tol:
            return c
        
        print(str(a) + " " + str(c) + " " + str(b))

        if numpy.sign(function(a)) == numpy.sign(function(c)):
            a = c
        else:
            b = c

        iter += 1

    return c

def NewtonRaphson(function, derivative, x, tol, maxIter):

    iter = 0
    temp = tol + 1
    while iter < maxIter and abs(temp) > tol:

        temp = function(x) / derivative(x)
        x = x - temp

        iter += 1

    return x

def Secant(function, x0, x1, tol, maxIter):

    iter = 0
    temp = tol + 1
    f0 = function(x0)
    f1 = function(x1)
    
    x2 = 0 # initialize variable
    
    while iter < maxIter and abs(temp) > tol:

        temp = f1*(x1 - x0) / (f1 - f0)

        x2 = x1 - temp

        x0, x1 = x1, x2
        f0, f1, = f1, function(x1)

        iter += 1

    return x2

def Aitken(p):
    return [p[i + 2] - math.pow(p[i + 1] - p[i], 2) / (p[i + 2] - 2 * p[i + 1] + p[i]) for i in range(len(p) - 2)]

def Steffensen(f, x, maxIter):
    p0 = x
    p1 = f(p0)
    p2 = f(p1)

    for i in range(maxIter):
        p0 = p2 - math.pow(p1 - p0, 2) / (p2 - 2 * p1 + p0)
        p1 = f(p0)
        p2 = f(p1)

    return p2