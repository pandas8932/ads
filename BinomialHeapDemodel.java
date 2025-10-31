import java.util.*;

public class BinomialHeapDemodel {

    // Node structure
    static class Node {
        int key, degree;
        Node parent, child, sibling;

        Node(int key) {
            this.key = key;
            this.degree = 0;
            this.parent = this.child = this.sibling = null;
        }
    }

    static Node head = null;

    // Merge two binomial heaps
    static Node mergeHeaps(Node h1, Node h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        Node head;
        Node tail;
        Node a = h1, b = h2;

        if (a.degree <= b.degree) {
            head = a;
            a = a.sibling;
        } else {
            head = b;
            b = b.sibling;
        }

        tail = head;
        while (a != null && b != null) {
            if (a.degree <= b.degree) {
                tail.sibling = a;
                a = a.sibling;
            } else {
                tail.sibling = b;
                b = b.sibling;
            }
            tail = tail.sibling;
        }
        tail.sibling = (a != null) ? a : b;
        return head;
    }

    // Link two binomial trees of same degree
    static void link(Node y, Node z) {
        y.parent = z;
        y.sibling = z.child;
        z.child = y;
        z.degree++;
    }

    // Union operation
    static Node unionHeaps(Node h1, Node h2) {
        Node newHead = mergeHeaps(h1, h2);
        if (newHead == null) return null;

        Node prev = null, curr = newHead, next = curr.sibling;

        while (next != null) {
            if ((curr.degree != next.degree) ||
                (next.sibling != null && next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else {
                if (curr.key <= next.key) {
                    curr.sibling = next.sibling;
                    link(next, curr);
                } else {
                    if (prev == null)
                        newHead = next;
                    else{ 
                        prev.sibling = next;
                    }
                    link(curr, next);
                    curr = next;
                }
            }
            next = curr.sibling;
        }
        return newHead;
    }

    // Insert a key
    static void insert(int key) {
        Node newNode = new Node(key);
        head = unionHeaps(head, newNode);
        System.out.println("Inserted: " + key);
    }

    // Find the minimum key
    static int findMin() {
        if (head == null) return Integer.MIN_VALUE;
        Node x = head;
        int min = Integer.MAX_VALUE;

        while (x != null) {
            if (x.key < min) {
                min = x.key;
            }
            x = x.sibling;
        }
        return min;
    }

    // Search a key in heap (DFS)
    static boolean search(Node node, int key) {
        if (node == null) return false;
        if (node.key == key) return true;
        return search(node.child, key) || search(node.sibling, key);
    }

    // Display the heap
    static void display(Node node) {
        if (node == null) return;
        System.out.print(node.key + " ");
        display(node.child);
        display(node.sibling);
    }

    // Main Function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice, key;

        while (true) {
            System.out.println("\n--- Binomial Heap Operations ---");
            System.out.println("1. Insert");
            System.out.println("2. Find Minimum");
            System.out.println("3. Search");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter key to insert: ");
                    key = sc.nextInt();
                    insert(key);
                    break;

                case 2:
                    key = findMin();
                    if (key == Integer.MIN_VALUE)
                        System.out.println("Heap is empty!");
                    else
                        System.out.println("Minimum key: " + key);
                    break;

                case 3:
                    System.out.print("Enter key to search: ");
                    key = sc.nextInt();
                    if (search(head, key))
                        System.out.println("Key found in heap.");
                    else
                        System.out.println("Key not found.");
                    break;

                case 4:
                    System.out.print("Heap Elements: ");
                    display(head);
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
