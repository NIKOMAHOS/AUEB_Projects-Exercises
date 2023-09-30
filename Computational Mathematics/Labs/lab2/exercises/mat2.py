import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(0, 2, 100)

#use subplots
#e.x. plt.subplots( ... figsize=(30,10) )

plt.plot( x, x, label='linear' ) 
plt.plot( x, x**2, label='quadratic' )
plt.plot( x, x**3, label='cubic' )

plt.xlabel('x label')
plt.ylabel('y label')

#set y_lim per subplot
#object[0].set_ylim( [0,2] )
#...object[...].set_ylim( [0,2] )

plt.title("Simple Plot")

plt.legend()
plt.show()