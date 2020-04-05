package Model.JDBC;
/*
此工具类是用来：
1.关闭connection，statement
2.getconnection（）

 */

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String driver;
    private static String user;
    private static String password;
    private static String url;

    static {
        Properties prop = new Properties();
        try {

             prop.load(JDBCUtils.class.getResourceAsStream("db.property"));
            driver = prop.getProperty("driver");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            url = prop.getProperty("url");
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    public static Statement getStatement() throws SQLException {
        Connection connection = getConnection();
        return  connection.createStatement();
    }

    private static Connection conn;
    public static Connection getSingleConnection()  throws SQLException {
        if(conn == null) {
            synchronized (JDBCUtils.class) {
                if(conn == null) {
                    conn = DriverManager.getConnection(url, user, password);
                }
            }
        }
        return conn;
    }

    public static void close(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement stat) {
        if(stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement stat,Connection connection,ResultSet resultSet){
        if (stat!=null&&connection!=null&&resultSet!=null){
            try {
                stat.close();
                connection.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement stat,Connection connection){
        if (stat!=null&&connection!=null){
            try {
                stat.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //万能的查询方法
    public static void select(String sql,Object...args) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        //DatabaseMetaData md = connection.getMetaData();
        for (int i=0;i<args.length;i++){
            ps.setObject(i+1,args[i]);
        }
        ResultSet rs = ps.executeQuery();//结果集
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i=0;i<rsmd.getColumnCount();i++){
            System.out.print(rsmd.getColumnName(i+1)+"\t");
        }
        System.out.println();
        while (rs.next()){
            for (int i=0;i<rsmd.getColumnCount();i++)
            System.out.print(rs.getObject(i+1)+" \t");
            System.out.println();

        }

    }
}
