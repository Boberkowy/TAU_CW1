package pl.edu.pjatk.tau.Web;

/**
 * Created by Boberkowy on 16.05.2017.
 */

public class mop {
    public static void main(String[] args) {

        int i = 5;
        Integer j = 10;

        changePrimitive(i);
        changeObject(j);

        System.out.println(i);
        System.out.println(j);
    }

    static void changePrimitive(int i) {
        i = 20;
    }

    static void changeObject(Integer j) {
        j = 30;
    }
}

