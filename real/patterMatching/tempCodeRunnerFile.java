import java.util.*;

public class booyermoore {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        String s=sc.nextLine();
        String p=sc.nextLine();
        int m=s.length();
        int n=p.length();

        Map <Character,Integer> map=new HashMap<>();

        for(int i=0;i<n;i++){
            map.put(p.charAt(i),Math.max(n-i-1,1));
        }
        int index=n-1;
        while(index<m){
            int i=index;
            int j=n-1;
            while(j>=0 && s.charAt(i)==p.charAt(j)){
                i--;j--;
            }

            if(j<0){
                System.out.println(index);
                break;
            }else{
                if(map.containsKey(s.charAt(i))){
                    index+=map.get(s.charAt(i));
                }else{
                    index+=n;
                }
            }
        }
    }
}
