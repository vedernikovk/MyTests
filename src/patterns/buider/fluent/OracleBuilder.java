package patterns.buider.fluent;

import patterns.buider.product.ITable;
import patterns.buider.product.OracleTable;

import patterns.buider.Connection;

public class OracleBuilder implements IBuilder, IParam, IConnection {

    private Connection con;
    private String param;

    @Override
    public ITable build() {
        return new OracleTable(con, param);
    }

    @Override
    public IBuilder addConnection(Connection con) {
        this.con = con;
        return this;
    }

    @Override
    public IConnection addParam(String param) {
        this.param = param;
        return this;
    }
}
