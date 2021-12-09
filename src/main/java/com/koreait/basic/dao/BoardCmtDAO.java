package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.board.model.BoardCmtDTO;
import com.koreait.basic.board.model.BoardCmtEntity;
import com.koreait.basic.board.model.BoardCmtVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardCmtDAO {
    public static int insBoardCmt(BoardCmtEntity entity){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_board_cmt (iboard, ctnt, writer) VALUES (?, ?, ?)";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, entity.getIbaord());
            ps.setString(2, entity.getCtnt());
            ps.setInt(3, entity.getWriter());
            return ps.executeUpdate();
        }catch (Exception e){e.printStackTrace();}
        finally { DbUtils.close(con, ps); }
        return 0;
    }
    public static List<BoardCmtVO> selBoardCmtList(BoardCmtDTO param){
        List<BoardCmtVO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.icmt, A.ctnt, A.writer, A.rdt, B.nm AS writerNm FROM t_board_cmt A INNER JOIN t_user B ON A.writer = B.iuser WHERE A.iboard = ?";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();
            while(rs.next()){
                BoardCmtVO vo = BoardCmtVO.builder()
                                .icmt(rs.getInt("icmt"))
                                .ctnt(rs.getString("ctnt"))
                                .writer(rs.getInt("writer"))
                                .writerNm(rs.getString("writerNm"))
                                .rdt(rs.getString("rdt"))
                                .build();
                list.add(vo);
            }
        }catch (Exception e){e.printStackTrace();}
        finally { DbUtils.close(con, ps); }
        return list;
    }
    public static int boardCmtDel(BoardCmtEntity entity){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM t_board_cmt WHERE iboard = ? AND icmt = ?";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, entity.getIbaord());
            ps.setInt(2, entity.getIcmt());
            return ps.executeUpdate();
        }catch (Exception e){e.printStackTrace();}
        finally { DbUtils.close(con, ps); }
        return 0;
    }




}
