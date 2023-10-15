def MaximumSubarraySum(array):
    if len(array) == 1:
        return array[0]
    
    mid = len(array)//2
    
    left = array[:mid]
    right = array[mid:]
    
    maxLeft = MaximumSubarraySum(left) # recursively call the function to calculate the max sum of the left subarray
    maxRight= MaximumSubarraySum(right) # recursively call the function to calculate the max sum of the right subarray
    
    """
    The function here calculates the maximum subarray sum that crosses the midpoint, which is the sum of the maximum subarray sums
    that end at the midpoint and start at the midpoint. It does this by computing the maximum sum of the left subarray that ends at
    the midpoint, maxLeftSum, and the maximum sum of the right subarray that starts at the midpoint, maxRightSum, and adding them together:
    """
    
    # Caclulate the max sum of the left subarray
    maxLeftSum = maxSum = 0 # initialize the max sum of the left subarray to 0
    for i in range (mid-1, -1, -1): # scan array from mid to left
        maxSum += array[i] # add the current element to the sum
        if maxSum > maxLeftSum: # if the current sum is greater than the max sum of the left subarray
            maxLeftSum = maxSum # update the max sum of the left subarray
    
    # Caclulate the max sum of the right subarray
    maxRightSum = maxSum = 0 # reset the max sum of the right subarray to 0
    for i in range (mid, len(array)): # scan array from mid to right
        maxSum += array[i] # add the current element to the sum
        if maxSum > maxRightSum: # if the current sum is greater than the max sum of the right subarray
            maxRightSum = maxSum # update the max sum of the right subarray
    
    maxSumWithMid = maxLeftSum + maxRightSum # calculate the max sum of the subarray that crosses the mid
    
    return max(maxLeftSum, maxRightSum, maxSumWithMid)

arr = [2, -3, 5, -1, 6, -4, 3]
print(MaximumSubarraySum(arr))  
# Output: 10 , subarray: [5, -1, 6]

        
    