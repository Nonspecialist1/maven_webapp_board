package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardCmtDTO;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardVO;
import com.koreait.basic.dao.BoardCmtDAO;
import com.koreait.basic.dao.BoardDAO;

import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BoardDTO param = new BoardDTO();
        int iboard = Utils.getParameterInt(req, "iboard");
        param.setIboard(iboard);

        BoardVO data = BoardDAO.selBoardDetail(param);

        BoardCmtDTO cmtParam = new BoardCmtDTO();
        cmtParam.setIboard(iboard);
        req.setAttribute("cmtList", BoardCmtDAO.selBoardCmtList(cmtParam));
        // 로그인 한 사람의 pk값과 data에 들어있는 writer 값이 다르거나 로그인이 안 되어 있으면 hit 값 올리기
        if(Utils.getLoginUser(req) == null || Utils.getLoginUserPk(req) != data.getWriter()){
            BoardDAO.updBoardHitUp(param);
            data = BoardDAO.selBoardDetail(param);
        }
        req.setAttribute("data", data);
        Utils.displayView("상세정보", "board/detail", req, res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
    
    