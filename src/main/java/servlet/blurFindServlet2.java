package servlet;

import domain.pageBean;
import domain.user;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/blurFindServlet2")
public class blurFindServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session1 = request.getSession();
        String name = (String)session1.getAttribute("name");
        name = "%"+name+"%";
        String address = (String)session1.getAttribute("address");
        address = "%"+address+"%";
        String email = (String)session1.getAttribute("email");
        email = "%"+email+"%";
        String currentPage = request.getParameter("current");
        UserService userService = new UserServiceImpl();
        pageBean<user> pageBean = userService.blurFind(Integer.parseInt(currentPage),5, name, address, email);
        request.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher("/blur.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
