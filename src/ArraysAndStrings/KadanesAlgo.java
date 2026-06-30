package ArraysAndStrings;

import java.nio.charset.MalformedInputException;

public class KadanesAlgo {

    /**
     * ========== KADANE'S ALGORITHM - MAXIMUM SUBARRAY SUM ==========
     * 
     * EXPLANATION:
     * Finds the maximum sum of any contiguous subarray using dynamic programming.
     * Key Idea: At each element, decide whether to extend the existing subarray or start fresh.
     * If current sum becomes negative, reset it to 0 (discard negative prefix).
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     * 
     * ALGORITHM STEPS:
     * 1. Keep track of current sum and maximum sum found so far
     * 2. For each element, add it to current sum
     * 3. Update maximum if current sum is greater
     * 4. If current sum becomes negative, reset to 0
     * 
     * EXAMPLES:
     * Input: [5, -3, 5]
     * - i=0: currsum=5, maxSubarray=5
     * - i=1: currsum=2, maxSubarray=5
     * - i=2: currsum=7, maxSubarray=7
     * Output: 7 (subarray [5, -3, 5])
     * 
     * Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
     * - Starts: currsum=0
     * - At 1: currsum=1, max=1
     * - At -3: currsum=-2, max=1
     * - At 4: currsum=4, max=4
     * - At -1: currsum=3, max=4
     * - At 2: currsum=5, max=5
     * - At 1: currsum=6, max=6 (subarray [4, -1, 2, 1])
     * Output: 6
     * 
     * Input: [-5, -2, -8, -1, -4]
     * - Finds least negative: -1
     * Output: -1
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int currsum = nums[0];

        for(int i=1; i<nums.length; i++){
            currsum = Math.max(nums[i] , nums[i] + currsum);
            max = Math.max(max , currsum);
        }

        return max;
    }

    /**
     * ========== BEST TIME TO BUY AND SELL STOCK ==========
     * 
     * EXPLANATION:
     * Find maximum profit from buying and selling stock once.
     * Must buy BEFORE selling. Track minimum price seen so far and calculate
     * profit at each position by selling at current price.
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     * 
     * ALGORITHM STEPS:
     * 1. Track the minimum price encountered so far
     * 2. For each price, calculate profit if we sell at current price
     * 3. Update maximum profit if current profit is better
     * 
     * EXAMPLES:
     * Input: [7, 1, 5, 3, 6, 4]
     * - i=0: minPrice=7, profit=0
     * - i=1: minPrice=1, profit=0
     * - i=2: profit=5-1=4, maxProfit=4
     * - i=3: profit=3-1=2, maxProfit=4
     * - i=4: profit=6-1=5, maxProfit=5
     * - i=5: profit=4-1=3, maxProfit=5
     * Output: 5 (buy at 1, sell at 6)
     * 
     * Input: [7, 6, 4, 3, 1]
     * - Prices only decrease, no profit possible
     * Output: 0
     * 
     * Input: [2, 4, 1, 7, 5, 11]
     * - Best: buy at 1, sell at 11
     * Output: 10
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i=0; i<prices.length; i++){
            if(prices[i] < minPrice) minPrice = prices[i];
            else if(prices[i] - minPrice > maxProfit) maxProfit = prices[i] - minPrice;
        }
        return maxProfit;
    }

    /**
     * ========== MAXIMUM PRODUCT SUBARRAY ==========
     * 
     * EXPLANATION:
     * Find the contiguous subarray with the largest product.
     * Challenge: Negative numbers can flip signs. A negative number times
     * a small negative number can become a large positive.
     * Solution: Track both maximum and minimum products at each position
     * (min can become max when multiplied by negative).
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     * 
     * ALGORITHM STEPS:
     * 1. Initialize maxProd and minProd with first element
     * 2. For each element starting from index 1:
     *    - Save previous max and min
     *    - maxProd = max(current, current * prevMax, current * prevMin)
     *    - minProd = min(current, current * prevMax, current * prevMin)
     * 3. Update result with current maxProd at each iteration
     * 
     * EXAMPLES:
     * Input: [2, 3, -2, 4]
     * - i=0: maxProd=2, minProd=2, result=2
     * - i=1: curr=3, maxProd=max(3, 2*3, 2*3)=6, minProd=min(3, 2*3, 2*3)=3, result=6
     * - i=2: curr=-2, maxProd=max(-2, 6*-2, 3*-2)=-2, minProd=min(-2, 6*-2, 3*-2)=-12, result=6
     * - i=3: curr=4, maxProd=max(4, -2*4, -12*4)=48, result=48
     * Output: 48 (subarray [-2, 4] gives -8, but [2, 3, -2, 4] gives 48)
     * 
     * Input: [-2, 3, -4]
     * - i=0: maxProd=-2, minProd=-2, result=-2
     * - i=1: curr=3, maxProd=6, minProd=-6, result=6
     * - i=2: curr=-4, maxProd=24, minProd=-24, result=24
     * Output: 24 (full array)
     * 
     * Input: [0, 2]
     * - i=0: maxProd=0, minProd=0, result=0
     * - i=1: curr=2, maxProd=2, result=2
     * Output: 2
     */
    public static int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];

        for(int i=1; i<nums.length; i++){
            int current = nums[i];

            int prevMax = maxProd;
            int prevMin = minProd;

            maxProd = Math.max(current , Math.max(prevMax * current , prevMin * current));
            minProd = Math.min(current , Math.min(prevMax * current , prevMin * current));

            result = Math.max(result , maxProd);
        }

        return result;
    }

    /**
     * ========== KADANE'S ALGORITHM - STANDARD FORM (MAXIMUM) ==========
     * 
     * EXPLANATION:
     * Classic Kadane's algorithm to find maximum subarray sum.
     * maxEnding = maximum sum of subarray ending at current position
     * maxSoFar = maximum sum seen so far in entire array
     * At each position, decide: extend current subarray or start fresh
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     * 
     * ALGORITHM STEPS:
     * 1. Initialize maxEnding and maxSoFar with first element
     * 2. For each element from index 1:
     *    - maxEnding = max(current element, maxEnding + current element)
     *    - maxSoFar = max(maxSoFar, maxEnding)
     * 3. Return maxSoFar
     * 
     * EXAMPLES:
     * Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
     * - i=0: maxEnding=-2, maxSoFar=-2
     * - i=1: maxEnding=max(1, -2+1)=1, maxSoFar=1
     * - i=2: maxEnding=max(-3, 1-3)=-3, maxSoFar=1
     * - i=3: maxEnding=max(4, -3+4)=4, maxSoFar=4
     * - i=4: maxEnding=max(-1, 4-1)=3, maxSoFar=4
     * - i=5: maxEnding=max(2, 3+2)=5, maxSoFar=5
     * - i=6: maxEnding=max(1, 5+1)=6, maxSoFar=6
     * - i=7: maxEnding=max(-5, 6-5)=1, maxSoFar=6
     * - i=8: maxEnding=max(4, 1+4)=5, maxSoFar=6
     * Output: 6 (subarray [4, -1, 2, 1])
     * 
     * Input: [5, -3, 5]
     * - i=1: maxEnding=max(-3, 5-3)=2, maxSoFar=5
     * - i=2: maxEnding=max(5, 2+5)=7, maxSoFar=7
     * Output: 7
     */
    private static int KadaneMax(int [] nums){
        int maxSoFar = nums[0];
        int maxEnding = nums[0];

        for(int i=1; i<nums.length; i++){
            maxEnding = Math.max(nums[i] , maxEnding + nums[i]);
            maxSoFar = Math.max(maxSoFar , maxEnding);
        }
        return maxSoFar;
    }

    /**
     * ========== KADANE'S ALGORITHM - MINIMUM SUBARRAY SUM ==========
     * 
     * EXPLANATION:
     * Variant of Kadane's algorithm to find minimum subarray sum.
     * minEnding = minimum sum of subarray ending at current position
     * minSoFar = minimum sum seen so far in entire array
     * Same principle as KadaneMax but tracking minimum instead.
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     * 
     * ALGORITHM STEPS:
     * 1. Initialize minEnding and minSoFar with first element
     * 2. For each element from index 1:
     *    - minEnding = min(current element, minEnding + current element)
     *    - minSoFar = min(minSoFar, minEnding)
     * 3. Return minSoFar
     * 
     * EXAMPLES:
     * Input: [1, -3, 2, -1, -2, 1]
     * - i=0: minEnding=1, minSoFar=1
     * - i=1: minEnding=min(-3, 1-3)=-3, minSoFar=-3
     * - i=2: minEnding=min(2, -3+2)=-1, minSoFar=-3
     * - i=3: minEnding=min(-1, -1-1)=-2, minSoFar=-3
     * - i=4: minEnding=min(-2, -2-2)=-4, minSoFar=-4
     * - i=5: minEnding=min(1, -4+1)=-3, minSoFar=-4
     * Output: -4 (subarray [-3, 2, -1, -2])
     * 
     * Input: [5, -3, 5]
     * - i=1: minEnding=min(-3, 5-3)=-3, minSoFar=-3
     * - i=2: minEnding=min(5, -3+5)=2, minSoFar=-3
     * Output: -3
     */
    private static int KadaneMin(int nums[]){
        int minSofar = nums[0];
        int minEnding = nums[0];

        for(int i=1; i<nums.length; i++){
            minEnding = Math.min(nums[i] , minEnding + nums[i]);
            minSofar = Math.min(minSofar , minEnding);
        }
        return minSofar;
    }

    /**
     * ========== MAXIMUM SUBARRAY SUM IN CIRCULAR ARRAY ==========
     * 
     * EXPLANATION:
     * Find maximum subarray sum where array is circular (elements wrap around).
     * Two cases:
     * 1. Non-circular: Maximum subarray doesn't wrap (use Kadane's max)
     * 2. Circular: Maximum subarray wraps around (= total - minimum subarray)
     * 
     * Why circular = total - minimum?
     * If we remove minimum subarray from middle, we get wrapping maximum.
     * Example: [1, -2, 3, 4, 5] → Remove [-2, 3] → Get [1] + [4, 5] wrapped
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     * 
     * ALGORITHM STEPS:
     * 1. Find maximum subarray sum (non-circular)
     * 2. Find minimum subarray sum
     * 3. Calculate total sum of array
     * 4. Circular max = total - minimum
     * 5. Return max(non-circular max, circular max)
     * 6. Edge case: if minSum == totalsum, all elements are negative, return max only
     * 
     * EXAMPLES:
     * Input: [1, -2, 3, 4, 5]
     * - KadaneMax = max([1], [1,-2,3,4,5]) = 11 (non-circular)
     * - KadaneMin = -2 (subarray [-2])
     * - totalsum = 11
     * - wrapsum = 11 - (-2) = 13 (subarray [3, 4, 5, 1] wraps around)
     * Output: 13
     * 
     * Input: [5, -3, 5]
     * - KadaneMax = 7 (subarray [5, -3, 5])
     * - KadaneMin = -3 (subarray [-3])
     * - totalsum = 7
     * - wrapsum = 7 - (-3) = 10 (subarray [5] + [5] wrapped)
     * Output: 10
     * 
     * Input: [-3, -2, -1]
     * - All negative, KadaneMax = -1
     * - KadaneMin = -6 (all elements)
     * - totalsum = -6
     * - minSum == totalsum, so return -1
     * Output: -1
     */
    public static int maxSubarraySumCircular(int nums[]){
        int maxSum = KadaneMax(nums);
        int minSum = KadaneMin(nums);
        int totalsum = 0;

        for(int sum : nums){
            totalsum += sum;
        }
        int wrapsum = totalsum - minSum;
        if(minSum == totalsum) return maxSum;
        return Math.max (maxSum , totalsum - minSum);
    }

    /**
     * ========== MAXIMUM ABSOLUTE SUM OF ANY SUBARRAY ==========
     * 
     * EXPLANATION:
     * Find the maximum absolute value of sum of any contiguous subarray.
     * Need to track both maximum positive sum and minimum (most negative) sum.
     * Return the one with larger absolute value.
     * 
     * Uses modified Kadane's algorithm to simultaneously track:
     * - maxCurrent: Maximum subarray sum ending here
     * - minCurrent: Minimum subarray sum ending here
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     * 
     * ALGORITHM STEPS:
     * 1. For each element:
     *    - maxCurrent = max(current, maxCurrent + current) [extend or restart max]
     *    - minCurrent = min(current, minCurrent + current) [extend or restart min]
     * 2. Track overall maxSum and minSum
     * 3. Return max(|maxSum|, |minSum|)
     * 
     * EXAMPLES:
     * Input: [1, -3, 2, 4, -5]
     * - i=0: maxCurrent=1, minCurrent=1, maxSum=1, minSum=0
     * - i=1: maxCurrent=max(-3,1-3)=-2, minCurrent=min(-3,1-3)=-3, maxSum=1, minSum=-3
     * - i=2: maxCurrent=max(2,-2+2)=2, minCurrent=min(2,-3+2)=-1, maxSum=2, minSum=-3
     * - i=3: maxCurrent=max(4,2+4)=6, minCurrent=min(4,-1+4)=3, maxSum=6, minSum=-3
     * - i=4: maxCurrent=max(-5,6-5)=1, minCurrent=min(-5,3-5)=-5, maxSum=6, minSum=-5
     * Output: max(6, 5) = 6 (subarray [2, 4])
     * 
     * Input: [2, -5, -2, -4, 1]
     * - maxSum eventually = 2
     * - minSum eventually = -11 (entire array except last element)
     * Output: max(2, 11) = 11
     * 
     * Input: [1, 2, 3]
     * - All positive, maxSum = 6, minSum = 0 (no negative sums)
     * Output: 6
     */
    public int maxAbsoluteSum(int[] nums) {
        int maxSum = 0;
        int minSum = 0;

        int maxCurrent = 0;
        int minCurrent = 0;

        for(int i=0; i<nums.length; i++){
            maxCurrent = Math.max(nums[i] , maxCurrent + nums[i]);
            minCurrent = Math.min(nums[i] , minCurrent + nums[i]);

            maxSum = Math.max(maxSum , maxCurrent);
            minSum = Math.min(minCurrent , minSum);
        }
        return Math.max(Math.abs(maxSum) , Math.abs(minSum));
    }

    public long maxAlternatingSum(int[] nums) {
        long add = 0;
        long sub = 0;

        for(int num : nums){
            long newAdd = Math.max(add , sub + num);
            long newSub = Math.max(sub , add - num);

            add = newAdd;
            sub = newSub;
        }
        return add;
    }

    public int maxSumSubarray(int arr[]) {
        if(arr == null || arr.length == 0) return 0;
        if(arr.length == 1)return arr[0];
        int maxEndingHere = arr[0];
        int maxRemovingOne = 0;
        int result = arr[0];

        for(int i=1; i<arr.length; i++){
            int newMaxRemovingHere = Math.max(maxRemovingOne + arr[i] , maxEndingHere);
            int newMaxEndingHere = Math.max(arr[i] , maxEndingHere + arr[i]);

            maxEndingHere = newMaxEndingHere;
            maxRemovingOne = newMaxRemovingHere;
            result = Math.max(result , Math.max(maxRemovingOne , maxEndingHere));
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
