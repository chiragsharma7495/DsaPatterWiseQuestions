## DSA Pattern-Wise Questions (Java)

This repository contains my personal collection of **Data Structures and Algorithms (DSA)** questions, solved **pattern-wise** in Java.  
The goal is to build strong problem-solving intuition by grouping problems by technique (Two Pointers, HashMap, etc.) instead of random practice.

### What I Am Trying To Do

- **Practice DSA in a structured way** instead of solving random questions.
- **Group problems by patterns**, so that:
  - I recognize which pattern to apply when I see a new problem.
  - I can quickly revise similar problems together.
- **Write clean, commented Java solutions** that explain:
  - The **approach and intuition**.
  - **Why** a certain pattern is used.
  - Time and space complexity.

### Current Patterns Covered

- **ArraysAndStrings + Two Pointers**
  - Classic problems like:
    - `Two Sum II (input array sorted)`
    - Valid Palindrome / Valid Palindrome II
    - Remove Duplicates from Sorted Array
    - Move Zeroes
    - Squares of a Sorted Array
    - Container With Most Water
    - 3Sum
  - Focus on:
    - Using `left` / `right` pointers efficiently.
    - Understanding when to move which pointer and why.

- **ArraysAndStrings + HashMap**
  - Problems that use frequency counting or complement lookup.
  - Builds intuition for when to trade space for time with hash maps.

> More patterns (Sliding Window, Binary Search, Prefix Sum, DP, etc.) can be added here as the project grows.

### Project Structure

- `src/Main.java`  
  - Basic entry point (can be used for quick tests).

- `src/ArraysAndStrings/TwoPointers.java`  
  - Contains multiple **two-pointer based** solutions (LeetCode-style problems).

- `src/ArraysAndStrings/Hashmap.java`  
  - Contains **hash map based** array solutions.

- `src/ArraysAndStrings/ThreeSum.java`  
  - Dedicated implementation and demo for the **3Sum** problem.

### How To Run

1. Open the project in **IntelliJ IDEA** (or any Java IDE).
2. Make sure the SDK is configured (Java 8+ is fine).
3. Run:
   - `Main` for generic tests, or
   - Individual classes with `main` methods (like `ThreeSum`) to see examples and outputs.

### Future Plans

- Add more patterns: **Sliding Window, Binary Search, Recursion/Backtracking, Dynamic Programming**, etc.
- For each pattern:
  - Add multiple problems.
  - Keep explanations focused on **recognizing the pattern** and **why it works**.

This repository is mainly for **learning, revision, and pattern recognition** rather than just storing solutions.

