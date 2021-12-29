package lab3;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //new API().createAccount("63467");
        new API().play("63467", 1, 352);
        //x0 : 984452904
        //x1 : 1496393831
        //x2 : 1276512922
        long a1 = 984452904 - 1496393831;
        long a2 = 1276512922 - 1496393831;
        int kInt = Integer.MAX_VALUE + (Integer.MAX_VALUE + 2 - 1616625442);
        long k = (long) Integer.MAX_VALUE + (Integer.MAX_VALUE + 2 - 529151308);
        System.out.println(kInt);
        System.out.println(k);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);
        //System.out.println(Math.pow(2, 32));
        //System.out.println(a1);
        //System.out.println(a2);
        //System.out.println((long) 1040402877 - 1664525 * a1);
        //System.out.println(a2 % Math.pow(2, 32));

        //long inverse_a1 = 0;
        //long a = (inverse_a1 * a1) / (long) Math.pow(2, 32);
        //System.out.println(a);
    }



}
