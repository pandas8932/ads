public class QuickSort {

    void sort(int[] array, int l, int r) {
        if (l < r) {
            int loc = partition(array, l, r);
            sort(array, l, loc - 1);
            sort(array, loc + 1, r);
        }
    }

    int partition(int[] array, int l, int r) {
        int pivot = array[l];
        int i = l;
        int j = r;

        while (i < j) {
            while (i <= r && array[i] <= pivot) {
                i++;
            }
            while (j >= l && array[j] > pivot) {
                j--;
            }
            if (i < j) {
                swap(array, i, j);
            }
        }

        // place pivot in correct position
        swap(array, l, j);
        return j;
    }

    void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {12, 9, 2, 1, 4, 0};
        System.out.print("Before Sorting: ");
        for (int val : array) {
            System.out.print(val + " ");
        }

        QuickSort qs = new QuickSort();
        qs.sort(array, 0, array.length - 1);

        System.out.print("\nAfter Sorting: ");
        for (int val : array) {
            System.out.print(val + " ");
        }
    }
}
