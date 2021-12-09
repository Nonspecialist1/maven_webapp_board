package com.koreait.basic.board.cmt;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardCmtEntity;
import com.koreait.basic.dao.BoardCmtDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/cmt/reg")
public class BoardCmtRegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int ibaord = Utils.getParameterInt(req, "iboard");
        String ctnt = req.getParameter("ctnt");
        int writer = Utils.getLoginUserPk(req);

        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIbaord(ibaord);
        entity.setWriter(writer);
        entity.setCtnt(ctnt);
        int result = BoardCmtDAO.insBoardCmt(entity);
        if(result == 1){ res.sendRedirect("/board/detail?iboard="+ibaord); }
    }
}
    
    