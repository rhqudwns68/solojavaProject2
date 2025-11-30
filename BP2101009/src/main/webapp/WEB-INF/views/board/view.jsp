<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<%
    dto.BoardDTO dto = (dto.BoardDTO) request.getAttribute("dto");
%>

<div class="container">
    <h2>게시글 상세보기</h2>
    <hr>

    <p><strong>번호:</strong> <%=dto.getNo()%></p>
    <p><strong>제목:</strong> <%=dto.getTitle()%></p>

    <p><strong>내용:</strong><br>
        <%=dto.getContent()%>
    </p>

    <p><strong>작성자:</strong> <%=dto.getWriter()%></p>
    <p><strong>작성일:</strong> <%=dto.getRegdate()%></p>

    <hr>
    <a href="list.do">목록으로</a>
</div>

<%@ include file="/footer.jsp" %>
