package com.koreait.board4.board;

import com.koreait.board4.MyUtils;
import com.koreait.board4.model.BoardVO;

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
        int iboard = MyUtils.getParameterInt(req, "pk");

        BoardVO param = new BoardVO();
        param.setIboard(iboard);

        BoardVO vo = BoardDAO.selDetail(param);
        req.setAttribute("data", vo);
        /*
            <%%> 스크립트릿은 잘 안쓴다, setAttribute 하지 않아도 jsp 안에서 변수 선언하고 사용가능함.
            ${} EL(표신식) 쓸 수 있는 것은 내장객체 4개에서 setAttribute 한 것만 사용가능하다. 구버전은 사용불가능
            우선순위는 위에서 부터 아래로 검색 (pageContext -> application 순서)
         */
        MyUtils.disForward(req, res, "board/detail");
    }

}
    
    