package com.koreait.board4.board;

import com.koreait.board4.MyUtils;
import com.koreait.board4.model.BoardParamVO;
import com.koreait.board4.model.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
    /* 내장객체 : set/get Attribute 즉, 통신하기위한 객체들(각자 라이프사이클이 다르다, 아래로갈수록 오래삶)
    - pageContext : jsp 파일 쓸때 생성되고 응답할 때 죽음, jsp 파일 안에서만 사용(JSTL에 많이 사용), 애가 생성되면 밑에 애들은 다 존재한다는 것
    - request : 요청할 때 생성되고 응답할 때 죽음 (Servlet 에서 생성되고 JSP 로 값을 전달할 때, 클라이언트가 jsp 로 값을 보낼 때 등)
    - session : request와 함께 무조건 기억, 쉽게 생각해서 브라우저를 켜면 생성되고 브라우저 종료하면 소멸됨(브라우저마다 객체 생성, 아마 20분 후 자동 소멸)
    ㄴ 로그인 처리를 세션이 아닌 JWT 웹토큰으로 바꾸면 브라우저 종료해도 JWT는 살아있음(계속 로그인되어있음), 세션을 많이 사용하면 서버에 부담되므로 웹토큰 사용하는 추세
    - application : 딱 1개, 개인용인 나머지 셋과 다르게 범용적, 서버 실행 시 존재해서 종료 시 소멸, 사실 잘 안쓰임
    - EL 식은 내장객체에 set 해놓은 값만 사용가능
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int recordCnt = 5;
        int page = MyUtils.getParameterInt(req, "page", 1);

        BoardParamVO param = new BoardParamVO();
        param.setRecordCnt(recordCnt);
        param.setPage(page);

        req.setAttribute("list", BoardDAO.selBoardList(param));
        req.setAttribute("maxPage", BoardDAO.selMaxPage(param));

        MyUtils.disForward(req, res, "board/list");
    }
}
    
    