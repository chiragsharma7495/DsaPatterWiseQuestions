package ArraysAndStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 167 - Two Sum II - Input Array Is Sorted
 *
 * APPROACH: Two Pointers
 *
 * WHY THIS APPROACH?
 * 1. The array is SORTED in non-decreasing order - we can exploit this.
 * 2. If numbers[left] + numbers[right] > target → sum is too large.
 *    Since array is sorted, the only way to get a smaller sum is to move RIGHT pointer left
 *    (smaller element on the right).
 * 3. If numbers[left] + numbers[right] < target → sum is too small.
 *    Move LEFT pointer right (larger element on the left).
 * 4. This gives O(n) time and O(1) space - better than hash map O(n) space,
 *    and no extra data structure needed.
 */
public class TwoPointers {

    public static int[] twoSum(int[] numbers, int target) {
        int left = 0; int right = numbers.length-1;

        while(left <= right){
            int sum = numbers[left] + numbers[right];

            if(sum == target) return new int[]{left+1 , right+1};
            if(sum > target) right--;
            else{
                left++;
            }
        }
        return new int[]{-1,-1};
    }

    public boolean isValindrome(String s) {

        if (s == null) return false;
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char charLeft = s.charAt(left);
            char charRight = s.charAt(right);

            if (!Character.isLetterOrDigit(charLeft)) left++;
            else if (!Character.isLetterOrDigit(charRight)) right--;
            else if (Character.toLowerCase(charLeft) != Character.toLowerCase(charRight)) {
                return false;
            }
            else {
                left++;
                right--;
            }
        }
        return true;
    }

    /**
     * LeetCode 680 - Valid Palindrome II
     *
     * APPROACH: Two Pointers + One Deletion Check
     *
     * WHY THIS APPROACH?
     * 1. Two pointers scan from both ends - O(n) time, O(1) space.
     * 2. When chars match → move both inward.
     * 3. When chars DON'T match → we get ONE chance to delete. Try BOTH:
     *    - Skip left char (check if s[left+1..right] is palindrome)
     *    - Skip right char (check if s[left..right-1] is palindrome)
     * 4. If either sub-string is a palindrome, original string is valid.
     */
    public boolean validPalindrome(String s) {
        int left = 0; int right = s.length()-1;
        while(left <= right){
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s , left+1 , right) || isPalindrome(s , left , right-1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public int removeDuplicates(int[] nums) {
        int i=0;
        for(int j=1; j<nums.length; j++){
            if(nums[i]!= nums[j]){
                nums[i+1] =nums[j];
                i++;
            }
        }
        return i+1;
    }

    public void moveZeroes(int []nums){
        int n = nums.length;
        int zeroes = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[zeroes];
                nums[zeroes] = temp;
                zeroes++;
            }
        }
    }

    /**
     * LeetCode 977 - Squares of a Sorted Array
     *
     * APPROACH: Two Pointers (from both ends)
     *
     * WHY?
     * - Array is sorted but can have negatives. After squaring, smallest can be in the middle
     *   (e.g. [-4,-1,0,3,10] -> 16,1,0,9,100). So we can't just square and append.
     * - Largest square is always at one of the ends (most negative or most positive).
     * - Put a pointer at left and right; compare absolute values; put the larger square at the
     *   end of the result array; then move that pointer. Fill result from right to left.
     * - Time O(n), space O(n) for result.
     */
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int result[] = new int[n];
        int k = n-1;
        int left = 0; int right = n-1;

        while(left <= right){
            int sqL = nums[left] * nums[left];
            int sqR = nums[right] * nums[right];
            if(sqL >= sqR){
                result[k--] = sqL;
                left++;
            }else{
                result[k--] = sqR;
                right--;
            }
        }
        return result;
    }

    /**
     * LeetCode 11 - Container With Most Water
     *
     * APPROACH: Two Pointers (left and right)
     *
     * WHY?
     * - Area = min(height[left], height[right]) * (right - left).
     * - Start with max width (left=0, right=n-1).
     * - The SHORTER line limits the height. So we move the SHORTER pointer inward:
     *   - Moving the shorter one might find a taller line → possibly more area.
     *   - Moving the taller one only shrinks width; height stays limited by the shorter one.
     * - O(n) time, O(1) space.
     */

     public static int maxArea(int[] height){
        int left = 0; int right = height.length-1;
        int maxWater = 0;

        while(left < right){
            int h = Math.min(height[left] , height[right]);
            int width = right-left;
            int area = h*width;
            maxWater = Math.max(maxWater,area);

            if(height[left] <= height[right])left++;
            else right--;
        }
        return maxWater;
     }

     public static int trap(int[] height){
         int left = 0; int right = height.length-1;
         int leftMax = 0; int rightMax = 0;
         int water = 0;

         while(left < right){
             if(height[left] < height[right]){
                 if(height[left] >= leftMax){
                     leftMax = height[left];
                 }else{
                     water += leftMax - height[left];
                 }
                 left++;
             }

             if(height[right] < height[left]){
                 if(height[right] >= rightMax){
                     rightMax = height[right];
                 }else{
                     water += rightMax - height[right];
                 }
                 right--;
             }
         }
         return water;
     }

    public static List<List<Integer>> threeSum(int[] nums) {
         List<List<Integer>> result = new ArrayList<>();
         if(nums == null || nums.length < 3) return result;

         Arrays.sort(nums);

         for(int i=0; i<nums.length-2; i++){
             if(i > 0 || nums[i] == nums[i-1]) continue;

             int target = -nums[i];
             int left = i+1;
             int right = nums.length-1;

             while(left < right){
                 int sum = nums[left] + nums[right];

                 if(sum == target){
                     result.add(Arrays.asList(nums[i] , nums[left] , nums[right]));

                     while(left < right && nums[left] == nums[left+1]) left++;
                     while(left < right && nums[right] == nums[right-1]) right--;

                     left++;
                     right--;
                 }else if(sum < target){
                     left++;
                 }else{
                     right--;
                 }

             }
         }
         return result;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if(n<4)return result;

        for(int i=0; i<n-3; i++){
            if(i>0 && nums[i] == nums[i-1])continue;

            for(int j=i+1; j<n-2; j++){
                if(j>i+1 && nums[j] == nums[j-1])continue;

                int left = j+1;
                int right = n-1;

                long twoTarget = (long) target - nums[i] - nums[j];

                while(left < right){
                    long sum = (long) nums[left] + nums[right];

                    if(sum == target){
                        result.add(Arrays.asList(nums[i] , nums[j] , nums[left] , nums[right]));

                        while(left < right && nums[left] == nums[left+1]) left++;
                        while(left < right && nums[right] == nums[right-1]) right--;

                        left++; right--;
                    }else if(sum < target){
                        left++;
                    }else{
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public int numRescueBoats(int[] people, int limit) {
         Arrays.sort(people);
        int i = 0;
        int j = people.length-1;
        int boats = 0;

        while(i <= j){
            if(people[i] + people[j] <= limit){
                i++;
            }
            j--;
            boats++;
        }
        return boats;
    }

    public boolean backspaceCompare(String s, String t) {

         int i = s.length()-1;
         int j = t.length()-1;
         int skipS = 0;
         int skipT = 0;

         while(i >= 0 || j>= 0){
             while(i >= 0) {
                 if (s.charAt(i) == '#') {
                     skipS++;
                     i--;
                 } else if (skipS > 0) {
                     skipS--;
                     i--;
                 } else {
                     break;
                 }
             }

                 while(j >= 0){
                     if(s.charAt(j) == '#'){
                         skipT++;
                         j--;
                     } else if (skipT > 0) {
                         skipT--;
                         j--;
                     }else{
                         break;
                     }
                 }

                 char chS = (i>=0) ? s.charAt(i) :'\0';
                 char chT = (j>=0) ? s.charAt(j) :'\0';

                 if(chS != chT)return false;
                 i--;j--;
             }
        return true;
    }

    public static void main(String[] args) {
        // Example: numbers = [2, 7, 11, 15], target = 9
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(numbers, target);
        System.out.println("Input: numbers = [2, 7, 11, 15], target = 9");
        System.out.println("Output: [" + result[0] + ", " + result[1] + "]");

        // LeetCode 680 - Valid Palindrome II
        TwoPointers tp = new TwoPointers();
        System.out.println("\nValid Palindrome II:");
        System.out.println("aba -> " + tp.validPalindrome("aba"));       // true
        System.out.println("abca -> " + tp.validPalindrome("abca"));    // true (remove 'c')
        System.out.println("abc -> " + tp.validPalindrome("abc"));       // false

        // LeetCode 977 - Squares of a Sorted Array
        int[] nums977 = {-4, -1, 0, 3, 10};
        int[] squared = sortedSquares(nums977);
        System.out.println("\nSquares of a Sorted Array: [-4,-1,0,3,10] -> " + java.util.Arrays.toString(squared));

        // LeetCode 11 - Container With Most Water
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("\nContainer With Most Water: [1,8,6,2,5,4,8,3,7] -> " + maxArea(height));
    }
}
