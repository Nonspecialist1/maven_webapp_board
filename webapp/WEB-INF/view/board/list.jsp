<%@ page import="com.koreait.board4.model.UserVO" %>
<%@ page import="com.koreait.board4.model.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // jsp 도 Servlet 이라고 보면됨, 이미 session 객체 주소가 메모리에 할당되어 있어서 사용가능
    UserVO loginUser = (UserVO) session.getAttribute("loginUser");
    List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
    int maxPage = (int) request.getAttribute("maxPage");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 목록</title>
    <style>
        body {
            background-color: black;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        h1, hr, table, div, a {
            color: white;
        }
        hr {
            width: 600px;
            margin-bottom: 20px;
        }
        .login {
            padding-bottom: 20px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .flex {
            display: flex;
        }
        table, tr, th, td{
            margin-top: 10px;
            padding: 5px;
            border-collapse: collapse;
            border: solid 1px white;
        }
        a:hover {
            cursor: pointer;
            color: lightpink;
        }
        table>tbody> tr>th:nth-child(2){width: 400px;}
    </style>
</head>
<body>
    <h1>리스트</h1>
    <hr>
    <% if(loginUser != null){ %>
        <div class="login">
            <a href="/board/write"><input class="btn" type="button" value="글쓰기"></a><br>
            <div class="flex">
                <%=loginUser.getNm()%> (<%=loginUser.getUid()%>)님 환영합니다.
                <a href="/user/logout" style="margin-left: 20px">로그아웃</a>
            </div>
        </div>
    <% } else { %>
        <a href="/user/login" class="login">로그인</a>
    <% } %>
    <table>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성일자</th>
            <th>작성자</th>
        </tr>
        <% for(BoardVO vo : list){%>
        <tr>
            <td><%=vo.getIboard()%></td>
            <td><a href="/board/detail?pk=<%=vo.getIboard()%>"><%=vo.getTitle()%></a></td>
            <td><%=vo.getRdt()%></td>
            <td><%=vo.getWriterNm()%></td>
        </tr>
        <% } %>
    </table>
    <div style="margin-top: 40px">
        <% for(int i=1; i<=maxPage; i++){ %>
            <span><a href="/board/list?page=<%=i%>">&nbsp;&nbsp;<%=i%>&nbsp;&nbsp;</a></span>
        <% } %>
    </div>
</body>
</html>