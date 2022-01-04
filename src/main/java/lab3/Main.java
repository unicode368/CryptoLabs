package lab3;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //new API().createAccount("63467");
        //new API().play("63467", 1, 352);
        //x0 : 1748853791
        //x1 : -998743310
        //x2 : -677747287
        //{"realNumber":-1589087500,"message":"You lost this time","account":
        // {"money":981,"deletionTime":"2021-12-29T15:26:23.0928168Z","id":"63467"}}
        int kInt = Integer.MAX_VALUE + (Integer.MAX_VALUE + 2 - 1616625442);
        long k = (long) Integer.MAX_VALUE + (Integer.MAX_VALUE + 2 - 1616625442);
        int x0 = 1748853791;
        int x1 = Integer.MAX_VALUE + (Integer.MAX_VALUE + 2 - 998743310);
        int x2 = Integer.MAX_VALUE + (Integer.MAX_VALUE + 2 - 677747287);
        int a1 = x0 - x1;
        int a2 = x1 - x2;
        /*System.out.println(kInt);
        System.out.println(k);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);*/
        System.out.println(x0);
        System.out.println(x1);
        System.out.println(x2);
        System.out.println(a1);
        System.out.println(a2);
    }



}
