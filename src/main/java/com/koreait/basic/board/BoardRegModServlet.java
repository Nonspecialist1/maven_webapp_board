package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardEntity;
import com.koreait.basic.dao.BoardDAO;
import com.koreait.basic.user.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/regmod")
public class BoardRegModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = Utils.getParameterInt(req, "iboard");
        String title = "글 등록";

        if (iboard > 0) {  //수정
            title = "글 수정";
            if(req.getAttribute("data") == null){
                BoardDTO param = new BoardDTO();
                param.setIboard(iboard);
                req.setAttribute("data", BoardDAO.selBoardDetail(param));
            }
        }
        Utils.displayView(title, "board/regmod", req, res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int loginUserPk = Utils.getLoginUserPk(req);
        if(loginUserPk == 0){
            res.sendRedirect("/user/login");
            return;
        }
        int iboard = Utils.getParameterInt(req, "iboard");
        String title = req.getParameter("title");
        title = title.replace("<", "&lt;").replace(">", "&gt;");
        String ctnt = req.getParameter("ctnt");
        ctnt = ctnt.replace("<", "&lt;").replace(">", "&gt;");
        // 더블 체크하면 더 좋은 것, Servlet 에서 1차 JSP 에서 2차 검사

        int result = 0;
        BoardEntity entity = new BoardEntity();
        entity.setTitle(title);
        entity.setCtnt(ctnt);
        entity.setWriter(loginUserPk);

        if(iboard == 0) { // 등록
            result = BoardDAO.insBoardWithPk(entity);
        } else { // 수정
            entity.setIbaord(iboard);
            result = BoardDAO.updBoard(entity);
        }
        switch (result){
            case 1:
                if(entity.getIbaord() != 0){
                    res.sendRedirect("/board/detail?iboard=" + entity.getIbaord());
                    return;
                }
                break;
            default:
                req.setAttribute("err", "등록/수정에 실패하였습니다.");
                req.setAttribute("data", entity);
                doGet(req, res);
                break;
        }
        res.sendRedirect("/board/list");
    }
}
    
    