package pack.firstProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 10.03.2016.
 */
public class Collection {
    public static void main(String[] args) {
        List<String> languages = new ArrayList<String>();
        languages.add("Java");
        languages.add("Python");
        languages.add("PHP");
        for (String i : languages) {
            System.out.println(i);
        }
        System.out.println(languages.size());
        System.out.println(languages.get(0));
        System.out.println();
        List<String> web = Arrays.asList("HTML", "CSS", "JS");

        for (String i : web) System.out.println(i);
    }
}
