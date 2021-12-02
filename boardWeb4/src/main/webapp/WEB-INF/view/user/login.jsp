<%@page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .hr {
            width: 500px;
            border: solid 1px #494848;
        }
        .err{
            color: red;
        }
        div{
            margin: 5px;
        }
        form {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .join {
            margin-top: 30px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            font-size: medium;
            color: blue;
        }
    </style>
</head>
<body>
    <h1>로그인</h1>
    <hr class="hr">
    <div class="err">${err}</div>
    <div class="form">
       <form action="/user/login" method="post">
           <div><input type="text" name="uid" placeholder="user id" value="aa"></div>
           <div><input type="password" name="upw" placeholder="user password" value="bb"></div>
           <div>
               <input type="submit" value="login">
           </div>
       </form>
       <div class="join">
           <a href="/user/join">회원가입</a>
       </div>
    </div>
</body>
</html>