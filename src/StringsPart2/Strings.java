package StringsPart2;

import java.util.ArrayList;

/**
 * Comprehensive String Processing and Manipulation Techniques
 * 
 * This class demonstrates various string algorithms and operations including:
 * - Anagram detection
 * - Pangram checking (brute force and optimized)
 * - Word counting and analysis
 * - String reversal (character and word level)
 * - String compression
 * - Character manipulation
 * - String permutation
 * 
 * Key concepts covered:
 * - Character frequency analysis using arrays
 * - Two-pointer technique for string reversal
 * - Early termination optimization
 * - String traversal patterns
 * - In-place string operations
 */
public class Strings {

    /**
     * Problem: Check if two strings are anagrams of each other.
     * 
     * An anagram is a word or phrase formed by rearranging the letters of another,
     * typically using all the original letters exactly once.
     * Example: "listen" and "silent" are anagrams
     * 
     * Algorithm: Frequency Array Approach
     * 1. If lengths differ, they cannot be anagrams → return 0
     * 2. Create frequency array of size 256 (for all ASCII characters)
     * 3. For each character in string 'a': increment frequency
     * 4. For each character in string 'b': decrement frequency
     * 5. If all frequencies are 0, strings are anagrams
     * 
     * Example:
     * Input: a = "listen", b = "silent"
     * Process:
     *   freq after a: l→1, i→1, s→1, t→1, e→1, n→1, others→0
     *   freq after b: l→0, i→0, s→0, t→0, e→0, n→0, others→0
     * Output: 1 (true)
     * 
     * Counter-example:
     * Input: a = "hello", b = "world"
     * Output: 0 (false) - different characters
     * 
     * Time Complexity: O(n) where n is the length of the strings
     *   - Length check: O(1)
     *   - Frequency building: O(n) for each string
     *   - Verification loop: O(256) = O(1)
     * 
     * Space Complexity: O(1)
     *   - Fixed array of size 256 regardless of input size
     * 
     * @param a First string to check
     * @param b Second string to check
     * @return 1 if anagrams, 0 otherwise
     */
    static int isAnagram(String a, String b) {
        if (a.length() != b.length()) return 0;

        int[] freq = new int[256];

        for (int i = 0; i < a.length(); i++) {
            freq[a.charAt(i)]++;
            freq[b.charAt(i)]--;
        }

        for (int i = 0; i < 256; i++) {
            if (freq[i] != 0) return 0;
        }

        return 1;
    }

    /**
     * Problem: Check if a string is a pangram (brute force approach).
     * 
     * A pangram is a sentence containing all 26 letters of the English alphabet.
     * Example: "The quick brown fox jumps over the lazy dog"
     * 
     * Algorithm: Brute Force with Boolean Array
     * 1. Create boolean array of size 26 for each letter a-z
     * 2. Traverse the string and mark each letter as seen
     * 3. Convert uppercase to lowercase by ignoring case
     * 4. After traversal, check if all 26 positions are true
     * 
     * Example:
     * Input: "The quick brown fox jumps over the lazy dog"
     * Process:
     *   T→t (index 19, seen[19]=true)
     *   h (index 7, seen[7]=true)
     *   e (index 4, seen[4]=true)
     *   ...continues for all letters...
     *   Result: All 26 seen[] positions are true
     * Output: true
     * 
     * Counter-example:
     * Input: "hello"
     * Missing letters: a,b,c,d,f,g,i,j,k,m,n,p,q,r,s,t,u,v,w,x,y,z
     * Output: false
     * 
     * Time Complexity: O(n) where n is the length of the string
     *   - Main traversal: O(n)
     *   - Verification loop: O(26) = O(1)
     * 
     * Space Complexity: O(1)
     *   - Fixed array of size 26 regardless of input
     * 
     * @param str String to check if it's a pangram
     * @return true if pangram, false otherwise
     */
    public static boolean isPangram0(String str) {
        boolean seen[] = new boolean[26];

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(Character.isLetter(ch)){
                seen[ch-'a'] = true;
            }
        }
        for(int i=0; i<seen.length; i++){
            if(!seen[i])return false;
        }
        return true;
    }

    /**
     * Problem: Check if a string is a pangram (optimized approach).
     * 
     * A pangram contains all 26 letters of the alphabet.
     * Optimized version: Uses early termination when all 26 letters are found.
     * 
     * Algorithm: Optimized with Early Termination Counter
     * 1. Create boolean array of size 26
     * 2. Maintain a counter of unique letters found
     * 3. Traverse the string:
     *    - If character is a letter and not yet seen, mark as seen and increment counter
     *    - If counter reaches 26, immediately return true (early termination)
     * 4. If string ends without counter reaching 26, return false
     * 
     * Optimization: Early exit when all 26 letters are found, avoiding complete traversal
     * This is particularly efficient for long strings where pangram is found early.
     * 
     * Example:
     * Input: "The quick brown fox jumps over the lazy dog"
     * Process:
     *   char 'T'→'t': index 19, seen[19]=false, set true, count=1
     *   char 'h': index 7, seen[7]=false, set true, count=2
     *   ...continues...
     *   When count reaches 26, immediately return true
     * Output: true
     * 
     * Time Complexity: O(n) worst case, but typically O(1) for pangrams
     *   - Best case: O(26) = O(1) when pangram is found quickly
     *   - Worst case: O(n) for non-pangrams where entire string is traversed
     * 
     * Space Complexity: O(1)
     *   - Fixed array of size 26
     * 
     * @param str String to check if it's a pangram
     * @return true if pangram, false otherwise
     */
    public static boolean isPangram(String str) {
        boolean[] seen = new boolean[26];
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                int idx = ch - 'a';
                if (!seen[idx]) {
                    seen[idx] = true;
                    count++;
                    if (count == 26) return true;
                }
            }
        }
        return false;
    }

    /**
     * Problem: Count the number of words in a string (brute force approach).
     * 
     * A word is any sequence of non-space characters.
     * This brute force version checks for any space character.
     * 
     * Algorithm: Word Boundary Detection
     * 1. Return 0 if string is null or empty
     * 2. Use a boolean flag 'inWord' to track if currently inside a word
     * 3. Traverse each character:
     *    - If character is not a space and inWord is false:
     *      - Increment word count
     *      - Set inWord = true (entering a word)
     *    - If character is a space:
     *      - Set inWord = false (exiting a word)
     * 4. Return total word count
     * 
     * Example:
     * Input: "hello world java"
     * Process:
     *   h: not space, !inWord, count=1, inWord=true
     *   e,l,l,o: not space, inWord=true, no action
     *   ' ': space, inWord=false
     *   w: not space, !inWord, count=2, inWord=true
     *   o,r,l,d: not space, inWord=true, no action
     *   ' ': space, inWord=false
     *   j: not space, !inWord, count=3, inWord=true
     *   a,v,a: not space, inWord=true, no action
     * Output: 3
     * 
     * Character-by-character trace for "a  b":
     *   Index 0: 'a' (not ' '), inWord=false → count=1, inWord=true
     *   Index 1: ' ' (space) → inWord=false
     *   Index 2: ' ' (space) → inWord=false
     *   Index 3: 'b' (not ' '), inWord=false → count=2, inWord=true
     *   Result: 2 words
     * 
     * Time Complexity: O(n) where n is the length of the string
     *   - Single pass through the string
     * 
     * Space Complexity: O(1)
     *   - Only uses constant extra space
     * 
     * @param str String to count words in
     * @return Number of words in the string
     */
    static int countWords0(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int wordCount = 0;
        boolean inWord = false;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch != ' ') {
                // Character is not a space
                if (!inWord) {
                    wordCount++;  // Start of a new word
                    inWord = true;
                }
            } else {
                // Character is a space
                inWord = false;
            }
        }
        return wordCount;
    }

    /**
     * Problem: Count the number of words in a string (optimized approach).
     * 
     * Words are sequences of lowercase letters, separated by spaces, tabs, or newlines.
     * Optimized version: Specifically checks for word characters (a-z) and common whitespace.
     * 
     * Algorithm: Optimized Word Boundary Detection
     * 1. Use a boolean flag 'inWord' to track word boundaries
     * 2. Traverse each character:
     *    - If character is lowercase letter (a-z):
     *      - If not already in a word (!inWord), increment count and set inWord=true
     *    - If character is whitespace (space, tab, newline):
     *      - Set inWord=false (word ended)
     *    - For any other character:
     *      - Set inWord=false (word ended)
     * 3. Return total word count
     * 
     * Key difference from countWords0():
     * - Only counts lowercase letters as word characters (more specific)
     * - Handles multiple whitespace types: space, tab, newline
     * - Skips special characters and uppercase letters
     * 
     * Example:
     * Input: "hello  world\tjava"
     * Process:
     *   h: letter, !inWord → count=1, inWord=true
     *   e,l,l,o: letter, inWord=true → no action
     *   ' ': space → inWord=false
     *   ' ': space → inWord=false (multiple spaces handled)
     *   w: letter, !inWord → count=2, inWord=true
     *   o,r,l,d: letter, inWord=true → no action
     *   '\t': tab → inWord=false
     *   j: letter, !inWord → count=3, inWord=true
     *   a,v,a: letter, inWord=true → no action
     * Output: 3
     * 
     * Time Complexity: O(n) where n is the length of the string
     *   - Single pass through the string
     * 
     * Space Complexity: O(1)
     *   - Only uses constant extra space
     * 
     * @param s String to count words in
     * @return Number of words in the string
     */
    public static int countWords(String s) {
        int count = 0;
        boolean inWord = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                if (!inWord) {
                    count++;
                    inWord = true;
                }
            } else if (c == ' ' || c == '\t' || c == '\n') {
                inWord = false;
            } else {
                // If input is guaranteed to be only space/tab/newline/lowercase, this can be omitted.
                inWord = false;
            }
        }
        return count;
    }

    /**
     * Problem: Compress a string using run-length encoding.
     * 
     * Run-length encoding replaces consecutive identical characters with the character
     * followed by its count (if count > 1).
     * 
     * Algorithm: Single Pass String Compression
     * 1. Return original string if null or empty
     * 2. Use StringBuilder for efficient string building
     * 3. Maintain a character count
     * 4. Traverse the string:
     *    - If next character differs or we're at the end:
     *      - Append current character to result
     *      - If count > 1, append the count
     *      - Reset count to 1
     *    - Otherwise, increment count
     * 5. Return compressed string
     * 
     * Example:
     * Input: "aabbbbcc"
     * Process:
     *   Index 0: 'a', count=1
     *   Index 1: 'a', next='b' (differs) → append 'a', count=2 → append '2', count=1
     *   Index 2: 'b', count=1
     *   Index 3: 'b', count=2
     *   Index 4: 'b', count=3
     *   Index 5: 'b', next='c' (differs) → append 'b', append '4', count=1
     *   Index 6: 'c', count=1
     *   Index 7: 'c', next=end → append 'c', count=2 → append '2'
     * Output: "a2b4c2"
     * 
     * Character-by-character trace:
     * Before: a a b b b b c c
     * After:  a 2 b 4 c 2
     * 
     * Edge cases:
     * - Single characters: "abc" → "abc" (no compression benefit)
     * - All same: "aaaa" → "a4"
     * - Empty/null: returns original string
     * 
     * Time Complexity: O(n) where n is the length of the string
     *   - Single pass through the string
     *   - StringBuilder append operations are amortized O(1)
     * 
     * Space Complexity: O(n) in worst case
     *   - Result string can be up to size of input (if no compression)
     * 
     * @param str String to compress
     * @return Compressed string using run-length encoding
     */
    static String compressString(String str) {
        if(str == null || str.isEmpty()){
            return str;
        }

        StringBuilder result = new StringBuilder();
        int count = 1;

        for(int i=0; i<str.length(); i++){
            if(i+1 >= str.length() || str.charAt(i) != str.charAt(i+1)){
                result.append(str.charAt(i));

                if(count > 1) result.append(count);

                count = 1;
            }else{
                count++;
            }
        }
        return result.toString();
    }

    /**
     * Problem: Compress a character array in-place using run-length encoding.
     * 
     * Similar to compressString, but modifies the input array directly and returns
     * the length of the compressed result. The compression is done in-place with
     * two pointers: one for reading (i) and one for writing (idx).
     * 
     * Algorithm: In-Place Compression with Two Pointers
     * 1. Return 0 if array is null or empty
     * 2. Use two pointers:
     *    - i: reads from the original positions
     *    - idx: writes to the compressed positions
     * 3. Maintain a character count
     * 4. Traverse with pointer i:
     *    - If character at i differs from next or at end:
     *      - Write character to arr[idx++]
     *      - If count > 1, convert count to string and write each digit
     *      - Reset count
     *    - Otherwise, increment count
     * 5. Return idx (length of compressed array)
     * 
     * Example:
     * Input: ['a','a','b','b','c','c','c','c']
     * Process:
     *   Read pointer i traverses, write pointer idx updates as follows:
     *   i=0-1: 'a' appears 2 times → arr[0]='a', arr[1]='2', idx=2
     *   i=2-3: 'b' appears 2 times → arr[2]='b', arr[3]='2', idx=4
     *   i=4-7: 'c' appears 4 times → arr[4]='c', arr[5]='4', idx=6
     * Output: arr becomes ['a','2','b','2','c','4','c','c'], returns 6
     * 
     * Multi-digit count handling:
     * Input: ['a' repeated 10 times] with enough space
     * When count=10: String.valueOf(10) = "10"
     *   arr[idx++] = '1'
     *   arr[idx++] = '0'
     * This allows handling counts > 9
     * 
     * Time Complexity: O(n) where n is the length of the array
     *   - Single pass through the array
     * 
     * Space Complexity: O(1)
     *   - Only uses constant extra space (two pointers and counter)
     *   - Compression is done in-place
     * 
     * @param arr Character array to compress in-place
     * @return Length of the compressed array
     */
    public int compress(char[] arr) {
        if(arr == null || arr.length == 0) return 0;
        int count = 1;
        int idx = 0;

        for(int i=0; i<arr.length; i++){
            if(i+1 >= arr.length || arr[i] != arr[i+1]){
                arr[idx++] = arr[i];

                if(count > 1){
                    String countStr = String.valueOf(count);
                    for(char c : countStr.toCharArray()){
                        arr[idx++] = c;
                    }
                }
                count = 1;
            }else{
                count++;
            }
        }
        return idx;
    }

    /**
     * Problem: Reverse a string by reversing its individual characters.
     * 
     * String reversal is a fundamental operation often used as a preprocessing step
     * for palindrome checking and other string algorithms.
     * 
     * Algorithm: Two-Pointer In-Place Reversal
     * 1. Return original string if null or empty
     * 2. Convert string to character array (since strings are immutable in Java)
     * 3. Use two pointers (left and right):
     *    - left starts at index 0
     *    - right starts at last index (length - 1)
     * 4. While left < right:
     *    - Swap characters at left and right positions
     *    - Move left forward and right backward
     * 5. Convert character array back to string and return
     * 
     * Example:
     * Input: "hello"
     * Initial state: h e l l o
     *                L         R
     * 
     * Step 1: Swap h and o
     *         o e l l h
     *         L       R
     *         left++, right--
     * 
     * Step 2: Swap e and l
     *         o l l e h
     *           L   R
     *           left++, right--
     * 
     * Step 3: left=2, right=2, left < right is false, stop
     * 
     * Output: "olleh"
     * 
     * Character-by-character trace for "abc":
     * Before: a b c
     *         0 1 2
     * Swap indices 0,2: c b a
     * Loop ends (1 < 1 is false)
     * After: "cba"
     * 
     * Time Complexity: O(n) where n is the length of the string
     *   - Traversing from both ends, each character visited once
     * 
     * Space Complexity: O(n)
     *   - Character array of size n is created
     * 
     * @param str String to reverse
     * @return Reversed string
     */
    public String reverseTheString(String str) {
        if(str == null || str.isEmpty()) return str;
        char arr[] = str.toCharArray();
        int left = 0, right = arr.length-1;

        while(left < right){
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
        return new String(arr);
    }

    /**
     * Problem: Reverse all words in a string individually (not their order).
     * 
     * This operation reverses each word in place while maintaining the original
     * positions of spaces. For example, "hello world" becomes "olleh dlrow".
     * This is different from reversing the order of words.
     * 
     * Algorithm: Character-by-Character Reversal with Boundaries
     * 1. Return if string is null or empty
     * 2. Convert string to character array for in-place manipulation
     * 3. Traverse the array, identifying word boundaries (spaces):
     *    - For each word (from start to space or end), reverse characters within that word
     * 4. Convert the modified array back to string and print
     * 
     * Helper method reverse(): Reverses characters between two indices in the array
     * 
     * Example:
     * Input: "hello world"
     * Process:
     *   Find word 1: indices 0-4 "hello" → reverse to "olleh"
     *   Skip space at index 5
     *   Find word 2: indices 6-10 "world" → reverse to "dlrow"
     * Output: "olleh dlrow"
     * 
     * Character-by-character trace for "hi bye":
     * Before: h i   b y e
     *         0 1 2 3 4 5
     * 
     * Word 1 (0-1): Reverse 'h' and 'i'
     *         i h   b y e
     * 
     * Word 2 (3-5): Reverse 'b','y','e'
     *         i h   e y b
     * 
     * After: "ih eyb"
     * 
     * Time Complexity: O(n) where n is the length of the string
     *   - Single pass to identify boundaries
     *   - Each character reversed exactly once across all words
     * 
     * Space Complexity: O(n)
     *   - Character array of size n
     * 
     * @param str String containing words to reverse individually
     */
    static void revWordsString(String str) {
        if(str == null || str.isEmpty()) {
            System.out.println(str);
            return;
        }
        char arr[] = str.toCharArray();
        int start = 0;

        for(int i=0; i<=arr.length; i++){
            if(i == arr.length || arr[i] == ' '){
                reverse(arr , start , i-1);
                start = i+1;
            }
        }
        String result = new String(arr);
        System.out.println(result);
    }

    /**
     * Helper method: Reverse characters in array between start and end indices.
     * 
     * Uses two-pointer technique to swap characters from outside moving inward.
     * This is a utility method used by revWordsString() to reverse individual words.
     * 
     * Algorithm: Two-Pointer Swap
     * 1. Initialize left pointer at start index, right pointer at end index
     * 2. While left < right:
     *    - Swap characters at left and right positions
     *    - Move left forward and right backward
     * 
     * Example (reversing indices 0-3 of "hello"):
     * Before: h e l l o
     *         L       R
     * After 1st swap: l e l h o
     *         L     R
     * After 2nd swap: l l e h o
     * (loop ends as left >= right)
     * 
     * Time Complexity: O(n) where n is the number of characters to reverse
     *   - Each character pair is visited once
     * 
     * Space Complexity: O(1)
     *   - Only uses two pointer variables
     * 
     * @param arr Character array to reverse within
     * @param start Starting index of the range to reverse
     * @param end Ending index of the range to reverse
     */
    private static void reverse(char arr[], int start, int end) {
        while(start < end){
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }

    /**
     * Problem: Generate all permutations of a string.
     * 
     * Permutations are all possible arrangements of the characters in a string.
     * For example, "ABC" has 6 permutations: ABC, ACB, BAC, BCA, CAB, CBA.
     * For a string of length n, there are n! permutations.
     * 
     * Algorithm: Recursive Backtracking with Character Swapping
     * 1. Base case: If left == right, we've reached the end of current permutation
     *    - Add this permutation to the result list
     * 2. Recursive case: For each position from left to right:
     *    - Swap character at position left with character at current position
     *    - Recursively permute the remaining substring (left+1 to right)
     *    - Swap back to restore original arrangement (backtrack)
     * 
     * Note: The swap operations rearrange characters to generate all unique combinations.
     * 
     * Example: Permutations of "AB"
     * permute("AB", 0, 1)
     *   i=0: swap(0,0)→"AB", permute("AB",1,1)→add "AB"
     *        swap back(0,0)→"AB"
     *   i=1: swap(0,1)→"BA", permute("BA",1,1)→add "BA"
     *        swap back(0,1)→"AB"
     * Result: ["AB", "BA"]
     * 
     * Permutations of "ABC":
     * Result: ["ABC", "ACB", "BAC", "BCA", "CAB", "CBA"]
     * 
     * Time Complexity: O(n * n!)
     *   - There are n! permutations
     *   - Each permutation requires O(n) time to copy/add to result
     *   - Total: O(n * n!)
     * 
     * Space Complexity: O(n * n!)
     *   - Storage for all n! permutations, each of length n
     *   - Recursive call stack depth: O(n)
     * 
     * @param str String to generate permutations for
     * @param l Left boundary index (typically 0 initially)
     * @param r Right boundary index (typically str.length()-1 initially)
     * @param perm ArrayList to store all generated permutations
     */
    void permute(String str, int l, int r, ArrayList<String> perm) {
        if(l == r) perm.add(str);
        else{
            for(int i=0; i<=r; i++){
                str = swap(str,l,r);
                permute(str,l+1,r,perm);
                str = swap(str,l,i);
            }
        }
    }

    /**
     * Helper method: Swap two characters in a string at given indices.
     * 
     * Since strings are immutable in Java, this method:
     * 1. Converts the string to a character array
     * 2. Swaps characters at indices i and j
     * 3. Converts the array back to a string
     * 
     * This is used by permute() to rearrange characters during backtracking.
     * 
     * Example:
     * Input: str="ABC", i=0, j=2
     * Process:
     *   chars = ['A','B','C']
     *   temp = 'A'
     *   chars[0] = 'C'
     *   chars[2] = 'A'
     *   chars = ['C','B','A']
     * Output: "CBA"
     * 
     * Time Complexity: O(n) where n is the length of the string
     *   - Converting to char array: O(n)
     *   - Swap operation: O(1)
     *   - Converting back to string: O(n)
     * 
     * Space Complexity: O(n)
     *   - Character array of size n
     * 
     * @param str String with characters to swap
     * @param i First index to swap
     * @param j Second index to swap
     * @return New string with characters at indices i and j swapped
     */
    private static String swap(String str, int i, int j) {
        char[] chars = str.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

    public static void main(String[] args) {
        String str = "chirag";
    }
}
