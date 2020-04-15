package com.KCR;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class Connector {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/kcr?useUnicode=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static Connection con;

    private static Connector instance;

    static {
        try {
            instance = new Connector();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connector() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, "root", "sesame");

        } catch(ClassNotFoundException e){
        e.printStackTrace();
        con.close();
        }
    }

    public static Connector getInstance(){
        return instance;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        try {
            Statement s = null;
            s = con.createStatement();
            ResultSet rs = s.executeQuery(query);

            if (rs != null){
                return rs;
            }

            s.close();
            con.close();
            return null; // rs was null

        } catch (SQLException e) {
            e.printStackTrace();
            con.close();
            return null;
        }
    }

    public int executeUpdate(String query) throws SQLException {
        int result = -1;
        try {
            Statement s = null;
            s = con.createStatement();
            result = s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            if (result != 0){
                return result;
            }

            s.close();
            con.close();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            con.close();
            return result;
        }
    }

    public int insertIfNotExistsInt(String checkQuery, String insertQuery) throws SQLException{
        Connector con = Connector.getInstance();
        ResultSet rs = con.executeQuery(checkQuery);
        if(!rs.next()) {
            return con.executeUpdate(insertQuery);
        } else {
            return rs.getInt("id");
        }
    }

    public String insertIfNotExistsString(String checkQuery, String insertQuery, String columnName) throws SQLException{
        Connector con = Connector.getInstance();
        ResultSet rs = con.executeQuery(checkQuery);
        if(!rs.next()) {
            con.executeUpdate(insertQuery);
            return null;
        } else {
            return rs.getString(columnName);
        }
    }

}
