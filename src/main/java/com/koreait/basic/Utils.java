package com.koreait.basic;

import com.koreait.basic.user.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Utils {
    public static void displayView(String title, String page, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        req.setAttribute("title", title);
        req.setAttribute("page", page);
        disForward(req, res, "layout");
    }
    public static void disForward(HttpServletRequest req, HttpServletResponse res, String jsp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/" + jsp + ".jsp").forward(req, res);
    }
    public static int getParameterInt(HttpServletRequest req, String key){
        return getParameterInt(req, key, 0);
    }
    public static int getParameterInt(HttpServletRequest req, String key, int defVal){
        String val = req.getParameter(key);
        return parseStringToInt(val, defVal);
    }

    public static int parseStringToInt(String val, int defVal){
        if(val == null || "".equals(val)){ return defVal; }
        else { return Integer.parseInt(val); }
    }

    public static UserEntity getLoginUser(HttpServletRequest req){
        HttpSession hs = req.getSession();
        return (UserEntity) hs.getAttribute("loginUser");
    }
    public static int getLoginUserPk(HttpServletRequest req){
        if(getLoginUser(req) == null){ return 0; }
        else { return getLoginUser(req).getIuser(); }
    }


}
