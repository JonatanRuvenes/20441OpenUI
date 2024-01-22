package maman13;
/**
 * Answers for Maman 13
 *
 * @author Yehonatan Eliezer Reuvenes
 * @version 22/1/2024
 */
public class Ex13{

    /**
     * This method calculates the maximum product of three numbers from an array.
     *
     * Time Complexity: O(n^3)
     * there are three loops one inside the other
     * the first one is running (n-2) times
     * the second one in running (n-1-i) times for each operation of the first loop
     * the second one in running (n-j) times for each operation of the second loop
     * the run time is (n-2)(n-1-i)(n-j) = O(n^3)
     *
     * Space Complexity: O(1)
     * the program creating only one parameter in the machine memory (max)
     * the amount of space the program needs does not depend on the params - the space complexity is O(1)
     *
     *
     * @param arr an array of integers from which the maximum product is calculated
     * @return the maximum product of three numbers from the array
     */
    public static int maxMult3(int[] arr) {
        // Initialize max to the smallest possible integer value
        int max = Integer.MIN_VALUE;

        // Iterate through the array to find the maximum product of three numbers
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    // Update max with the maximum product found so far
                    max = Math.max(max, arr[i] * arr[j] * arr[k]);
                }
            }
        }

        // Return the maximum product of three numbers
        return max;
    }


    /**
     * This method calculate and returns the median between two sorted arrays in the same length.
     *
     * Time Complexity: O(n)
     * the method have one loop that pass over all the values in the two arrays
     * the loop runs (n+n) = (2n) times
     * the run time is (2n) = O(n)
     *
     * Space Complexity: O(n)
     * The program creates three parameters:
     * - arr: An array with a length equal to the sum of the lengths of the two input arrays (space: 2n).
     * - i1, i2: Two integers (space: 2).
     * The space complexity is (2n + 2) = O(n).
     *
     * @param arr1 sorted array of integers
     * @param arr2 sorted array of integers at the same length of arr1
     * @return the median of the two arras (the average of the two medians)
     */
    public static int findMedian(int[] arr1, int[] arr2) {
        // Create a new array to merge the two input arrays
        int[] arr = new int[arr1.length + arr2.length];

        // Initialize indices for arr1 and arr2
        int i1 = 0;
        int i2 = 0;

        // Iterate over the merged array
        for (int i = 0; i < arr.length; i++) {
            // Check if there are elements remaining in both arr1 and arr2
            if (i1 < arr1.length) {
                if (i2 < arr2.length) {
                    // Compare elements at the current indices of arr1 and arr2
                    if (arr1[i1] < arr2[i2]) {
                        arr[i] = arr1[i1];
                        i1++;
                    } else {
                        arr[i] = arr2[i2];
                        i2++;
                    }
                } else {
                    // If arr2 is exhausted, copy the remaining elements from arr1
                    arr[i] = arr1[i1];
                    i1++;
                }
            } else {
                // If arr1 is exhausted, copy the remaining elements from arr2
                arr[i] = arr2[i2];
                i2++;
            }
        }

        // Calculate and return the median of the merged array
        return (arr[arr.length / 2 - 1] + arr[arr.length / 2]) / 2;
    }


    /**
     * This function joins two Strings together in a way that the String includes both inputs in the original order,
     * while minimizing the length.
     *
     * @param st1
     * @param st2
     * @return the minimal (by length) String that include st1 and st2 in it
     */
    public static String minimalSt(String st1, String st2){
        // If either st1 or st2 is an empty string, concatenate and return the other string
        if (st1.length() == 0 || st2.length() == 0)
            return st1 + st2;

        /*
        there are three options for the string
         - both st1, st2 first letter are the same --> this is the first letter (find the rest of the string)
         - the first letters of st1, st2 are different -->
            - the first letter is the one from st1
            - the first letter is the one from st1
            find the shortest and the rest of the String
        */
        if (st1.charAt(0) == st2.charAt(0)){
            return st1.charAt(0) + minimalSt(st1.substring(1), st2.substring(1));
        }
        else{
            String s1 = st1.charAt(0) + minimalSt(st1.substring(1), st2);
            String s2 = st2.charAt(0) + minimalSt(st1, st2.substring(1));

            return s1.length() < s2.length() ? s1 : s2;
        }
    }


    /**
     * Finds the length of the longest "snake" path in the given matrix.
     *
     * @param mat The input matrix.
     * @return The length of the longest snake path.
     */
    public static int maxSnake(int[][] mat) {
        // Start the recursive exploration from the top-left corner
        return maxSnake(mat, 0, 0);
    }

    /**
     * Helper function to find the length of the longest snake path.
     *
     * @param mat The input matrix.
     * @param i   Current row index.
     * @param j   Current column index.
     * @return The length of the longest snake path starting from the given position.
     */
    private static int maxSnake(int[][] mat, int i, int j) {
        final int BEEN_HERE = -1;

        // Base case: If the current position is the bottom-right corner, return 1
        if (i == mat.length - 1 && j == mat[0].length - 1)
            return 1;

        // Save the current matrix value and mark the current position as visited
        int save = mat[i][j];
        mat[i][j] = BEEN_HERE;

        int max = Integer.MIN_VALUE;

        // Move left
        if (i > 0 && mat[i - 1][j] != BEEN_HERE && Math.abs(save - mat[i - 1][j]) <= 1) {
            max = Math.max(max, maxSnake(mat, i - 1, j));
        }
        // Move right
        if (i < mat.length - 1 && mat[i + 1][j] != BEEN_HERE && Math.abs(save - mat[i + 1][j]) <= 1) {
            max = Math.max(max, maxSnake(mat, i + 1, j));
        }
        // Move up
        if (j > 0 && mat[i][j - 1] != BEEN_HERE && Math.abs(save - mat[i][j - 1]) <= 1) {
            max = Math.max(max, maxSnake(mat, i, j - 1));
        }
        // Move down
        if (j < mat[0].length - 1 && mat[i][j + 1] != BEEN_HERE && Math.abs(save - mat[i][j + 1]) <= 1) {
            max = Math.max(max, maxSnake(mat, i, j + 1));
        }

        // Restore the original matrix value at the current position
        mat[i][j] = save;

        // Return the maximum length from the current position plus 1 (for this move)
        return max + 1;
    }


}
