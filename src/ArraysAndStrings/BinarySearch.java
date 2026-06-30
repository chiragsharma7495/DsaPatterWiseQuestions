package ArraysAndStrings;

/**
 * Binary Search Problems Collection
 * 
 * This class contains various binary search problems from LeetCode and coding interviews.
 * Binary search is efficient for searching in sorted arrays with O(log n) time complexity.
 */
public class BinarySearch {

    /**
     * LeetCode 35 - Search Insert Position
     * 
     * Given a sorted array and a target value, return the index if found.
     * If not found, return the index where it would be if it were inserted in order.
     * 
     * Approach: Standard binary search to find target or insertion position
     * 
     * Example: nums = [1, 3, 5, 6], target = 5 → return 2
     *          nums = [1, 3, 5, 6], target = 7 → return 4
     *          nums = [1, 3, 5, 6], target = 0 → return 0
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param nums - Sorted array of integers
     * @param target - Target value to search or insert
     * @return Index of target if found, otherwise index for insertion
     */
    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        while(left <= right){
            int mid = left + (right - left) /2;

            if(nums[mid] == target) return mid;
            else if(nums[mid] < target){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return left;
    }

    /**
     * LeetCode 69 - Sqrt(x)
     * 
     * Given a non-negative integer x, return the integer part of the square root of x.
     * 
     * Approach: Binary search to find the largest number whose square is ≤ x
     * 
     * Example: x = 4 → return 2
     *          x = 8 → return 2 (sqrt(8) ≈ 2.82, integer part is 2)
     *          x = 1 → return 1
     *          x = 0 → return 0
     * 
     * Time Complexity: O(log x)
     * Space Complexity: O(1)
     * 
     * @param x - Non-negative integer
     * @return Integer square root of x
     */
    public int mySqrt(int x) {
         int left = 0;
         int right = x;
         int result = 0;

         while(left <= right){
             int mid = left + (right - left) /2;
             long midSquare = (long) mid * mid;
             if(midSquare == x) return mid;
             else if(midSquare < x){
                 result = mid;
                 left = mid+1;
             }else{
                 right = mid-1;
             }
         }
         return result;
    }

    /**
     * LeetCode 744 - Find Smallest Letter Greater Than Target
     * 
     * Given a sorted array of characters and a target character, return the smallest character
     * in the array that is greater than target. If no such character exists, return the first character.
     * 
     * Approach: Binary search to find the first character greater than target
     * 
     * Example: letters = ['c', 'f', 'j'], target = 'a' → return 'c'
     *          letters = ['c', 'f', 'j'], target = 'c' → return 'f'
     *          letters = ['c', 'f', 'j'], target = 'j' → return 'c' (wrap around)
     * 
     * Time Complexity: O(log n) where n is length of letters
     * Space Complexity: O(1)
     * 
     * @param letters - Sorted array of characters
     * @param target - Target character
     * @return Smallest character greater than target, or wrap to first
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length-1;

        if(target >= letters[letters.length-1]) return letters[0];

        while( left <= right){
            int mid = left + (right - left) /2;
            if(letters[mid] > target) right = mid-1;
            else{
                left = mid+1;
            }
        }
        return letters[left];
    }

    /**
     * LeetCode 278 - First Bad Version
     * 
     * You have n versions [1, 2, ..., n] and you want to find the first bad version.
     * The bad version propagates to all later versions (if version x is bad, all versions > x are bad).
     * You can call isBadVersion(mid) to check if a version is bad (limited API calls).
     * 
     * Approach: Binary search to find the first bad version where isBadVersion returns true
     * 
     * Example: n = 5, badVersion = 4
     *          1, 2, 3 are good; 4, 5 are bad
     *          Return 4 (first bad version)
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param n - Total number of versions
     * @return Index of the first bad version
     */
    public int firstBadVersion(int n) {
        int left = 0; int right = n; int result = n;

        while(left <= right){
            int mid = left + (right - left) /2;
            if(isBadVersion(mid)){
                result = mid;
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return result;
    }

    private boolean isBadVersion(int mid) {
        return false;
    }

    /**
     * LeetCode 162 - Find Peak Element
     * 
     * A peak element is an element strictly greater than its neighbors.
     * An array may contain multiple peaks. Find ANY one peak element.
     * Elements are assumed to be distinct; assume arr[-1] = arr[n] = -∞
     * 
     * Approach: Binary search. If mid > mid+1, then a peak exists on the left side (including mid).
     *           Otherwise, a peak exists on the right side.
     * 
     * Example: nums = [1, 2, 3, 1] → return 2 (nums[2] = 3, peak element)
     *          nums = [1, 2, 1, 3, 5, 6, 4] → return 5 (nums[5] = 6, peak element)
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param nums - Array where element at each index i: nums[i] != nums[i + 1]
     * @return Index of a peak element
     */
    public int findPeakElement(int[] nums) {
        int left = 0; int right = nums.length-1;

        while(left <= right){
            int mid = left + (right-left) /2;
            if(nums[mid] > nums[mid+1]) right = mid;
            else{
                left = mid+1;
            }
        }
        return left;
    }

    /**
     * LeetCode 33 - Search in Rotated Sorted Array
     * 
     * A sorted array is rotated at an unknown pivot. Search for a target value.
     * Return the index if found, otherwise return -1.
     * 
     * Approach: Binary search. One half is always sorted. Check if target is in sorted half.
     *           If yes, search in that half; otherwise search in the other half.
     * 
     * Example: nums = [4, 5, 6, 7, 0, 1, 2], target = 0 → return 4
     *          nums = [4, 5, 6, 7, 0, 1, 2], target = 3 → return -1
     *          nums = [1], target = 1 → return 0
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param arr - Rotated sorted array
     * @param key - Target value to search
     * @return Index of target if found, -1 otherwise
     */
    int search(int[] arr, int key) {
        int left = 0; int right = arr.length-1;

        while(left <= right){
            int mid = left + (right - left) /2;
            if(arr[mid] == key) return mid;

            if(arr[left] <= arr[mid]){
                if(key >= arr[left] && key < arr[mid]){
                    right = mid-1;
                }else{
                    left = mid + 1;
                }
            }else{
                if(key > arr[mid] && key <= arr[right]){
                    left = mid + 1;
                }else{
                    right = mid-1;
                }
            }
        }
        return -1;
    }

    /**
     * LeetCode 153 - Find Minimum in Rotated Sorted Array
     * 
     * A sorted array of distinct integers is rotated at an unknown pivot.
     * Find the minimum element in the array.
     * 
     * Approach: Binary search. If arr[left] < arr[right], minimum is arr[left] (no rotation in this segment).
     *           Otherwise, check mid and search the side that might contain the minimum.
     * 
     * Example: nums = [3, 4, 5, 1, 2] → return 1
     *          nums = [2, 1] → return 1
     *          nums = [1] → return 1
     *          nums = [1, 3] → return 1
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param arr - Rotated sorted array of distinct integers
     * @return Minimum element in the array
     */
    public int findMin(int[] arr) {
        int left = 0; int right = arr.length-1;
        int result = arr[0];

        while(left <= right){
            if(arr[left] < arr[right]){
                result = Math.min(result , arr[left]);
                break;
            }

            int mid = left + (right - left) /2;
            result = Math.min(result , arr[mid]);

            if(arr[mid] >= arr[left]) left = mid+1;
            else{
                right = mid-1;
            }
        }
        return result;
    }

    /**
     * LeetCode 74 - Search a 2D Matrix
     * 
     * Write an efficient algorithm that searches for a target value in an m x n integer matrix.
     * Matrix properties: Integers increase left to right and top to bottom.
     * 
     * Approach: Treat 2D matrix as a 1D sorted array. Use binary search on flattened indices.
     *           Convert mid index to 2D coordinates using: row = mid/cols, col = mid%cols
     * 
     * Example: matrix = [[1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 60]], target = 3
     *          Return true (found at position [0][1])
     *          matrix = [[1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 60]], target = 13
     *          Return false (not found)
     * 
     * Time Complexity: O(log(m*n)) where m is rows, n is columns
     * Space Complexity: O(1)
     * 
     * @param matrix - m x n matrix with integers increasing left-to-right and top-to-bottom
     * @param target - Target value to search
     * @return true if target found, false otherwise
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int rows = matrix.length; int cols = matrix[0].length;
        int left = 0; int right = rows*cols -1;

        while(left <= right){
            int mid = left + (right - left) /2;
            int midValue = matrix[mid/cols][mid%cols];

            if(midValue == target) return true;
            else if(midValue < target){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return false;
    }

    /**
     * LeetCode 1011 - Capacity To Ship Packages Within D Days
     * 
     * A conveyor belt has packages. Each day, can ship at most "capacity" amount.
     * Ship packages in order. Find minimum capacity needed to ship all packages within D days.
     * 
     * Approach: Binary search on capacity. For each capacity, check if all packages can ship in D days.
     *           Minimum capacity = max(weights), Maximum capacity = sum(weights)
     * 
     * Example: weights = [1, 2, 3, 4, 5, 6], days = 3
     *          Capacity 15 ships in 1 day, capacity 10 ships in 1 day, capacity 5 ships in 3 days
     *          Return 15
     * 
     * Time Complexity: O(n * log(sum)) where n is length of weights
     * Space Complexity: O(1)
     * 
     * @param weights - Array of package weights
     * @param days - Number of days to ship all packages
     * @return Minimum ship capacity needed
     */
    public int shipWithinDays(int[] weights, int days) {
        int left = 0; int right = 0;
        for(int weight : weights){
            left = Math.max(left , weight);
            right += weight;
        }

        while(left < right){
            int mid = left + (right - left) /2;
            if(canShip(weights , mid , days)){
                right = mid;
            }else{
                left = mid +1;
            }
        }
        return left;
    }

    /**
     * Helper method for shipWithinDays
     * 
     * Checks if all packages can be shipped with given capacity within specified days
     * 
     * @param weights - Array of package weights
     * @param capacity - Ship capacity per day
     * @param days - Number of days available
     * @return true if all packages fit within days, false otherwise
     */
    private boolean canShip(int [] weights , int capacity , int days){
        int currentLoad = 0; int requiredDays = 1;
        for(int weight : weights){
            if(currentLoad + weight <= capacity){
                currentLoad += weight;
            }else{
                requiredDays++;
                currentLoad = weight;

                if(requiredDays > days){
                    return  false;
                }
            }
        }
        return requiredDays <= days;
    }

    /**
     * LeetCode 1283 - Find the Smallest Divisor Given a Threshold
     * 
     * Given an array and a threshold, find the smallest divisor such that when dividing each
     * element in the array by the divisor and summing the ceiling results, the sum is ≤ threshold.
     * 
     * Approach: Binary search on divisor value (1 to max element).
     *           For each divisor, calculate sum of ceiling divisions.
     * 
     * Example: nums = [1, 2, 5, 9], threshold = 6
     *          Divisor 1: 1+2+5+9 = 17 (too large)
     *          Divisor 2: 1+1+3+5 = 10 (too large)
     *          Divisor 3: 1+1+2+3 = 7 (too large)
     *          Divisor 5: 1+1+1+2 = 5 (≤ 6, valid)
     *          Return 5
     * 
     * Time Complexity: O(n * log(max)) where n is array length
     * Space Complexity: O(1)
     * 
     * @param nums - Array of positive integers
     * @param threshold - Maximum allowed sum of ceiling divisions
     * @return Smallest divisor that satisfies the threshold
     */
    public int smallestDivisor(int []nums , int threshold) {
        int left = 1;
        int right = 1;

        for (int num : nums) {
            right = Math.max(right, num);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            if(sumOfCeilingDivisions(nums,mid) <= threshold){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * Helper method for smallestDivisor
     * 
     * Calculates the sum of ceiling divisions (num/divisor rounded up for each num)
     * Formula for ceiling: (num + divisor - 1) / divisor
     * 
     * @param nums - Array of positive integers
     * @param divisor - Current divisor to test
     * @return Sum of ceiling divisions
     */
    private long sumOfCeilingDivisions(int[] nums , int divisor){
        long sum = 0;
        for(int num : nums){
            sum += (num + divisor -1) /divisor;
        }
        return sum;
    }

    /**
     * LeetCode 274 - H-Index
     * 
     * Given an array of citation counts where citations[i] is the citation count of researcher i.
     * H-index is the largest number h such that the researcher has at least h papers with at least h citations.
     * 
     * Approach: Binary search on h-index value (0 to n).
     *           For each mid, count papers with at least mid citations.
     *           If count >= mid, then h-index of at least mid is possible.
     * 
     * Example: citations = [25, 8, 5, 3, 3]
     *          Sorted descending: [25, 8, 5, 3, 3]
     *          Papers with ≥3 citations: 5, Papers with ≥4 citations: 4, Papers with ≥5 citations: 3
     *          H-index = 3 (at least 3 papers with 3+ citations)
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param citations - Array of citation counts
     * @return H-index of the researcher
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0; int right = n;

        while(left <= right){
            int mid = left + (right - left +1)/2;
            int minCitations = citations.length - mid;

            if(minCitations >= mid){
                left = mid;
            }else{
                right = mid-1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println("=== Binary Search Examples ===\n");
        
        // Example 1: searchInsert
        System.out.println("Example 1: Search Insert Position");
        int[] nums1 = {1, 3, 5, 6};
        System.out.println("Array: [1, 3, 5, 6], Target: 5 → Index: " + searchInsert(nums1, 5)); // Output: 2
        System.out.println("Array: [1, 3, 5, 6], Target: 7 → Index: " + searchInsert(nums1, 7)); // Output: 4
        System.out.println();
        
        // Example 2: mySqrt
        System.out.println("Example 2: Sqrt(x)");
        BinarySearch bs = new BinarySearch();
        System.out.println("Sqrt(4) = " + bs.mySqrt(4));   // Output: 2
        System.out.println("Sqrt(8) = " + bs.mySqrt(8));   // Output: 2
        System.out.println();
        
        // Example 3: nextGreatestLetter
        System.out.println("Example 3: Next Greatest Letter");
        char[] letters = {'c', 'f', 'j'};
        System.out.println("Letters: [c, f, j], Target: 'a' → " + bs.nextGreatestLetter(letters, 'a')); // Output: c
        System.out.println("Letters: [c, f, j], Target: 'j' → " + bs.nextGreatestLetter(letters, 'j')); // Output: c
        System.out.println();
        
        // Example 4: searchMatrix
        System.out.println("Example 4: Search 2D Matrix");
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println("Search 3 in matrix: " + bs.searchMatrix(matrix, 3));   // Output: true
        System.out.println("Search 13 in matrix: " + bs.searchMatrix(matrix, 13)); // Output: false
    }
}
