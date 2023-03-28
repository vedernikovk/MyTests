package patterns.buider.classic;

import patterns.buider.product.Db2Table;
import patterns.buider.product.ITable;

import patterns.buider.Connection;

public class Db2Builder implements IBuilder {
    private Connection con;
    private String param;

    @Override
    public IBuilder addConnection(Connection con) {
        this.con = con;
        return this;
    }

    @Override
    public IBuilder addParam(String param) {
        this.param = param;
        return this;
    }

    @Override
    public ITable build() {
        return new Db2Table(con, param);
    }
}