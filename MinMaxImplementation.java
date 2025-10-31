class MinMax{
    int arr[]=new int[50];
    int size=0;

    void insert(int key){
        arr[size++]=key;
        if(size==1){
            return; 
        }
        int i=size-1;
        int p=(i-1)/2;
        if(isMin(i)){
            if(p>=0 && arr[p]<arr[i]){
                swap(p,i);
                bubbleUpMax(p);
            }else{
                bubbleUpMin(i);
            }
        }else{
            if(p>=0 && arr[p]>arr[i]){
                swap(p,i);
                bubbleUpMin(p);
            }else{
                bubbleUpMax(i);
            }
        }

        printHeap();

    }

    void search(int key){
        for(int i=0;i<this.size;i++){
            if(arr[i]==key){
                System.out.println("key found");
                return ;
            }
        }
        System.out.println("key not found");
    }

    boolean isMin(int i){
            int level= (int) (Math.log(i+1)/ Math.log(2));
            
            return level%2==0;
    }

    private int leftChild(int i){
        return 2*i+1;
    }
    private int rightChild(int i){
        return 2*i +2;
    }
    private int parent(int i){

        if(i==0){
            return -1;
        }
        else{
            return (i-1)/2;
        }
    }

   private void swap(int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
   } 

   void bubbleUpMax(int i){
    int gp=parent(parent(i));
    if(gp>=0 && arr[gp]<arr[i]){
        swap(i, gp);
        bubbleUpMax(gp);
    }
   }

   void bubbleUpMin(int i){
    int gp=parent(parent(i));
    if(gp>=0 && arr[gp]>arr[i]){
        swap(i,gp);
        bubbleUpMin(gp);
    }
   }

   void printHeap(){
    for(int i=0;i<size;i++){
        System.out.print(arr[i] + " ");
    }
    System.out.println();
   }

   

   

}
public class MinMaxImplementation {
    public static void main(String[] args) {
        MinMax m= new MinMax();
        int insert[]={16,33,19,21,23,18,17,9,4,2};
        for (int i=0;i<10;i++){
            m.insert(insert[i]);
        }
        
    }
}
