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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet("/board/del")
public class BoardDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(MyUtils.isLogout(req)){
            // res.sendRedirect("/board/detail?err=1") -> sendRedirect 는 req 객체가 재생성되서 그대로 사용할 수 없음
            req.setAttribute("err", "로그인 해 주세요");
            req.getRequestDispatcher("/board/detail").forward(req, res); // RequestDispatcher는 req 객체를 살려서 그대로 보냄
            return;
        }
        BoardVO param = new BoardVO();
        param.setIboard(Integer.parseInt(req.getParameter("pk")));
        param.setWriter(MyUtils.getLoginUserPk(req));

        int result = BoardDAO.delBoard(param);
        if(result == 0){
            req.setAttribute("err", "로그인 사용자가 작성한 글이 아닙니다.");
            req.getRequestDispatcher("/board/detail").forward(req, res); // req 객체가 null 값이어도 아무것도 표시안함 (매우편함)
            return;
        }
        res.sendRedirect("/board/list");
    }

}
