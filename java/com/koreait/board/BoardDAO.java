package com.koreait.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    public static int insBoard(BoardVO param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_board (title, ctnt, writer) VALUES (?, ?, ?)";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getCtnt());
            ps.setString(3, param.getWriter());
            return ps.executeUpdate();
        }catch (Exception e){e.printStackTrace();}
        finally { DbUtils.close(con, ps, null); }
        return 0;
    }
    public static List<BoardVO> selBoardList(){
        List<BoardVO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT iboard, title, writer, rdt FROM t_board ORDER BY iboard DESC";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                BoardVO vo = new BoardVO();
                vo.setIboard(rs.getInt("iboard"));
                vo.setTitle(rs.getString("title"));
                vo.setWriter(rs.getString("writer"));
                vo.setRdt(rs.getString("rdt"));
                list.add(vo);
            }
        }catch (Exception e){ e.printStackTrace(); }
        finally { DbUtils.close(con, ps, rs); }
        return list;
    }
    public static List<BoardVO> getSearch(String field, String searchWord){
        List<BoardVO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM t_board where "+field+" like '%"+searchWord+"%'";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setIboard(rs.getInt("iboard"));
                vo.setTitle(rs.getString("title"));
                vo.setCtnt(rs.getString("ctnt"));
                vo.setWriter(rs.getString("writer"));
                vo.setRdt(rs.getString("rdt"));
                list.add(vo);
            }
        }catch (Exception e){ e.printStackTrace(); }
        finally { DbUtils.close(con, ps, rs); }
        return list;
    }
    public static BoardVO selBoard(BoardVO param){
        BoardVO vo = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM t_board WHERE iboard = ?";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();
            if(rs.next()){
                vo = new BoardVO();
                vo.setIboard(rs.getInt("iboard"));
                vo.setTitle(rs.getString("title"));
                vo.setCtnt(rs.getString("ctnt"));
                vo.setWriter(rs.getString("writer"));
                vo.setRdt(rs.getString("rdt"));
            } else {
                System.out.println("데이터 없음");
                return null;
            }
        }catch (Exception e){e.printStackTrace();}
        finally { DbUtils.close(con, ps, rs); }
        return vo;
    }
    // (순서)이전글 가져오기
    public static int selPrevIboard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT iboard FROM t_board WHERE iboard > ? ORDER BY iboard LIMIT 1";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();
            if (rs.next()) {
                int iboard = rs.getInt("iboard");
                return iboard;
            }
        } catch(Exception e){ e.printStackTrace(); }
        finally{ DbUtils.close(con, ps, rs);}
        return 0;
    }
    // (순서)다음글 가져오기
    public static int selNextIboard(BoardVO param){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT iboard FROM t_board WHERE iboard < ? ORDER BY iboard DESC LIMIT 1";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();
            if(rs.next()){
                int iboard = rs.getInt("iboard");
                return iboard;
            }
        }catch (Exception e){e.printStackTrace();}
        finally { DbUtils.close(con, ps, rs); }
        return 0;
    }
    public static int upBoard(BoardVO param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE t_board SET title = ?, ctnt = ?, writer = ? WHERE iboard = ?";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getCtnt());
            ps.setString(3, param.getWriter());
            ps.setInt(4, param.getIboard());
            return ps.executeUpdate();
        }catch (Exception e){e.printStackTrace();}
        finally { DbUtils.close(con, ps, null); }
        return 0;
    }
    public static int delBoard(BoardVO param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM t_board WHERE iboard = ?";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            return ps.executeUpdate();
        }catch (Exception e){e.printStackTrace();}
        finally { DbUtils.close(con, ps, null); }
        return 0;
    }

}
