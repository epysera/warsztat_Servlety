package pl.coderslab.users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        UserDao userDao = new UserDao();
        User read = userDao.read(Integer.parseInt(id));
        request.setAttribute("user", read);

        getServletContext().getRequestDispatcher("/users/editUser.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();

        String name = request.getParameter("userName");
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");
        int id = Integer.parseInt(request.getParameter("id"));


        user.setUserName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setId(id);

        UserDao userDao = new UserDao();
        userDao.update(user);

        response.sendRedirect(request.getContextPath() + "/user/list");


    }
}
