import java.math.BigInteger;
import java.util.*;
public class a1
{
    static BigInteger divid(String a)
    {
        int start=0;
        int end=a.length()-1;
    
    if(start==end)
    {
        return new BigInteger(a).pow(2);
    }
    int mid =(start + end)/2;

    BigInteger left, right;

    String s1 =a.substring(0, mid+1);
    left = divid(s1);

    String s2 = a.substring(mid+1,end+1);
    right =divid(s2);

    int length = Math.min(s1.length(),s2.length());
    BigInteger pow1 = BigInteger.TEN.pow(length*2);
    BigInteger pow2 =BigInteger.TEN.pow(length);

    BigInteger multipy = pow1.multiply(left).add(right).add(pow2.multiply(new BigInteger(s1)).multiply(new BigInteger(s2)).multiply(BigInteger.TWO));
    return multipy;
}
public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    System.out.println("enter a large no");
    String num =s.nextLine();
    System.out.println("number"+num);
    System.out.println("square"+divid(num));
    s.close();
}

}