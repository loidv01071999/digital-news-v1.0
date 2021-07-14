/*
 *Copyright(C) 2021,  FPTU.
 * J3.L.P0004 :
 *  Digital News
 *
 * Record of change:
 * DATE                       Version             AUTHOR            DESCRIPTION
 * 2021-05-10                  1.0                PhatNT         Start implement
 * 2021-05-12                  1.0                PhatNT         Test, Comment
 * 2021-05-24                  1.0                PhatNT         Review
 * 2021-05-31                  1.0                PhatNT         Fix bug, Fix comment
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * The class contains method get connection , close connection with database and
 * get image path. 
 * Bugs: None
 *
 * @author PhatNT
 */
public class DBContext {

    private final InitialContext initial;
    private final Context context;
    private final String dbName; 
    private final String dbUserName;
    private final String dbPassword; 
    private final String localhost;
    private final String portNumber;
    private final String image;

    /**
     * Get data from file 'context.xml'
     *
     * @throws javax.naming.NamingException
     */
    public DBContext() throws NamingException {
        initial = new InitialContext();
        Object obj = initial.lookup("java:comp/env");
        context = (Context) obj;
        dbName = context.lookup("dbName").toString();
        dbUserName = context.lookup("user").toString();
        dbPassword = context.lookup("pass").toString();
        localhost = context.lookup("serverName").toString();
        portNumber = context.lookup("portNumber").toString();
        image = context.lookup("image").toString();
    }

    /**
     * User DriverManager to get connection with static variations
     *
     * @return A connection (session) with a specific database. Type is Connection
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://"
                + localhost + ":"
                + portNumber + ";databaseName="
                + dbName, dbUserName, dbPassword);
        return conn;
    }

    /**
     * Close result set. Closing a pointer to a row of a table
     *
     * @param rs A table of data representing a database result set
     * @throws java.sql.SQLException
     */
    public void closeResultSet(ResultSet rs) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
    }
    /**
     * Close prepared statement. Closing query parameter implementation.
     *
     * @param ps An object that represents a precompiled SQL statement.
     * @throws java.sql.SQLException
     */
    public void closePreparedStatement(PreparedStatement ps) throws SQLException {
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
    }
    /**
     * Close connection. Closing the session between java application and database
     *
     * @param connection A connection (session) with a specific database.
     * @throws java.sql.SQLException
     */
    public void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    /**
     * Get image path
     *
     * @return a image path <code>java.lang.String</code> object.
     * @throws java.lang.Exception
     */
    public String getImagePath() throws Exception {
        return image;
    }
}
