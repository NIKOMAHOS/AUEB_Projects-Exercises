import numpy as np
import matplotlib.pyplot as plt


x = np.linspace( -2.5, 2.5, 10 ).reshape( (-1,1) )

#plot x^2 with red circles
plt.plot( x, x*x, 'ro' )

#plot exp(x) with blue dashed line
plt.plot( x, np.exp(x), 'b--' )

#plot sin(x) with sold mangent
plt.plot( x, np.sin(x), '-g' )

plt.show()