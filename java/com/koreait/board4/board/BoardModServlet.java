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

@WebServlet("/board/mod")
public class BoardModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(MyUtils.isLogout(req)){
            res.sendRedirect("/user/login");
            return;
        }
        BoardVO param = new BoardVO();
        param.setIboard(MyUtils.getParameterInt(req, "pk"));
        BoardVO vo = BoardDAO.selDetail(param);

        req.setAttribute("data", vo);
        MyUtils.disForward(req, res, "board/mod");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(MyUtils.isLogout(req)){ // 왜 필요한가?
            String err = "로그인 해주세요.";
            req.setAttribute("err", err);
            doGet(req, res);
            return;
        }
        int iboard = Integer.parseInt(req.getParameter("pk"));
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");

        BoardVO param = new BoardVO();
        param.setIboard(iboard);
        param.setTitle(title);
        param.setCtnt(ctnt);
        // login 되있으면 pk값 넣고 아니면 0 넣기 때문에, 아닐시 업데이트가 되지 않음
        param.setWriter(MyUtils.getLoginUserPk(req));

        int result = BoardDAO.modBoard(param);
        switch (result){
            case 1:
                res.sendRedirect("/board/detail?pk="+iboard);
                break;
            default:
                req.setAttribute("err", "수정에 실패하였습니다.");
                req.setAttribute("data", param);
                MyUtils.disForward(req, res, "board/mod");
                break;
        }
    }

}
    
    