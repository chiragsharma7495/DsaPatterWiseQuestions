package StringsPart2;

import java.util.*;

/**
 * Pattern-Wise String Processing Solutions
 * 
 * This class contains solutions to common LeetCode string problems organized by pattern type.
 * It demonstrates fundamental string processing techniques used in interviews:
 * 
 * 1. Basic String Traversal & Frequency Analysis
 *    - Character reversal (LeetCode 344)
 *    - First unique character (LeetCode 387)
 *    - Anagram detection (LeetCode 242)
 *    - Pangram checking (LeetCode 1832)
 *    - Word count from array (LeetCode 2657)
 *    - Balloon count problem (LeetCode 1189)
 *    - Find the difference (LeetCode 389)
 * 
 * 2. Two-Pointer Technique on Strings
 *    - Reverse vowels (LeetCode 345)
 *    - Valid palindrome with one deletion (LeetCode 680)
 *    - Merge strings alternately (LeetCode 1768)
 *    - Reverse words individually (LeetCode 557)
 * 
 * Key Data Structures:
 * - Frequency arrays (fixed size 26 for lowercase letters)
 * - HashMaps for counting and validation
 * - Two-pointer technique for in-place operations
 * - String manipulation with StringBuilder
 * 
 * Common Time/Space Patterns:
 * - Single pass: O(n) time, O(1) space (for fixed alphabet)
 * - HashMap approach: O(n) time, O(k) space where k is unique characters
 * - Two-pointer: O(n) time, O(n) space (for char array conversion)
 */
public class PatternWiseStringQues {

    // ============ BASIC STRING TRAVERSAL & FREQUENCY ============

    /**
     * Problem: Reverse a character array in-place.
     * LeetCode: 344. Reverse String
     * 
     * Reverse the characters in a character array without using any extra space.
     * The reversal should be done in-place.
     * 
     * Algorithm: Two-Pointer In-Place Reversal
     * 1. Use two pointers: start at beginning, end at last position
     * 2. While start < end:
     *    - Swap characters at start and end
     *    - Move start forward and end backward
     * 3. Modification happens directly in the input array
     * 
     * Example:
     * Input: char[] s = ['h','e','l','l','o']
     * Process:
     *   Swap indices 0,4: ['o','e','l','l','h']
     *   Swap indices 1,3: ['o','l','l','e','h']
     *   Loop ends (left=2, right=2, left < right is false)
     * Output: ['o','l','l','e','h']
     * 
     * Time Complexity: O(n) where n is the length of the array
     *   - Each character visited exactly once
     * 
     * Space Complexity: O(1)
     *   - Only two pointer variables used
     *   - In-place reversal, no extra space for storage
     * 
     * @param arr Character array to reverse in-place
     */
    public static void reverseString(char[] arr) {
       int start = 0; int end = arr.length-1;
        while(start < end){
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * Problem: Reverse a string.
     * LeetCode: 344. Reverse String (String overload version)
     * 
     * Similar to reverseString(char[]), but takes and returns a String instead of modifying an array.
     * Since strings are immutable in Java, this method creates a new reversed string.
     * 
     * Algorithm: Convert-Reverse-Convert Pattern
     * 1. Return original string if null or empty
     * 2. Convert string to character array
     * 3. Use two pointers to reverse the array in-place
     * 4. Convert character array back to string
     * 5. Return reversed string
     * 
     * Example:
     * Input: "hello"
     * Process:
     *   Convert to: ['h','e','l','l','o']
     *   Reverse: ['o','l','l','e','h']
     *   Convert to: "olleh"
     * Output: "olleh"
     * 
     * Time Complexity: O(n) where n is the length of the string
     *   - Array conversion: O(n)
     *   - Reversal: O(n)
     *   - String conversion: O(n)
     * 
     * Space Complexity: O(n)
     *   - Character array storage: O(n)
     *   - Result string: O(n)
     * 
     * @param str String to reverse
     * @return Reversed string
     */
    public static String reverseString(String str) {
        if(str == null || str.length() == 0) return str;
        char arr[] = str.toCharArray();
        int start = 0; int end = arr.length-1;
        while(start < end){
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        return new String(arr);
    }

    /**
     * Problem: Find the first character that appears only once in a string.
     * LeetCode: 387. First Unique Character in a String
     * 
     * Given a string, find the index of the first character that appears only once.
     * If all characters appear more than once, return -1.
     * 
     * Algorithm: Two-Pass Frequency Array
     * Pass 1: Count frequency of each character
     * Pass 2: Find first character with frequency == 1
     * 
     * Example: s = "leetcode"
     * Character breakdown:
     *   l: 1 time (index 0)
     *   e: 3 times (indices 1, 6, 7)
     *   t: 1 time (index 3)
     *   c: 1 time (index 4)
     *   o: 1 time (index 5)
     *   d: 1 time (index 6)
     * 
     * First pass frequency array building (freq array for a-z):
     * Position in freq array: a=0, b=1, c=2, d=3, e=4, ..., l=11, ..., o=14, ..., t=19, ...
     * freq[4] (e) = 3, freq[11] (l) = 1, freq[19] (t) = 1, etc.
     * 
     * Second pass - searching for first unique:
     * Index 0: 'l' (freq = 1) → RETURN 0
     * 
     * Another example: s = "loveleetcode"
     * Unique characters: v(1), others appear more
     * Index 2: 'v' (freq = 1) → RETURN 2
     * 
     * Character-by-character trace for "bb":
     * Pass 1: freq['b'-'a'] = 2
     * Pass 2: 'b' has freq 2, not 1
     * Result: -1 (no unique character)
     * 
     * Time Complexity: O(n) where n is the length of the string
     *   - Pass 1: O(n) to build frequency array
     *   - Pass 2: O(n) to find first unique character
     *   - Total: O(2n) = O(n)
     * 
     * Space Complexity: O(1)
     *   - Fixed frequency array of size 26 (lowercase letters only)
     *   - Space does not depend on input size
     * 
     * @param s String to search for first unique character
     * @return Index of first unique character, or -1 if none exists
     */
    public int firstUniqChar(String s) {
         int n = s.length();
         int freq[] = new int[26];

         for(int i=0; i<n; i++){
             freq[s.charAt(i) - 'a'] ++;
         }

         for(int i=0; i<n; i++){
             if(freq[s.charAt(i) - 'a'] == 1 ) return i;
         }
         return -1;
     }

    /**
     * Problem: Check if two strings are anagrams of each other.
     * LeetCode: 242. Valid Anagram
     * 
     * Two strings are anagrams if they contain the same characters with the same frequencies,
     * just rearranged. For example: "listen" and "silent" are anagrams.
     * 
     * Algorithm: Frequency Increment-Decrement
     * 1. If strings have different lengths, they cannot be anagrams → return false
     * 2. Create frequency array of size 26 (for a-z)
     * 3. For each character at position i:
     *    - Increment frequency for character in string s
     *    - Decrement frequency for character in string t
     * 4. Check if all frequencies are 0:
     *    - If any frequency != 0, strings are not anagrams → return false
     *    - If all frequencies == 0, strings are anagrams → return true
     * 
     * Example: s = "anagram", t = "nagaram"
     * Process:
     *   s[0]='a': freq[0]++ → freq[0]=1, t[0]='n': freq[13]-- → freq[13]=-1
     *   s[1]='n': freq[13]++ → freq[13]=0, t[1]='a': freq[0]-- → freq[0]=0
     *   s[2]='a': freq[0]++ → freq[0]=1, t[2]='g': freq[6]-- → freq[6]=-1
     *   s[3]='g': freq[6]++ → freq[6]=0, t[3]='a': freq[0]-- → freq[0]=0
     *   s[4]='r': freq[17]++ → freq[17]=1, t[4]='r': freq[17]-- → freq[17]=0
     *   s[5]='a': freq[0]++ → freq[0]=1, t[5]='a': freq[0]-- → freq[0]=0
     *   s[6]='m': freq[12]++ → freq[12]=1, t[6]='m': freq[12]-- → freq[12]=0
     * After processing: All freq[i] == 0
     * Output: true
     * 
     * Counter-example: s = "ab", t = "cd"
     * s[0]='a': freq[0]++ → freq[0]=1, t[0]='c': freq[2]-- → freq[2]=-1
     * s[1]='b': freq[1]++ → freq[1]=1, t[1]='d': freq[3]-- → freq[3]=-1
     * After processing: freq[0]=1, freq[1]=1, freq[2]=-1, freq[3]=-1 (not all 0)
     * Output: false
     * 
     * Time Complexity: O(n) where n is the length of the strings
     *   - Length check: O(1)
     *   - Frequency building: O(n)
     *   - Verification loop: O(26) = O(1)
     * 
     * Space Complexity: O(1)
     *   - Fixed frequency array of size 26
     * 
     * @param s First string
     * @param t Second string
     * @return true if anagrams, false otherwise
     */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        int freq[] = new int [26];

        for(int i=0; i<s.length(); i++){
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for(int str : freq){
            if(str != 0) return false;
        }
        return true;
    }

    /**
     * Problem: Check if a string is a pangram (contains all 26 letters).
     * LeetCode: 1832. Check if the Sentence Is Pangram
     * 
     * A pangram is a sentence containing all 26 letters of the English alphabet at least once.
     * Example: "The quick brown fox jumps over the lazy dog"
     * 
     * Algorithm: Frequency Array Validation
     * 1. Create frequency array of size 26
     * 2. Traverse the string, counting frequency of each lowercase letter
     *    (assumes input is already lowercase or non-letter characters are ignored)
     * 3. Check if any letter has frequency == 0:
     *    - If found, string is not a pangram → return false
     * 4. If all letters have frequency > 0 → return true
     * 
     * Note: This implementation assumes the input string contains only lowercase letters
     * and spaces. For uppercase or mixed case, convert to lowercase first.
     * 
     * Example: str = "abcdefghijklmnopqrstuvwxyz"
     * Process:
     *   Each letter a-z has freq[i]++ executed once
     *   All freq[i] = 1 (not 0)
     * Output: true
     * 
     * Example: str = "hello world"
     * Missing letters: c,f,g,j,k,p,q,s,t,u,v,x,y,z
     * freq[2] (c) = 0 → RETURN false
     * Output: false
     * 
     * Time Complexity: O(n) where n is the length of the string
     *   - Single pass to count frequencies
     *   - Verification: O(26) = O(1)
     * 
     * Space Complexity: O(1)
     *   - Fixed frequency array of size 26
     * 
     * @param str String to check if it's a pangram
     * @return true if pangram, false otherwise
     */
    public boolean checkIfPangram(String str) {

        int freq[] = new int[26];

        for(int i=0; i<str.length(); i++){
            freq[str.charAt(i) - 'a']++;
        }

        for(int a : freq){
            if(a == 0)return false;
        }
        return true;
    }

    /**
     * Problem: Count words appearing exactly once in both arrays.
     * LeetCode: 2657. Find the Prefix Common Array of Two Arrays
     * 
     * Given two arrays of strings, count how many words appear exactly once in words1
     * AND exactly once in words2 (but not necessarily in same positions).
     * 
     * Algorithm: HashMap Frequency Counting
     * 1. Create HashMap for words1:
     *    - Count frequency of each word
     *    - Key: word, Value: frequency
     * 2. Create HashMap for words2:
     *    - Count frequency of each word
     * 3. Iterate through all words in words1:
     *    - If word appears exactly once in words1 (map1.get(word)==1)
     *    - AND word exists in words2 (map2.containsKey(word))
     *    - AND word appears exactly once in words2 (map2.get(word)==1)
     *    - Then increment count
     * 4. Return total count
     * 
     * Example:
     * words1 = ["x","y","z"], words2 = ["z","y","x"]
     * Process:
     *   map1: {"x": 1, "y": 1, "z": 1}
     *   map2: {"z": 1, "y": 1, "x": 1}
     *   
     *   word "x": map1.get("x")=1 AND map2.containsKey("x") AND map2.get("x")=1 → count=1
     *   word "y": map1.get("y")=1 AND map2.containsKey("y") AND map2.get("y")=1 → count=2
     *   word "z": map1.get("z")=1 AND map2.containsKey("z") AND map2.get("z")=1 → count=3
     * Output: 3
     * 
     * Another example:
     * words1 = ["a","aa","aaa"], words2 = ["a","a","a"]
     * Process:
     *   map1: {"a": 1, "aa": 1, "aaa": 1}
     *   map2: {"a": 3}
     *   
     *   word "a": map1.get("a")=1 AND map2.containsKey("a") BUT map2.get("a")=3 → no increment
     *   word "aa": map1.get("aa")=1 AND map2.containsKey("aa")=false → no increment
     *   word "aaa": map1.get("aaa")=1 AND map2.containsKey("aaa")=false → no increment
     * Output: 0
     * 
     * Time Complexity: O(m + n) where m = words1.length, n = words2.length
     *   - Building map1: O(m)
     *   - Building map2: O(n)
     *   - Checking common words: O(m)
     * 
     * Space Complexity: O(m + n)
     *   - HashMap for words1: O(unique words in words1)
     *   - HashMap for words2: O(unique words in words2)
     * 
     * @param words1 First array of strings
     * @param words2 Second array of strings
     * @return Count of words appearing exactly once in both arrays
     */
    public int countWords(String[] words1, String[] words2) {
        Map<String , Integer> map1 = new HashMap<>();
        for(String word : words1){
            map1.put(word , map1.getOrDefault(word , 0) +1);
        }

        Map<String , Integer> map2 = new HashMap<>();
        for(String word : words2){
            map2.put(word , map2.getOrDefault(word , 0) +1);
        }

        int count = 0;
        for(String word : map1.keySet()){
            if(map1.get(word) == 1 && map2.containsKey(word) && map2.get(word) == 1) count++;
        }
        return count;
    }

    /**
     * Problem: Find the maximum number of instances of "balloon" that can be formed.
     * LeetCode: 1189. Maximum Number of Balloons
     * 
     * Given a string text, find how many times you can form the word "balloon"
     * using characters from text. Each character can be used at most once.
     * 
     * Key insight: "balloon" requires:
     *   b: 1 time
     *   a: 1 time
     *   l: 2 times
     *   o: 2 times
     * 
     * Algorithm: Frequency Analysis with Bottleneck Detection
     * 1. Count frequency of each character in text
     * 2. Extract required characters:
     *    - b_count: frequency of 'b' (need 1 per balloon)
     *    - a_count: frequency of 'a' (need 1 per balloon)
     *    - l_count: frequency of 'l' divided by 2 (need 2 per balloon)
     *    - o_count: frequency of 'o' divided by 2 (need 2 per balloon)
     * 3. The bottleneck (minimum) determines how many balloons can be formed
     * 4. Return the minimum of all counts
     * 
     * Example: text = "loonbalxballpoon"
     * Character frequencies:
     *   l: 3 → for "balloon" need 2, so 3/2 = 1 balloon possible
     *   o: 4 → for "balloon" need 2, so 4/2 = 2 balloons possible
     *   n: 2
     *   b: 1 → for "balloon" need 1, so 1 balloon possible
     *   a: 1 → for "balloon" need 1, so 1 balloon possible
     *   x: 1
     *   p: 1
     * 
     * Bottleneck analysis:
     *   b_count = 1
     *   a_count = 1
     *   l_count = 3/2 = 1
     *   o_count = 4/2 = 2
     * 
     * min(1, 1, 1, 2) = 1
     * Output: 1 (can form "balloon" once)
     * 
     * Another example: text = "balon"
     * Frequencies: b=1, a=1, l=1, o=1, n=1
     *   b_count = 1
     *   a_count = 1
     *   l_count = 1/2 = 0 (floor division, we need 2 'l's)
     *   o_count = 1/2 = 0 (floor division, we need 2 'o's)
     * 
     * min(1, 1, 0, 0) = 0
     * Output: 0 (cannot form "balloon" at all)
     * 
     * Time Complexity: O(n) where n is the length of the text
     *   - Building frequency map: O(n)
     *   - Extracting counts: O(1) (constant operations)
     * 
     * Space Complexity: O(1)
     *   - Fixed HashMap size (at most 26 lowercase letters)
     * 
     * @param text String to check for "balloon" formation
     * @return Maximum number of "balloon" instances that can be formed
     */
    public int maxNumberOfBalloons(String text) {
         Map<Character , Integer> freq = new HashMap<>();
         for(char c : text.toCharArray()){
             freq.put(c,freq.getOrDefault(c,0) +1);
         }
         int b_count = freq.getOrDefault('b' , 0);
         int a_count = freq.getOrDefault('a' , 0);
         int l_count = freq.getOrDefault('l' , 0)/2;
         int o_count = freq.getOrDefault('o' , 0)/2;

         return Math.min(Math.min(b_count , a_count), Math.min(l_count , o_count));
     }

    /**
     * Problem: Find the extra character when given two strings where one has one extra character.
     * LeetCode: 389. Find the Difference
     * 
     * Given two strings s and t where t is exactly the same as s but with one extra
     * character added anywhere, find and return that extra character.
     * 
     * Algorithm: Frequency Decrement Until Negative
     * 1. Create frequency array for all characters
     * 2. For each character in s: increment frequency (increment phase)
     * 3. For each character in t: decrement frequency (decrement phase)
     *    - If frequency becomes negative, this character is the extra one → return immediately
     * 4. If loop completes without finding negative (shouldn't happen with valid input),
     *    return space ' '
     * 
     * Key insight: As soon as we find a character in t that doesn't match s's count,
     * we found the extra character. Early termination saves time.
     * 
     * Example: s = "abcd", t = "abcde"
     * Process:
     *   Build freq from s: a=1, b=1, c=1, d=1
     *   Process t:
     *     't': 'a'→freq[0]-- = 0
     *     't': 'b'→freq[1]-- = 0
     *     't': 'c'→freq[2]-- = 0
     *     't': 'd'→freq[3]-- = 0
     *     't': 'e'→freq[4]-- = -1 (negative!) → RETURN 'e'
     * Output: 'e'
     * 
     * Another example: s = "ae", t = "aea"
     * Process:
     *   Build freq from s: a=1, e=1
     *   Process t:
     *     't': 'a'→freq[0]-- = 0
     *     't': 'e'→freq[4]-- = 0
     *     't': 'a'→freq[0]-- = -1 (negative!) → RETURN 'a'
     * Output: 'a'
     * 
     * Time Complexity: O(m + n) where m = s.length, n = t.length
     *   - Building frequency from s: O(m)
     *   - Processing t: O(n) worst case, but typically O(n) with early exit
     * 
     * Space Complexity: O(1)
     *   - Fixed frequency array of size 26 for lowercase letters
     * 
     * @param s Original string
     * @param t String with one extra character added
     * @return The extra character
     */
    public char findTheDifference(String s, String t) {
        int freq[] = new int[26];
        for(int i=0; i<s.length(); i++){
            freq[s.charAt(i) - 'a']++;
        }
        for(int i=0; i<t.length(); i++){
            freq[t.charAt(i) - 'a']--;
            if(freq[t.charAt(i) - 'a'] < 0) return t.charAt(i);
        }
        return ' ';
    }

    // ============ TWO-POINTER TECHNIQUE ON STRINGS ============

    /**
     * Problem: Reverse only the vowels in a string.
     * LeetCode: 345. Reverse Vowels of a String
     * 
     * Given a string, reverse only the vowel characters while keeping consonants
     * in their original positions.
     * 
     * Algorithm: Two-Pointer with Vowel Skipping
     * 1. Convert string to character array
     * 2. Use two pointers: left at start, right at end
     * 3. While left < right:
     *    - Move left forward until it points to a vowel
     *    - Move right backward until it points to a vowel
     *    - Swap characters at left and right positions
     *    - Move both pointers inward
     * 
     * Helper method: isVowel() checks if a character is a vowel (both cases: a,e,i,o,u,A,E,I,O,U)
     * 
     * Example: s = "hello"
     * Vowels: 'e' at index 1, 'o' at index 4
     * Process:
     *   left=0: 'h' (not vowel), left++ → left=1
     *   left=1: 'e' (vowel!)
     *   right=4: 'o' (vowel!)
     *   Swap 'e' and 'o': ['h','o','l','l','e']
     *   left++, right--
     *   left=2, right=3: left < right? No, exit
     * Output: "holle"
     * 
     * Character-by-character trace for "aA":
     * Before: a A
     *         0 1
     * left=0: 'a' is vowel
     * right=1: 'A' is vowel
     * Swap: A a
     * After: "Aa"
     * 
     * Time Complexity: O(n) where n is the length of the string
     *   - Each character visited at most once by both pointers
     * 
     * Space Complexity: O(n)
     *   - Character array of size n
     *   - Result string of size n
     * 
     * @param s String with vowels to reverse
     * @return String with vowels reversed and consonants in place
     */
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length-1;

        while(left < right){
            while(left < right && !isVowel(chars[left])) left++;
            while(left < right && !isVowel(chars[right])) right--;

            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    /**
     * Helper method: Check if a character is a vowel.
     * 
     * Checks both lowercase and uppercase vowels: a, e, i, o, u, A, E, I, O, U
     * 
     * @param c Character to check
     * @return true if c is a vowel, false otherwise
     */
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    /**
     * Problem: Check if a string is a valid palindrome with at most one character deletion.
     * LeetCode: 680. Valid Palindrome II
     * 
     * Given a string, determine if it can be a valid palindrome by removing at most one character.
     * A palindrome reads the same forwards and backwards.
     * 
     * Algorithm: Two-Pointer with Character Skip Option
     * 1. Use two pointers: left at start, right at end
     * 2. While left < right:
     *    - If characters match, move pointers inward
     *    - If characters don't match:
     *      - Try skipping left character: check if substring[left+1...right] is palindrome
     *      - Try skipping right character: check if substring[left...right-1] is palindrome
     *      - Return true if either works
     * 3. If we complete the loop without mismatch, it's already a palindrome
     * 
     * Helper method: isPalindrome() checks if a substring between two indices is palindromic
     * 
     * Example: s = "abca"
     * Process:
     *   left=0, right=3: 'a' == 'a'? Yes, left++, right--
     *   left=1, right=2: 'b' == 'c'? No
     *   Try skip left: isPalindrome(s, 2, 2)? Yes (single char)
     *   Return true (can form palindrome by removing 'b')
     * Output: true (after removing 'b': "aca")
     * 
     * Another example: s = "abc"
     * Process:
     *   left=0, right=2: 'a' == 'c'? No
     *   Try skip left: isPalindrome(s, 1, 2)? "bc" - b!=c? No
     *   Try skip right: isPalindrome(s, 0, 1)? "ab" - a!=b? No
     *   Return false (cannot form palindrome with one deletion)
     * Output: false
     * 
     * Time Complexity: O(n) where n is the length of the string
     *   - Main loop: O(n)
     *   - In worst case, one isPalindrome call: O(n)
     *   - Total: O(2n) = O(n)
     * 
     * Space Complexity: O(1)
     *   - Only pointers used, no extra data structures
     * 
     * @param s String to check if valid palindrome with at most one deletion
     * @return true if valid palindrome with at most one deletion, false otherwise
     */
    public boolean validPalindrome(String s) {
        int left = 0; int right = s.length()-1;

        while(left < right){
            if(s.charAt(left) == s.charAt(right)){
                left++;
                right--;
            }else{
                boolean skipLeft = isPalindrome(s,left+1,right);
                boolean skipright = isPalindrome(s,left,right-1);
                return skipLeft || skipright;
            }
        }
        return true;
    }

    /**
     * Helper method: Check if substring between left and right indices is a palindrome.
     * 
     * Two-pointer approach: Compare characters from outside moving inward.
     * If any mismatch found, it's not a palindrome.
     * If all characters match through the substring, it's a palindrome.
     * 
     * Example: s = "abcba", left=0, right=4
     * Process:
     *   s[0]='a' == s[4]='a'? Yes, left++, right--
     *   s[1]='b' == s[3]='b'? Yes, left++, right--
     *   left=2, right=2: left < right? No, exit
     *   Return true
     * 
     * Time Complexity: O(n) where n = right - left
     *   - Each character pair compared once
     * 
     * Space Complexity: O(1)
     *   - Only two pointer variables
     * 
     * @param s String to check
     * @param left Starting index
     * @param right Ending index
     * @return true if substring is palindrome, false otherwise
     */
    private boolean isPalindrome(String s, int left, int right) {
        while(left < right){
            if(s.charAt(left) != s.charAt(right)) return false;
            left++; right--;
        }
        return true;
    }

    /**
     * Problem: Merge two strings alternately.
     * LeetCode: 1768. Merge Strings Alternately
     * 
     * Given two strings word1 and word2, merge them by adding letters alternately,
     * starting with word1. If one string is longer, append the remaining characters.
     * 
     * Algorithm: Two-Pointer Alternating Append
     * 1. Use two pointers (i and j) for word1 and word2
     * 2. While both pointers are within bounds:
     *    - Append character from word1[i]
     *    - Append character from word2[j]
     *    - Increment both pointers
     * 3. Append remaining characters from word1 (if any)
     * 4. Append remaining characters from word2 (if any)
     * 5. Return the merged result
     * 
     * Example: word1 = "abc", word2 = "defgh"
     * Process:
     *   i=0,j=0: append 'a', 'd' → result="ad"
     *   i=1,j=1: append 'b', 'e' → result="adbe"
     *   i=2,j=2: append 'c', 'f' → result="adbecf"
     *   i=3 (out of bounds), word2 has remaining: "gh"
     *   Append remaining: result="adbecfgh"
     * Output: "adbecfgh"
     * 
     * Character-by-character trace for "ab", "cd":
     * Before: word1="ab", word2="cd"
     *         i=0       j=0
     * 
     * i=0,j=0: append word1[0]='a', word2[0]='c' → result="ac"
     *          i++, j++
     *         word1="ab", word2="cd"
     *                i=1       j=1
     * 
     * i=1,j=1: append word1[1]='b', word2[1]='d' → result="acbd"
     *          i++, j++
     *         i=2 (out), j=2 (out)
     * 
     * Both exhausted, exit
     * Output: "acbd"
     * 
     * Time Complexity: O(m + n) where m = word1.length, n = word2.length
     *   - First loop: O(min(m,n))
     *   - Append remaining word1: O(m)
     *   - Append remaining word2: O(n)
     *   - Total: O(m + n)
     * 
     * Space Complexity: O(m + n)
     *   - Result string of size m + n
     * 
     * @param word1 First string to merge
     * @param word2 Second string to merge
     * @return Merged string with alternating characters
     */
    public String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        int i = 0; int j = 0;

        while(i < word1.length() && j < word2.length()){
            result.append(word1.charAt(i));
            result.append(word2.charAt(j));
            i++; j++;
        }

        while(i < word1.length()){
            result.append(word1.charAt(i));
            i++;
        }

        while(j < word2.length()){
            result.append(word2.charAt(j));
            j++;
        }
        return result.toString();
    }

    /**
     * Problem: Reverse each word in a string while keeping word order.
     * LeetCode: 557. Reverse Words in a String III
     * 
     * Given a string, reverse the characters within each word, maintaining the original
     * order of words. For example, "Let's take LeetCode contest" becomes
     * "s'teL ekat edoceTeL tsettnoc".
     * 
     * Algorithm: Split-Reverse-Join Pattern
     * 1. Split the string by spaces (using split(" ", -1) to preserve empty strings/multiple spaces)
     * 2. For each word in the resulting array:
     *    - Reverse the word using helper method reverseStringg()
     *    - Replace the original word with reversed version
     * 3. Join the array back with spaces
     * 4. Return the result
     * 
     * Helper method: reverseStringg() reverses a single word string using two pointers
     * (Note: Method name has typo "reverseStringg" in original code)
     * 
     * Example: s = "Hello World"
     * Process:
     *   Split by space: ["Hello", "World"]
     *   Reverse "Hello": "olleH"
     *   Reverse "World": "dlroW"
     *   Join with space: "olleH dlroW"
     * Output: "olleH dlroW"
     * 
     * Character-by-character trace for "ab cd":
     * Split: ["ab", "cd"]
     * 
     * Reverse "ab":
     *   Before: a b
     *           0 1
     *   Swap: b a
     *   After: "ba"
     * 
     * Reverse "cd":
     *   Before: c d
     *           0 1
     *   Swap: d c
     *   After: "dc"
     * 
     * Join: "ba dc"
     * Output: "ba dc"
     * 
     * Time Complexity: O(n) where n is the length of the string
     *   - Split operation: O(n)
     *   - Reversing each word: O(n) total (each character reversed once)
     *   - Join operation: O(n)
     *   - Total: O(3n) = O(n)
     * 
     * Space Complexity: O(n)
     *   - String array for words: O(number of words)
     *   - Result string: O(n)
     * 
     * @param s String with words to reverse
     * @return String with each word reversed but order preserved
     */
    public String reverseWords(String s) {
        String words[] = s.split(" " , -1);

        for(int i=0; i<words.length; i++){
            words[i] = reverseStringg(words[i]);
        }

        return String.join(" " , words);
    }

    /**
     * Helper method: Reverse a single word string.
     * 
     * Uses two-pointer technique to reverse characters in a word.
     * This method is used by reverseWords() to reverse each word individually.
     * 
     * Algorithm: Two-Pointer In-Place Reversal
     * 1. Convert word to character array
     * 2. Use left pointer at start, right pointer at end
     * 3. While left < right:
     *    - Swap characters at left and right
     *    - Move pointers inward
     * 4. Convert array back to string
     * 
     * Example: word = "hello"
     * Process:
     *   chars = ['h','e','l','l','o']
     *   Swap 0,4: ['o','e','l','l','h']
     *   Swap 1,3: ['o','l','l','e','h']
     *   Return "olleh"
     * 
     * Note: Method name "reverseStringg" has a typo (double 'g') in the original code.
     * This documentation follows the existing implementation.
     * 
     * Time Complexity: O(n) where n is the length of the word
     *   - Each character pair visited once
     * 
     * Space Complexity: O(n)
     *   - Character array of size n
     * 
     * @param word Word to reverse
     * @return Reversed word
     */
    private String reverseStringg(String word) {
        char chars[] = word.toCharArray();
        int left = 0; int right = chars.length-1;

        while(left < right){
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }










    // stack
    public String minRemoveToMakeValid(String s) {
        HashSet<Integer> toRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);

            if(c == '(') stack.push(i);
            else if(c == ')'){
                if(!stack.isEmpty()) stack.pop();
                else toRemove.add(i);
            }
        }

        while(!stack.isEmpty()){
            toRemove.add(stack.pop());
        }

        StringBuilder result = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            if(!toRemove.contains(i)) result.append(s.charAt(i));
        }
        return result.toString();
    }

    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(!stack.isEmpty() && stack.peek() == ch) stack.pop();
            else{
                stack.push(ch);
            }
        }
        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()){
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }

    public String longestPalindrome(String s) {
        if(s == null || s.length() < 1) return "";
        int start = 0; int maxLen = 0;

        for(int i=0; i<s.length(); i++){
            int len1 = expand(s,i,i);
            int len2 = expand(s,i,i+1);
            int len = Math.max(len1 , len2);

            if(len > maxLen){
                maxLen = len;
                start = i - (len-1)/2;
            }
        }
        return s.substring(start , start + maxLen);
    }

    private int expand(String s, int left, int right) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1;
    }

    public boolean isSubsequence(String s, String t) {
        if(s == null || s.length() == 0) return true;
        if(t == null || s.length() > t.length()) return false;

        int spointer = 0;
        int tpointer = 0;

        while(tpointer < t.length()){
            if(spointer < s.length() && s.charAt(spointer) == t.charAt(tpointer)) spointer++;
            tpointer++;
        }
        return spointer == s.length();
    }

    // pattern : sliding window

    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;

        HashMap<Character , Integer> charIndex = new HashMap<>();
        int masLen = 0; int left = 0;

        for(int right = 0; right < s.length(); right++){
            char currentChar = s.charAt(right);

            if(charIndex.containsKey(currentChar)){
                left = Math.max(left , charIndex.get(currentChar)+1);
            }

            charIndex.put(currentChar , right);
            int currLen = right - left +1;
            masLen = Math.max(masLen , currLen);
        }
        return masLen;
    }

    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s1.length()) return false;

        int [] s1Count = new int[26];
        for(char c : s1.toCharArray()){
            s1Count[c-'a']++;
        }
        int windowSize = s1.length();

        int windowCount[] = new int[26];
        for(int i=0; i<windowSize; i++){
            windowCount[s2.charAt(i) - 'a']++
        }

        if(Arrays.equals(s1Count , windowCount)) return true;

        for(int i=windowSize; i<s2.length(); i++){
            windowCount[s2.charAt(i) - 'a']++;
            windowCount[s2.charAt(i) - 'a']--;
            if(Arrays.equals(s1Count , windowCount)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        char arr[] = {'c' , 'h' , 'i' , 'r' , 'a' ,'g'};
        reverseString(arr);
        System.out.println(arr);
    }
}
