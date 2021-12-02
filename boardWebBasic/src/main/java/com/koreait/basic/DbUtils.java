package com.koreait.basic;

import java.sql.*;

public class DbUtils {
    public static Connection getCon() throws ClassNotFoundException, SQLException {
        final String URL = "jdbc:mysql://localhost:3308/boardbasic";
        final String USER = "root";
        final String PW = "koreait";
        final String Driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(Driver);
        Connection con = DriverManager.getConnection(URL, USER, PW);

        System.out.println("DB 연결 성공 !");
        return con;
    }
    public static void close(Connection con, PreparedStatement ps, ResultSet rs){
        if(rs != null){
            try{ rs.close(); }
            catch (Exception e){ e.printStackTrace(); }
        }
        if(ps != null){
            try{ ps.close(); }
            catch (Exception e){ e.printStackTrace(); }
        }
        if(con != null){
            try{ con.close(); }
            catch (Exception e){ e.printStackTrace(); }
        }
    }
    public static void close(Connection con, PreparedStatement ps) {
        close(con, ps, null);
    }


}
