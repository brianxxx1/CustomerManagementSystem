package servlet;

import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/userloginServlet")
public class userloginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        UserService userService = new UserServiceImpl();
        String checkcode = request.getParameter("checkcode");
        String username = request.getParameter("user");
        String password = request.getParameter("password");
        Boolean bool = userService.loginCheck(username, password);
        HttpSession session = request.getSession();
        String checkcode1 = (String)session.getAttribute("checkcode1");
        if (checkcode1.equalsIgnoreCase(checkcode)){
            if (bool){
                session.setAttribute("username",username);
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }
            else{
                session.setAttribute("msg","账号密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }
        else{
            request.setAttribute("msg","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
