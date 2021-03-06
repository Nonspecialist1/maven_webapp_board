package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardEntity;
import com.koreait.basic.dao.BoardDAO;
import com.sun.org.apache.bcel.internal.generic.SWITCH;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/del")
public class BoardDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int loginPk = Utils.getLoginUserPk(req);
        if(loginPk == 0){
            res.sendRedirect("/user/login");
            return;
        }
        BoardEntity entity = new BoardEntity();
        entity.setWriter(loginPk);
        entity.setIbaord(Utils.getParameterInt(req, "iboard"));
        int result = BoardDAO.delBoard(entity);
        switch (result){
            case 1:
                res.sendRedirect("/board/list");
                return;
            default:
                req.setAttribute("err", "글 삭제를 실패하였습니다.");
                req.getRequestDispatcher("/board/detail?iboard=" + entity.getIbaord()).forward(req, res);
                return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
    
    