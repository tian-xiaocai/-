package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(urlPatterns = {"/findAll","/add","/add.jsp","/list_goods.jsp"})//这是要过滤的页面
public class FilterAll  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤啦。。。");
        HttpServletRequest request=(HttpServletRequest)servletRequest;//强转:把ServletRequest->HttpServletRequest
        HttpSession session = request.getSession();
        String state =(String) session.getAttribute("state");
        if (state!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            HttpServletResponse response=(HttpServletResponse)servletResponse;
            request.setAttribute("errorA","你还没有登录login，请重新登录");
            request.getRequestDispatcher("login.jsp").forward(request,response);

        }


    }

    @Override
    public void destroy() {

    }
}
