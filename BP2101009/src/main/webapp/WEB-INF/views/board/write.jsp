<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/header.jsp" %>

<div class="container">

    <h2>글쓰기</h2>
    <hr>

    <form action="write.do" method="post">
        제목: <input type="text" name="title"><br><br>

        내용:<br>
        <textarea name="content" rows="10" cols="50"></textarea><br><br>

        <button type="submit">등록</button>
    </form>

    <br>
    <a href="list.do">목록으로</a>
</div>

<%@ include file="/footer.jsp" %>
