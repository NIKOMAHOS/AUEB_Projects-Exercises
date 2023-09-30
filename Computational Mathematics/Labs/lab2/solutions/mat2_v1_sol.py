import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(0, 2, 100)

f, axes = plt.subplots( 3, 1, figsize=(3*7,1*7) )

axes[0].plot( x, x )#, label='linear' ) 
axes[1].plot( x, x**2 )#, label='quadratic' )
axes[2].plot( x, x**3 )#, label='cubic' )

axes[0].set_ylim( [0,2] )
axes[1].set_ylim( [0,2] )
axes[2].set_ylim( [0,2] )
axes[2].set_xlabel('x label')
axes[0].set_ylabel('linear')
axes[1].set_ylabel('quadratic')
axes[2].set_ylabel('cubic')

plt.legend()
plt.show()