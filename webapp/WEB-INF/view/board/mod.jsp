<%@page contentType="text/html;charset=UTF-8" language="java" %>
<% String err = (String) request.getAttribute("err"); %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>수정하기</title>
    <style>
        .err{
            color: red;
            font-size: large;
        }
    </style>
</head>
<body>
    <h1>글 수정</h1>
    <%if(err != null){%>
        <div class="err"><%=err%></div>
    <% } else  %>
        <div>
            <form action="/board/mod?pk=${requestScope.data.iboard}" method="post">
                <%// <input type="text" name="pk" value="data.iboard" hidden OR readonly 으로도 pk보내도 됨%>
                <div><input type="text" name="title" value="${requestScope.data.title}"></div>
                <div>
                    <textarea name="ctnt">${requestScope.data.ctnt}</textarea>
                </div>
                <div>
                    <input type="submit" value="등록">
                    <input type="reset" value="초기화">
                </div>
            </form>
        </div>
</body>
</html>