package Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 15 - 3Sum
 *
 * PROBLEM:
 * Given an integer array nums, return all unique triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * APPROACH: Sort + Two Pointers
 *
 * WHY THIS APPROACH?
 * 1. BRUTE FORCE (3 nested loops): O(n³) - too slow for large arrays.
 *
 * 2. HASH MAP: Fix one number, use 2Sum with hash map for the other two.
 *    - O(n²) time, O(n) space.
 *    - Tricky to avoid duplicates without extra logic.
 *
 * 3. SORT + TWO POINTERS (BEST):
 *    - Sort the array first: O(n log n).
 *    - Fix the first element (i), then use two pointers (left, right) to find
 *      pairs that sum to -nums[i] in the remaining subarray.
 *    - Time: O(n²) - outer loop O(n), two-pointer scan O(n).
 *    - Space: O(1) excluding output (or O(log n) for sort).
 *    - Duplicates are easy to skip: just move past same values.
 *
 * KEY INSIGHT:
 * After sorting, for each fixed nums[i], we need nums[left] + nums[right] = -nums[i].
 * If sum < target → move left (need larger sum).
 * If sum > target → move right (need smaller sum).
 * If sum == target → add triplet, then skip duplicates on both sides.
 */
public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;

        Arrays.sort(nums);  // Must sort first for two-pointer to work

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate first elements
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int target = -nums[i];  // We need left + right = target
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for left
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // Skip duplicates for right
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < target) {
                    left++;  // Need larger sum
                } else {
                    right--; // Need smaller sum
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println("Input: nums = " + Arrays.toString(nums));
        List<List<Integer>> result = threeSum(nums);
        System.out.println("Output: " + result);
    }
}
