class leftist_Node {
    int key;
    leftist_Node left, right;
    int npl; // null path length

    leftist_Node(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.npl = 0;
    }
}

class leftist_Tree {
    leftist_Node root;

    leftist_Tree() {
        root = null;
    }

    boolean isEmpty() {
        return root == null;
    }

    leftist_Node meld(leftist_Node h1, leftist_Node h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        // Ensure heap property: smaller key at root
        if (h1.key > h2.key) {
            leftist_Node temp = h1;
            h1 = h2;
            h2 = temp;
        }

        // Meld h1's right child with h2
        h1.right = meld(h1.right, h2);

        // Maintain leftist property
        int l_npl = (h1.left == null) ? -1 : h1.left.npl;
        int r_npl = (h1.right == null) ? -1 : h1.right.npl;

        if (l_npl < r_npl) {
            // swap children
            leftist_Node temp = h1.left;
            h1.left = h1.right;
            h1.right = temp;
        }

        // Update NPL
        h1.npl = (h1.right == null) ? 0 : h1.right.npl + 1;

        return h1;
    }

    leftist_Node insert(int key) {
        leftist_Node h2 = new leftist_Node(key);
        root = meld(root, h2);
        return root;
    }

    int delete() {
        if (isEmpty()) {
            System.out.println("Heap is empty!");
            return -1;
        }

        int min = root.key;
        root = meld(root.left, root.right);
        return min;
    }

    void printHeap() {
        System.out.print("Heap (preorder): ");
        printRec(root);
        System.out.println();
    }

    void printRec(leftist_Node node) {
        if (node == null) return;
        System.out.print(node.key + " ");
        printRec(node.left);
        printRec(node.right);
    }
}

public class lt {
    public static void main(String[] args) {
        leftist_Tree heap = new leftist_Tree();
        heap.insert(10);
        heap.insert(5);
        heap.insert(20);
        heap.insert(30);
        heap.insert(15);

        heap.printHeap();

        System.out.println("Deleted min: " + heap.delete());
        heap.printHeap();
    }
}
