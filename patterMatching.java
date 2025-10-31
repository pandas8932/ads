public class patterMatching {
    public static void main(String[] args) {
        String s="abcabcabcd";
        String p="abcd";

        for(int i=0;i<s.length()-p.length()+1;i++){
            boolean flag=true;
            for(int j=0;j<p.length();j++){
                if(s.charAt(i+j)!=p.charAt(j)){
                    flag=false;
                }
            }
            if(flag==true){
                System.out.println(i);
            }
        }
    }
}
