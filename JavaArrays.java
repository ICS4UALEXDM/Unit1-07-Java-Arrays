import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
* This program calculates amount of mean median and mode
*
* @author  Alex De Meo
* @version 1.0
* @since   2023/02/08
*/

public final class JavaArrays {
    /**
    * This is a private constructor used to satisfy the
    * style checker.
    *
    * @exception IllegalStateException Utility Class
     */
    private JavaArrays() {
        throw new IllegalStateException("Utility Class");
    }
    /**
    * This is the main method.
    *
    * @param args Unused
    * @throws Exception if something goes wrong
    */

    public static void main(String[] args) throws Exception {
        // Initializing variables
        String line;
        ArrayList<String> intList = new ArrayList<String>();
        String err = "Error";

        try {
            // Creating the writer
            final FileWriter myWriter = new FileWriter("output.txt");

            try {
                // Creating the input file object
                final File inFile = new File("input.txt");

                // Creating the scanner.
                final Scanner scanner = new Scanner(inFile);

                // Iterating through the lines, will continue this until
                // no new line
                // is detected
                while (scanner.hasNextLine()) {
                    // getting the next line
                    line = scanner.nextLine();
                    // Adds the line to the list
                    intList.add(line);
                }
                // More variables to be used in the next part
                int counter = 0;
                int[] intArr = new int[intList.size()];

                for (String num : intList) {
                    // parsing to int
                    int numInt = Integer.parseInt(num);
                    // Putting the int into its spot in the array
                    intArr[counter] = numInt;
                    // Add one to the counter
                    counter++;
                }
                // Sorts the array that will be passed through to the functions
                Arrays.sort(intArr);
                // getting the mean, median and mode
                float theMean = calcMean(intArr);
                float median = calcMedian(intArr);
                ArrayList<Integer> mode = calcMode(intArr);
                // Writing to the file with the return values
                myWriter.write("The mean is " + theMean);
                myWriter.write("\nThe median is " + median);
                myWriter.write("\nThe mode is " + mode);
            } catch (IOException error) {
                System.out.println(err + error.getMessage());
            }
            // closes the writer
            myWriter.close();
        } catch (IOException error) {
            System.out.println(err + error.getMessage());
        }
    }
    /**
    * This is the method calculates the mean
    *
    * @param numbers needed to calculate the mean
    * @return the mean
    */
    public static float calcMean(int[] numbers) {
        // setting the sum to 0
        int sum = 0;
        // Iterates through the array
        for (int num : numbers) {
            // adds to sum
            sum += num;
        }
        // calculating the average/mean
        float sumFlt = sum;
        float mean = sumFlt / (float)numbers.length;
        // returning to the main method
        return mean;
    }
    /**
    * This is the method calculates the median
    *
    * @param numbers needed to calculate the median
    * @return the median
    */
    public static float calcMedian(int[] numbers) {
        float median;
        // Checks to see if the length of the array is even or odd
        // depending on which it is, there are two different ways of handling it
        if (numbers.length % 2 == 0) {
            // calculating the median if it is even
            median = (
                numbers[numbers.length / 2] + numbers[(numbers.length / 2) - 1]
                ) / 2;
        } else {
            // calculating the median if it is odd
            median = numbers[(int)Math.floor((float)numbers.length / 2.0)];
        }
        // returning to main method
        return median;
    }
    /**
    * This is the method calculates the mode
    *
    * @param numbers needed to calculate the mode
    * @return the mode
    */
    public static ArrayList<Integer> calcMode(int[] numbers) {
        // Declaring variables
        int maxRepeats = 0;
        int timesRepeated = 0;
        Integer number;
        ArrayList<Integer> mode = new ArrayList<>();
        // Loops through the array
        for (int i = 0; i <= numbers.length - 1; i++) {
            // sets variable "number" to the current place in the array
            number = Integer.valueOf(numbers[i]);
            // Checks to see if the current number is repeated in the next
            // spot of the array
            if (i < numbers.length - 1) {
                if (numbers[i] == numbers[i + 1]) {
                    timesRepeated++;
                } else {
                    // Checks to see if theres a new record
                    if (timesRepeated > maxRepeats) {
                        
                        // clears previous records
                        mode.clear();
                        // adds new record
                        mode.add(number);
                        // updates the amount of repeats needed to be a record
                        maxRepeats = timesRepeated;
                    } else if (timesRepeated == maxRepeats) {
                        // add to records
                        mode.add(number);
                    }
                    // reset the times repeated
                    timesRepeated = 0;
                }
            } else  {
                System.out.println(i);
                // Checks to see if theres a new record
                if (timesRepeated > maxRepeats) {
                    
                    // clears previous records
                    mode.clear();
                    // adds new record
                    mode.add(number);
                    // updates the amount of repeats needed to be a record
                    maxRepeats = timesRepeated;
                } else if (timesRepeated == maxRepeats) {
                    // add to records
                    mode.add(number);
                }
                // reset the times repeated
                timesRepeated = 0;
            }
        }
        // returning to main method
        return mode;
    }
}