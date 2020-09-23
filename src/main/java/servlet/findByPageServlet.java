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
import java.io.IOException;

@WebServlet("/findByPageServlet")
public class findByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        UserService userService = new UserServiceImpl();
        pageBean<user> byPage = userService.findByPage(pageNumber, 5);
        request.setAttribute("pageBean",byPage);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
