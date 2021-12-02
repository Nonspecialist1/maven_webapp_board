package com.koreait.board4.board;

import com.koreait.board4.MyUtils;
import com.koreait.board4.model.BoardVO;
import com.koreait.board4.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/write")
public class BoardWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 로그인이 되어 있지 않으면 "/board/list"로 주소 이동
        if(MyUtils.isLogout(req)){
            res.sendRedirect("/user/login");
            return;
        }
        // 로그인이 되어 있으면 아래 소스 실행이 되도록
        MyUtils.disForward(req, res, "board/write");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");

        BoardVO param = new BoardVO();
        param.setTitle(title);
        param.setCtnt(ctnt);
        param.setWriter(MyUtils.getLoginUserPk(req));

        int result = BoardDAO.insBoard(param);
        if(result == 1){
            res.sendRedirect("/board/list");
        } else {
            String err = "글 등록에 실패하였습니다.";
            req.setAttribute("err", err);
            req.setAttribute("data", param);
            doGet(req, res);
        }
    }

}
    
    