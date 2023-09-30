import numpy as np

val = np.random.random()#return number at range [0,1)

#print number at range[0,x)
x = 10
val = np.random.random() * x
print(val)
#print number at range (a,b)
a = 10
b = 20
val = np.random.random() * (b-a) + a
print(val)
#print number at range [n,m] when n and m are integers
n = 10
m = 20
val = np.random.random() * (m-n+1) + n
print(val)
