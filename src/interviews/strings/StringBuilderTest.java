package interviews.strings;

import java.util.StringJoiner;

public class StringBuilderTest {
    public static void main(String... args) {
        StringBuilder builder = new StringBuilder("Welcome everybody");
        StringJoiner joiner = new StringJoiner(" ", "H", "?");

        builder.replace(0, 7, "welcome");
        builder.setCharAt(7, '!');

        joiner.add("ello and").add(builder);
        System.out.println(joiner.toString());
    }
}

