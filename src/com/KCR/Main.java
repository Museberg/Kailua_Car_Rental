package com.KCR;
import java.sql.*;
public class Main {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/kcr?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static Connection con;
    public static void main(String[] args)throws SQLException {
        try{
            Statement s = null;
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, "root","sesame");
            s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT model FROM cars");

            if(rs != null)
            while(rs.next()){
                System.out.println("Data for name: " + rs.getString("model"));
            }
            s.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            con.close();
        }

    }
}
