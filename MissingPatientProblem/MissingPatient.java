import java.util.Random;

public class MissingPatient {

    // T(n)=T(n/2)+Θ(n) => T(n)=Θ(n)

    public static void main(String[] args) {

        final int K = 100;

        final int N = K * 1000;

        final int M = N - 1;

        int[] patients = new int[M];

        // K = {  10K  ,  20K  ,  30K  ,  40K  ,  50K  ,  60K  ,  70K  ,  80K  ,  90K  ,  100K  ,  200K  ,  300K  ,  400K  ,  500K  ,  1000K  }
        // K = { 10000 , 20000 , 30000 , 40000 , 50000 , 60000 , 70000 , 80000 , 90000 , 100000 , 200000 , 300000 , 400000 , 500000 , 1000000 }


        int missingPatient = fillArray(patients);

        System.out.println("Missing Patient : " + missingPatient);

        long startTime = getTime();
        int foundMissingPatient = getMissingPatient(patients, 0, patients.length - 1);
        long endTime = getTime();
        System.out.println("Founded Patient : " + foundMissingPatient);

        System.out.print("Array size = " + (N / 1000) + "K" + " , ");
        System.out.println("Time : " + timeDifference(startTime, endTime) + "ms");


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
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
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
    public static double timeDifference(long startTime, long finishTime) {
        return (finishTime - startTime) / 1000000.0;
    }


    // return the missing patient's number
    public static int getMissingPatient(int[] arr, int low, int high) {

        if (low >= high) {
            if ((high - low == 0) || (arr[low] == arr.length + 1))
                return arr[low] - 1;
            else
                return arr[low] + 1;
        }

        int[] midElement = getMidElement(arr, low, high);

        if (midElement[0] < 0)
            return midElement[1];

        arr[midElement[0]] = arr[low];
        arr[low] = midElement[1];

        int pivot = partition(arr, low, high);

        // T(n)=T(n/2)+Θ(n).
        if (arr[midElement[1] - 1] == pivot)
            return getMissingPatient(arr, midElement[1], high);
        else
            return getMissingPatient(arr, 0, midElement[1] - 2);

    }

    // Partition method with pivot value first element and change operation with pivot
    public static int partition(int[] arr, int low, int high) {

        int pivot = arr[low];

        int i = low;
        int j = high;

        while (i < j) {

            while (i < j && arr[j] >= pivot) {
                j--;
            }

            if (i < j) {
                arr[i] = arr[j];
                arr[j] = pivot;
            }

            while (i < j && arr[i] <= pivot) {
                i++;
            }

            if (i < j) {
                arr[j] = arr[i];
                arr[i] = pivot;
            }

        }

        return pivot;


    }


    // It takes the average of the elements in the array, and if the element with this average is in the array, it gives the average directly, if it is not in the array, it multiplies the average by (-1) and returns that value.
    public static int[] getMidElement(int[] array, int low, int high) {

        int midIndex = -1;

        long average = ((high - low) / 2) + 1 + low;

        for (int i = low; i <= high; i++) {
            if (array[i] == average) {
                midIndex = i;
                break;
            }
        }

        if (midIndex == -1)
            return new int[]{-1, (int) average};   // Missing patient is average value So return -1 index
        else
            return new int[]{midIndex, (int) average};

    }

}