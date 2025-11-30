<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<h2>로그인</h2>
<hr>

<form action="login.doo" method="post">

    아이디: <input type="text" name="id"><br><br>

    비밀번호: <input type="password" name="pw"><br><br>

    <button type="submit">로그인</button>
</form>

<br>

<a href="joinForm.doo">회원가입</a>

<br><br>

<p style="color:red;">
    ${msg}
</p>

<%@ include file="/footer.jsp" %>
