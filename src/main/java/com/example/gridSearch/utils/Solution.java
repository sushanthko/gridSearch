package com.example.gridSearch.utils;

import java.util.*;

/**
 * A class to store a com.example.gridSearch.utils.Trie node
 * this data structure stores all the words
 */
class Trie {
    // when true indicates the end of a word
    boolean isLeaf;

    Map<Character, Trie> character = new HashMap<>();

    Trie() {
        isLeaf = false;
    }
}

public class Solution {
    // Below arrays detail all four permitted movements from a cell
    // (top, right, bottom, and left)
    public static int[] row = {-1, 0, 1, 0};
    public static int[] col = {0, 1, 0, -1};

    // a `M × N` matrix
    // for grid size
    private static int M, N;

    /**
     * Iterative function to insert a string into a com.example.gridSearch.utils.Trie
     *
     * @param root com.example.gridSearch.utils.Trie node
     * @param str  a word
     */
    private static void insert(Trie root, String str) {
        // start from the root node
        Trie curr = root;

        for (char ch : str.toCharArray()) {
            // create a new node if the path/string doesn't exist
            Map<Character, Trie> current = curr.character;
            if (current.get(ch) == null) {
                current.put(ch, new Trie());
            }

            // go to the next node
            curr = current.get(ch);
        }

        curr.isLeaf = true;
    }

    /**
     * Checks for grid bounds and valid movements
     *
     * @param x         grid row coordinate
     * @param y         grid column coordinate
     * @param processed matrix to store visited grid coordinates
     * @param grid      grid/matrix
     * @param ch        character from the current grid location
     * @return false if `(x, y)` is not valid matrix coordinates
     * or cell `(x, y)` is already processed or doesn't lead to the solution, else true
     */
    public static boolean isSafe(int x, int y, boolean[][] processed,
                                 char[][] grid, char ch) {
        return (x >= 0 && x < M) && (y >= 0 && y < N) &&
                !processed[x][y] && (grid[x][y] == ch);
    }

    /**
     * A recursive method to search valid words present in a grid using com.example.gridSearch.utils.Trie
     *
     * @param root      com.example.gridSearch.utils.Trie root node
     * @param grid      grid/matrix
     * @param i         current grid row
     * @param j         current grid column
     * @param processed matrix to store visited grid coordinates
     * @param path      path traced on the grid by the algorithm
     * @param result    set that store all valid words
     */
    public static void searchGrid(Trie root, char[][] grid, int i, int j,
                                  boolean[][] processed, String path,
                                  Set<String> result) {
        // if a leaf node is encountered
        if (root.isLeaf) {
            // update result with the current word
            result.add(path);
        }

        // mark the current cell as processed
        processed[i][j] = true;

        // traverse all children of the current com.example.gridSearch.utils.Trie node
        for (Map.Entry<Character, Trie> entry : root.character.entrySet()) {
            // check for all four possible movements from the current cell
            for (int k = 0; k < 4; k++) {
                // skip if cell is invalid or entry is already processed
                // or doesn't lead to any path in the com.example.gridSearch.utils.Trie
                if (isSafe(i + row[k], j + col[k], processed, grid, entry.getKey())) {
                    searchGrid(entry.getValue(), grid, i + row[k], j + col[k],
                            processed, path + entry.getKey(), result);
                }
            }
        }

        // backtrack: mark the current cell as unprocessed
        processed[i][j] = false;

    }

    /**
     * search for a valid set of words in a character grid
     *
     * @param grid  grid/matrix
     * @param input list of words
     * @return list of words found
     */
    public static List<String> searchGrid(char[][] grid, List<String> input) {
        // insert all words into a trie
        Trie root = new Trie();
        for (String word : input) {
            insert(root, word);
        }

        // construct a set for storing the result
        Set<String> result = new HashSet<>();

        // construct a boolean matrix to store whether a cell is processed or not
        boolean[][] processed = new boolean[M][N];

        // consider each character in the matrix
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                // current character
                char ch = grid[i][j];

                Map<Character, Trie> rootNodeMap = root.character;
                // proceed only if the current character is a child of the com.example.gridSearch.utils.Trie root
                if (rootNodeMap.containsKey(ch)) {
                    searchGrid(rootNodeMap.get(ch), grid, i, j,
                            processed, Character.toString(ch), result);
                }
            }
        }

        return new ArrayList<>(result);
    }

    /**
     * runs the word search algorithm
     *
     * @param grid  the character grid/matrix
     * @param words list of valid words to search
     * @return the words found in the grid listed by length then by alphabetical order
     */
    public static List<String> runSolution(char[][] grid, List<String> words) {
        M = grid.length;
        N = grid[0].length;

        List<String> output = searchGrid(grid, words);
        output.sort((o1, o2) -> {
            if (o1.length() > o2.length())
                return -1;
            else if (o1.length() < o2.length())
                return 1;
            return o1.compareTo(o2);
        });
        return output;
    }
}
