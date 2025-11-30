<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%
    dto.MemberDTO user = (dto.MemberDTO) session.getAttribute("loginUser");
    String ctx = request.getContextPath();
%>

<style>
    .container {
        max-width: 900px;
        margin: 0 auto;
        padding: 10px;
    }
</style>

<div class="container">

    <div style="display:flex; gap:15px; padding:10px 0; align-items:center;">

        <% if (user == null) { %>

            <a href="<%=ctx%>/loginForm.doo" style="display:flex; align-items:center; gap:5px;">
                <img src="<%=ctx%>/images/loginimage.png" style="width:20px;">
                로그인
            </a>

            <a href="<%=ctx%>/joinForm.doo">회원가입</a>

        <% } else { %>

            <img src="<%=ctx%>/images/loginimage.png" style="width:20px;">
            <span><%=user.getName()%>님</span>

            <a href="<%=ctx%>/mypage.doo">마이페이지</a>
            <a href="<%=ctx%>/logout.doo">로그아웃</a>

        <% } %>

    </div>

    <header style="padding:15px;">

        <div style="display:flex; align-items:center; gap:15px; margin-bottom:10px;">
            <img src="<%=ctx%>/images/logoImage.png" style="width:120px;">
            <h2 style="margin:0;">Mini Shop</h2>
        </div>

        <nav style="display:flex; gap:20px; margin-bottom:10px;">
            <a href="<%=ctx%>/list.do">
                <img src="<%=ctx%>/images/boardimage.png" style="width:22px;">
                게시판
            </a>
        </nav>

        <hr>

    </header>
</div>
