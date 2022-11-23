import java.util.Random;

public class HungarianFolkDance {

    // T(n)=2T(n/2)+Θ(n) => T(n)=Θ(nlogn)

    public static void main(String[] args) {

        int K = 10;

        int N = K * 1000;

        int[] array = new int[N];

        // K = {  10K  ,  20K  ,  30K  ,  40K  ,  50K  ,  60K  ,  70K  ,  80K  ,  90K  ,  100K  ,  200K  ,  300K  ,  400K  ,  500K  ,  1000K  }
        // K = { 10000 , 20000 , 30000 , 40000 , 50000 , 60000 , 70000 , 80000 , 90000 , 100000 , 200000 , 300000 , 400000 , 500000 , 1000000 }

        fillArray(array);

        long startTime = getTime();
        quickSort(array, 0, array.length - 1);
        long endTime = getTime();

        System.out.print("Array size = " + (N / 1000) + "K" + " , ");
        System.out.println("Time : " + getTimeDifference(startTime, endTime) + "ms");

    }

    // Fills the array with random numbers that can be equal to the maximum array length
    public static void fillArray(int[] array) {

        Random random = new Random();

        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt(array.length);

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


    // Quick Sort Method
    public static void quickSort(int[] array, int left, int right) {

        if (left < right) {

            int pivot = partition(array, left, right);

            quickSort(array, left, pivot - 1);
            quickSort(array, pivot + 1, right);

        }

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

        return j;

    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}