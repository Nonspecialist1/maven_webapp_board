<%@ page import="com.koreait.board.BoardVO" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<% BoardVO vo = (BoardVO) request.getAttribute("data"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>수정</title>
    <style>
        textarea {
            width: 200px;
            height: 200px;
        }
    </style>
</head>

<body>
    <h1>수정</h1>

    <form action="/mod?iboard=<%=vo.getIboard()%>" method="post" id="frm">
        <div><input type="text" name="title" value="<%= vo.getTitle()%>"></div>
        <div>
            <textarea name="ctnt"><%= vo.getCtnt()%></textarea>
        </div>
        <div><input type="text" name="writer" value="<%= vo.getWriter()%>"></div>
        <div>
            <input type="submit" value="저장">
            <input type="reset" value="초기화">
            <input type="button" value="모두 삭제" onclick="removeAll();">
        </div>
    </form>
    <script>
        function removeAll(){
            var frm = document.querySelector("#frm");
            if(frm){
                frm.title.value = "";
                frm.ctnt.value = "";
                frm.writer.value = "";
            }
        }
    </script>

</body>
</html>