import java.util.Random;

public class PhoneOperatorProblem {

    public static void main(String[] args) {

        final int N = 10;

        Phone[] PHONES = new Phone[N];

        /*
        phones[0] = new Phone(0, 2);
        phones[1] = new Phone(1, 5);
        phones[2] = new Phone(2, 3);
        phones[3] = new Phone(3, 0);
        phones[4] = new Phone(4, 10);
        phones[5] = new Phone(5, 4);
        phones[6] = new Phone(6, 6);
        phones[7] = new Phone(7, 8);
        phones[8] = new Phone(8, 1);
        phones[9] = new Phone(9, 11);
         */

        fillArray(PHONES);

        buildMaxHeap(PHONES);

        printArray(PHONES);
        System.out.println("Is  Heap: " + isMaxHeap(PHONES));


        addNewCall(PHONES, 5);
        addNewCall(PHONES, 5);
        addNewCall(PHONES, 5);

        printArray(PHONES);
        System.out.println("Is  Heap: " + isMaxHeap(PHONES));

        directCall(PHONES);
        directCall(PHONES);

        printArray(PHONES);
        System.out.println("Is  Heap: " + isMaxHeap(PHONES));

        directCall(PHONES);

        printArray(PHONES);
        System.out.println("Is  Heap: " + isMaxHeap(PHONES));

        deleteWaitCall(PHONES, 5);
        deleteWaitCall(PHONES, 4);
        deleteWaitCall(PHONES, 3);
        deleteWaitCall(PHONES, 5);
        deleteWaitCall(PHONES, 5);

        printArray(PHONES);
        System.out.println("Is  Heap: " + isMaxHeap(PHONES));

        addNewCall(PHONES, 5);
        addNewCall(PHONES, 5);

        printArray(PHONES);
        System.out.println("Is  Heap: " + isMaxHeap(PHONES));

        directCall(PHONES);
        directCall(PHONES);
        directCall(PHONES);
        directCall(PHONES);
        directCall(PHONES);
        directCall(PHONES);
        printArray(PHONES);


    }

    // Fills the array with random numbers that can be equal to the maximum array length
    private static void fillArray(Phone[] phones) {

        Random random = new Random();

        for (int i = 0; i < phones.length; i++) {
            Phone phone = new Phone(i, random.nextInt(phones.length) + 1);
            phones[i] = phone;
        }

    }

    // print array method
    private static void printArray(Phone[] phones) {

        for (Phone phone : phones)
            System.out.print(phone + ", ");

        System.out.println();

    }

    private static boolean isMaxHeap(Phone[] phones) {

        for (int i = 0; i < phones.length / 2; i++) {

            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < phones.length && phones[left].getKey() > phones[i].getKey()) return false;

            if (right < phones.length && phones[right].getKey() > phones[i].getKey()) return false;

        }

        return true;

    }

    private static void buildMaxHeap(Phone[] phones) {

        for (int i = (phones.length / 2) - 1; i >= 0; i--)
            heapify(phones, i, phones.length);

    }

    private static void increaseCall(Phone[] phones, int index) {

        phones[index].setKey(phones[index].getKey() + 1);

        while (index > 0 && phones[index].getKey() > phones[(index - 1) / 2].getKey()) {

            swap(phones, index, (index - 1) / 2);
            index = (index - 1) / 2;

        }


    }

    private static void decreaseCall(Phone[] phones, int index) {

        phones[index].setKey(phones[index].getKey() - 1);

        heapify(phones, index, phones.length);


    }


    private static void heapify(Phone[] phones, int index, int heapSize) {

        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;

        if (left < heapSize && phones[left].getKey() > phones[largest].getKey()) largest = left;

        if (right < heapSize && phones[right].getKey() > phones[largest].getKey()) largest = right;

        if (largest != index) {

            swap(phones, index, largest);

            heapify(phones, largest, heapSize);

        }


    }

    private static int findIndex(Phone[] phones, int id) {

        for (int i = 0; i < phones.length; i++)
            if (phones[i].getId() == id) return i;

        return -1;
    }

    private static void swap(Phone[] phones, int i, int j) {
        Phone temp = phones[i];
        phones[i] = phones[j];
        phones[j] = temp;
    }

    public static void addNewCall(Phone[] phones, int id) {

        if (id > phones.length || id < 0) return;

        increaseCall(phones, findIndex(phones, id));
        printAddNewCall(phones, id);

    }

    public static void deleteWaitCall(Phone[] phones, int id) {

        if (id > phones.length || id < 0) return;

        decreaseCall(phones, findIndex(phones, id));
        printDeleteCall(phones, id);

    }

    public static void directCall(Phone[] phones) {

        Phone directedPhone = phones[0];

        if (directedPhone.getKey() != 0) decreaseCall(phones, 0);

        printDirect(directedPhone);

    }

    private static void printAddNewCall(Phone[] phones, int id) {
        System.out.println("Add new call the queue : " + phones[findIndex(phones, id)]);
    }

    private static void printDeleteCall(Phone[] phones, int id) {
        System.out.println("Leave call the queue : " + phones[findIndex(phones, id)]);
    }

    private static void printDirect(Phone phones) {
        System.out.println("Directing call to phone :" + phones.getId());
    }


}

class Phone {

    private final int id;
    private int key;

    public Phone(int id, int key) {
        this.id = id;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "{ Id : " + id + " Key : " + key + " }";
    }

}