package com.koreait.board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class BoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<BoardVO> list = BoardDAO.selBoardList();
        req.setAttribute("data", list);
        //webapp 이 root 폴더임
        String field = req.getParameter("f"); // title, writerId
        String query = req.getParameter("q"); // 검색어
        boolean none = true;
        List<BoardVO> findList = BoardDAO.getSearch(field, query);
        req.setAttribute("findData", findList);

        if(query == null || query.equals("")){
            none = false;
            req.setAttribute("none", none);
        } else if(!query.equals("") && findList.size() == 0){
            req.setAttribute("none", none);
        } else {
            none = false;
            req.setAttribute("none", none);
        }
        String path = "/WEB-INF/jsp/list.jsp";
        RequestDispatcher rd = req.getRequestDispatcher(path);
        rd.forward(req, res);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 클라이언트가 쿼리스트링 또는 폼 태그를 통해서 전달했을 때 getParameter로 받음 set은 없음 (get만)
        // getAttribute 는 이전에 set 한 것만 가져올 수 있음 (get, set 세트)

    }
}
    
    