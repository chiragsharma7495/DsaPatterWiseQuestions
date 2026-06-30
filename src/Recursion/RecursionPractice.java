package Recursion;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Recursion Practice - Comprehensive Collection of Recursive Algorithms
 * 
 * This class contains various recursive algorithms demonstrating fundamental recursion concepts:
 * 
 * KEY CONCEPTS:
 * - Base Case: The condition that stops recursion (prevents infinite loop)
 * - Recursive Case: The function calling itself with modified parameters, moving towards base case
 * - Call Stack: Each function call is pushed onto stack; returns pop from stack
 * - Recursion Depth: Maximum number of nested calls; limited by stack size
 * 
 * WHY RECURSION:
 * - Elegant solution for naturally recursive problems (tree traversal, backtracking)
 * - Divides problem into smaller subproblems of same type
 * - Code often more intuitive than iterative approach
 * 
 * WHEN TO AVOID:
 * - Deep recursion causes stack overflow
 * - Often slower than iteration due to function call overhead
 * - Difficult to debug due to call stack complexity
 * 
 * OPTIMIZATION TECHNIQUES:
 * - Memoization: Cache results of subproblems (dynamic programming)
 * - Tail Recursion: Last statement is recursive call (compiler optimization in some languages)
 * - Fast Exponentiation: Use divide-and-conquer to reduce exponential time to logarithmic
 */
public class RecursionPractice {

    /**
     * Print Numbers 1 to N (Increasing Order)
     * 
     * PROBLEM: Print integers from 1 to N in increasing order using recursion.
     * 
     * RECURSIVE APPROACH:
     * - Base Case: If n == 0, return (stop recursion)
     * - Recursive Case: First recursively call Print1toN(n-1), then print n
     * - Key Insight: By making recursive call first, then printing, we defer printing until unwinding stack
     * 
     * EXECUTION TRACE for n=3:
     * Print1toN(3)
     * ├─ Print1toN(2)
     * │  ├─ Print1toN(1)
     * │  │  ├─ Print1toN(0)
     * │  │  │  └─ [BASE CASE - return]
     * │  │  └─ print 1        [unwinding]
     * │  └─ print 2           [unwinding]
     * └─ print 3              [unwinding]
     * Output: 1 2 3
     * 
     * Example: Print1toN(4)
     * Expected Output: 1 2 3 4
     * 
     * Time Complexity: O(n) - makes n recursive calls
     * Space Complexity: O(n) - call stack depth is n
     * 
     * @param n - Non-negative integer representing upper limit (inclusive)
     */
    public static void Print1toN(int n){
        if(n == 0) return;
        Print1toN(n-1);
        System.out.println(n);
    }

    /**
     * Print Numbers N to 1 (Decreasing Order)
     * 
     * PROBLEM: Print integers from N to 1 in decreasing order using recursion.
     * 
     * RECURSIVE APPROACH:
     * - Base Case: If n == 0, return (stop recursion)
     * - Recursive Case: First print n, then recursively call PrintNto1(n-1)
     * - Key Insight: By printing first, then making recursive call, we print on the way down (not unwinding)
     * 
     * EXECUTION TRACE for n=3:
     * PrintNto1(3)
     * ├─ print 3           [on the way down]
     * ├─ PrintNto1(2)
     * │  ├─ print 2        [on the way down]
     * │  ├─ PrintNto1(1)
     * │  │  ├─ print 1     [on the way down]
     * │  │  ├─ PrintNto1(0)
     * │  │  │  └─ [BASE CASE - return]
     * │  │  └─ [back to PrintNto1(1)]
     * │  └─ [back to PrintNto1(2)]
     * └─ [back to PrintNto1(3)]
     * Output: 3 2 1
     * 
     * Example: PrintNto1(4)
     * Expected Output: 4 3 2 1
     * 
     * Time Complexity: O(n) - makes n recursive calls
     * Space Complexity: O(n) - call stack depth is n
     * 
     * @param n - Non-negative integer representing starting value
     */
    public static void PrintNto1(int n){
        if(n == 0)return;
        System.out.println(n);
        PrintNto1(n-1);
    }

    /**
     * Increasing Then Decreasing Pattern
     * 
     * PROBLEM: Print numbers 1 to N on the way down the recursion stack, then N to 1 on the way up.
     * Result: N (N-1) ... 2 1 2 ... (N-1) N
     * 
     * RECURSIVE APPROACH:
     * - Base Case: If n == 0, return (stop recursion)
     * - Recursive Case: 
     *   1. Print n (on the way down)
     *   2. Recursively call IncreasingDecreasing(n-1)
     *   3. Print n (on the way up/unwinding)
     * - Key Insight: Statements before recursive call execute going down; statements after execute unwinding
     * 
     * EXECUTION TRACE for n=3:
     * IncreasingDecreasing(3)
     * ├─ print 3           [going down - depth 3]
     * ├─ IncreasingDecreasing(2)
     * │  ├─ print 2        [going down - depth 2]
     * │  ├─ IncreasingDecreasing(1)
     * │  │  ├─ print 1     [going down - depth 1]
     * │  │  ├─ IncreasingDecreasing(0)
     * │  │  │  └─ [BASE CASE - return]
     * │  │  └─ print 1     [unwinding - depth 1]
     * │  └─ print 2        [unwinding - depth 2]
     * └─ print 3           [unwinding - depth 3]
     * Output: 3 2 1 1 2 3
     * 
     * Example: IncreasingDecreasing(3)
     * Expected Output: 3 2 1 1 2 3
     * 
     * Time Complexity: O(n) - makes n recursive calls, each with constant work
     * Space Complexity: O(n) - call stack depth is n
     * 
     * @param n - Non-negative integer representing starting value
     */
    public static void IncreasingDecreasing(int n){
        if(n == 0) return;
        System.out.println(n + " ");
        IncreasingDecreasing(n-1);
        System.out.println(n + " ");
    }

    /**
     * Calculate Factorial Using Recursion
     * 
     * PROBLEM: Calculate n! (n factorial) = n × (n-1) × (n-2) × ... × 1
     * Special case: 0! = 1
     * 
     * RECURSIVE APPROACH:
     * - Base Case: If n ≤ 1, return 1
     * - Recursive Case: Return n × Factorial(n-1)
     * - Key Insight: n! = n × (n-1)!
     * 
     * EXECUTION TRACE for n=5:
     * Factorial(5)
     * ├─ 5 × Factorial(4)
     * │  ├─ 4 × Factorial(3)
     * │  │  ├─ 3 × Factorial(2)
     * │  │  │  ├─ 2 × Factorial(1)
     * │  │  │  │  └─ [BASE CASE: return 1]
     * │  │  │  └─ return 2 × 1 = 2
     * │  │  └─ return 3 × 2 = 6
     * │  └─ return 4 × 6 = 24
     * └─ return 5 × 24 = 120
     * 
     * Examples:
     * Factorial(0) → 1
     * Factorial(1) → 1
     * Factorial(5) → 120
     * Factorial(6) → 720
     * 
     * Time Complexity: O(n) - makes n recursive calls
     * Space Complexity: O(n) - call stack depth is n
     * 
     * NOTE: Integer overflow occurs for n > 20 (use long for larger values)
     * 
     * @param n - Non-negative integer
     * @return Factorial of n (n!)
     */
    static int Factorial(int n){
        if(n <= 1) return 1;
        int ans = n*Factorial(n-1);
        return ans;
    }

    /**
     * Calculate Power - Linear Exponentiation
     * 
     * PROBLEM: Calculate a^b (a to the power b) using recursion.
     * 
     * RECURSIVE APPROACH:
     * - Base Case: If b == 0, return 1 (any number to power 0 is 1)
     * - Recursive Case: Return a × Power(a, b-1)
     * - Key Insight: a^b = a × a^(b-1)
     * - Drawback: Linear time complexity O(b), inefficient for large exponents
     * 
     * EXECUTION TRACE for a=2, b=4:
     * Power(2, 4)
     * ├─ 2 × Power(2, 3)
     * │  ├─ 2 × Power(2, 2)
     * │  │  ├─ 2 × Power(2, 1)
     * │  │  │  ├─ 2 × Power(2, 0)
     * │  │  │  │  └─ [BASE CASE: return 1]
     * │  │  │  └─ return 2 × 1 = 2
     * │  │  └─ return 2 × 2 = 4
     * │  └─ return 2 × 4 = 8
     * └─ return 2 × 8 = 16
     * 
     * Examples:
     * Power(2, 3) → 8
     * Power(3, 4) → 81
     * Power(5, 0) → 1
     * 
     * Time Complexity: O(b) - makes b recursive calls
     * Space Complexity: O(b) - call stack depth is b
     * 
     * @param a - Base number
     * @param b - Exponent (power)
     * @return a raised to the power b (a^b)
     */
    static int Power(int a , int b){
        if(b == 0) return 1;
        return a*Power(a,b-1);
    }

    /**
     * Calculate Power - Optimized Using Divide and Conquer (Fast Exponentiation)
     * 
     * PROBLEM: Calculate a^b efficiently using the fast exponentiation algorithm.
     * 
     * RECURSIVE APPROACH:
     * - Base Case: If b == 0, return 1
     * - Divide and Conquer:
     *   1. Calculate ans = power2(a, b/2)
     *   2. If b is even: a^b = ans × ans
     *   3. If b is odd: a^b = a × ans × ans
     * - Key Insight: Reduces exponential time from O(b) to O(log b)
     * 
     * MATHEMATICAL PRINCIPLE:
     * - a^8 = (a^4)^2
     * - a^7 = a × (a^3)^2
     * - By repeatedly halving the exponent, we reduce exponential work
     * 
     * EXECUTION TRACE for a=2, b=6:
     * power2(2, 6)
     * ├─ ans = power2(2, 3)
     * │  ├─ ans = power2(2, 1)
     * │  │  ├─ ans = power2(2, 0)
     * │  │  │  └─ [BASE CASE: return 1]
     * │  │  └─ b is odd: return 2 × 1 × 1 = 2
     * │  └─ b is odd: return 2 × 2 × 2 = 8
     * └─ b is even: return 8 × 8 = 64
     * Result: 64
     * 
     * Examples:
     * power2(2, 4) → 16
     * power2(3, 5) → 243
     * power2(2, 10) → 1024
     * 
     * Time Complexity: O(log b) - exponent halved each recursion
     * Space Complexity: O(log b) - call stack depth is O(log b)
     * 
     * @param a - Base number
     * @param b - Exponent (power)
     * @return a raised to the power b (a^b)
     */
    static int power2(int a , int b){
        if(b == 0) return 1;
        int ans = power2(a,b/2);
        if(b % 2 == 0) return ans*ans;
        else{
            return a*ans*ans;
        }
    }

    /**
     * LeetCode 50 - Pow(x, n)
     * 
     * PROBLEM: Implement x^n (x to the power n) with support for negative exponents and handle edge cases.
     * Must handle negative exponents and integer overflow for n = Integer.MIN_VALUE.
     * 
     * APPROACH:
     * - Convert exponent to long to safely handle Integer.MIN_VALUE
     * - Handle negative exponents by converting to positive: x^(-n) = (1/x)^n
     * - Use fastPow() with fast exponentiation for efficiency
     * 
     * EXECUTION TRACE for x=2.0, n=-3:
     * myPow(2.0, -3)
     * ├─ exp = -3 (as long)
     * ├─ Since exp < 0: x = 1.0/2.0 = 0.5, exp = 3
     * └─ return fastPow(0.5, 3) = 0.125
     * 
     * Examples:
     * myPow(2.0, 10) → 1024.0
     * myPow(2.1, 3) → 9.261
     * myPow(2.0, -2) → 0.25
     * myPow(2.0, Integer.MIN_VALUE) → handles correctly using long
     * 
     * Time Complexity: O(log n) - fast exponentiation
     * Space Complexity: O(log n) - recursion depth in fastPow()
     * 
     * @param x - Base (double value, can be negative, zero, or fractional)
     * @param n - Exponent (integer, can be negative)
     * @return x raised to the power n
     */
    public static double myPow(double x, int n) {
        long exp = n;           // promote to long

        if (exp < 0) {
            x = 1.0 / x;
            exp = -exp;        // safe now, because exp is long
        }

        return fastPow(x, exp);
    }

    /**
     * Fast Exponentiation - Core Recursive Algorithm
     * 
     * PROBLEM: Calculate x^n efficiently using divide-and-conquer.
     * Helper method for myPow() that performs the actual fast exponentiation.
     * 
     * RECURSIVE APPROACH:
     * - Base Case: If n == 0, return 1.0
     * - Divide and Conquer:
     *   1. Calculate half = fastPow(x, n/2)
     *   2. result = half × half (= x^n if n is even)
     *   3. If n is odd: result × x (to account for the odd power)
     * - Key Insight: Reduces exponential problem to logarithmic depth
     * 
     * MATHEMATICAL EXPLANATION:
     * - x^6 = (x^3)^2
     * - x^7 = x × (x^3)^2
     * - x^10 = (x^5)^2
     * 
     * EXECUTION TRACE for x=2.0, n=5:
     * fastPow(2.0, 5)
     * ├─ half = fastPow(2.0, 2)
     * │  ├─ half = fastPow(2.0, 1)
     * │  │  ├─ half = fastPow(2.0, 0)
     * │  │  │  └─ [BASE CASE: return 1.0]
     * │  │  └─ n=1 is odd: result = 1.0 × 2.0 = 2.0
     * │  └─ n=2 is even: result = 2.0 × 2.0 = 4.0
     * └─ n=5 is odd: result = 4.0 × 4.0 × 2.0 = 32.0
     * Result: 32.0 (2^5)
     * 
     * Examples:
     * fastPow(2.0, 6) → 64.0
     * fastPow(3.0, 4) → 81.0
     * fastPow(0.5, 2) → 0.25
     * 
     * Time Complexity: O(log n) - n halved in each recursive call
     * Space Complexity: O(log n) - recursion call stack depth
     * 
     * @param x - Base value (double)
     * @param n - Exponent (non-negative long)
     * @return x raised to the power n
     */
    private static double fastPow(double x, long n) {
        if (n == 0) return 1.0;

        double half = fastPow(x, n / 2);
        double result = half * half;

        if (n % 2 == 1) result *= x;
        return result;
    }

    /**
     * Reverse a Number Using Iteration
     * 
     * PROBLEM: Reverse the digits of a given integer.
     * NOTE: This implementation uses iteration, not recursion.
     * 
     * ALGORITHM (Iterative Approach):
     * - Extract last digit: digit = n % 10
     * - Shift reversed number left by 1 position and add digit: rev = rev × 10 + digit
     * - Remove last digit from original: n = n / 10
     * - Repeat until n becomes 0
     * 
     * STEP-BY-STEP TRACE for n=1234:
     * Iteration 1: digit = 4, rev = 0 × 10 + 4 = 4, n = 123
     * Iteration 2: digit = 3, rev = 4 × 10 + 3 = 43, n = 12
     * Iteration 3: digit = 2, rev = 43 × 10 + 2 = 432, n = 1
     * Iteration 4: digit = 1, rev = 432 × 10 + 1 = 4321, n = 0
     * Return: 4321
     * 
     * Examples:
     * ReverseNumber(123) → 321
     * ReverseNumber(100) → 1
     * ReverseNumber(12340) → 4321
     * 
     * Edge Cases:
     * - Positive integers: Works correctly
     * - Sign: Doesn't preserve negative sign (returns positive)
     * - Trailing zeros: Lost after reversal (123400 → 4321)
     * 
     * Time Complexity: O(log n) - number of digits in n
     * Space Complexity: O(1) - uses only constant extra space
     * 
     * @param n - Integer to be reversed
     * @return Reversed integer
     */
    public static int ReverseNumber(int n){
        int rev = 0;
        while(n > 0){
            int digit = n % 10;           // take the last digit of n.
            rev = rev * 10 + digit;       // shift rev left (add a 0) and add the new digit at the end.
            n = n / 10;                   // remove the last digit from n.
        }
        return rev;
    }

    /**
     * Fast Exponentiation - Integer Version with Long Intermediate
     * 
     * PROBLEM: Calculate base^expo efficiently using divide-and-conquer.
     * Similar to power2() but handles larger results by using long during computation.
     * 
     * RECURSIVE APPROACH:
     * - Base Case: If expo == 0, return 1
     * - Divide and Conquer:
     *   1. Calculate half = poweer(base, expo/2)
     *   2. result = half × half
     *   3. If expo is odd: result × base
     *   4. Cast result back to int for return
     * - Key Insight: Using long prevents intermediate overflow during multiplication
     * 
     * EXECUTION TRACE for base=3, expo=5:
     * poweer(3, 5)
     * ├─ half = poweer(3, 2)
     * │  ├─ half = poweer(3, 1)
     * │  │  ├─ half = poweer(3, 0)
     * │  │  │  └─ [BASE CASE: return 1]
     * │  │  └─ expo=1 is odd: result = 1 × 1 × 3 = 3
     * │  └─ expo=2 is even: result = 3 × 3 = 9
     * └─ expo=5 is odd: result = 9 × 9 × 3 = 243
     * Result: 243 (3^5)
     * 
     * Examples:
     * poweer(2, 10) → 1024
     * poweer(3, 4) → 81
     * poweer(5, 3) → 125
     * 
     * Time Complexity: O(log expo) - exponent halved each recursion
     * Space Complexity: O(log expo) - recursion call stack depth
     * 
     * @param base - Base number
     * @param expo - Exponent
     * @return base raised to the power expo
     */
    static int poweer(int base , int expo){
        if(expo == 0)return 1;

        long half = poweer(base,expo/2);
        long result = half*half;
        if(expo % 2 == 1) result *= base;
        return (int) result;
    }

    /**
     * Reverse a Number Using Exponentiation
     * 
     * PROBLEM: Calculate base^(reverse of n).
     * This combines two operations: reverse the number and then use it as exponent.
     * 
     * ALGORITHM:
     * - Step 1: Reverse the digits of n using ReverseNumber()
     * - Step 2: Calculate n^(reversed number) using poweer() method
     * 
     * EXECUTION TRACE for n=23:
     * reverseExponentiation(23)
     * ├─ rev = ReverseNumber(23) = 32
     * └─ return poweer(23, 32)
     * Returns: 23^32 (very large number)
     * 
     * Examples:
     * reverseExponentiation(12) → 12^21 (huge number)
     * reverseExponentiation(10) → 10^1 = 10
     * reverseExponentiation(2) → 2^2 = 4
     * 
     * Edge Cases:
     * - Numbers ending in zero: Reverse loses the zero
     *   reverseExponentiation(120) → 120^21 (reverse of 120 is 21)
     * - Single digit: n → n^n (reverseExponentiation(5) → 5^5)
     * - Result can be extremely large for moderate input sizes
     * 
     * Time Complexity: O(log n + log reversed_n) ≈ O(log n)
     * Space Complexity: O(log n) - recursion depth in poweer()
     * 
     * @param n - Integer whose reverse will be used as exponent with n as base
     * @return n raised to the power (reverse of n)
     */
    public static int reverseExponentiation(int n) {
        int rev = ReverseNumber(n);
        return poweer(n,rev);
    }

    /**
     * Sum of First N Natural Numbers
     * 
     * PROBLEM: Calculate the sum 1 + 2 + 3 + ... + n using recursion.
     * Mathematical formula: Sum = n × (n + 1) / 2
     * 
     * RECURSIVE APPROACH:
     * - Base Case: If n == 1, return 1
     * - Recursive Case: Return n + findSum(n-1)
     * - Key Insight: Sum(n) = n + Sum(n-1)
     * 
     * EXECUTION TRACE for n=5:
     * findSum(5)
     * ├─ 5 + findSum(4)
     * │  ├─ 4 + findSum(3)
     * │  │  ├─ 3 + findSum(2)
     * │  │  │  ├─ 2 + findSum(1)
     * │  │  │  │  └─ [BASE CASE: return 1]
     * │  │  │  └─ return 2 + 1 = 3
     * │  │  └─ return 3 + 3 = 6
     * │  └─ return 4 + 6 = 10
     * └─ return 5 + 10 = 15
     * Result: 15 = 1+2+3+4+5
     * 
     * Examples:
     * findSum(1) → 1
     * findSum(3) → 6 (1+2+3)
     * findSum(10) → 55
     * findSum(100) → 5050
     * 
     * Time Complexity: O(n) - makes n recursive calls
     * Space Complexity: O(n) - call stack depth is n
     * 
     * Alternative Closed-Form Solution: n × (n + 1) / 2 [O(1) time]
     * 
     * @param n - Positive integer
     * @return Sum of all integers from 1 to n
     */
    public static int findSum(int n) {
        // code here
        if( n == 1) return 1;
        return n + findSum(n-1);
    }

    /**
     * Nth Fibonacci Number
     * 
     * PROBLEM: Find the nth number in the Fibonacci sequence where each number is sum of previous two.
     * Sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
     * 
     * RECURSIVE APPROACH:
     * - Base Case: If n == 0 or n == 1, return n
     * - Recursive Case: Return nthFibonacci(n-1) + nthFibonacci(n-2)
     * - Key Insight: fib(n) = fib(n-1) + fib(n-2)
     * - Drawback: EXPONENTIAL TIME O(2^n) due to massive overlapping subproblems
     *   Each node has 2 children, creating tree of exponential size
     * 
     * EXECUTION TRACE for n=5:
     * nthFibonacci(5)
     * ├─ nthFibonacci(4)
     * │  ├─ nthFibonacci(3)
     * │  │  ├─ nthFibonacci(2)
     * │  │  │  ├─ nthFibonacci(1) → 1
     * │  │  │  └─ nthFibonacci(0) → 0
     * │  │  └─ nthFibonacci(1) → 1
     * │  └─ nthFibonacci(2)
     * │     ├─ nthFibonacci(1) → 1
     * │     └─ nthFibonacci(0) → 0
     * └─ nthFibonacci(3)
     *    ├─ nthFibonacci(2)
     *    │  ├─ nthFibonacci(1) → 1
     *    │  └─ nthFibonacci(0) → 0
     *    └─ nthFibonacci(1) → 1
     * Result: 1+1+1 = 5
     * 
     * Examples:
     * nthFibonacci(0) → 0
     * nthFibonacci(1) → 1
     * nthFibonacci(5) → 5
     * nthFibonacci(10) → 55
     * 
     * WARNING: VERY SLOW for n > 30 due to exponential complexity!
     * 
     * Time Complexity: O(2^n) - exponential, highly inefficient
     * Space Complexity: O(n) - recursion call stack depth
     * 
     * OPTIMIZATION: Use memoization (see climpStairs/ways methods) for O(n) solution
     * 
     * @param n - Index of Fibonacci number (0-indexed)
     * @return The nth Fibonacci number
     */
    public int nthFibonacci(int n) {
        // code here
        if( n == 0 || n == 1) return n;
        return nthFibonacci(n-1) + nthFibonacci(n-2);
    }

    /**
     * Greatest Common Divisor (GCD) Using Euclidean Algorithm
     * 
     * PROBLEM: Find the greatest common divisor of two integers a and b.
     * GCD is the largest positive integer that divides both numbers without remainder.
     * 
     * RECURSIVE APPROACH (Euclidean Algorithm):
     * - Base Case: If a == 0, return b (gcd(0, b) = b)
     * - Recursive Case: Return gcd(b % a, a)
     * - Key Insight: gcd(a, b) = gcd(b mod a, a) reduces problem size rapidly
     * 
     * MATHEMATICAL PRINCIPLE:
     * - gcd(48, 18):
     *   48 % 18 = 12, so gcd(48, 18) = gcd(12, 18)
     *   18 % 12 = 6, so gcd(12, 18) = gcd(6, 12)
     *   12 % 6 = 0, so gcd(6, 12) = gcd(0, 6) = 6
     * - Result: 6 is GCD of 48 and 18
     * 
     * EXECUTION TRACE for a=48, b=18:
     * gcd(48, 18)
     * ├─ return gcd(18 % 48, 48) = gcd(18, 48)
     * └─ return gcd(48 % 18, 18) = gcd(12, 18)
     *    └─ return gcd(18 % 12, 12) = gcd(6, 12)
     *       └─ return gcd(12 % 6, 6) = gcd(0, 6)
     *          └─ [BASE CASE: return 6]
     * Result: 6
     * 
     * Examples:
     * gcd(48, 18) → 6
     * gcd(100, 50) → 50
     * gcd(17, 19) → 1 (coprime)
     * gcd(12, 8) → 4
     * 
     * Time Complexity: O(log(min(a, b))) - Euclidean algorithm is very efficient
     * Space Complexity: O(log(min(a, b))) - recursion depth is logarithmic
     * 
     * @param a - First integer
     * @param b - Second integer
     * @return Greatest common divisor of a and b
     */
    public static int gcd(int a, int b) {
        if(a == 0) return b;
        return gcd(b%a , a);
    }

    /**
     * Climb Stairs - Number of Ways to Reach the Top
     * 
     * PROBLEM: You can climb 1 or 2 steps at a time. How many distinct ways can you climb n stairs?
     * Similar to Fibonacci but with context: at each step, you can either climb 1 or 2 stairs.
     * 
     * RECURSIVE APPROACH WITH MEMOIZATION:
     * - Base Case: If n == 0 or n == 1, return 1 (one way: take all 1-steps)
     * - Memoization Check: If memo[n] is already computed, return it
     * - Recursive Case: memo[n] = ways(n-1) + ways(n-2)
     *   (reach stair n from n-1 by taking 1 step, or from n-2 by taking 2 steps)
     * - Key Insight: Dynamic programming reduces exponential O(2^n) to linear O(n)
     * 
     * STEP COMBINATIONS for n=4:
     * - 1+1+1+1 (four 1-steps)
     * - 1+1+2 (two 1-steps, one 2-step)
     * - 1+2+1 (1-step, 2-step, 1-step)
     * - 2+1+1 (2-step, two 1-steps)
     * - 2+2 (two 2-steps)
     * Total: 5 ways
     * 
     * EXECUTION TRACE for n=4 (with memoization):
     * climpStairs(4)
     * ├─ ways(4, memo)
     * │  ├─ ways(3, memo)
     * │  │  ├─ ways(2, memo)
     * │  │  │  ├─ ways(1, memo) → 1
     * │  │  │  └─ ways(0, memo) → 1
     * │  │  │  → memo[2] = 2
     * │  │  └─ ways(1, memo) → 1
     * │  │  → memo[3] = 3
     * │  ├─ ways(2, memo) → memo[2] = 2 (cached!)
     * │  → memo[4] = 5
     * 
     * Examples:
     * climpStairs(1) → 1
     * climpStairs(2) → 2 (1+1 or 2)
     * climpStairs(3) → 3 (1+1+1, 1+2, 2+1)
     * climpStairs(4) → 5
     * climpStairs(5) → 8
     * 
     * Time Complexity: O(n) - each subproblem computed once with memoization
     * Space Complexity: O(n) - memo array of size n, recursion depth is n
     * 
     * NOTE: Initializing memo array with n-1 is suspicious; should verify this logic
     * 
     * @param n - Number of stairs to climb
     * @return Number of distinct ways to climb n stairs
     */
    public static int climpStairs(int n){
        int[] memo = new int[n+1];
        Arrays.fill(memo,n-1);
        return ways(n,memo);
    }

    /**
     * Helper Function for Climb Stairs - Recursive Computation with Memoization
     * 
     * PROBLEM: Compute the number of ways to climb n stairs using dynamic programming.
     * This is the core recursive function called by climpStairs().
     * 
     * RECURSIVE APPROACH WITH MEMOIZATION:
     * - Base Case: If n == 0 or n == 1, return 1
     * - Memoization Check: If memo[n] != -1, return cached result
     * - Recursive Case: memo[n] = ways(n-1, memo) + ways(n-2, memo)
     *   Then return memo[n]
     * - Key Insight: Cache prevents recalculating same subproblems
     * 
     * MEMOIZATION TRACE for n=5:
     * ways(5, memo)
     * ├─ ways(4, memo)
     * │  ├─ ways(3, memo)
     * │  │  ├─ ways(2, memo)
     * │  │  │  ├─ ways(1, memo) → 1
     * │  │  │  └─ ways(0, memo) → 1
     * │  │  │  → memo[2] = 2
     * │  │  └─ ways(1, memo) → 1
     * │  │  → memo[3] = 3
     * │  ├─ ways(2, memo) → memo[2] = 2 [CACHE HIT - no recursion!]
     * │  → memo[4] = 5
     * └─ ways(3, memo) → memo[3] = 3 [CACHE HIT]
     * → memo[5] = 8
     * 
     * Cache Hits: Total function calls reduced from O(2^n) to O(n)
     * 
     * Time Complexity: O(n) - each of n subproblems solved once
     * Space Complexity: O(n) - memo array stores n results
     * 
     * @param n - Current stair number
     * @param memo - Memoization array where memo[i] stores result for stairs(i)
     * @return Number of ways to climb n stairs
     */
    static int ways(int n , int[] memo){
        if(n == 0 || n == 1) return 1;
        if(memo[n] != -1) return memo[n];

        memo[n] = ways(n-1, memo) + ways(n-2 , memo);
        return memo[n];
    }

    /**
     * Search for Element in Array Using Recursion
     * 
     * PROBLEM: Search for a given element in an array using linear recursion.
     * Check if element exists at any position in the array.
     * 
     * RECURSIVE APPROACH:
     * - Base Case: If idx == arr.length - 1, return false (reached end without finding)
     * - Check Current: If arr[idx] == ele, return true (found!)
     * - Recursive Case: Return findEle(arr, ele, idx+1) (search next position)
     * - Key Insight: Linear search through array, one element per recursive call
     * 
     * EXECUTION TRACE for arr=[10, 20, 30, 40], ele=30, starting idx=0:
     * findEle(arr, 30, 0)
     * ├─ arr[0] = 10 ≠ 30
     * ├─ findEle(arr, 30, 1)
     * │  ├─ arr[1] = 20 ≠ 30
     * │  ├─ findEle(arr, 30, 2)
     * │  │  ├─ arr[2] = 30 == 30 → TRUE
     * │  │  → return true
     * │  → return true
     * → return true
     * Result: true (found 30 at index 2)
     * 
     * Examples:
     * findEle([5, 10, 15, 20], 15, 0) → true
     * findEle([5, 10, 15, 20], 25, 0) → false
     * findEle([1], 1, 0) → true
     * findEle([1, 2, 3], 3, 0) → true
     * 
     * Edge Cases:
     * - Single element: Works correctly
     * - Element at beginning: Takes 1 call
     * - Element at end: Takes n calls
     * - Element not found: Takes n calls
     * 
     * Time Complexity: O(n) - may check all n elements in worst case
     * Space Complexity: O(n) - recursion call stack depth is up to n
     * 
     * @param arr - Array to search in
     * @param ele - Element to find
     * @param idx - Current index (start with 0)
     * @return true if element found, false otherwise
     */
    static boolean findEle(int arr[] , int ele, int idx){
        if(idx == arr.length-1 ) return false;
        if(arr[idx] == ele) return true;
        return findEle(arr,ele,idx+1);
    }

    public static void main(String[] args) {

    }
}
