

class Node{
    int key,npl;
    Node left ,right;
    Node(int key){
        this.key=key;
        this.left=this.right=null;
        this.npl=0;
    }
}

class lt{
    Node root;
    lt(){
        this.root=null;
    }

    boolean isEmpty(){
        return root==null;
    }

    Node meld(Node h1,Node h2){
        if(h1==null) return h2;
        if(h2==null) return h1;
        if(h1.key>h2.key){
            Node temp=h1;
            h1=h2;
            h2=temp;
        }
        h1.right=meld(h1.right,h2);

        int left_npl=(h1.left==null)?-1:h1.left.npl;
        int right_npl=(h1.right==null)?-1:h1.right.npl;

        if(left_npl<right_npl){
            Node temp=h1.left;
            h1.left=h1.right;
            h1.right=temp;
        }

        h1.npl=(h1.right==null)?0:h1.right.npl+1;
        return h1;
    }

    void insert(int key){
        Node h2=new Node(key);
        root=meld(root, h2);
    }

    int delete(){
        if(isEmpty()){
            return -1;
        }
        int temp=root.key;
        root=meld(root.left,root.right);
        return temp;
    }

    void printtree(){
        System.out.println("the Leftist heap in inorder :");
        printRec(root);
        System.out.println();
    }

    void printRec(Node root){
        if(root==null) return;
        printRec(root.left);
        System.out.print(root.key+" ");
        printRec(root.right);
    }
}

public class leftistTree {
    public static void main(String[] args) {
        lt heap = new lt();

        heap.insert(10);
        heap.insert(5);
        heap.insert(20);
        heap.insert(3);
        heap.insert(15);

        heap.printtree();

        System.out.println("Deleted root: " + heap.delete());
        heap.printtree();

        System.out.println("Deleted root: " + heap.delete());
        heap.printtree();
    }
}
