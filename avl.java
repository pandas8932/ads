class Node{
    int val;
    Node left;
    Node right;
    int height;

    Node (int val){
        this.val=val;
        this.left=null;
        this.right=null;
        this.height=1;
    }
}
public class avl {

    

    static int getHeight(Node root){
        if(root==null){
            return 0;
        }
        return root.height;
    }

    static void updateHeight(Node node){
        node.height=1+Math.max(getHeight(node.left),getHeight(node.right));
    }

    static int getBalance(Node node){
        if(node==null){
            return 0;
        }
        return getHeight(node.left)-getHeight(node.right);
    }

    static Node rightRotation(Node x){
        Node y=x.left;
        Node T2=y.right;
        x.left=T2;
        y.right=x;

         updateHeight(x);
         updateHeight(y);

        return y;

    }

    static Node leftRotation(Node x){
        Node y=x.right;
        Node T2=y.left;
        x.right=T2;
        y.left=x;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    static Node insertAvl(Node root,int key){
        if(root==null){
            return new Node(key);
        }
        if(root.val>key){
            root.left= insertAvl(root.left, key);
        }else if(root.val<key){
            root.right=insertAvl(root.right, key);
        }else{
            return root;
        }

        updateHeight(root);

        int balance=getBalance(root);

        if(balance > 1 && key < root.left.val){
            return rightRotation(root);
        }
        else if(balance < -1 && key > root.right.val){
            
            return leftRotation(root);
        }
        else if(balance > 1 && key >root.left.val){
            root.left=leftRotation(root.left);
            return rightRotation(root);
        }
        else if(balance < -1 && key <root.right.val){
            root.right=rightRotation(root.right);
            return leftRotation(root);
        }


        return root;
    }

    static Node getSuccesor(Node root){
        if(root.left!=null) return getSuccesor(root.left);
        return root;
    }

    static Node getPredeccsor(Node root){
        if(root.right!=null) return getPredeccsor(root.right);
        return root;
    }
    
    static Node deleteAvl(Node root, int key){
        if(root == null){
            return null;
        }

        if(key<root.val){
            root.left=deleteAvl(root.left, key);
        }else if(key>root.val){
            root.right=deleteAvl(root.right, key);
        }else{
            
        }

        updateHeight(root);

        int balance=getBalance(root);

        if(balance)

        return root;
    }

    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

    static void printLevelOrder(Node root) {
        java.util.Queue<Node> q = new java.util.LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Node node = q.poll();
                System.out.print(node.val + " ");
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            System.out.println(); // next level
        }
    }
    
    public static void main(String[] args) {
        Node root = null;

        // Example 1: Normal insertions
        int[] values = {30, 20, 10, 25, 40, 50, 22};

        for (int val : values) {
            root = insertAvl(root, val);
        }

        System.out.println("\nInorder Traversal (Sorted):");
        inorder(root);

        System.out.println("\n\nLevel Order Traversal (Tree Structure):");
        printLevelOrder(root);

        // Example 2: Force Right-Left (RL) case
        System.out.println("\n\n--- Forcing RL Case ---");
        Node rlRoot = null;
        int[] rlValues = {10, 30, 20};
        for (int v : rlValues) rlRoot = insertAvl(rlRoot, v);
        System.out.println("Inorder after RL Case:");
        inorder(rlRoot);

        // Example 3: Force Left-Right (LR) case
        System.out.println("\n\n--- Forcing LR Case ---");
        Node lrRoot = null;
        int[] lrValues = {30, 10, 20};
        for (int v : lrValues) lrRoot = insertAvl(lrRoot, v);
        System.out.println("Inorder after LR Case:");
        inorder(lrRoot);
    }
}
