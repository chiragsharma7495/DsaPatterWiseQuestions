package ArraysAndStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sliding Window Pattern Problems Collection
 * 
 * The sliding window technique is a two-pointer approach used to solve problems involving contiguous
 * subarrays/substrings. It maintains a window [left, right] and expands/contracts based on conditions.
 * 
 * Key Concepts:
 * - Fixed window: Window size is constant, slide across the array
 * - Variable window: Window size changes based on conditions, maintain invariants
 * - Track characters/frequencies in a HashMap or array
 * - Time complexity typically O(n) as each element enters and leaves window once
 * 
 * Applications:
 * - Find longest/shortest substring/subarray with specific properties
 * - Maximum sum/product in subarray of size k
 * - Permutations/anagrams in substrings
 * - Minimum window containing characters
 * 
 * Pattern:
 * 1. Initialize left pointer and data structure to track window state
 * 2. Expand right pointer to grow window
 * 3. Contract left pointer when condition violated
 * 4. Update result at each valid window state
 */
public class SlidingWindow {

    /**
     * LeetCode 3 - Longest Substring Without Repeating Characters
     * 
     * Find the length of the longest substring without repeating characters.
     * 
     * Approach: Variable sliding window with character tracking using ASCII array
     * - Expand window by moving right pointer
     * - When duplicate found, move left pointer to position after last occurrence
     * - Track last index of each character to jump efficiently
     * - Update maximum length whenever a new character is added
     * 
     * Example: s = "abcabcbb" → return 3 (substring "abc")
     *          s = "bbbbb" → return 1 (substring "b")
     *          s = "pwwkew" → return 3 (substring "wke")
     *          s = "au" → return 2 (substring "au")
     * 
     * Time Complexity: O(n) where n is length of string (each char processed once)
     * Space Complexity: O(1) - ASCII array of fixed size 128
     * 
     * @param s - Input string
     * @return Length of longest substring without repeating characters
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;

        int lastInd[] = new int[128];
        for(int i=0; i<128; i++){
            lastInd[i] = -1;
        }

        int maxLen = 0;
        int left = 0;

        for(int right = 0; right < s.length(); right++){
            char ch = s.charAt(right);
            int ascii = (int) ch;

            if(lastInd[ascii] >= left) left = lastInd[ascii]+1; // lastInd[ascii]+1; means you have encountered duplicate so you increment the left “If I’ve seen this character before in my current window, move the start of the window to one position after its previous occurrence, so the window has no duplicates.”

            lastInd[ascii] = right;

            int currLen = right - left +1;
            if(currLen > maxLen) maxLen = currLen;
        }
        return maxLen;
    }

    /**
     * LeetCode 643 - Maximum Average Subarray I
     * 
     * Find the maximum average of any contiguous subarray of size k.
     * 
     * Approach: Fixed sliding window technique
     * - Initial window: sum of first k elements
     * - Slide: remove leftmost element, add new rightmost element
     * - Track maximum sum throughout sliding process
     * - Calculate average as maxSum / k at the end
     * - Use long to prevent integer overflow
     * 
     * Example: nums = [1, 12, -5, -6, 50, 3], k = 4
     *          Window [12, -5, -6, 50] → sum = 51, avg = 51/4 = 12.75
     *          
     * Example: nums = [5], k = 1 → return 5.0
     * 
     * Time Complexity: O(n) where n is length of array
     * Space Complexity: O(1)
     * 
     * @param nums - Array of integers
     * @param k - Window size
     * @return Maximum average of any subarray of size k
     */
    public double findMaxAverage(int[] nums, int k) {
        long windowSum = 0;
        for(int i=0; i<k; i++){
            windowSum += nums[i];
        }

        long maxSum = windowSum;

        for(int i=k; i<nums.length; i++){
            windowSum += nums[i] - nums[i-k];

            if(windowSum > maxSum) maxSum = windowSum;
        }
        return (double) maxSum / k;
    }

    /**
     * LeetCode 567 - Permutation in String
     * 
     * Given two strings s1 and s2, return true if s2 contains a permutation of s1.
     * 
     * Approach: Fixed sliding window with character frequency matching
     * - Create frequency array for s1 (need)
     * - Maintain frequency array for sliding window in s2
     * - Expand window to size of s1, then slide
     * - Check if window frequencies match s1 frequencies using matches() helper
     * - Move window by one step: remove left char, add right char
     * 
     * Example: s1 = "ab", s2 = "eidbaooo" → return true (window "ba" is permutation)
     *          s1 = "ab", s2 = "eidboaoo" → return false (no permutation exists)
     * 
     * Step-by-step: s1 = "ab", s2 = "eidbaooo"
     * - need = [1, 1, 0, ..., 0] (a=1, b=1)
     * - Window [0,1]="ei": no match
     * - Window [1,2]="id": no match
     * - Window [2,3]="db": no match
     * - Window [3,4]="ba": MATCH! return true
     * 
     * Time Complexity: O(n) where n is length of s2
     * Space Complexity: O(1) - fixed arrays of size 26 for lowercase letters
     * 
     * @param s1 - String to check for permutation
     * @param s2 - String to search in
     * @return True if s2 contains a permutation of s1
     */
    public boolean checkInclusion(String s1, String s2) {
        int k = s1.length();
        int n = s2.length();
        if(k > n) return false;

        int need[] = new int[26];
        int window[] = new int[26];

        for(int i=0; i<k; i++){
            need[s1.charAt(i) - 'a']++;
        }

        for(int i=0; i<k; i++){
            window[s2.charAt(i) - 'a']++;
        }
        if(matches(need , window)) return true;

        for(int right = k; right<n; right++){
            int left = right - k;

            window[s2.charAt(left) - 'a']--;
            window[s2.charAt(right) - 'a']++;

            if(matches(need , window)) return true;
        }
        return false;
    }

    /**
     * Helper method for checkInclusion()
     * 
     * Compares two character frequency arrays to check if they match exactly.
     * Used to validate if current window contains exact permutation of target.
     * 
     * @param a - First frequency array (need)
     * @param b - Second frequency array (window)
     * @return True if both arrays have identical frequencies for all characters
     */
    public static boolean matches(int[] a , int[] b){
        for(int i=0; i<26; i++){
            if(a[i] != b[i]) return false;
        }
        return true;
    }

    /**
     * LeetCode 438 - Find All Anagrams in a String
     * 
     * Find all start indices where an anagram of string p exists in string s.
     * 
     * Approach: Fixed sliding window with character frequency matching
     * - Similar to checkInclusion but collects ALL matching positions
     * - Create frequency array for p (need)
     * - Maintain frequency array for sliding window in s
     * - When frequencies match, add the starting index to result list
     * - Slide window: remove left char, add right char
     * 
     * Example: s = "cbaebabacd", p = "abc" → return [0, 6]
     *          Position 0: "cba" is anagram of "abc"
     *          Position 6: "bac" is anagram of "abc"
     *          
     * Example: s = "abab", p = "ab" → return [0, 1, 2]
     *          Position 0: "ab"
     *          Position 1: "ba"
     *          Position 2: "ab"
     * 
     * Step-by-step: s = "cbaebabacd", p = "abc"
     * - Window [0,2]="cba": frequencies match → add 0
     * - Window [1,3]="bae": no match
     * - Window [2,4]="aeb": no match
     * - ...
     * - Window [6,8]="bac": frequencies match → add 6
     * 
     * Time Complexity: O(n) where n is length of s
     * Space Complexity: O(1) for character arrays, O(k) for result where k is number of anagrams
     * 
     * @param s - String to search in
     * @param p - String pattern to find anagrams of
     * @return List of starting indices of all anagrams
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int n = s.length();
        int k = p.length();

        if(k > n) return result;

        int need[] = new int[26];
        int window[] = new int[26];

        for(int i=0; i<k; i++){
            need[p.charAt(i) - 'a']++;
        }

        for(int i=0; i<k; i++){
            window[s.charAt(i) - 'a']++;
        }

        if(match(need , window)) result.add(0);

        for(int right = k; right<n; right++){
            int left = right-k;

            window[s.charAt(left) - 'a']--;
            window[s.charAt(right) - 'a']++;

            if(match(need , window)) result.add(left+1);
        }
        return result;
    }

    /**
     * Helper method for findAnagrams()
     * 
     * Compares two character frequency arrays to check if they match exactly.
     * Used to validate if current window contains exact anagram of pattern.
     * 
     * @param a - First frequency array (pattern)
     * @param b - Second frequency array (current window)
     * @return True if both arrays have identical frequencies for all lowercase letters
     */
    private static boolean match(int a[] , int b[]){
        for(int i=0; i<26; i++){
            if(a[i] != b[i])return false;
        }
        return true;
    }

    /**
     * LeetCode 76 - Minimum Window Substring
     * 
     * Find the minimum window substring that contains all characters in t.
     * Return empty string if no such window exists.
     * 
     * Approach: Variable sliding window with character frequency tracking
     * - Use HashMap to track required characters (need) and window characters (window)
     * - Expand right pointer to grow window
     * - Track "have" count: number of unique character types with correct frequency
     * - When have == needTypes, all required characters are in window
     * - Contract left pointer to shrink window and find minimum length
     * - Update minimum window at each valid state
     * 
     * Example: s = "ADOBECODEBANC", t = "ABC" → return "BANC"
     *          Minimum window at [9, 12] contains A, B, C with length 4
     *          
     * Example: s = "a", t = "aa" → return "" (not enough characters)
     * 
     * Step-by-step: s = "ADOBECODEBANC", t = "ABC"
     * - need = {A:1, B:1, C:1}, needTypes = 3
     * - Expand until have == 3, first valid window at [3, 5]="BCE" (length 3)
     * - Continue expanding and contracting to find minimum
     * - Final answer: [9, 12]="BANC" (length 4)
     * 
     * Time Complexity: O(n) where n is length of s (each char visited at most twice)
     * Space Complexity: O(m) where m is size of character set in t
     * 
     * @param s - String to search in
     * @param t - String containing required characters
     * @return Minimum window substring or empty string if not found
     */
    public String minWindow(String s, String t) {
        if(s == null || t.length() > s.length()) return "";

        Map<Character , Integer> need = new HashMap<>();
            for(char c : t.toCharArray()){
                need.put(c,need.getOrDefault(c,0)+1);
            }

            Map<Character , Integer> window = new HashMap<>();
            int have = 0;
            int needTypes = need.size();

            int left = 0;
            int minLen = Integer.MAX_VALUE;
            int minStart = 0;

            for(int right=0; right<s.length(); right++){
                char c = s.charAt(right);

                if(need.containsKey(c)){
                    window.put(c,window.getOrDefault(c,0)+1);
                    if(window.get(c).intValue() == need.get(c).intValue()) have++;
                }

                while(have == needTypes){
                    int windowLen = right - left +1;
                    if(windowLen < minLen){
                        minLen = windowLen;
                        minStart = left;
                    }
                    char leftchar = s.charAt(left);

                    if(need.containsKey(leftchar)){
                        if(window.get(leftchar).intValue() == need.get(leftchar).intValue()) have--;
                        window.put(leftchar , window.get(leftchar)-1);
                    }
                    left++;
                }
            }
            if(minLen == Integer.MAX_VALUE) return "";
            return s.substring(minStart , minStart + minLen);
    }

    /**
     * LeetCode 904 - Fruit Into Baskets
     * 
     * Given array of fruit types in order, pick fruits consecutively.
     * Can hold at most 2 different fruit types at a time.
     * Find the maximum number of fruits you can pick.
     * 
     * Approach: Variable sliding window with HashMap for fruit counting
     * - Expand window by moving right pointer, adding fruits
     * - When more than 2 fruit types in window, contract from left
     * - Remove left fruit and decrease its count; if count becomes 0, remove from map
     * - Track maximum window size throughout the process
     * - Window size represents consecutive fruits with at most 2 types
     * 
     * Example: fruits = [1, 2, 1] → return 3 (all fruits: type 1 and type 2)
     *          
     * Example: fruits = [0, 1, 2, 2] → return 3
     *          Can pick [1, 2, 2] starting from index 1 (types 1 and 2)
     *          
     * Example: fruits = [1, 2, 3, 2, 2] → return 4
     *          Can pick [2, 3, 2, 2] (types 2 and 3)
     * 
     * Time Complexity: O(n) where n is number of fruits
     * Space Complexity: O(1) - at most 2 fruit types in map
     * 
     * @param fruits - Array of fruit types
     * @return Maximum number of consecutive fruits with at most 2 different types
     */
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        if(n == 0) return 0;

        Map<Integer , Integer> count = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for(int right = 0; right < n; right++){
            int fruit = fruits[right];
            count.put(fruit, count.getOrDefault(fruit,0)+1);

            while(count.size() > 2){
                int leftFruit = fruits[left];
                count.put(leftFruit , count.getOrDefault(leftFruit , 0) -1);
                if(count.get(leftFruit) == 0){
                    count.remove(leftFruit);
                }
                left++;
            }

            int windowSize = right - left+1;
            if(windowSize > maxLen) maxLen = windowSize;
        }

        return maxLen;
    }

    /**
     * LeetCode 1052 - Grumpy Bookstore Owner
     * 
     * Given arrays of customers count and grumpy state for each minute,
     * find the maximum satisfied customers using a technique for X consecutive minutes.
     * 
     * Approach: Sliding window to optimize satisfaction gain
     * - Calculate base satisfied customers (when not grumpy)
     * - Find the window of size 'minutes' with maximum additional satisfaction
     * - Use sliding window to try all positions and find best satisfaction gain
     * - Return base + maxExtra (maximum possible satisfied customers)
     * 
     * Example: customers = [1, 0, 1, 2, 1, 1, 7, 5], grumpy = [0, 1, 0, 1, 0, 1, 0, 1], minutes = 3
     *          Base = 1 + 1 + 1 + 7 = 10 (satisfied when not grumpy)
     *          Window [3, 5] gains: 2 + 1 = 3 extra (making grumpy customers satisfied)
     *          Result = 10 + 3 = 13
     *          
     * Example: customers = [1], grumpy = [0], minutes = 1 → return 1
     * 
     * Time Complexity: O(n) where n is number of minutes
     * Space Complexity: O(1)
     * 
     * @param customers - Array of customer counts at each minute
     * @param grumpy - Array where 1 means owner is grumpy, 0 means satisfied
     * @param minutes - Consecutive minutes owner can use technique
     * @return Maximum satisfied customers achievable
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int base = 0;

        for(int i=0; i<n; i++) {
            if (grumpy[i] == 0) {
                base += customers[i];
            }
        }

            int currExtra = 0;
            for(int i=0; i<minutes && i<n; i++){
                if(grumpy[i] == 1){
                    currExtra += customers[i];
                }
            }

            int maxExtra = currExtra;

            for(int right = minutes; right < n; right++){
                int left = right - minutes;

                if(grumpy[left] == 1){
                    currExtra -= customers[left];
                }

                if(grumpy[right] == 1){
                    currExtra += customers[right];
                }

                if(currExtra > maxExtra) maxExtra = currExtra;
            }

            return  base + maxExtra;
    }

    /**
     * LeetCode 1423 - Maximum Points You Can Obtain from Cards
     * 
     * Given array of card points, you can take k cards from either the beginning
     * or end of the array. Find the maximum score (sum) you can obtain.
     * 
     * Approach: Transform to minimum middle window problem using sliding window
     * - If taking k cards from ends, we leave (n - k) cards in middle
     * - Instead of maximizing ends, minimize the middle window
     * - Use sliding window to find minimum sum of (n - k) consecutive cards
     * - Return total sum - minimum middle sum
     * 
     * Example: cardPoints = [1, 2, 3, 4, 5, 6, 1], k = 3 → return 12
     *          Can take [1, 6, 5] from ends or [6, 5, 1] → sum = 12
     *          Middle window [2, 3, 4, 1] has min sum = 10
     *          Total = 22, Result = 22 - 10 = 12
     *          
     * Example: cardPoints = [2, 2, 2], k = 2 → return 4
     *          Take 2 cards from either end: [2, 2] = 4
     * 
     * Time Complexity: O(n) where n is number of cards
     * Space Complexity: O(1)
     * 
     * @param cardPoints - Array of point values for each card
     * @param k - Number of cards to take from beginning or end
     * @return Maximum score (sum) achievable by taking k cards from ends
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        if(k >= n){
            int total = 0;
            for(int x : cardPoints)  total += x;
            return total;
        }

        int totalSum = 0;
        for(int x : cardPoints) totalSum += x;

        int windowSize = n-k;
        int windowSum = 0;

        for(int i=0; i<windowSize; i++){
            windowSum += cardPoints[i];
        }

        int middleSum = windowSum;

        for(int right = windowSize; right <n; right++){
            int left = right - windowSize;
            windowSum -= cardPoints[left];
            windowSum += cardPoints[right];
            if(windowSum < middleSum) middleSum = windowSum;
        }

        return totalSum - middleSum;
    }

    /**
     * LeetCode 1493 - Longest Subarray of 1's After Deleting One Element
     * 
     * Given a binary array, delete one element and return the length of the
     * longest contiguous subarray of 1's that remains.
     * 
     * Approach: Variable sliding window allowing exactly one 0
     * - Maintain window with at most 1 zero
     * - Expand window by moving right pointer
     * - When more than 1 zero, contract from left until only 1 zero remains
     * - Track maximum window size
     * - Subtract 1 from result (must delete one element, even if it's the max window)
     * 
     * Example: nums = [1, 1, 0, 1] → return 3
     *          Delete 0 at index 2 → [1, 1, 1] of length 3
     *          
     * Example: nums = [0, 1, 1, 1, 0, 1, 1, 0, 1] → return 5
     *          Delete any 0 → longest is [1, 1, 1, 0, 1, 1] minus one 0 = 5
     *          
     * Example: nums = [1, 1, 1] → return 2
     *          Must delete one element → [1, 1] of length 2
     * 
     * Time Complexity: O(n) where n is length of array
     * Space Complexity: O(1)
     * 
     * @param nums - Binary array containing 0s and 1s
     * @return Length of longest subarray of 1's after deleting one element
     */
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int zeroesCount = 0;
        int left = 0;
        int maxLen = 0;

        for(int i=0; i<n; i++){
            if(nums[i] == 0) zeroesCount++;

            while(zeroesCount > 1){
                if(nums[left] == 0){
                    zeroesCount--;
                }
                left++;
            }

            int windowLen = i - left + 1;
            if(windowLen > maxLen) maxLen = windowLen;
        }
        return maxLen-1;
    }

    /**
     * LeetCode 209 - Minimum Size Subarray Sum
     * 
     * Given array of positive integers and a target sum, find the minimum length
     * of a subarray whose sum is greater than or equal to target.
     * 
     * Approach: Variable sliding window shrinking from left
     * - Expand window by moving right pointer, accumulating sum
     * - When sum >= target, contract from left to find minimum length
     * - Check if current window is smaller than minimum found
     * - Move left pointer by removing elements from window
     * - Continue until right reaches end
     * 
     * Example: target = 7, nums = [2, 3, 1, 2, 4, 3] → return 2
     *          Subarray [4, 3] has sum = 7, length = 2
     *          This is the minimum length
     *          
     * Example: target = 4, nums = [1, 4, 4] → return 1
     *          Subarray [4] has sum = 4, length = 1
     *          
     * Example: target = 11, nums = [1, 1, 1, 1, 1, 1, 1, 1] → return 0
     *          No subarray sum >= 11
     * 
     * Time Complexity: O(n) where n is length of array (each element visited twice max)
     * Space Complexity: O(1)
     * 
     * @param target - Target sum to achieve or exceed
     * @param nums - Array of positive integers
     * @return Minimum length of subarray with sum >= target, or 0 if not possible
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int windowSum = 0;
        int minLen = Integer.MAX_VALUE;

        for(int right = 0; right < nums.length; right++){
            windowSum += nums[right];

            while(windowSum >= target){
                minLen = Math.min(minLen , right-left+1);

                windowSum -= nums[left];
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
    }
}
