package lamdas.predicate;

import java.util.function.Predicate;

public class Predicator {
    static boolean testit(Object s, Predicate<Object> p) {
        return p.test(s);
    }

    public static void  main(String... args) {
        final String s1 = "HI";
        final Integer i1 = 3;
        final int x = 1;

        MyPredicate<Object>[] p = new MyPredicate[3];

        p[0] = new MyPredicate(e -> {
                return e.equals(s1);
            }
        );

        p[1] = new MyPredicate(e -> {
            if (x  == 1) {
                return e.equals(s1);
            } else {
                return !e.equals(s1);
            }
        });

        p[2] = new MyPredicate(e -> {
            if (x  != 1) {
                return e.equals(s1);
            } else {
                return !e.equals(s1);
            }
        });

        /*
        p[3] = new MyPredicate((Object) e -> {
            if (x  != 1) { return e.equals(s1); } else { return !e.equals(s1); }
        });
        */

        for (MyPredicate<Object> pp:p) {
            System.out.println(testit(s1, pp));
        }
    }
}

class MyPredicate<T> implements Predicate<T> {

    Predicate<T> local;

    public MyPredicate(Predicate<T> t) {
        this.local = t;
    }

    @Override
    public boolean test(T t) {
        return local.test(t);
    }
}
