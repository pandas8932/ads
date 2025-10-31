class RBNode {
    int key;
    RBNode left, right, parent;
    boolean color; // true = RED, false = BLACK

    RBNode(int key) {
        this.key = key;
        this.color = true; // New node is RED by default
        this.left = this.right = this.parent = null;
    }
}

class RedBlackTree {
    private RBNode root;
    private final boolean RED = true;
    private final boolean BLACK = false;

    // Left Rotation
    private void leftRotate(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != null) y.left.parent = x;

        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    // Right Rotation
    private void rightRotate(RBNode x) {
        RBNode y = x.left;
        x.left = y.right;
        if (y.right != null) y.right.parent = x;

        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;

        y.right = x;
        x.parent = y;
    }

    // Insert method
    public void insert(int key) {
        RBNode newNode = new RBNode(key);
        root = bstInsert(root, newNode);
        fixInsert(newNode);
    }

    // Normal BST insertion
    private RBNode bstInsert(RBNode root, RBNode node) {
        if (root == null) return node;
        if (node.key < root.key) {
            root.left = bstInsert(root.left, node);
            root.left.parent = root;
        } else if (node.key > root.key) {
            root.right = bstInsert(root.right, node);
            root.right.parent = root;
        }
        return root;
    }

    // Fixing the red-black properties after insert
    private void fixInsert(RBNode node) {
        while (node != root && node.parent.color == RED) {
            RBNode parent = node.parent;
            RBNode grandparent = parent.parent;

            if (parent == grandparent.left) {
                RBNode uncle = grandparent.right;
                if (uncle != null && uncle.color == RED) { // Case 1
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandparent.color = RED;
                    node = grandparent;
                } else {
                    if (node == parent.right) { // Case 2
                        node = parent;
                        leftRotate(node);
                    }
                    // Case 3
                    parent.color = BLACK;
                    grandparent.color = RED;
                    rightRotate(grandparent);
                }
            } else { // Mirror case
                RBNode uncle = grandparent.left;
                if (uncle != null && uncle.color == RED) { // Case 1
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandparent.color = RED;
                    node = grandparent;
                } else {
                    if (node == parent.left) { // Case 2
                        node = parent;
                        rightRotate(node);
                    }
                    // Case 3
                    parent.color = BLACK;
                    grandparent.color = RED;
                    leftRotate(grandparent);
                }
            }
        }
        root.color = BLACK; // Property: Root must be BLACK
    }

    // Search method (standard BST search)
    public boolean search(int key) {
        return searchHelper(root, key);
    }

    private boolean searchHelper(RBNode node, int key) {
        if (node == null) return false;
        if (key == node.key) return true;
        else if (key < node.key) return searchHelper(node.left, key);
        else return searchHelper(node.right, key);
    }

    // Inorder traversal for debugging
    public void inorder() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(RBNode node) {
        if (node != null) {
            inorderHelper(node.left);
            System.out.print(node.key + (node.color ? "(R) " : "(B) "));
            inorderHelper(node.right);
        }
    }
}

// ✅ Driver code
public class RBTreeDemo {
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();

        rbt.insert(10);
        rbt.insert(20);
        rbt.insert(30);
        rbt.insert(15);
        rbt.insert(25);
        rbt.insert(5);

        System.out.println("Inorder traversal (with colors):");
        rbt.inorder();

        System.out.println("Search 15: " + rbt.search(15));
        System.out.println("Search 50: " + rbt.search(50));
    }
}
