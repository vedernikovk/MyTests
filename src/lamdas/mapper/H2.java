package lamdas.mapper;

public class H2 {

    static String mapMe(Y y, StringMapper<Y> t) {
        return t.map(y);
    }

    static String mapMe(X x, StringMapper<X> t) {
        return t.map(x);
    }

    public static void main(String[] args) {
        System.out.println(mapMe(new Y<>(new X("Hello")), e -> e.toString() ));

        System.out.println(mapMe(new Y<>(3),
                e ->  e.toString().equals("3") ? "hello" : "HELLO" ));

        System.out.println(mapMe(new Y<>("HELLO"), e -> e.toString() ));


        /*
        System.out.println(mapMe(new X("HELLO"), e -> {
            if (e.s.charAt(0) == 'H') return e.toString();
            return e.toLowerCase();
        }
        ));
        */

        System.out.println(mapMe(new Y<>(3), e -> e.toString() ));
    }
}
