<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<%
    if (user == null) {
        response.sendRedirect("loginForm.doo");
        return;
    }
%>

<h2>마이페이지</h2>
<hr>

<p>아이디: <%=user.getId()%></p>
<p>이름: <%=user.getName()%></p>
<p>이메일: <%=user.getEmail()%></p>
<p>전화번호: <%=user.getPhone()%></p>

<br>

<a href="logout.doo">로그아웃</a>
<br><br>

<a href="<%=request.getContextPath()%>/index.jsp">홈으로</a>

<%@ include file="/footer.jsp" %>
