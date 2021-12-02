<%@ page import="com.koreait.board.BoardVO" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BoardVO vo = (BoardVO) request.getAttribute("data");
    int prevIdx = (int) request.getAttribute("prevIdx");
    int nextIdx = (int) request.getAttribute("nextIdx");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= vo.getTitle()%></title>
    <style>
        .cls{
            color: blue;
            width: 500px;
        }
    </style>
</head>
<body>
    <div style="margin-bottom: 20px">
        <a href="/del?iboard=<%= vo.getIboard()%>"><input type="button" value="삭제"></a>
        <a href="/mod?iboard=<%= vo.getIboard()%>"><input type="button" value="수정"></a>
        <a href="/list"><input type="button" value="글 목록"></a>
    </div>
    <div>제목 : <%= vo.getTitle()%></div>
    <div>작성자 : <%= vo.getWriter()%></div>
    <div>작성일시 : <%= vo.getRdt()%></div>
    <br>
    <div class="cls">내용 : <%= vo.getCtnt()%></div>
    <br>
    <div>
        <% if(nextIdx != 0) { %>
            <a href="/detail?iboard=<%=nextIdx%>"><input type="button" value="다음 목록"></a>
        <% } %>
        <% if(prevIdx != 0) { %>
        <a href="/detail?iboard=<%=prevIdx%>"><input type="button" value="이전 목록"></a>
        <% } %>
    </div>

</body>
</html>