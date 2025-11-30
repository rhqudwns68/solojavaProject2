package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.BoardDAO;
import dto.BoardDTO;
import dto.MemberDTO;

@WebServlet("*.do")
public class BoardController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    BoardDAO dao = new BoardDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String uri = request.getRequestURI();
        String cmd = uri.substring(uri.lastIndexOf("/"));

        if (cmd.equals("/list.do")) {
            list(request, response);
        } else if (cmd.equals("/writeForm.do")) {
            writeForm(request, response);
        } else if (cmd.equals("/write.do")) {
            write(request, response);
        } else if (cmd.equals("/view.do")) {
            view(request, response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int totalCount = dao.count(null, null);
        List<BoardDTO> list = dao.list(0, 10, null, null);

        request.setAttribute("list", list);
        request.setAttribute("totalCount", totalCount);

        request.getRequestDispatcher("/WEB-INF/views/board/list.jsp")
                .forward(request, response);
    }

    private void writeForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        MemberDTO user = (MemberDTO) session.getAttribute("loginUser");

        if (user == null) {
            response.sendRedirect("loginForm.doo");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/board/write.jsp")
                .forward(request, response);
    }

    private void write(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        MemberDTO user = (MemberDTO) session.getAttribute("loginUser");

        if (user == null) {
            response.sendRedirect("loginForm.doo");
            return;
        }

        BoardDTO dto = new BoardDTO();
        dto.setTitle(request.getParameter("title"));
        dto.setContent(request.getParameter("content"));
        dto.setWriter(user.getId());

        dao.write(dto);
        response.sendRedirect("list.do");
    }

    private void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int no = Integer.parseInt(request.getParameter("no"));
        BoardDTO dto = dao.view(no);

        request.setAttribute("dto", dto);

        request.getRequestDispatcher("/WEB-INF/views/board/view.jsp")
                .forward(request, response);
    }
}
