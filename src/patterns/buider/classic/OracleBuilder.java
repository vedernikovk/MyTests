package patterns.buider.classic;

import patterns.buider.product.ITable;
import patterns.buider.product.OracleTable;

import patterns.buider.Connection;

public class OracleBuilder implements IBuilder {
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
        return new OracleTable(con, param);
    }
}
