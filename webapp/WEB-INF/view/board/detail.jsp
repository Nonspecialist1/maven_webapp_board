<%@ page import="com.koreait.board4.model.BoardVO" %>
<%@ page import="com.koreait.board4.model.UserVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BoardVO vo = (BoardVO) request.getAttribute("data");
    UserVO loginUser = (UserVO) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${requestScope.data.title}</title>
    <style>
        body {
            background-color: lightgrey;
        }
        a:hover {
            color: blue;
        }
        .err{
            font-size: large;
            color: red;
        }
        div{
            margin: 5px
        }
    </style>
</head>
<body>
    <h1>디테일</h1>
    <% if(loginUser != null && loginUser.getIuser() == vo.getWriter()){ %>
        <div>
            <a href="/board/mod?pk=${requestScope.data.iboard}"><input type="button" value="수정"></a>
            <a href="/board/del?pk=${requestScope.data.iboard}"><input type="button" value="삭제"></a>
        </div>
    <% } %>
    <div style="margin-bottom: 10px">
        <a href="/board/list">리스트</a>
    </div>
    <div class="err">${requestScope.err}</div>
    <div>번호 : ${requestScope.data.iboard}</div>
    <div>제목 : ${requestScope.data.title}</div>
    <div>작성자 : ${requestScope.data.writerNm}</div>
    <% if(vo.getRdt().equals(vo.getMdt())){ %>
        <div>작성일시 : ${requestScope.data.rdt}</div>
    <% }else{ %>
        <div>작성일시 : ${requestScope.data.mdt}(수정)</div>
    <% } %>
    <div>내용 : ${requestScope.data.ctnt}</div>
</body>
</html>