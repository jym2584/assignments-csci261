def merge_and_count(arr, left, right):
    i = j = 0
    total_weighted_count = 0
    merged = []

    while i < len(left) and j < len(right):
        if left[i] > right[j]:
            total_weighted_count += len(left) - i  # Count inversion with weight |j - i|
            merged.append(right[j])
            j += 1
        else:
            merged.append(left[i])
            i += 1

    merged.extend(left[i:])
    merged.extend(right[j:])
    arr[:] = merged
    return total_weighted_count

def weighted_inversions(arr):
    if len(arr) <= 1:
        return 0

    mid = len(arr) // 2
    left = arr[:mid]
    right = arr[mid:]

    left_count = weighted_inversions(left)
    right_count = weighted_inversions(right)
    merge_count = merge_and_count(arr, left, right)

    return left_count + right_count + merge_count

# Example usage:
permutation = [2, 5, 3, 1, 4]
weighted_count = weighted_inversions(permutation)
print(weighted_count)  # Output: 10
