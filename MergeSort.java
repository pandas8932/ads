import java.util.*;
public class MergeSort {
    void Merge(int array[],int l,int m,int r){
        int s1=m-l+1;
        int s2=r-m;

        int temp1[]=new int[s1];
        int temp2[]=new int[s2];
        int temp[]=new int [s1+s2];

        for(int i=0;i<s1;i++){
            temp1[i]=array[l+i];
        }

        for(int i=0;i<s2;i++){
            temp2[i]=array[m+1+i];
        }

        int p=0;int q=0;int o=0;
        while(p<s1 && q<s2){
            if(temp1[p]<temp2[q]){
                temp[o]=temp1[p++];
            }
            else{
                temp[o]=temp2[q++];
            }
            o++;
        }
        while(p<s1){
            temp[o]=temp1[p++];o++;
        }
        while(q<s2){
            temp[o]=temp2[q++];o++;
        }
        for(int i=0;i<temp.length;i++){
            array[l+i]=temp[i];
        }
    }


    void Sort(int array[],int l,int r){
        if(r<=l){
            return;
        }
        int m=(l+r)/2;
        Sort(array,l,m);
        Sort(array,m+1,r);
        Merge(array,l,m,r);
        return ;

    }
    public static void main(String[] args) {
        int array[]={12,9,2,1,4,0};
        
        System.out.print("Before Sorting : ");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i] + " ");
        }
        
        MergeSort ms= new MergeSort();
        ms.Sort(array,0,array.length-1);
        System.out.print("\nafter Sorting : ");
        Arrays.sort(array);

        for(int i=0;i<array.length;i++){
            System.out.print(array[i] + " ");
        }

    }
}
