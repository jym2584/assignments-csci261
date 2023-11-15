def max_hopscotch_sum(nums):
    n = len(nums)
    if n == 0:
        return 0
    if n == 1:
        return nums[0]

    dp = [0] * n
    dp[0] = nums[0]
    dp[1] = nums[1]

    for i in range(2, n):
        dp[i] = max(dp[i - 2], dp[i - 3]) + nums[i]

    return max(dp[n - 1], dp[n - 2])

# Example usage
nums = [1, 5, 2, 3, 6, 1, 8]
result = max_hopscotch_sum(nums)
print(result)  # Output: 17
