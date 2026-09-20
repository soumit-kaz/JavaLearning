public class ExtraArrayExample {
        public static void main(String[] args) {
        int rowSize = 5;
        int colSize = 4;
        int[][] chars;
        chars = new int[rowSize][colSize];

        // Fill the array with values
        for(int rowIndex = 0; rowIndex < rowSize; rowIndex++) 
        {
            for(int colIndex = 0; colIndex < colSize; colIndex++)
            {
                if(rowIndex == colIndex)
                {
                    chars[rowIndex][colIndex] = 100*rowIndex;
                }
                else
                {
                    chars[rowIndex][colIndex] = 0;
                }
            }
        }
        
        // Print the array
        for(int rowIndex = 0; rowIndex < rowSize; rowIndex++) 
        {

            for(int colIndex = 0; colIndex < colSize; colIndex++)
            {
                System.out.print(chars[rowIndex][colIndex] + "\t");
            }
            System.out.println();
        }
        
    }
    
}
