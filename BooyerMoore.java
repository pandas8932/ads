import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BooyerMoore {
    public static void main(String[] args) {
        String s="451464561";
        String p="456";
        int m=s.length();
        int n=p.length();
        // Scanner sc= new Scanner(System.in);
        // String s=sc.nextLine()

        Map <Character,Integer> map= new HashMap<>();
        
        for(int i=0;i<n;i++){
            map.put(p.charAt(i),Math.max(n-i-1,1));
        } 
        int index=n-1;
        while (index<m){
            int i = index;
            int j=p.length()-1;
            while(j>0){
                if(s.charAt(i) == p.charAt(j)){
                    i--;
                    j--;
                }else{
                    if(map.containsKey(s.charAt(i))){
                        index+=map.get(s.charAt(i));
                    }else{
                        index+=n;
                    }
                    
                    break;
                }
                
            }
            if(j==0){
                System.out.print(i);
                break;
            }
        }

        // System.out.println(map);

    }   
}
