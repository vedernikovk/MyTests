package patterns.buider.classic;

import patterns.buider.product.ITable;

import patterns.buider.Connection;

public interface IBuilder {

    IBuilder addConnection(Connection con);
    IBuilder addParam(String param);

    ITable build();
}
