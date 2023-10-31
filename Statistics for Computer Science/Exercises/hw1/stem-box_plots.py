import matplotlib.pyplot as plt
import numpy as np
import scipy.stats as stats

# Provided data
data1 = [30.3, 31.0, 31.2, 32.1, 32.6, 32.7, 33.4, 33.6, 34.2, 34.5]
data2 = [0.0, 0.0, 0.2, 0.8, 1.2, 1.4, 3.2, 4.2, 6.4, 9.0]
data3 = [0, 1, 6, 8, 10, 13, 15, 16, 17, 17, 18, 18, 20, 20, 21, 25, 26, 30, 35, 39, 40,
41, 43, 44, 46, 48, 52, 54, 58, 59, 59, 60, 66, 81, 86, 87, 88, 89, 94, 96]

# Convert data from lists to numpy arrays
data1 = np.array(data1)
data2 = np.array(data2)
data3 = np.array(data3)

print("DATA 1 is: ", data1)
print("***************************************************")
print("DATA 2 is: ", data2)
print("***************************************************")
print("DATA 3 is: ", data3)
print("***************************************************")

def createStemplot(data, dim=1):
    # Create stem and leaf components
    if dim == 1:
        stem = [int(x // 1) for x in data]
        leaf = [int(round((x % 1) * 10)) for x in data]
        plt.figure(figsize=(5, 8))
        
    elif dim == 2:
        stem = [int(x // 10) for x in data]
        leaf = [int((x % 10)) for x in data]
        plt.figure(figsize=(6, 10))
    
    # Create the stemplot using matplotlib

    plt.stem(stem, leaf, linefmt='-', markerfmt='o', basefmt=" ")
    plt.yticks(np.arange(min(leaf), max(leaf)+1, 1))
    plt.xticks(np.arange(min(stem), max(stem)+1, 1))
    plt.xlabel('Stem')
    plt.ylabel('Leaf')
    plt.title('Stemplot')
    plt.grid(True)
    
    plt.show()

createStemplot(data1)
createStemplot(data2)
createStemplot(data3, 2)

def createBoxplot(data):
    plt.figure(figsize=(5, 8))
    plt.boxplot(data)
    plt.title('Boxplot')
    plt.show()

createBoxplot(data1)
createBoxplot(data2)
createBoxplot(data3)

# Calculate mean and standard deviation for each dataset
mean_data1 = np.mean(data1)
mean_data2 = np.mean(data2)
mean_data3 = np.mean(data3)

std_data1 = np.std(data1)
std_data2 = np.std(data2)
std_data3 = np.std(data3)

print("The median of DATA 1 is: ", np.median(data1))
print("The median of DATA 2 is: ", np.median(data2))
print("The median of DATA 3 is: ", np.median(data3))
print("----------------------------------------------------")
print("The standard deviation of DATA 1 is: ", std_data1)
print("The standard deviation of DATA 2 is: ", std_data2)
print("The standard deviation of DATA 3 is: ", std_data3)
print("----------------------------------------------------")
print("The summary of DATA 1 is: \n", np.percentile(data1, [25, 50, 75]), "\n", np.array(data1).min(), "\n", np.array(data1).max() )
print("The summary of DATA 2 is: \n", np.percentile(data2, [25, 50, 75]), "\n", np.array(data2).min(), "\n", np.array(data2).max() )
print("The summary of DATA 3 is: \n", np.percentile(data3, [25, 50, 75]), "\n", np.array(data3).min(), "\n", np.array(data3).max() )

print(
"""
Data1: 
The range of the data is small (34.5 − 30.3 = 4.2), and the standard deviation is also low (1.34).
In this case, the median and standard deviation are sufficient to describe the central tendency and dispersion.

Data2: 
The data has a wider range (9.0 − 0.0 = 9.0) and a higher standard deviation (2.90).
The 5-number summary provides more information about the spread and quartiles, making it more descriptive for this dataset.

Data3:
The range is much larger (96 − 0 = 96)and the standard deviation is also high (27.91).
Given the wide spread, the 5-number summary would be more descriptive for understanding the distribution.

In summary, for Data1, the median and standard deviation are quite informative,
whereas for Data2 and Data3, the 5-number summary offers a more descriptive analysis.​
"""
)

# Function to plot density alongside real data points
def plot_density(data, mean, std_dev, title):
    # Generate synthetic normal distribution data
    x = np.linspace(min(data) - 2, max(data) + 2, 1000)
    y = stats.norm.pdf(x, mean, std_dev)
    
    # Plotting
    plt.figure(figsize=(10, 4))
    plt.title(title)
    plt.hist(data, density=True, alpha=0.5, label='Real Data', bins=15)
    plt.plot(x, y, label='Normal Distribution')
    plt.legend()
    plt.show()

# Plotting density plots for each dataset
plot_density(data1, mean_data1, std_data1, 'Density Plot for Data1')
plot_density(data2, mean_data2, std_data2, 'Density Plot for Data2')
plot_density(data3, mean_data3, std_data3, 'Density Plot for Data3')




