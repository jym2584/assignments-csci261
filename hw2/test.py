def largest_hopscotch_sum(arr):
    n = len(arr)
    
    # Create an array to store the maximum sum up to each square
    max_sum = [0] * n

    # Initialize the first two elements of max_sum based on the first square values
    max_sum[0] = arr[0]
    max_sum[1] = arr[1]

    # Iterate through the rest of the squares
    for i in range(2, n):
        # Calculate the maximum sum up to square i using the two possible jump options
        max_sum[i] = arr[i] + max(max_sum[i - 2], max_sum[i - 3])

    # The largest sum is the maximum of the last two elements in max_sum
    largest_sum = max(max_sum[-1], max_sum[-2])

    return largest_sum

# Example usage
arr1 = [1, 5, 2, 3, 6, 1, 8]
arr2 = [1, 5, 2, 6, 3, 8, 1]

print("Largest hopscotch sum (arr1):", largest_hopscotch_sum(arr1))
print("Largest hopscotch sum (arr2):", largest_hopscotch_sum(arr2))