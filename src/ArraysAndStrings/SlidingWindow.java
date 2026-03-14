package ArraysAndStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlidingWindow {

    /* Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers. */

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

//    Example 1:

//    Input: nums = [1,12,-5,-6,50,3], k = 4
//    Output: 12.75000
//    Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
//    Example 2:
//
//    Input: nums = [5], k = 1
//    Output: 5.00000

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

//    Example 1:
//
//    Input: s1 = "ab", s2 = "eidbaooo"
//    Output: true
//    Explanation: s2 contains one permutation of s1 ("ba").
//    Example 2:
//
//    Input: s1 = "ab", s2 = "eidboaoo"
//    Output: false

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

    public static boolean matches(int[] a , int[] b){
        for(int i=0; i<26; i++){
            if(a[i] != b[i]) return false;
        }
        return true;
    }

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

    private static boolean match(int a[] , int b[]){
        for(int i=0; i<26; i++){
            if(a[i] != b[i])return false;
        }
        return true;
    }

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

    public static void main(String[] args) {
    }
}
