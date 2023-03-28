package patterns.buider.fluent;

import patterns.buider.product.ITable;
import patterns.buider.Connection;

public class UseIt {
    public static void main(String[] args) {
        ITable dao = builder().addParam("A").addConnection(new Connection()).build();
        System.out.println(dao);
    }

    private static IParam builder() {
        return new OracleBuilder();
    }
}
