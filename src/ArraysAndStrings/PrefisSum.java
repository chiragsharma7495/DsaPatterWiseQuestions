package ArraysAndStrings;

import java.util.HashMap;
import java.util.Map;

public class PrefisSum {

    /**
     * Problem 1: Running Sum of 1D Array
     * 
     * Problem: Given an array nums, return the running sum of the array.
     * Running sum[i] = sum(nums[0]...nums[i])
     * 
     * Example:
     * Input: nums = [1,2,3,4]
     * Output: [1,3,6,10]
     * Explanation: Running sum is [1, 1+2, 1+2+3, 1+2+3+4]
     * 
     * Time: O(n), Space: O(1)
     */
    public int[] runningSum(int[] nums) {
        for(int i=1; i<nums.length; i++){
            nums[i] += nums[i-1];
        }
        return nums;
    }
    /**
     * Problem 2: Range Sum Query - Immutable (NumArray + sumRange)
     * 
     * Problem: Given an array nums, design an object that supports queries to find
     * the sum of elements between indices left and right (inclusive).
     * 
     * Example:
     * NumArray([-2, 0, 3, -5, 2, -1])
     * sumRange(0, 2) -> 1 ((-2) + 0 + 3 = 1)
     * sumRange(2, 5) -> -1 (3 + (-5) + 2 + (-1) = -1)
     * 
     * Time: Constructor O(n), sumRange O(1), Space: O(n)
     */
    private int [] prefix;
    public void NumArray(int[] nums) {
        prefix = new int[nums.length+1];
        for(int i=0; i<nums.length; i++){
            prefix[i+1] = prefix[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefix[right+1] - prefix[left];
    }
    /**
     * Problem 3: Find the Middle Index In Array (Pivot Index)
     * 
     * Problem: Given an array nums, find the pivot index where the sum of left elements
     * equals the sum of right elements.
     * 
     * Example:
     * Input: nums = [1,7,3,6,5,6]
     * Output: 3
     * Explanation: At index 3, left sum = 1+7+3 = 11, right sum = 5+6 = 11
     * 
     * Time: O(n), Space: O(1)
     */
    public int pivotIndex(int[] nums) {
        int totalSum = 0;
        for(int num : nums){
            totalSum += num;
        }

        int left = 0;
        for(int i=0; i<nums.length; i++){
            int rightSum = totalSum - left - nums[i];
            if(left == rightSum) return i;
            left += nums[i];
        }
        return -1;
    }
    /**
     * Problem 4: Minimum Value to Get Positive Step by Step Sum
     * 
     * Problem: Given an array nums, find the minimum positive integer startValue such that
     * when we start from startValue and subtract each element, the running sum is always positive.
     * 
     * Example:
     * Input: nums = [-3,2,-3,4,2]
     * Output: 5
     * Explanation: With startValue = 5: 5-3=2, 2-2=0 (not valid), but with 5: 5→2→0→-3 (invalid)
     * Need startValue = 5 so minimum = 5-(-3) = 5
     * 
     * Time: O(n), Space: O(1)
     */
    public int minStartValue(int[] nums) {
        int prefix = 0;
        int minPrefix = 0;
        for(int x : nums){
            prefix += x;
            if(prefix < minPrefix) minPrefix = prefix;
        }
        return 1 - minPrefix;
    }
    /**
     * Problem 5: Subarray Sum Equals K
     * 
     * Problem: Given an array nums and an integer k, return the number of subarrays that sum to k.
     * A subarray is a contiguous part of the array.
     * 
     * Example:
     * Input: nums = [1,1,1], k = 2
     * Output: 2
     * Explanation: Subarrays [1,1] at indices (0,1) and (1,2) both sum to 2
     * 
     * Approach: Use prefix sum + HashMap. For each element, check if (currentSum - k) exists.
     * Time: O(n), Space: O(n)
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer , Integer> count = new HashMap<>();
        count.put(0, 1);
        int sum = 0; int ans = 0;
        for(int x : nums){
            sum += x;
            if(count.containsKey(sum-k)) ans += count.get(sum-k);
            count.put(sum, count.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
    /**
     * Problem 6: Subarrays Divisible by K
     * 
     * Problem: Given an array nums and an integer k, return the number of subarrays
     * whose sum is divisible by k.
     * 
     * Example:
     * Input: nums = [4,5,0,-2,-3,1], k = 5
     * Output: 7
     * Explanation: Subarrays with sum divisible by 5: [4,5,0,-2,-3,1], [5], [5,0], [5,0,-2,-3], [0], [0,-2,-3], [-2,-3]
     * 
     * Approach: Use modulo arithmetic. Track prefix sum modulo k in HashMap.
     * Time: O(n), Space: O(k)
     */
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer , Integer> map = new HashMap<>();
        map.put(0, 1);

        int prefix = 0; int ans = 0;
        for(int x : nums){
            prefix += x;

            int mod = prefix % k;
            if(mod < 0) mod += k;

            if(map.containsKey(mod)) ans += map.get(mod);
            map.put(mod , map.getOrDefault(mod, 0) + 1);
        }
        return ans;
    }
    /**
     * Problem 7: Maximum Length of Contiguous Subarray With Equal Number of 0s and 1s
     * 
     * Problem: Given an array nums containing 0s and 1s, find the longest contiguous subarray
     * with an equal number of 0s and 1s.
     * 
     * Example:
     * Input: nums = [0,1,0]
     * Output: 2
     * Explanation: The subarray [0,1] (or [1,0]) has equal number of 0s and 1s
     * 
     * Approach: Convert 0 to -1, find longest subarray with sum 0 using HashMap.
     * Time: O(n), Space: O(n)
     */
    public int findMaxLength(int[] nums) {
        Map<Integer , Integer> count = new HashMap<>();
        count.put(0, -1);int sum = 0; int ans = 0;
        for(int i=0; i<nums.length; i++){
            sum += nums[i] == 0 ? -1 : 1;
            if(count.containsKey(sum)) ans = Math.max(ans , i - count.get(sum));
            else count.put(sum, i);
        }
        return ans;
    }
    /**
     * Problem 8: Product of Array Except Self
     * 
     * Problem: Given an array nums, return an array answer where answer[i] is the product
     * of all elements except nums[i]. You must do this without division and in O(n) time.
     * 
     * Example:
     * Input: nums = [1,2,3,4]
     * Output: [24,12,8,6]
     * Explanation: answer[0] = 2*3*4=24, answer[1] = 1*3*4=12, answer[2] = 1*2*4=8, answer[3] = 1*2*3=6
     * 
     * Approach: Use prefix and suffix products. First pass: prefix products. Second pass: multiply with suffix.
     * Time: O(n), Space: O(1) excluding output array
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int ans[] = new int[n];
        ans[0] = 1;

        for(int i=1; i<n; i++){
            ans[i] = ans[i-1] * nums[i-1];
        }

        int suffix = 1;
        for(int i= n-1; i >= 0; i--){
            ans[i] *= suffix;
            suffix *= nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
