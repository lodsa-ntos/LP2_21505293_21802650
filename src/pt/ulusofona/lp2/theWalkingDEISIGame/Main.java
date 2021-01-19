package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        String a = "ola\n";
        System.out.println(a.substring(0, a.length()-1));

        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(2, 1, 3));
        b.stream()
                .sorted(Comparator.comparingInt(eq -> eq))
                .forEach(System.out::println);


    }
}
