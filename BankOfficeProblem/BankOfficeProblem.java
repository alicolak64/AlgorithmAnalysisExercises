import java.util.Random;

public class BankOfficeProblem {

    public static void main(String[] args) {

        final int POWER = 16;

        final int N = (int) Math.pow(2, POWER);

        final int[] ARRAY = new int[N];

        final int K = 1;

        fillArray(ARRAY);


        // K = { 1,  N/16  ,  2N/16 ,  3N/16 ,  4N/16 ,  5N/16 ,  6N/16 ,  7N/16 ,  8N/16 ,  9N/16 ,  10N/16 ,  11N/16 ,  12N/16 ,  13N/16 ,  14N/16 ,  15N/16 ,  N-1  }
        // K = { 1 , 4096  ,  8192  ,  12288 ,  16384 ,  20480 ,  24576 ,  28672 ,  32768 ,  36864 ,  40960  ,  45056  ,  49152  ,  53248  ,  57344  ,  61440  , 65535 }


        insertionSort(ARRAY);
        int[] SHIFTED_ARRAY = shiftArray(ARRAY, K);

        long startTime = getTime();
        insertionSort(SHIFTED_ARRAY);
        long endTime = getTime();

        System.out.print("K  =  " + K + " , ");
        System.out.println("Time : " + getTimeDifference(startTime, endTime) + "ms");

        System.out.println("Is Sorted : " + isSorted(SHIFTED_ARRAY));

    }

    // Fills the array with random numbers that can be equal to the maximum array length
    private static void fillArray (int[] array) {

        Random random = new Random();

        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt(array.length);

    }

    // print array method
    private static void printArray (int[] array) {

        for (int element : array)
            System.out.print(element + " ");

        System.out.println();

    }

    private static boolean isSorted(int[] array) {

        for (int i = 0; i < array.length - 1; i++)
            if (array[i] > array[i + 1])
                return false;

        return true;

    }

    // return the time in nanoseconds
    public static long getTime() {
        return System.nanoTime();
    }

    // It takes the difference of the nanosecond type time values and converts the result to ms.
    public static double getTimeDifference(long startTime, long finishTime) {
        return (finishTime - startTime)/1000000.0;
    }


    // K is the shift value to the right
    private static int[] shiftArray(int[] array, int shiftedValue) {

        int[] shiftedArray = new int[array.length];

        for (int i = 0; i < array.length; i++)
            shiftedArray[(i + shiftedValue) % array.length] = array[i];

        return shiftedArray;

    }

    // Insertion Sort Method
    public static void insertionSort (int[] array) {

        int key;
        int i;

        for ( int j = 1; j < array.length ; j++ ) {

            key = array[j];
            i = j - 1;

            while ( i >= 0 && array [i] > key ) {
                array [ i + 1 ] = array[i] ;
                i--;
            }

            array [ i + 1 ] = key;

        }

    }

}