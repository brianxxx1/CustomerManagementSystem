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

@WebServlet("/blurFindServlet")
public class blurFindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        name = "%"+name+"%";
        String address = request.getParameter("address");
        address = "%"+address+"%";
        String email = request.getParameter("email");
        email = "%"+email+"%";
        String currentPage = request.getParameter("current");
        UserService userService = new UserServiceImpl();
        pageBean<user> pageBean = userService.blurFind(Integer.parseInt(currentPage),5, name, address, email);
        request.setAttribute("pageBean",pageBean);
        HttpSession session = request.getSession();
        session.setAttribute("name",name);
        session.setAttribute("address",address);
        session.setAttribute("email",email);
        request.getRequestDispatcher("/blur.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
