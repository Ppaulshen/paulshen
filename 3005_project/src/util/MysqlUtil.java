package util;

import java.sql.*;

/**
 * connect mysql database
 */
public class MysqlUtil {
    private static final String URL="jdbc:mysql://127.0.0.1:3306/bookmanager?useUnicode=true&characterEncoding=utf8&useSSL=false";
    private static final String USER="root";
    private static final String PASSWORD="123456";
    private static Connection conn = null;
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return conn;
    }

}
