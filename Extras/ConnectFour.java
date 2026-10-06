
public class ConnectFour {
    public static boolean connect4(int[] arr)
    {
        if(arr == null || arr.length < 4)
        {
            return false;
        }
        int len = arr.length;
        int windowSize = 1;
        for(int r =1; r<len; r++)
        {
            int currentValue = arr[r];
            int PrevValue = arr[r-1];
            if(currentValue == PrevValue)
            {
                windowSize++;
                if(windowSize == 4)
                {
                    return true;
                }
            }
            else
            {
                windowSize = 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Given test cases
        System.out.println(connect4(new int[]{}));                                                                  // false
        System.out.println(connect4(null));                                                                         // false
        System.out.println(connect4(new int[]{10, 10, 10}));                                                        // false
        System.out.println(connect4(new int[]{10, 10, 10, 20, 20, 20, 10, 10, 10, 30, 30, 30, 40}));                // false
        System.out.println(connect4(new int[]{10, 10, 10, 20, 20, 20, 10, 10, 10, 30, 30, 30, 30, 30, 30}));        // true
        System.out.println(connect4(new int[]{10, 10, 10, 10, 20, 20, 20, 10, 10, 10, 30}));                        // true
        System.out.println(connect4(new int[]{10, 10, 10, 10, 20, 20, 20, 5000, 5000, 5000, 5000, 5000, 10, 10, 10, 30})); // true
        System.out.println(connect4(new int[]{10, 10, 10, 10, 20, 20, 20, 5000, 5000, 5000, 5000, 10, 10, 10, 30}));     // true

        // Extra test cases
        System.out.println(connect4(new int[]{7}));                                  // false - single element
        System.out.println(connect4(new int[]{7, 7, 7, 7}));                         // true  - exactly 4, whole array
        System.out.println(connect4(new int[]{7, 7, 7, 8}));                         // false - length 4, run of 3
        System.out.println(connect4(new int[]{1, 2, 3, 4}));                         // false - all different
        System.out.println(connect4(new int[]{1, 2, 5, 5, 5, 5}));                   // true  - run at the end
        System.out.println(connect4(new int[]{5, 5, 5, 1, 5}));                      // false - 4 fives, not consecutive
        System.out.println(connect4(new int[]{1, 2, 1, 2, 1, 2, 1, 2}));             // false - alternating
        System.out.println(connect4(new int[]{3, 3, 3, 4, 3, 3, 3}));                // false - two runs of 3 split
        System.out.println(connect4(new int[]{-1, -1, -1, -1}));                     // true  - negatives
        System.out.println(connect4(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));       // true  - long run of zeros
        System.out.println(connect4(new int[]{2, 2, 3, 3, 3, 2, 2}));                // false - mixed short runs
        System.out.println(connect4(new int[]{9, 8, 8, 8, 8, 9}));                   // true  - run in the middle
    }
}
