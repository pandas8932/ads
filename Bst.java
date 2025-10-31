public class Bst {

    // Node class
    public class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    // Insert node in BST
    Node InsertNode(Node root, int val) {
        if (root == null) {
            return new Node(val);
        }

        if (val < root.val) {
            root.left = InsertNode(root.left, val); // assign back to left
        } else if (val > root.val) {
            root.right = InsertNode(root.right, val); // assign back to right
        }
        // duplicates ignored
        return root;
    }

    // Search node in BST
    Boolean SearchNode(Node root, int key) {
        if (root == null) return false;
        if (root.val == key) return true;
        if (key < root.val) return SearchNode(root.left, key);
        return SearchNode(root.right, key);
    }

    // Delete node in BST
    Node DeleteNode(Node root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = DeleteNode(root.left, key); // update left
        } else if (key > root.val) {
            root.right = DeleteNode(root.right, key); // update right
        } else {
            // Node found
            if (root.left == null && root.right == null) return null; // Case 1: no children
            else if (root.left == null) return root.right; // Case 2: only right child
            else if (root.right == null) return root.left; // Case 2: only left child
            else {
                // Case 3: two children
                Node rms = helper(root.right); // find inorder successor
                root.val = rms.val; // copy value
                root.right = DeleteNode(root.right, rms.val); // delete successor
            }
        }
        return root;
    }

    // Helper to find leftmost node (inorder successor)
    Node helper(Node root) {
        if (root.left != null) return helper(root.left);
        return root;
    }

    // Print tree in-order (sorted)
    void printTree(Node root) {
        if (root == null) return;
        printTree(root.left);
        System.out.print(root.val + " ");
        printTree(root.right);
    }

    // Print tree structure
    void printTreeStructure(Node root, String prefix, boolean isLeft) {
        if (root == null) return;
        System.out.println(prefix + (isLeft ? "├── " : "└── ") + root.val);
        printTreeStructure(root.left, prefix + (isLeft ? "│   " : "    "), true);
        printTreeStructure(root.right, prefix + (isLeft ? "│   " : "    "), false);
    }

    // Main function to test
    public static void main(String[] args) {
        Bst tree = new Bst();
        Node root = null;

        // Insert nodes
        root = tree.InsertNode(root, 50);
        root = tree.InsertNode(root, 30);
        root = tree.InsertNode(root, 70);
        root = tree.InsertNode(root, 20);
        root = tree.InsertNode(root, 40);
        root = tree.InsertNode(root, 60);
        root = tree.InsertNode(root, 80);

        System.out.println("Original Tree (in-order):");
        tree.printTree(root);
        System.out.println("\nOriginal Tree (structure):");
        tree.printTreeStructure(root, "", false);

        // Delete nodes
        root = tree.DeleteNode(root, 20); // leaf
        root = tree.DeleteNode(root, 30); // one child
        root = tree.DeleteNode(root, 50); // two children

        System.out.println("\nTree after deletions (in-order):");
        tree.printTree(root);
        System.out.println("\nTree after deletions (structure):");
        tree.printTreeStructure(root, "", false);

        // Search nodes
        System.out.println("\nSearch 40: " + tree.SearchNode(root, 40));
        System.out.println("Search 100: " + tree.SearchNode(root, 100));
    }
}
