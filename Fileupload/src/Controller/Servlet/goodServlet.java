package Controller.Servlet;

import Controller.Service.GoodService;
import Model.Dao.GoodsDao.bean.Goods;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet({"/add","/findAll","/logout"})
public class goodServlet extends HttpServlet {
    GoodService service = new GoodService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if ("/add".equals(uri)) {
            //  System.out.println("我访问的是" + uri);
            //调用addGoods()方法
            addGoods(req, resp);
        } else if ("/findAll".equals(uri)) {
            //调用findAll()方法
            findAll(req, resp);
        }else if ("/logout".equals(uri)) {
            //调用 logout(req, resp);方法
            logout(req, resp);
        }

    }


    private void addGoods(HttpServletRequest req, HttpServletResponse reps) throws IOException, ServletException {
        //使用enctype="multipart/form-data"提交数据不能直接获取
        // String name = req.getParameter("name");
        //System.out.println(name);

        //1.创建磁盘文件元素工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2.创建一个解析器（用于解析上传的数据）
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //
        try {
            Goods good=new Goods();
            //3.解析request
            List<FileItem> items = sfu.parseRequest(req);
            //4、遍历解析到的数据
            for (FileItem item : items) {
                //item存在一种是普通的表单域，另外一种是文件，如图片
                if (item.isFormField()) {//普通的表单域
                    if ("name".equals(item.getFieldName())){//FieldName是和name相等的
                        //获取到数据的字符串
                        String name1 = item.getString();
                        //设置字符编码，把name按照原来的字符编码，变成字符数组
                        byte[] bytes = name1.getBytes("ISO-8859-1");
                        //转成utf-8
                        String name = new String(bytes, "UTF-8");
                        good.setName(name);


                    }else if ("price".equals(item.getFieldName())){
                        good.setPrice(Double.parseDouble(item.getString()));
                    }

                } else {//图片，往数据库里存，要存相对路径，不要存真实的路径
                    String filename = item.getName();
                    System.out.println(filename);
                    String path = "imgs" + File.separator + (System.currentTimeMillis() + "=" + filename);
                    System.out.println(path);
                    ServletContext sc = this.getServletContext();
                    String realPath = sc.getRealPath(path);
                    System.out.println(realPath);
                    //将文件存到硬盘
                    File file = new File(realPath);
                    item.write(file);
                    //将图片路径存入实体类
                    good.setImg(path);
                }
            }
        //在for循环结束之后，打印这个对象
            System.out.println(good);//ok正确！
            //存入把商品信息存入数据库

            service.add(good);
            reps.sendRedirect("findAll");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Goods> list = service.findAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("list_goods.jsp").forward(req, resp);
    }
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect("login.jsp");
    }

}