package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.MemberDAO;
import dto.MemberDTO;

@WebServlet("*.doo")
public class MemberController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    MemberDAO dao = new MemberDAO();

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

        if (cmd.equals("/loginForm.doo")) {
            loginForm(request, response);

        } else if (cmd.equals("/joinForm.doo")) {
            joinForm(request, response);

        } else if (cmd.equals("/login.doo")) {
            login(request, response);

        } else if (cmd.equals("/join.doo")) {
            join(request, response);

        } else if (cmd.equals("/logout.doo")) {
            logout(request, response);

        } else if (cmd.equals("/mypage.doo")) {
            mypage(request, response);
        }
    }


    private void loginForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
               .forward(request, response);
    }


    private void joinForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/member/join.jsp")
               .forward(request, response);
    }


    private void login(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        MemberDTO dto = dao.login(id, pw);

        if (dto == null) {
            request.setAttribute("msg", "아이디 또는 비밀번호가 틀렸습니다.");
            request.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
                   .forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginUser", dto);

        response.sendRedirect("index.jsp");
    }


    private void join(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        MemberDTO dto = new MemberDTO();
        dto.setId(request.getParameter("id"));
        dto.setPw(request.getParameter("pw"));
        dto.setName(request.getParameter("name"));
        dto.setEmail(request.getParameter("email"));
        dto.setPhone(request.getParameter("phone"));

        dao.join(dto);

        response.sendRedirect("loginForm.doo");
    }


    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect("index.jsp");
    }


    private void mypage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        MemberDTO user = (MemberDTO) session.getAttribute("loginUser");

        if (user == null) {
            response.sendRedirect("loginForm.doo");
            return;
        }

        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp")
               .forward(request, response);
    }
}
