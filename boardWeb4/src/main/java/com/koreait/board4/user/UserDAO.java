package com.koreait.board4.user;

import com.koreait.board4.DbUtils;
import com.koreait.board4.model.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public static int join(UserVO param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_user (uid, upw, nm, gender) VALUES (?, ?, ?, ?)";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getUid());
            ps.setString(2, param.getUpw());
            ps.setString(3, param.getNm());
            ps.setInt(4, param.getGender());
            return ps.executeUpdate();
        }catch (Exception e){ e.printStackTrace(); }
        finally { DbUtils.close(con, ps); }
        return 0;
    }
    // int 값 리턴을 바꾸긴 싫고...SI 업체에서 많이하는 야매 방식, 다음 시간에 정석 알려줌
    public static int login(UserVO param){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM t_user WHERE uid = ?";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getUid());
            rs = ps.executeQuery();
            // 밑에 이거 야매다..회사에서 이런거하면..
            if(rs.next()){
                param.setIuser(rs.getInt("iuser"));
                param.setNm(rs.getString("nm"));
                String dbUpw = rs.getString("upw");
                return dbUpw.equals(param.getUpw()) ? 1 : 3;
            } else {
                return 2; // id 없음
            }
        }catch (Exception e){ e.printStackTrace(); }
        finally { DbUtils.close(con, ps); }
        return 0; // 에러 발생
    }

}
