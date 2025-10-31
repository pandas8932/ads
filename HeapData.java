public class HeapData {
    public static void swap(int array[],int p,int q){
        int temp=array[p];
        array[p]=array[q];
        array[q]=temp;
    }
    public static class Heap{
        int size=0;
        int arr[]=new int[100];

        

        void insert(int val){
            size++;
            int index=size;
            arr[index]=val;
            while(index>1){
                int parent = index / 2;
                if(arr[index]>arr[index/2]){
                    swap(arr,index,parent);
                    index=index/2;
                }else{
                    return ;
                }
            }
        }

        void print(){
            for(int i=1;i<size+1;i++){
                System.out.print(arr[i]+" ");
            }
        }

        void delete(){
            swap(arr,1,size);
            size--;
            int i=1;
            while(i<size){
                int lc=2*i;
                int rc=2*i+1;
                boolean flag=true,change=false;
                if(arr[lc]>arr[rc]){
                    flag=true;
                }else{
                    flag=false;
                }
                if(flag){
                    if(lc<=size && arr[lc]>arr[i]){
                        swap(arr, lc, i);
                        i=lc;change=true;
                    }
                }else{
                    if(rc<=size && arr[rc]>arr[i]){
                        swap(arr, rc, i);
                        i=rc;change=true;
                    }
                }
                if(change!=true){
                    break;
                }
            }
        }
        

    }
    public static void main(String[] args) {
        Heap h=new Heap();
        h.insert(10);
        h.insert(45);
        h.insert(8);
        h.insert(13);
        h.insert(56);
        h.insert(12);
        h.insert(13);
        h.print();
        
    }
}
