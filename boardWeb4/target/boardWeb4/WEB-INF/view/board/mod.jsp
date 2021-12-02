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
            <form id="form" action="/board/mod?pk=${requestScope.data.iboard}" method="post">
                <% // <input type="text" name="pk" value="${requestScope.data.iboard}" hidden OR readonly 로 pk보내도 됨 %>
                <div><input type="text" name="title" value="${requestScope.data.title}"></div>
                <div>
                    <textarea name="ctnt">${requestScope.data.ctnt}</textarea>
                </div>
                <div>
                    <input type="submit" value="등록">
                    <input type="reset" value="초기화">
                    <input type="button" value="모두삭제" onclick="remove();">
                </div>
            </form>
        </div>
    <script>
        function remove(){
            const form = document.getElementById("form");
                form.title.value = "";
                form.ctnt.value = "";
        }
    </script>
</body>
</html>