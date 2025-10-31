import java.util.*;
public class BruteForce {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        String s=sc.nextLine();
        String p=sc.nextLine();

        for(int i=0;i<=s.length()-p.length();i++){
            int j;
            for(j=0;j<p.length();j++){
                if(s.charAt(i+j)!=p.charAt(j)){
                    break;
                }
            }
            if(j==p.length()){
                System.out.println(i);
            }
        }
    }
}
