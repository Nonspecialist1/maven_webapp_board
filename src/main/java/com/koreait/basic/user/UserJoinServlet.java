package com.koreait.basic.user;

import com.koreait.basic.Utils;
import com.koreait.basic.dao.UserDAO;
import com.koreait.basic.user.model.UserEntity;
import org.apache.catalina.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/join")
public class UserJoinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Utils.displayView("회원가입", "user/join", req, res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");
        String nm = req.getParameter("nm");
        int gender = Utils.getParameterInt(req, "gender");
        String hashPw = BCrypt.hashpw(upw, BCrypt.gensalt());

        UserEntity entity = new UserEntity();
        entity.setUid(uid);
        // 단방향 암호화 - 예전에는 비밀번호를 그대로 저장해서 찾기누르면 그냥 알려주고 그랬음..가장 기본 - 문자열을 비트바꾼 다음 원래 키값과 혼합하는 방법
        // 같은 비밀번호도 다른 암호화값이 나온다(하나 뚫려도 다른 건 못뚫는 것), 세상에 완벽한 암호화는 없다 뚫는데 얼마나 오래 걸리느냐의 문제
        entity.setUpw(hashPw);
        entity.setNm(nm);
        entity.setGender(gender);
        // @ToString 이 자동으로 각 맴버필드의 값을 넣어서 주소값을 보여줌
        int result = UserDAO.join(entity);
        switch (result){
            case 1:
                res.sendRedirect("/user/login");
                break;
            default:
                req.setAttribute("err", "회원가입에 실패하였습니다.");
                doGet(req, res);
                break;
        }
    }
}
    
    