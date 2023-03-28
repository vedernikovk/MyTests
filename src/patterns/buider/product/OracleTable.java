package patterns.buider.product;

import patterns.buider.Connection;

public class OracleTable implements ITable {

    private Connection con;
    private String param;

    public OracleTable(Connection con, String param) {
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
