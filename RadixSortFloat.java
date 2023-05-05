import java.util.*;

public class RadixSortFloat {
    public static void radixSort(double[] arr, ArrayList<Double> list) {
        int n = arr.length;
        double max = Arrays.stream(arr).max().getAsDouble();

        ArrayList<Double>[] arr1 = new ArrayList[10];
        ArrayList<Double>[] arr2 = new ArrayList[10];

        // Initialize each element of the array with an ArrayList object
        for (int i = 0; i < 10; i++) {
            arr1[i] = new ArrayList<Double>();
            arr2[i] = new ArrayList<Double>();
        }
        //declare two dynamic arrays of ArrayList objects
        // e.g. index[0] is for digit '0', [1] for digit '1'...
        // so we add the appropriate integers to each respective object

        boolean evenTime = false;
        // if it is 1st,3rd,5th... time to sort,arr1 is involved
        // if it is 2nd,4th, 6th... time to sort, arr2 is involved
        for (int exp = 1; max / exp > 0; exp *= 10) {
            if (!evenTime) {
                countingSort(n, exp, arr1, list);
                evenTime = true;
                // clear all object in arr2 after each sort
                for (int i = 0; i < 10; i++) {
                    arr2[i] = new ArrayList<Double>();
                }
            } else {
                countingSort(n, exp, arr2, list);
                evenTime = false;
                // clear all object in arr1 after each sort
                for (int i = 0; i < 10; i++) {
                    arr1[i] = new ArrayList<Double>();
                }
            }
        }
    }

    public static void countingSort(int n, int exp, ArrayList<Double>[] arrayInUse, ArrayList<Double> list) {
        // radix sorting based on the significant figures
        // array in use (arr1 or arr2) stores the doubles in correct order
        // e.g. if first double is 73.14, it is added to ArrayList object at index 4
        for (int i = 0; i < n; i++) {
            arrayInUse[(int) (list.get(i) / exp) % 10].add(list.get(i));
        }
        // This intermediary list is cleared
        // to store the doubles in the order which they are sorted in arrayInUse (either arr1 or arr2)
        list.clear();
        for (int i = 0, j = 0, k = 0; i < n; k++) {
            if (!arrayInUse[j].isEmpty()) {
                list.add(arrayInUse[j].get(k));
                if (k == arrayInUse[j].size() - 1) {
                    j++;
                    k = -1;
                }
                i++;
            } else {
                j++;
                k = -1;
            }
        }
        System.out.println(list);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> list = new ArrayList<Double>();
        System.out.println("Input numbers to sort(enter non-number to stop):");

        // use a list to store the input
        // this list is an intermediary in the sorting process later on
        while (scanner.hasNextDouble()) {
            double input = scanner.nextDouble();
            list.add(input);
        }
        int size = list.size();

        // Create an array for finding the maximum number
        double[] arr = new double[size];
        for (int i = 0; i < size; i++)
            arr[i] = list.get(i);

        System.out.println("Done!");
        radixSort(arr, list);
    }
}
