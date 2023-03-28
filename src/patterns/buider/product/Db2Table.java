package patterns.buider.product;

import patterns.buider.Connection;

public class Db2Table implements ITable {

    private Connection con;
    private String param;

    public Db2Table(Connection con, String param) {
        this.con = con;
        this.param = param;
    }

    @Override
    public void insert() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
