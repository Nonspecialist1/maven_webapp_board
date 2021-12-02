package com.koreait.board4.user;

import com.koreait.board4.MyUtils;
import com.koreait.board4.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    // 로그인 화면 띄우는 담당
        MyUtils.disForward(req, res, "user/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    // 아이디 비밀번호 입력 후 로그인 처리 담당
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");

        UserVO param = new UserVO();
        param.setUid(uid);
        param.setUpw(upw);
    //0: 로그인실패, 1:로그인성공, 2:아이디없음, 3:비번틀림
        int result = UserDAO.login(param);
        if(result == 1){
        // 세션 로그인 처리
            param.setUpw(null); // 비밀번호 꼭 지울필요는 없지만 통상적으로 지워줌.
            //현재 param 에 iuser, nm, uid 값이 담겨져 있다.
            HttpSession session = req.getSession(); // 개인용 session 객체 주소 얻어옴
            session.setAttribute("loginUser", param); // param 은 세션이 죽기 전까지 계속 살게 됨

            res.sendRedirect("/board/list");
            return; // 여기서 return 안하면 아래 다 실행해서 에러터짐
        }
        String err = null;
        switch (result){
            case 0: err = "로그인에 실패하였습니다.";
                break;
            case 2: err = "아이디를 확인해주세요.";
                break;
            case 3: err = "비밀번호를 확인해주세요.";
                break;
        }
        req.setAttribute("err", err);
        doGet(req, res); // 로그인 페이지 이동
    }

}
    
    