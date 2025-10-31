class min_max {
    int[] arr;
    int max = 0;

    min_max() {
        arr = new int[10];
    }

    private int leftChild(int i) {
        return i * 2 + 1;
    }

    private int rightChild(int i) {
        return (i * 2) + 2;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private boolean isMinLevel(int i) {
        int level = (int) (Math.log(i + 1) / Math.log(2));
        return level % 2 == 0;
    }
    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void insert(int a) {
        arr[max] = a;
        max++;

        if (max == 1)
            return;

        int p = parent(max - 1);
        if (isMinLevel(p)) {
            if (arr[max - 1] < arr[p]) {
                swap(max - 1, p);
                bubbleUpMax(p);
            } else {
                bubbleUpMin(max - 1);
            }
        } else {
            if (arr[max - 1] > arr[p]) {
                swap(max - 1, p);
                bubbleUpMin(p);
            } else {
                bubbleUpMax(max - 1);
            }
        }
        this.printHeap();
    }

    private void bubbleUpMin(int i) {
        int gp = parent(parent(i));

        if (gp >= 0 && arr[i] < arr[gp]) {
            swap(i, gp);
            bubbleUpMin(gp);
        }
    }

    private void bubbleUpMax(int i) {
        int gp = parent(parent(i));

        if (gp >= 1 && arr[i] > arr[gp]) {
            swap(i, gp);
            bubbleUpMax(gp);
        }
    }

    public void printHeap() {
        for (int i = 0; i < max; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public int deleteMin() {
        int temp = arr[0];
        arr[0] = arr[max - 1];
        max--;
        pushDownMin(0);
        return temp;
    }

    public int deleteMax() {
        if (max == 0)
            throw new RuntimeException("Heap is empty!");
        if (max == 1)
            return arr[max--];

        int maxIndex;
        if (max == 2 || arr[2] > arr[3])
            maxIndex = 2;
        else
            maxIndex = 3;

        int maximum = arr[maxIndex];
        arr[maxIndex] = arr[max-1];
        max--;
        pushDownMax(maxIndex);
        return maximum;
    }


    private boolean isGrandchild(int i, int j) {
    int l = leftChild(i);
    int r = rightChild(i);
    return (j == leftChild(l) || j == rightChild(l) ||
            j == leftChild(r) || j == rightChild(r));
}


    private void pushDownMin(int i) {
        while (true) {
            int smallest = i;

            int[] candidates = {
                leftChild(i), rightChild(i),
                leftChild(leftChild(i)), rightChild(leftChild(i)),
                leftChild(rightChild(i)), rightChild(rightChild(i))
            };

            for (int j : candidates) {
                if (j < max && arr[j] < arr[smallest]) {
                    smallest = j;
                }
            }

            if (smallest == i) break; 

            // if smallest is a grandchild
            if (isGrandchild(i, smallest)) {
                swap(i, smallest);
                int parentOfSmallest = parent(smallest);

                // check if min/max property with parent violated
            
                if (arr[smallest] > arr[parentOfSmallest]) {
                    swap(smallest, parentOfSmallest);
                }

                i = smallest; // continue pushing down
            } else {
                // smallest is a child
                if (arr[smallest] < arr[i]) swap(i, smallest);
                break;
            }
        }
    }

    private void pushDownMax(int i) {
        while (true) {
            int largest = i;

            // collect children and grandchildren
            int[] candidates = {
                leftChild(i), rightChild(i),
                leftChild(leftChild(i)), rightChild(leftChild(i)),
                leftChild(rightChild(i)), rightChild(rightChild(i))
            };

            // find largest among all valid candidates
            for (int j : candidates) {
                if (j < max && arr[j] > arr[largest]) {
                    largest = j;
                }
            }

            if (largest == i) break;  // already correct

            // if largest is a grandchild
            if (isGrandchild(i, largest)) {
                swap(i, largest);
                int parentOfLargest = parent(largest);

                // fix violation with parent
                if (arr[largest] < arr[parentOfLargest]) {
                    swap(largest, parentOfLargest);
                }

                i = largest; // continue pushing down
            } else {
                // largest is a direct child
                if (arr[largest] > arr[i]) swap(i, largest);
                break;
            }
        }
    }


    public boolean search(int a) {
        for (int i : arr) {
            if (i == a)
                return true;
        }
        return false;
    }

}

public class Min_Max_heap {
    public static void main(String[] args) {
        min_max heap = new min_max();

        heap.insert(10);
        heap.insert(5);
        heap.insert(30);
        heap.insert(20);
        heap.insert(15);
        heap.insert(25);
        heap.insert(40);

        System.out.print("Heap: ");
        heap.printHeap();

        System.out.println("Delete Min: " + heap.deleteMin());
        heap.printHeap();

        System.out.println("Delete Max: " + heap.deleteMax());
        heap.printHeap();
    }
}