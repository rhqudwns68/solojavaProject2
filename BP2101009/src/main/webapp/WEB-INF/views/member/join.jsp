<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<h2>회원가입</h2>
<hr>

<form action="join.doo" method="post">

    아이디: <input type="text" name="id"><br><br>

    비밀번호: <input type="password" name="pw"><br><br>

    이름: <input type="text" name="name"><br><br>

    이메일: <input type="text" name="email"><br><br>

    전화번호: <input type="text" name="phone"><br><br>

    <button type="submit">가입</button>
</form>

<br>
<a href="loginForm.doo">로그인 페이지로</a>

<%@ include file="/footer.jsp" %>
