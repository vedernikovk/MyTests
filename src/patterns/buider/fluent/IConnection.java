package patterns.buider.fluent;

import patterns.buider.Connection;

public interface IConnection {
    IBuilder addConnection(Connection con);
}
