import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

import org.apache.commons.io.FileUtils;


public class JDBCTestSelect {

	public static void main(String[] args) {

        try {
            String encoding = System.getProperty("file.encoding");
            System.out.println(encoding);

        	System.setProperty("file.encoding", "UTF-8");
            encoding = System.getProperty("file.encoding");
            System.out.println(encoding);

	        // Load the database driver
	        //Class.forName( "com.ibm.db2.jcc.DB2Driver" ) ;
        	Class.forName( "oracle.jdbc.OracleDriver" ) ;
            //Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" ) ;

	        // Get a connection to the database
	        //Connection conn = DriverManager.getConnection( "jdbc:db2://172.16.66.12:5065/VENTYX8X", "JXK0100", "JXK0100" ) ;
        	Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@169.254.56.200:1521:local", "d01e", "d01e" ) ;
            //Connection conn = DriverManager.getConnection( "jdbc:sqlserver://USATL-S-ASDEV04\\AS9MSSQL;database=AS9MSSQL1", "as9", "as9" ) ;
	        conn.setAutoCommit(false);

	         // Print all warnings
	         for( SQLWarning warn = conn.getWarnings(); warn != null; warn = warn.getNextWarning() ) {
	             System.out.println( "SQL Warning:" ) ;
	             System.out.println( "State  : " + warn.getSQLState()  ) ;
	             System.out.println( "Message: " + warn.getMessage()   ) ;
	             System.out.println( "Error  : " + warn.getErrorCode() ) ;
	         }

	         // Get a statement from the connection	         
	         String query = "SELECT invoice_date FROM tidapmst WHERE INVOICE_NO = :1";

	         PreparedStatement stmt = conn.prepareStatement(query);
	         stmt.setString(1, "1                             ");

	         // Execute the query
	         ResultSet rs = stmt.executeQuery();
             if (rs.next()) {
            	 String XML = readClob(rs.getClob("XML_DOCUMENT"));
            	 FileUtils.write(new File("message.xml"), XML, Charset.forName("UTF-8")); 
             }

	         // Close the result set, statement and the connection
	         conn.commit();
	         stmt.close() ;
	         conn.close() ;

        } catch( SQLException se ) {
        	System.out.println( "SQL Exception:" ) ;
	        se.printStackTrace(System.out);

	         // Loop through the SQL Exceptions
	         while( se != null ) {
	             System.out.println( "State  : " + se.getSQLState()  ) ;
	             System.out.println( "Message: " + se.getMessage()   ) ;
	             System.out.println( "Error  : " + se.getErrorCode() ) ;

	             se = se.getNextException() ;
	         }

	    } catch( Exception e ) {
	    	System.out.println( e ) ;
        }
    }
	
	private static String readClob(Clob clob) throws SQLException, IOException {
		StringBuffer result = new StringBuffer();
		String line;

		BufferedReader reader = new BufferedReader(clob.getCharacterStream());
		try {
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
		} finally {
			reader.close();
		}

		return result.toString();
	}	
}
