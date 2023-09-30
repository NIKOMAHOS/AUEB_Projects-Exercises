# -*- coding: utf-8 -*-
"""
Created on Fri Nov 26 20:03:25 2021

@author: nikomahos
"""
#1 
print("1")
from operator import pow
f_a=0
df_a=0
a=16
X=17
count_1=0
while abs(X-a)>1e-2:
    count_1+=1
    f_a = pow(a, 1/4)
    df_a = (0.25)*pow(X, -0.75)
    f_x = f_a + df_a*(X-a)
    a=pow(f_x, 4)
    print(count_1, f_x, a)

print(" ")
    
#2 
print("2")
x_old=2
x_new=2
count_2=0
while abs(x_new - x_old)>1e-16 or count_2==0:
    count_2+=1
    x_old=x_new
    g_x_old = pow(x_old,4)-17
    dg_x_old = 4*pow(x_old, 3)
    x_new = x_old - g_x_old/dg_x_old
    print(count_2, x_old, x_new)

print(" ")
print(17**(1/4))
print(" ")
print("pow:", pow(17,1/4))
print(" ")

#3
print("3")
from sympy import *
x=Symbol('x')
h_func = x**4 -17
h = lambdify(x,h_func)
dh_func = h_func.diff(x)
dh = lambdify(x, dh_func)
x_old=2
x_new=2
count_2=0
while abs(x_new - x_old)>1e-16 or count_2==0:
    count_2+=1
    x_old=x_new
    x_new = x_old - h(x_old)/dh(x_old)
    print(count_2, x_old, x_new)
