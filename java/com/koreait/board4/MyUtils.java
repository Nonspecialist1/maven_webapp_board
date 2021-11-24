package com.koreait.board4;

import com.koreait.board4.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyUtils {
    public static void disForward(HttpServletRequest req, HttpServletResponse res, String path) throws ServletException, IOException {
        String jsp = "/WEB-INF/view/"+path+".jsp";
        req.getRequestDispatcher(jsp).forward(req, res);
    }
    // 로그인이 안 되어 있으면 null, 되있으면 UserVO 객체 주소값
    public static UserVO getLoginUser(HttpServletRequest req){
        HttpSession session = req.getSession();
        return (UserVO) session.getAttribute("loginUser");
    }
    public static boolean isLogin(HttpServletRequest req){return getLoginUser(req) != null;}
    public static boolean isLogout(HttpServletRequest req){
        return getLoginUser(req) == null;
    }
    // 로그인 했으면 return pk값, 로그아웃 상태면 return 0
    public static int getLoginUserPk(HttpServletRequest req){
        int iuser = getLoginUser(req).getIuser();
        return isLogin(req) ? iuser : 0;
    }

    public static int getParameterInt(HttpServletRequest req, String key, int defVal){
        return parseStringToInt(req.getParameter(key), defVal);
    }
    public static int getParameterInt(HttpServletRequest req, String param){
        String str = req.getParameter(param);
        return parseStringToInt(str);
    }
    public static int parseStringToInt(String str){
        // 예외발생 시 무조건 0을 리턴하는 함수
        return parseStringToInt(str, 0);
    }
    public static int parseStringToInt(String str, int defVal){
        // 예외발생 시 원하는 defVal 값을 리턴받는 함수
        // Wrapper 클래스 : 데이터타입이면서 매소드를 가지고 있는 변수; double, boolean, character 등등
        try{
            return Integer.parseInt(str);
        }catch (Exception e){}
        return defVal;
    }


}
