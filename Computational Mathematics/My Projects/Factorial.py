# -*- coding: utf-8 -*-
"""
Created on Sat Nov 27 16:51:20 2021

@author: nikomahos
"""
#With ForLoop
def factorial(n):
    """Calculates the factorial of a number."""
    if n==1 or n==0:
        return 1
    elif n<0:
        print("Invalid input. Factorials for negative numbers do not exist.")
    elif n>1:
        factorial=1
        for i in range(1,n+1):
            factorial=factorial*i
        return factorial
    else:
        print("Error.")
factorial(-1)
print(factorial(2))
print(factorial(0))
print(factorial(1))
print(factorial(5))

#With Recursion
def recur_factorial(m):
    if m==1 or m==0: 
        return 1
    elif m<0:
        print("Invalid input. Factorials for negative numbers do not exist.")
    elif m>1:
        return m*recur_factorial(m-1)
    else:
       print("Error.") 
num = int(input("Enter a number: "))
print("The factorial of ", num, "is", recur_factorial(num), ".")
    
#No point writing it with WhileLoop