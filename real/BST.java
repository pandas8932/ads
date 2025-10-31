class Node{
    int key;
    Node left,right;

    Node (int key){
        this.key=key;
        this.left=this.right=null;
    }
}

class BST_implementation{
    Node root;

    BST_implementation(){
        root=null;
    }

    
    boolean search(int key){
        boolean find = bst_search(root,key);
        return find;
    }

    boolean bst_search(Node root,int key){
        if(root==null) return false;
        if(root.key==key){
            return true;
        }
        if(root.key>key){
            return bst_search(root.left, key);
        }else{
            
            return bst_search(root.right, key);
        
        }
    }

    void Insert(int key){
        root=bst_insert(root, key);
    }

    Node bst_insert(Node root, int key){
        if(root==null){
            return new Node(key);
        }
        if(root.key>key){
            root.left= bst_insert(root.left, key);
        }else{
            root.right=bst_insert(root.right, key);
        }
        return root;
    }

    void print(){
        System.out.println("The tree in preorder");
        printTree(root);
        System.out.println();
    }

    void printTree(Node root){
        if(root==null) return ;
        System.out.print(root.key+" ");
        printTree(root.left);
        printTree(root.right);
    }

    void inorder() {
    inorderRec(root);
    System.out.println();
}

void inorderRec(Node root) {
    if (root == null) return;
    inorderRec(root.left);
    System.out.print(root.key + " ");
    inorderRec(root.right);
}



}
public class BST {
    public static void main(String[] args) {
        int array[]={20,10,30,40,5};
        BST_implementation bst=new BST_implementation();
    for(int i=0;i<array.length;i++){
        bst.Insert(array[i]);
    }

    bst.inorder();
    }

    
}
