package interviews.strings;

import java.util.*;

public class Hackerrank {

    /* Given a string s, and an integer, k, complete the function so that it finds
       the lexicographically smallest and largest substrings of length
     */
    public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";

        List<String> subs = new ArrayList<>();
        for (int i=0; i <= s.length() - k; i++) {
            String sub = s.substring(i, i + k);
            subs.add(sub);
        }

        Collections.sort(subs);
        smallest = subs.get(0);
        largest = subs.get(subs.size() - 1);

        return smallest + "\n" + largest;
    }

    /* Two strings, a and, b are called anagrams if they contain all the same characters in the same
       frequencies. For this challenge, the test is not case-sensitive.
       For example, the anagrams of CAT are CAT, ACT, tac, TCA, aTC, and CtA.
     */
    static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        var afrequency = frequency(a.toLowerCase());
        var bfrequency = frequency(b.toLowerCase());

        for (Map.Entry<Character, Integer> entry : afrequency.entrySet()) {
            if (!bfrequency.containsKey(entry.getKey())) {
                return false;
            }

            if (bfrequency.get(entry.getKey()) != entry.getValue()) {
                return false;
            }
        }

        return true;
    }

    private static Map<Character, Integer> frequency(String s) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i <= s.length() - 1; i++) {
            Character c = s.charAt(i);
            if (result.containsKey(c)) {
                result.put(c, result.get(c)+1);
            } else {
                result.put(c, 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getSmallestAndLargest("welcometojava" , 3));

        boolean ret = isAnagram("anagramm", "marganaa");
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}
