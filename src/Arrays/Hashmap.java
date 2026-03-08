package Arrays;

import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hashmap {

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

    //arr = [2, 7, 11, 15], target = 9
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

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> seen = new HashSet <>();
        for(int num : nums){
            if(!seen.add(num)) return true;
        }
        return false;
    }

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

    public int[] topKFrequent(int[] nums, int k) {      // very important for Syntaxwise
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
    }

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
