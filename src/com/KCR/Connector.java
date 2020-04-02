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

            // Usage
            /*while(rs.next()){
                System.out.println("Data for name: " + rs.getString("model"));
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
            con.close();
            return null;
        }
    }
}
