from math import *

import numpy as np
import sympy as sp

a = (60/3**6)
print(a/((2**5)*6))

def f(x):
    return 1/x**3
    
x_1 = 3.933
x_2 = 3.5
x_3 = 3.067

print("f(x_1) , f(x_2) , f(x_3)")
print(f(x_1))
print(f(x_2))
print(f(x_3))

f_1 = (f(x_2) - f(x_1))/(x_2 - x_1)
print("f[x_1, x_2]")
print(f_1)

f_2 = (f(x_3) - f(x_2))/(x_3 - x_2)
print("f[x_2, x_3]")
print(f_2)

print("f[x_1, x_2, x_3]")
f_3 = (f_2 - f_1) / (x_3 - x_1)
print( f_3 )  # type: ignore

Symbol = sp.Symbol('x')
print()
sp.pprint(1/Symbol**3)
