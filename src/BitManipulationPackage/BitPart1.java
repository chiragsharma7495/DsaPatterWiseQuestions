package BitManipulationPackage;

/**
 * Bit Manipulation Techniques and Problems
 * 
 * This class demonstrates fundamental bit manipulation operations and common problems
 * that use bitwise operations for efficient solutions.
 * 
 * Key Bit Operations:
 * - AND (&): Sets bit to 1 only if both bits are 1
 * - OR (|): Sets bit to 1 if at least one bit is 1
 * - XOR (^): Sets bit to 1 if bits are different
 * - NOT (~): Inverts all bits
 * - Left Shift (<<): Multiplies by 2^n
 * - Right Shift (>>): Divides by 2^n
 * 
 * Applications: Fast exponentiation, number manipulation, bit counting, subset generation
 */
public class BitPart1 {
    
    /**
     * Checks if a number is a power of 2
     * 
     * A number is a power of 2 if it has exactly one bit set.
     * Approach: A power of 2 AND (power of 2 - 1) always equals 0
     * 
     * Example: 8 (1000) is power of 2, because 8 & 7 = 1000 & 0111 = 0000 = 0
     *          6 (0110) is not power of 2, because 6 & 5 = 0110 & 0101 = 0100 ≠ 0
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param n - Positive integer to check
     * @return true if n is a power of 2, false otherwise
     */
    public static boolean isPowerOf2(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
    
    /**
     * Counts the number of 1 bits in binary representation (LeetCode 191)
     * 
     * Approach: Use n & (n-1) to turn off rightmost 1 bit repeatedly until n becomes 0
     * Each iteration removes one 1 bit, so count total iterations.
     * 
     * Example: 11 = 1011 (binary)
     *          1011 & 1010 = 1010 (1 iteration)
     *          1010 & 1001 = 1000 (2 iterations)
     *          1000 & 0111 = 0000 (3 iterations)
     *          Total 1 bits = 3
     * 
     * Time Complexity: O(k) where k is number of 1 bits
     * Space Complexity: O(1)
     * 
     * @param n - Unsigned integer
     * @return Number of 1 bits in binary representation
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);  // Removes rightmost 1 bit
            count++;
        }
        return count;
    }
    
    /**
     * Finds a single number in array where all others appear twice (LeetCode 136)
     * 
     * Using XOR: a ^ a = 0 and a ^ 0 = a
     * XOR all elements: duplicates cancel out, leaving single number
     * 
     * Example: [4, 1, 2, 1, 2]
     *          4 ^ 1 ^ 2 ^ 1 ^ 2 = 4 ^ (1^1) ^ (2^2) = 4 ^ 0 ^ 0 = 4
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param nums - Array where all elements appear twice except one
     * @return The single number that appears once
     */
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;  // XOR all elements
        }
        return result;
    }
    
    /**
     * Checks if two integers have opposite signs (LeetCode variant)
     * 
     * If signs are opposite, XOR of their MSB (after right shift) is 1
     * 
     * Example: 5 (0101) and -5 (1011 in 2's complement) have opposite signs
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param a - First integer
     * @param b - Second integer
     * @return true if a and b have opposite signs
     */
    public static boolean oppositeSign(int a, int b) {
        return ((a ^ b) < 0);
    }
    
    /**
     * Swaps two integers without using temporary variable
     * 
     * Using XOR properties: a ^ a = 0, a ^ 0 = a
     * 
     * Example: a = 5, b = 3
     *          a = 5 ^ 3 = 6
     *          b = 6 ^ 3 = 5
     *          a = 6 ^ 5 = 3
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param nums - Array with two elements to swap
     */
    public static void swapUsingXOR(int[] nums) {
        nums[0] = nums[0] ^ nums[1];
        nums[1] = nums[0] ^ nums[1];
        nums[0] = nums[0] ^ nums[1];
    }
    
    /**
     * Multiplies two numbers using bit shifting (for powers of 2 divisors)
     * 
     * Left shift by n is equivalent to multiplying by 2^n
     * Right shift by n is equivalent to dividing by 2^n
     * 
     * Example: 5 << 2 = 5 * 4 = 20
     *          5 >> 1 = 5 / 2 = 2
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param num - Number to shift
     * @param n - Number of positions to shift
     * @return num * 2^n
     */
    public static int multiplyByPowerOf2(int num, int n) {
        return num << n;
    }
    
    /**
     * Divides two numbers using bit shifting (for powers of 2)
     * 
     * Right shift by n is equivalent to dividing by 2^n (integer division)
     * 
     * Example: 20 >> 2 = 20 / 4 = 5
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param num - Number to divide
     * @param n - Shift amount (divide by 2^n)
     * @return num / 2^n (integer division)
     */
    public static int divideByPowerOf2(int num, int n) {
        return num >> n;
    }
    
    /**
     * Checks if ith bit is set in a number
     * 
     * Approach: Right shift number by i positions and check if LSB is 1
     * 
     * Example: Check if bit 2 is set in 5 (0101)
     *          5 >> 2 = 1 (0001)
     *          1 & 1 = 1 (bit is set)
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param num - Number to check
     * @param i - Bit position (0-indexed from right)
     * @return true if ith bit is set, false otherwise
     */
    public static boolean isBitSet(int num, int i) {
        return ((num >> i) & 1) == 1;
    }
    
    /**
     * Sets the ith bit in a number to 1
     * 
     * Approach: OR the number with (1 << i)
     * 
     * Example: Set bit 1 in 5 (0101)
     *          5 | (1 << 1) = 0101 | 0010 = 0111 = 7
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param num - Number to modify
     * @param i - Bit position to set
     * @return Number with ith bit set to 1
     */
    public static int setBit(int num, int i) {
        return num | (1 << i);
    }
    
    /**
     * Clears (sets to 0) the ith bit in a number
     * 
     * Approach: AND the number with ~(1 << i)
     * 
     * Example: Clear bit 1 in 7 (0111)
     *          7 & ~(1 << 1) = 0111 & 1101 = 0101 = 5
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param num - Number to modify
     * @param i - Bit position to clear
     * @return Number with ith bit set to 0
     */
    public static int clearBit(int num, int i) {
        return num & ~(1 << i);
    }
    
    /**
     * Toggles (flips) the ith bit in a number
     * 
     * Approach: XOR the number with (1 << i)
     * 
     * Example: Toggle bit 1 in 5 (0101)
     *          5 ^ (1 << 1) = 0101 ^ 0010 = 0111 = 7
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param num - Number to modify
     * @param i - Bit position to toggle
     * @return Number with ith bit toggled
     */
    public static int toggleBit(int num, int i) {
        return num ^ (1 << i);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Bit Manipulation Examples ===\n");
        
        // Example 1: Power of 2
        System.out.println("Example 1: Power of 2");
        System.out.println("isPowerOf2(8) = " + isPowerOf2(8));   // true
        System.out.println("isPowerOf2(6) = " + isPowerOf2(6));   // false
        System.out.println();
        
        // Example 2: Hamming Weight
        System.out.println("Example 2: Count 1 Bits");
        System.out.println("hammingWeight(11) = " + hammingWeight(11));  // 3 (1011 has 3 ones)
        System.out.println("hammingWeight(128) = " + hammingWeight(128)); // 1 (10000000 has 1 one)
        System.out.println();
        
        // Example 3: Single Number
        System.out.println("Example 3: Single Number (XOR)");
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println("Array: [4, 1, 2, 1, 2]");
        System.out.println("Single number: " + singleNumber(nums)); // 4
        System.out.println();
        
        // Example 4: Bit Operations
        System.out.println("Example 4: Bit Operations");
        System.out.println("Multiply 5 by 2^2 (5 << 2): " + multiplyByPowerOf2(5, 2));  // 20
        System.out.println("Divide 20 by 2^2 (20 >> 2): " + divideByPowerOf2(20, 2));    // 5
        System.out.println();
        
        // Example 5: Check/Set/Clear Bits
        System.out.println("Example 5: Bit Manipulation");
        int num = 5;  // 0101
        System.out.println("Number: " + num + " (binary: 0101)");
        System.out.println("Is bit 1 set? " + isBitSet(num, 1));  // true
        System.out.println("Set bit 1: " + setBit(num, 1));       // 7
        System.out.println("Clear bit 2: " + clearBit(num, 2));   // 1
        System.out.println("Toggle bit 0: " + toggleBit(num, 0)); // 4
    }
}
