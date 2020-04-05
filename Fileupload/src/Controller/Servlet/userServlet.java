package Controller.Servlet;

import Model.Dao.UserDao.bean.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
@WebServlet({"/login"})
public class userServlet  extends HttpServlet {
    Controller.Service.userService userservice=new Controller.Service.userService();
    //此方法是用来处理登录页面login请求的方法
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将您输入的登上信息的用户名和数据库中的用户名密码箱比较，一样的话则登录，否则提示错误
        List<user> list = userservice.findAll();//获取数据库中的用户信息
        //从login中获取input中的内容
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //先判断用户端输入的逻辑：如果用户输入的username是空，或者是空格的话，返回错误，不允许登录
        if (username==null||username.trim().length()==0){//trim()的作用：去掉字符串首尾的空格
            req.setAttribute("nameError","用户名输入错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }
        if (password==null||password.trim().length()==0){//密码为空
            req.setAttribute("passwordError","密码输入错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }
        //怎么判断客户端输入的数据是不是和db中的相同呢?
        for (user user1 : list) {
            //如果两者相同的话则转发、return
            String username_db = user1.getUsername();
            String password_db = user1.getPassword();
            if (username.equals(username_db)&&password.equals(password_db)){
                //用户名和密码都正确
                HttpSession session = req.getSession();
               session.setAttribute("state","状态");
                resp.sendRedirect("findAll");
                return;
            }
        }
        //循环结束了的话，还没有搜寻到相同的用户名和密码，则转发
        req.setAttribute("uerError","无此用户，请注册！");
        req.getRequestDispatcher("login.jsp").forward(req,resp);

    }
}
