import java.util.Random;

public class MissingPatient {

    // T(n)=T(n/2)+Θ(n) => T(n)=Θ(n)

    public static void main(String[] args) {

        final int K = 100;

        final int N = K * 1000;

        final int M = N - 1;

        final int[] PATIENTS = new int[M];

        // K = {  10K  ,  20K  ,  30K  ,  40K  ,  50K  ,  60K  ,  70K  ,  80K  ,  90K  ,  100K  ,  200K  ,  300K  ,  400K  ,  500K  ,  1000K  }
        // K = { 10000 , 20000 , 30000 , 40000 , 50000 , 60000 , 70000 , 80000 , 90000 , 100000 , 200000 , 300000 , 400000 , 500000 , 1000000 }

        int missingPatient = fillArray(PATIENTS);

        System.out.println("Missing Patient : " + missingPatient);

        long startTime = getTime();
        int foundedMissingPatient = getMissingPatient(PATIENTS, 0, PATIENTS.length - 1);
        long endTime = getTime();
        System.out.println("Founded Patient : " + foundedMissingPatient);

        System.out.print("Array size = " + (N / 1000) + "K" + " , ");
        System.out.println("Time : " + getTimeDifference(startTime, endTime) + "ms");

    }

    // Fills an array of length N with numbers other than the randomly determined number in this range from 1 to N+1.
    // It then converts the sorted array to the unsorted array by swapping the randomly determined number
    public static int fillArray(int[] array) {

        Random random = new Random();
        int randomNumber = random.nextInt(array.length) + 1;

        for (int i = 1; i <= array.length + 1; i++) {

            if (i < randomNumber)
                array[i - 1] = i;
            else if (i > randomNumber)
                array[i - 2] = i;

        }

        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = random.nextInt(array.length);
            swap(array, randomIndexToSwap, i);
        }

        return randomNumber;

    }

    // print array method
    public static void printArray(int[] array) {

        for (int j : array)
            System.out.print(j + " ");

        System.out.println();

    }


    // return the time in nanoseconds
    public static long getTime() {
        return System.nanoTime();
    }

    // It takes the difference of the nanosecond type time values and converts the result to ms.
    public static double getTimeDifference(long startTime, long finishTime) {
        return (finishTime - startTime) / 1000000.0;
    }


    // return the missing patient's number
    public static int getMissingPatient(int[] array, int low, int high) {

        if (low >= high)
            return array[low] - 1;

        int avgIndex = getAverageIndex(array, low, high);

        if (avgIndex < 0)  // avg is not in the array so avg is missing patient
            return avgIndex * -1;

        swap(array, low, avgIndex);  // pivot that avg value assign to the first element
        int pivot = partition(array, low, high);

        // T(n)=T(n/2)+Θ(n).
        if (array[pivot - 1] == pivot) // missing patient on the right side of the pivot
            return getMissingPatient(array, pivot, high);
        else // missing patient on the left side of the pivot
            return getMissingPatient(array, 0, pivot - 2);

    }

    // Partition method with pivot value first element and change operation with pivot
    private static int partition(int[] arr, int low, int high) {

        int pivot = arr[low];

        int i = low;
        int j = high;

        while (i < j) {

            while (arr[j] >= pivot && i < j)
                j--;

            if (i < j)
                swap(arr, i, j);

            while (arr[i] <= pivot && i < j)
                i++;

            if (i < j)
                swap(arr, i, j);

        }

        return pivot;

    }


    // It takes the average of the elements in the array, and if the element with this average is in the array, it gives the average directly, if it is not in the array, it multiplies the average by (-1) and returns that value.
    private static int getAverageIndex(int[] array, int low, int high) {

        int average = ((high - low) / 2) + 1 + low;

        int avgIndex = getIndex(array, average, low, high);

        if (avgIndex == -1)
            return -1 * average;   // Missing patient number is average value So return -1 index
        else
            return avgIndex;

    }

    // get index of value in array between low and high indexes
    private static int getIndex(int[] array, int value, int low, int high) {

        for (int i = low; i <= high; i++)
            if (array[i] == value)
                return i;

        return -1;

    }

    // swap method
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}