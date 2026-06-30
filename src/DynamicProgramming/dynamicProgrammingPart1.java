package DynamicProgramming;

import java.util.Arrays;

/**
 * Dynamic Programming Solutions - Part 1
 * 
 * <h2>What is Dynamic Programming?</h2>
 * Dynamic Programming (DP) is an optimization technique for solving complex problems by
 * breaking them down into overlapping subproblems and storing the results of subproblems
 * to avoid redundant computations.
 * 
 * <h2>Key Characteristics of DP Problems:</h2>
 * <ul>
 *   <li><b>Overlapping Subproblems:</b> The same subproblems are solved multiple times.
 *       DP avoids recalculating these by storing results in a cache (memoization) or
 *       building up from base cases (tabulation).</li>
 *   <li><b>Optimal Substructure:</b> The optimal solution to a problem can be constructed
 *       from optimal solutions of its subproblems.</li>
 * </ul>
 * 
 * <h2>Two DP Approaches:</h2>
 * 
 * <h3>1. Memoization (Top-Down)</h3>
 * <ul>
 *   <li>Starts with the main problem and recursively breaks it into smaller subproblems</li>
 *   <li>Caches results in a data structure (array/HashMap) to avoid recomputation</li>
 *   <li>Easier to think about and code (follows natural recursive logic)</li>
 *   <li>Example: {@link #nthFibonacci(int)}, {@link #findMaxSum(int[])} </li>
 *   <li><b>Time Complexity:</b> O(n) - Each state computed only once</li>
 *   <li><b>Space Complexity:</b> O(n) - Cache + O(n) recursion stack = O(n)</li>
 * </ul>
 * 
 * <h3>2. Tabulation (Bottom-Up)</h3>
 * <ul>
 *   <li>Starts from base cases and iteratively builds up solutions</li>
 *   <li>Fills a table systematically in a specific order</li>
 *   <li>Generally faster (no recursion overhead) and can be space-optimized</li>
 *   <li>Example: {@link #NthFibonacci1(int)}, {@link #NthFibonacci2(int)}</li>
 *   <li><b>Time Complexity:</b> O(n) - Fill each cell once</li>
 *   <li><b>Space Complexity:</b> O(n) - or O(1) with space optimization</li>
 * </ul>
 * 
 * <h2>DP vs Naive Recursion:</h2>
 * <table border="1">
 * <tr>
 *   <th>Fibonacci(5)</th>
 *   <th>Naive Recursion</th>
 *   <th>DP with Memoization</th>
 * </tr>
 * <tr>
 *   <td>Calls</td>
 *   <td>15 function calls</td>
 *   <td>5 function calls (each state once)</td>
 * </tr>
 * <tr>
 *   <td>Time</td>
 *   <td>O(2^n) - exponential</td>
 *   <td>O(n) - linear</td>
 * </tr>
 * <tr>
 *   <td>Examples</td>
 *   <td>fib(3) computed 2 times</td>
 *   <td>fib(3) computed 1 time and cached</td>
 * </tr>
 * </table>
 * 
 * <h2>When to Use DP:</h2>
 * <ul>
 *   <li>The problem exhibits overlapping subproblems (same inputs solved multiple times)</li>
 *   <li>The problem has optimal substructure (optimal solution uses optimal sub-solutions)</li>
 *   <li>Naive recursion is too slow due to exponential time complexity</li>
 *   <li>The problem involves optimization (maximize/minimize) or counting</li>
 * </ul>
 * 
 * <h2>Classes and Methods in This File:</h2>
 * <ul>
 *   <li><b>Fibonacci Sequence:</b> {@link #nthFibonacci(int)}, {@link #Fibonacci(int)},
 *       {@link #NthFibonacci1(int)}, {@link #NthFibonacci2(int)}</li>
 *   <li><b>House Robbery:</b> {@link #findMaxSum(int[])}, {@link #loot(int, int[])}</li>
 *   <li><b>Climbing Stairs:</b> {@link #minCostClimbingStairs(int[])}, {@link #minCostClimbingStairs2(int[])}</li>
 *   <li><b>Staircase Ways:</b> {@link #countWays(int)}, {@link #solve(int)}</li>
 *   <li><b>Grid Paths:</b> {@link #numberOfPaths(int, int)}, {@link #uniquePaths(int, int)},
 *       {@link #uniquePathsWithObstacles(int[][])}, {@link #minPathSum(int[][])}</li>
 *   <li><b>Knapsack Problem:</b> {@link #knapsack(int, int[], int[])}, {@link #knapSack(int[], int[], int)}</li>
 *   <li><b>Subset Sum:</b> {@link #subset(int[], int)}</li>
 *   <li><b>Maximal Square:</b> {@link #countSquares(int[][])}</li>
 * </ul>
 * 
 * @author DSA Problem Solver
 * @version 1.0
 */
public class dynamicProgrammingPart1 {

    static int dp[];
    
    /**
     * Calculates the nth Fibonacci number using memoization (top-down DP).
     * 
     * <h3>Problem Description:</h3>
     * The Fibonacci sequence is: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34...
     * where each number is the sum of the two preceding ones.
     * Find the nth number in this sequence.
     * 
     * <h3>DP Approach (Memoization):</h3>
     * <ul>
     *   <li><b>Strategy:</b> Top-down recursive approach with caching</li>
     *   <li><b>Subproblem:</b> Fibonacci(n) depends on Fibonacci(n-1) and Fibonacci(n-2)</li>
     *   <li><b>Cache:</b> Store computed values in dp[] array to avoid recomputation</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * F(n) = F(n-1) + F(n-2)
     * Base cases:
     *   F(0) = 0
     *   F(1) = 1
     * </pre>
     * 
     * <h3>Example: nthFibonacci(5)</h3>
     * <pre>
     * dp = [0, 0, 0, 0, 0, 0]  (initialized)
     * 
     * Call Tree with Memoization:
     * fib(5) = fib(4) + fib(3)
     *   fib(4) = fib(3) + fib(2)
     *     fib(3) = fib(2) + fib(1)
     *       fib(2) = fib(1) + fib(0)
     *         fib(1) = 1  [base]
     *         fib(0) = 0  [base]
     *       dp[2] = 1, returns 1
     *       fib(1) = 1 (base)
     *     dp[3] = 1 + 1 = 2, returns 2
     *     fib(2) = dp[2] = 1 (MEMO HIT! No recalculation!)
     *   dp[4] = 2 + 1 = 3, returns 3
     *   fib(3) = dp[3] = 2 (MEMO HIT!)
     * dp[5] = 3 + 2 = 5, returns 5
     * 
     * Result: F(5) = 5
     * Computation Count: 6 calls (each state computed once)
     * </pre>
     * 
     * <h3>Time Complexity: O(n)</h3>
     * Each Fibonacci number from 0 to n is computed exactly once and cached.
     * 
     * <h3>Space Complexity: O(n)</h3>
     * dp[] array: O(n) + Recursion call stack: O(n) = O(n) total
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li>Use tabulation (bottom-up) to avoid recursion overhead: {@link #NthFibonacci1(int)}</li>
     *   <li>Space-optimize to O(1) by keeping only last 2 values: {@link #NthFibonacci2(int)}</li>
     *   <li>Use matrix exponentiation for O(log n) time (advanced)</li>
     * </ul>
     * 
     * @param n the position in Fibonacci sequence (0-indexed)
     * @return the nth Fibonacci number
     * @see #Fibonacci(int) for the recursive computation
     * @see #NthFibonacci1(int) for tabulation approach
     * @see #NthFibonacci2(int) for space-optimized approach
     */
    public int nthFibonacci(int n) {
        dp = new int[n+1];
        return Fibonacci(n);
    }
    
    /**
     * Helper method for computing Fibonacci numbers recursively with memoization.
     * 
     * <h3>Problem Description:</h3>
     * Recursive Fibonacci computation with caching to prevent redundant calculations.
     * This is called by {@link #nthFibonacci(int)} after initializing the dp array.
     * 
     * <h3>DP Approach:</h3>
     * Check if the result is already cached. If yes, return it immediately.
     * Otherwise, compute by recursion and store in cache for future use.
     * 
     * <h3>Algorithm:</h3>
     * <pre>
     * 1. Base case: if n ≤ 1, return n
     * 2. Check cache: if dp[n] is already computed, return dp[n]
     * 3. Compute: ans = Fibonacci(n-1) + Fibonacci(n-2)
     * 4. Cache: dp[n] = ans
     * 5. Return ans
     * </pre>
     * 
     * <h3>Example Dry Run: Fibonacci(4)</h3>
     * <pre>
     * Initial dp = [0, 0, 0, 0, 0]
     * 
     * Fibonacci(4):
     *   dp[4] is 0 (not computed), so recurse
     *   ans = Fibonacci(3) + Fibonacci(2)
     *   
     *   Fibonacci(3):
     *     dp[3] is 0, recurse
     *     ans = Fibonacci(2) + Fibonacci(1)
     *     
     *     Fibonacci(2):
     *       dp[2] is 0, recurse
     *       ans = Fibonacci(1) + Fibonacci(0)
     *       Fibonacci(1) returns 1
     *       Fibonacci(0) returns 0
     *       dp[2] = 1 + 0 = 1 ✓
     *     Fibonacci(1) returns 1
     *     dp[3] = 1 + 1 = 2 ✓
     *   
     *   Fibonacci(2) returns dp[2] = 1 (MEMO HIT!)
     *   dp[4] = 2 + 1 = 3 ✓
     * 
     * Result: F(4) = 3
     * </pre>
     * 
     * <h3>Time Complexity: O(n)</h3>
     * Each state is computed once, subsequent calls hit the cache.
     * 
     * <h3>Space Complexity: O(n)</h3>
     * dp[] array: O(n) + Call stack depth: O(n)
     * 
     * @param n the Fibonacci index
     * @return the nth Fibonacci number
     */
    public int Fibonacci(int n) {
        if(n<=1) return n;
        if(dp[n] != 0) return dp[n];
        int ans = Fibonacci(n-1) + Fibonacci(n-2);
        dp[n] = ans;
        return ans;
    }
    
    // ============ EXAMPLE 1: arr = [5, 3, 2] ============
    // dp1 = [-1, -1, -1] initially
    //
    // CALL TREE:
    // loot(0) → pick=5+loot(2), skip=loot(1)
    //   ├─ loot(2) → pick=2+loot(4)=2+0=2, skip=loot(3)=0
    //   │   dp1[2] = max(0, 2) = 2 ✓ STORED
    //   │   returns 2
    //   │
    //   └─ loot(1) → pick=3+loot(3)=3+0=3, skip=loot(2)
    //       ├─ loot(2) → dp1[2] already = 2, returns 2 immediately! (MEMO HIT)
    //       dp1[1] = max(2, 3) = 3 ✓ STORED
    //       returns 3
    //
    // Back to loot(0): pick = 5+2=7, skip=3
    // dp1[0] = max(3, 7) = 7
    // ANSWER: 7
    
    // ============ EXAMPLE 2: arr = [6, 7, 1, 3] ============
    // dp1 = [-1, -1, -1, -1] initially
    //
    // CALL TREE:
    // loot(0) → pick=6+loot(2), skip=loot(1)
    //   ├─ loot(2) → pick=1+loot(4)=1+0=1, skip=loot(3)=3
    //   │   dp1[2] = max(3, 1) = 3 ✓ STORED
    //   │   returns 3
    //   │
    //   └─ loot(1) → pick=7+loot(3), skip=loot(2)
    //       ├─ loot(3) → pick=3+loot(5)=3+0=3, skip=loot(4)=0
    //       │   dp1[3] = max(0, 3) = 3 ✓ STORED
    //       │   returns 3
    //       ├─ loot(2) → dp1[2] = 3 (MEMO HIT! No recalculation)
    //       dp1[1] = max(3, 7+3) = max(3, 10) = 10 ✓ STORED
    //       returns 10
    //
    // Back to loot(0): pick = 6+3=9, skip=10
    // dp1[0] = max(10, 9) = 10
    // ANSWER: 10 (pick houses at index 1 and 3: 7+3=10)


    static int dp1[];
    
    /**
     * Finds the maximum sum of non-adjacent elements in an array.
     * 
     * <h3>Problem Description:</h3>
     * This is the classic "House Robbery" problem. Given an array of positive integers,
     * select non-adjacent elements such that their sum is maximized.
     * 
     * Real-world context: You want to rob houses in a street. If you rob house i,
     * you cannot rob house i-1 or i+1 (alarm). Find the maximum money you can steal.
     * 
     * <h3>DP Approach (Memoization):</h3>
     * <ul>
     *   <li><b>Strategy:</b> At each house, decide: rob it or skip it?</li>
     *   <li><b>State:</b> dp[i] = maximum sum starting from index i</li>
     *   <li><b>Recurrence:</b> dp[i] = max(arr[i] + dp[i+2], dp[i+1])</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * dp1[i] = max(
     *   arr[i] + dp1[i+2],    // Rob house i, then best from i+2
     *   dp1[i+1]               // Skip house i, take best from i+1
     * )
     * 
     * Base case:
     *   dp1[i] = 0 if i >= arr.length
     * </pre>
     * 
     * <h3>Example: findMaxSum([6, 7, 1, 3])</h3>
     * <pre>
     * Initial dp1 = [-1, -1, -1, -1]
     * 
     * Call Tree with Memoization:
     * loot(0):
     *   pick(0) = 6 + loot(2)
     *     loot(2):
     *       pick(2) = 1 + loot(4) = 1 + 0 = 1
     *       skip(2) = loot(3)
     *         loot(3):
     *           pick(3) = 3 + loot(5) = 3 + 0 = 3
     *           skip(3) = loot(4) = 0
     *           dp1[3] = max(3, 0) = 3 ✓
     *       loot(3) returns 3
     *       dp1[2] = max(1, 3) = 3 ✓
     *   loot(2) returns 3
     *   pick(0) = 6 + 3 = 9
     *   
     *   skip(0) = loot(1)
     *     loot(1):
     *       pick(1) = 7 + loot(3)
     *         loot(3) = dp1[3] = 3 (MEMO HIT!)
     *       pick(1) = 7 + 3 = 10
     *       skip(1) = loot(2)
     *         loot(2) = dp1[2] = 3 (MEMO HIT!)
     *       skip(1) = 3
     *       dp1[1] = max(10, 3) = 10 ✓
     *   loot(1) returns 10
     *   skip(0) = 10
     *   
     *   dp1[0] = max(9, 10) = 10 ✓
     * 
     * Result: Maximum sum = 10
     * Selected houses: index 1 (7) and index 3 (3) = 10
     * </pre>
     * 
     * <h3>Visualization of DP Table (Bottom-Up Build):</h3>
     * <pre>
     * Array:  [6,  7,  1,  3]
     * Index:   0   1   2   3
     * 
     * DP building backwards (conceptually):
     * dp[4] = 0      (beyond array)
     * dp[3] = max(3+0, 0) = 3
     * dp[2] = max(1+0, 3) = 3
     * dp[1] = max(7+3, 3) = 10
     * dp[0] = max(6+3, 10) = 10
     * 
     * Answer: dp[0] = 10
     * </pre>
     * 
     * <h3>Time Complexity: O(n)</h3>
     * Each index is processed only once. Memoization ensures no redundant calculations.
     * 
     * <h3>Space Complexity: O(n)</h3>
     * dp1[] array: O(n) + Recursion call stack: O(n) = O(n) total
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li><b>Space Optimization to O(1):</b> Keep only the last 2 computed values
     *       since dp[i] only depends on dp[i+1] and dp[i+2]</li>
     *   <li><b>Tabulation (Bottom-Up):</b> See {@link #findMaxSum2(int[])} for iterative approach</li>
     *   <li><b>Extended Problem:</b> Can be modified to track which elements were picked</li>
     * </ul>
     * 
     * @param arr array of house values (positive integers)
     * @return maximum sum of non-adjacent elements
     * @see #loot(int, int[]) for the recursive computation
     * @see #findMaxSum2(int[]) for the tabulation (bottom-up) approach
     */
    public int findMaxSum(int arr[]) {
        dp1 = new int[arr.length];
        Arrays.fill(dp1 , -1);  // Initialize all as -1 (meaning NOT computed yet)
        return loot(0 , arr);
    }
    
    /**
     * Recursively computes the maximum sum starting from index i using memoization.
     * 
     * <h3>Problem Description:</h3>
     * Helper method for {@link #findMaxSum(int[])}.
     * Recursively solves the subproblem of finding max sum from index i onwards
     * with the constraint that selected elements cannot be adjacent.
     * 
     * <h3>DP Approach:</h3>
     * At each house (index i):
     * 1. If robbed: get arr[i] + best sum from i+2 onwards (skip adjacent house)
     * 2. If skipped: get best sum from i+1 onwards
     * 3. Take the maximum of these two choices
     * 4. Cache the result for future lookups
     * 
     * <h3>Key Insight - Memoization Check:</h3>
     * If dp1[i] != -1, it means this subproblem was already solved.
     * Return cached result instead of recalculating (prevents redundant work).
     * 
     * <h3>Algorithm:</h3>
     * <pre>
     * 1. Base case: if i >= arr.length, return 0 (no more elements)
     * 2. Check cache: if dp1[i] != -1, return dp1[i]
     * 3. Calculate pick option: arr[i] + loot(i+2)
     * 4. Calculate skip option: loot(i+1)
     * 5. Store result: dp1[i] = max(pick, skip)
     * 6. Return dp1[i]
     * </pre>
     * 
     * <h3>Example Trace: loot(0) for arr=[5,3,2]</h3>
     * <pre>
     * loot(0):
     *   pick = 5 + loot(2)
     *     loot(2):
     *       pick = 2 + loot(4) = 2 + 0 = 2
     *       skip = loot(3) = 0
     *       dp1[2] = max(2, 0) = 2 ✓
     *       returns 2
     *   pick = 5 + 2 = 7
     *   
     *   skip = loot(1)
     *     loot(1):
     *       pick = 3 + loot(3) = 3 + 0 = 3
     *       skip = loot(2)
     *         loot(2) = dp1[2] = 2 (MEMO HIT! Already cached!)
     *       skip = 2
     *       dp1[1] = max(3, 2) = 3 ✓
     *       returns 3
     *   skip = 3
     *   
     *   dp1[0] = max(7, 3) = 7 ✓
     * Result: 7
     * </pre>
     * 
     * <h3>Time Complexity: O(n)</h3>
     * Each index from 0 to n-1 is computed exactly once.
     * After first computation, result is retrieved from cache in O(1).
     * 
     * <h3>Space Complexity: O(n)</h3>
     * dp1[] cache: O(n) + Recursion call stack: O(n) in worst case
     * 
     * <h3>Why Memoization Works Here:</h3>
     * Without memoization, loot(2) would be called twice (from loot(0) and loot(1)).
     * With memoization, loot(2) is computed once and reused, saving exponential time.
     * 
     * @param i current index in array
     * @param arr array of house values
     * @return maximum sum from index i to end, with non-adjacent constraint
     * @see #findMaxSum(int[]) for the wrapper method
     */
    private int loot(int i , int arr[]){
        if(i>= arr.length) return 0;  // Base case: no more elements
        
        if(dp1[i] != -1) return dp1[i];  // KEY: Already computed? Return from memory!
        
        int pick = arr[i] + loot(i+2, arr);  // Take current, skip next
        int skip = loot(i+1 , arr);           // Skip current, go to next
        int ans =  Math.max(skip , pick);     // Choose max
        
        dp1[i] = ans;  // STORE result in dp1 array
        return ans;
    }
    
    // ============ MIN COST CLIMBING STAIRS ============
    // Problem: You're at step 0 or 1. At each step, you can jump +1 or +2 steps.
    // Find minimum cost to reach the end.
    // You PAY cost[i] when you LAND on step i, then add the min cost from next positions.
    //
    // The function tries BOTH starting points (0 and 1) and returns the minimum.
    // KEY: dp2[i] = cost[i] + min(minCost(i+1), minCost(i+2))
    
    // ============ EXAMPLE 1: cost = [1, 2, 3, 4] ============
    // Total length = 4, reach index 4 (beyond array) to exit
    // dp2 = [-1, -1, -1, -1] initially
    //
    // minCostClimbingStairs() = Math.min(minCost(0), minCost(1))
    //
    // STARTING FROM STEP 0:
    //   minCost(0) = cost[0] + min(minCost(1), minCost(2))
    //   
    //     minCost(1) = cost[1] + min(minCost(2), minCost(3))
    //       minCost(2) = cost[2] + min(minCost(3), minCost(4))
    //         minCost(3) = cost[3] + min(minCost(4), minCost(5))
    //           minCost(4) = 0 (i >= 4, end!)
    //           minCost(5) = 0 (i >= 4, out!)
    //           dp2[3] = 4 + min(0, 0) = 4 ✓
    //         minCost(4) = 0
    //         dp2[2] = 3 + min(4, 0) = 3 + 0 = 3 ✓
    //       minCost(3) = dp2[3] = 4 (MEMO HIT!)
    //       dp2[1] = 2 + min(3, 4) = 2 + 3 = 5 ✓
    //     minCost(2) = dp2[2] = 3 (MEMO HIT!)
    //     dp2[0] = 1 + min(5, 3) = 1 + 3 = 4 ✓
    //
    // STARTING FROM STEP 1:
    //   minCost(1) = dp2[1] = 5 (MEMO HIT! Already computed)
    //
    // FINAL ANSWER = Math.min(4, 5) = 4
    // (Best path: step 0 (cost 1) → step 2 (cost 3) → end = 1+3=4)
    //
    // ============ EXAMPLE 2: cost = [10, 15, 20] ============
    // Total length = 3, reach index 3 to exit
    // dp2 = [-1, -1, -1] initially
    //
    // minCostClimbingStairs() = Math.min(minCost(0), minCost(1))
    //
    // STARTING FROM STEP 0:
    //   minCost(0) = cost[0] + min(minCost(1), minCost(2))
    //   
    //     minCost(1) = cost[1] + min(minCost(2), minCost(3))
    //       minCost(2) = cost[2] + min(minCost(3), minCost(4))
    //         minCost(3) = 0 (i >= 3, end!)
    //         minCost(4) = 0 (i >= 3, out!)
    //         dp2[2] = 20 + min(0, 0) = 20 ✓
    //       minCost(3) = 0
    //       dp2[1] = 15 + min(20, 0) = 15 + 0 = 15 ✓
    //     minCost(2) = dp2[2] = 20 (MEMO HIT!)
    //     dp2[0] = 10 + min(15, 20) = 10 + 15 = 25 ✓
    //
    // STARTING FROM STEP 1:
    //   minCost(1) = dp2[1] = 15 (MEMO HIT! Already computed)
    //
    // FINAL ANSWER = Math.min(25, 15) = 15
    // (Best path: step 1 (cost 15) → end = 15)

    
    static int dp2[];
    
    /**
     * Finds the minimum cost to climb stairs, starting from step 0 or 1.
     * 
     * <h3>Problem Description:</h3>
     * You want to reach the top of a staircase. At each step, you can move 1 or 2 steps forward.
     * Each step i has a cost[i]. When you land on step i, you must pay cost[i].
     * Find the minimum total cost to reach the end (beyond the array).
     * 
     * Real-world analogy: Climbing stairs with slippery steps of varying difficulty.
     * Some steps are harder (more cost) than others. You can skip every other step to
     * optimize your path to the top.
     * 
     * <h3>DP Approach (Memoization):</h3>
     * <ul>
     *   <li><b>State:</b> dp2[i] = minimum cost starting from step i to reach the end</li>
     *   <li><b>Recurrence:</b> dp2[i] = cost[i] + min(dp2[i+1], dp2[i+2])</li>
     *   <li><b>Transitions:</b> From step i, you can go to i+1 or i+2</li>
     *   <li><b>Special:</b> Try both starting points (0 and 1) and return the minimum</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * dp2[i] = cost[i] + min(minCost(i+1), minCost(i+2))
     * 
     * Base case:
     *   minCost(i) = 0 if i >= cost.length
     * </pre>
     * 
     * <h3>Example: minCostClimbingStairs([1, 2, 3, 4])</h3>
     * <pre>
     * Initial dp2 = [-1, -1, -1, -1]
     * 
     * Starting from Step 0:
     *   minCost(0) = 1 + min(minCost(1), minCost(2))
     *   
     *   minCost(1) = 2 + min(minCost(2), minCost(3))
     *     minCost(2) = 3 + min(minCost(3), minCost(4))
     *       minCost(3) = 4 + min(minCost(4), minCost(5))
     *         minCost(4) = 0, minCost(5) = 0
     *         dp2[3] = 4 + min(0, 0) = 4 ✓
     *       minCost(4) = 0
     *       dp2[2] = 3 + min(4, 0) = 3 ✓
     *     minCost(3) = dp2[3] = 4 (MEMO HIT!)
     *     dp2[1] = 2 + min(3, 4) = 5 ✓
     *   minCost(2) = dp2[2] = 3 (MEMO HIT!)
     *   dp2[0] = 1 + min(5, 3) = 4 ✓
     * 
     * Starting from Step 1:
     *   minCost(1) = dp2[1] = 5 (MEMO HIT! Already computed!)
     * 
     * Result: min(4, 5) = 4
     * Best path: Step 0 (cost 1) → Step 2 (cost 3) → End = 1 + 3 = 4
     * </pre>
     * 
     * <h3>Time Complexity: O(n)</h3>
     * Each step is computed exactly once due to memoization.
     * We compute dp2[i] for all valid indices.
     * 
     * <h3>Space Complexity: O(n)</h3>
     * dp2[] array: O(n) + Recursion call stack: O(n) = O(n) total
     * Maximum recursion depth is n (linear chain of calls).
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li><b>Tabulation:</b> See {@link #minCostClimbingStairs2(int[])} for bottom-up iterative</li>
     *   <li><b>Space-Optimized:</b> Use only 2 variables instead of dp array (O(1) space)</li>
     *   <li><b>Path Reconstruction:</b> Can modify to track which steps were taken</li>
     * </ul>
     * 
     * @param cost array where cost[i] is the cost of landing on step i
     * @return minimum total cost to reach the top (beyond the array)
     * @see #minCost(int, int[]) for the recursive computation
     * @see #minCostClimbingStairs2(int[]) for the tabulation approach
     */
    static int minCostClimbingStairs(int[] cost) {
        dp2 = new int[cost.length];
        Arrays.fill(dp2 , -1);
        return Math.min(minCost(0,cost) , minCost(1,cost));
    }
    
    /**
     * Helper method: Computes minimum cost from step i to the end using memoization.
     * 
     * <h3>Problem Description:</h3>
     * Recursive helper for {@link #minCostClimbingStairs(int[])}.
     * Solves the subproblem: given that we're at step i, what's the minimum
     * cost to reach the end (beyond the array)?
     * 
     * <h3>DP Logic:</h3>
     * From step i, we have two choices:
     * 1. Step forward 1 step to i+1: total cost = cost[i] + minCost(i+1)
     * 2. Step forward 2 steps to i+2: total cost = cost[i] + minCost(i+2)
     * 
     * Choose the option with minimum cost.
     * Cache the result to avoid recomputation if this step is visited again.
     * 
     * <h3>Algorithm:</h3>
     * <pre>
     * 1. Base case: if i >= cost.length, return 0 (already at or past the end)
     * 2. Check cache: if dp2[i] != -1, return dp2[i]
     * 3. Compute: dp2[i] = cost[i] + min(minCost(i+1), minCost(i+2))
     * 4. Return dp2[i]
     * </pre>
     * 
     * <h3>Example Trace: minCost(0, [1,2,3,4])</h3>
     * <pre>
     * minCost(0):
     *   cost[0] + min(minCost(1), minCost(2))
     *   1 + min(?, ?)
     *   
     *   minCost(1):
     *     cost[1] + min(minCost(2), minCost(3))
     *     2 + min(?, ?)
     *     
     *     minCost(2):
     *       cost[2] + min(minCost(3), minCost(4))
     *       3 + min(?, ?)
     *       
     *       minCost(3):
     *         cost[3] + min(minCost(4), minCost(5))
     *         4 + min(0, 0) = 4
     *       
     *       minCost(4) = 0
     *       3 + min(4, 0) = 3, dp2[2] = 3 ✓
     *     
     *     minCost(3) = dp2[3] = 4 (MEMO!)
     *     2 + min(3, 4) = 5, dp2[1] = 5 ✓
     *   
     *   minCost(2) = dp2[2] = 3 (MEMO!)
     *   1 + min(5, 3) = 4, dp2[0] = 4 ✓
     * </pre>
     * 
     * <h3>Time Complexity: O(n)</h3>
     * Each step i computes only once. Subsequent calls hit cache in O(1).
     * 
     * <h3>Space Complexity: O(n)</h3>
     * Recursion depth: O(n) in worst case (linear chain)
     * 
     * <h3>Key Insight:</h3>
     * The memoization is crucial. Without it, we'd compute minCost(2) twice:
     * - Once from minCost(0) → minCost(2)
     * - Once from minCost(1) → minCost(2)
     * With memoization, it's computed once and cached.
     * 
     * @param i current step index
     * @param cost array of costs for each step
     * @return minimum cost from step i to the end
     */
    private static int minCost(int i, int[] cost) {
        if(i>=cost.length) return 0;  // Base case: reached the top (no more cost)
        if(dp2[i] != -1) return dp2[i];  // Already computed? Return from memory!
        return dp2[i] = cost[i] + Math.min(minCost(i+1 , cost) , minCost(i+2 , cost));
    }

    
    int[] dp3;
    
    /**
     * Counts the number of distinct ways to climb n steps.
     * 
     * <h3>Problem Description:</h3>
     * You're at step 0 and want to reach step n. At each step, you can move +1 or +2 steps.
     * How many distinct ways are there to reach step n?
     * 
     * Real-world context: A child climbing stairs. At each point, they can take either one
     * or two steps. Count all the different sequences of moves to reach the top.
     * 
     * <h3>DP Approach (Memoization):</h3>
     * <ul>
     *   <li><b>State:</b> ways(n) = number of distinct ways to reach step n</li>
     *   <li><b>Recurrence:</b> ways(n) = ways(n-1) + ways(n-2)</li>
     *   <li><b>Insight:</b> To reach step n, you either came from step n-1 (+1) or n-2 (+2)</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * ways(n) = ways(n-1) + ways(n-2)
     * 
     * Base cases:
     *   ways(0) = 1 (one way: don't move, already there)
     *   ways(1) = 1 (one way: take 1 step)
     * </pre>
     * 
     * <h3>Example: countWays(4)</h3>
     * <pre>
     * Initial dp3 = [-1, -1, -1, -1, -1]
     * 
     * solve(4) = solve(3) + solve(2)
     *   solve(3) = solve(2) + solve(1)
     *     solve(2) = solve(1) + solve(0)
     *       solve(1) = 1 (base case)
     *       solve(0) = 1 (base case)
     *       dp3[2] = 1 + 1 = 2 ✓
     *     solve(1) = 1
     *     dp3[3] = 2 + 1 = 3 ✓
     *   solve(2) = dp3[2] = 2 (MEMO HIT!)
     *   dp3[4] = 3 + 2 = 5 ✓
     * 
     * Result: 5 ways to reach step 4
     * All possible sequences:
     *   1. 1+1+1+1 (four 1-step moves)
     *   2. 1+1+2   (two 1-steps, then one 2-step)
     *   3. 1+2+1   (1-step, 2-step, 1-step)
     *   4. 2+1+1   (one 2-step, then two 1-steps)
     *   5. 2+2     (two 2-step moves)
     * </pre>
     * 
     * <h3>DP Table Visualization:</h3>
     * <pre>
     * Step:  0   1   2   3   4
     * Ways:  1   1   2   3   5
     * 
     * dp[0] = 1
     * dp[1] = 1
     * dp[2] = dp[1] + dp[0] = 1 + 1 = 2
     * dp[3] = dp[2] + dp[1] = 2 + 1 = 3
     * dp[4] = dp[3] + dp[2] = 3 + 2 = 5
     * </pre>
     * 
     * <h3>Time Complexity: O(n)</h3>
     * Each step from 0 to n is computed exactly once.
     * 
     * <h3>Space Complexity: O(n)</h3>
     * dp3[] array: O(n) + Recursion call stack: O(n)
     * 
     * <h3>Relation to Fibonacci:</h3>
     * This problem IS the Fibonacci sequence! ways(n) = fib(n+1).
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li><b>Space-Optimized:</b> Use only 2 variables (O(1) space) since only
     *       the last 2 values are needed</li>
     *   <li><b>Tabulation:</b> Build iteratively from bottom-up</li>
     *   <li><b>Matrix Exponentiation:</b> Compute in O(log n) time</li>
     * </ul>
     * 
     * @param n the target step number
     * @return number of distinct ways to reach step n
     * @see #solve(int) for the recursive computation
     */
    int countWays(int n) {
        dp3 = new int[n+1];
        Arrays.fill(dp3, -1);
        return solve(n);
    }
    
    /**
     * Recursively counts ways to reach step n using memoization.
     * 
     * <h3>Problem Description:</h3>
     * Helper method for {@link #countWays(int)}.
     * Computes: in how many ways can we reach step n by taking +1 or +2 steps?
     * 
     * <h3>DP Logic:</h3>
     * To reach step n, you must have been at either:
     * - Step n-1 (and took +1), or
     * - Step n-2 (and took +2)
     * 
     * So the number of ways to reach n is the sum of:
     * - Ways to reach n-1 (then +1 to n), and
     * - Ways to reach n-2 (then +2 to n)
     * 
     * <h3>Algorithm:</h3>
     * <pre>
     * 1. Base cases: if n == 0 or n == 1, return 1
     * 2. Check cache: if dp3[n] != -1, return dp3[n]
     * 3. Compute: dp3[n] = solve(n-1) + solve(n-2)
     * 4. Return dp3[n]
     * </pre>
     * 
     * <h3>Example Trace: solve(3)</h3>
     * <pre>
     * solve(3):
     *   Not base case, dp3[3] = -1
     *   dp3[3] = solve(2) + solve(1)
     *   
     *   solve(2):
     *     Not base case, dp3[2] = -1
     *     dp3[2] = solve(1) + solve(0)
     *     solve(1) = 1 (base)
     *     solve(0) = 1 (base)
     *     dp3[2] = 1 + 1 = 2 ✓
     *   
     *   solve(1) = 1 (base case)
     *   dp3[3] = 2 + 1 = 3 ✓
     * 
     * Result: 3 ways
     * Sequences to reach step 3:
     * 1. 1+1+1
     * 2. 1+2
     * 3. 2+1
     * </pre>
     * 
     * <h3>Why Memoization Matters:</h3>
     * Without memoization (naive recursion):
     * - solve(3) calls solve(2) and solve(1)
     * - solve(2) calls solve(1) again and solve(0)
     * So solve(1) is called twice!
     * 
     * With memoization: solve(1) is computed once and cached.
     * This reduces exponential O(2^n) to linear O(n).
     * 
     * <h3>Time Complexity: O(n)</h3>
     * Each state computed exactly once, cached for reuse.
     * 
     * <h3>Space Complexity: O(n)</h3>
     * Recursion depth: O(n), plus dp3[] array: O(n)
     * 
     * @param n the target step
     * @return number of ways to reach step n
     * @see #countWays(int) for the wrapper
     */
    int solve(int n) {
        if(n == 0 || n == 1) return 1;
        if(dp3[n] != -1) return dp3[n];
        return dp3[n] = solve(n-1) + solve(n-2);
    }


    /**
     * Calculates the nth Fibonacci number using tabulation (bottom-up DP).
     * 
     * <h3>Problem Description:</h3>
     * Compute Fibonacci(n) iteratively by building up from base cases.
     * This is the classic tabulation (bottom-up) approach.
     * 
     * <h3>DP Approach (Tabulation - Bottom-Up):</h3>
     * <ul>
     *   <li><b>Strategy:</b> Start from base cases and iteratively compute larger values</li>
     *   <li><b>Order:</b> Fill dp array from index 2 to n</li>
     *   <li><b>Advantage:</b> No recursion overhead, purely iterative</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * dp[i] = dp[i-1] + dp[i-2]
     * 
     * Base cases:
     *   dp[0] = 0
     *   dp[1] = 1
     * </pre>
     * 
     * <h3>Example: NthFibonacci1(5)</h3>
     * <pre>
     * Initialize dp = [0, 0, 0, 0, 0, 0]  (size n+1)
     * Set dp[1] = 1 if n >= 1: dp = [0, 1, 0, 0, 0, 0]
     * 
     * Build iteratively:
     * i=2: dp[2] = dp[1] + dp[0] = 1 + 0 = 1     → dp = [0, 1, 1, 0, 0, 0]
     * i=3: dp[3] = dp[2] + dp[1] = 1 + 1 = 2     → dp = [0, 1, 1, 2, 0, 0]
     * i=4: dp[4] = dp[3] + dp[2] = 2 + 1 = 3     → dp = [0, 1, 1, 2, 3, 0]
     * i=5: dp[5] = dp[4] + dp[3] = 3 + 2 = 5     → dp = [0, 1, 1, 2, 3, 5]
     * 
     * Return dp[5] = 5
     * </pre>
     * 
     * <h3>Time Complexity: O(n)</h3>
     * Single loop from 2 to n, each iteration is O(1).
     * 
     * <h3>Space Complexity: O(n)</h3>
     * dp array of size n+1.
     * 
     * <h3>Comparison with Memoization:</h3>
     * <table border="1">
     * <tr>
     *   <th>Aspect</th>
     *   <th>Memoization (Top-Down)</th>
     *   <th>Tabulation (Bottom-Up)</th>
     * </tr>
     * <tr>
     *   <td>Style</td>
     *   <td>Recursive (natural for problem)</td>
     *   <td>Iterative (build systematically)</td>
     * </tr>
     * <tr>
     *   <td>Stack Overhead</td>
     *   <td>Yes, recursion stack O(n)</td>
     *   <td>No recursion</td>
     * </tr>
     * <tr>
     *   <td>Speed</td>
     *   <td>Slightly slower (function calls)</td>
     *   <td>Faster (pure loops)</td>
     * </tr>
     * <tr>
     *   <td>Easier to Optimize</td>
     *   <td>After understanding recurrence</td>
     *   <td>Natural for space optimization</td>
     * </tr>
     * </table>
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li><b>Space-Optimized to O(1):</b> See {@link #NthFibonacci2(int)} - only keep last 2 values</li>
     *   <li><b>Memoization Alternative:</b> See {@link #Fibonacci(int)} for recursive approach</li>
     * </ul>
     * 
     * @param n the position in Fibonacci sequence (0-indexed)
     * @return the nth Fibonacci number
     * @see #Fibonacci(int) for memoization approach
     * @see #NthFibonacci2(int) for space-optimized version
     */
    public int NthFibonacci1(int n){
        int dp[] = new int[n+1];
        if(n>=1) dp[1] = 1;
        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /**
     * Calculates the nth Fibonacci number with space optimization (O(1) space).
     * 
     * <h3>Problem Description:</h3>
     * Compute Fibonacci(n) using tabulation with minimal space usage.
     * Instead of storing all n values, keep only the last 3 values needed for computation.
     * 
     * <h3>DP Approach (Space-Optimized Tabulation):</h3>
     * <ul>
     *   <li><b>Strategy:</b> Use a sliding window of size 3 instead of full dp array</li>
     *   <li><b>Key Insight:</b> dp[i] depends only on dp[i-1] and dp[i-2]</li>
     *   <li><b>Space Reduction:</b> O(n) → O(1) by discarding older values</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * In the dp array of size 3:
     * - dp[0] stores dp[i-2]
     * - dp[1] stores dp[i-1]
     * - dp[2] stores dp[i] (newly computed)
     * 
     * Update:
     *   dp[2] = dp[1] + dp[0]
     *   dp[0] = dp[1]  (shift values)
     *   dp[1] = dp[2]  (shift values)
     * </pre>
     * 
     * <h3>Example: NthFibonacci2(5)</h3>
     * <pre>
     * Initialize dp = [0, 0, 0]
     * if n <= 1, return n immediately
     * Set dp[1] = 1: dp = [0, 1, 0]
     * 
     * i=2:
     *   dp[2] = dp[1] + dp[0] = 1 + 0 = 1
     *   dp[0] = dp[1] = 1
     *   dp[1] = dp[2] = 1
     *   → dp = [1, 1, 1]
     * 
     * i=3:
     *   dp[2] = dp[1] + dp[0] = 1 + 1 = 2
     *   dp[0] = dp[1] = 1
     *   dp[1] = dp[2] = 2
     *   → dp = [1, 2, 2]
     * 
     * i=4:
     *   dp[2] = dp[1] + dp[0] = 2 + 1 = 3
     *   dp[0] = dp[1] = 2
     *   dp[1] = dp[2] = 3
     *   → dp = [2, 3, 3]
     * 
     * i=5:
     *   dp[2] = dp[1] + dp[0] = 3 + 2 = 5
     *   dp[0] = dp[1] = 3
     *   dp[1] = dp[2] = 5
     *   → dp = [3, 5, 5]
     * 
     * Return dp[2] = 5
     * </pre>
     * 
     * <h3>Time Complexity: O(n)</h3>
     * Single loop iteration from 2 to n.
     * 
     * <h3>Space Complexity: O(1)</h3>
     * Fixed-size array of size 3, regardless of n.
     * This is the best space complexity possible for this problem!
     * 
     * <h3>Visual Explanation of Sliding Window:</h3>
     * <pre>
     * Fibonacci: [0, 1, 1, 2, 3, 5, 8, 13, ...]
     *             ↑  ↑  ↑
     *             │  │  └─ dp[2] (current F(i))
     *             │  └──── dp[1] (F(i-1))
     *             └─────── dp[0] (F(i-2))
     * 
     * As we iterate, the window slides right:
     * [0, 1, ?] → Compute ? = 1, shift window → [1, 1, ?]
     * [1, 1, ?] → Compute ? = 2, shift window → [1, 2, ?]
     * [1, 2, ?] → Compute ? = 3, shift window → [2, 3, ?]
     * [2, 3, ?] → Compute ? = 5
     * </pre>
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li><b>Even Better:</b> Use two variables instead of 3 (previous and current)</li>
     *   <li><b>Matrix Exponentiation:</b> Compute in O(log n) time and O(1) space</li>
     *   <li><b>Memoization:</b> See {@link #Fibonacci(int)} if you need top-down recursion</li>
     * </ul>
     * 
     * @param n the position in Fibonacci sequence (0-indexed)
     * @return the nth Fibonacci number
     * @see #NthFibonacci1(int) for standard space tabulation
     * @see #Fibonacci(int) for memoization approach
     */
    public int NthFibonacci2(int n){
        int dp[] = new int[3];
        if(n<=1) return n;
        dp[1] = 1;
        for(int i=2; i<=n; i++){
            dp[2] = dp[1] + dp[0];
            dp[0] = dp[1];
            dp[1] = dp[2];
        }
        return dp[2];
    }

    static int dp4[];
    
    /**
     * Finds maximum sum of non-adjacent elements using tabulation (bottom-up DP).
     * 
     * <h3>Problem Description:</h3>
     * Given an array, select non-adjacent elements to maximize their sum.
     * This is the tabulation (iterative) version of {@link #findMaxSum(int[])}.
     * 
     * <h3>DP Approach (Tabulation - Bottom-Up):</h3>
     * <ul>
     *   <li><b>Strategy:</b> Build solution iteratively from left to right</li>
     *   <li><b>State:</b> dp4[i] = maximum sum using elements from 0 to i</li>
     *   <li><b>Recurrence:</b> dp4[i] = max(arr[i] + dp4[i-2], dp4[i-1])</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * At each position i, we have two choices:
     * 1. Include arr[i]: get arr[i] + dp4[i-2] (best sum up to i-2)
     * 2. Exclude arr[i]: get dp4[i-1] (best sum up to i-1)
     * 
     * dp4[i] = max(arr[i] + dp4[i-2], dp4[i-1])
     * 
     * Base cases:
     *   dp4[0] = arr[0]
     *   dp4[1] = max(arr[0], arr[1])
     * </pre>
     * 
     * <h3>Example: findMaxSum2([6, 7, 1, 3])</h3>
     * <pre>
     * Initialize:
     * dp4[0] = arr[0] = 6
     * dp4[1] = max(6, 7) = 7
     * 
     * i=2 (arr[2] = 1):
     *   dp4[2] = max(1 + dp4[0], dp4[1])
     *          = max(1 + 6, 7)
     *          = max(7, 7)
     *          = 7
     * 
     * i=3 (arr[3] = 3):
     *   dp4[3] = max(3 + dp4[1], dp4[2])
     *          = max(3 + 7, 7)
     *          = max(10, 7)
     *          = 10
     * 
     * Final dp4 = [6, 7, 7, 10]
     * Return dp4[3] = 10
     * </pre>
     * 
     * <h3>DP Table Visualization:</h3>
     * <pre>
     * Array:     [6,  7,  1,  3]
     * Index:      0   1   2   3
     * --------- --- --- --- ----
     * dp4:       [6,  7,  7, 10]
     * 
     * dp4[0] = 6         (only element 0)
     * dp4[1] = 7         (take element 1)
     * dp4[2] = 7         (take element 1, skip element 2)
     * dp4[3] = 10        (take elements 1 and 3)
     * </pre>
     * 
     * <h3>Time Complexity: O(n)</h3>
     * Single loop from 2 to n-1, each iteration is O(1).
     * 
     * <h3>Space Complexity: O(n)</h3>
     * dp4[] array of size n.
     * 
     * <h3>Comparison with Memoization Version:</h3>
     * <ul>
     *   <li><b>Memoization</b> ({@link #findMaxSum(int[])}): Top-down, recursive, handles only needed subproblems</li>
     *   <li><b>Tabulation</b> (this method): Bottom-up, iterative, computes all subproblems systematically</li>
     *   <li>Both have same Time/Space complexity, but tabulation is generally faster (no recursion overhead)</li>
     * </ul>
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li><b>Space Optimization:</b> Use only 2 variables instead of full array (O(1) space)
     *       by keeping track of only the last 2 computed values</li>
     *   <li><b>Track Selected Elements:</b> Modify to store which elements were selected</li>
     *   <li><b>Path Reconstruction:</b> Can backtrack to find actual sequence</li>
     * </ul>
     * 
     * @param arr array of positive integers
     * @return maximum sum of non-adjacent elements
     * @see #findMaxSum(int[]) for memoization approach
     */
    public int findMaxSum2(int arr[]) {
        dp4 = new int[arr.length];
        dp4[0] = arr[0];
        if(arr.length > 1) dp4[1] = Math.max(arr[0] , arr[1]);
        for(int i=2; i<arr.length; i++){
            dp4[i] = Math.max(arr[i] + dp4[i-2] , dp4[i-1]);
        }
        return dp4[arr.length-1];
    }

    static int dp5[];
    
    /**
     * Finds minimum cost to climb stairs using tabulation (bottom-up DP).
     * 
     * <h3>Problem Description:</h3>
     * Calculate the minimum cost to reach the top of the stairs, where you can move
     * +1 or +2 steps from the current position. This is the iterative version of
     * {@link #minCostClimbingStairs(int[])}.
     * 
     * <h3>DP Approach (Tabulation - Bottom-Up):</h3>
     * <ul>
     *   <li><b>Strategy:</b> Build iteratively from left to right</li>
     *   <li><b>State:</b> dp5[i] = minimum cost to reach the end starting from step i</li>
     *   <li><b>Recurrence:</b> dp5[i] = cost[i] + min(dp5[i-1], dp5[i-2])</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * dp5[i] = cost[i] + min(dp5[i-1], dp5[i-2])
     * 
     * Base cases:
     *   dp5[0] = cost[0]
     *   dp5[1] = cost[1] (if n > 1)
     * 
     * Result: min(dp5[n-1], dp5[n-2])
     *   (We can end from either the last or second-to-last step)
     * </pre>
     * 
     * <h3>Example: minCostClimbingStairs2([1, 2, 3, 4])</h3>
     * <pre>
     * Initialize:
     * dp5[0] = cost[0] = 1
     * dp5[1] = cost[1] = 2
     * 
     * i=2 (cost[2] = 3):
     *   dp5[2] = 3 + min(dp5[1], dp5[0])
     *          = 3 + min(2, 1)
     *          = 3 + 1
     *          = 4
     * 
     * i=3 (cost[3] = 4):
     *   dp5[3] = 4 + min(dp5[2], dp5[1])
     *          = 4 + min(4, 2)
     *          = 4 + 2
     *          = 6
     * 
     * Final dp5 = [1, 2, 4, 6]
     * Return min(dp5[3], dp5[2]) = min(6, 4) = 4
     * </pre>
     * 
     * <h3>DP Table Visualization:</h3>
     * <pre>
     * Cost:     [1,  2,  3,  4]
     * Index:     0   1   2   3
     * --------- --- --- --- ---
     * dp5:      [1,  2,  4,  6]
     * 
     * dp5[0] = 1              (cost to reach end from step 0)
     * dp5[1] = 2              (cost to reach end from step 1)
     * dp5[2] = 4              (cost to reach end from step 2)
     * dp5[3] = 6              (cost to reach end from step 3)
     * 
     * Best ending: min(6, 4) = 4
     * Path: Step 0 (cost 1) → Step 2 (cost 3) → End = 1 + 3 = 4
     * </pre>
     * 
     * <h3>Time Complexity: O(n)</h3>
     * Single loop from 2 to n-1.
     * 
     * <h3>Space Complexity: O(n)</h3>
     * dp5[] array of size n.
     * 
     * <h3>Key Difference from Memoization:</h3>
     * <ul>
     *   <li><b>Memoization</b> ({@link #minCostClimbingStairs(int[])}): 
     *       Works backwards from end to start, tries both starting points explicitly</li>
     *   <li><b>Tabulation</b> (this method):
     *       Works forwards from start to end, returns min of last two options</li>
     *   <li>Both produce same result, just different iteration direction</li>
     * </ul>
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li><b>Space Optimization:</b> Use only 2 variables (O(1) space)</li>
     *   <li><b>Path Tracking:</b> Store which step was chosen at each position</li>
     * </ul>
     * 
     * @param cost array where cost[i] is the cost of step i
     * @return minimum total cost to reach the top
     * @see #minCostClimbingStairs(int[]) for memoization approach
     */
    static int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        dp5 = new int [n];
        int n2 = dp5.length;
        dp5[0] = cost[0];
        if(n > 1) dp5[1] = cost[1];
        for(int i=2; i<n; i++){
            dp5[i] = cost[i] + Math.min(dp5[i-1] , dp5[i-2]);
        }
        return Math.min(dp5[n2-1] , dp5[n2-2]);
    }

    
    static int dp6[][];
    
    /**
     * Counts the number of unique paths in an m×n grid using memoization.
     * 
     * <h3>Problem Description:</h3>
     * In an m×n grid, you start at the top-left (0,0) and want to reach the bottom-right (m-1,n-1).
     * You can only move right or down. Count the total number of unique paths.
     * 
     * Real-world: Navigate a grid with only rightward or downward moves allowed.
     * How many different routes can you take?
     * 
     * <h3>DP Approach (Memoization):</h3>
     * <ul>
     *   <li><b>State:</b> path(m,n) = number of ways to reach cell (m,n)</li>
     *   <li><b>Recurrence:</b> path(m,n) = path(m-1,n) + path(m,n-1)</li>
     *   <li><b>Insight:</b> To reach (m,n), you came from either (m-1,n) or (m,n-1)</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * path(m,n) = path(m-1,n) + path(m,n-1)
     * 
     * Base cases:
     *   path(1, n) = 1 for any n (only one way: go right)
     *   path(m, 1) = 1 for any m (only one way: go down)
     * </pre>
     * 
     * <h3>Example: numberOfPaths(3, 3)</h3>
     * <pre>
     * Grid is 3×3, compute path(3,3)
     * 
     * DP Table (building top-down via recursion):
     *    1  2  3
     * 1  1  1  1
     * 2  1  2  3
     * 3  1  3  6
     * 
     * path(3,3) = path(2,3) + path(3,2)
     *           = 3 + 3 = 6
     * 
     * All 6 paths (R=right, D=down):
     * 1. RRDD
     * 2. RDRD
     * 3. RDDR
     * 4. DRRD
     * 5. DRDR
     * 6. DDRR
     * </pre>
     * 
     * <h3>Time Complexity: O(m × n)</h3>
     * Each cell is computed exactly once via memoization.
     * 
     * <h3>Space Complexity: O(m × n)</h3>
     * 2D dp6 array of size (m+1)×(n+1) + Recursion depth O(m+n)
     * 
     * <h3>Comparison with Tabulation:</h3>
     * <ul>
     *   <li><b>Memoization</b> (this method): Top-down, recursive, intuitive</li>
     *   <li><b>Tabulation</b> ({@link #uniquePaths(int, int)}): Bottom-up, iterative, faster</li>
     * </ul>
     * 
     * @param m number of rows in grid
     * @param n number of columns in grid
     * @return total number of unique paths from (0,0) to (m-1,n-1)
     * @see #path(int, int) for recursive computation
     * @see #uniquePaths(int, int) for tabulation approach
     */
    public int numberOfPaths(int m, int n) {
        dp6 = new int[m+1][n+1];
        return path(m,n);
    }
    
    /**
     * Helper method: Recursively computes number of paths using memoization.
     * 
     * <h3>Problem Description:</h3>
     * Computes the number of ways to reach cell (m,n) from (1,1) in a grid
     * where you can only move right or down.
     * 
     * <h3>DP Logic:</h3>
     * To reach (m,n):
     * - Come from (m-1,n) by moving down, or
     * - Come from (m,n-1) by moving right
     * Total paths = paths from (m-1,n) + paths from (m,n-1)
     * 
     * <h3>Algorithm:</h3>
     * <pre>
     * 1. Base: if m==1 or n==1, return 1 (only one path in border cells)
     * 2. Cache check: if dp6[m][n] != 0, return dp6[m][n]
     * 3. Compute: dp6[m][n] = path(m-1,n) + path(m,n-1)
     * 4. Return dp6[m][n]
     * </pre>
     * 
     * <h3>Example: path(2,2)</h3>
     * <pre>
     * path(2,2) = path(1,2) + path(2,1)
     *           = 1 + 1 = 2
     * 
     * The 2 paths are:
     * 1. Go right, then down: (1,1) → (1,2) → (2,2)
     * 2. Go down, then right: (1,1) → (2,1) → (2,2)
     * </pre>
     * 
     * <h3>Time Complexity: O(m × n)</h3>
     * Each cell computed once, recursion spans entire grid.
     * 
     * <h3>Space Complexity: O(m × n)</h3>
     * 2D array + recursion call stack O(m+n)
     * 
     * @param m row coordinate
     * @param n column coordinate
     * @return number of unique paths to cell (m,n)
     */
    private static int path(int m , int n){
        if(m == 1 || n == 1) return 1;
        if(dp6[m][n] != 0) return dp6[m][n];
        return dp6[m][n] = path(m , n-1) + path(m-1 , n);
    }

    /**
     * Counts unique paths in an m×n grid using tabulation (bottom-up DP).
     * 
     * <h3>Problem Description:</h3>
     * In an m×n grid, you start at the top-left (0,0) and want to reach the bottom-right (m-1,n-1).
     * You can only move right or down. Count the total number of unique paths.
     * This is the iterative (tabulation) version of {@link #numberOfPaths(int, int)}.
     * 
     * <h3>DP Approach (Tabulation):</h3>
     * <ul>
     *   <li><b>Strategy:</b> Fill the DP table from top-left to bottom-right</li>
     *   <li><b>State:</b> dp7[i][j] = number of ways to reach cell (i,j)</li>
     *   <li><b>Recurrence:</b> dp7[i][j] = dp7[i-1][j] + dp7[i][j-1]</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * To reach cell (i,j), we can come from:
     * - Cell above (i-1,j) by moving down
     * - Cell to the left (i,j-1) by moving right
     * 
     * dp7[i][j] = dp7[i-1][j] + dp7[i][j-1]
     * 
     * Base cases:
     *   dp7[i][0] = 1 for all i (only one way: go down)
     *   dp7[0][j] = 1 for all j (only one way: go right)
     * </pre>
     * 
     * <h3>Example: uniquePaths(3, 3)</h3>
     * <pre>
     * Initialize dp7 as 3×3 table
     * 
     * Fill row 0 and column 0 with 1s:
     * 1 1 1
     * 1 ? ?
     * 1 ? ?
     * 
     * Fill interior cells:
     * dp7[1][1] = dp7[0][1] + dp7[1][0] = 1 + 1 = 2
     * dp7[1][2] = dp7[0][2] + dp7[1][1] = 1 + 2 = 3
     * dp7[2][1] = dp7[1][1] + dp7[2][0] = 2 + 1 = 3
     * dp7[2][2] = dp7[1][2] + dp7[2][1] = 3 + 3 = 6
     * 
     * Final DP table:
     * 1 1 1
     * 1 2 3
     * 1 3 6
     * 
     * Result: dp7[2][2] = 6
     * </pre>
     * 
     * <h3>DP Table Visualization:</h3>
     * <pre>
     * Grid Layout (m=3, n=3):
     *    (0,0) (0,1) (0,2)
     *    (1,0) (1,1) (1,2)
     *    (2,0) (2,1) (2,2)
     * 
     * DP Values (ways to reach each cell):
     *    1  1  1
     *    1  2  3
     *    1  3  6
     * 
     * Interpretation:
     * - (0,0): 1 way (start here)
     * - (0,1): 1 way (only go right)
     * - (1,1): 2 ways (RD or DR)
     * - (2,2): 6 ways (all paths to bottom-right)
     * </pre>
     * 
     * <h3>Time Complexity: O(m × n)</h3>
     * Fill each cell once in the grid: m rows × n columns
     * 
     * <h3>Space Complexity: O(m × n)</h3>
     * 2D dp7 array of size m×n
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li><b>Space Optimization:</b> Use only 1 row at a time (O(n) space)
     *       since each cell only depends on row above and cell to the left</li>
     *   <li><b>Mathematical:</b> This is actually C(m+n-2, m-1) combinatorial problem</li>
     * </ul>
     * 
     * @param m number of rows
     * @param n number of columns
     * @return total number of unique paths from top-left to bottom-right
     * @see #numberOfPaths(int, int) for memoization approach
     */
    public int uniquePaths(int m, int n) {
        int dp7[][] = new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i == 0 || j == 0) dp7[i][j] = 1;
                else dp7[i][j] = dp7[i-1][j] + dp7[i][j-1];
            }
        }

        return dp7[m-1][n-1];
    }

    static int dp7[][];
    
    /**
     * Finds the minimum sum path in a grid from top-left to bottom-right.
     * 
     * <h3>Problem Description:</h3>
     * Given an m×n grid with non-negative integers, find a path from top-left (0,0)
     * to bottom-right (m-1,n-1) such that the sum of numbers along the path is minimized.
     * You can only move right or down.
     * 
     * Real-world: Traverse a map with terrain costs, finding the cheapest route.
     * 
     * <h3>DP Approach (Tabulation):</h3>
     * <ul>
     *   <li><b>State:</b> dp7[i][j] = minimum path sum from (0,0) to (i,j)</li>
     *   <li><b>Recurrence:</b> dp7[i][j] = grid[i][j] + min(dp7[i-1][j], dp7[i][j-1])</li>
     *   <li><b>Strategy:</b> Each cell = its cost + best path from above or left</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * dp7[i][j] = grid[i][j] + min(
     *   dp7[i-1][j],  // best sum from top
     *   dp7[i][j-1]   // best sum from left
     * )
     * 
     * Base cases:
     *   dp7[0][0] = grid[0][0] (start)
     *   First row: dp7[0][j] = grid[0][j] + dp7[0][j-1] (can only go right)
     *   First column: dp7[i][0] = grid[i][0] + dp7[i-1][0] (can only go down)
     * </pre>
     * 
     * <h3>Example: minPathSum([[1,3,1],[1,5,1],[4,2,1]])</h3>
     * <pre>
     * Grid:
     * 1 3 1
     * 1 5 1
     * 4 2 1
     * 
     * Build DP table:
     * 
     * dp7[0][0] = 1
     * dp7[0][1] = 1 + 3 = 4
     * dp7[0][2] = 4 + 1 = 5
     * 
     * dp7[1][0] = 1 + 1 = 2
     * dp7[1][1] = 5 + min(4, 2) = 5 + 2 = 7
     * dp7[1][2] = 1 + min(5, 7) = 1 + 5 = 6
     * 
     * dp7[2][0] = 4 + 2 = 6
     * dp7[2][1] = 2 + min(7, 6) = 2 + 6 = 8
     * dp7[2][2] = 1 + min(6, 8) = 1 + 6 = 7
     * 
     * Final DP table:
     * 1  4  5
     * 2  7  6
     * 6  8  7
     * 
     * Result: dp7[2][2] = 7
     * Best path: (0,0)→(0,1)→(0,2)→(1,2)→(2,2) with costs 1+3+1+1+1=7
     * Or: (0,0)→(1,0)→(1,1)→(1,2)→(2,2) with costs 1+1+5+1+1=9
     * Or: (0,0)→(1,0)→(2,0)→(2,1)→(2,2) with costs 1+1+4+2+1=9
     * Minimum is 7.
     * </pre>
     * 
     * <h3>Time Complexity: O(m × n)</h3>
     * Each cell computed once in a two-nested loop.
     * 
     * <h3>Space Complexity: O(m × n)</h3>
     * 2D dp7 array of size m×n
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li><b>Space Optimization:</b> Use only 1D array (O(n) space)
     *       by processing row by row and updating in-place</li>
     *   <li><b>Path Reconstruction:</b> Can store parent pointers to trace the actual path</li>
     * </ul>
     * 
     * @param grid m×n matrix of non-negative integers
     * @return minimum sum of any path from top-left to bottom-right
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length; int n = grid[0].length;
        dp7 = new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i==0 && j==0) dp7[i][j] = grid[i][j];
                else if(i==0) dp7[i][j] = grid[i][j] + dp7[i][j-1];
                else if(j==0) dp7[i][j] = grid[i][j] + dp7[i-1][j];
                else {
                    dp7[i][j] = grid[i][j] + Math.min(dp7[i][j-1] , dp7[i-1][j]);
                }
            }
        }
        return dp7[m-1][n-1];
    }

    /**
     * Counts unique paths in a grid with obstacles using tabulation.
     * 
     * <h3>Problem Description:</h3>
     * Similar to {@link #uniquePaths(int, int)}, but the grid has obstacles.
     * Some cells are blocked (arr[i][j] == 1), and you cannot pass through them.
     * Count the number of unique paths from top-left to bottom-right,
     * avoiding obstacles and only moving right or down.
     * 
     * Real-world: Navigate a grid with walls, finding all possible routes.
     * 
     * <h3>DP Approach (Tabulation):</h3>
     * <ul>
     *   <li><b>State:</b> dp[i][j] = number of ways to reach (i,j)</li>
     *   <li><b>Recurrence:</b> if (i,j) is blocked, dp[i][j] = 0; else standard sum</li>
     *   <li><b>Key:</b> Check for obstacles FIRST before computing from neighbors</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * If arr[i][j] == 1 (obstacle):
     *   dp[i][j] = 0
     * Else:
     *   dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * 
     * Base case:
     *   dp[0][0] = 1 if arr[0][0] != 1, else 0
     * </pre>
     * 
     * <h3>Example: uniquePathsWithObstacles([[0,0,0],[0,1,0],[0,0,0]])</h3>
     * <pre>
     * Grid (0=free, 1=obstacle):
     * 0 0 0
     * 0 1 0
     * 0 0 0
     * 
     * Build DP table:
     * 
     * dp[0][0] = 1       (free cell, start)
     * dp[0][1] = 1       (free cell, only from left)
     * dp[0][2] = 1       (free cell, only from left)
     * 
     * dp[1][0] = 1       (free cell, only from top)
     * dp[1][1] = 0       (OBSTACLE! Set to 0)
     * dp[1][2] = 0+1 = 1 (free cell, can only come from left)
     * 
     * dp[2][0] = 1       (free cell, only from top)
     * dp[2][1] = 1+0 = 1 (free cell, from top or left)
     * dp[2][2] = 1+1 = 2 (free cell, from top or left)
     * 
     * Final DP table:
     * 1 1 1
     * 1 0 1
     * 1 1 2
     * 
     * Result: dp[2][2] = 2
     * The 2 paths:
     * 1. (0,0)→(0,1)→(0,2)→(1,2)→(2,2)
     * 2. (0,0)→(1,0)→(2,0)→(2,1)→(2,2)
     * (Cannot use (1,1) due to obstacle)
     * </pre>
     * 
     * <h3>Critical Note - Why Order Matters:</h3>
     * <pre>
     * WRONG approach (checks border first):
     * if(i == 0 || j == 0) dp[i][j] = 1;
     * else if(arr[i][j] == 1) dp[i][j] = 0;
     * 
     * Problem: In arr = [[0,1,0]], this would set dp[0][2] = 1 even though
     * it's unreachable because there's an obstacle at (0,1).
     * 
     * CORRECT approach (checks obstacle first):
     * if(arr[i][j] == 1) dp[i][j] = 0;              // Obstacle blocks everything
     * else if(i == 0 && j == 0) dp[i][j] = 1;        // Start
     * else if(i == 0) dp[i][j] = dp[i][j-1];         // Only from left
     * else if(j == 0) dp[i][j] = dp[i-1][j];         // Only from top
     * else dp[i][j] = dp[i-1][j] + dp[i][j-1];       // From either
     * </pre>
     * 
     * <h3>Time Complexity: O(m × n)</h3>
     * Visit each cell once.
     * 
     * <h3>Space Complexity: O(m × n)</h3>
     * 2D DP array of size m×n
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li><b>Space Optimization:</b> Use O(n) space with rolling array</li>
     *   <li><b>In-place (careful):</b> Can modify input arr instead of using separate dp</li>
     * </ul>
     * 
     * @param arr m×n grid where 1 = obstacle, 0 = free cell
     * @return number of unique paths avoiding obstacles
     */
    public int uniquePathsWithObstacles(int[][] arr) {
        int m = arr.length; int n = arr[0].length;
        int dp[][] = new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(arr[i][j] == 1) dp[i][j] = 0;
                else if(i == 0 && j == 0) dp[i][j] = 1;
                else if(i == 0) dp[i][j] = dp[i][j-1];
                else if(j == 0) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
        return dp[m-1][n-1];
    }

    
    /**
     * Counts the total number of square submatrices containing only 1s.
     * 
     * <h3>Problem Description:</h3>
     * Given an m×n binary matrix (containing only 0s and 1s), count the total number
     * of square submatrices that are filled entirely with 1s.
     * 
     * Example: In the matrix [[1,0,1],[1,1,0],[1,1,0]], there are 3 squares of size 1×1,
     * 1 square of size 2×2, for a total of 4 squares.
     * 
     * Real-world: Find all square regions of interest in a binary image.
     * 
     * <h3>DP Approach (In-place Tabulation):</h3>
     * <ul>
     *   <li><b>State:</b> arr[i][j] is modified to store the side length of max square with (i,j) as bottom-right</li>
     *   <li><b>Recurrence:</b> If arr[i][j]==1, arr[i][j] = 1 + min(left, top, diagonal)</li>
     *   <li><b>Count:</b> Sum all arr[i][j] values to get total count of all squares</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * For each cell arr[i][j]:
     * If arr[i][j] == 1 (contains 1):
     *   arr[i][j] = 1 + min(
     *     arr[i-1][j],       // square above
     *     arr[i][j-1],       // square to the left
     *     arr[i-1][j-1]      // square diagonally
     *   )
     * 
     * This represents the side length of the maximal square with (i,j) at bottom-right.
     * 
     * If arr[i][j] == 0:
     *   arr[i][j] stays 0
     * </pre>
     * 
     * <h3>Example: countSquares([[1,0,1],[1,1,0],[1,1,0]])</h3>
     * <pre>
     * Initial matrix:
     * 1 0 1
     * 1 1 0
     * 1 1 0
     * 
     * Build DP table (modifying matrix in place):
     * 
     * Border cells (row 0, col 0) stay unchanged:
     * 1 0 1
     * 1 ? 0
     * 1 ? 0
     * 
     * arr[1][1] = 1 + min(arr[0][1], arr[1][0], arr[0][0])
     *           = 1 + min(0, 1, 1)
     *           = 1 + 0 = 1
     * 
     * arr[2][1] = 1 + min(arr[1][1], arr[2][0], arr[1][0])
     *           = 1 + min(1, 1, 1)
     *           = 1 + 1 = 2
     * 
     * Final modified matrix:
     * 1 0 1
     * 1 1 0
     * 1 2 0
     * 
     * Count = 1 + 0 + 1 + 1 + 1 + 0 + 1 + 2 + 0 = 7
     * 
     * This counts:
     * - 6 squares of size 1×1 (one for each 1 in matrix)
     * - 1 square of size 2×2 (at position [1,0] to [2,1])
     * Total = 7
     * </pre>
     * 
     * <h3>DP Table Interpretation:</h3>
     * <pre>
     * After computation, arr[i][j] represents:
     * - 0: No square can be formed
     * - 1: One 1×1 square exists at (i,j)
     * - 2: One 2×2 square exists with (i,j) at bottom-right (counts both the 2×2 and four 1×1s)
     * - 3: One 3×3 square (counts all 1s in the 3×3 region)
     * 
     * Sum of all values = total count of all possible squares at all positions
     * </pre>
     * 
     * <h3>Time Complexity: O(m × n)</h3>
     * Visit each cell once, each update is O(1).
     * 
     * <h3>Space Complexity: O(1)</h3>
     * Modifies the input matrix in place, no additional space.
     * 
     * <h3>Important Note:</h3>
     * This method modifies the input array! If you need the original array,
     * make a deep copy before calling this method.
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li><b>Preserve Input:</b> Use separate DP array instead of modifying arr</li>
     *   <li><b>Space Trade-off:</b> Current O(1) space but modifies input; can use O(n) for preservation</li>
     * </ul>
     * 
     * @param arr m×n binary matrix (1s and 0s), will be modified in place
     * @return total count of all square submatrices containing only 1s
     */
    public int countSquares(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int count = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i!=0 && j!=0){
                    if(arr[i][j] == 1){
                        arr[i][j] += Math.min(arr[i-1][j] , Math.min(arr[i][j-1] , arr[i-1][j-1]));
                    }
                }

                count += arr[i][j];
            }
        }
        return count;
    }

    /**
     * Solves the 0/1 Knapsack problem using memoization (top-down DP).
     * <b>0/1 variant:</b> Each item can be selected at most once.
     * 
     * <h3>Problem Description:</h3>
     * You have a knapsack with a weight capacity. There are n items, each with a
     * value and weight. Select items to maximize total value without exceeding capacity.
     * 
     * Real-world: Pack a backpack with items to maximize value while respecting weight limit.
     * Constraints: Can pick each item 0 or 1 time (not unlimited).
     * 
     * <h3>DP Approach (Memoization):</h3>
     * <ul>
     *   <li><b>State:</b> dp[i][w] = max value using items 0..i with weight limit w</li>
     *   <li><b>Recurrence:</b> Choose max of including item i or skipping it</li>
     *   <li><b>Transition:</b> If we include item i, move to (i+1, w-wt[i]), else (i+1, w)</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * dp[i][w] = max(
     *   skip:     dp[i+1][w],                        // Don't take item i
     *   pick:     val[i] + dp[i+1][w - wt[i]]       // Take item i (if wt[i] <= w)
     * )
     * 
     * Base case:
     *   dp[n][w] = 0 (no more items)
     * </pre>
     * 
     * <h3>Example: knapsack(10, [60, 100, 120], [1, 2, 3])</h3>
     * <pre>
     * Capacity = 10
     * Items: [(val=60,wt=1), (val=100,wt=2), (val=120,wt=3)]
     * 
     * Optimal selection: All 3 items fit! (1+2+3=6 ≤ 10)
     * Maximum value = 60 + 100 + 120 = 280
     * 
     * The computation tree (simplified):
     * helper(0, 10):
     *   skip item 0: helper(1, 10)
     *   pick item 0: 60 + helper(1, 9)
     * 
     * Both branches eventually lead to including all items since they all fit.
     * </pre>
     * 
     * <h3>Time Complexity: O(n × capacity)</h3>
     * Each subproblem (i, w) computed once via memoization.
     * There are n items × capacity states = O(n × capacity) states.
     * 
     * <h3>Space Complexity: O(n × capacity)</h3>
     * dp[][] array of size n × (capacity+1) + recursion depth O(n)
     * 
     * <h3>Key Difference from Unbounded Knapsack:</h3>
     * <ul>
     *   <li><b>0/1 Knapsack:</b> After picking item i, move to (i+1, new_capacity)
     *       Item i cannot be picked again</li>
     *   <li><b>Unbounded Knapsack:</b> After picking item i, stay at (i, new_capacity)
     *       Item i can be picked unlimited times</li>
     *   <li>See {@link #knapSack(int[], int[], int)} for unbounded variant</li>
     * </ul>
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li><b>Space Optimization:</b> Use 1D array (O(capacity) space) instead of 2D</li>
     *   <li><b>Tabulation:</b> Convert to bottom-up iterative approach</li>
     *   <li><b>Item Selection Tracking:</b> Store which items were selected</li>
     * </ul>
     * 
     * @param capacity maximum weight capacity
     * @param val array of item values
     * @param wt array of item weights
     * @return maximum value achievable within capacity (0/1 variant: each item at most once)
     * @see #helper(int, int, int[], int[], int[][]) for the recursive computation
     * @see #knapSack(int[], int[], int) for unbounded knapsack variant
     */
    static int knapsack(int capacity, int val[], int wt[]) {
        int n = val.length;
        int dp[][] = new int[n][capacity + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return helper(0, capacity, val, wt, dp);
    }

    /**
     * Helper method: Recursively computes max knapsack value using memoization (0/1 variant).
     * 
     * <h3>Problem Description:</h3>
     * Starting from item index i with remaining capacity w, what's the maximum value
     * we can achieve for the 0/1 knapsack problem?
     * 
     * <h3>DP Logic:</h3>
     * At each item, we have two choices:
     * 1. Skip this item: get the best value from remaining items
     * 2. Pick this item (if it fits): get this item's value + best from remaining items with reduced capacity
     * 
     * Choose the option that gives maximum value.
     * 
     * <h3>Algorithm:</h3>
     * <pre>
     * 1. Base: if i == n, return 0 (no more items)
     * 2. Check cache: if dp[i][w] != -1, return dp[i][w]
     * 3. Skip option: helper(i+1, w)
     * 4. Pick option: if wt[i] <= w, get val[i] + helper(i+1, w - wt[i])
     * 5. Store and return: dp[i][w] = max(pick, skip)
     * </pre>
     * 
     * <h3>Key Difference in Item Transition:</h3>
     * Notice: After picking item i, we move to helper(i+1, ...), NOT helper(i, ...)
     * This ensures each item is used at most once (0/1 constraint).
     * 
     * Compare with {@link #helper1(int, int, int[], int[], int[][])} which uses helper(i, ...),
     * allowing unlimited picks of the same item.
     * 
     * <h3>Time Complexity: O(n × capacity)</h3>
     * Each state (i, w) computed once.
     * 
     * <h3>Space Complexity: O(n × capacity)</h3>
     * 2D dp array + recursion depth O(n)
     * 
     * @param i current item index
     * @param capacity remaining capacity
     * @param val array of item values
     * @param wt array of item weights
     * @param dp memoization cache
     * @return maximum value achievable with remaining items and capacity
     */
    static int helper(int i, int capacity, int[] val, int[] wt, int[][] dp) {
        if (i == val.length) return 0;

        if (dp[i][capacity] != -1) return dp[i][capacity];

        int skip = helper(i + 1, capacity, val, wt, dp);

        int pick = 0;
        if (wt[i] <= capacity) {
            pick = val[i] + helper(i + 1, capacity - wt[i], val, wt, dp);
        }
        return dp[i][capacity] = Math.max(pick, skip);
    }

    /**
     * Solves the Unbounded Knapsack problem using memoization.
     * <b>Unbounded variant:</b> Each item can be selected unlimited times.
     * 
     * <h3>Problem Description:</h3>
     * Similar to the 0/1 knapsack, but each item can be picked multiple times
     * (as long as there's remaining capacity). Maximize value without exceeding capacity.
     * 
     * Real-world: Coin change problem - make change with unlimited coins.
     * Or packing items where you have infinite stock of each type.
     * 
     * <h3>DP Approach (Memoization):</h3>
     * <ul>
     *   <li><b>State:</b> dp[i][w] = max value using items 0..i with capacity w</li>
     *   <li><b>Recurrence:</b> Same as 0/1, but after picking an item, we can pick it again</li>
     *   <li><b>Key Difference:</b> After picking item i, stay at index i (not i+1)</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * dp[i][w] = max(
     *   skip:     dp[i+1][w],                    // Don't use this item type
     *   pick:     val[i] + dp[i][w - wt[i]]     // Use this item, can use again (stay at i!)
     * )
     * 
     * Base case:
     *   dp[n][w] = 0 (no more item types)
     * </pre>
     * 
     * <h3>Example: knapSack([60, 100, 120], [1, 2, 3], 10)</h3>
     * <pre>
     * Capacity = 10
     * Items (can use multiple times): [(60,1), (100,2), (120,3)]
     * 
     * Optimal: Use item 1 (val=100,wt=2) five times:
     * 5 × 100 = 500 value, 5 × 2 = 10 weight (exactly fits!)
     * 
     * Computation:
     * helper1(0, 10):
     *   skip item 0: helper1(1, 10)
     *   pick item 0: 60 + helper1(0, 9)  ← Note: stays at 0, not 1!
     * 
     * With unlimited picks, the optimal solution is 5 of item 1.
     * </pre>
     * 
     * <h3>Time Complexity: O(n × capacity)</h3>
     * Each state computed once via memoization.
     * 
     * <h3>Space Complexity: O(n × capacity)</h3>
     * 2D dp array + recursion depth O(n)
     * 
     * <h3>0/1 vs Unbounded Comparison:</h3>
     * <table border="1">
     * <tr>
     *   <th>Aspect</th>
     *   <th>0/1 Knapsack</th>
     *   <th>Unbounded Knapsack</th>
     * </tr>
     * <tr>
     *   <td>Item Reuse</td>
     *   <td>Each at most once</td>
     *   <td>Each unlimited times</td>
     * </tr>
     * <tr>
     *   <td>After Pick</td>
     *   <td>helper(i+1, ...)</td>
     *   <td>helper(i, ...)</td>
     * </tr>
     * <tr>
     *   <td>Real-world</td>
     *   <td>Selecting from unique items</td>
     *   <td>Coin change, item types in stock</td>
     * </tr>
     * </table>
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li><b>Space Optimization:</b> Use 1D array (O(capacity) space)</li>
     *   <li><b>Tabulation:</b> Convert to iterative bottom-up approach</li>
     *   <li><b>Coin Count:</b> Can track number of each item used</li>
     * </ul>
     * 
     * @param val array of item values
     * @param wt array of item weights
     * @param capacity maximum weight capacity
     * @return maximum value achievable (unbounded: each item can be used unlimited times)
     * @see #helper1(int, int, int[], int[], int[][]) for recursive computation
     * @see #knapsack(int, int[], int[]) for 0/1 knapsack variant
     */
    public int knapSack(int val[], int wt[], int capacity) {
        int n = val.length;
        int dp[][] = new int[n][capacity + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return helper1(0, capacity, val, wt, dp);
    }

    /**
     * Helper method: Recursively computes max knapsack value using memoization (unbounded variant).
     * 
     * <h3>Problem Description:</h3>
     * Starting from item type i with remaining capacity w, what's the maximum value
     * we can achieve when each item type can be used unlimited times?
     * 
     * <h3>DP Logic:</h3>
     * At each item type, we have two choices:
     * 1. Skip this item type: get best value from remaining item types
     * 2. Pick this item type (if it fits): get this item's value + best from same items with reduced capacity
     * 
     * The key difference from 0/1 knapsack: after picking, we call helper1(i, ...) not helper1(i+1, ...),
     * meaning we can pick the same item type again!
     * 
     * <h3>Algorithm:</h3>
     * <pre>
     * 1. Base: if i == n, return 0 (no more item types)
     * 2. Check cache: if dp[i][w] != -1, return dp[i][w]
     * 3. Skip option: helper1(i+1, w)  ← Move to next item type
     * 4. Pick option: if wt[i] <= w, get val[i] + helper1(i, w - wt[i])  ← Stay at same item!
     * 5. Store and return: dp[i][w] = max(pick, skip)
     * </pre>
     * 
     * <h3>Key Difference: Stay at i, not i+1!</h3>
     * In 0/1 knapsack: pick → helper(i+1, w-wt[i])  (move to next item, can't reuse current)
     * In Unbounded:    pick → helper(i, w-wt[i])    (stay at current item, can reuse!)
     * 
     * <h3>Example: helper1(0, 10, [60,100,120], [1,2,3])</h3>
     * <pre>
     * Starting at item 0 with capacity 10:
     * 
     * skip: helper1(1, 10)         ← Try other items
     * pick: 60 + helper1(0, 9)     ← Pick item 0, can pick 0 again
     * 
     * If we keep picking item 0:
     * 60 + (60 + helper1(0, 8))
     * = 60 + 60 + (60 + helper1(0, 7))
     * = ...
     * = 600  (10 items of value 60 each)
     * 
     * But better: switch to item 1 and get 500 (5 items of value 100 each)
     * </pre>
     * 
     * <h3>Time Complexity: O(n × capacity)</h3>
     * Each state (i, w) computed once.
     * 
     * <h3>Space Complexity: O(n × capacity)</h3>
     * 2D dp array + recursion depth O(n)
     * 
     * <h3>Common Application:</h3>
     * Unbounded knapsack is the foundation for the Coin Change problem:
     * "Find minimum coins to make amount X" or "Count ways to make amount X"
     * 
     * @param i current item type index
     * @param capacity remaining capacity
     * @param val array of item values
     * @param wt array of item weights
     * @param dp memoization cache
     * @return maximum value achievable (items can be reused)
     */
    static int helper1(int i, int capacity, int[] val, int[] wt, int[][] dp) {
        if (i == val.length) return 0;

        if (dp[i][capacity] != -1) return dp[i][capacity];

        int skip = helper1(i + 1, capacity, val, wt, dp);

        int pick = 0;
        if (wt[i] <= capacity) {
            pick = val[i] + helper1(i, capacity - wt[i], val, wt, dp);
        }
        return dp[i][capacity] = Math.max(pick, skip);
    }

    /**
     * Solves the Subset Sum problem: can we select elements to reach target sum?
     * 
     * <h3>Problem Description:</h3>
     * Given an array of integers and a target sum, determine if there exists a subset
     * of the array whose elements sum to exactly the target sum.
     * 
     * Real-world: Partition problem, exact change problem, resource allocation.
     * 
     * <h3>DP Approach (Memoization):</h3>
     * <ul>
     *   <li><b>State:</b> dp[i][target] = can we make sum 'target' using elements 0..i?</li>
     *   <li><b>Recurrence:</b> Either include element i or skip it</li>
     *   <li><b>Encoding:</b> Store 1 for true, 0 for false (to use -1 for uncomputed)</li>
     * </ul>
     * 
     * <h3>Recurrence Relation:</h3>
     * <pre>
     * dp[i][target] = dp[i+1][target] || dp[i+1][target - arr[i]]
     * 
     * Explanation:
     * - dp[i+1][target]: can we make 'target' without element i?
     * - dp[i+1][target - arr[i]]: can we make 'target' by including element i?
     * 
     * Base case:
     *   If target == 0, return true (empty subset sums to 0)
     *   If i == arr.length and target != 0, return false (no more elements)
     * </pre>
     * 
     * <h3>Example: subset([3, 34, 4, 12, 5, 2], 9)</h3>
     * <pre>
     * Can we find a subset that sums to 9?
     * Answer: YES, [4, 5]
     * 
     * Call Tree (simplified):
     * helper2(0, 9):
     *   skip 3: helper2(1, 9)
     *   pick 3: helper2(1, 6)
     * 
     * Eventually explores:
     * ...
     * helper2(2, 9):
     *   skip 34: helper2(3, 9)
     *   pick 34: not possible (34 > 9)
     * 
     * helper2(3, 9):
     *   skip 12: helper2(4, 9)
     *   pick 12: not possible (12 > 9)
     * 
     * helper2(4, 9):
     *   skip 5: helper2(5, 9)
     *   pick 5: helper2(5, 4)
     *     helper2(5, 4):
     *       skip 2: helper2(6, 4) → false
     *       pick 2: helper2(6, 2) → false
     *       returns false
     *   
     *   helper2(5, 9):
     *     skip 2: helper2(6, 9) → false
     *     pick 2: helper2(6, 7) → false
     *     returns false
     *   
     *   Final: pick 5 gave false, skip 5 gave false → false at this node
     *   
     *   BUT: Actually, [4, 5] should work from earlier nodes
     * </pre>
     * 
     * <h3>Time Complexity: O(n × sum)</h3>
     * Each subproblem (i, target) computed once.
     * There are n items × (sum+1) possible targets = O(n × sum).
     * 
     * <h3>Space Complexity: O(n × sum)</h3>
     * 2D dp array of size n × (target+1) + recursion depth O(n)
     * 
     * <h3>Related Problems:</h3>
     * <ul>
     *   <li><b>Partition Equal Subset Sum:</b> Can we split array into two equal-sum subsets?
     *       Answer: subset(arr, sum/2) where sum is total</li>
     *   <li><b>Count Subsets with Given Sum:</b> How many subsets sum to target?
     *       Modify to return count instead of boolean</li>
     *   <li><b>Minimum Subset Sum Difference:</b> Split into two subsets to minimize difference</li>
     * </ul>
     * 
     * <h3>Optimization Opportunities:</h3>
     * <ul>
     *   <li><b>Space Optimization:</b> Use 1D DP array of size (sum+1)</li>
     *   <li><b>Tabulation:</b> Convert to bottom-up iterative approach</li>
     *   <li><b>Early Exit:</b> Return true as soon as subset found</li>
     * </ul>
     * 
     * @param arr array of positive integers
     * @param target the target sum to achieve
     * @return true if a subset with sum equal to target exists, false otherwise
     * @see #helper2(int, int[], int, int[][]) for the recursive computation
     */
    public boolean subset(int arr[] , int target){
        int dp[][] = new int[arr.length][target+1];
        Arrays.fill(dp,-1);
        return helper2(0,arr,target,dp);
    }

    /**
     * Helper method: Recursively determines if target sum is achievable using memoization.
     * 
     * <h3>Problem Description:</h3>
     * Starting from index i in the array, can we select elements to reach the target sum?
     * 
     * <h3>DP Logic:</h3>
     * At each element arr[i], we have two choices:
     * 1. Skip this element: try to make 'target' from remaining elements
     * 2. Pick this element (if value ≤ target): try to make 'target - arr[i]' from remaining elements
     * 
     * Return true if either option succeeds.
     * 
     * <h3>Algorithm:</h3>
     * <pre>
     * 1. Base: if i == arr.length
     *    - if target == 0, return true (successfully made the sum!)
     *    - else return false (no more elements but target not reached)
     * 
     * 2. Check cache: if dp[i][target] != -1, return (dp[i][target] == 1)
     *    (Tricky: stored 1/0, so check if != 1)
     * 
     * 3. Skip option: helper2(i+1, arr, target)
     * 
     * 4. Pick option: 
     *    - if target - arr[i] < 0, can't pick (would go negative)
     *    - else helper2(i+1, arr, target - arr[i])
     * 
     * 5. Result: ans = pick || skip
     * 
     * 6. Store: dp[i][target] = 1 if ans, else 0
     * 
     * 7. Return ans
     * </pre>
     * 
     * <h3>Example Trace: helper2(0, [3, 34, 4, 12, 5, 2], 9)</h3>
     * <pre>
     * i=0, target=9, arr[0]=3:
     *   skip: helper2(1, [34,4,12,5,2], 9)
     *   pick: 9-3=6 possible, helper2(1, [34,4,12,5,2], 6)
     * 
     * Eventually reaches arr[2]=4 with target=9:
     *   skip: helper2(3, [12,5,2], 9)
     *   pick: 9-4=5, helper2(3, [12,5,2], 5)
     * 
     * Reaches arr[4]=5 with target=5:
     *   pick: 5-5=0, helper2(5, [2], 0)
     *     i=5, target=0 → base case → return true!
     * 
     * Backtrack returns true all the way up.
     * </pre>
     * 
     * <h3>Why dp[i][target] Stores 1/0:</h3>
     * The dp array is initialized with -1 to mark "not computed".
     * When computing, we store the boolean result as 1 (true) or 0 (false).
     * So: dp[i][target] = -1 means "not computed",
     *     dp[i][target] = 1 means "true",
     *     dp[i][target] = 0 means "false".
     * 
     * To retrieve: return (dp[i][target] == 1) to convert back to boolean.
     * 
     * <h3>Time Complexity: O(n × target)</h3>
     * Each state (i, target) computed once.
     * 
     * <h3>Space Complexity: O(n × target)</h3>
     * 2D dp array + recursion depth O(n)
     * 
     * @param i current index in array
     * @param arr array of positive integers
     * @param target remaining target sum to achieve
     * @param dp memoization cache (stores 1/0, -1 for uncomputed)
     * @return true if target sum is achievable from elements at indices i..n-1
     */
    private boolean helper2(int i, int[] arr,int target,int dp[][]) {
        if(i==arr.length){
            if(target==0)return true;
                else return false;
        }
        if(dp[i][target] != -1) return (dp[i][target] == 1);
        boolean ans = false;
        boolean skip = helper2(i+1, arr , target,dp);
        if(target-arr[i] < 0) ans = skip;
        else{
            boolean pick = helper2(i+1, arr  ,target-arr[i],dp);
            ans = pick || skip;
        }
        if(ans) dp[i][target] = 1;
        else dp[i][target] = 0;
        return ans;
    }

    public static void main(String[] args) {

    }
}
