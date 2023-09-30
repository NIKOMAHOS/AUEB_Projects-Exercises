# -*- coding: utf-8 -*-
"""
Created on Fri Nov 12 13:22:36 2021

@author: nikomahos
"""
#ΑΣΚΗΣΗ 26 ΚΑΙ 27
f= open("print from python.txt", "w+")
import math
 
def func(x):
	return 2*math.cos(x)-x


def bisection(a, b, func, tol_x, tol_f):
    if b<a:
        print("Μη έγκυρα όρια διαστήματος.")
        return
    if (func(a) * func(b) >= 0):
        print("Λανθασμένη αρχική επιλογή διαστήματος.\n")
        return
    
    N_Max = int(math.ceil(math.log(abs(b-a)/tol_x))/math.log(2.0))
    #print(N_Max)
    
    m=(a+b)/2
    iteration=1
    print( f'{"  n":^5}', f'{"a":^8}', f'{"b":^8}', f'{"m":^8}' , f'{"f(a)":^8}' , f'{"f(b)":^8}' , f'{"f(m)":^8}')
    print()
    while ((abs(b-a)/2 > tol_x) and (abs(func(m))>tol_f)):
        print(f'{iteration:4d}', f'{a:8.4f}', f'{b:8.4f}', f'{m:8.4f}', f'{func(a):8.4f}', f'{func(b):8.4f}', f'{func(m):8.4f}')
        if (func(m)*func(a) < 0):
            b = m
        elif func(m)*func(b) < 0:
            a = m
        m=a+(b-a)/2
        iteration+=1
    return 

a = 1.
b = 3.
tolerance_in_root = 1e-3
tolerance_in_function = 1e-3
bisection(a, b, func, tolerance_in_root, tolerance_in_function)
f.close()