<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
<%@ page import="java.util.*, dto.BoardDTO" %>

<%
    List<BoardDTO> list = (List<BoardDTO>) request.getAttribute("list");
    Integer totalCountObj = (Integer) request.getAttribute("totalCount");
    int totalCount = (totalCountObj == null) ? 0 : totalCountObj;

%>

<h2>게시판 목록</h2>
<p>총 글 수: <%=totalCount%></p>

<a href="<%=request.getContextPath()%>/index.jsp">홈으로</a>
&nbsp;&nbsp;
<a href="writeForm.do">글쓰기</a>
<hr>


<table border="1" width="700">
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
    </tr>

<%
    if (list != null && !list.isEmpty()) {
        for (BoardDTO dto : list) {
%>
    <tr>
        <td><%=dto.getNo()%></td>
        <td>
            <a href="<%=ctx%>/view.do?no=<%=dto.getNo()%>">
                <%=dto.getTitle()%>
            </a>
        </td>
        <td><%=dto.getWriter()%></td>
        <td><%=dto.getRegdate()%></td>
    </tr>
<%
        }
    } else {
%>
    <tr>
        <td colspan="4">등록된 글이 없습니다.</td>
    </tr>
<%
    }
%>
</table>

<hr>

<form action="<%=ctx%>/list.do" method="get">
    <select name="type">
        <option value="title">제목</option>
        <option value="content">내용</option>
        <option value="writer">작성자</option>
    </select>
    <input type="text" name="keyword">
    <button type="submit">검색</button>
</form>

<hr>

<a href="<%=ctx%>/list.do">목록 새로고침</a>

<%@ include file="/footer.jsp" %>
