import numpy
import math
import matplotlib.pyplot as PLUDecomposition
import copy
from LinearAlgebra import *

def ForwardDif(f):
    x = copy.deepcopy(f)
    result = []

    for i in range(1, len(f)):
        for j in range(len(f) - i):
            x[j] = x[j + 1] - x[j]

        print (x[:-i])
        result.append(copy.deepcopy(x[:-i]))
    
    return result

def BackwardDif(f):
    x = copy.deepcopy(f)
    result = []

    for i in range(len(f) - 1):
        for j in range(len(f) - 1,  i, -1):
            x[j] = x[j] - x[j - 1]

        result.append(copy.deepcopy(x[i + 1:]))
    
    return result

def DividedDif(f, x):
    result = []
    y = copy.deepcopy(f)

    for i in range(1, len(f)):
        for j in range(len(f) - i):
            y[j] = (y[j + 1] - y[j]) / (x[j + i] - x[j])


        result.append(copy.deepcopy(y[:-i]))
    return result

def NewtonForward(f, x, theta):

    diff = ForwardDif(f)
    result = f[0]

    for i in range(1, len(diff) + 1):
        temp = 1
        for j in range(i):
            temp *= (theta - j) / (j + 1)
        
        result += diff[i - 1][0] * temp
    
    return result

def NewtonBackward(f, x, theta):

    diff = BackwardDif(f)
    result = f[-1]

    for i in range(1, len(diff) + 1):
        temp = 1
        for j in range(i):
            temp *= (theta + j) / (j + 1)
        
        result += diff[i - 1][-1] * temp
    
    return result

def Chebyshev(a, b, n):


    return [(b + a) / 2 + (b - a) / 2 * math.cos((2 * i - 1) * math.pi / (2 * n)) for i in range(1, n + 1)]

def NewtonDivided(f, x, x_star):

    diff = DividedDif(f, x)
    result = f[0]

    for i in range(1, len(diff) + 1):
        temp = 1
        for j in range(i):
            temp *= x_star - x[j]

        result += temp * diff[i - 1][0]

    return result

def Lagrange(f, x, x_star):

    result = 0
    for i in range(len(f)):
        temp = 1
        for j in range(len(f)):

            if j == i: continue

            temp *= (x_star - x[j]) / (x[i] - x[j])

        result += temp * f[i]
    return result

def SplinesNatural(f, h):
    A = []

    for i in range(len(f)):
        if i == 0:
            A.append([1 if j == 0 else 0 for j in range(len(f) + 1)])
        elif i == len(f) - 1:
            A.append([1 if j == len(f) - 1 else 0 for j in range(len(f) + 1)])
        else:
            row = numpy.zeros(len(f) + 1)
            row[i - 1] = h[i]
            row[i] = 2 * (h[i - 1] + h[i])
            row[i + 1] = h[i]
            row[-1] = 3 * (f[i + 1] - f[i]) / h[i] - 3 * (f[i] - f[i - 1])/ h[i - 1]
            A.append(copy.deepcopy(row))

    x = gauss(A)

    b = [(f[i + 1] - f[i]) / h[i] - h[i] * (2 * x[i] + x[i + 1]) / 3 for i in range(len(f) - 1)]
    d = [(x[i + 1] - x[i]) / h[i] / 3 for i in range(len(f) - 1)]

    return f[:-1], b, x[:-1], d

            