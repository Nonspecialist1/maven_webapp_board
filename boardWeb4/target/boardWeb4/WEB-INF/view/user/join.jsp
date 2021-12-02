<%@page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        hr {
            width: 500px;
            border: solid 1px #494848;
        }
        .err{
            color: red;
            font-size: large;
        }
        div{
            margin: 3px;
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
    <h1>회원가입</h1>
    <hr>
    <div class="err">${msg}</div>
    <div>
        <form action="/user/join" method="post">
            <div><input type="text" name="uid" placeholder="user id"></div>
            <div><input type="password" name="upw" placeholder="user password"></div>
            <div><input type="text" name="nm" placeholder="user name"></div>
            <div>
                gender : <label>woman<input type="radio" name="gender" value="0"></label>
                        <label>man<input type="radio" name="gender" value="1"></label>
            </div>
            <div>
                <input type="submit" value="join">
            </div>
        </form>
    </div>
</body>
</html>