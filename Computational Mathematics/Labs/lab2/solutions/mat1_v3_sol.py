import numpy as np
import matplotlib.pyplot as plt

x = np.linspace( -2.5, 2.5, 10 ).reshape( (-1,1) )

#plot x^2 with red circles
plt.plot( x, x*x, 'ro', label='x^2' )

#plot exp(x) with blue dashed line
plt.plot( x, np.exp(x), 'b--', label='exp' )

#plot sin(x) with sold mangent
plt.plot( x, np.sin(x), '-g', label='sin' )

plt.title( 'Matplotlib plot')
plt.xlabel( 'x' )
plt.ylabel( 'y(x)' )

plt.legend()
plt.show()