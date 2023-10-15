# Code to find the emach value of a given system:
eps = 1.
while 1.+0.5*eps != 1.:
    eps /= 2.
print(eps)
# prints 2.220446049250313e-16