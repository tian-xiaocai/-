package Model.Dao.UserDao.UserImp;
import Model.Dao.UserDao.Dao.Userdao;
import Model.Dao.UserDao.bean.user;
import Model.JDBC.JDBCUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class userImp implements Userdao {
    @Override
    public List<user> findAll() {
        List<user> list=new ArrayList<>();
        String sql="select * from user";
        PreparedStatement ps=null;
        try {
             ps = JDBCUtils.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                user user=new user(rs.getInt(1),rs.getString(2),rs.getString(3));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
