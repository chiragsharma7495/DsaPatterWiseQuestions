package ArraysAndStrings;

import java.util.*;
import java.util.Collections;

/**
 * Hashing algorithms and data structure problems.
 * 
 * This class contains various solutions to classic hashing and hash map problems,
 * including Two Sum, anagram detection, frequency counting, and pattern matching.
 * Solutions utilize HashMap, HashSet, and bucket sort algorithms to efficiently
 * solve problems in linear/near-linear time complexity.
 */
public class Hashing {

    /**
     * Helper method to print a two-element integer array result.
     * 
     * Prints the result of a two-sum query in the format [x, y].
     * Handles null and empty array cases gracefully.
     * 
     * @param res the result array (typically containing two indices)
     * 
     * Example:
     * <pre>
     * printResult(new int[]{0, 1});  // Output: [0, 1]
     * printResult(null);             // Output: null
     * printResult(new int[]{});      // Output: []
     * </pre>
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static void printResult(int[] res) {
        if (res == null) {
            System.out.println("null");
            return;
        }
        if (res.length == 0) {
            System.out.println("[]");
            return;
        }
        System.out.println("[" + res[0] + ", " + res[1] + "]");
    }

    /**
     * Two Sum.
     * LeetCode 1: https://leetcode.com/problems/two-sum/
     * 
     * Approach:
     * 1. Iterate through array once
     * 2. For each element, calculate complement = target - current_element
     * 3. Check if complement exists in HashMap (previous elements)
     * 4. If found, return indices [index_of_complement, current_index]
     * 5. Otherwise, add current element to HashMap for future lookups
     * 
     * Key insight: Using HashMap enables O(1) lookup for complements, achieving
     * linear time complexity instead of O(n²) with nested loops.
     * 
     * @param arr the input array
     * @param target the target sum
     * @return array of two indices [i, j] where arr[i] + arr[j] = target, 
     *         or empty array if no such pair exists
     * 
     * Example:
     * <pre>
     * Input: arr = [2, 7, 11, 15], target = 9
     * Iteration: 2 (need 7) → not found
     *           7 (need 2) → found! at index 0
     * Output: [0, 1]
     * </pre>
     * 
     * Time Complexity: O(n) - single pass through array
     * Space Complexity: O(n) - HashMap stores up to n elements
     */
    public static int[] TwoSum(int arr[], int target){
        HashMap<Integer , Integer> seen = new HashMap<>();
        for(int i=0; i<arr.length; i++){
            int need = target - arr[i];
            if(seen.containsKey(need)){
                return new int[]{seen.get(need),i};
            }
            seen.put(arr[i], i);
        }
        return new int[0];
    }

    /**
     * Contains Duplicate check.
     * LeetCode 217: https://leetcode.com/problems/contains-duplicate/
     * 
     * Approach:
     * 1. Iterate through the array
     * 2. For each element, attempt to add it to a HashSet using set.add()
     * 3. The add() method returns false if element already exists in set
     * 4. If add() returns false, duplicate found → return true
     * 5. If loop completes without duplicates → return false
     * 
     * Key insight: HashSet.add() provides both insertion and duplicate detection
     * in a single O(1) operation, making this more elegant than explicit contains().
     * 
     * @param nums the integer array
     * @return true if array contains any duplicate, false otherwise
     * 
     * Example:
     * <pre>
     * Input: nums = [1, 2, 3, 1]
     * Output: true (1 appears twice)
     * 
     * Input: nums = [1, 2, 3, 4]
     * Output: false (no duplicates)
     * </pre>
     * 
     * Time Complexity: O(n) - single pass through array
     * Space Complexity: O(n) - HashSet stores up to n elements
     */
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> seen = new HashSet <>();
        for(int num : nums){
            if(!seen.add(num)) return true;
        }
        return false;
    }

    /**
     * Valid Anagram check.
     * LeetCode 242: https://leetcode.com/problems/valid-anagram/
     * 
     * Approach:
     * 1. Check if strings have equal length (anagrams must have same length)
     * 2. Count character frequencies in first string using HashMap
     * 3. For each character in second string:
     *    - If character not in map, return false (extra character)
     *    - Decrement frequency count
     *    - If count reaches 0, remove from map (cleanup)
     * 4. If map is empty at end, all characters matched perfectly → true
     * 
     * Key insight: Two-phase approach (count then verify) ensures both
     * character set and frequencies match exactly.
     * 
     * @param s the first string
     * @param t the second string
     * @return true if t is an anagram of s, false otherwise
     * 
     * Example:
     * <pre>
     * Input: s = "anagram", t = "nagraam"
     * Count s: a→3, n→1, g→1, r→1, m→1
     * Verify t: each char exists and count reaches 0
     * Output: true
     * 
     * Input: s = "rat", t = "car"
     * Count s: r→1, a→1, t→1
     * Verify t: 'c' not found in map
     * Output: false
     * </pre>
     * 
     * Time Complexity: O(n) - iterate both strings once
     * Space Complexity: O(1) - HashMap size ≤ 26 (English alphabet)
     */
    public static boolean isAnagram(String s, String t) {

        if(s.length() != t.length()) return false;
        HashMap<Character , Integer> freq = new HashMap<>();

        //count characters
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            freq.put(c , freq.getOrDefault(c , 0) +1);
        }

        //subtract form t
        for(int i=0; i<t.length(); i++){
            char c = t.charAt(i);
             if(!freq.containsKey(c)) return false;
             freq.put(c, freq.get(c) -1);
             if(freq.get(c) == 0) freq.remove(c);
        }

        return freq.isEmpty();
    }

    /**
     * Helper method to find the maximum value in a float array.
     * 
     * Used as a utility for the BucketSort algorithm to determine the range
     * of values in the array for bucket sizing.
     * 
     * @param arr the float array to search
     * @return the maximum value in the array
     * 
     * Example:
     * <pre>
     * Input: [0.3f, 0.1f, 0.9f, 0.5f]
     * Output: 0.9f
     * </pre>
     * 
     * Time Complexity: O(n) - linear scan of all elements
     * Space Complexity: O(1)
     */
    public static Float FindMax(float [] arr){
        Float max = Float.MIN_VALUE;
        for(float nums : arr){
            max = Math.max(max , nums);
        }
        return max;
    }

    /**
     * Bucket Sort algorithm for sorting floating-point numbers.
     * 
     * LeetCode reference: Related to sorting problems
     * 
     * Approach:
     * 1. Find the maximum element to determine bucket range
     * 2. Create buckets (size = max_element * array_length)
     * 3. Distribute elements into buckets using formula: index = length * element
     * 4. Sort individual buckets using Collections.sort()
     * 5. Concatenate sorted buckets to get final result
     * 
     * @param arr the float array to sort (assumes values in range [0, 1))
     * @return a sorted ArrayList of float values
     * 
     * Example:
     * <pre>
     * Input: [0.9f, 0.3f, 0.2f, 0.5f]
     * Output: [0.2f, 0.3f, 0.5f, 0.9f]
     * </pre>
     * 
     * Time Complexity: O(n + k) where n is array length and k is number of buckets
     * Space Complexity: O(n + k)
     */
    public static ArrayList<Float> BucketSort(float [] arr){
        if(arr.length == 0) return new ArrayList<>();

        //find the bucket size
        float maxElement = FindMax(arr);
        int maxIndex = (int)(maxElement*arr.length);
        int bucketSize = maxIndex+1;

        // initialize bucket list
        ArrayList<Float> bucket[] = new ArrayList[bucketSize];
        for(int i=0; i<bucketSize; i++){
            bucket[i] = new ArrayList<>();
        }

        //insert elemnts in bucket
        for(int i=0; i<arr.length; i++){
            int index = (int)(arr.length*arr[i]);
            bucket[index].add(arr[i]);
        }

//        sort the individual elements
        for(int i=0; i<bucketSize; i++){
            Collections.sort(bucket[i]);
        }
        ArrayList<Float> res = new ArrayList<>();
        for(int i=0; i<bucketSize; i++){
            for(Float elements : bucket[i]) {
                res.add(elements);
            }
        }
        return res;
    }

    /**
     * Top K Frequent Elements using Bucket Sort approach.
     * LeetCode 347: https://leetcode.com/problems/top-k-frequent-elements/
     * 
     * Approach:
     * 1. Count frequency of each element using HashMap
     * 2. Create buckets indexed by frequency (index = frequency count)
     * 3. Place elements into buckets by their frequency
     * 4. Iterate buckets from highest to lowest frequency
     * 5. Collect k elements starting from highest frequency bucket
     * 
     * Rationale: By-frequency bucket sort avoids heap operations and achieves
     * linear time complexity. Frequency can only be from 1 to n.
     * 
     * @param nums the integer array
     * @param k the number of most frequent elements to return
     * @return array of k most frequent elements in any order
     * 
     * Example:
     * <pre>
     * Input: nums = [1, 1, 1, 2, 2, 3], k = 2
     * Frequencies: 1→3, 2→2, 3→1
     * Output: [1, 2]  (or [2, 1])
     * </pre>
     * 
     * Time Complexity: O(n) - counting + bucketizing + extraction
     * Space Complexity: O(n) - HashMap and buckets
     */
    public int[] topKFrequent(int[] nums, int k) {      // very important for SyntaxWise
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new List[nums.length+1];
        for(Map.Entry<Integer , Integer> entery : freq.entrySet()){
            int count = entery.getValue();
            if(buckets[count] == null) buckets[count] = new ArrayList<>();
            buckets[count].add(entery.getKey());
        }
        int[] result = new int[k];
        int idx = 0;
        for(int i= buckets.length-1; i>=0 && idx < k; i--){
            if(buckets[i] == null) continue;
            for(int val : buckets[i]){
                result[idx++] = val;
                if(idx == k) break;
            }
        }
        return result;
    }

    /**
     * Top K Frequent Elements using Min-Heap approach.
     * LeetCode 347: https://leetcode.com/problems/top-k-frequent-elements/
     * 
     * Approach:
     * 1. Count frequency of each element using HashMap
     * 2. Use a PriorityQueue (min-heap) of size k
     * 3. For each unique element with its frequency:
     *    - Add [element, frequency] to heap
     *    - If heap size exceeds k, remove element with smallest frequency
     * 4. Extract all elements from heap as result
     * 
     * Key insight: Min-heap keeps the k most frequent elements. Once we have k
     * elements, any new element with lower frequency is rejected.
     * 
     * @param nums the integer array
     * @param k the number of most frequent elements to return
     * @return array of k most frequent elements in any order
     * 
     * Example:
     * <pre>
     * Input: nums = [1, 1, 1, 2, 2, 3], k = 2
     * Heap operations track: [1,3] and [2,2] as top k
     * Output: [1, 2]  (or [2, 1])
     * </pre>
     * 
     * Time Complexity: O(n + m*log(k)) where n = array length, m = unique elements
     * Space Complexity: O(m + k) - HashMap + heap of size k
     */
    // Top K Frequent — Heap approach: min-heap of size k by frequency
    public int[] topKFrequentHeap(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        // Min-heap: compare by frequency (a[1], b[1]). Smallest freq at top.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int num = e.getKey();
            int count = e.getValue();
            minHeap.add(new int[]{num, count});
            if (minHeap.size() > k) {
                minHeap.poll(); // remove the entry with smallest frequency
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll()[0];
        }
        return result;
    }

    /**
     * Longest Consecutive Sequence.
     * LeetCode 128: https://leetcode.com/problems/longest-consecutive-sequence/
     * 
     * Approach:
     * 1. Insert all elements into a HashSet for O(1) lookup
     * 2. For each element, check if it's the start of a sequence (num-1 not in set)
     * 3. If it's a start, extend the sequence: count consecutive elements
     * 4. Track the maximum streak length
     * 
     * Key insight: By skipping non-start elements, we avoid redundant work.
     * Each element is processed at most once, achieving linear time.
     * 
     * @param nums the integer array (can contain duplicates and be unordered)
     * @return the length of the longest consecutive sequence
     * 
     * Example:
     * <pre>
     * Input: nums = [100, 4, 200, 1, 3, 2]
     * Sequences: 1→2→3→4 (length 4), 100 (length 1), 200 (length 1)
     * Output: 4
     * </pre>
     * 
     * Time Complexity: O(n) - each element visited at most twice
     * Space Complexity: O(n) - HashSet storage
     */
    /* Examples
1.
nums = [100, 4, 200, 1, 3, 2]
Possible consecutive sequence: 1, 2, 3, 4 → length = 4
Answer: 4*/

    public int longestConsecutive(int[] nums) {
        if(nums.length == 0 || nums == null) return 0;

        Set<Integer>  set = new HashSet<>();
        for(int num : nums) set.add(num);

        int longest = 0;
        for(int num : set){
            if(!set.contains(num-1)){
                int current = num;
                int streak = 1;

                while(set.contains(current + 1)){
                    current++;
                    streak++;
                }
                longest = Math.max(longest , streak);
            }
        }
        return longest;
    }

    /**
     * Isomorphic Strings check.
     * LeetCode 205: https://leetcode.com/problems/isomorphic-strings/
     * 
     * Approach:
     * 1. Maintain two HashMaps: s→t and t→s mappings
     * 2. For each character pair (s[i], t[i]):
     *    - Check if s[i] already has a mapping, must be consistent
     *    - Check if t[i] already has a reverse mapping, must be consistent
     *    - If both pass, establish bidirectional mapping
     * 3. If any inconsistency found, return false
     * 4. If all pairs consistent, return true
     * 
     * Key insight: Using bidirectional maps prevents two different characters
     * in s from mapping to the same character in t.
     * 
     * @param s the source string
     * @param t the target string
     * @return true if strings are isomorphic, false otherwise
     * 
     * Example:
     * <pre>
     * Input: s = "paper", t = "title"
     * Mapping: p→t, a→i, p→t, e→l, r→e (all consistent both ways)
     * Output: true
     * 
     * Input: s = "badc", t = "baba"
     * Mapping: b→b, a→a, d→b (conflict! b←b but also b←d)
     * Output: false
     * </pre>
     * 
     * Time Complexity: O(n) - single pass through both strings
     * Space Complexity: O(1) - at most 26 English letters + limited charset
     */
    /* Examples
•
s = "egg", t = "add" → true
◦
e -> a, g -> d → mapping is consistent and one-to-one.
•
s = "foo", t = "bar" → false
◦
f -> b, o -> a and o -> r (conflict).
•
s = "paper", t = "title" → true
◦
p->t, a->i, p->t, e->l, r->e (consistent in both directions).
•
s = "badc", t = "baba" → false
◦
b->b, a->a, d->b (but b already mapped from b), so two different chars map to same t char.*/


    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) return false;

        Map<Character , Character> sToT = new HashMap<>();
        Map<Character , Character> tToS = new HashMap<>();

        for(int i=0; i<s.length(); i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if(sToT.containsKey(c1) && sToT.get(c1) != c2) return false;
            if(tToS.containsKey(c2) && tToS.get(c2) != c1) return false;

            sToT.put(c1,c2);
            tToS.put(c2,c1);
        }
            return true;
    }

    /**
     * Word Pattern matching.
     * LeetCode 290: https://leetcode.com/problems/word-pattern/
     * 
     * Approach:
     * 1. Split the string into words
     * 2. Verify pattern length matches number of words
     * 3. Maintain two HashMaps: pattern_char→word and word→pattern_char
     * 4. For each pattern[i]→word mapping:
     *    - Check if pattern[i] already maps to a different word → return false
     *    - Check if word is already mapped from a different char → return false
     *    - Otherwise, establish bidirectional mapping
     * 5. Return true if all mappings consistent
     * 
     * Key insight: Similar to isomorphic strings but for words instead of chars.
     * Bidirectional mapping ensures one-to-one correspondence.
     * 
     * @param pattern the pattern string (single characters)
     * @param s the string to match against pattern (space-separated words)
     * @return true if pattern matches word sequence, false otherwise
     * 
     * Example:
     * <pre>
     * Input: pattern = "abba", s = "dog cat cat dog"
     * Mapping: a→dog, b→cat, b→cat, a→dog (all consistent)
     * Output: true
     * 
     * Input: pattern = "abba", s = "dog cat cat fish"
     * Mapping: a→dog, b→cat, b→cat, a→fish (conflict! a→dog but also a→fish)
     * Output: false
     * </pre>
     * 
     * Time Complexity: O(n + m) where n = pattern length, m = total chars in s
     * Space Complexity: O(k) where k = number of unique words
     */
    /* Examples
•
pattern = "abba", s = "dog cat cat dog" → true
◦
a -> dog, b -> cat, mapping is consistent both ways.
•
pattern = "abba", s = "dog cat cat fish" → false
◦
a -> dog, b -> cat, but last word is fish, not dog.
•
pattern = "aaaa", s = "dog cat cat dog" → false
◦
a would need to map to both dog and cat.
•
pattern = "abba", s = "dog dog dog dog" → false
◦
a -> dog, but then b also tries to map to dog (two different pattern chars to same word).*/

    public boolean wordPattern(String pattern, String s) {
        String [] words = s.split(" ");
        if(pattern.length() != words.length)return false;

        Map<Character , String> ptoW = new HashMap<>();
        Map<String , Character> wtoP = new HashMap<>();

        for(int i=0; i<pattern.length(); i++){
             char c = pattern.charAt(i);
             String w = words[i];

             if(ptoW.containsKey(c) && !ptoW.get(c).equals(w))return false;
             if(wtoP.containsKey(w) && !wtoP.get(w).equals(c) )return false;

             ptoW.put(c,w);
             wtoP.put(w,c);
        }
            return true;
    }

    /**
     * Subarray Sum Equals K.
     * LeetCode 560: https://leetcode.com/problems/subarray-sum-equals-k/
     * 
     * Approach:
     * 1. Use cumulative sum with HashMap to track prefix sums
     * 2. Initialize map with {0: 1} to handle subarrays starting from index 0
     * 3. For each element, compute running sum
     * 4. Check if (currentSum - k) exists in map:
     *    - If yes, all subarrays ending at current index with sum k are found
     *    - Increment answer by count of such prefixes
     * 5. Add current sum to map for future lookups
     * 
     * Key insight: If sum[j] - sum[i] = k, then subarray [i+1, j] has sum k.
     * We search for previous prefix with value (current_sum - k).
     * 
     * @param nums the integer array (can contain negative numbers)
     * @param k the target sum
     * @return the count of subarrays with sum equal to k
     * 
     * Example:
     * <pre>
     * Input: nums = [1, 1, 1], k = 2
     * Subarrays with sum 2: [1,1] at (0,1), [1,1] at (1,2)
     * Output: 2
     * </pre>
     * 
     * Time Complexity: O(n) - single pass with HashMap operations
     * Space Complexity: O(n) - HashMap to store prefix sums
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer , Integer> count = new HashMap<>();
        count.put(0,1);

        int ans = 0; int sum = 0;
        for(int num : nums){
            sum += num;
            ans += count.getOrDefault(sum-k, 0);
            count.put(sum , count.getOrDefault(sum , 0) +1);
        }
        return ans;
    }

    /**
     * Find All Anagrams in a String.
     * LeetCode 438: https://leetcode.com/problems/find-all-anagrams-in-a-string/
     * 
     * Approach:
     * 1. Return empty list if pattern longer than string
     * 2. Create two frequency arrays (size 26 for lowercase letters)
     *    - 'need' array: frequency of pattern characters
     *    - 'window' array: frequency of first p.length() chars in s
     * 3. Count how many character frequencies match between need and window
     * 4. Slide window across string:
     *    - If all 26 frequencies match, add window start index to result
     *    - Remove leftmost character and add next character
     *    - Recalculate matches
     * 5. Return list of all starting indices where anagrams are found
     * 
     * @param s the source string to search in
     * @param p the pattern string to find as anagrams
     * @return list of starting indices of all anagrams of p in s
     * 
     * Example:
     * <pre>
     * Input: s = "cbaebabacd", p = "abc"
     * Window matches:
     *   "cba" at 0 → anagram ✓
     *   "bae" at 1 → not anagram
     *   ...
     *   "aba" at 5 → anagram ✓
     *   "bac" at 6 → anagram ✓
     * Output: [0, 6]
     * </pre>
     * 
     * Time Complexity: O(n) - sliding window with constant work per position
     * Space Complexity: O(1) - two fixed-size arrays of 26 elements
     */
    /*  Examples
• 
s = "cbaebabacd", p = "abc"
Substrings of length 3:
◦
"cba" (index 0) → anagram of "abc"
◦
"bae" (1) → no
◦
"aeb" (2) → no
◦
"eba" (3) → no
◦
"bab" (4) → no
◦
"aba" (5) → anagram of "abc"
◦
"bac" (6) → anagram of "abc"
Answer: [0, 6]*/

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int n = s.length(); int m = p.length();
        if(m > n) return result; // base case

        int[] need = new int[26];
        int[] window = new int[26];

        for(int i=0; i<m; i++){
            need[p.charAt(i) - 'a']++;
            window[s.charAt(i) - 'a']++;
        }

        int matches = 0;
        for(int i=0; i<26; i++){
            if(need[i] == window[i]) matches++;
        }
        if(matches == 26) result.add(0);
        
        return result;
    }

    /**
     * Ransom Note construction check.
     * LeetCode 383: https://leetcode.com/problems/ransom-note/
     * 
     * Approach:
     * 1. Create a frequency array for all 26 lowercase letters
     * 2. Count character frequencies in magazine
     * 3. For each character in ransom note:
     *    - Decrement its count from the frequency array
     *    - If count becomes negative, character not available → return false
     * 4. If all characters can be decremented, return true
     * 
     * Key insight: We only need magazine characters to exceed or equal
     * ransom note character frequencies. Using a counter array is efficient
     * since we only deal with lowercase letters.
     * 
     * @param ransomNote the ransom note string to construct
     * @param magazine the magazine string with available characters
     * @return true if ransom note can be constructed from magazine, false otherwise
     * 
     * Example:
     * <pre>
     * Input: ransomNote = "aa", magazine = "aab"
     * Count magazine: a→2, b→1
     * Check ransom: first 'a' OK (a→1), second 'a' OK (a→0)
     * Output: true
     * 
     * Input: ransomNote = "aa", magazine = "ab"
     * Count magazine: a→1, b→1
     * Check ransom: first 'a' OK (a→0), second 'a' FAIL (a→-1)
     * Output: false
     * </pre>
     * 
     * Time Complexity: O(n + m) - count magazine + check ransom note
     * Space Complexity: O(1) - fixed array of 26 elements
     */
    /* Examples
• 
ransomNote = "a", magazine = "b" → false (no a).
•
ransomNote = "aa", magazine = "ab" → false (only one a in magazine).
•
ransomNote = "aa", magazine = "aab" → true (magazine has two as).*/
    public boolean canConstruct(String ransomNote, String magazine) {

        int count[] = new int[26];
        for(int i=0; i<magazine.length(); i++){
            count[magazine.charAt(i) - 'a']++;
        }
        for(int i=0; i<ransomNote.length(); i++){
            int idx = ransomNote.charAt(i)-'a';
            if(--count[idx] < 0) return false;
        }
        return true;
    }

    /**
     * First Unique Character in a String.
     * LeetCode 387: https://leetcode.com/problems/first-unique-character-in-a-string/
     * 
     * Approach:
     * 1. Create a frequency array for all 26 lowercase letters
     * 2. Count all character frequencies in the string
     * 3. Iterate through string again from index 0
     * 4. Return the index of first character with frequency = 1
     * 5. If no unique character found, return -1
     * 
     * Key insight: Two-pass approach ensures we find the FIRST unique character
     * by first building frequency map, then scanning linearly for count == 1.
     * 
     * @param s the input string
     * @return index of first unique character, or -1 if none exists
     * 
     * Example:
     * <pre>
     * Input: s = "leetcode"
     * Frequency: l→1, e→3, t→1, c→1, o→1, d→1
     * First pass result: character at index 0 is 'l' with count 1
     * Output: 0
     * 
     * Input: s = "aabb"
     * Frequency: a→2, b→2
     * No character has frequency 1
     * Output: -1
     * </pre>
     * 
     * Time Complexity: O(n) - two passes through string
     * Space Complexity: O(1) - fixed array of 26 elements
     */
    public int firstUniqChar(String s) {
        int count[] = new int[26];
        for(int i=0; i<s.length(); i++){
            count[s.charAt(i) - 'a']++;
        }
        for(int i=0; i<s.length(); i++){
            if(count[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int nums[] = {2, 11, 7, 15}; int target = 9;
        String s = "anagram"; String t = "nagraam";
        System.out.println(isAnagram(s,t));
    }
}

/*  Two Sum

Group Anagrams

Valid Anagram

Top K Frequent Elements

Longest Consecutive Sequence

Isomorphic Strings

Subarray Sum Equals K

Word Pattern

Find All Anagrams in a String

Intersection of Two Arrays

Happy Number

Ransom Note

First Unique Character in a String

Sort Characters by Frequency

Check if Array Pairs Are Divisible by k
*/
